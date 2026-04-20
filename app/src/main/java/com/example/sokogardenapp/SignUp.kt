package com.example.sokogardenapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.loopj.android.http.RequestParams

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        find all view bt use of their Ids
        val username = findViewById<EditText>(R.id.username)
        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val phone = findViewById<EditText>(R.id.phone)
        val signUpButton = findViewById<Button>(R.id.signupBtn)
        val signinTextView = findViewById<TextView>(R.id.signintxt)

//        below when a person clicks on the TextView, he/she is navigated to the signin page
        signinTextView.setOnClickListener {
            val intent = Intent(applicationContext, SignIn::class.java)
            startActivity(intent)
        }
//        Onclick of the signup button we want to register a person
        signUpButton.setOnClickListener {

//            Specify the API endpoint
            val api = "https://vicmakau.alwaysdata.net/api/signup"

//            Create a request Params ~ it is where we ae going to hold all the data
            val data = RequestParams()

//            Add/Append the username,email,password,and phone on the data
            data.put("username", username.text.toString().trim())
            data.put("email", email.text.toString().trim())
            data.put("password", password.text.toString().trim())
            data.put("phone", phone.text.toString().trim())

//            Import the API helper class
            val helper = ApiHelper(applicationContext)

//            inside of the helper class, access the function post
            helper.post(api, data)

            email.text.clear()
            password.text.clear()
            phone.text.clear()
            username.text.clear()

//            Intent to the main Activity page
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)

        }
    }
}