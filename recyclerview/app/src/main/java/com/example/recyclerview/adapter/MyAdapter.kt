package com.example.recyclerview.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import com.example.recyclerview.model.User
import android.content.Intent
import com.example.recyclerview.ContactActivity


import com.example.recyclerview.databinding.RecyclerItemBinding

class MyAdapter(
    private val context: Context,
    private val userData: MutableList<User>,
    private val onSelectionModeChanged: (Boolean) -> Unit
) : RecyclerView.Adapter<MyAdapter.ListViewHolder>() {

    var isSelectionModeEnabled = false
        set(value) {
            field = value
            onSelectionModeChanged(value)
            notifyDataSetChanged()
        }

    fun addUser(user: User) {
        userData.add(user)
        notifyItemInserted(userData.size - 1)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyAdapter.ListViewHolder {
        val binding = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyAdapter.ListViewHolder, position: Int) {
        val user = userData[position]
        holder.bind(user, isSelectionModeEnabled)
        
        // Add entry animation 
        val animation = android.view.animation.AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left)
        holder.itemView.startAnimation(animation)
        
        holder.itemView.setOnLongClickListener {
            if (!isSelectionModeEnabled) {
                isSelectionModeEnabled = true
                user.isSelected = true
                notifyItemChanged(position)
            }
            true
        }
        
        holder.itemView.setOnClickListener{
            if (isSelectionModeEnabled) {
                user.isSelected = !user.isSelected
                notifyItemChanged(position)
            } else {
                val intent=Intent(context,ContactActivity::class.java)
                intent.putExtra("name", user.userName)
                intent.putExtra("contact", user.userContact)
                intent.putExtra("image", user.profileImage)
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int=userData.size

    class ListViewHolder(private val binding: RecyclerItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(user: User, isSelectionModeEnabled: Boolean){
            binding.contactImage.setImageResource(user.profileImage)
            binding.contactName.text=user.userName
            binding.contactPhone.text=user.userContact
            
            // Handle Selection UI
            binding.selectionCheckbox.visibility = if (isSelectionModeEnabled) android.view.View.VISIBLE else android.view.View.GONE
            binding.selectionCheckbox.isChecked = user.isSelected
            
            // Prevent Checkbox itself from stealing click from the item layout
            binding.selectionCheckbox.isClickable = false
        }
    }
}
