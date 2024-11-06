package com.app.spiral

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.spiral.databinding.FragmentAuthSuccessBinding


class AuthSuccessFragment : Fragment() {
   lateinit var binding: FragmentAuthSuccessBinding
    lateinit var activity: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        it: Bundle?
    ): View {
        binding = FragmentAuthSuccessBinding.inflate(layoutInflater)
        activity=requireActivity() as MainActivity
        Handler().postDelayed({
            activity.navController.navigate(R.id.homeFragment)
        },3000)
        return binding.root
    }
}