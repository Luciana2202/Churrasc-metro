package com.example.churrascometro

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.churrascometro.databinding.ActivitySummaryBinding

class SummaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySummaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val totalCarne = intent.getStringExtra("QuantidadeTotalCarne")
        val totalRefri = intent.getFloatExtra("QuantidadeTotalRefrigerante", 0.0f)
        val totalAgua = intent.getFloatExtra("QuantidadeTotalAgua", 0.0f)
        val totalCerveja = intent.getIntExtra("QuantidadeTotalCerveja", 0)

        binding.totalCarne.text = "Quantidade total de Carne(Kg): " + totalCarne
        binding.totalRefrigerante.text = "Quantidade total de Refrigerante(L): " + totalRefri.toString()
        binding.totalAgua.text = "Quantidade total de Água(L): " + totalAgua.toString()
        binding.totalCerveja.text = "Quantidade total de Cerveja(Lata): " + totalCerveja.toString()

        binding.btRecalcular.setOnClickListener {
            finish()
        }
    }
}