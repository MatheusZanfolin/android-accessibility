package br.com.dextra.accessibility

import android.content.DialogInterface
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
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
        setupInfoIconHandlers()
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

    private fun setupInfoIconHandlers() {
        binding.createAccountInfinityInfoIcon.setOnClickListener {
            showMessageDialog(R.string.create_account_message_dialog_infinity_message)
        }
        binding.createAccountDbzInfoIcon.setOnClickListener {
            showMessageDialog(R.string.create_account_message_dialog_dbz_message)
        }
    }

    private fun showMessageDialog(messageId: Int) {
        AlertDialog.Builder(this)
            .setTitle(R.string.create_account_message_dialog_title)
            .setMessage(getString(messageId))
            .setPositiveButton(R.string.create_account_message_dialog_positive_button_text, { dialog, _ -> dialog.dismiss() })
            .create()
            .show()
    }
}
