package com.app.spiral.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.app.spiral.R
import com.app.spiral.model.Story

class StoryAdapter(private val storyList: List<Story> = data) : RecyclerView.Adapter<StoryAdapter.StoryViewHolder>() {

    companion object{
        val data = listOf<Story>(
            Story("My Story", R.drawable.ic_profile),
            Story("Salena", R.drawable.ic_profile_1),
            Story("Clare", R.drawable.ic_profile_2),
            Story("Fabian", R.drawable.ic_profile_3),
            Story("Ali", R.drawable.ic_profile_1),
        )
    }
    inner class StoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewStory: ImageView = itemView.findViewById(R.id.imageViewStory)
        val ivAdd: ImageView = itemView.findViewById(R.id.ivAdd)
        val textViewName: TextView = itemView.findViewById(R.id.textViewName)
        val roundedLayout: ConstraintLayout = itemView.findViewById(R.id.roundedLayout)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_stories, parent, false)
        return StoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        val story = storyList[position]

        // Display "My Story" for the first item
        holder.textViewName.text = story.name
        if (position == 0) {
            holder.roundedLayout.background = null
            holder.imageViewStory.setImageResource(R.drawable.ic_profile)
            holder.ivAdd.visibility = View.VISIBLE
        } else {
            holder.ivAdd.visibility = View.GONE
            holder.roundedLayout.setBackgroundResource(R.drawable.rounded_gradient_border)
            holder.imageViewStory.setImageResource(story.imageResId)
        }
    }

    override fun getItemCount(): Int = storyList.size
}
