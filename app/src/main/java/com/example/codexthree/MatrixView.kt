package com.example.codexthree // App's package name

import android.content.Context       // Provides access to application-specific resources
import android.graphics.Canvas       // Canvas to draw on the screen
import android.graphics.Color        // Utility for colors
import android.graphics.Paint        // Paint object describes how to draw
import android.graphics.Typeface     // Allows selecting font family
import android.util.AttributeSet     // For XML attributes
import android.view.View             // Base class for custom views
import kotlin.random.Random          // Random number generator

// Custom view that draws the Matrix rain effect
class MatrixView @JvmOverloads constructor(
    context: Context,               // Context from the parent view or activity
    attrs: AttributeSet? = null     // Optional attributes from XML
) : View(context, attrs) {          // Extends Android's View class

    // Paint object for drawing the characters
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.GREEN                     // Use a classic Matrix green
        textSize = 32f                          // Size of each character
        typeface = Typeface.MONOSPACE          // Monospace font for uniform width
    } // End Paint configuration

    // Y positions for each column of text
    private var columnY = FloatArray(0)

    // Pool of characters to randomly display
    private val charPool = """ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789@#$%^&*(){}[]<>?/|+-="""

    // Called whenever the view size changes
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        val textHeight = paint.textSize                 // Height of one character
        val columnCount = (w / textHeight).toInt() + 1  // How many columns fit on screen
        // Start each column at a random vertical position above the screen
        columnY = FloatArray(columnCount) { Random.nextInt(h) * -1f }
    } // End onSizeChanged

    // Draw one frame of the animation
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)                       // Let the parent do its work
        canvas.drawColor(Color.BLACK)              // Clear the screen with black
        val textHeight = paint.textSize            // Character height used for spacing
        for (i in columnY.indices) {               // Loop over each text column
            var y = columnY[i]                     // Current Y position for this column
            val x = i * textHeight                 // X coordinate based on column index
            while (y < height) {                   // Draw characters down the screen
                // Pick a random character from our pool
                val ch = charPool[Random.nextInt(charPool.length)].toString()
                // Random alpha value for fading effect
                paint.color = Color.argb(Random.nextInt(50, 256), 0, 255, 0)
                canvas.drawText(ch, x, y, paint)   // Draw the character
                y += textHeight / 2                // Move down half a char height (slower)
            } // End while loop
            columnY[i] += textHeight / 2           // Advance the starting Y position
            if (columnY[i] > height) {             // If we've moved past bottom
                columnY[i] = 0f                    // Reset to top
            } // End if
        } // End for loop
        postInvalidateOnAnimation()                // Schedule next frame
    } // End onDraw
} // End of MatrixView class
