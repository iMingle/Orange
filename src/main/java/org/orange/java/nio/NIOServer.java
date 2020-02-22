/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.orange.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author mingle
 */
public class NIOServer {
    //通道管理器
    private Selector selector;

    /**
     * 获得一个ServerSocket通道，并对该通道做一些初始化的工作
     *
     * @param port 绑定的端口号
     * @throws IOException
     */
    public void initServer(int port) throws IOException {
        // 获得一个ServerSocket通道
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        // 设置通道为非阻塞
        serverChannel.configureBlocking(false);
        // 将该通道对应的ServerSocket绑定到port端口
        serverChannel.socket().bind(new InetSocketAddress(port));
        // 获得一个通道管理器
        this.selector = Selector.open();
        //将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_ACCEPT事件,注册该事件后，
        //当该事件到达时，selector.select()会返回，如果该事件没到达selector.select()会一直阻塞。
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    /**
     * 采用轮询的方式监听selector上是否有需要处理的事件，如果有，则进行处理
     *
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    private void listen() throws IOException {
        System.out.println("服务端启动成功！");
        // 轮询访问selector
        while (true) {
            //当注册的事件到达时，方法返回；否则,该方法会一直阻塞
            selector.select();
            // 获得selector中选中的项的迭代器，选中的项为注册的事件
            Iterator<SelectionKey> it = this.selector.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey key = it.next();
                // 删除已选的key,以防重复处理
                it.remove();
                // 客户端请求连接事件
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    // 获得和客户端连接的通道
                    SocketChannel channel = server.accept();
                    // 设置成非阻塞
                    channel.configureBlocking(false);

                    //在这里可以给客户端发送信息哦
                    channel.write(ByteBuffer.wrap("发送信息".getBytes()));
                    //在和客户端连接成功之后，为了可以接收到客户端的信息，需要给通道设置读的权限。
                    channel.register(this.selector, SelectionKey.OP_READ);

                    // 获得了可读的事件
                } else if (key.isReadable()) {
                    read(key);
                }

            }

        }
    }

    /**
     * 处理读取客户端发来的信息的事件
     *
     * @param key
     * @throws IOException
     */
    public void read(SelectionKey key) throws IOException {
        // 服务器可读取消息:得到事件发生的Socket通道
        SocketChannel channel = (SocketChannel) key.channel();
        // 创建读取的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(12);
        channel.read(buffer);
        byte[] data = buffer.array();
        System.out.println("服务端收到信息：" + new String(data));
        ByteBuffer outBuffer = ByteBuffer.wrap(data);
        channel.write(outBuffer);// 将消息回送给客户端
    }

    /**
     * 启动服务端测试
     *
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        NIOServer server = new NIOServer();
        server.initServer(8000);
        server.listen();
    }
}
