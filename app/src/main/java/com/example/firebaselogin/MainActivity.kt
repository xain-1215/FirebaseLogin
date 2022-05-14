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
    private lateinit var register: Button//register button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        email=findViewById(R.id.email)
        pwd=findViewById(R.id.password)
        loginBtn=findViewById(R.id.login)
        register=findViewById(R.id.register)
        auth = Firebase.auth//initialize Firebase auth
        loginBtn.setOnClickListener{
            signIn(email.getText().toString(),pwd.getText().toString())
        }
        register.setOnClickListener{
            val intent = Intent()
            intent.setClass(this@MainActivity, account_register::class.java)
            startActivity(intent)
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

    //login function
    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //Login success
                    Log.d(TAG, "signInWithEmail:success")
                    updateUI()
                    reload()
                } else {
                    //Login failed and show message
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    reload()
                }
            }
    }

    private fun updateUI() {
        val intent = Intent()
        intent.setClass(this@MainActivity, user_information::class.java)
        val bundle = Bundle()
        bundle.putString("email",email.getText().toString())
        intent.putExtras(bundle)
        startActivity(intent)
    }

    //clean the edittext
    private fun reload() {
        email.setText("  jane@example.com")
        pwd.setText("  *****")
    }

    companion object {
        const val TAG = "EmailPassword"
    }
}