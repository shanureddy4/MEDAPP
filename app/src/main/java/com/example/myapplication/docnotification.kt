package com.example.myapplication

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.children
import androidx.fragment.app.Fragment
//import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.google.firebase.database.*


class docnotification: Fragment() {
    var keyfordoc = keyfordoc()

    private lateinit var database: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_docnotifications ,container, false)
        activity?.setTitle("Notifications")
        val doctorid:String = arguments?.getSerializable("doctor") as String
        var listview = view.findViewById<ListView>(R.id.patientlist)
        database = FirebaseDatabase.getInstance().getReference().child("patients")
        var list = arrayListOf<String>()
        var adapter:ArrayAdapter<String> = ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,list)
        val txt = view.findViewById<TextView>(R.id.textView12)
        var Dkey = arrayListOf<String>()
        var count = 0

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                list.clear()
                for (i in dataSnapshot.children)
                {
                    if(i.child("doctorId").value.toString().equals(doctorid))
                    {
                        list.add(i.key.toString())
                        Dkey.add(i.child("key").value.toString())
                        count++
                    }

                   // list.add(i.key.toString())
                    //list.add(i.getValue().toString()) // to print all values
                }
                if (count==0)
                {
                    txt.visibility = View.VISIBLE
                }
                adapter.notifyDataSetChanged()

            }

            override fun onCancelled(databaseError: DatabaseError) {
            }

        }
        database.addValueEventListener(postListener)

        //list items
        listview.onItemClickListener =
            OnItemClickListener { parent, view, position, id ->
                var PID  = list[position]
                var DKEY = Dkey[position]
                var bundle = Bundle()
                bundle.putSerializable("PID",PID)
                bundle.putSerializable("DKEY",DKEY)
                keyfordoc.arguments=bundle
                val fm = requireActivity().supportFragmentManager
                fm.beginTransaction().replace(R.id.nav_host_fragment,keyfordoc).addToBackStack("tag10").commit()


            }

        listview.adapter = adapter
        for(i in listview.children)
        {
            i.background = resources.getDrawable(R.drawable.background)
        }




        return view
    }


}