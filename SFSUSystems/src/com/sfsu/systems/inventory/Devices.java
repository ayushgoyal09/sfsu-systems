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
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.sfsu.sfsusystems.R;
import com.sfsu.systems.DisplayDevice;
import com.sfsu.systems.JSONParser;

public class Devices extends Fragment {

	private static final String URL = "http://www.ayushgoyal09.com/webservice/get_all_devices.php";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_DEVICES = "devices";

	private static final String TAG_DEVICE_NAME = "name";
	JSONArray devices;
	ArrayList<String> devicesList;
	ListView list_all_devices;
	ArrayAdapter<String> adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		View rootView = inflater.inflate(R.layout.devices, container, false);
		list_all_devices = (ListView) rootView.findViewById(R.id.devices_list);
		devicesList = new ArrayList<String>();
		new GetDevices().execute();
		list_all_devices
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						Intent intent = new Intent(getActivity(),
								DisplayDevice.class);
						String selectedDevice = parent.getItemAtPosition(
								position).toString();
						String barcode = getBarcodeForSelectedDevice(selectedDevice);
						if(barcode!=null){
							intent.putExtra("content", barcode);
							startActivity(intent);	
						
						}
						
					}
				});
		return rootView;

	}

	protected String getBarcodeForSelectedDevice(String selectedDevice) {

		for (int i = 0; i < devices.length(); i++) {
			JSONObject device;
			try {
				device = devices.getJSONObject(i);
				String deviceName = device.getString(TAG_DEVICE_NAME);
				if (deviceName.equals(selectedDevice))
					return device.getString("barcode");

				Log.i("Owner: ", devicesList.get(i).toString());

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return null;
	}

	class GetDevices extends AsyncTask<String, String, String> {

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
					devices = json.getJSONArray(TAG_DEVICES);
					for (int i = 0; i < devices.length(); i++) {
						JSONObject device = devices.getJSONObject(i);
						String deviceName = device.getString(TAG_DEVICE_NAME);

						devicesList.add(deviceName);

						Log.i("Owner: ", devicesList.get(i).toString());

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
			Log.i("size", devicesList.size() + "");
			adapter = new ArrayAdapter<String>(getActivity(),
					R.layout.devices_adapter, R.id.textView1, devicesList);
			list_all_devices.setAdapter(adapter);

		}
	}

}
