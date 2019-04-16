package br.com.dextra.accessibility

import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.TransitionDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.databinding.DataBindingUtil
import br.com.dextra.accessibility.databinding.ActivityMainBinding

class MainActivity : Activity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var defaultIntroductionColor: ColorStateList
    private lateinit var defaultTitleColor: ColorStateList
    private lateinit var defaultSwitchTextColor: ColorStateList
    private lateinit var defaultButtonTextColor: ColorStateList

    val TRANSITION_TIME_MILIS = 500

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
        val background = binding.root.background as TransitionDrawable
        background.startTransition(TRANSITION_TIME_MILIS)
    }

    private fun removeContrast() {
        val background = binding.root.background as TransitionDrawable
        background.reverseTransition(TRANSITION_TIME_MILIS)
    }
}
