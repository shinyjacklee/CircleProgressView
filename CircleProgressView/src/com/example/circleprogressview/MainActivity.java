package com.example.circleprogressview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends Activity {

	private DotCircleProgress mDotCircleProgress;
	private DashCircleProgress mDashCircleProgress;
	private TextView mTextView;
	int progress = 0;
	Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mDotCircleProgress = (DotCircleProgress) findViewById(R.id.circleProgressView);
		mDashCircleProgress = (DashCircleProgress) findViewById(R.id.circleProgressBar);

		mTextView = (TextView) findViewById(R.id.textView1);

		final long timeInterval = 500;

		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				progress++;
				progress = progress > 100 ? progress : progress % 100;
				handler.postDelayed(this, timeInterval);
				mTextView.setText("progress: " + progress + "%");
				mDotCircleProgress.setProgress(progress);
				mDashCircleProgress.setProgress(progress);

			}
		};

		handler.post(runnable);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
