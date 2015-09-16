package com.yellowmoss.spamtextmessager;

import android.os.Bundle;
import android.content.Intent;
import android.app.Activity;

public class DebugScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_debug_screen);

		startActivity(new Intent(getApplicationContext(), SpamActivity.class));

	}
}
