/**
 * The Add Owners class is an Activity that can be used to save a new owner to the inventory. * 
 */

package com.sfsu.system.inventory.add;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.sfsu.sfsusystems.R;
import com.sfsu.systems.JSONParser;

public class AddOwners extends Activity {
	private static final String URL = "http://www.ayushgoyal09.com/webservice/insert_owners.php";
	private static final String TAG_SUCCESS = "success";
	private EditText firstName, lastName;
	private ProgressDialog pDialog;
	JSONParser jsonParser = new JSONParser();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_owners);
		firstName = (EditText) findViewById(R.id.firstname);
		lastName = (EditText) findViewById(R.id.lastname);

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
			new CreateNewOwner().execute();
			return true;
		default:
			return super.onMenuItemSelected(featureId, item);
		}
	}


	class CreateNewOwner extends AsyncTask<String, String, String>{

		/**
	     * Before starting background thread Show Progress Dialog
	     * */
	    @Override
	    protected void onPreExecute() {
	        super.onPreExecute();
	        pDialog = new ProgressDialog(AddOwners.this);
	        pDialog.setMessage("Creating Product..");
	        pDialog.setIndeterminate(false);
	        pDialog.setCancelable(true);
	        pDialog.show();
	    }
		
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			String first = firstName.getText().toString();
			String last = lastName.getText().toString();
			List<NameValuePair> args = new ArrayList<NameValuePair>();
			args.add(new BasicNameValuePair("firstname", first));
			args.add(new BasicNameValuePair("lastname", last));
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

