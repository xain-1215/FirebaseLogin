package com.example.firebaselogin

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class account_register : AppCompatActivity() {
    private lateinit var newEmail: EditText//user's new email
    private lateinit var newPassword: EditText//user's account password
    private lateinit var register: Button//register button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_information)

        newEmail=findViewById(R.id.newEmail)
        register=findViewById(R.id.create)
        auth = Firebase.auth//initialize Firebase auth
        register.setOnClickListener(){
            newPassword=findViewById(R.id.newPassword)
            createAccount(newEmail.getText().toString(),newPassword.getText().toString())
        }
    }
    //register function
    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(MainActivity.TAG, "createUserWithEmail:success")
                    finish()
                } else {
                    Log.w(MainActivity.TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }
    companion object {
        private const val TAG = "EmailPassword"
    }
}