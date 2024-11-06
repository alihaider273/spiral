package com.app.spiral

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import com.app.spiral.PasskeyFragment.Companion.showBiometricPrompt
import com.app.spiral.databinding.FragmentPinViewBinding


class PinViewFragment : Fragment() {
    lateinit var binding: FragmentPinViewBinding
    lateinit var activity: MainActivity
    private var pinValue = StringBuilder()  // Holds the current pin value
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        it: Bundle?
    ): View {
        binding = FragmentPinViewBinding.inflate(layoutInflater)
        activity = requireActivity() as MainActivity
        Handler().postDelayed(
            {
                binding.ivCall.visibility = View.GONE
                binding.progressBar.visibility = View.GONE
                binding.llMain.visibility = View.VISIBLE
            },
            2000
        )
        clickListeners()
        return binding.root
    }

    private fun clickListeners() {
        binding.tvEnterPin.setOnClickListener {
           showCallerScreen()
        }
        binding.ivThumb.setOnClickListener {
            showBiometricPrompt(this) {
                showCallerScreen()
            }
        }
        binding.ivBack.setOnClickListener { activity.navController.popBackStack() }
        setValue(binding.tv1)
        setValue(binding.tv2)
        setValue(binding.tv3)
        setValue(binding.tv4)
        setValue(binding.tv5)
        setValue(binding.tv6)
        setValue(binding.tv7)
        setValue(binding.tv8)
        setValue(binding.tv9)
        setValue(binding.tv0)
    }

    private fun showCallerScreen() {
        activity.navController.navigate(
            R.id.callerIDFragment, null, NavOptions.Builder()
                .setPopUpTo(R.id.pinViewFragment, true)
                .build()
        )
    }

    private fun setValue(textView: TextView) {
        textView.setOnClickListener {
            if (pinValue.length < 4) {
                pinValue.append((textView.text.toString()))
                binding.pinView.setText(pinValue.toString())
            }
        }

    }
}