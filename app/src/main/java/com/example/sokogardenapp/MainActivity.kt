package com.example.sokogardenapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    lateinit var signupBtn: Button
    lateinit var signinBtn: Button
    lateinit var welcomeText: TextView
    lateinit var logoutBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        find the buttons by Id
        val SignUpButton = findViewById<Button>(R.id.signupBtn)
        val SignInButton = findViewById<Button>(R.id.signinBtn)

//        Create the intents to the two activities
        SignUpButton.setOnClickListener {
            val intent = Intent(applicationContext, SignUp::class.java)
            startActivity(intent)
        }
        SignInButton.setOnClickListener {
            val intent = Intent(applicationContext, SignIn::class.java)
            startActivity(intent)
        }

        signupBtn = findViewById(R.id.signupBtn)
        signinBtn = findViewById(R.id.signinBtn)
        welcomeText = findViewById(R.id.welcomeText)
        logoutBtn = findViewById(R.id.logoutBtn)

        val prefs = getSharedPreferences("user_session", MODE_PRIVATE)
        val username = prefs.getString("username", null)

        if (username != null) {
// ✅ User is logged in
            welcomeText.text = "Welcome $username"
            welcomeText.visibility = View.VISIBLE
            logoutBtn.visibility = View.VISIBLE

            signupBtn.visibility = View.GONE
            signinBtn.visibility = View.GONE
        } else {
// ❌ User not logged in
            signupBtn.visibility = View.VISIBLE
            signinBtn.visibility = View.VISIBLE

            welcomeText.visibility = View.GONE
            logoutBtn.visibility = View.GONE
        }

// 🔓 Logout logic
        logoutBtn.setOnClickListener {
            val editor = prefs.edit()
            editor.clear()
            editor.apply()

            Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()

// Refresh activity
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
//        Find the recyclerView and the progress bar by the use of their ids
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val progressBar = findViewById<ProgressBar>(R.id.progressbar)

//        specify the API URL endpoint for fetching the products(always data)
        val url = "https://vicmakau.alwaysdata.net/api/get_product_details"

//        Import the helper class
        val helper = ApiHelper(applicationContext)

//        Inside of the helper class, access the function loadproducts
        helper.loadProducts(url, recyclerView,progressBar)

        // find the About button by use of its ID and have the intent
        val aboutButton = findViewById<Button>(R.id.aboutBtn)

        //below is the intent to the About activity
        aboutButton.setOnClickListener {
            val intent = Intent(applicationContext, About::class.java)
            startActivity(intent)

        }

    }

}