package com.example.circleprogressview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class DashCircleProgress extends View {
	private int maxProgress = 100;
	private int progress = 30;
	private int progressStrokeWidth = 5;
	RectF rect;
	Paint paintProgress;
	Paint paintBack;

	public DashCircleProgress(Context context, AttributeSet attrs) {
		super(context, attrs);
		rect = new RectF();
		paintProgress = new Paint();
		paintBack = new Paint();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int width = this.getWidth();
		int height = this.getHeight();

		if (width != height) {
			int min = Math.min(width, height);
			width = min;
			height = min;
		}
		canvas.drawColor(Color.TRANSPARENT);// set the background color of the
											// canvas(whole circle progress
											// view)

		paintBack.setFlags(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
		paintBack.setColor(Color.WHITE);
		paintBack.setStrokeWidth(progressStrokeWidth);
		paintBack.setStyle(Style.STROKE);

		paintProgress.setAntiAlias(true);
		paintProgress.setColor(Color.WHITE);
		paintProgress.setStrokeWidth(progressStrokeWidth);
		paintProgress.setStyle(Style.STROKE);

		rect.left = progressStrokeWidth / 2;
		rect.top = progressStrokeWidth / 2;
		rect.right = width - progressStrokeWidth / 2;
		rect.bottom = height - progressStrokeWidth / 2;

		canvas.drawArc(rect, -90, 360, false, paintBack); // draw the background
															// of the progress

		paintProgress.setColor(Color.BLUE);// set color of the progress
		// set the dash effect of the paint ,thus coming out the dash progress
		// ,or this is just a circle progress view
		PathEffect effect = new DashPathEffect(new float[] { 10, 10, 10, 10 }, 1);
		paintProgress.setPathEffect(effect);

		canvas.drawArc(rect, -90, ((float) progress / maxProgress) * 360,
				false, paintProgress); // draw the arc (progress), the value of
										// the progress is evaluated by the
										// progress

		paintProgress.setStrokeWidth(2);// width of the progress
		//
		String text = progress + "%"; // progress text
		Paint textPaint = new Paint();
		textPaint.setAntiAlias(true);
		textPaint.setColor(Color.BLUE);
		textPaint.setTextSize(40);
		canvas.drawText(text, width / 2 - textPaint.measureText(text) / 2,
				height / 2, textPaint);

	}

	public void setProgress(int progress) {
		this.progress = progress;
		this.invalidate();
	}

}
