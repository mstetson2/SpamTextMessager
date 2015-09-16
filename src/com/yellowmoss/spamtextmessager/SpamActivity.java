package com.yellowmoss.spamtextmessager;

import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

public class SpamActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spam);

	}

	@Override
	protected void onStart() {
		super.onStart();

		int messageCount = 0;
		Bundle extras = getIntent().getExtras();
		String newMessage = extras.getString("messageString");
		String newphoneNo = extras.getString("phoneNoString");
		String stringspamAmount = extras.getString("spamAmountString");
		int realspamAmount = Integer.parseInt(stringspamAmount);

		if (realspamAmount <= 25) {
			while (realspamAmount >= messageCount) {
				sendSMS(newphoneNo, newMessage);
				++messageCount;

			}
		} 
		else
		{
			Toast.makeText(
					getBaseContext(),
					"You can only send 25 messages at once.",
					Toast.LENGTH_SHORT).show();
			startActivity(new Intent(getApplicationContext(), StartSpamScreen.class));
		}
	}

	private void sendSMS(String newphoneNo, String newMessage) {
		PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this,
				StartSpamScreen.class), 0);
		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(newphoneNo, null, newMessage, pi, null);
	}

}
