/*
 *
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
 * imitations under the License.
 *
 */

package org.mingle.orange.security;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

/**
 * 生成签名
 * 
 * @author mingle
 */
public class GenerateSign {
    /**
     * 合作方的id
     */
    private static String partnerId = "partnerId1";
    /**
     * 合作方的key,用于生成摘要时的盐
     */
    private static String partnerKey = "key1";
    /**
     * RSA公钥
     */
    private static String dmallRsaPubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC+s79vTHAU4KLcBiRpSzM8aGTp17mBmW0rGj087rEtUkaPaPSlhhMT8pxJWnRZeIA10VYGgqeHzFSkHy9B9XiJkn8im7v3oO4I15k6+z5DAGBRFuoWoV4F14E68t9b3zHGZQPiTRBbzhZ9JeABZnk3aw+kurI9ZJUhl8YzTm3YcwIDAQAB";
    /**
     * 合作方自己的私钥,需要妥善保存
     */
    private static String rsaPrivateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMXROSSBGht/K8dwFj9sRjQ4fTfzf9Yq7NzeWViGnM02UQVpzuM2HvQkIQ7lhSm8FEFQw1D5TSaGKKwwFc2efgWwQ7vFhtCBKhXtZVCnAsc+XIE1CwyXnLMjS9nsshAl96hZk40nDyQC0No2WypewGEP8ntsDPErihuYywklHnlnAgMBAAECgYB2yU5hB01gGZt2ZW5Wo3P8w7cdtBCd0KTvdG1+ZUcfS3CXqZPciJFJ1bAsO9Mc3p3jItZGd43GhmVCEJXnJTdSsFyflyazz1d2BpPvjqpbx6FeH3+CrKSFvSBeorW/uLFEKYhS0nU3B7ZIMb9Nzb5J8//AT7izMTnLh7dfSLhJwQJBAO+NB7OD/gupbz0VIEqOE7+lvC5ZXHsLC6S13HaqDUUk4yyHln8ITFs/gAFg6m5sNemeUhA2OR+9srillx1TGncCQQDTZpMNWfvOCoX+em6PAC3/jwMoJT4dsWwoWqpfF4riENynbgFidh8I90mHHRgBAdRbfXDM+yrVtYHHtRi6emSRAkASx8m8xzJZk7UG4usfLThbJBE2yQa5FsqY5TEdlINp0lSFHzQrTWk/FiFg+kom0hD/+cuRPc8IsASc+U55nBRNAkEAg+J8X1oCA/sAuVA8ZS5hhAv+li8V34Ruy4Y0v/p6BKssXa/9YSX8GqXb01VNQmOXzjQmCVuwz37bJnJOqRfuYQJBAMhl655SSF8P80nz2IY2FHLdXcMH1lvumtydrwvSQSHEwIT0dMbT015chWe8HsiHDpPprbqeAEv4C7rbJ/QX5Tk=";

    private static final String SHA1_SIGN_ALGORITHMS = "SHA1WithRSA";
    private static final String MD5_SIGN_ALGORITHMS = "MD5WithRSA";

    public static void main(String[] args) throws Exception {
        requestDemo();
        validResponseDemo();
    }

    private static void requestDemo() throws Exception {
        JSONObject input = new JSONObject();
        input.put("param1", "param1");
        input.put("param2", "param2");
        JSONArray arr = new JSONArray();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        input.put("item", arr);
        input.put("partner_id", partnerId);

        String signType = "md5";
        // String signType="sha1";
        String sign = sign(input, signType);
        System.out.println("sign=" + sign);
        // 将签名类型和签名结果放入到请求参数中
        input.put("sign_type", signType);
        input.put("sign", sign);
    }

    /**
     * 验证返回结果的签名
     * 
     * @return
     * @throws Exception
     */
    private static boolean validResponseDemo() throws Exception {
        JSONArray arr = new JSONArray();
        arr.add(1);
        arr.add(2);
        arr.add(2);
        String responseStr = "{\"name\":\"mingle\",\"gender\":\"male\",\"item\":[1,2,2],\"sign\":\"dqNAVzHw1vilyLSrakyAm2wxYuTmps77gDio+WiAc6hV7J3ZWXJHFeP88sQl6f9/84l8neleF8e7GC6Y635Ych5IiLBlXc8SK/OKZOEHL80ZYcUXXsoUbz0/jtodMpEfWSlgL/5W290+e7/SK+kQ8KzLbA4dRGnXkcNcrpiFjcU=\",\"sign_type\":\"md5\"}";
        JSONObject data = JSONObject.parseObject(responseStr);
        boolean validate = valid(data);
        System.out.println("validate = " + validate);
        
        return validate;
    }

    private static String sign(JSONObject json, String signType)
            throws Exception {
        // 生成特征字符串
        String specialStr = gen(json, partnerKey);
        if ("md5".equalsIgnoreCase(signType))
            return md5Sign(specialStr, rsaPrivateKey);
        else
            return sha1Sign(specialStr, rsaPrivateKey);
    }

    private static boolean valid(JSONObject json) throws Exception {
        String signType = json.getString("sign_type");

        String specialStr = gen(json, "");
        if ("md5".equalsIgnoreCase(signType)) {
            return md5Verify(specialStr, json.getString("sign"), dmallRsaPubKey);
        } else if ("sha1".equalsIgnoreCase(signType)) {
            return sha1Verify(specialStr, json.getString("sign"), dmallRsaPubKey);
        }

        return false;
    }

    /**
     * MD5WithRSA签名
     * 
     * @param content
     *            待签名数据
     * @param privateKey
     *            私钥
     * @return 签名值
     */
    private static String md5Sign(String content, String privateKey) {
        return sign(content, privateKey, MD5_SIGN_ALGORITHMS);
    }

    /**
     * SHA1WithRSA签名
     * 
     * @param content
     *            待签名数据
     * @param privateKey
     *            私钥
     * @return 签名值
     */
    private static String sha1Sign(String content, String privateKey) {
        return sign(content, privateKey, SHA1_SIGN_ALGORITHMS);
    }

    /**
     * RSA签名
     * 
     * @param content
     *            待签名数据
     * @param privateKey
     *            私钥
     * @param signAlgorithms
     *            签名方式
     * @return 签名值
     */
    private static String sign(String content, String privateKey,
            String signAlgorithms) {
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64
                    .getDecoder().decode(privateKey));
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PrivateKey priKey = keyf.generatePrivate(priPKCS8);

            java.security.Signature signature = java.security.Signature
                    .getInstance(signAlgorithms);

            signature.initSign(priKey);
            signature.update(content.getBytes());

            byte[] signed = signature.sign();

            return Base64.getEncoder().encodeToString(signed);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * MD5WithRSA验签名检查
     * 
     * @param content
     *            待签名数据
     * @param sign
     *            签名值
     * @param pubKey
     *            商户公钥
     * @return 布尔值
     */
    private static boolean md5Verify(String content, String sign, String pubKey) {
        return verify(content, sign, pubKey, MD5_SIGN_ALGORITHMS);
    }

    /**
     * SHA1WithRSA验签名检查
     * 
     * @param content
     *            待签名数据
     * @param sign
     *            签名值
     * @param pubKey
     *            商户公钥
     * @return 布尔值
     */
    private static boolean sha1Verify(String content, String sign, String pubKey) {
        return verify(content, sign, pubKey, SHA1_SIGN_ALGORITHMS);
    }

    /**
     * RSA验签名检查
     * 
     * @param content
     *            待签名数据
     * @param sign
     *            签名值
     * @param pubKey
     *            商户公钥
     * @param signAlgorithms
     *            签名方式
     * @return 布尔值
     */
    private static boolean verify(String content, String sign, String pubKey,
            String signAlgorithms) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] encodedKey = Base64.getDecoder().decode(pubKey);
            PublicKey pk = keyFactory.generatePublic(new X509EncodedKeySpec(
                    encodedKey));

            java.security.Signature signature = java.security.Signature
                    .getInstance(signAlgorithms);

            signature.initVerify(pk);
            signature.update(content.getBytes());

            boolean bverify = signature
                    .verify(Base64.getDecoder().decode(sign));
            return bverify;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 解密
     * 
     * @param content
     *            密文
     * @param private_key
     *            商户私钥
     * @return 解密后的字符串
     */
    @SuppressWarnings("unused")
    private static String decrypt(String content, String private_key)
            throws Exception {
        PrivateKey prikey = getPrivateKey(private_key);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, prikey);

        InputStream ins = new ByteArrayInputStream(Base64.getDecoder().decode(
                content));
        ByteArrayOutputStream writer = new ByteArrayOutputStream();
        // rsa解密的字节大小最多是128，将需要解密的内容，按128位拆开解密
        byte[] buf = new byte[128];
        int bufl;

        while ((bufl = ins.read(buf)) != -1) {
            byte[] block = null;

            if (buf.length == bufl) {
                block = buf;
            } else {
                block = new byte[bufl];
                for (int i = 0; i < bufl; i++) {
                    block[i] = buf[i];
                }
            }

            writer.write(cipher.doFinal(block));
        }

        return new String(writer.toByteArray());
    }

    /**
     * 得到私钥
     * 
     * @param key
     *            密钥字符串（经过base64编码）
     * @throws Exception
     */
    private static PrivateKey getPrivateKey(String key) throws Exception {

        byte[] keyBytes;

        keyBytes = Base64.getDecoder().decode(key);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        return privateKey;
    }

    @SuppressWarnings("unused")
    private static byte[] md5(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(data.getBytes());
        return md.digest();
    }

    @SuppressWarnings("unused")
    private static byte[] sha1(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA1");
        md.update(data.getBytes());
        return md.digest();
    }

    @SuppressWarnings("unused")
    private static String base64Encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    @SuppressWarnings("unused")
    private static byte[] base64Decode(String data) {
        return Base64.getDecoder().decode(data);
    }

    /**
     * 生成特征字符串
     * 
     * @param input
     * @param salt
     * @return
     * @throws Exception
     */
    private static String gen(JSONObject input, String salt)
            throws Exception {
        StringBuilder builder = new StringBuilder();
        Map<String, List<String>> kvs = new HashMap<>();
        List<String> keys = new LinkedList<>();
        fetchLeafKV(input, kvs);

        keys.addAll(kvs.keySet());
        keys.remove("sign_type");
        keys.remove("sign");
        Collections.sort(keys);
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            String value = mergeValues(kvs.get(key));
            if (value != null && !"".equals(value))
                builder.append(key).append("=").append(value).append("&");
        }
        builder.deleteCharAt(builder.length() - 1);
        String specialStr = builder.toString();
        
        return specialStr + salt;
    }

    private static String mergeValues(List<String> vls) {
        String result = null;
        StringBuilder sbVls = new StringBuilder();
        if (vls != null && !vls.isEmpty()) {
            Collections.sort(vls);
            Iterator<String> iterator = vls.iterator();
            while (iterator.hasNext()) {
                Object obj = iterator.next();
                sbVls.append(obj).append("-");
            }
            if (sbVls.length() > 0)
                sbVls.deleteCharAt(sbVls.length() - 1);
            result = sbVls.toString();
        }
        
        return result;
    }

    private static void fetchLeafKV(String key, JSONArray jsonArray,
            Map<String, List<String>> kvs) throws JSONException {
        for (int i = 0; i < jsonArray.size(); i++) {
            Object valObj = jsonArray.get(i);
            if (valObj != null) {
                if ((valObj instanceof JSONObject)) {
                    fetchLeafKV((JSONObject) valObj, kvs);
                } else if ((valObj instanceof JSONArray)) {
                    JSONArray array = (JSONArray) valObj;
                    fetchLeafKV(key, array, kvs);
                } else {
                    List<String> vLst = null;
                    String lKey = key;
                    if (kvs.containsKey(lKey)) {
                        vLst = kvs.get(lKey);
                    } else {
                        vLst = new LinkedList<>();
                        kvs.put(lKey, vLst);
                    }
                    vLst.add(valObj.toString());
                }
            }
        }
    }

    private static void fetchLeafKV(JSONObject jsonObject,
            Map<String, List<String>> kvs) throws JSONException {
        Iterator<String> iterator = jsonObject.keySet().iterator();
        String key = null;
        while (iterator.hasNext()) {
            key = iterator.next();
            Object valObj = jsonObject.get(key);
            if (valObj != null) {
                if ((valObj instanceof JSONObject)) {
                    fetchLeafKV((JSONObject) valObj, kvs);
                } else if ((valObj instanceof JSONArray)) {
                    JSONArray array = (JSONArray) valObj;
                    fetchLeafKV(key, array, kvs);
                } else {
                    List<String> vLst = null;
                    String lKey = key;
                    if (kvs.containsKey(lKey)) {
                        vLst = kvs.get(lKey);
                    } else {
                        vLst = new LinkedList<>();
                        kvs.put(lKey, vLst);
                    }
                    vLst.add(valObj.toString());
                }
            }
        }
    }
}
