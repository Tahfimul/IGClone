package com.example.igclone.Util;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.View;
import com.example.igclone.R;

public class CommentUserClickableSpan extends ClickableSpan {

    String spanString;
    int color;

    public CommentUserClickableSpan(String spanString, int color)
    {
        super();
        this.spanString = spanString;
        this.color = color;
    }
    @Override
    public void onClick(@NonNull View widget) {

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void updateDrawState(TextPaint p_DrawState) {
        super.updateDrawState(p_DrawState);
        p_DrawState.setUnderlineText(false);
        p_DrawState.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        p_DrawState.setColor(color);
    }
}