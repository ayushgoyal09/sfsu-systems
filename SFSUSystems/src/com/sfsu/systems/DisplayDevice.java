package com.sfsu.systems;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sfsu.sfsusystems.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DisplayDevice extends Activity {

	private static final String URL = "http://www.ayushgoyal09.com/webservice/display_device.php";
	private TextView device_name, ip_address, model, year, os;
	private ProgressDialog pDialog;
	private String barcode;
	JSONArray device;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display_device);
		barcode = getIntent().getExtras().getString("content");
		device_name = (TextView) findViewById(R.id.device_name);
		ip_address = (TextView) findViewById(R.id.ip_address);
		model = (TextView) findViewById(R.id.model);
		year = (TextView) findViewById(R.id.year);
		os = (TextView) findViewById(R.id.os);
		new GetDeviceData().execute();

	}

	class GetDeviceData extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(DisplayDevice.this);
			pDialog.setMessage("Getting Data...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			List<NameValuePair> args = new ArrayList<NameValuePair>();
			args.add(new BasicNameValuePair("barcode", barcode));
			JSONObject json = new JSONParser()
					.makeHttpRequest(URL, "GET", args);
			try {
				device = json.getJSONArray("device");
				Log.i("device",""+device.toString());
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			for (int i = 0; i < device.length(); i++) {
				try {
					JSONObject device_attribute = device.getJSONObject(i);
					//setting the values fetched to corresponding textviews on Display device activity.
					device_name.setText(device_attribute.getString("name"));
					ip_address.setText(device_attribute.getString("ip_address"));
					model.setText(device_attribute.getString("model"));
					year.setText(device_attribute.getString("year"));
					os.setText(device_attribute.getString("os"));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//String name = owner.getString(TAG_FIRSTNAME);
				

			}

			pDialog.dismiss();
			// device_name.setText("hello");
		}
	}
}
