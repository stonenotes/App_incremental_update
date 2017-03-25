package com.stonenotes.bsdiff;

public class Bsdiff {

	static {
		System.loadLibrary("Bsdiff");
	}
	
	public static native void bsdiff(String oldfile, String newfile, String patchfile);
	
}
