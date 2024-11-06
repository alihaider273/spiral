package com.app.spiral

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.spiral.databinding.FragmentAuthSuccessBinding
import com.app.spiral.databinding.FragmentEditProfileBinding
import com.app.spiral.databinding.FragmentLoginBinding


class EditProfileFragment : Fragment() {
   lateinit var binding: FragmentEditProfileBinding
   lateinit var activity: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        it: Bundle?
    ): View {
        binding = FragmentEditProfileBinding.inflate(layoutInflater)
        activity=requireActivity() as MainActivity
        binding.ivBack.setOnClickListener { showHomeScreen() }
        binding.tvSave.setOnClickListener { showHomeScreen() }
        return binding.root
    }

    private fun showHomeScreen() {
        activity.navController.popBackStack()
    }
}