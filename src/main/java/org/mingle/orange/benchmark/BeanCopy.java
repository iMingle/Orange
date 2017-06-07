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

package org.mingle.orange.benchmark;

import com.baidu.unbiz.easymapper.MapperFactory;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import net.sf.cglib.beans.BeanCopier;
import org.apache.commons.beanutils.PropertyUtils;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.openjdk.jmh.annotations.*;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;

/**
 * 测试bean拷贝的性能
 * java -jar target/practice-0.0.1-SNAPSHOT.jar org.mingle.orange.benchmark.BeanCopy
 *
 * @author mingle
 */
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(1)
@Threads(1)
@Measurement(iterations = 1, batchSize = 100)
@Warmup(iterations = 1)
@BenchmarkMode(Mode.All)
public class BeanCopy {
    private static BeanCopier beanCopier;
    private static Mapper mapper;
    private static com.baidu.unbiz.easymapper.Mapper easyMapper;

    public static void main(String[] args) throws Exception {
        BeanCopy copy = new BeanCopy();
        copy.setup();
        copy.easyMapper();
        System.out.println(copy.getDest());
    }

    private Src src = new Src();
    @Getter private Dest dest = new Dest();

    @Setup
    public void setup() {
        beanCopier = BeanCopier.create(Src.class, Dest.class, false);
        mapper = new DozerBeanMapper();
        easyMapper = MapperFactory.getCopyByRefMapper();

        src.setId(1);
        src.setName("mingle");
        src.setSex((short) 1);
        src.setEmail("mingle@yeah.net");
        src.setPhone("18610887688");
    }

    @TearDown
    public void tearDown() {
        src = null;
        dest = null;
    }

    @Benchmark
    public void beanSet() {
        dest.setId(src.getId());
        dest.setName(src.getName());
        dest.setSex(src.getSex());
        dest.setEmail(src.getEmail());
        dest.setPhone(src.getPhone());
    }

    @Benchmark
    public void propertyUtils() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        PropertyUtils.copyProperties(dest, src);
    }

    @Benchmark
    public void beanUtils() throws InvocationTargetException, IllegalAccessException {
        org.apache.commons.beanutils.BeanUtils.copyProperties(dest, src);
    }

    @Benchmark
    public void springBeanUtils() {
        BeanUtils.copyProperties(src, dest);
    }

    @Benchmark
    public void dozer() {
        dest = mapper.map(src, Dest.class);
    }

    @Benchmark
    public void easyMapper() {
        dest = easyMapper.mapClass(Src.class, Dest.class).register().map(src, Dest.class);
    }

    @Benchmark
    public void cglib() {
        beanCopier.copy(src, dest, null);
    }

    @Data
    public static class Src {
        private int id;
        private String name;
        private short sex;
        private String email;
        private String phone;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @ToString(callSuper = true)
    public static class Dest extends Src {
    }
}
