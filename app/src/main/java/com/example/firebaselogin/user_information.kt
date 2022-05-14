package com.example.firebaselogin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class user_information : AppCompatActivity() {
    private lateinit var username: TextView//user's information
    private lateinit var chPwdBtn: Button//change password button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_information)

        val bundle = this.intent.extras
        username=findViewById(R.id.username)
        chPwdBtn=findViewById(R.id.changePwd)
        username.setText("Hello,"+bundle!!.getString("email"))
        chPwdBtn.setOnClickListener(){
            val intent = Intent()
            intent.setClass(this@user_information, password_change::class.java)
            startActivity(intent)
        }
    }
}