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

package org.mingle.orange.quartz;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author mingle
 */
public class QuartzTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            // Grab the Scheduler instance from the Factory 
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            
            // and start it off
            scheduler.start();
            
            // define the job and tie it to our SimpleJob class
            JobDetail job = JobBuilder.newJob(SimpleJob.class).withIdentity("job", "group").build();
            
            // Trigger the job to run now, and then repeat every 5 seconds
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger", "group").startNow()
                    .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(5)).build();
            
            scheduler.scheduleJob(job, trigger);
            
            try {
                System.out.println(Thread.activeCount());    // 13
                Thread.sleep(10L * 1000L);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            scheduler.shutdown();
            // scheduler is a alone thread
            System.out.println(Thread.activeCount());        // 12
        } catch (SchedulerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
