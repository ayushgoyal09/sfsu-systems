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

import com.sfsu.sfsusystems.R;
import com.sfsu.systems.JSONParser;

public class AddDevice extends Activity implements OnClickListener{

	private static final String URL = "http://www.ayushgoyal09.com/webservice/insert_device.php";
//	private static final String TAG_SUCCESS = "success";
	private EditText deviceName;
	private ProgressDialog pDialog;
	private Button mapDevice;
	JSONParser jsonParser = new JSONParser();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_devices);
		deviceName = (EditText) findViewById(R.id.device_name);
		mapDevice = (Button) findViewById(R.id.map_device);
		mapDevice.setOnClickListener(this);
		
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
		if(v.getId()==R.id.map_device){
			Intent intent = new Intent(this,MapDevice.class);
			startActivity(intent);
		}
		
	}
	
	
	class CreateNewDevice extends AsyncTask<String, String, String>
	{
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
			List<NameValuePair> args = new ArrayList<NameValuePair>();
			String device=deviceName.getText().toString();
			args.add(new BasicNameValuePair("device", device));
			Log.d("REQUEST", args.toString());
			JSONObject json = jsonParser.makeHttpRequest(URL, "POST",
					args);
			
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
