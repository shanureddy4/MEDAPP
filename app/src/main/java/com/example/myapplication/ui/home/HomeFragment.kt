package com.example.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.beardedhen.androidbootstrap.BootstrapButton
import com.example.myapplication.R
import com.example.myapplication.consultation
import kotlinx.android.synthetic.main.content_main.*


class HomeFragment : Fragment() {
    var consult = consultation()



    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view:View = inflater.inflate(R.layout.fragment_home, container, false)
        val btn = view.findViewById<BootstrapButton>(R.id.startconsultation)

        btn.setOnClickListener {
            val bundle = Bundle()
            consult.arguments=bundle


            val fm = activity!!.supportFragmentManager
            var fragmentTransaction=fm.beginTransaction().replace(R.id.nav_host_fragment,consult).addToBackStack("tag1").commit()

        }

//        val textView: TextView = root.findViewById(R.id.text_home)
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return view
    }
}