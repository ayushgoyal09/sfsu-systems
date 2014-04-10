package com.sfsu.systems.inventory;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * A FragmentPagerAdapter that returns a fragment corresponding to one of the
 * primary sections of the Inventory (i.e. Locations, owner and devices).
 */

public class InventoryPagerAdapter extends FragmentPagerAdapter {

	public InventoryPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) {
		switch (arg0) {
		case 0:
			return new Devices();
		case 1:
			return new Locations();
		case 2:
			return new Owners();
		}
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}

}
