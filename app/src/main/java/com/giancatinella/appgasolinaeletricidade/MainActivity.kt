package com.giancatinella.appgasolinaeletricidade

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var textInputEnergia: TextInputLayout
    private lateinit var editEnergia: TextInputEditText
    private lateinit var textInputGasolina: TextInputLayout
    private lateinit var editGasolina: TextInputEditText
    private lateinit var btnCalcular: Button
    private lateinit var textResultado: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        inicializarComponentesInterface()
        btnCalcular.setOnClickListener {
            calcularMelhorPreco()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun calcularMelhorPreco() {
        val precoEnergia = editEnergia.text.toString()
        val precoGasolina = editGasolina.text.toString()

        val resultadoValidacao = validarCampos(precoEnergia,precoGasolina)
        if(resultadoValidacao){
            val pEnergia = precoEnergia.toDouble()
            val pGasolina = precoGasolina.toDouble()
            val resultado = pGasolina / pEnergia
            if(resultado <= 4.447){
                textResultado.text = "Melhor usar Gasolina"
            }else {
                textResultado.text = "Melhor usar Eletricidade"
            }


        }




    }

    private fun validarCampos(pEnergia: String, pGasolina: String): Boolean {

        textInputEnergia.error = null
        textInputGasolina.error = null

        if(pEnergia.isEmpty()){
            textInputEnergia.error = "Digite o preçp da Energia"
            return false
        }else if (pGasolina.isEmpty()){
            textInputGasolina.error = "Digite o preço da Gasolina"
            return false
        }
        return true

    }

    private fun inicializarComponentesInterface() {

        textInputEnergia = findViewById(R.id.text_input_energia)
        editEnergia = findViewById(R.id.edit_energia)
        textInputGasolina = findViewById(R.id.text_input_gasolina)
        editGasolina = findViewById(R.id.edit_gasolina)
        btnCalcular = findViewById(R.id.text_btn)
        textResultado = findViewById(R.id.text_resultado)

    }
}