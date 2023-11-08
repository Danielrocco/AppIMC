package com.pmdm.appimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat

class ImcCalculatorActivity : AppCompatActivity() {

    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private var isMaleSelected: Boolean = true

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
    }

    private fun initUI() {
        setGenderColor()
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
    }

    private fun setGenderColor() {
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(!isMaleSelected))
    }
}