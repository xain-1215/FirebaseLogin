package com.example.firebaselogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private lateinit var email: EditText
    private lateinit var pwd: EditText
    private lateinit var loginBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        email=findViewById(R.id.email)
        pwd=findViewById(R.id.password)
        loginBtn=findViewById(R.id.login)
        loginBtn.setOnClickListener{
            email.getText().toString()
            pwd.getText().toString()
        }
        try{

        }catch (e : Exception){
            e.printStackTrace()
        }
    }
}