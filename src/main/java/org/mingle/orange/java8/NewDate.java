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

package org.mingle.orange.java8;

import java.time.*;
import java.time.chrono.ChronoLocalDateTime;
import java.time.chrono.Chronology;
import java.time.chrono.HijrahChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.IsoFields;
import java.util.Date;
import java.util.Locale;

/**
 * 新Date API
 *
 * @author mingle
 */
public class NewDate {

    public static void main(String[] args) throws InterruptedException {
        /**
         * Clock类提供了访问当前日期和时间的方法,Clock是时区敏感的,
         * 可以用来取代System.currentTimeMillis()来获取当前的微秒数.
         * 某一个特定的时间点也可以使用Instant类来表示,Instant类也可以用来创建老的java.util.Date对象.
         */
        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis();
        System.out.println(millis);
        clock = Clock.systemUTC();
        System.out.println(clock.millis());
        clock = Clock.system(ZoneId.of("Europe/Paris")); //巴黎时区
        System.out.println(clock.millis());
        clock = Clock.system(ZoneId.of("Asia/Shanghai")); //上海时区
        System.out.println(clock.millis());
        clock = Clock.fixed(Instant.now(), ZoneId.of("Asia/Shanghai"));//固定上海时区时钟
        System.out.println(clock.millis());
        Thread.sleep(500);
        System.out.println(clock.millis()); //不变 即时钟在那一个点不动
        clock = Clock.offset(Clock.systemUTC(), Duration.ofSeconds(20));
        System.out.println(clock.millis());

        clock = Clock.systemUTC();
        Instant instant = clock.instant();
        System.out.println(instant);
        instant = Instant.now();
        System.out.println(instant);
        System.out.println(instant.getEpochSecond()); //精确到秒 得到相对于1970-01-01 00:00:00 UTC的一个时间
        System.out.println(instant.toEpochMilli()); //精确到毫秒
        instant = Instant.now(clock);
        System.out.println(instant);
        Date legacyDate = Date.from(instant);   // legacy java.util.Date
        System.out.println(legacyDate);

        /**
         * Timezones 时区
         *
         * 在新API中时区使用ZoneId来表示.时区可以很方便的使用静态方法of来获取到. 
         * 时区定义了到UTS时间的时间差,在Instant时间点对象到本地日期对象之间转换的时候是极其重要的.
         */
        System.out.println(ZoneId.getAvailableZoneIds());
        // prints all available timezone ids

        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");
        System.out.println(zone1.getRules()); // ZoneRules[currentStandardOffset=+01:00]
        System.out.println(zone2.getRules()); // ZoneRules[currentStandardOffset=-03:00]

        /**
         * 带有时区的date-time 存储纳秒、时区和时差（避免与本地date-time歧义）。
         * API和LocalDateTime类似，只是多了时差(如2013-12-20T10:35:50.711+08:00[Asia/Shanghai])
         */
        ZonedDateTime zoneNnow = ZonedDateTime.now();
        System.out.println(zoneNnow);
        ZonedDateTime zonedNow2 = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
        System.out.println(zonedNow2);
        ZonedDateTime z1 = ZonedDateTime.parse("2015-11-20T23:59:59Z[Europe/Paris]");
        System.out.println(z1);

        /**
         * 两个瞬时时间的时间段
         */
        Duration duration1 = Duration.between(Instant.ofEpochMilli(System.currentTimeMillis() - 12323123), Instant.now());
        //得到相应的时差
        System.out.println(duration1.toDays());
        System.out.println(duration1.toHours());
        System.out.println(duration1.toMinutes());
        System.out.println(duration1.toMillis());
        System.out.println(duration1.toNanos());
        //1天时差 类似的还有如ofHours()
        Duration duration2 = Duration.ofDays(1);
        System.out.println(duration2.toDays());

        /**
         * 提供对java.util.Calendar的替换，提供对年历系统的支持
         */
        Chronology c = HijrahChronology.INSTANCE;
        ChronoLocalDateTime d = c.localDateTime(LocalDateTime.now());
        System.out.println(d);

        /**
         * 新旧日期转换
         */
        Instant toInstant = new Date().toInstant();
        Date date = Date.from(toInstant);
        System.out.println(toInstant);
        System.out.println(date);

        /**
         * LocalTime 本地时间
         *
         * LocalTime 定义了一个没有时区信息的时间,例如 晚上10点,或者 17:30:15.
         * 下面的例子使用前面代码创建的时区创建了两个本地时间.之后比较时间并以小时和分钟为单位计算两个时间的时间差
         */
        LocalTime now1 = LocalTime.now(zone1);
        LocalTime now2 = LocalTime.now(zone2);

        System.out.println(now1.isBefore(now2));  // false

        long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
        long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);

        System.out.println(hoursBetween);       // -3
        System.out.println(minutesBetween);     // -239

        /**
         * LocalTime 提供了多种工厂方法来简化对象的创建,包括解析时间字符串
         */
        LocalTime late = LocalTime.of(23, 59, 59);
        System.out.println(late);       // 23:59:59

        DateTimeFormatter germanFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.GERMAN);

        LocalTime leetTime = LocalTime.parse("13:37", germanFormatter);
        System.out.println(leetTime);   // 13:37

        /**
         * LocalDate 本地日期
         *
         * LocalDate 表示了一个确切的日期,比如 2014-03-11.该对象值是不可变的,用起来和LocalTime基本一致.
         * 下面的例子展示了如何给Date对象加减天/月/年.另外要注意的是这些对象是不可变的,操作返回的总是一个新实例
         */
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        LocalDate yesterday = tomorrow.minusDays(2);

        LocalDate independenceDay = LocalDate.of(2014, Month.JULY, 4);
        DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();
        System.out.println(dayOfWeek);    // FRIDAY
        System.out.println(yesterday);

        /**
         * 从字符串解析一个LocalDate类型和解析LocalTime一样简单
         */
        germanFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(Locale.GERMAN);

        LocalDate xmas = LocalDate.parse("24.12.2014", germanFormatter);
        System.out.println(xmas);   // 2014-12-24

        /**
         * LocalDateTime 本地日期时间
         *
         * LocalDateTime 同时表示了时间和日期,相当于前两节内容合并到一个对象上了.
         * LocalDateTime和LocalTime还有LocalDate一样,都是不可变的.LocalDateTime提供了一些能访问具体字段的方法
         */
        LocalDateTime sylvester = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59);

        dayOfWeek = sylvester.getDayOfWeek();
        System.out.println(dayOfWeek);      // WEDNESDAY

        Month month = sylvester.getMonth();
        System.out.println(month);          // DECEMBER

        long minuteOfDay = sylvester.getLong(ChronoField.MINUTE_OF_DAY);
        System.out.println(minuteOfDay);    // 1439

        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        //自定义时区
        LocalDateTime now3 = LocalDateTime.now(ZoneId.of("Europe/Paris"));
        System.out.println(now3);//会以相应的时区显示日期
        //自定义时钟
        clock = Clock.system(ZoneId.of("Asia/Dhaka"));
        LocalDateTime now4 = LocalDateTime.now(clock);
        System.out.println(now4);//会以相应的时区显示日期
        //不需要写什么相对时间 如java.util.Date年是相对于1900 月是从0开始
        //2013-12-31 23:59
        LocalDateTime d1 = LocalDateTime.of(2013, 12, 31, 23, 59);
        //年月日 时分秒 纳秒
        LocalDateTime d2 = LocalDateTime.of(2013, 12, 31, 23, 59, 59, 11);
        //使用瞬时时间 + 时区
        toInstant = Instant.now();
        LocalDateTime d3 = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        System.out.println(d3);
        //解析String--->LocalDateTime
        LocalDateTime d4 = LocalDateTime.parse("2013-12-31T23:59");
        System.out.println(d4);
        LocalDateTime d5 = LocalDateTime.parse("2013-12-31T23:59:59.999");//999毫秒 等价于999000000纳秒
        System.out.println(d5);
        //使用DateTimeFormatter API 解析 和 格式化
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime d6 = LocalDateTime.parse("2013/12/31 23:59:59", formatter);
        System.out.println(formatter.format(d6));
        //时间获取
        System.out.println(d6.getYear());
        System.out.println(d6.getMonth());
        System.out.println(d6.getDayOfYear());
        System.out.println(d6.getDayOfMonth());
        System.out.println(d6.getDayOfWeek());
        System.out.println(d6.getHour());
        System.out.println(d6.getMinute());
        System.out.println(d6.getSecond());
        System.out.println(d6.getNano());
        //时间增减
        LocalDateTime d7 = d6.minusDays(1);
        LocalDateTime d8 = d7.plus(1, IsoFields.QUARTER_YEARS);

        /**
         * 只要附加上时区信息,就可以将其转换为一个时间点Instant对象,Instant时间点对象可以很容易的转换为老式的java.util.Date
         */
        toInstant = sylvester.atZone(ZoneId.systemDefault()).toInstant();

        legacyDate = Date.from(toInstant);
        System.out.println(legacyDate);     // Wed Dec 31 23:59:59 CET 2014

        /**
         * 格式化LocalDateTime和格式化时间和日期一样的,除了使用预定义好的格式外,我们也可以自己定义格式
         * DateTimeFormatter是不可变的,所以它是线程安全的
         */
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM dd, yyyy - HH:mm");

        LocalDateTime parsed = LocalDateTime.parse("11 03, 2014 - 07:13", dateTimeFormatter);
        String string = dateTimeFormatter.format(parsed);
        System.out.println(string);     // Nov 03, 2014 - 07:13
    }

}
