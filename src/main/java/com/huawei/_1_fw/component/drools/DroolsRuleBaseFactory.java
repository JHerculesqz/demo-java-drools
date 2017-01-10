package com.huawei._1_fw.component.drools;

import org.drools.core.RuleBase;
import org.drools.core.RuleBaseFactory;

public class DroolsRuleBaseFactory {
	// #region Fields

	private static RuleBase ruleBase;

	// #endregion

	// #region initRuleBase

	public static RuleBase getRuleBase() {
		if (null == ruleBase) {
			return RuleBaseFactory.newRuleBase();
		}
		return ruleBase;
	}

	public static void setRuleBase(RuleBase oRuleBase) {
		DroolsRuleBaseFactory.ruleBase = oRuleBase;
	}

	// #endregion
}
