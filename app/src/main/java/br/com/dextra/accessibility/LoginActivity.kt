package br.com.dextra.accessibility

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.setPadding
import androidx.databinding.DataBindingUtil
import br.com.dextra.accessibility.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private lateinit var defaultEditBackground: Drawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        setupDefaultValues()
        setupScreenComponents()
    }

    private fun setupDefaultValues() {
        defaultEditBackground = binding.usernameEdit.background
    }

    private fun setupScreenComponents() {
        setupLoginButton()
        setupCreateAccountLink()
    }

    private fun setupLoginButton() {
        binding.loginButton.setOnClickListener {
            Toast.makeText(this, getMessageByInput(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupCreateAccountLink() {
        binding.createAccountLinkBg.setOnClickListener {
            startActivity(Intent(this, CreateAccountActivity::class.java))
        }
    }

    private fun getMessageByInput(): CharSequence {
        return if (emptyInput()) getString(R.string.login_empty_input) else getString(R.string.login_fail_message)
    }

    private fun emptyInput(): Boolean {
        return binding.usernameEdit.text.isNullOrBlank() || binding.passwordEdit.text.isNullOrBlank()
    }
}
