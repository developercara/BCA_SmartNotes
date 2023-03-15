package com.developercara.bcanotes.practicalnotes

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.developercara.bcanotes.R
import com.developercara.bcanotes.databinding.ActivityCpracBinding
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class cPrac : AppCompatActivity() {

    // Define your categories list here
    private val categories = mutableListOf<Category>()

    private lateinit var binding: ActivityCpracBinding
    private lateinit var adapter: CategoryAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCpracBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Add sample data to categories list (replace with your own data)
        categories.add(Category("FIND SIMPLE INTEREST", "Code 1"))
        categories.add(Category("Category 2", "Code 2"))
        categories.add(Category("Category 3", "Code 3"))

        adapter = CategoryAdapter(categories)
        binding.categoryRecyclerView.adapter = adapter
    }
}