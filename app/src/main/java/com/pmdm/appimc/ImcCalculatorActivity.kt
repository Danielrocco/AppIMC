package com.pmdm.appimc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class ImcCalculatorActivity : AppCompatActivity() {

    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var rsHeight: RangeSlider
    private lateinit var tvHeight: TextView
    private lateinit var tvWeight: TextView
    private lateinit var tvAge: TextView
    private lateinit var btnAddWeight: FloatingActionButton
    private lateinit var btnAddAge: FloatingActionButton
    private lateinit var btnRemoveWeight: FloatingActionButton
    private lateinit var btnRemoveAge: FloatingActionButton
    private lateinit var btnCalc: AppCompatButton

    private var isMaleSelected: Boolean = true
    private var height: Int = 0
    private var weight: Int = 40
    private var age: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_calculator)
        initComponents()
        initListeners()
        initUI()
    }

    private fun initListeners() {
        setGenderColor()
        viewMale.setOnClickListener() {
            isMaleSelected = true
            setGenderColor()
        }

        viewFemale.setOnClickListener() {
            isMaleSelected = false
            setGenderColor()
        }

        rsHeight.addOnChangeListener { _, value, _ ->
            tvHeight.text = DecimalFormat("#.##").format(value) + " cm"
            height = value.toInt()
        }

        btnRemoveWeight.setOnClickListener() {
            weight -= 1
            setWeight()
        }

        btnAddWeight.setOnClickListener {
            weight += 1
            setWeight()
        }

        btnRemoveAge.setOnClickListener {
            age -= 1
            setAge()
        }

        btnAddAge.setOnClickListener {
            age += 1
            setAge()
        }

        btnCalc.setOnClickListener {
            navigate2result(calculateImc())
        }
    }

    private fun navigate2result(d:Double) {
        val intentResult = Intent(this, ImcResultActivity::class.java)
        intentResult.putExtra("IMC", d)
        startActivity(intentResult)
    }

    fun calculateImc(): Double {
        var result: Double = 0.0
        result = weight.toDouble()/Math.pow((height/100.0),2.0)
        val formato = DecimalFormat("#.##")
        formato.maximumFractionDigits = 2
        val resultformat = formato.format(result)
        result = resultformat.toDouble()
        return result
    }


    private fun initUI() {
        setGenderColor()
        setWeight()
        setAge()
    }

    private fun getBackgroundColor(isComponentSelected: Boolean): Int {
        val colorReference = if (isComponentSelected) {
                R.color.bg_comp_sel
        } else {
            R.color.bg_comp
        }
        return ContextCompat.getColor(this, colorReference)
    }

    private fun initComponents() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        rsHeight = findViewById(R.id.rsHeight)
        tvHeight = findViewById(R.id.tvHeight)
        tvWeight = findViewById(R.id.tvWeight)
        tvAge = findViewById(R.id.tvAge)
        btnAddAge = findViewById(R.id.btnAddAge)
        btnAddWeight = findViewById(R.id.btnAddWeight)
        btnRemoveAge = findViewById(R.id.btnRemoveAge)
        btnRemoveWeight = findViewById(R.id.btnRemoveWeight)
        btnCalc = findViewById(R.id.btnCalc)
    }

    private fun setGenderColor() {
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(!isMaleSelected))
    }

    private fun setWeight() {
        tvWeight.setText(weight.toString() + " Kg")
    }

    private fun setAge() {
        tvAge.setText(age.toString() + " Años")
    }
}