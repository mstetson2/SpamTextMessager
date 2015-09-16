package com.yellowmoss.spamtextmessager;

import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class StartSpamScreen extends Activity {

	Button btnSendSMS;
	EditText txtPhoneNo;
	EditText txtMessage;
	EditText spamAmount;
	int messageCount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_spam_screen);

		btnSendSMS = (Button) findViewById(R.id.btnSendSMS);
		txtPhoneNo = (EditText) findViewById(R.id.txtPhoneNo);
		txtMessage = (EditText) findViewById(R.id.txtMessage);
		spamAmount = (EditText) findViewById(R.id.spamAmountEdit);

	}

	public void startSendSMS(View v) {
		String phoneNo = txtPhoneNo.getText().toString();
		String message = txtMessage.getText().toString();
		String stringspamAmount = spamAmount.getText().toString();

		String newMessage = message;
		String newphoneNo = phoneNo;
		String newstringspamAmount = stringspamAmount;

		final Intent spamIntent = new Intent(getApplicationContext(),
				SpamActivity.class);

		spamIntent.putExtra("messageString", newMessage);
		spamIntent.putExtra("phoneNoString", newphoneNo);
		spamIntent.putExtra("spamAmountString", newstringspamAmount);

		if (phoneNo.length() > 0 && message.length() > 0 && newstringspamAmount.length() > 0) {

			final Context context = this;
			// public void dialogBox(View view) {

			AlertDialog.Builder builder = new AlertDialog.Builder(context);

			// Add the buttons
			builder.setPositiveButton("Continue",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							// User clicked OK button

							startActivity(spamIntent);
						}
					});
			builder.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							// user clicked negative

							Toast.makeText(getBaseContext(), "Cancelled",
									Toast.LENGTH_SHORT).show();
							startActivity(new Intent(getApplicationContext(),
									StartSpamScreen.class));
						}
					});

			// Set other dialog properties
			builder.setMessage(
					"You cannot cancel past this point! If you are on JellyBean, just click Allow when prompted. It will take a few minutes for all texts to send.")
					.setTitle("Continue?");

			// Create the AlertDialog
			AlertDialog dialog = builder.create();
			// Show Dialog
			dialog.show();

		}

		else
			Toast.makeText(
					getBaseContext(),
					"Make sure the phone number, message, and the amount of texts to spam have values!",
					Toast.LENGTH_SHORT).show();
	}

	public void startHelp(View v) {
		startActivity(new Intent(getApplicationContext(), Help.class));
	}
}
