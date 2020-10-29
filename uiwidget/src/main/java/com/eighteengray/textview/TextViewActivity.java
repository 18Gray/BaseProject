package com.eighteengray.textview;

import com.eighteengray.materialdesign.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;


public class TextViewActivity extends Activity implements OnClickListener
{
	TextView tv1, tv2;
	

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_textview);
		
		initView();
	}
	

	private void initView()
	{
		tv1 = (TextView) findViewById(R.id.tv1);
		tv2 = (TextView) findViewById(R.id.tv2);

		tv1.setOnClickListener(this);
		tv2.setOnClickListener(this);
	}

	

	@Override
	public void onClick(View v)
	{
		int i = v.getId();
		if (i == R.id.tv1) {
			Intent intent1 = new Intent(TextViewActivity.this, WaveTextViewActivity.class);
			startActivity(intent1);
		} else if (i == R.id.tv2) {
			Intent intent2 = new Intent(TextViewActivity.this, StyleTextViewActivity.class);
			startActivity(intent2);
		}
	}
	

}
