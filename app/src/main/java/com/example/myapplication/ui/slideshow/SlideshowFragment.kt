package com.example.myapplication.ui.slideshow

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.text.set
import androidx.fragment.app.Fragment
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.docnotification
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.ktx.Firebase
import java.nio.file.Files.size
private lateinit var auth:FirebaseAuth

class SlideshowFragment : Fragment() {
    var notifications = docnotification()

    public override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.doctorlogin,container,false)
        //getting form fields
        val email = view.findViewById<EditText>(R.id.editTextTextEmailAddress)
        val password =view.findViewById<EditText>(R.id.editTextTextPassword)
        val loginbtn = view.findViewById<Button>(R.id.login)
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        //TODO update ui
       var inflaterview = layoutInflater.inflate(R.layout.activity_main,null)
        var navView = inflaterview.findViewById<NavigationView>(R.id.nav_view)
        var item = navView.menu.findItem(R.id.nav_slideshow)

        loginbtn.setOnClickListener(View.OnClickListener { view ->
            auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener{ task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        Log.d(TAG, user.toString())
                        item.setTitle("Logout")
                        val bundle = Bundle()
                        bundle.putSerializable("doctor",user?.uid.toString())
                        notifications.arguments=bundle
                        val fm = requireActivity().supportFragmentManager
                        fm.beginTransaction().replace(R.id.nav_host_fragment,notifications).addToBackStack("tag6").commit()



                       // updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(context, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        //updateUI(null)
                        // ...
                    }


                }
        })












        return view
    }


}