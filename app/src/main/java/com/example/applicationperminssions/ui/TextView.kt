package com.example.applicationperminssions.ui

import android.R
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.ResourcesCompat


class TextView(context: Context, val attributeSet: AttributeSet, defStyleAttr: Int) : AppCompatTextView(context, attributeSet, defStyleAttr) {

    init {
        applyFont()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    private fun applyFont() {
//        val headlineTypeface: Typeface = Typeface.create("shizuru", Typeface.NORMAL)
//        typeface = headlineTypeface
//
//        val a: TypedArray = context.obtainStyledAttributes(attributeSet,0)
        val customFont = "shizuru"
        val tf = Typeface.createFromAsset(context.assets, "assets/$customFont.ttf")
        typeface = tf
//        a.recycle()
    }
}