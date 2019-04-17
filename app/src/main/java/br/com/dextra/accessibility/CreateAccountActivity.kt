package br.com.dextra.accessibility

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import br.com.dextra.accessibility.databinding.ActivityCreateAccountBinding

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_account)
        title = "Novo usuário"
        setupScreenComponents()
    }

    private fun setupScreenComponents() {
        setupNumericEditTexts()
        setupCreateAccountButton()
    }

    private fun setupNumericEditTexts() {
        binding.createAccountDbzEdit.setOnFocusChangeListener { v, hasFocus -> onNumericEditFocusChange(binding.createAccountDbzEdit, hasFocus) }
        binding.createAccountInfinityEdit.setOnFocusChangeListener { v, hasFocus -> onNumericEditFocusChange(binding.createAccountInfinityEdit, hasFocus) }
    }

    private fun onNumericEditFocusChange(target: EditText, hasFocus: Boolean) {
        val currentText = target.text.toString()
        if (hasFocus) {
            if (currentText == "0") {
                target.setText("")
            }
        } else {
            if (currentText == "") {
                target.setText("0")
            }
        }
    }

    private fun setupCreateAccountButton() {
        binding.createAccountButton.setOnClickListener {
            Toast.makeText(this, "Usuário criado com sucesso", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
