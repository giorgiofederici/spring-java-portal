package com.giorgiofederici.sjp.showcases.ocs.activiti.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class ShopperTaskListener implements TaskListener {

	private static final long serialVersionUID = 4301702446405439215L;

	// Log all transaction data at the Activiti Level

	@Override
	public void notify(DelegateTask task) {
		task.setVariable("var", "Shopping Logging Enabled");

	}

}
