package com.example.applicationperminssions.ui

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.applicationperminssions.R


class FitsSystemWindowConstraintLayout(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
    : ConstraintLayout(context, attrs, defStyleAttr) {
    private var statusBarBackgroundDrawable: Drawable? = null
    private var mDrawStatusBarBackground = false
    private var mLastInsets: WindowInsetsCompat? = null
    private val childsMargins: MutableMap<View, IntArray> = HashMap()

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    private fun setChildInsets(insets: WindowInsetsCompat?, draw: Boolean) {
        mLastInsets = insets
        mDrawStatusBarBackground = draw
        setWillNotDraw(!draw && background == null)
        for (i in 0 until childCount) {
            val child: View = getChildAt(i)
            if (child.visibility !== GONE) {
                if (ViewCompat.getFitsSystemWindows(this)) {
                    val layoutParams = child.layoutParams as LayoutParams
                    if (ViewCompat.getFitsSystemWindows(child)) {
                        ViewCompat.dispatchApplyWindowInsets(child, insets!!)
                    } else {
                        var childMargins = childsMargins[child]
                        if (childMargins == null) {
                            childMargins = intArrayOf(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.rightMargin, layoutParams.bottomMargin)
                            childsMargins[child] = childMargins
                        }
                        if (layoutParams.leftToLeft == LayoutParams.PARENT_ID) {
                            layoutParams.leftMargin = childMargins[0] + insets!!.systemWindowInsetLeft
                        }
                        if (layoutParams.topToTop == LayoutParams.PARENT_ID) {
                            layoutParams.topMargin = childMargins[1] + insets!!.systemWindowInsetTop
                        }
                        if (layoutParams.rightToRight == LayoutParams.PARENT_ID) {
                            layoutParams.rightMargin = childMargins[2] + insets!!.systemWindowInsetRight
                        }
                        if (layoutParams.bottomToBottom == LayoutParams.PARENT_ID) {
                            layoutParams.bottomMargin = childMargins[3] + insets!!.systemWindowInsetBottom
                        }
                    }
                }
            }
        }
        requestLayout()
    }

    fun setStatusBarBackground(bg: Drawable?) {
        statusBarBackgroundDrawable = bg
        invalidate()
    }

    fun setStatusBarBackground(resId: Int) {
        statusBarBackgroundDrawable = if (resId != 0) ContextCompat.getDrawable(context, resId) else null
        invalidate()
    }

    fun setStatusBarBackgroundColor(@ColorInt color: Int) {
        statusBarBackgroundDrawable = ColorDrawable(color)
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (mDrawStatusBarBackground && statusBarBackgroundDrawable != null) {
            val inset = if (mLastInsets != null) mLastInsets!!.systemWindowInsetTop else 0
            if (inset > 0) {
                statusBarBackgroundDrawable!!.setBounds(0, 0, width, inset)
                statusBarBackgroundDrawable!!.draw(canvas!!)
            }
        }
    }

    init {
        if (ViewCompat.getFitsSystemWindows(this)) {
            ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
                val layout = v as FitsSystemWindowConstraintLayout
                layout.setChildInsets(insets, insets?.systemWindowInsetTop!! > 0)
                insets.consumeSystemWindowInsets()
            }
            systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
            val typedArray: TypedArray = context.obtainStyledAttributes(intArrayOf(R.attr.colorPrimaryDark))
            try {
                statusBarBackgroundDrawable = typedArray.getDrawable(0)
            } finally {
                typedArray.recycle()
            }
        } else {
            statusBarBackgroundDrawable = null
        }
    }
}