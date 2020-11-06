package com.example.myapplication



import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
import java.io.Serializable


class patient_view: Fragment() {
var Sendinfo = sendinfo()
    var mail = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_patientview ,container, false)
        var name = view.findViewById<TextView>(R.id.NMM)
        var age = view.findViewById<TextView>(R.id.AGG)
        var gender = view.findViewById<TextView>(R.id.GNN)
        var mobile = view.findViewById<TextView>(R.id.MBB)
        var email = view.findViewById<TextView>(R.id.EMM)
        var address = view.findViewById<TextView>(R.id.ADD)
        var problem = view.findViewById<TextView>(R.id.PBB)
        var description = view.findViewById<TextView>(R.id.DPP)



        var decrypt = view.findViewById<Button>(R.id.DEC)

        var getaddress = view.findViewById<EditText>(R.id.ADD)
        getaddress.isEnabled = false

        var getDes = view.findViewById<EditText>(R.id.DPP)
        getDes.isEnabled = false

        var collection = arguments?.getSerializable("collection") as MutableMap<String,String>


        fun decodedmessage(txt: String, key: String): String
        {
            var vig = Vigenere(txt,key)
            return vig.decodeMessage()

        }
        decrypt.setOnClickListener {
            var getkey = view.findViewById<EditText>(R.id.patientKey).text.toString()
            if (getkey.equals(collection.getValue("key")))
            {
                if (collection.getValue("privacy").toBoolean())
                {
                    age.text = decodedmessage(collection.getValue("age"),getkey)
                    gender.text = decodedmessage(collection.getValue("sex"),getkey)
                    email.text = decodedmessage(collection.getValue("email"),getkey)
                    problem.text = decodedmessage(collection.getValue("problem"),getkey)
                    description.text = decodedmessage(collection.getValue("describe"),getkey)

                    age.visibility = View.VISIBLE
                    gender.visibility = View.VISIBLE
                    email.visibility = View.VISIBLE
                    problem.visibility = View.VISIBLE
                    description.visibility = View.VISIBLE


                }
                else
                {
                    name.text = decodedmessage(collection.getValue("name"),getkey)
                    age.text = decodedmessage(collection.getValue("age"),getkey)
                    gender.text = decodedmessage(collection.getValue("sex"),getkey)
                    mobile.text = decodedmessage(collection.getValue("phone"),getkey)
                    email.text = decodedmessage(collection.getValue("email"),getkey)
                    address.text = decodedmessage(collection.getValue("address"),getkey)
                    problem.text = decodedmessage(collection.getValue("problem"),getkey)
                    description.text = decodedmessage(collection.getValue("describe"),getkey)

                    //to send mail
                    mail = decodedmessage(collection.getValue("email"),getkey)

                    name.visibility = View.VISIBLE
                    age.visibility = View.VISIBLE
                    gender.visibility = View.VISIBLE
                    mobile.visibility = View.VISIBLE
                    email.visibility = View.VISIBLE
                    address.visibility = View.VISIBLE
                    problem.visibility = View.VISIBLE
                    description.visibility = View.VISIBLE

                }
            }
            else
            {
                Toast.makeText(requireContext(),"Please Enter Valid Key",Toast.LENGTH_SHORT).show()
            }

        }
        val next = view.findViewById<Button>(R.id.N2)
        next.setOnClickListener {
            var bundle = Bundle()
            bundle.putSerializable("mail",mail)
            bundle.putSerializable("iddoc",collection.getValue("doctorId"))
            Sendinfo.arguments=bundle
            val fm = requireActivity().supportFragmentManager
            fm.beginTransaction().replace(R.id.nav_host_fragment,Sendinfo).addToBackStack("tag2").commit()
        }







        return view
    }


}