package com.pmdm.appimc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import java.text.DecimalFormat

class ImcResultActivity : AppCompatActivity() {
    private lateinit var tvResult: TextView
    private lateinit var tvTitulo: TextView
    private lateinit var tvDescri: TextView
    private lateinit var btnRecalc: AppCompatButton

    private var resultado: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_result)
        initComponents()
        initListeners()
        resultado = intent.extras?.getDouble("IMC")?: 0.0

        initUI()
    }

    private fun initListeners() {
        btnRecalc.setOnClickListener {
            val intentCalculator = Intent(this, ImcCalculatorActivity::class.java)
            startActivity(intentCalculator)
        }
    }
    private fun initComponents() {
        tvResult = findViewById(R.id.tvResult)
        tvTitulo = findViewById(R.id.tvTitulo)
        tvDescri = findViewById(R.id.tvDescri)
        btnRecalc = findViewById(R.id.btnRecalc)
    }

    private fun initUI() {
        setTitulo()
        setResultado()
        setDescripcion()
    }

    private fun setTitulo() {
        if (resultado <= 18.5) {
            tvTitulo.text = "Peso inferior al normal"
        } else if (resultado > 18.5 && resultado < 24.9) {
            tvTitulo.text = "Normal"
        } else if (resultado >= 25 && resultado < 29.9) {
            tvTitulo.text = "Peso superior al normal"
        } else if (resultado >= 30) {
            tvTitulo.text = "Obesidad"
        }
    }

    private fun setResultado() {
            tvResult.text = resultado.toString()
    }

    private fun setDescripcion() {
        if (resultado <= 18.5) {
            tvDescri.text = "tas flaco weon come bocadillo mortadela weon"
        } else if (resultado > 18.5 && resultado < 24.9) {
            tvDescri.text = "Fino señores"
        } else if (resultado >= 25 && resultado < 29.9) {
            tvDescri.text = "El lorzitas, empieza la dieta chiquitin"
        } else if (resultado >= 30) {
            tvDescri.text = "Irrecuperable, es mas facil saltarte que rodearte"
        }
    }
}