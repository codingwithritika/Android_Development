package com.example.recyclerview

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ContactActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_contact)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val username=findViewById<TextView>(R.id.Names)
        val usercontact=findViewById<TextView>(R.id.Number)
        val userimage=findViewById<ImageView>(R.id.Image)
        val name=intent.getStringExtra("name")
        val contact=intent.getStringExtra("contact")
        val image=intent.getIntExtra("image",R.drawable.avtar1)
        username.text=name
        usercontact.text=contact
        userimage.setImageResource(image)

    }

}