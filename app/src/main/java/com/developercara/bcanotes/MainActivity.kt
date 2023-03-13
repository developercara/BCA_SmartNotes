package com.developercara.bcanotes

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.billingclient.api.BillingClient
import com.bumptech.glide.Glide
import com.developercara.bcanotes.Adapter.OthersAdapter
import com.developercara.bcanotes.Adapter.SemesAdapter
import com.developercara.bcanotes.Adapter.SliderAdapter

import com.developercara.bcanotes.databinding.ActivityMainBinding
import com.developercara.bcanotes.dataclass.Others
import com.developercara.bcanotes.dataclass.Semes
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.smarteist.autoimageslider.SliderAnimations
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private var recyclerView : RecyclerView?=null
    private var SemesAdapter: SemesAdapter?=null
    private var semesList = mutableListOf<Semes>()
    //other
    private var recyclerView2 : RecyclerView?=null
    private var otherList = mutableListOf<Others>()
    private var OthersAdapter: OthersAdapter?=null
    //in app purchase

    private var recyclerView3 : RecyclerView?=null
    private var purchaseList = mutableListOf<Semes>()





    //are you sure toast
    private var backPressedTime: Long = 0
    private lateinit var backToast: Toast

  // to get user name
   private lateinit var billingClient: BillingClient
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        val navigationView = binding.navView
        navigationView.setNavigationItemSelectedListener(this)
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()



        // navigation drawer

        // image slider
        val images = listOf(R.drawable.a, R.drawable.b, R.drawable.c)
        val sliderAdapter = SliderAdapter(images, this)


        binding.viewPager.setSliderAdapter(sliderAdapter)
        binding.viewPager.indicatorSelectedColor = Color.WHITE
        binding.viewPager.indicatorUnselectedColor = Color.GRAY
        binding.viewPager.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
        binding.viewPager.startAutoCycle()



        //image slider

        // Check if user is logged in
//        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
//        val isLoggedIn = prefs.getBoolean("is_logged_in", false)
//        if (!isLoggedIn) {
//            val intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//            finish()
//        } else {
            // Getting user name to display on homepage
            database = FirebaseDatabase.getInstance().reference
            binding.text1

            val user = FirebaseAuth.getInstance().currentUser

            if (user != null) {
                val userId = user.uid
                val userRef = database.child("users").child(userId)
                val name2 = user.displayName
                val photoUrl2 = user.photoUrl
                if (name2 != null) {
                    binding.text1.text = " $name2"
                }
                if (photoUrl2 != null) {
                    Glide.with(this)
                        .load(photoUrl2)
                        .into(binding.imageViewProfile)
                }


                userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val name = dataSnapshot.child("name").getValue(String::class.java)
                       binding.text1.text = " $name"
                        //
//                        // Get User object and use the values to update UI
//                        val userData = dataSnapshot.getValue(Userrs::class.java)
//                        val namee = userData!!.name
//                        val photoUrl = userData.photoUrl
//
//                        // Load user image
//                        val imageViewProfile = findViewById<ImageView>(R.id.imageViewProfile)
//                        Glide.with(this@MainActivity).load(photoUrl).into(imageViewProfile)
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        // handle error
                    }
                })
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

        //IN APP PUCRCHASE



    }

    private fun prepareSemesListData2() {
        var others =Others("Quiz Game",R.drawable.quiz)
        otherList.add(others)
        others =Others("Practical Notes",R.drawable.practical)
        otherList.add(others)
        others =Others("Online compiler",R.drawable.c)
        otherList.add(others)
        others =Others("1st semester",R.drawable.d)
        otherList.add(others)
        others =Others("1st semester",R.drawable.a)
        otherList.add(others)


        OthersAdapter!!.notifyDataSetChanged()
    }

    private  fun prepareSemesListData(){
        var semseters =Semes("1st Semester",R.drawable.sems1)
        semesList.add(semseters)
        semseters =Semes("2nd Semester",R.drawable.sems2)
        semesList.add(semseters)
        semseters =Semes("3rd Semester",R.drawable.sems3)
        semesList.add(semseters)
        semseters =Semes("4th Semester",R.drawable.sems4)
        semesList.add(semseters)
        semseters =Semes("5th Semester",R.drawable.sems5)
        semesList.add(semseters)
        semseters =Semes("Internship",R.drawable.sems6)
        semesList.add(semseters)

        SemesAdapter!!.notifyDataSetChanged()

    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_home -> {
                // Launch GalleryActivity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

            R.id.nav_logout -> {
                // Sign out the user from FirebaseAuth
                FirebaseAuth.getInstance().signOut()

                // Clear the shared preferences
                val prefs = PreferenceManager.getDefaultSharedPreferences(this)
                prefs.edit().putBoolean("is_logged_in", false).apply()

                // Launch LoginActivity and finish this activity
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }



}