package com.example.firebaselogin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    private lateinit var email: EditText//user's email
    private lateinit var pwd: EditText//user's password
    private lateinit var loginBtn: Button//login button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        email=findViewById(R.id.email)
        pwd=findViewById(R.id.password)
        loginBtn=findViewById(R.id.login)
        auth = Firebase.auth//initialize Firebase auth
        loginBtn.setOnClickListener{
            signIn(email.getText().toString(),pwd.getText().toString())
        }
    }
    public override fun onStart() {
        super.onStart()
        //Check if user is signed
        val currentUser = auth.currentUser
        if(currentUser != null){
            reload();
        }
    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //Login success
                    Log.d(TAG, "signInWithEmail:success")
                    updateUI()
                } else {
                    //Login failed and show message
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun sendEmailVerification() {
        // [START send_email_verification]
        val user = auth.currentUser!!
        user.sendEmailVerification()
            .addOnCompleteListener(this) { task ->
                // Email Verification sent
            }
        // [END send_email_verification]
    }

    private fun updateUI() {
        val intent = Intent()
        intent.setClass(this@MainActivity, user_information::class.java)
        val bundle = Bundle()
        bundle.putString("email",email.getText().toString())
        intent.putExtras(bundle)
        startActivity(intent)
    }

    private fun reload() {

    }


    companion object {
        private const val TAG = "EmailPassword"
    }
}