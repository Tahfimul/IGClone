package com.example.igclone.Util;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import com.example.igclone.R;

public class CommentClickableSpan extends ClickableSpan {

    String spanString;
    int color;


    public CommentClickableSpan(String spanString, int color)
    {
        super();
        this.spanString = spanString;
        this.color = color;
    }

    @Override
    public void onClick(@NonNull View widget) {

    }

    @Override
    public void updateDrawState(@NonNull TextPaint ds) {
        super.updateDrawState(ds);
        ds.setUnderlineText(false);
        ds.setColor(color);
    }
}
