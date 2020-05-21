package com.aceinteract.topcoder.covidstats.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import com.aceinteract.topcoder.covidstats.R
import com.aceinteract.topcoder.covidstats.util.getColorCompat
import kotlin.math.*

class PieChartView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    @ColorInt
    var textColor = context.getColorCompat(android.R.color.white)
        set(value) {
            field = value
            textPaint.color = value
            invalidate()
        }

    var textSize = 0
        set(value) {
            field = value
            textPaint.textSize = value.toFloat()
            invalidate()
        }

    var strokeWidth = 0
        set(value) {
            field = value
            defaultPaint.strokeWidth = value.toFloat()
            backgroundPaint.strokeWidth = value.toFloat()
            invalidate()
        }

    var piePieces = listOf<PiePiece>()
        set(value) {
            field = value
            invalidate()
        }

    private var rectOval = RectF()

    private val defaultPaint = Paint().apply {
        isAntiAlias = true
        color = context.getColorCompat(R.color.color_accent)
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.SQUARE
        strokeWidth = this@PieChartView.strokeWidth.toFloat()
    }

    private val textPaint = Paint().apply {
        isAntiAlias = true
        color = textColor
        textSize = this@PieChartView.textSize.toFloat()
    }

    private val backgroundPaint = Paint().apply {
        isAntiAlias = true
        color = context.getColorCompat(R.color.color_dark_translucent)
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        strokeWidth = this@PieChartView.strokeWidth.toFloat()
    }

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.PieChartView, 0, 0)
        try {
            strokeWidth = typedArray.getDimensionPixelSize(
                R.styleable.PieChartView_strokeSize,
                24 * resources.displayMetrics.density.toInt()
            )
            textSize = typedArray.getDimensionPixelSize(
                R.styleable.PieChartView_textSize,
                12 * resources.displayMetrics.density.toInt()
            )
            textColor = typedArray.getColor(
                R.styleable.PieChartView_textColor,
                context.getColorCompat(android.R.color.white)
            )
        } finally {
            typedArray.recycle()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawArc(rectOval, 0F, 360F, false, backgroundPaint)

        val radius = rectOval.width() / 2

        val totalPiecesSize = piePieces.sumByDouble { it.value }
        var lastDegree = 0F

        piePieces.forEach {
            // Draw Arc
            val arcPaint = defaultPaint.apply { color = it.color }
            val rationDegreeSweep = (it.value / totalPiecesSize) * 360
            canvas.drawArc(rectOval, lastDegree, rationDegreeSweep.toFloat(), false, arcPaint)
            lastDegree = (rationDegreeSweep + lastDegree).toFloat()

            // Draw Text
            val textX =
                (rectOval.right - (rectOval.width() / 2)) + radius * cos(Math.toRadians(lastDegree - (rationDegreeSweep / 2)))
            val textY =
                (rectOval.bottom - (rectOval.height() / 2)) + radius * sin(Math.toRadians(lastDegree - (rationDegreeSweep / 2)))
            val percentage = (it.value / totalPiecesSize) * 100
            canvas.drawText(
                "${round(percentage)}%",
                (textX - textSize).toFloat(),
                (textY + (textSize / 2)).toFloat(),
                textPaint
            )
        }

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        val strokePadding = strokeWidth / 2

        val smallerDimension = min(w, h).toFloat()
        val x = (w / 2) - (smallerDimension / 2)
        val y = (h / 2) - (smallerDimension / 2)
        rectOval = RectF(
            x + strokePadding,
            y + strokePadding,
            smallerDimension + x - strokePadding,
            smallerDimension + y - strokePadding
        )
    }

    data class PiePiece(val value: Double, @ColorInt val color: Int)

}