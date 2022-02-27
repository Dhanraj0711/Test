package com.example.applicationperminssions.ui

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout

class ConstraintLayout(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : ConstraintLayout(context, attributeSet, defStyleAttr) {

    init {

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}