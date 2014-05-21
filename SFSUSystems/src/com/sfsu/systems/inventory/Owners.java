package com.sfsu.systems.inventory;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sfsu.sfsusystems.R;
import com.sfsu.systems.JSONParser;

public class Owners extends Fragment {

	private ProgressDialog pDialog;
	private static final String URL = "http://www.ayushgoyal09.com/webservice/test.php";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_OWNERS = "owners";
	private static final String TAG_FIRSTNAME = "firstname";
	private static final String TAG_LASTNAME = "lastname";
	JSONArray owners;
	ArrayList<String> ownersList;
	ListView list_all_owners;
	ArrayAdapter<String> adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		View rootView = inflater.inflate(R.layout.owners, container, false);
		list_all_owners = (ListView) rootView.findViewById(R.id.owners_list);
		ownersList = new ArrayList<String>();
		new GetOwners().execute();
		return rootView;
	}

	class GetOwners extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			List<NameValuePair> args = new ArrayList<NameValuePair>();
			JSONObject json = new JSONParser()
					.makeHttpRequest(URL, "GET", args);
			Log.i("Output", json.toString());
			try {
				int success = json.getInt(TAG_SUCCESS);
				if (success == 1) {
					owners = json.getJSONArray(TAG_OWNERS);
					for (int i = 0; i < owners.length(); i++) {
						JSONObject owner = owners.getJSONObject(i);
						String firstname = owner.getString(TAG_FIRSTNAME);
						String lastname = owner.getString(TAG_LASTNAME);
						ownersList.add(firstname + " " + lastname);

						Log.i("Owner: ", ownersList.get(i).toString());

					}

				}

			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			Log.i("size", ownersList.size() + "");
			adapter = new ArrayAdapter<String>(getActivity(),
					R.layout.owners_adapter, ownersList);
			list_all_owners.setAdapter(adapter);

		}
	}

}
