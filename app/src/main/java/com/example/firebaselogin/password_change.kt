package com.example.firebaselogin

import android.content.Intent
import android.os.Bundle
import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class password_change: AppCompatActivity() {
    private lateinit var password: EditText//new password
    private lateinit var password2: EditText//password check
    private lateinit var submit:Button//submit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.changepwd)

        password=findViewById(R.id.password)
        password2=findViewById(R.id.password2)
        submit=findViewById(R.id.submit)
        submit.setOnClickListener(){
            val user = Firebase.auth.currentUser
            val newPassword = password.getText().toString()
            val newPassword2=password2.getText().toString()
            if(newPassword.equals(newPassword2)) {
                //password change function
                user!!.updatePassword(newPassword).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "User password updated.")
                        val intent = Intent()
                        intent.setClass(this@password_change, MainActivity::class.java)
                        startActivity(intent)
                        Firebase.auth.signOut()//sign out function
                        finish()
                    }
                }
            }
        }
    }
}