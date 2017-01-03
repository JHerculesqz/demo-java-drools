package com.huawei.client.provider;

import org.activiti.engine.RuntimeService;

import com.huawei._1_fw.core.ioc.IOCUtils;

public class MainProvider {
	// #region hello

	public static String hello() {
		RuntimeService oRuntimeService = IOCUtils.getInstance().getBean(RuntimeService.class);
		oRuntimeService.startProcessInstanceByKey("WF1");
		return "ok";
	}

	// #endregion
}
