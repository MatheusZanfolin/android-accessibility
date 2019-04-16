package br.com.dextra.accessibility

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.TransitionDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import br.com.dextra.accessibility.databinding.ActivityMainBinding

class MainActivity : Activity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var defaultTextViewColor: ColorStateList
    private lateinit var defaultButtonTextColor: ColorStateList

    val TRANSITION_TIME_MILIS = 500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setDefaultTextColors()
        setupScreenComponents()
    }

    private fun setDefaultTextColors() {
        defaultTextViewColor = binding.loginAppNameText.textColors
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
        fadeToWhite(binding.loginAppNameText)
        fadeToWhite(binding.loginIntroductionText)
        fadeToWhite(binding.contrastSwitchText)
        fadeToWhite(binding.startButton)
    }

    private fun fadeToWhite(view: TextView) {
        fade(view, getDefaultColor(view), Color.WHITE)
    }

    private fun removeContrast() {
        val background = binding.root.background as TransitionDrawable
        background.reverseTransition(TRANSITION_TIME_MILIS)
        fadeToDefault(binding.loginAppNameText)
        fadeToDefault(binding.loginIntroductionText)
        fadeToDefault(binding.contrastSwitchText)
        fadeToDefault(binding.startButton)
    }

    private fun fadeToDefault(view: TextView) {
        fade(view, Color.WHITE, getDefaultColor(view))

    }

    private fun fade(view: TextView, startColor: Int, endColor: Int) {
        val anim = ObjectAnimator.ofInt(view, "textColor", startColor, endColor)
            .setDuration(TRANSITION_TIME_MILIS.toLong())
        anim.setEvaluator(ArgbEvaluator())
        anim.start()
    }

    private fun getDefaultColor(view: TextView): Int {
        return if (view is Button) defaultButtonTextColor.defaultColor else defaultTextViewColor.defaultColor
    }
}
