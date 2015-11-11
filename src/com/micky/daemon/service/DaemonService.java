package com.micky.daemon.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.micky.daemon.Daemon;

public class DaemonService extends Service {
	private static final String TAG = "DaemonService";
	private static int count = 0;

	@Override
	public void onCreate() {
		super.onCreate();
		Daemon.run(this, DaemonService.class, Daemon.INTERVAL_ONE_MINUTE * 2);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(TAG, "onStartCommand");
		
		startService(new Intent(this, MsgService.class));
//		new Thread(new OutputRunnable()).start();
		return super.onStartCommand(intent, START_STICKY, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroy");
	}

	class OutputRunnable implements Runnable {

		@Override
		public void run() {
			count = 0;
			output();
		}
	}

	public void output() {

		HttpURLConnection conn = null;
		while (true) {
			try {
				URL url = new URL("http://192.168.190.107:8080/CommonProject/ServletTest?page=" + count++);
				conn = (HttpURLConnection) url.openConnection();
				conn.setConnectTimeout(5 * 1000);
				conn.setRequestMethod("GET");
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

				String lines = "";
				String ss = "";
				while((lines = in.readLine()) != null){
					ss += lines;
				}
				Log.d(TAG, ss);

				Thread.sleep(3000);

			} catch (Exception  e) {
				Log.e(TAG, e.getMessage(), e);
			} finally {
				conn.disconnect();
			}
		}
	}
}
