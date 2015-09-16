package com.yellowmoss.spamtextmessager;

import android.os.Bundle;
import android.app.Activity;
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

	public void startSendSMS(final View v) {
		String phoneNo = txtPhoneNo.getText().toString();
		String message = txtMessage.getText().toString();
		final String stringspamAmount = spamAmount.getText().toString();

		final String newMessage = message;
		final String newphoneNo = phoneNo;
		String newstringspamAmount = stringspamAmount;

		final Context context = this;
		
		
		if (phoneNo.length() > 0 && message.length() > 0
				&& newstringspamAmount.length() > 0)
			if (message.length() <= 160) {
				AlertDialog.Builder builder = new AlertDialog.Builder(context);

				builder.setPositiveButton("Continue",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								setContentView(R.layout.activity_spam);

								
								final int realspamAmount = Integer
										.parseInt(stringspamAmount);

								if (realspamAmount <= 25) {
								
									Toast.makeText(getBaseContext(),
											"Sending messages...",
											Toast.LENGTH_SHORT).show();
									
									for(int messageCount = 1; messageCount <= realspamAmount; messageCount++)
									{
										sendSMS(newphoneNo, newMessage);
										
										System.out.println("Message " + messageCount + "Sent!");
									
									//if(counter % 5 == 0)
										//System.out.println();
									
									}
									System.out.println("Done.");
									setContentView(R.layout.splash);
								}
								else {
									Toast.makeText(
											getBaseContext(),
											"You can only send 25 messages at once.",
											Toast.LENGTH_SHORT).show();

								}
								if(realspamAmount <= messageCount)
								{
									setContentView(R.layout.splash);
								}
							}
						});
				builder.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								Toast.makeText(getBaseContext(), "Cancelled",
										Toast.LENGTH_SHORT).show();

							}
						});
				builder.setMessage(
						"You cannot cancel past this point!")
						.setTitle("Continue?");

				AlertDialog dialog = builder.create();
				dialog.show();
			}
			else {
				Toast.makeText(getBaseContext(),
						"Must be less than 160 characters!", Toast.LENGTH_SHORT)
						.show();
			}
		else {
			Toast.makeText(
					getBaseContext(),
					"Make sure the phone number, message, and the amount of texts to spam have values!",
					Toast.LENGTH_SHORT).show();
		}
	}

	private void sendSMS(String newphoneNo, String newMessage) {
		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(newphoneNo, null, newMessage, null, null);
	}

	public void startHelp(View v) {
		startActivity(new Intent(getApplicationContext(), Help.class));
	}

}
