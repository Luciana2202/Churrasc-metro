package com.example.churrascometro

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.churrascometro.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btCalcular.setOnClickListener {

            val totalAdultosTemp = binding.numAdultos.text
            val totalCriancasTemp = binding.numCriancas.text
            val tempoChurrascoTemp = binding.duracao.text

            if (totalAdultosTemp?.isEmpty() == true ||
                totalCriancasTemp?.isEmpty() == true ||
                tempoChurrascoTemp?.isEmpty() == true
            ) {
                Snackbar.make(
                    binding.numAdultos,
                    "Preencha todos os campos para continuar",
                    Snackbar.LENGTH_LONG
                ).show()
            } else {
                val totalAdultos: Int = totalAdultosTemp.toString().toInt()
                val totalCrianca: Int = totalCriancasTemp.toString().toInt()
                val tempoChurrasco: Int = tempoChurrascoTemp.toString().toInt()

                val porcaoPorPessoa: Float = when {
                    tempoChurrasco <= 3 -> 0.25f  // Até 3 horas: 0.250 kg por pessoa
                    tempoChurrasco <= 5 -> 0.4f   // Até 5 horas: 0.4 kg por pessoa
                    else -> 0.5f         // Acima de 5 horas: 0.5 kg por pessoa
                }

                val quantRefriPorPessoa: Float = when {
                    tempoChurrasco <= 4 -> 0.6f // Até 4 horas: 600 ml por pessoa
                    else -> 1.0f // Acima de 4 horas: 1 litro por pessoa
                }

                val quantAguaPorPessoa: Float = when {
                    tempoChurrasco <= 4 -> 0.3f // Até 4 horas: 600 ml por pessoa
                    else -> 1.0f // Acima de 4 horas: 1 litro por pessoa
                }

                val quantCervejaPorPessoa: Int = when {
                    tempoChurrasco <= 3 -> 4 // Até 3 horas: 4 latas por pessoa
                    tempoChurrasco <= 5 -> 6  // Até 5 horas: 6 latas por pessoa
                    else -> 10        // Acima de 5 horas: 10 latas por pessoa
                }


                val totalCarne: Float =
                    (porcaoPorPessoa * totalAdultos) + ((porcaoPorPessoa / 2) * totalCrianca)
                val totalRefri: Float = quantRefriPorPessoa * (totalAdultos + totalCrianca)
                val totalAgua: Float = quantAguaPorPessoa * (totalAdultos + totalCrianca)
                val totalCerveja: Int = quantCervejaPorPessoa * totalAdultos

                val decimalFormat = DecimalFormat("#.##")
                val totalCarneFormatado = decimalFormat.format(totalCarne)

                val intent = Intent(this, SummaryActivity::class.java)
                intent.apply {
                    putExtra("QuantidadeTotalCarne", totalCarneFormatado)
                    putExtra("QuantidadeTotalRefrigerante", totalRefri)
                    putExtra("QuantidadeTotalAgua", totalAgua)
                    putExtra("QuantidadeTotalCerveja", totalCerveja)
                }
                startActivity(intent)
                clean()
            }
        }
    }

    fun clean() {
        binding.numAdultos.setText("")
        binding.numCriancas.setText("")
        binding.duracao.setText("")

    }
}