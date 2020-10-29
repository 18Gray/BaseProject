package com.eighteengray.textview.ellipsizingtextview;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Layout;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.util.AttributeSet;
import com.eighteengray.materialdesign.R;
import java.util.ArrayList;
import java.util.List;



public class EllipsizingTextView extends android.support.v7.widget.AppCompatTextView
{
    private static final String ELLIPSIS = "...";

    public interface EllipsizeListener
    {
        void ellipsizeStateChanged(boolean ellipsized);
    }

    private final List<EllipsizeListener> ellipsizeListeners = new ArrayList<EllipsizeListener>();
    public boolean isNeedEllipsized = true;
    private boolean isEllipsized;
    private boolean isStale;
    public boolean programmaticChange;
    private String fullText;
    private int maxLines = -1;
    private float lineSpacingMultiplier = 1.0f;
    private float lineAdditionalVerticalPadding = 0.0f;

    public EllipsizingTextView(Context context)
    {
        super(context);
    }

    public EllipsizingTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public EllipsizingTextView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }


    @Override
    protected void onTextChanged(CharSequence text, int start, int before, int after)
    {
        super.onTextChanged(text, start, before, after);
        if (!programmaticChange)
        {
            fullText = text.toString();
            isStale = true;
        }
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        if (isStale)
        {
            super.setEllipsize(null);
            resetText();
        }
        super.onDraw(canvas);
    }

    private void resetText()
    {
        int maxLines = getMaxLines();
        String workingText = fullText;
        boolean ellipsized = false;
        if (maxLines != -1)
        {
            Layout layout = createWorkingLayout(workingText);
            if (layout.getLineCount() > maxLines)
            {
                workingText = fullText.substring(0, layout.getLineEnd(maxLines - 1) - 4).trim();
                while (createWorkingLayout(workingText + ELLIPSIS).getLineCount() > maxLines)
                {
                    int lastSpace = workingText.lastIndexOf(' ');
                    if (lastSpace == -1)
                    {
                        break;
                    }
                    workingText = workingText.substring(0, lastSpace);
                }
                workingText = workingText + ELLIPSIS;
                ellipsized = true;
            }
        }
        if (!workingText.equals(getText()))
        {
            programmaticChange = true;
            try
            {
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
                workingText = workingText + "<img src='" + R.drawable.bg_circle_today + "'/>";
                setText(Html.fromHtml(workingText, imageGetter, null));
                setText(workingText);

            } finally
            {
                programmaticChange = false;
            }
        }
        isStale = false;
        if (ellipsized != isEllipsized)
        {
            isEllipsized = ellipsized;
            for (EllipsizeListener listener : ellipsizeListeners)
            {
                listener.ellipsizeStateChanged(ellipsized);
            }
        }
    }



    public Layout createWorkingLayout(String workingText)
    {
        return new StaticLayout(workingText, getPaint(), getWidth() - getPaddingLeft() - getPaddingRight(),
                Alignment.ALIGN_NORMAL, lineSpacingMultiplier, lineAdditionalVerticalPadding, false);
    }

    public boolean isEllipsized()
    {
        return isEllipsized;
    }


    public boolean isNeedEllipsized()
    {
        int pMaxLins = createWorkingLayout(fullText).getLineCount();

        if (pMaxLins <= getMaxLines())
        {
            isNeedEllipsized = false;
        } else
        {
            isNeedEllipsized = true;
        }
        return isNeedEllipsized;
    }


    @Override
    public void setLineSpacing(float add, float mult)
    {
        this.lineAdditionalVerticalPadding = add;
        this.lineSpacingMultiplier = mult;
        super.setLineSpacing(add, mult);
    }


    @Override
    public void setMaxLines(int maxLines)
    {
        super.setMaxLines(maxLines);
        this.maxLines = maxLines;
        isStale = true;
    }


    public int getMaxLines()
    {
        return maxLines;
    }

    public void addEllipsizeListener(EllipsizeListener listener)
    {
        if (listener == null)
        {
            throw new NullPointerException();
        }
        ellipsizeListeners.add(listener);
    }

    public void removeEllipsizeListener(EllipsizeListener listener)
    {
        ellipsizeListeners.remove(listener);
    }




}  
