package com.yellowmoss.spamtextmessager;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.app.ProgressDialog;
import android.os.Handler;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts; 

public class StartSpamScreen2 extends Activity {

	Button btnSendSMS;
	static EditText txtPhoneNo;
	EditText txtMessage;
	EditText spamAmount;
	int messageCount = 1;
	int progress;

	ProgressDialog progressBar;
	private int progressBarStatus = 0;
	private Handler progressBarHandler = new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_spam_screen2);
	}
	
	public void startSendSMS(final View v) {
		System.out.println("2");
		btnSendSMS = (Button) findViewById(R.id.btnSendSMS);
		txtPhoneNo = (EditText) findViewById(R.id.txtPhoneNo);
		txtMessage = (EditText) findViewById(R.id.txtMessage);
		spamAmount = (EditText) findViewById(R.id.spamAmountEdit);
	/*	
		final String stringspamAmount = spamAmount.getText().toString();
		final int realspamAmount = Integer.parseInt(stringspamAmount);
		final String message = txtMessage.getText().toString();
		final String phoneNo = txtPhoneNo.getText().toString();
		*/
		
		String stringspamAmount = spamAmount.getText().toString();
		final int realspamAmount = Integer.parseInt(stringspamAmount);
		String message = txtMessage.getText().toString();
		final String newMessage = message;
		String phoneNo = txtPhoneNo.getText().toString();
		final String newphoneNo = phoneNo;
		
		// public void startSendSMS(final View v) {
		// String phoneNo = txtPhoneNo.getText().toString();
		// String message = txtMessage.getText().toString();
		// final String stringspamAmount = spamAmount.getText().toString();
		// final String newMessage = message;
		// final String newphoneNo = phoneNo;
		// String newstringspamAmount = stringspamAmount;

		final Context context = this;
		System.out.println("3");
		if (phoneNo.length() > 0 && message.length() > 0
				&& stringspamAmount.length() > 0)
			if (message.length() <= 160) {
				AlertDialog.Builder builder = new AlertDialog.Builder(context);

				builder.setPositiveButton("Continue",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								//setContentView(R.layout.activity_spam);

							//	int realspamAmount = Integer.parseInt(stringspamAmount);
								System.out.println("4");
								if (realspamAmount <= 25) {

									//
									progressBar = new ProgressDialog(
											context);
									progressBar.setCancelable(false);
									progressBar
											.setMessage("Sending Spam...");
									progressBar
											.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);							
									progressBar.setProgress(0);
									progressBar.setMax(100);
									progressBar.show();
									// reset progress bar status
									progressBarStatus = 0;
									
									progress = 0;
									System.out.println("5");
									new Thread(new Runnable() {
										public void run() {
											//int messageCount = 1;
											int progressTrack = 100 / realspamAmount;
																						
											for (messageCount = 1; messageCount <= realspamAmount; messageCount++) {					
												 //doSomeTasks();
												int progress = (progressTrack * messageCount);
												sendSMS(newphoneNo, newMessage);
												System.out.println("MessageCONFIRM " + messageCount + " Sent!");
												progressBarStatus = progress;
											}
											System.out.println("Done.");
											Toast.makeText(getBaseContext(),
													"Spam Sent!",
													Toast.LENGTH_SHORT).show();
											//setContentView(R.layout.splash);
											
												System.out.println("6");
												// your computer is too fast,
												// sleep 1 second
												try {
													Thread.sleep(1000);
												} catch (InterruptedException e) {
													e.printStackTrace();
												}

												// Update the progress bar
												progressBarHandler
														.post(new Runnable() {
															public void run() {
																progressBar
																		.setProgress(progressBarStatus);
															}
														});

											// ok, file is downloaded,
											if (progressBarStatus >= 100) {

												// sleep 2 seconds, so that you
												// can see the 100%
												try {
													Thread.sleep(1500);
												} catch (InterruptedException e) {
													e.printStackTrace();
												}

												// close the progress bar dialog
												progressBar.dismiss();
											}
										}
									}).start();
									//
									// TODO INSERT BACK HERE
								} else {
									Toast.makeText(
											getBaseContext(),
											"You can only send 25 messages at once.",
											Toast.LENGTH_SHORT).show();

								}
								if (realspamAmount <= messageCount) {
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
				builder.setMessage("You cannot cancel past this point!")
						.setTitle("Continue?");

				AlertDialog dialog = builder.create();
				dialog.show();
			} else {
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

	public void sendSMS(String newphoneNo, String newMessage) {
		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(newphoneNo, null, newMessage, null, null);
	}

	public void startHelp(View v) {
		startActivity(new Intent(getApplicationContext(), Help.class));
	}
	
	private static final int CONTACT_PICKER_RESULT = 1001;
	public void startContactPicker(View v)
	{
		Intent contactPickerIntent = new Intent(Intent.ACTION_PICK, Contacts.CONTENT_URI);  	
		startActivityForResult(contactPickerIntent, CONTACT_PICKER_RESULT);
		
	}
	//protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		//public static void getContacts(ContentResolver cr) {
		//Cursor cursor = null;
	     //ContentResolver cr = getContentResolver();
	 public static void getContacts(ContentResolver cr) {
			Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
			if (cur.getCount() > 0) {
			    while (cur.moveToNext()) {
			    // read id
			        String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
			        /** read names **/
			     //   String displayName = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
			        /** Phone Numbers **/
			            Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
			                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[] { id }, null);
			            while (pCur.moveToNext()) {
			               String number = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
			               //final String chosenNumber = number;
			               txtPhoneNo.setText(number, TextView.BufferType.EDITABLE);
			               // String typeStr = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
			                
			                
			        }
			        pCur.close();
			        
					
			        
			    }
			}
			}
	 
}
