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

public class TestView extends View {

	//private static Point point = new Point();
	float x = 0, y = 0;
	Paint p = new Paint();

	public TestView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		p.setAntiAlias(false);
		p.setColor(Color.RED);
		p.setStyle(Style.FILL_AND_STROKE);

		if(x!=0&&y!=0)
		canvas.drawCircle(x, y, 50, p);

	}

	/*
	 * @Override protected void onDraw(Canvas canvas) { // TODO Auto-generated
	 * method stub super.onDraw(canvas); Paint p = new Paint();
	 *//**
	 * Set anti-aliasing to false. http://en.wikipedia.org/wiki/Anti-aliasing
	 */
	/*
	 * p.setAntiAlias(false);
	 *//**
	 * Set the drawing color to red with no transparency. The color is
	 * specified as (Alpha, Red, Green, Blue) tuple whose values must be between
	 * 0-255.
	 */
	/*
	 * p.setColor(Color.argb(255, 255, 0, 0));
	 *//**
	 * Draw a rectangle at the position (50, 50) of size (50, 50).
	 */
	/*
	 * canvas.drawRect(50, 50, 100, 100, p);
	 *//**
	 * Change the color to Green with a reduced transparency value.
	 */
	/*
	 * p.setColor(Color.argb(102, 0, 255, 0));
	 *//**
	 * Draw a circle at coordinates (105, 75) with a radius of 25.
	 */
	/*
	 * canvas.drawCircle(105, 75, 25, p); }
	 */

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		int action = event.getAction();

		if (action == MotionEvent.ACTION_DOWN) {
			Log.i("TOUCH", "touch event");
			// point = new Point((int) event.getX(), (int) event.getY());
			x = event.getX();
			y = event.getY();
		}

		invalidate();
		return true;

	}

}
