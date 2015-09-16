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

		final Context context = this;

		AlertDialog.Builder builder = new AlertDialog.Builder(context);

		builder.setPositiveButton("Accept",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// User clicked OK button

					}
				});
		builder.setNegativeButton("Deny and Exit",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						finish();
						System.exit(0);
					}
				});

		builder.setMessage(
				"We are not responsibile for any damages from using this app! Only use for test purposes, and if you have an unlimited plan!\n\nYour screen will flicker when you send a large amount of texts, this is normal. \n\nClick Accept to continue or Deny to exit.")
				.setTitle("Warning!");

		AlertDialog dialog = builder.create();
		dialog.show();

	}

	public void startSendSMS(View v) {
		String phoneNo = txtPhoneNo.getText().toString();
		String message = txtMessage.getText().toString();
		final String stringspamAmount = spamAmount.getText().toString();

		final String newMessage = message;
		final String newphoneNo = phoneNo;
		String newstringspamAmount = stringspamAmount;
	/*	final String link = "<name>Spam Text Messager on Google Play!\n\n</name><url>https://play.google.com/store</url>";

		

		final Intent sharetheIntent = new Intent();
		{
			AlertDialog.Builder builderFinish = new AlertDialog.Builder(context);

			builderFinish.setPositiveButton("Share",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							// User clicked SHARE
							Intent shareIntent = new Intent();
							shareIntent.setAction(Intent.ACTION_CHOOSER);
							shareIntent.putExtra(Intent.EXTRA_TEXT,
									"I just spammed" + newphoneNo
											+ "with the message" + newMessage
											+ stringspamAmount + "times with"
											+ link);
							shareIntent.setType("text/plain");
							startActivity(shareIntent);
						}
					});

			builderFinish.setPositiveButton("Continue",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							startActivity(new Intent(getApplicationContext(),
									StartSpamScreen.class));
						}
					});

			builderFinish.setNegativeButton("Exit App",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							finish();
							System.exit(0);
						}
					});

			builderFinish.setMessage("Spam is sent! What next?").setTitle(
					"Finished");

			AlertDialog dialogFinish = builderFinish.create();
			dialogFinish.show();
		}
	*/	
		final Context context = this;
		
		if (phoneNo.length() > 0 && message.length() > 0
				&& newstringspamAmount.length() > 0) {

			AlertDialog.Builder builder = new AlertDialog.Builder(context);

			builder.setPositiveButton("Continue",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {

							setContentView(R.layout.activity_spam);
							
							int messageCount = 1;
							int realspamAmount = Integer.parseInt(stringspamAmount);

							if (realspamAmount <= 25) {								
								while (realspamAmount >= messageCount) {
									sendSMS(newphoneNo, newMessage);
									++messageCount;
								}

								/*int newMessageCount = messageCount - 1;
								if (realspamAmount == newMessageCount) {
									startActivity(sharetheIntent);
								}
								*/
							} else {
								Toast.makeText(
										getBaseContext(),
										"You can only send 25 messages at once.",
										Toast.LENGTH_SHORT).show();

							}					
						}
					});
			builder.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							Toast.makeText(getBaseContext(), "Cancelled",
									Toast.LENGTH_SHORT).show();
							startActivity(new Intent(getApplicationContext(),
									StartSpamScreen.class));
						}
					});
			builder.setMessage(
					"You cannot cancel past this point! If you are on JellyBean, just click Allow when prompted. It will take a few minutes for all texts to send.")
					.setTitle("Continue?");

			AlertDialog dialog = builder.create();
			dialog.show();

		}

		else
		{
			Toast.makeText(
					getBaseContext(),
					"Make sure the phone number, message, and the amount of texts to spam have values!",
					Toast.LENGTH_SHORT).show();
		}
		
	}

	private void sendSMS(String newphoneNo, String newMessage) {
		PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this,
				StartSpamScreen.class), 0);
		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(newphoneNo, null, newMessage, pi, null);
	}

	public void startHelp(View v) {
		startActivity(new Intent(getApplicationContext(), Help.class));
	}

}
