package com.mohammadag.disablecleardefaultsdialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class DisableClearDefaultsDialog implements IXposedHookZygoteInit {
	@Override
	public void initZygote(StartupParam startupParam) throws Throwable {
		Class<?> ResolverGuideActivity = XposedHelpers.findClass("com.android.internal.app.ResolverGuideActivity", null);
		
		XposedHelpers.findAndHookMethod(ResolverGuideActivity, "onCreate", Bundle.class, new XC_MethodHook() {
			@Override
			protected void afterHookedMethod(MethodHookParam param) throws Throwable {
				Activity activity = (Activity) param.thisObject;
				
			    Intent localIntent = (Intent) XposedHelpers.callMethod(param.thisObject, "makeMyIntent");
			    if (localIntent != null)
			    	activity.startActivity(localIntent);
				
				activity.finish();
			}
		});
	}

}
