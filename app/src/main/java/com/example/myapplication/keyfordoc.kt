package com.example.myapplication




import android.R.attr.label
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment


class keyfordoc: Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_keyandpatient ,container, false)

        val PID = arguments?.getSerializable("PID") as String
        val KEY = arguments?.getSerializable("DKEY") as String
        var editPID = view.findViewById<EditText>(R.id.PID)
        var editKEY = view.findViewById<EditText>(R.id.KEY)
        editPID.setText(PID)
        editKEY.setText(KEY)
        editKEY.isEnabled = false
        editPID.isEnabled = false
        var copyPID = view.findViewById<ImageButton>(R.id.imageButton)
        var copyKEY = view.findViewById<ImageButton>(R.id.imageButton2)
        val myClipboard: ClipboardManager = activity?.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager


        copyPID.setOnClickListener(object :View.OnClickListener
        {
            override fun onClick(p0: View?) {
                val clipData = ClipData.newPlainText("PID",PID )
                myClipboard.setPrimaryClip(clipData)
            }
        })
        copyKEY.setOnClickListener(object :View.OnClickListener
        {
            override fun onClick(p0: View?) {
                val clipData = ClipData.newPlainText("KEY",KEY )
                myClipboard.setPrimaryClip(clipData)
            }
        })






        return view
    }


}