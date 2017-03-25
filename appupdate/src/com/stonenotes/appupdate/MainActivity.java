package com.stonenotes.appupdate;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new ApkUpdateTask().execute();
			}
		});
		
	}

	class ApkUpdateTask extends AsyncTask<Void, Void, Boolean>{

		@Override
		protected Boolean doInBackground(Void... params) {
			try {
				Log.d("stone", "开始合并...");
				//获取当前应用的apk文件/data/app/app
				String oldfile = ApkUtils.getSourceApkPath(MainActivity.this, getPackageName());
				//2.合并得到最新版本的APK文件
				String newfile = Constants.NEW_APK_PATH;
				String patchfile = Constants.PATCH_FILE_PATH;
				BsPatch.patch(oldfile, newfile, patchfile);
				
				Log.d("stone", "oldfile:"+oldfile);
				Log.d("stone", "newfile:"+newfile);
				Log.d("stone", "patchfile:"+patchfile);
				Log.d("stone", "合并完毕...");
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			
			return true;
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			//3.安装
			if(result){
				ApkUtils.installApk(MainActivity.this, Constants.NEW_APK_PATH);
			}
		}
		
	}
}
