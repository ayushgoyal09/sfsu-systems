package com.sfsu.systems.inventory;

import com.sfsu.sfsusystems.R;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;



public class Inventory extends FragmentActivity implements
		ActionBar.TabListener {

	private ViewPager viewPager;
	private InventoryPagerAdapter inventoryPagerAdapter;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.inventory_home);
		
		// Create the adapter that will return a fragment for each of the three primary sections
        // of the app.
		inventoryPagerAdapter = new InventoryPagerAdapter(getSupportFragmentManager());
		
		// Set up the action bar.
        final ActionBar actionBar = getActionBar();

        // Specify that the Home/Up button should not be enabled, since there is no hierarchical
        // parent.
        actionBar.setHomeButtonEnabled(false);

        // Specify that we will be displaying tabs in the action bar.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Set up the ViewPager, attaching the adapter and setting up a listener for when the
        // user swipes between sections.
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(inventoryPagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
        	@Override
        	public void onPageSelected(int position) {
        		// TODO Auto-generated method stub
        		super.onPageSelected(position);
        		actionBar.setSelectedNavigationItem(position);
        	}
        });
        
        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < inventoryPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by the adapter.
            // Also specify this Activity object, which implements the TabListener interface, as the
            // listener for when this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(inventoryPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
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