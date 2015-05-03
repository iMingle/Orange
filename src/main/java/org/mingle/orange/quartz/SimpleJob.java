/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * a job
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @date 2014年9月9日
 * @version 1.0
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
