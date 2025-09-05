package com.example.recyclerview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.adapter.Myadapter
import com.example.recyclerview.adapter.modal.User

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val recyclerview = findViewById<RecyclerView>(R.id.recycler_view)
        val layoutManager = LinearLayoutManager(this)
        recyclerview.layoutManager = layoutManager
        val userDatalist = dummyData()
        val adapter = Myadapter(this,userDatalist)
        recyclerview.adapter = adapter
    }
}

private fun dummyData(): MutableList<User> {
    val userList=mutableListOf<User>()
    userList.add(User("John Doe", "123-456-7890", R.drawable.avtar1))
    userList.add(User("Jane Smith", "987-654-3210", R.drawable.avatar2))
    userList.add(User("Bob Johnson", "555-555-5555", R.drawable.avatar3))
    userList.add(User("Alice Brown", "111-222-3333", R.drawable.boy))
    userList.add(User("David Lee", "444-444-4444", R.drawable.chicken))
    userList.add(User("Emily Chen", "777-777-7777", R.drawable.hacker))
    userList.add(User("Michael Kim", "888-888-8888", R.drawable.human))
    userList.add(User("Sarah Lee", "999-999-9999", R.drawable.man))
    userList.add(User("Tom Wilson", "333-333-3333", R.drawable.profile))
    userList.add(User("Linda Davis", "666-666-6666", R.drawable.woman))

    return userList

}
