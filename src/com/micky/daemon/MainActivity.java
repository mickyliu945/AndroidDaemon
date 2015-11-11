package com.micky.daemon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.micky.daemon.service.DaemonService;

public class MainActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		TextView tv = new TextView(this);
		tv.setText("Deamon");
		setContentView(tv);
		
		startService(new Intent(this, DaemonService.class));
//		startService(new Intent(this, MsgService.class));
	}
	
}
