package com.kayere.customviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.widget.Toast

class Circle(context: Context, attr: AttributeSet): View(context, attr){

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var halfWidth = 0
    private var halfHeight = 0
    private var radius = 0
    //getting attributes specified in xml
    private val typedArray = context.theme.obtainStyledAttributes(attr, R.styleable.Circle, 0, 0)
    private val circleColor = typedArray.getColor(R.styleable.Circle_circleColor, Color.YELLOW)
    private val borderColor = typedArray.getColor(R.styleable.Circle_borderColor, Color.BLACK)
    private val borderWidth = typedArray.getFloat(R.styleable.Circle_borderWidth, 2F)

    override fun onDraw(canvas: Canvas?) {
        //drawing the circle
        paint.apply { color = circleColor; style = Paint.Style.FILL }
        canvas?.drawCircle(halfWidth.toFloat(), halfHeight.toFloat(), radius.toFloat(), paint)
        //drawing circle border
        paint.apply { color = borderColor; style = Paint.Style.STROKE; strokeWidth = borderWidth}
        canvas?.drawCircle(halfWidth.toFloat(), halfHeight.toFloat(), radius.toFloat(), paint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        halfHeight = measuredHeight / 2
        halfWidth = measuredWidth / 2
        radius = halfHeight.coerceAtMost(halfWidth) - borderWidth.toInt()
        setMeasuredDimension(measuredWidth, measuredHeight)
    }
}