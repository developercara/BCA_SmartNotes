package com.developercara.bcanotes

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developercara.bcanotes.Adapter.OthersAdapter
import com.developercara.bcanotes.Adapter.SemesAdapter

import com.developercara.bcanotes.databinding.ActivityMainBinding
import com.developercara.bcanotes.dataclass.Others
import com.developercara.bcanotes.dataclass.Semes
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private var recyclerView : RecyclerView?=null
    private var SemesAdapter: SemesAdapter?=null
    private var semesList = mutableListOf<Semes>()
    //other
    private var recyclerView2 : RecyclerView?=null
    private var otherList = mutableListOf<Others>()
    private var OthersAdapter: OthersAdapter?=null
//are you sure toast
    private var backPressedTime: Long = 0
    private lateinit var backToast: Toast

  // to get user name

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
// to prevent log out every time when user close app

//  binding.logoutButton.setOnClickListener {
//            auth.signOut()
//            val intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//            finish()
//        }
        // Check if user is logged in
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val isLoggedIn = prefs.getBoolean("is_logged_in", false)
        if (!isLoggedIn) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            // Getting user name to display on homepage
            database = FirebaseDatabase.getInstance().reference
            binding.text1

            val user = FirebaseAuth.getInstance().currentUser
            if (user != null) {
                val userId = user.uid
                val userRef = database.child("users").child(userId)

                userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val name = dataSnapshot.child("name").getValue(String::class.java)
                        binding.text1.text = "Welcome, $name!"
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        // handle error
                    }
                })
            }
        }

//// SEMESTER GRID VIEW
        semesList =ArrayList()
        recyclerView = binding.recyclemain as RecyclerView
        SemesAdapter = SemesAdapter(this@MainActivity,semesList)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(this,2)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = SemesAdapter
        prepareSemesListData()

        /// OTHER LIST
        otherList =ArrayList()
        recyclerView2 = binding.recyclersecond as RecyclerView
        OthersAdapter = OthersAdapter(this@MainActivity,otherList)
        val layoutManager2: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView2!!.layoutManager = layoutManager2
        recyclerView2!!.adapter = OthersAdapter
        prepareSemesListData2()


    }

    private fun prepareSemesListData2() {
        var others =Others("1st semester",R.drawable.a)
        otherList.add(others)
        others =Others("1st semester",R.drawable.b)
        otherList.add(others)
        others =Others("1st semester",R.drawable.c)
        otherList.add(others)
        others =Others("1st semester",R.drawable.d)
        otherList.add(others)
        others =Others("1st semester",R.drawable.a)
        otherList.add(others)


        OthersAdapter!!.notifyDataSetChanged()
    }

    private  fun prepareSemesListData(){
        var semseters =Semes("1st semester",R.drawable.a)
        semesList.add(semseters)
        semseters =Semes("2nd semester",R.drawable.a)
        semesList.add(semseters)
        semseters =Semes("3rd semester",R.drawable.b)
        semesList.add(semseters)
        semseters =Semes("4th semester",R.drawable.c)
        semesList.add(semseters)
        semseters =Semes("5th semester",R.drawable.d)
        semesList.add(semseters)

        SemesAdapter!!.notifyDataSetChanged()

    }
    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel()
            super.onBackPressed()
            return
        } else {
            backToast = Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT)
            backToast.show()
        }
        backPressedTime = System.currentTimeMillis()
}

}