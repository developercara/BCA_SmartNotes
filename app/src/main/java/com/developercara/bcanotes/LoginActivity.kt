package com.developercara.bcanotes

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.util.Patterns
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.preference.PreferenceManager
import com.developercara.bcanotes.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.FirebaseDatabase
import com.google.android.gms.common.SignInButton
import com.google.android.gms.tasks.Task

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    // Initialize Firebase Auth

    private lateinit var progressBar: ProgressBar


    private lateinit var googleSignInClient : GoogleSignInClient


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        progressBar = findViewById(R.id.progressBar)
        progressBar.visibility = View.GONE





        auth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this , gso)

        findViewById<Button>(R.id.gSignInBtn).setOnClickListener {


            signInGoogle()
        }

        // Check if user's credentials are saved in SharedPreferences
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val isLoggedIn = prefs.getBoolean("is_logged_in", false)
        if (isLoggedIn) {
            val email = prefs.getString("email", null)
            val password = prefs.getString("password", null)
            if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
                val user = auth.currentUser
                if (user != null) {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("user", user)
                    intent.putExtra("email", email)
                    startActivity(intent)
                    finish()
                    return
                }
            }
        }
        // Check if user is logged in with Google
        val isLoggedInWithGoogle = prefs.getBoolean("is_logged_in_with_google", false)
        if (isLoggedInWithGoogle) {
            val email = prefs.getString("google_email", null)
            val name = prefs.getString("google_name", null)
            if (!email.isNullOrEmpty() && !name.isNullOrEmpty()) {
                val user = auth.currentUser
                if (user != null) {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("user", user)
                    intent.putExtra("email", email)
                    intent.putExtra("name", name)
                    startActivity(intent)
                    finish()
                    return
                }
            }
        }

        binding.signupText.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
        binding.buttonLogin.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else if (!isEmailValid(email)) {
                Toast.makeText(this, "Invalid email address", Toast.LENGTH_SHORT).show()
            } else {

                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        progressBar.visibility = View.GONE // hide progress bar
                        if (task.isSuccessful) {
                            val user = auth.currentUser
                            if (user != null) {
                                // Save user's credentials to SharedPreferences

                                prefs.edit()
                                    .putBoolean("is_logged_in", true)
                                    .putString("email", email)
                                    .putString("password", password)
                                    .apply()

                                val intent = Intent(this, MainActivity::class.java)
                                intent.putExtra("user", user)
                                startActivity(intent)
                                finish()
                            }
                        } else {
                            Toast.makeText(
                                this,
                                "Login failed: ${task.exception?.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    .addOnFailureListener {
                        progressBar.visibility = View.GONE // hide progress bar
                    }
                progressBar.visibility = View.VISIBLE // show progress bar
            }
        }
        binding.editTextPassword.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= binding.editTextPassword.right - binding.editTextPassword.compoundDrawables[2].bounds.width()) {
                    // show/hide password
                    val selectionStart = binding.editTextPassword.selectionStart
                    val selectionEnd = binding.editTextPassword.selectionEnd
                    if (binding.editTextPassword.transformationMethod == PasswordTransformationMethod.getInstance()) {
                        // show password
                        binding.editTextPassword.transformationMethod =
                            HideReturnsTransformationMethod.getInstance()
                        binding.editTextPassword.setCompoundDrawablesWithIntrinsicBounds(
                            0,
                            0,
                            R.drawable.ic_baseline_visibility_24,
                            0
                        )
                    } else {
                        // hide password
                        binding.editTextPassword.transformationMethod =
                            PasswordTransformationMethod.getInstance()
                        binding.editTextPassword.setCompoundDrawablesWithIntrinsicBounds(
                            0,
                            0,
                            R.drawable.ic_baseline_visibility_off_24,
                            0
                        )
                    }
                    binding.editTextPassword.setSelection(selectionStart, selectionEnd)
                    return@setOnTouchListener true
                }
            }
            return@setOnTouchListener false
        }
        //sign in with google


    }

    private fun isEmailValid(email: String): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }
    private fun signInGoogle(){
        progressBar.visibility = View.VISIBLE // show progress bar
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        if (result.resultCode == Activity.RESULT_OK){

            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleResults(task)
        }
        progressBar.visibility = View.GONE // hide progress bar
    }

    private fun handleResults(task: Task<GoogleSignInAccount>) {
        try {
            progressBar.visibility = View.GONE // hide progress bar
            if (task.isSuccessful){
                val account : GoogleSignInAccount? = task.result
                if (account != null){
                    updateUI(account)
                }
            }else{
                Toast.makeText(this, task.exception.toString() , Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            progressBar.visibility = View.GONE // hide progress bar
            e.printStackTrace()
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                if (user != null) {
                    // Save user's credentials to SharedPreferences
                    val prefs = PreferenceManager.getDefaultSharedPreferences(this)
                    prefs.edit()
                        .putBoolean("is_logged_in_with_google", true)
                        .putString("google_email", user.email)
                        .putString("google_name", user.displayName)
                        .apply()

                    // Save user's credentials to Firebase Realtime Database
                    val database = FirebaseDatabase.getInstance()
                    val myRef = database.getReference("users").child(user.uid)
                    myRef.child("email").setValue(user.email)
                    myRef.child("name").setValue(user.displayName)
                    myRef.child("photoUrl").setValue(user.photoUrl.toString())

                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("user", user)
                    intent.putExtra("name", account.displayName)
                    intent.putExtra("photo", account.photoUrl?.toString())
                    startActivity(intent)
                    finish()
                }
            } else {
                Toast.makeText(this, "Authentication failed.",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }


}

