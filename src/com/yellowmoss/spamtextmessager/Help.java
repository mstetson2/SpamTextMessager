package com.yellowmoss.spamtextmessager;

import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.content.Intent;

public class Help extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
	}

	public void startDontWork(View v) {
		// startActivity(new Intent(getApplicationContext(), DontWork.class));
	}
	public void startDeveloperInfo(View v)
	{
		setContentView(R.layout.developer_info);
	}
}
