package com.sfsu.systems.inventory;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sfsu.sfsusystems.R;

public class Devices extends android.support.v4.app.Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
	
		View rootView = inflater.inflate(R.layout.devices, container, false);
		return rootView;
	}

}
