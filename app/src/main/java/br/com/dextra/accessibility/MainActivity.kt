package br.com.dextra.accessibility

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import br.com.dextra.accessibility.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    lateinit var defaultIntroductionColor: ColorStateList
    lateinit var defaultTitleColor: ColorStateList
    lateinit var defaultSwitchTextColor: ColorStateList
    lateinit var defaultButtonTextColor: ColorStateList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setDefaultTextColors()
        setupScreenComponents()
    }

    private fun setDefaultTextColors() {
        defaultTitleColor = binding.loginAppNameText.textColors
        defaultIntroductionColor = binding.loginIntroductionText.textColors
        defaultSwitchTextColor = binding.contrastSwitchText.textColors
        defaultButtonTextColor = binding.startButton.textColors
    }

    private fun setupScreenComponents() {
        title = getString(R.string.activity_main_title)
        setupSwitch()
        setupLoginButton()
    }

    private fun setupSwitch() {
        binding.contrastSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                applyConstrast()
            } else {
                removeContrast()
            }
        }
    }

    private fun setupLoginButton() {
        binding.startButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun applyConstrast() {
        binding.root.background = getDrawable(R.color.backgroundDark)
        binding.loginAppNameText.setTextColor(Color.WHITE)
        binding.loginIntroductionText.setTextColor(Color.WHITE)
        binding.contrastSwitchText.setTextColor(Color.WHITE)
        binding.startButton.setTextColor(Color.WHITE)
    }

    private fun removeContrast() {
        binding.root.background = getDrawable(R.color.background)
        binding.loginAppNameText.setTextColor(defaultTitleColor)
        binding.loginIntroductionText.setTextColor(defaultIntroductionColor)
        binding.contrastSwitchText.setTextColor(defaultSwitchTextColor)
        binding.startButton.setTextColor(defaultButtonTextColor)
    }
}
