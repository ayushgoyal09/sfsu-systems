/**
 * This Fragment is for the LOCATIONS Tab in the Inventory activity. 
 */

package com.sfsu.systems.inventory;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sfsu.sfsusystems.R;
import com.sfsu.systems.JSONParser;

public class Locations extends android.support.v4.app.Fragment {

	private static final String URL = "http://www.ayushgoyal09.com/webservice/get_all_locations.php";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_LOCATIONS = "locations";
	private static final String TAG_LOCATION_NAME = "name";
	JSONArray locations;
	ArrayList<String> locationsList;
	ListView list_all_locations;
	ArrayAdapter<String> adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		View rootView = inflater.inflate(R.layout.locations, container, false);
		list_all_locations = (ListView) rootView.findViewById(R.id.locations_list);
		locationsList = new ArrayList<String>();
		new GetOwners().execute();
		list_all_locations.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				Intent intent = new Intent(getActivity(),DevicesPositionOnMap.class);
				startActivity(intent);
								
			}
		});
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
					locations = json.getJSONArray(TAG_LOCATIONS);
					for (int i = 0; i < locations.length(); i++) {
						JSONObject location = locations.getJSONObject(i);
						String locationName = location.getString(TAG_LOCATION_NAME);
								
						
						locationsList.add(locationName);

						Log.i("Location: ", locationsList.get(i).toString());

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
			Log.i("size", locationsList.size() + "");
			adapter = new ArrayAdapter<String>(getActivity(),
					R.layout.locations_adapter,R.id.textView1, locationsList);
			list_all_locations.setAdapter(adapter);

		}
	}

}
