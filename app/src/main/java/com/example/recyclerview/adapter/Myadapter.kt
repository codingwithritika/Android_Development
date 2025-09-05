package com.example.recyclerview.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.widget.ImageView
import com.example.recyclerview.R
import android.widget.TextView
import com.example.recyclerview.adapter.modal.User
import android.content.Intent
import com.example.recyclerview.ContactActivity


class Myadapter(private val context:Context, private val userData:List<User>): RecyclerView.Adapter<Myadapter.ListViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Myadapter.ListViewHolder {
        return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_item,parent,false))
    }

    override fun onBindViewHolder(holder: Myadapter.ListViewHolder, position: Int) {
        holder.bind(userData[position])
        holder.itemView.setOnClickListener{
            val intent=Intent(context,ContactActivity::class.java)
            intent.putExtra("name",userData[position].userName)
            intent.putExtra("contact",userData[position].userContact)
            intent.putExtra("image",userData[position].profileImage)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int=userData.size

        class ListViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
            fun bind(user: User){
                val profileImage=itemView.findViewById<ImageView>(R.id.contact_image)
                val name=itemView.findViewById<TextView>(R.id.contact_name)
                val contact=itemView.findViewById<TextView>(R.id.contact_phone)

                profileImage.setImageResource(user.profileImage)
                name.text=user.userName
                contact.text=user.userContact
            }
        }


}
