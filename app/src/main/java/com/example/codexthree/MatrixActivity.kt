package com.example.codexthree

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MatrixActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_matrix)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.matrix_root)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textView = findViewById<TextView>(R.id.matrix_text)
        textView.text = generateMatrixText()

        textView.post {
            val parentHeight = findViewById<View>(R.id.matrix_root).height
            val anim = ValueAnimator.ofFloat(-textView.height.toFloat(), parentHeight.toFloat())
            anim.duration = 5000L
            anim.repeatCount = ValueAnimator.INFINITE
            anim.interpolator = LinearInterpolator()
            anim.addUpdateListener {
                textView.translationY = it.animatedValue as Float
            }
            anim.start()
        }
    }

    private fun generateMatrixText(): String {
        val chars = "01"
        val builder = StringBuilder()
        repeat(200) {
            repeat(40) { builder.append(chars[Random.nextInt(chars.length)]) }
            builder.append("\n")
        }
        return builder.toString()
    }
}
