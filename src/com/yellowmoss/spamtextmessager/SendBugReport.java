package com.yellowmoss.spamtextmessager;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.content.Intent;
import android.os.Build;

public class SendBugReport extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_bug_report);
	}

	public void startSendBugReport(View view) {
		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
		emailIntent.setType("message/rfc822");
		emailIntent.putExtra(Intent.EXTRA_EMAIL,
				new String[] { "yellowmossstudios@gmail.com" });
		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
				"Bug Report: Spam Text Messager");
		emailIntent.putExtra(
				android.content.Intent.EXTRA_TEXT,
				("\n\n\n" + "DEVICE INFO:\n" + "\n OS Version: "
						+ System.getProperty("os.version") + "("
						+ android.os.Build.VERSION.INCREMENTAL + ")"
						+ "\n OS API Level: "
						+ android.os.Build.VERSION.SDK_INT
						+ "\nCompany and brand: " + android.os.Build.BRAND
						+ android.os.Build.MANUFACTURER + "\n Device: "
						+ android.os.Build.DEVICE + "\n Model (and Product): "
						+ android.os.Build.MODEL + android.os.Build.PRODUCT));
		startActivity(Intent.createChooser(emailIntent, "Choose Email Client"));
	}

}
