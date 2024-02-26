package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.tipcalculator.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    fun calculateTip(view: View)
    {
        if (binding.billAmount.text.isNotEmpty())
        {
            val dollarValue = binding.billAmount.text.toString().toFloat()
            val tenPercent = (dollarValue * .10) + dollarValue
            val fifthteenPercent = (dollarValue * .15) + dollarValue
            val twentyPercent = (dollarValue * .20) + dollarValue
            val message = """ 
                The tips are as Follows:
                
                10% = $tenPercent
                15% = $fifthteenPercent
                20% = $twentyPercent
            """.trimIndent()
            binding.output.text = message
        } else
        {
            binding.output.text = "YOU MUST ENTER A BILL AMOUNT "
        }
    }



}