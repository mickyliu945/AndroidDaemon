package com.micky.daemon.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MsgService extends Service {
	
	private static final String TAG = "MsgService";
	
	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(TAG, "MsgService onCreate");
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
}
