package com.example.myapplication.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.myapplication.R
import com.example.myapplication.consultation
import com.example.myapplication.patient_view
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.content_main.*
import java.io.Serializable


class GalleryFragment : Fragment() {
    private lateinit var database: DatabaseReference
    var patientView = patient_view()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view:View = inflater.inflate(R.layout.fragment_search, container, false)

        val search = view.findViewById<Button>(R.id.button2)
        database = FirebaseDatabase.getInstance().getReference().child("patients")
        var collection = mutableMapOf<String,Any>()
        var pidcounter = 0

        val postListener = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val pid = view.findViewById<EditText>(R.id.idsearch).text.toString()

                for(i in dataSnapshot.children)
                {

                    if(i.key.equals(pid)) {

                        pidcounter++
                        for (k in i.children) {
                            collection.set(k.key.toString(),k.value.toString())

                        }
                        val bundle = Bundle()
                        bundle.putSerializable("collection",collection as Serializable)
                        patientView.arguments=bundle
                        val fm = requireActivity().supportFragmentManager
                        fm.beginTransaction().replace(R.id.nav_host_fragment,patientView).addToBackStack("tag11").commit()
                    }

                }
                if(pidcounter==0)
                {
                    Toast.makeText(requireContext(),"Invalid Patient ID",Toast.LENGTH_SHORT).show()
                }

            }


            override fun onCancelled(databaseError: DatabaseError) {
            }

        }
        search.setOnClickListener {
            collection.clear()
            database.addValueEventListener(postListener)

        }


        return view
    }
}