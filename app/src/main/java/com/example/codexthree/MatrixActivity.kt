package com.example.codexthree // App package

import android.os.Bundle                       // To receive savedInstanceState
import androidx.activity.enableEdgeToEdge      // Utility for drawing edge-to-edge
import androidx.appcompat.app.AppCompatActivity // Base class for activities
import androidx.core.view.ViewCompat            // Compat helpers for views
import androidx.core.view.WindowInsetsCompat    // For handling system insets

// Activity that hosts the MatrixView animation
class MatrixActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)                 // Call parent implementation
        enableEdgeToEdge()                                 // Draw under system bars
        setContentView(R.layout.activity_matrix)           // Use layout containing MatrixView
        // Adjust padding so content isn't obscured by system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.matrix_root)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars()) // Areas occupied by status/navigation bars
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom) // Apply padding
            insets
        }
    } // End onCreate
} // End of MatrixActivity
