/**
 * The DeviceSpriteView class is a Custom view that implements touch
 * event to mark position of a device on floor map  
 */

package com.sfsu.system.inventory.add;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class DeviceSpriteView extends View {

	public static float x = 0, y = 0;
	Paint p = new Paint();

	public DeviceSpriteView(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	@Override
	protected void onDraw(Canvas canvas) {

		super.onDraw(canvas);

		p.setAntiAlias(false);
		p.setColor(Color.RED);
		p.setStyle(Style.FILL_AND_STROKE);

		if (x != 0 && y != 0)
			canvas.drawCircle(x, y, 50, p);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		int action = event.getAction();

		if (action == MotionEvent.ACTION_DOWN) {
			Log.i("TOUCH", "touch event");
			x = event.getX();
			y = event.getY();
		}

		invalidate();
		return true;

	}

}
