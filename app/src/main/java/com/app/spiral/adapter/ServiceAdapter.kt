package com.app.spiral.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.spiral.R
import com.app.spiral.databinding.ItemServiceBinding
import com.app.spiral.model.Service

class ServiceAdapter(
    private val context: Context,
    private var list: List<Service>,
    private val onSelected: () -> Unit = {}
) : RecyclerView.Adapter<ServiceAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemServiceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val item = list[position]
        with(holder.binding) {
            tvTitle.text = item.title
            ivIcon.setImageResource(item.imageResId)
            roundedLayout.setCardBackgroundColor(context.getColor(item.backgroundColor))
            holder.itemView.setOnClickListener {
                if (item.imageResId==R.drawable.ic_caller){
                    onSelected.invoke()
                }
            }
        }
    }


    override fun getItemCount(): Int {
        return list.size
    }


    inner class ViewHolder(val binding: ItemServiceBinding) : RecyclerView.ViewHolder(binding.root)
}
