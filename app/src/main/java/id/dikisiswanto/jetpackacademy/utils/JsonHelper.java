package id.dikisiswanto.jetpackacademy.utils;

import android.app.Application;

import java.io.IOException;
import java.io.InputStream;

public class JsonHelper {
	private Application application;

	public JsonHelper(Application application) {
		this.application = application;
	}

	private String parsingFileToString(String filename) {
		try {
			InputStream is = application.getAssets().open(filename);
			byte[] buffer = new byte[is.available()];
			is.read(buffer);
			is.close();
			return new String(buffer);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}


}
