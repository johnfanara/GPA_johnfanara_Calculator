package com.example.gpa_johnfanara_calculator

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gpa_johnfanara_calculator.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isCalculating = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val grades = arrayOf(
            binding.grade1,
            binding.grade2,
            binding.grade3,
            binding.grade4,
            binding.grade5
        )

        binding.button.setOnClickListener {
            if (isCalculating) {
                var sum = 0.0
                var gradeChecker = 0
                var isEmptyField = false

                for (grade in grades) {
                    val input = grade.text.toString()
                    if (input.isNotEmpty()) {
                        val grade = input.toDouble()
                        sum += grade
                        gradeChecker++
                    } else {
                        isEmptyField = true
                    }
                }

                if (isEmptyField) {
                    binding.displayAvg.text = "You better enter some grades"
                } else if (gradeChecker > 0) {
                    val avg = sum / gradeChecker
                    binding.displayAvg.text = "GPA: $avg"

                    if (avg < 60) {
                        view.setBackgroundColor(Color.RED)
                    } else if (avg > 60 && avg < 80) {
                        view.setBackgroundColor(Color.YELLOW)
                    } else if (avg > 80 && avg <= 100) {
                        view.setBackgroundColor(Color.GREEN)
                    } else {
                        view.setBackgroundColor(Color.WHITE)
                    }
                }
                binding.button.text = "Clear"
                isCalculating = false
            }
            else {
                for (grade in grades) {
                    grade.text.clear()
                }
                binding.displayAvg.text = ""
                view.setBackgroundColor(Color.WHITE)
                binding.button.text = "Calculate"
                isCalculating = true
            }
        }
    }
}
