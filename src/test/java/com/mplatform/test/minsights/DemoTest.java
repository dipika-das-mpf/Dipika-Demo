package com.mplatform.test.minsights;

import com.mplatform.framework.base.ScriptBase;
import org.testng.annotations.Test;

/**
 * Created by ahmed.neinae on 5/12/17.
 */

public class DemoTest extends ScriptBase {




	@Test(priority = 1)
	public void login() throws Exception {

		reportingSetup("01", "As a user, navigate to mInsights app then login");

		strDTParametersNValues = "InputDataRow=>2";
		//mPlatform().MI_Login().login();

	}

}
