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

class SignIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        Find the two edit texts, a button and text view by use of their Ids
        val email = findViewById<EditText>(R.id.email)

        val password = findViewById<EditText>(R.id.password)

        val signinButton = findViewById<Button>(R.id.signinBtn)

        val signupTextView = findViewById<TextView>(R.id.signuptxt)

//        On the TextView set onClickListener such that it will navigate you ti the signup page
        signupTextView.setOnClickListener {
            val intent = Intent(applicationContext, SignUp::class.java)
            startActivity(intent)
        }

//        On click of the button signin, we need to interact with our API endpoint as we pass the two data into email and password
        signinButton.setOnClickListener {
//            Specify the API endpoint
            val api = "https://kbenkamotho.alwaysdata.net/api/signin"

//            Create a requestParams that will enable you to hold the data in form of a bundle/package
            val data = RequestParams()

//            Add/Append/Attach the email and the password
            data.put("email", email.text.toString())
            data.put("password", password.text.toString())

//            Import the API helper

            val helper = ApiHelper(applicationContext)

//By use of the function post_login inside of the helper class, post your data

            helper.post_login(api, data)
        }
    }
}