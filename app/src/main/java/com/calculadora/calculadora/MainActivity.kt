package com.calculadora.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.calculadora.calculadora.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        var view = binding.root

        setContentView(view)

        supportActionBar!!.hide()

        binding.btnZero.setOnClickListener { addExpression("0", true)}
        binding.btnOne.setOnClickListener { addExpression("1", true)}
        binding.btnTwo.setOnClickListener { addExpression("2", true)}
        binding.btnThree.setOnClickListener { addExpression("3", true)}
        binding.btnFour.setOnClickListener { addExpression("4", true)}
        binding.btnFive.setOnClickListener { addExpression("5", true)}
        binding.btnSix.setOnClickListener { addExpression("6", true)}
        binding.btnSeven.setOnClickListener { addExpression("7", true)}
        binding.btnEight.setOnClickListener { addExpression("8", true)}
        binding.btnNine.setOnClickListener { addExpression("9", true)}

        // operators
        binding.btnX.setOnClickListener { addExpression("*", false)}
        binding.btnMinus.setOnClickListener { addExpression("-", false)}
        binding.btnPlus.setOnClickListener { addExpression("+", false)}
        binding.btnBar.setOnClickListener { addExpression("/", false)}
        binding.btnEqual.setOnClickListener { addExpression("=", false)}
        binding.btnComma.setOnClickListener { addExpression(".", false)}

        binding.btnC.setOnClickListener {
            binding.expression.text = ""
            binding.result.text  = ""
        }

        binding.btnBackspace.setOnClickListener {
            val string = binding.expression.text.toString()

            if(string.isNotEmpty()) {
                binding.expression.text = string.substring(0, string.length -1)
            }

            binding.result.text = ""
        }

        binding.btnEqual.setOnClickListener {
            try {
                val valueCalculated =  ExpressionBuilder(binding.expression.text.toString()).build()
                val  tempResult = valueCalculated.evaluate()
                val longResult = tempResult.toString()

                if (tempResult == longResult.toDouble())
                    binding.result.text = longResult.toString()
                else
                    binding.result.text = tempResult.toString()

            } catch (e: Exception) {

            }
        }

    }

     private fun addExpression(string: String, clear:  Boolean) {
        if(binding.result.text.isNotEmpty()) {
            binding.expression.text = ""
        } else if(clear) {
            binding.result.text = ""
            binding.expression.append(string)
        } else {
            binding.expression.append(binding.result.text)
            binding.expression.append(string)
            binding.result.text = ""
        }
    }
}