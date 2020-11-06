package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.text.set
import androidx.fragment.app.Fragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.IgnoreExtraProperties
import java.nio.file.Files.size


class consultation : Fragment() {
    var Bother = bother()





    public override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.fragment_startconsultation,container,false)
        //getting form fields
        val name = view.findViewById<EditText>(R.id.Name)
        val age=view.findViewById<EditText>(R.id.Age)
        val rg = view.findViewById<RadioGroup>(R.id.radiogrp)
        val phone = view.findViewById<EditText>(R.id.phone)
        val email = view.findViewById<EditText>(R.id.email)
        val address = view.findViewById<EditText>(R.id.address)
        val pincode = view.findViewById<EditText>(R.id.pincode)
        var gender = ""



        //radio
        rg.clearCheck()
        rg.setOnCheckedChangeListener { radioGroup, i ->
            gender = view.findViewById<RadioButton>(i).text.toString()


        }

        //checkbox
        var cb = view.findViewById<CheckBox>(R.id.checkBox)


        val next = view.findViewById<Button>(R.id.nxt)






        next.setOnClickListener(object :View.OnClickListener
        {
            override fun onClick(p0: View?) {
              var details:ArrayList<String> = arrayListOf()
                details.clear()
                val key = getRandomString(7)
                val pid = getRandomString(4)
                val enName = Vigenere(name.text.toString(),key).encodeMessage()
                val enAge  = Vigenere(age.text.toString(),key).encodeMessage()
                val enGender = Vigenere(gender,key).encodeMessage()
                val enMobile = Vigenere(phone.text.toString(),key).encodeMessage()
                val enEmail = Vigenere(email.text.toString(),key).encodeMessage()
                val enAddress = Vigenere(address.text.toString(),key).encodeMessage()
                val enPincode = Vigenere(pincode.text.toString(),key).encodeMessage()
                val CheckBox = cb.isChecked

                details = arrayListOf(pid,enName,enAge,enGender,enMobile,enEmail,enAddress,enPincode,CheckBox.toString())
                if(pid.length<1 || enName.length<1 || enAge.length<1 || enGender.length<1 || enMobile.length<1 || enEmail.length<1 || enAddress.length<1 ||enPincode.length<1)
                {
                    Toast.makeText(context,"Please enter all the fields",Toast.LENGTH_SHORT).show()
                }
                else
                {
                    val bundle = Bundle()
                    bundle.putSerializable("details",details)
                    bundle.putSerializable("key",key)
                    Bother.arguments=bundle
                    val fm = activity!!.supportFragmentManager
                    fm.beginTransaction().replace(R.id.nav_host_fragment,Bother).addToBackStack("tag2").commit()
                }


            }
        })


            








        return view
    }


}