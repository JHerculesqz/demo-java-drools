package com.huawei.client.provider;

import com.huawei.client.provider.subprovider.SubProvider;

public class MainProvider {
	// #region hello

	public static String hello() {
		return SubProvider.hello();
	}

	// #endregion
}
