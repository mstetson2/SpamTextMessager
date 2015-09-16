package com.yellowmoss.spamtextmessager;

import android.os.Bundle;
import android.widget.Toast;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

public class StartupScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_startup_screen);

		final Context context = this;
		// public void dialogBox(View view) {

		AlertDialog.Builder builder = new AlertDialog.Builder(context);

		// Add the buttons
		builder.setPositiveButton("Accept",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// User clicked OK button

						startActivity(new Intent(getApplicationContext(),
								StartSpamScreen.class));
					}
				});
		builder.setNegativeButton("Deny and Exit",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// user clicked negative
						finish();
						System.exit(0);
					}
				});
		// Set other dialog properties
		builder.setMessage(
				"We are not responsibile for any damages from using this app! Only use for test purposes, and if you have an unlimited plan!\n\nYour screen will flicker when you send a large amount of texts, this is normal. \n\nClick Accept to continue or Deny to exit.")
				.setTitle("Warning!");

		// Create the AlertDialog
		AlertDialog dialog = builder.create();
		// Show Dialog
		dialog.show();

	}

}
