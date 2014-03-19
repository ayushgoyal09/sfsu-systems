/*This class serves as the main activity for the project*/
package com.sfsu.systems;

import android.app.Activity;
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
		//Register click listener for login button
		loginButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		// switch takes in the id of the view element clicked
		switch (v.getId()) {

		// executed when the user hits login button
		case R.id.login_button:

			// get the username and password entered by the user as Strings
			user = username.getText().toString();
			pass = password.getText().toString();

			// Method to check if the credentials are added or not
			if (checkForCredentials()) {

			}
			break;

		// executed when the user hits on Forgot Password link
		case R.id.forgot_password:
			
			break;
		}

	}

	private boolean checkForCredentials() {
		Log.d("Credentials", "username :" + user + "\npassword :" + pass);
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

}
