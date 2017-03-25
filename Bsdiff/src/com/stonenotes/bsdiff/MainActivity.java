package com.stonenotes.bsdiff;

import java.io.File;
import java.io.IOException;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends FragmentActivity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById(R.id.btn_diff_path).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				diffApk();
			}
		});
	}
	
	private void diffApk() {
		EditText oldEdit= (EditText) findViewById(R.id.edt_oldfile);
		EditText newEdit = (EditText) findViewById(R.id.edt_newfile);
		EditText pathEdit = (EditText) findViewById(R.id.edt_pathfile);
		
		String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath();
		final String oldfile = sdcard + "/" + oldEdit.getText().toString().trim();
		final String newfile = sdcard + "/" + newEdit.getText().toString().trim();
		final String patchfile = sdcard + "/" + pathEdit.getText().toString().trim();
		Log.i("stone", "oldfile: " + oldfile);
		Log.i("stone", "newfile: " + newfile);
		Log.i("stone", "patchfile: " + patchfile);
		File f = new File(patchfile);
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Log.i("stone", "start.....");
				Bsdiff.bsdiff(oldfile, newfile, patchfile);
				Log.i("stone", "end.....");
			}
		}).start();
		
	}
	
}
