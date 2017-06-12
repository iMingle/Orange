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
 * Benchmark                                           Mode    Cnt       Score    Error   Units
 * BeanCopy.beanSet                                   thrpt         388235.695           ops/ms
 * BeanCopy.beanUtils                                 thrpt              1.408           ops/ms
 * BeanCopy.cglib                                     thrpt         381276.268           ops/ms
 * BeanCopy.dozer                                     thrpt              0.738           ops/ms
 * BeanCopy.easyMapper                                thrpt             11.917           ops/ms
 * BeanCopy.propertyUtils                             thrpt            343.724           ops/ms
 * BeanCopy.springBeanUtils                           thrpt           3216.029           ops/ms
 * BeanCopy.beanSet                                    avgt             ≈ 10⁻⁶            ms/op
 * BeanCopy.beanUtils                                  avgt              0.669            ms/op
 * BeanCopy.cglib                                      avgt             ≈ 10⁻⁶            ms/op
 * BeanCopy.dozer                                      avgt              1.346            ms/op
 * BeanCopy.easyMapper                                 avgt              0.072            ms/op
 * BeanCopy.propertyUtils                              avgt              0.003            ms/op
 * BeanCopy.springBeanUtils                            avgt             ≈ 10⁻⁴            ms/op
 * BeanCopy.beanSet                                  sample  23570      ≈ 10⁻⁴            ms/op
 * BeanCopy.beanSet:beanSet·p0.00                    sample             ≈ 10⁻⁵            ms/op
 * BeanCopy.beanSet:beanSet·p0.50                    sample             ≈ 10⁻⁴            ms/op
 * BeanCopy.beanSet:beanSet·p0.90                    sample             ≈ 10⁻⁴            ms/op
 * BeanCopy.beanSet:beanSet·p0.95                    sample             ≈ 10⁻⁴            ms/op
 * BeanCopy.beanSet:beanSet·p0.99                    sample             ≈ 10⁻⁴            ms/op
 * BeanCopy.beanSet:beanSet·p0.999                   sample              0.006            ms/op
 * BeanCopy.beanSet:beanSet·p0.9999                  sample              0.040            ms/op
 * BeanCopy.beanSet:beanSet·p1.00                    sample              0.090            ms/op
 * BeanCopy.beanUtils                                sample   1413       0.706 ±  0.023   ms/op
 * BeanCopy.beanUtils:beanUtils·p0.00                sample              0.428            ms/op
 * BeanCopy.beanUtils:beanUtils·p0.50                sample              0.666            ms/op
 * BeanCopy.beanUtils:beanUtils·p0.90                sample              0.962            ms/op
 * BeanCopy.beanUtils:beanUtils·p0.95                sample              1.066            ms/op
 * BeanCopy.beanUtils:beanUtils·p0.99                sample              1.694            ms/op
 * BeanCopy.beanUtils:beanUtils·p0.999               sample              3.817            ms/op
 * BeanCopy.beanUtils:beanUtils·p0.9999              sample              4.375            ms/op
 * BeanCopy.beanUtils:beanUtils·p1.00                sample              4.375            ms/op
 * BeanCopy.cglib                                    sample  23378      ≈ 10⁻⁴            ms/op
 * BeanCopy.cglib:cglib·p0.00                        sample             ≈ 10⁻⁵            ms/op
 * BeanCopy.cglib:cglib·p0.50                        sample             ≈ 10⁻⁴            ms/op
 * BeanCopy.cglib:cglib·p0.90                        sample             ≈ 10⁻⁴            ms/op
 * BeanCopy.cglib:cglib·p0.95                        sample             ≈ 10⁻⁴            ms/op
 * BeanCopy.cglib:cglib·p0.99                        sample             ≈ 10⁻⁴            ms/op
 * BeanCopy.cglib:cglib·p0.999                       sample              0.007            ms/op
 * BeanCopy.cglib:cglib·p0.9999                      sample              0.022            ms/op
 * BeanCopy.cglib:cglib·p1.00                        sample              0.603            ms/op
 * BeanCopy.dozer                                    sample    823       1.216 ±  0.044   ms/op
 * BeanCopy.dozer:dozer·p0.00                        sample              0.821            ms/op
 * BeanCopy.dozer:dozer·p0.50                        sample              1.098            ms/op
 * BeanCopy.dozer:dozer·p0.90                        sample              1.690            ms/op
 * BeanCopy.dozer:dozer·p0.95                        sample              1.964            ms/op
 * BeanCopy.dozer:dozer·p0.99                        sample              2.491            ms/op
 * BeanCopy.dozer:dozer·p0.999                       sample              4.071            ms/op
 * BeanCopy.dozer:dozer·p0.9999                      sample              4.071            ms/op
 * BeanCopy.dozer:dozer·p1.00                        sample              4.071            ms/op
 * BeanCopy.easyMapper                               sample  15175       0.066 ±  0.001   ms/op
 * BeanCopy.easyMapper:easyMapper·p0.00              sample              0.046            ms/op
 * BeanCopy.easyMapper:easyMapper·p0.50              sample              0.057            ms/op
 * BeanCopy.easyMapper:easyMapper·p0.90              sample              0.094            ms/op
 * BeanCopy.easyMapper:easyMapper·p0.95              sample              0.106            ms/op
 * BeanCopy.easyMapper:easyMapper·p0.99              sample              0.134            ms/op
 * BeanCopy.easyMapper:easyMapper·p0.999             sample              0.948            ms/op
 * BeanCopy.easyMapper:easyMapper·p0.9999            sample              2.648            ms/op
 * BeanCopy.easyMapper:easyMapper·p1.00              sample              2.900            ms/op
 * BeanCopy.propertyUtils                            sample  21995       0.003 ±  0.001   ms/op
 * BeanCopy.propertyUtils:propertyUtils·p0.00        sample              0.002            ms/op
 * BeanCopy.propertyUtils:propertyUtils·p0.50        sample              0.002            ms/op
 * BeanCopy.propertyUtils:propertyUtils·p0.90        sample              0.004            ms/op
 * BeanCopy.propertyUtils:propertyUtils·p0.95        sample              0.005            ms/op
 * BeanCopy.propertyUtils:propertyUtils·p0.99        sample              0.012            ms/op
 * BeanCopy.propertyUtils:propertyUtils·p0.999       sample              0.046            ms/op
 * BeanCopy.propertyUtils:propertyUtils·p0.9999      sample              0.432            ms/op
 * BeanCopy.propertyUtils:propertyUtils·p1.00        sample              0.853            ms/op
 * BeanCopy.springBeanUtils                          sample  24502       0.001 ±  0.001   ms/op
 * BeanCopy.springBeanUtils:springBeanUtils·p0.00    sample             ≈ 10⁻⁴            ms/op
 * BeanCopy.springBeanUtils:springBeanUtils·p0.50    sample             ≈ 10⁻⁴            ms/op
 * BeanCopy.springBeanUtils:springBeanUtils·p0.90    sample             ≈ 10⁻³            ms/op
 * BeanCopy.springBeanUtils:springBeanUtils·p0.95    sample              0.001            ms/op
 * BeanCopy.springBeanUtils:springBeanUtils·p0.99    sample              0.003            ms/op
 * BeanCopy.springBeanUtils:springBeanUtils·p0.999   sample              0.015            ms/op
 * BeanCopy.springBeanUtils:springBeanUtils·p0.9999  sample              0.150            ms/op
 * BeanCopy.springBeanUtils:springBeanUtils·p1.00    sample              3.707            ms/op
 * BeanCopy.beanSet                                      ss              0.003            ms/op
 * BeanCopy.beanUtils                                    ss              2.026            ms/op
 * BeanCopy.cglib                                        ss              0.004            ms/op
 * BeanCopy.dozer                                        ss              4.722            ms/op
 * BeanCopy.easyMapper                                   ss              0.535            ms/op
 * BeanCopy.propertyUtils                                ss              0.205            ms/op
 * BeanCopy.springBeanUtils                              ss              0.037            ms/op
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
