package com.sfsu.system.inventory.add;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.sfsu.sfsusystems.R;
import com.sfsu.systems.JSONParser;

public class AddDevice extends Activity implements OnClickListener {

	private static final String URL = "http://www.ayushgoyal09.com/webservice/insert_device.php";
	// private static final String TAG_SUCCESS = "success";
	private EditText barcodeText, nameText, ip_addressText, osText, modelText,
			yearText;
	private ProgressDialog pDialog;
	private Button mapDevice;
	private ImageButton scanButton;
	JSONParser jsonParser = new JSONParser();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_devices);
		barcodeText=(EditText) findViewById(R.id.barcode);
		nameText = (EditText) findViewById(R.id.device_name);
		ip_addressText=(EditText) findViewById(R.id.ip_address);
		osText=(EditText) findViewById(R.id.os);
		modelText=(EditText) findViewById(R.id.model);
		yearText=(EditText) findViewById(R.id.year);
		mapDevice = (Button) findViewById(R.id.map_device);
		mapDevice.setOnClickListener(this);
		scanButton = (ImageButton) findViewById(R.id.scan_button);
		scanButton.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.action_done, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.action_done:
			new CreateNewDevice().execute();
			return true;
		default:
			return super.onMenuItemSelected(featureId, item);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.map_device) {
			Intent intent = new Intent(this, MapDevice.class);
			startActivity(intent);
		}

		if(v.getId()==R.id.scan_button){
			IntentIntegrator scanIntegrator = new IntentIntegrator(this);
			scanIntegrator.initiateScan();
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
			barcodeText.setText(content);

		} else {
			Toast toast = Toast.makeText(getApplicationContext(),
					"No scan data received!", Toast.LENGTH_SHORT);
			toast.show();
		}
	}

	class CreateNewDevice extends AsyncTask<String, String, String> {
		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(AddDevice.this);
			pDialog.setMessage("Adding Device..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			Log.i("checkpoint", "HERE");
			List<NameValuePair> args = new ArrayList<NameValuePair>();
			String barcode = barcodeText.getText().toString();
			String name = nameText.getText().toString();
			String ip_address = ip_addressText.getText().toString();
			String os = osText.getText().toString();
			String model = modelText.getText().toString();
			String year = yearText.getText().toString();
			args.add(new BasicNameValuePair("barcode", barcode));
			args.add(new BasicNameValuePair("name", name));
			args.add(new BasicNameValuePair("ip_address", ip_address));
			args.add(new BasicNameValuePair("os", os));
			args.add(new BasicNameValuePair("model", model));
			args.add(new BasicNameValuePair("year", year));
			Log.d("REQUEST", args.toString());
			JSONObject json = jsonParser.makeHttpRequest(URL, "POST", args);

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once done
			pDialog.dismiss();
		}

	}

}
