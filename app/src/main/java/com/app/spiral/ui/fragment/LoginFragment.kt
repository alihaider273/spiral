package com.app.spiral

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.InputType
import android.util.Patterns
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import com.app.spiral.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    lateinit var activity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        it: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        activity = requireActivity() as MainActivity
        clickListener()
        showHidePassword()
        return binding.root
    }

    private fun clickListener() {
        binding.llSignIn.setOnClickListener { activity.navController.navigate(R.id.passKeyFragment) }
        binding.tvSignIn.setOnClickListener {
            if (binding.etEmail.text.toString().isEmpty()) {
                binding.etEmail.error = getString(R.string.email_field_is_required)
            } else if (!isValidEmail(binding.etEmail.text.toString())) {
                binding.etEmail.error = getString(R.string.invalid_email_address)
            } else if (binding.etPassword.text.toString().isEmpty()) {
                binding.etPassword.error = getString(R.string.password_field_is_required)
            } else {
                activity.navController.navigate(R.id.homeFragment, null, NavOptions.Builder()
                    .setPopUpTo(R.id.loginFragment, true)
                    .build()) }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun showHidePassword() {
        var isPasswordVisible = false
        binding.etPassword.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val drawableEnd = 2 // Index of drawableEnd
                if (event.rawX >= ((binding.etPassword.right - binding.etPassword.compoundDrawables[drawableEnd]?.bounds?.width()!!))) {
                    // Toggle password visibility
                    if (isPasswordVisible) {
                        // Hide password
                        binding.etPassword.inputType =
                            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                        binding.etPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            0,
                            0,
                            R.drawable.ic_visibility_off,
                            0
                        )
                    } else {
                        // Show password
                        binding.etPassword.inputType =
                            InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                        binding.etPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            0,
                            0,
                            R.drawable.ic_visibility_on,
                            0
                        )
                    }
                    // Move cursor to the end of the input
                    binding.etPassword.setSelection(binding.etPassword.text.length)
                    isPasswordVisible = !isPasswordVisible
                    return@setOnTouchListener true
                }
            }
            false
        }
    }
    fun isValidEmail(email: String): Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}