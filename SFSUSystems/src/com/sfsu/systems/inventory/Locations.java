package com.sfsu.systems.inventory;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sfsu.sfsusystems.R;

public class Locations extends android.support.v4.app.Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		View rootView = inflater.inflate(R.layout.locations, container, false);

		ListView locationsList = (ListView) rootView
				.findViewById(R.id.locations_list);
		String[] testVal = new String[] { "Administration", "Digitization",
				"Catalogue", "Library Retrieval", "Study Commons",
				"Administration", "Digitization", "Catalogue",
				"Library Retrieval", "Study Commons", "Administration",
				"Digitization", "Catalogue", "Library Retrieval",
				"Study Commons", "Administration", "Digitization", "Catalogue",
				"Library Retrieval", "Study Commons", "Administration",
				"Digitization", "Catalogue", "Library Retrieval",
				"Study Commons" };
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < testVal.length; i++) {
			list.add(testVal[i]);
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				R.layout.locations_adapter, list);
		locationsList.setAdapter(adapter);

		return rootView;
	}

}
