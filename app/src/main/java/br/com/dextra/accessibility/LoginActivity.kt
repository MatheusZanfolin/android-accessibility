package br.com.dextra.accessibility

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

    lateinit var binding: ActivityLoginBinding

    lateinit var defaultEditBackground: Drawable

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
        setupButton()
    }

    private fun setupButton() {
        binding.loginButton.setOnClickListener {
            Toast.makeText(this, getString(R.string.login_fail_message), Toast.LENGTH_SHORT).show()
        }
    }
}
