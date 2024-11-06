package com.app.spiral.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.spiral.MainActivity
import com.app.spiral.R
import com.app.spiral.adapter.ServiceAdapter
import com.app.spiral.adapter.StoryAdapter
import com.app.spiral.databinding.FragmentHomeBinding
import com.app.spiral.model.Service

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    lateinit var activity: MainActivity

        val list = listOf(
            Service("Transaction History", R.drawable.ic_chart,R.color.bank_transfer_bg),
            Service("Request Money", R.drawable.ic_money,R.color.bank_transfer_bg),
            Service("Bank Transfer", R.drawable.ic_bank_transfer,R.color.bank_transfer_bg),
            Service("Credit Champ", R.drawable.ic_credit,R.color.credit_bg),
            Service("Caller ID", R.drawable.ic_caller,R.color.caller_bg),
        )
        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        activity = requireActivity() as MainActivity
        binding.ivProfile.setOnClickListener { activity.navController.navigate(R.id.editProfileFragment) }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewStories.adapter = StoryAdapter()
        binding.rvFrequentUsedService.layoutManager=LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL,true)
        var adapter=ServiceAdapter(requireContext(), list) {
            activity.navController.navigate(R.id.pinViewFragment)
        }
        binding.rvFrequentUsedService.adapter = adapter
        binding.rvService.adapter = adapter
    }


}