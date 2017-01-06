package com.huawei._1_fw.component.drools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.drools.compiler.compiler.DroolsParserException;
import org.drools.compiler.compiler.PackageBuilder;
import org.drools.core.RuleBase;
import org.drools.core.StatefulSession;
import org.drools.core.spi.Activation;
import org.drools.core.spi.AgendaFilter;

public class DroolsUtils {
	// #region initEngine

	public static void initEngine(IDroolsInitCallback oIDroolsInitCallback) {
		// 1.oIDroolsCallback.getDrlFilePaths
		List<String> lstDrlFilePath = oIDroolsInitCallback.getDrlFilePaths();

		// 2.initEngine
		System.setProperty("drools.dateformat", "yyyy-MM-dd HH:mm:ss");
		RuleBase oRuleBase = DroolsRuleBaseFactory.getRuleBase();
		try {
			PackageBuilder backageBuilder = getPackageBuilderFromDrlFile(lstDrlFilePath);
			oRuleBase.addPackages(backageBuilder.getPackages());
		} catch (DroolsParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static PackageBuilder getPackageBuilderFromDrlFile(List<String> drlFilePath) throws Exception {
		// 装载测试脚本文件
		List<Reader> readers = readRuleFromDrlFile(drlFilePath);

		PackageBuilder backageBuilder = new PackageBuilder();
		for (Reader r : readers) {
			backageBuilder.addPackageFromDrl(r);
		}

		// 检查脚本是否有问题
		if (backageBuilder.hasErrors()) {
			throw new Exception(backageBuilder.getErrors().toString());
		}

		return backageBuilder;
	}

	private static List<Reader> readRuleFromDrlFile(List<String> drlFilePath) throws FileNotFoundException {
		if (null == drlFilePath || 0 == drlFilePath.size()) {
			return null;
		}

		List<Reader> readers = new ArrayList<Reader>();

		for (String ruleFilePath : drlFilePath) {
			readers.add(new FileReader(new File(ruleFilePath)));
		}

		return readers;
	}

	// #endregion

	// #region reloadEngine

	public static void reloadEngine(IDroolsInitCallback oIDroolsInitCallback) {
		RuleBase oRuleBase = DroolsRuleBaseFactory.getRuleBase();
		org.drools.core.rule.Package[] packages = oRuleBase.getPackages();
		for (org.drools.core.rule.Package pg : packages) {
			oRuleBase.removePackage(pg.getName());
		}

		initEngine(oIDroolsInitCallback);
	}

	// #endregion

	// #region run

	public static <T> void run(IDroolsRunCallback<T> oIDroolsRunCallback) {
		// #region 暂时保留
		// KieServices kieServices = KieServices.Factory.get();
		// KieContainer kieContainer = kieServices.getKieClasspathContainer();
		// KieSession kieSession = kieContainer.newKieSession();
		// kieSession.fireAllRules();
		// kieSession.destroy();
		// #endregion

		RuleBase oRuleBase = DroolsRuleBaseFactory.getRuleBase();
		if (null == oRuleBase.getPackages() || 0 == oRuleBase.getPackages().length) {
			return;
		}

		StatefulSession oStatefulSession = oRuleBase.newStatefulSession();
		oStatefulSession.insert(oIDroolsRunCallback.run());

		// fire
		oStatefulSession.fireAllRules(new AgendaFilter() {
			public boolean accept(Activation activation) {
				return !activation.getRule().getName().contains("_test");
			}
		});

		oStatefulSession.dispose();
	}

	// #endregion
}
