package com.yellowmoss.spamtextmessager;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
//import android.widget.Toast;
import android.app.AlertDialog;
//import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

public class Share extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_share);
		
		final Context context = this;
			
		final String link = "<name>Spam Text Messager on Google Play!\n\n</name><url>https://play.google.com/store</url>";
			
			AlertDialog.Builder builderFinish = new AlertDialog.Builder(context);

			builderFinish.setPositiveButton("Share",
					new DialogInterface.OnClickListener() {
				
				Bundle extras = getIntent().getExtras();
				String newMessage = extras.getString("messageString");
				String newphoneNo = extras.getString("phoneNoString");
				String stringspamAmount = extras.getString("spamAmountString");
				
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
		
	

}
