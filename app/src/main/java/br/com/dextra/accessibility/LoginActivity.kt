package br.com.dextra.accessibility

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
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
        setupSwitch()
    }

    private fun setupSwitch() {
        binding.backgroundSwitch.isChecked = true
        binding.backgroundSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                addBackground(binding.usernameEdit)
                addBackground(binding.passwordEdit)
            } else {
                removeBackground(binding.usernameEdit)
                removeBackground(binding.passwordEdit)
            }
            binding.usernameEdit.requestFocus()
        }
    }

    private fun addBackground(editText: EditText) {
        editText.background = getDrawable(R.drawable.login_edit_bg)
        editText.setPadding(8)
        editText.setTextColor(Color.WHITE)
        editText.setHintTextColor(Color.WHITE)
    }

    private fun removeBackground(editText: EditText) {
        editText.background = defaultEditBackground
        editText.setTextColor(Color.BLACK)
        editText.setHintTextColor(Color.BLACK)
    }
}
