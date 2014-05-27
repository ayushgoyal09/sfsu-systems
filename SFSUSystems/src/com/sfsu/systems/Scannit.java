/**
 * This Scannit class is the Activity presented to user on successfull login.
 * It has three buttons, Scannit: scan for a device, Inventory: lookup inventory, and 
 * Profile: view user profile.  
 */

package com.sfsu.systems;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.sfsu.sfsusystems.R;
import com.sfsu.systems.inventory.Inventory;


public class Scannit extends Activity implements OnClickListener {

	private ImageButton scanButton, inventoryButton;
	private TextView scanContent, scanFormat;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_screen);
		scanButton = (ImageButton) findViewById(R.id.scan_button);
		inventoryButton = (ImageButton) findViewById(R.id.inventory_button);
		scanContent = (TextView) findViewById(R.id.scan_content);
		scanFormat = (TextView) findViewById(R.id.scan_format);
		// register click listener for the scan button and inventory button
		scanButton.setOnClickListener(this);
		inventoryButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.scan_button) {
			// method initiateScan of the class IntentIntegrator is called.
			
			IntentIntegrator scanIntegrator = new IntentIntegrator(this);
			scanIntegrator.initiateScan();
			
		}

		if (v.getId() == R.id.inventory_button) {
			Intent intent = new Intent(this, Inventory.class);
			startActivity(intent);
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, intent);
		IntentResult scanningResult = IntentIntegrator.parseActivityResult(
				requestCode, resultCode, intent);
		if (scanningResult != null) {
			// call to methods in IntentResult class for the scanned results
			String content = scanningResult.getContents();
			String format = scanningResult.getFormatName();
			Intent i = new Intent(getApplicationContext(),DisplayDevice.class);
			i.putExtra("content", content);
			startActivity(i);
//			scanFormat.setText("FORMAT: " + format);
//			scanContent.setText("CONTENT: " + content);
		} else {
			Toast toast = Toast.makeText(getApplicationContext(),
					"No scan data received!", Toast.LENGTH_SHORT);
			toast.show();
		}
	}
}
