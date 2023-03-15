package com.developercara.bcanotes

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.developercara.bcanotes.databinding.ActivitySignupBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.regex.Pattern

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var googleSignInClient : GoogleSignInClient
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()





        database = FirebaseDatabase.getInstance().reference
        binding.backtologin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.buttonSignup.setOnClickListener {
            val name = binding.editTextName.text.toString()
            val email = binding.editTextEmail.text.toString()
            val phone = binding.editTextPhone.text.toString()
            val password = binding.editTextPassword.text.toString()

            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else if (!isEmailValid(email)) {
                Toast.makeText(this, "Invalid email address", Toast.LENGTH_SHORT).show()
            } else if (!isPasswordValid(password)) {
                Toast.makeText(this, "Password: Min. 6 chars, 1 uppercase, 1 digit, 1 special character, no whitespace.", Toast.LENGTH_LONG).show()
            } else if (!isValidPhoneNumber(phone)) {
                Toast.makeText(this, "Please enter a valid phone number with 10 digits", Toast.LENGTH_SHORT).show()
            } else {

                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user = User(name, email, phone, password)
                            val uid = auth.currentUser?.uid
                            if (uid != null) {
                                database.child("users").child(uid).setValue(user)
                                    .addOnSuccessListener {

                                        Toast.makeText(this, "Sign up successful", Toast.LENGTH_SHORT).show()
                                        val intent = Intent(this, MainActivity::class.java)
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                                        startActivity(intent)
                                        finish()
                                    }
                                    .addOnFailureListener {

                                        Toast.makeText(this, "Sign up failed: ${it.message}", Toast.LENGTH_SHORT).show()
                                    }
                            }
                        } else {

                            Toast.makeText(this, "Sign up failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
        //show/hide password
        binding.editTextPassword.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= binding.editTextPassword.right - binding.editTextPassword.compoundDrawables[2].bounds.width()) {
                    // show/hide password
                    val selectionStart = binding.editTextPassword.selectionStart
                    val selectionEnd = binding.editTextPassword.selectionEnd
                    if (binding.editTextPassword.transformationMethod == PasswordTransformationMethod.getInstance()) {
                        // show password
                        binding.editTextPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                        binding.editTextPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_24, 0)
                    } else {
                        // hide password
                        binding.editTextPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                        binding.editTextPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_off_24, 0)
                    }
                    binding.editTextPassword.setSelection(selectionStart, selectionEnd)
                    return@setOnTouchListener true
                }
            }
            return@setOnTouchListener false
        }

    }
    private fun isEmailValid(email: String): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    private fun isPasswordValid(password: String): Boolean {

        val pattern = Pattern.compile("^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=])(?!.*\\s)[A-Za-z\\d@#$%^&+=]{6,}$")
        return pattern.matcher(password).matches()
    }

    private fun isValidPhoneNumber(phone: String): Boolean {
        return phone.matches(Regex("^\\d{10}$"))
    }
}