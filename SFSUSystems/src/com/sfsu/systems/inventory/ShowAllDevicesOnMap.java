package com.sfsu.systems.inventory;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

public class ShowAllDevicesOnMap extends View {

	public ShowAllDevicesOnMap(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		Paint p = new Paint();
		p.setAntiAlias(false);
		p.setColor(Color.RED);
		p.setStyle(Style.FILL_AND_STROKE);
		for (int i = 0; i < Devices.devices.length(); i++) {
			JSONObject device;
			try {
				device = Devices.devices.getJSONObject(i);
				String x_value = device.getString("x_value");
				String y_value = device.getString("y_value");
				if (!x_value.equals("null") && !y_value.equals("null")) {
					float x = Float.parseFloat(x_value);
					float y = Float.parseFloat(y_value);
					canvas.drawCircle(x, y, 50, p);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
