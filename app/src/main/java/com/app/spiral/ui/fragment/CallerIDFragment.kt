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
import com.app.spiral.databinding.FragmentCallerIdBinding
import com.app.spiral.databinding.FragmentPinViewBinding


class CallerIDFragment : Fragment() {
    lateinit var binding: FragmentCallerIdBinding
    lateinit var activity: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        it: Bundle?
    ): View {
        binding = FragmentCallerIdBinding.inflate(layoutInflater)
        activity = requireActivity() as MainActivity
        binding.ivBack.setOnClickListener { activity.navController.popBackStack() }
        return binding.root
    }

}