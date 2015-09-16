package com.yellowmoss.spamtextmessager;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

//THIS IS REALLY THE STARTUP/DIALOG CLASS!!
public class SpamActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		final Context context = this;

		AlertDialog.Builder builder = new AlertDialog.Builder(context);

		builder.setPositiveButton("Accept",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						//startActivity(new Intent(getApplicationContext(), StartSpamScreen.class));
						System.out.println("1a");
						startActivity(new Intent(getApplicationContext(), StartSpamScreen2.class));
						System.out.println("1b");
					}
				});
		
		builder.setNegativeButton("Deny and Exit",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						System.out.println("Clicked Exit!");
						finish();
						System.exit(0);
					}
				});

		builder.setMessage(
				"We are not responsibile for any damages from using this app. Every text sent still counts as a normal text. It is reccommended that you and your recipient have an unlimited plan.\n\nClick Accept to continue or Deny to exit.")
				.setTitle("Notice:");

		AlertDialog dialog = builder.create();
		dialog.show();

	}
}
//BELOW IS OLD!!
	/*@Override
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
		} else {
			Toast.makeText(getBaseContext(),
					"You can only send 25 messages at once.",
					Toast.LENGTH_SHORT).show();
			startActivity(new Intent(getApplicationContext(),
					StartSpamScreen.class));
		}
	}

	private void sendSMS(String newphoneNo, String newMessage) {
		PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this,
				StartSpamScreen.class), 0);
		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(newphoneNo, null, newMessage, pi, null);
	}
*/