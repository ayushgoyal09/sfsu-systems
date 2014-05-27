/**
 * This class is a FragmentActivity. It uses PageView with three tabs: Devices, Locations, Owners. 
 */

package com.sfsu.systems.inventory;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.sfsu.sfsusystems.R;
import com.sfsu.system.inventory.add.AddDevice;
import com.sfsu.system.inventory.add.AddOwners;

public class Inventory extends FragmentActivity implements
		ActionBar.TabListener {

	private ViewPager viewPager;
	private InventoryPagerAdapter inventoryPagerAdapter;
	private String tabs[] = { "DEVICES", "LOCATIONS", "OWNERS" };
	String currentTab;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.inventory_home);

		// Create the adapter that will return a fragment for each of the three
		// primary sections
		// of the app.
		inventoryPagerAdapter = new InventoryPagerAdapter(
				getSupportFragmentManager());

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();

		// Specify that the Home/Up button should not be enabled, since there is
		// no hierarchical
		// parent.
		actionBar.setHomeButtonEnabled(false);

		// Specify that we will be displaying tabs in the action bar.
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Set up the ViewPager, attaching the adapter and setting up a listener
		// for when the
		// user swipes between sections.
		viewPager = (ViewPager) findViewById(R.id.pager);
		viewPager.setAdapter(inventoryPagerAdapter);
		viewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						// TODO Auto-generated method stub
						super.onPageSelected(position);
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
		}

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		currentTab = (String) getActionBar().getSelectedTab().getText();
		Log.i("Tab Selected", currentTab);
		switch (item.getItemId()) {
		case R.id.search:
			openSearch(currentTab);
			return true;
		case R.id.add:
			openAdd(currentTab);
			return true;
		default:
			return super.onOptionsItemSelected(item);

		}

	}

	private void openAdd(String currentTab) {
		if(currentTab.equals("OWNERS")){
			Intent intent = new Intent(this,AddOwners.class);
			startActivity(intent);
		}
		if(currentTab.equals("DEVICES")){
			Intent intent = new Intent(this,AddDevice.class);
			startActivity(intent);
		}
	}

	private void openSearch(String currentTab) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.options, menu);
		return true;
	}

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		viewPager.setCurrentItem(arg0.getPosition());

	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

}