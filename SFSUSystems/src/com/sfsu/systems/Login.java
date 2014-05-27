/**
 * This class serves as the main activity for the project. It provides a login interface for the user. 
 */

package com.sfsu.systems;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sfsu.sfsusystems.R;

/*
 * The class implements on click listener for login button and forgot password link
 */
public class Login extends Activity implements OnClickListener {

	private Button loginButton;
	private EditText username, password;
	private ProgressDialog pDialog;
	private static final String LOGIN_URL = "http://www.ayushgoyal09.com/webservice/login.php";
	private static final String TAG_RESULT = "result";
	JSONParser jsonParser = new JSONParser();
	/*
	 * user and pass store the credentials parsed into string format from
	 * EditText username and password
	 */
	private String user, pass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
		loginButton = (Button) findViewById(R.id.login_button);
		// Register click listener for login button
		loginButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		// switch takes in the id of the view element clicked
		switch (v.getId()) {

		// executed when the user hits login button
		case R.id.login_button:

			// get the username and password entered by the user as Strings
			user = username.getText().toString();
			pass = password.getText().toString();

			// Method to check if the credentials are added or not
			if (checkForCredentials()) {
				new AttemptLogin().execute();
			}
			break;

		// executed when the user hits on Forgot Password link
		case R.id.forgot_password:

			break;
		}

	}

	private boolean checkForCredentials() {
		Log.d("USERNAME", user);
		Log.d("PASSWORD", pass);
		// Log.d("IP Address", myIP);
		if (user.equals("") && pass.equals("")) {
			Toast.makeText(getApplicationContext(),
					"Username and password cannot be left blank!",
					Toast.LENGTH_SHORT).show();
			return false;
		} else if (user.equals("")) {
			Toast.makeText(getApplicationContext(),
					"Please enter the username", Toast.LENGTH_SHORT).show();
			return false;
		} else if (pass.equals("")) {
			Toast.makeText(getApplicationContext(),
					"Please enter the password", Toast.LENGTH_SHORT).show();
			return false;
		} else
			return true;
	}

	class AttemptLogin extends AsyncTask<String, String, Integer> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		boolean failure = false;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Login.this);
			pDialog.setMessage("Attempting login...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected Integer doInBackground(String... params) {
			// TODO Auto-generated method stub

			int result = 0;
			try {
				List<NameValuePair> args = new ArrayList<NameValuePair>();
				args.add(new BasicNameValuePair("username", user));
				args.add(new BasicNameValuePair("password", pass));
				Log.d("REQUEST", args.toString());
				// HTTP request
				JSONObject json = jsonParser.makeHttpRequest(LOGIN_URL, "POST",
						args);

				// json result tag
				result = json.getInt(TAG_RESULT);
				Log.d("RESULT", "" + result);
				if (result == 1) {
					Log.d("Login Successful!", json.toString());
					return 1;
				} else if (result == 0) {
					Log.d("Login Failure", json.toString());
					return 0;
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
		}

		protected void onPostExecute(Integer res) {
			// dismiss the dialog once product deleted
			pDialog.dismiss();
			switch (res) {
			case 0:
				Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_LONG)
						.show();
				break;

			case 1:
				Intent intent = new Intent(getApplicationContext(),
						Scannit.class);
				startActivity(intent);
				Toast.makeText(Login.this, "Welcome " + user, Toast.LENGTH_SHORT)
						.show();
				break;

			case 2:
				Toast.makeText(Login.this, "Database Error..!",
						Toast.LENGTH_LONG).show();
				break;
			}

		}

	}

}
