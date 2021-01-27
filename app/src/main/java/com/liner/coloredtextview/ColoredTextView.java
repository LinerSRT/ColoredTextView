package com.liner.coloredtextview;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColoredTextView extends AppCompatTextView {
    private final Pattern pattern = Pattern.compile("\\{c=([#0-9a-fA-F]*), t=([a-zA-Z0-9!?,. ~@#$%^&*()_\\-+|\n\t\r]*)}");

    public ColoredTextView(@NonNull Context context) {
        this(context, null);
    }

    public ColoredTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setText(processSpannableText(getText().toString()));
    }

    public void setText(String text){
        setText(processSpannableText(text));
    }

    private Spannable processSpannableText(String text) {
        List<ColorSpan> colorSpanList = new ArrayList<>();
        colorSpanList.clear();
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            colorSpanList.add(new ColorSpan(matcher.group(0), matcher.group(2), Color.parseColor(matcher.group(1))));
        }
        String clearSpannableText = text;
        for (ColorSpan colorSpan : colorSpanList)
            clearSpannableText = clearSpannableText.replace(colorSpan.key, colorSpan.text);
        Spannable spannable = new SpannableString(clearSpannableText);
        for (ColorSpan colorSpan : colorSpanList) {
            int startIndex = clearSpannableText.indexOf(colorSpan.text);
            int endIndex = startIndex + colorSpan.text.length();
            spannable.setSpan(new ForegroundColorSpan(colorSpan.color), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannable;
    }

    private static class ColorSpan {
        public String key;
        public String text;
        @ColorInt
        public int color;

        public ColorSpan(String key, String text, int color) {
            this.key = key;
            this.text = text;
            this.color = color;
        }
    }

    public static class Builder{
        private final StringBuilder spannableString;

        public Builder() {
            this.spannableString = new StringBuilder();
        }

        public Builder add(String text){
            spannableString.append(text);
            return this;
        }

        public Builder add(String text, String color){
            spannableString.append("{c=").append(color).append(", t=").append(text).append("}");
            return this;
        }

        public Builder add(String text, @ColorInt int color){
            spannableString.append("{c=").append(String.format("#%06X", (0xFFFFFF & color))).append(", t=").append(text).append("}");
            return this;
        }

        public String build(){
            return spannableString.toString();
        }
    }
}
