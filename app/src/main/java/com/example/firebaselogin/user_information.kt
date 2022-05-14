package com.example.firebaselogin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class user_information : AppCompatActivity() {
    private lateinit var username: TextView//user's information
    private lateinit var chPwdBtn: Button//change password button
    private lateinit var signOut:Button//Sign Out button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_information)

        val bundle = this.intent.extras
        username=findViewById(R.id.username)
        chPwdBtn=findViewById(R.id.changePwd)
        signOut=findViewById(R.id.signout)
        username.setText("Hello,"+bundle!!.getString("email"))//use bundle to get the value
        chPwdBtn.setOnClickListener(){
            val intent = Intent()
            intent.setClass(this@user_information, password_change::class.java)
            startActivity(intent)
        }
        signOut.setOnClickListener(){
            Firebase.auth.signOut()//sign out function
            finish()
        }
    }
}