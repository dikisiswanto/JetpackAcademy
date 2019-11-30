package id.dikisiswanto.jetpackacademy.utils;

import android.app.Application;
import android.content.Context;

import androidx.test.runner.AndroidJUnitRunner;

import id.dikisiswanto.jetpackacademy.TestApp;

public class MovieTestRunner extends AndroidJUnitRunner {
	@Override
	public Application newApplication(ClassLoader cl, String className, Context context)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		return super.newApplication(cl, TestApp.class.getName(), context);
	}
}
