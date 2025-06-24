package com.example.codexthree

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import kotlin.random.Random

class MatrixView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.GREEN
        textSize = 32f
        typeface = Typeface.MONOSPACE
    }
    private var columnY = FloatArray(0)
    private val charPool = """ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789@#$%^&*(){}[]<>?/|+-="""

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        val textHeight = paint.textSize
        val columnCount = (w / textHeight).toInt() + 1
        columnY = FloatArray(columnCount) { Random.nextInt(h) * -1f }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.BLACK)
        val textHeight = paint.textSize
        for (i in columnY.indices) {
            var y = columnY[i]
            val x = i * textHeight
            while (y < height) {
                val ch = charPool[Random.nextInt(charPool.length)].toString()
                paint.color = Color.argb(Random.nextInt(50, 256), 0, 255, 0)
                canvas.drawText(ch, x, y, paint)
                y += textHeight
            }
            columnY[i] += textHeight
            if (columnY[i] > height) {
                columnY[i] = 0f
            }
        }
        postInvalidateOnAnimation()
    }
}
