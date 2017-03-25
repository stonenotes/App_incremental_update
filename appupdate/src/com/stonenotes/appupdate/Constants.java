package com.stonenotes.appupdate;

import java.io.File;

import android.os.Environment;

public class Constants {

	public static final String PATCH_FILE = "apk.patch";

	public static final String PACKAGE_NAME = "com.dongnaoedu.appupdate";

	public static final String SD_CARD = Environment
			.getExternalStorageDirectory() + File.separator;

	// 新版本apk的目录
	public static final String NEW_APK_PATH = SD_CARD + "apk_new.apk";

	public static final String PATCH_FILE_PATH = SD_CARD + PATCH_FILE;

}
