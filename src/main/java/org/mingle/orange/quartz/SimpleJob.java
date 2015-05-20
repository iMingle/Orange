/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * a job
 * 
 * @since 1.8
 * @author Mingle
 */
public class SimpleJob implements Job {

	/* (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println(context.getTrigger().getJobKey() + ":" + context.getTrigger().getKey());
	}

}
