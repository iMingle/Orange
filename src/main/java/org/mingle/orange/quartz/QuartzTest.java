/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package org.mingle.orange.quartz;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @since 1.0 2014年9月5日
 * @author <a href="mailto:jinml@legendsec.com">靳明雷</a>
 * @version 1.0
 */
public class QuartzTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// Grab the Scheduler instance from the Factory 
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			
			// define the job and tie it to our SimpleJob class
			JobDetail job = JobBuilder.newJob(SimpleJob.class).withIdentity("job", "group").build();
			
			// Trigger the job to run now, and then repeat every 40 seconds
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger", "group").startNow()
					.withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(5)).build();
			
			scheduler.scheduleJob(job, trigger);
			
			// and start it off
			scheduler.start();
			
			try {
				Thread.sleep(6 * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			scheduler.shutdown();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

/**
 * a job
 */
class SimpleJob implements Job {

	/* (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println(context.getTrigger().getJobKey() + ":" + context.getTrigger().getKey());
	}
	
}