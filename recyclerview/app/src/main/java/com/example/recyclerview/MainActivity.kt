package com.example.recyclerview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.adapter.MyAdapter
import com.example.recyclerview.model.User
import android.app.Activity
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import com.example.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MyAdapter
    private var userDatalist: MutableList<User> = mutableListOf()

    private val addContactLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val name = data?.getStringExtra("name")
            val phone = data?.getStringExtra("phone")
            
            if (name != null && phone != null) {
                // Add the new user to the list (using a default avatar for newly added users)
                val newUser = User(name, phone, R.drawable.profile) 
                adapter.addUser(newUser)
                
                // Scroll to the bottom to see the new addition
                binding.recyclerView.smoothScrollToPosition(adapter.itemCount - 1)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        
        // Add a clean divider between items
        val dividerItemDecoration = androidx.recyclerview.widget.DividerItemDecoration(
            binding.recyclerView.context,
            layoutManager.orientation
        )
        binding.recyclerView.addItemDecoration(dividerItemDecoration)
        
        userDatalist = dummyData()
        adapter = MyAdapter(this, userDatalist) { isSelectionMode ->
            if (isSelectionMode) {
                binding.deleteContactFab.visibility = android.view.View.VISIBLE
                binding.addContactFab.visibility = android.view.View.GONE
            } else {
                binding.deleteContactFab.visibility = android.view.View.GONE
                binding.addContactFab.visibility = android.view.View.VISIBLE
                
                // Clear any remaining selections when exiting selection mode
                userDatalist.forEach { it.isSelected = false }
                adapter.notifyDataSetChanged()
            }
        }
        binding.recyclerView.adapter = adapter
        
        binding.deleteContactFab.setOnClickListener {
            val Iterator = userDatalist.iterator()
            while (Iterator.hasNext()) {
                val user = Iterator.next()
                if (user.isSelected) {
                    Iterator.remove()
                }
            }
            adapter.isSelectionModeEnabled = false // This will un-toggle mode and notify changes
        }
        
        binding.addContactFab.setOnClickListener {
            val intent = Intent(this, AddContactActivity::class.java)
            addContactLauncher.launch(intent)
        }
    }

    override fun onBackPressed() {
        if (::adapter.isInitialized && adapter.isSelectionModeEnabled) {
            adapter.isSelectionModeEnabled = false
        } else {
            super.onBackPressed()
        }
    }
}

private fun dummyData(): MutableList<User> {
    val userList=mutableListOf<User>()
    userList.add(User("Sarah Lee", "99253-82189", R.drawable.avtar1))
    userList.add(User("Alice Brown", "987-654-3210", R.drawable.avatar2))
    userList.add(User("Bob Johnson", "555-555-5555", R.drawable.avatar3))
    userList.add(User("Jane Smith", "8832-56488", R.drawable.boy))
    userList.add(User("David Lee", "444-444-4444", R.drawable.chicken))
    userList.add(User("Michael Kim", "777-777-7777", R.drawable.hacker))
    userList.add(User("Emily Chen", "888-888-8888", R.drawable.human))
    userList.add(User("John Doe", "999-999-9999", R.drawable.man))
    userList.add(User("Tom Wilson", "333-333-3333", R.drawable.profile))
    userList.add(User("Lina Davis", "666-666-6666", R.drawable.woman))
    return userList

}
