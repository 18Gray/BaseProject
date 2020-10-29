package com.eighteengray.textview;

import com.eighteengray.materialdesign.R;
import com.eighteengray.textview.shimmertextview.Shimmer;
import com.eighteengray.textview.shimmertextview.ShimmerTextView;
import com.eighteengray.textview.titanictextview.Titanic;
import com.eighteengray.textview.titanictextview.TitanicTextView;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;

public class WaveTextViewActivity extends Activity
{
	ShimmerTextView stv;
	Shimmer shimmer;
	
	TitanicTextView titanicTextView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_textview);
		
		//ShimmerTextView
		stv = (ShimmerTextView) findViewById(R.id.stv_shimmertextview);
		if (shimmer != null && shimmer.isAnimating())
		{
			shimmer.cancel();
		} else
		{
			shimmer = new Shimmer();
			shimmer.start(stv);
		}
		
		//TitanicTextView
		titanicTextView = (TitanicTextView) findViewById(R.id.ttv_titanic);

		//字体
//		Typeface typeFace = Typeface.createFromAsset(getAssets(), "huakangshaonv.ttf");
//		titanicTextView.setTypeface(typeFace);

        // start animation
        new Titanic().start(titanicTextView);

	}
}
