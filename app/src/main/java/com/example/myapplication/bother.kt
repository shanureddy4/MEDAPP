package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.IgnoreExtraProperties
import kotlin.math.acos


class bother :  Fragment() {
    val doctors = doctors()
    val problems = problems()



    //DB
    //private lateinit var rootnode: FirebaseDatabase
    //private lateinit var reference: DatabaseReference
    private lateinit var database: DatabaseReference
    @IgnoreExtraProperties
    data class newpatient(
        var Name: String?,
        var Age: String?,
        var Sex: String?,
        var Phone: String?,
        var Email: String?,
        var Address: String?,
        var Pincode: String?,
        var Privacy:String?,
        var Problem:String?,
        var DoctorId:String?,
        var Describe:String?,
        var Key:String?

    )

    private fun writeNewUser(ID:String,Name: String?, Age: String?, Sex: String?,Phone:String?,Email:String?,Address: String?,Pincode: String?,Privacy: String?,Problem: String?,DoctorId: String?,Describe: String?,Key:String?) {
        val Patient= newpatient(Name,Age,Sex,Phone, Email, Address, Pincode, Privacy,Problem,DoctorId,Describe,Key)


        database = FirebaseDatabase.getInstance().getReference()
        database.child("patients").child(ID).setValue(Patient)


//        rootnode = FirebaseDatabase.getInstance()
//        reference = rootnode.getReference("patients")
//        reference.setValue(Patient)


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_bother ,container, false)
        //get petient details with key
        val det:ArrayList<String> = arguments?.getSerializable("details") as ArrayList<String>
        val key:String = arguments?.getSerializable("key") as String
        // got patient details



        //find consultant
        val consultant = view.findViewById<ImageButton>(R.id.imgbtn1)
        consultant.setOnClickListener(object :View.OnClickListener
        {
            override fun onClick(p0: View?) {
                val bundle = Bundle()
                doctors.arguments=bundle
                val fm = activity!!.supportFragmentManager
                fm.beginTransaction().replace(R.id.nav_host_fragment,doctors).addToBackStack("tag4").commit()
            }
        })



        //find pbls
        val pbls = view.findViewById<ImageButton>(R.id.imgbtn)
        pbls.setOnClickListener(object :View.OnClickListener
        {
            override fun onClick(p0: View?) {
                val bundle = Bundle()
                doctors.arguments=bundle
                val fm = activity!!.supportFragmentManager
                fm.beginTransaction().replace(R.id.nav_host_fragment,problems).addToBackStack("tag4").commit()
            }
        })




        //pbl description
        var decription_reference = view.findViewById<EditText>(R.id.describe)




        //submit
        val submit = view.findViewById<Button>(R.id.button)
        submit.setOnClickListener(object :View.OnClickListener
        {
            override fun onClick(p0: View?) {
                    //write to DB

                     val bundle = arguments
                    var problem = (activity as MainActivity).problem
                    var enProblem = Vigenere(problem as String,key).encodeMessage()
                    var endDscription =  Vigenere(decription_reference.text.toString(),key).encodeMessage()
                    var doctorID = (activity as MainActivity).docID
                    if(problem.length<=1 || doctorID.length<=1)
                    {
                        Toast.makeText(context,"Please select your health issue and doctor",Toast.LENGTH_SHORT).show()
                    }
                else if (endDscription.length<=1)
                    {
                        Toast.makeText(context,"Please describe your problem",Toast.LENGTH_SHORT).show()
                    }
                else
                    {
                        writeNewUser(det[0],det[1],det[2],det[3],det[4],det[5],det[6],det[7],det[8],enProblem,doctorID,endDscription,key)
                        Toast.makeText(context,"you will receive mail regarding confirmation",Toast.LENGTH_LONG).show()
                    }
            }
        })


        return view
    }


}