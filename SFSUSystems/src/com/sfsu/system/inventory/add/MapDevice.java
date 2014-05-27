/**
 * The Map Device class is an Activity that shows the floor map
 * so the user may mark the position of device. * 
 */

package com.sfsu.system.inventory.add;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;

import com.sfsu.sfsusystems.R;

public class MapDevice extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.map_device);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.action_done, menu);
		return super.onCreateOptionsMenu(menu);

	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.action_done:
			// Toast.makeText(getApplicationContext(), "SAVINGGGG",
			// Toast.LENGTH_SHORT).show();
			Intent intent = getIntent();

			intent.putExtra("x_value", "" + DeviceSpriteView.x);
			intent.putExtra("y_value", "" + DeviceSpriteView.y);
			setResult(RESULT_OK, intent);
			finish();
			return true;
		default:
			return super.onMenuItemSelected(featureId, item);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return super.onTouchEvent(event);

	}

}
