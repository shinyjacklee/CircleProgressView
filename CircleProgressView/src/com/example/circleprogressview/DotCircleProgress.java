package com.example.circleprogressview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class DotCircleProgress extends View {

	private CircleAttribute mCircleAttribute;

	private int mMaxProgress;// max progress
	private int mSubCurProgress;// sub progress
	private int mCircleRadius;// radius of the circle

	public int mCircleX;// the center_X of the circle
	public int mCircleY;// the center_Y of the circle
	public int mAngle;// angle of the progress
	private Bitmap mDot;// the clock-hand-like dot
	private int mDotRadius;// radius of the dot
	private Rect mDotRect;// rect of the dot

	public DotCircleProgress(Context paramContext) {
		this(paramContext, null);
	}

	public DotCircleProgress(Context paramContext,
			AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);
		defaultParam();
	}

	/**
	 * init default param
	 */
	private void defaultParam() {
		this.mCircleAttribute = new CircleAttribute();
		this.mMaxProgress = 100;
		this.mSubCurProgress = 0;
	}

	/*
	 * draw the circle view
	 * 
	 * actually i make this effect with 3 roundOval folded up,and their radius
	 * is different,so coming out the stroke effect and add the right dot ,so
	 * this custom view out~
	 */
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		canvas.drawArc(this.mCircleAttribute.mRoundOval, 0.0F, 360.0F,
				this.mCircleAttribute.mBRoundPaintsFill,
				this.mCircleAttribute.mBottomPaint);
		canvas.drawArc(this.mCircleAttribute.mRoundOval,
				this.mCircleAttribute.mDrawPos, mAngle,
				this.mCircleAttribute.mBRoundPaintsFill,
				this.mCircleAttribute.mSubPaint);
		canvas.drawArc(this.mCircleAttribute.inRoundOval, 0.0F, 360.0F,
				this.mCircleAttribute.mBRoundPaintsFill,
				this.mCircleAttribute.mMainPaints);
		String progres = mSubCurProgress + "%";
		canvas.drawText(
				progres,
				mCircleX
						+ this.mCircleAttribute.mTextPaint.measureText(progres),
				mCircleY
						+ this.mCircleAttribute.mTextPaint.measureText(progres),
				this.mCircleAttribute.mTextPaint);
		// Log.i(TAG, "onDraw");
		canvas.drawBitmap(mDot, null, mDotRect, null);

	}

	/**
	 * set the progress,and calculate the right position of the dot
	 * 
	 * @param progress
	 *            [0,100]
	 */
	public void setProgress(int progress) {
		this.mSubCurProgress = progress;

		mAngle = (int) (360.0F * this.mSubCurProgress / this.mMaxProgress);
		float radian = (float) ((mAngle - 90) * Math.PI / 180);// radian of the
																// arc

		int newX = (int) (Math.cos(radian) * mCircleRadius + mCircleX)
				+ mDot.getWidth();// new X of the dot
		int newY = (int) (Math.sin(radian) * mCircleRadius + mCircleY)
				+ mDot.getWidth();// new Y of the dot

		// set the rect of the dot
		mDotRect.left = newX - mDotRadius;
		mDotRect.right = newX + mDotRadius;
		mDotRect.top = newY - mDotRadius;
		mDotRect.bottom = newY + mDotRadius;
		// Log.i(TAG,
		// mCircleX + ", " + mCircleY + "-------"
		// + mDotRect.toShortString());
		invalidate();
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		// remember subtract the width of the dot
		mCircleX = (right - left) / 2 - mDot.getWidth();
		mCircleY = (bottom - top) / 2 - mDot.getWidth();
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		// Log.i(TAG, "onSizeChanged");
		mCircleX = w / 2 - mDot.getWidth();
		mCircleY = h / 2 - mDot.getWidth();
		this.mCircleAttribute.autoFix(w, h);// auto fix the width height
		setProgress(mSubCurProgress);
	}

	class CircleAttribute {
		public boolean mBRoundPaintsFill = true;
		public Paint mBottomPaint;
		public int mDrawPos = -90;
		public Paint mMainPaints;
		public int mSubPaintColor = Color.WHITE;// the progress(between top and
												// bottom roundOval ) color
		public int mBottomPaintColor = Color.BLUE;// the bottom roundOval's
													// color
		public int mMainPaintColor = getResources().getColor(
				R.color.backgroundColor);// the top cover roundOval's color
		public int mPaintWidth = 0;
		public RectF mRoundOval = new RectF();
		public RectF inRoundOval = new RectF();
		public int mSidePaintInterval = 4;
		public Paint mSubPaint;

		public Paint mTextPaint;

		public CircleAttribute() {
			// top roundOval
			this.mMainPaints = new Paint();
			this.mMainPaints.setAntiAlias(true);
			this.mMainPaints.setStyle(Paint.Style.FILL);
			this.mMainPaints.setStrokeWidth(this.mPaintWidth);
			this.mMainPaints.setColor(this.mMainPaintColor);
			// sub roundOval,under the top layer
			this.mSubPaint = new Paint();
			this.mSubPaint.setAntiAlias(true);
			this.mSubPaint.setStyle(Paint.Style.FILL);
			this.mSubPaint.setStrokeWidth(this.mPaintWidth);
			this.mSubPaint.setColor(this.mSubPaintColor);
			// bottom roundOval
			this.mBottomPaint = new Paint();
			this.mBottomPaint.setAntiAlias(true);
			this.mBottomPaint.setStyle(Paint.Style.FILL);
			this.mBottomPaint.setStrokeWidth(this.mPaintWidth);
			this.mBottomPaint.setColor(this.mBottomPaintColor);
			//
			this.mTextPaint = new Paint();
			this.mTextPaint.setAntiAlias(true);
			this.mTextPaint.setColor(Color.WHITE);
			this.mTextPaint.setTextSize(40);
			this.mTextPaint.setTextAlign(Paint.Align.CENTER);
			// dot
			mDot = BitmapFactory.decodeResource(getResources(),
					R.drawable.ic_dot);
			mDotRadius = mDot.getWidth() / 2;
			mDotRect = new Rect();
		}

		/**
		 * fix the width and height of the view
		 * 
		 * @param width
		 * @param height
		 */
		public void autoFix(int width, int height) {
			this.inRoundOval.set(this.mPaintWidth / 2 + this.mSidePaintInterval
					+ mDotRadius, this.mPaintWidth / 2
					+ this.mSidePaintInterval + mDotRadius, width - mDotRadius
					- this.mPaintWidth / 2 - this.mSidePaintInterval, height
					- this.mPaintWidth / 2 - this.mSidePaintInterval
					- mDotRadius);
			int left = DotCircleProgress.this.getPaddingLeft();
			int right = DotCircleProgress.this.getPaddingRight();
			int top = DotCircleProgress.this.getPaddingTop();
			int bottom = DotCircleProgress.this.getPaddingBottom();
			mCircleRadius = width / 2 - mDotRadius;
			// mCircleX = left + width / 2;
			// mCircleY = top + height / 2;
			this.mRoundOval.set(left + this.mPaintWidth / 2 + mDotRadius,
					mDotRadius + top + this.mPaintWidth / 2, width - mDotRadius
							- right - this.mPaintWidth / 2, height - bottom
							- this.mPaintWidth / 2 - mDotRadius);
		}

	}
}