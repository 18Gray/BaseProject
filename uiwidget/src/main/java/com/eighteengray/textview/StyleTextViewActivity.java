package com.eighteengray.textview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.eighteengray.materialdesign.R;
import com.eighteengray.textview.ellipsizingtextview.EllipsizingTextView;


public class StyleTextViewActivity extends Activity
{
	LinearLayout ll_styletextview;
	EllipsizingTextView etv_styletextview;
	ImageView iv_arrow_styletextview;
	String text;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_styletextview);

		ll_styletextview = (LinearLayout) findViewById(R.id.ll_styletextview);
		etv_styletextview = (EllipsizingTextView) findViewById(R.id.etv_styletextview);
		iv_arrow_styletextview = (ImageView) findViewById(R.id.iv_arrow_styletextview);

		SpannableStringBuilder builder = new SpannableStringBuilder("AaaaaaaAAAAAAAAAAAAAAAABBBBBBBBBBBBBBBBBBBBBBBBBbbbbbbbbbbbbbbbbbbbbbCCCCCCCCCCCCCCCCCCCCCCCCcccccccccccccccccccccccDDDDDDDDDDDDDDDDDDDDDDDDDddddddddddddddddddddddddddddEEEEEEEEEEEEEEEEEEEEEEEEEEEEeeeeeeeeeeeeeeeeeeeeeeFFFFFFFFFFFFFFFFFFFFFFFffffffffffffffffff");
		BackgroundColorSpan backgroundColorSpan = new BackgroundColorSpan(getResources().getColor(R.color.yellow_middle));
		builder.setSpan(backgroundColorSpan, 0, 15, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

		ForegroundColorSpan foreColorSpan = new ForegroundColorSpan(getResources().getColor(R.color.red_deep));
		builder.setSpan(foreColorSpan, 16, 23, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

		AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(dip2px(StyleTextViewActivity.this, 16));
		builder.setSpan(absoluteSizeSpan, 24, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

		StyleSpan styleSpan = new StyleSpan(Typeface.BOLD);
		builder.setSpan(styleSpan, 31, 35, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

		Html.ImageGetter imageGetter = new Html.ImageGetter()
		{
			@Override
			public Drawable getDrawable(String source)
			{
				int id = Integer.parseInt(source);
				//根据id从资源文件中获取图片对象
				Drawable d = getResources().getDrawable(id);
				d.setBounds(0, 0, d.getIntrinsicWidth(),d.getIntrinsicHeight());
				return d;
			}
		};
		text = builder.toString()+ "<img src='" + R.drawable.book_detail_up + "'/>";
		text = text + "<b>粗体字</b>" + "<font size=20 color=#666666>大小和字体</font>";

		etv_styletextview.setText(Html.fromHtml(text, imageGetter, null));

		etv_styletextview.setLineSpacing(3, 2);
		etv_styletextview.setMaxLines(3);

		if (!etv_styletextview.isNeedEllipsized())
		{
			iv_arrow_styletextview.setVisibility(View.GONE);
		}

		ll_styletextview.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if(iv_arrow_styletextview.getVisibility() == View.GONE)
				{
					return;
				}
				else if(ll_styletextview.isSelected())
				{
					etv_styletextview.setMaxLines(3);
					ll_styletextview.setSelected(false);
					iv_arrow_styletextview.setSelected(false);
				}
				else
				{
					etv_styletextview.setMaxLines(30);
					ll_styletextview.setSelected(true);
					iv_arrow_styletextview.setSelected(true);
				}
			}
		});

	}


	public int dip2px(Context context, float dipValue)
	{
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

}
