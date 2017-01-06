package com.huawei.client.provider.subprovider;

import java.util.ArrayList;
import java.util.List;

import com.huawei._1_fw.component.drools.DroolsUtils;
import com.huawei._1_fw.component.drools.IDroolsInitCallback;
import com.huawei._1_fw.component.drools.IDroolsRunCallback;
import com.huawei.client.provider.subprovider.vo.TestVo;

public class SubProvider {
	// #region hello

	public static String hello() {
		// 1.initEngine
		DroolsUtils.initEngine(new IDroolsInitCallback() {
			@Override
			public List<String> getDrlFilePaths() {
				List<String> lstDrlFilePath = new ArrayList<String>();
				lstDrlFilePath
						.add("C:/1.Monkey/1.Research/4.SCMGit/demo-java-drools/src/main/resources/rules/addpoint.drl");
				lstDrlFilePath
						.add("C:/1.Monkey/1.Research/4.SCMGit/demo-java-drools/src/main/resources/rules/subpoint.drl");
				lstDrlFilePath
						.add("C:/1.Monkey/1.Research/4.SCMGit/demo-java-drools/src/main/resources/rules/test.drl");
				return lstDrlFilePath;
			}
		});

		// 2.executeEngine
		DroolsUtils.run(new IDroolsRunCallback<TestVo>() {
			@Override
			public TestVo run() {
				TestVo oTestVo = new TestVo();
				oTestVo.setUserName("hello kity");
				oTestVo.setBackMondy(100d);
				oTestVo.setBuyMoney(500d);
				oTestVo.setBackNums(1);
				oTestVo.setBuyNums(5);
				oTestVo.setBillThisMonth(5);
				oTestVo.setBirthDay(true);
				oTestVo.setPoint(0l);
				return oTestVo;
			}
		});

		return "ok...";
	}

	// #endregion
}
