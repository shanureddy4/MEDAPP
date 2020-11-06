package com.example.myapplication



import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import java.util.*


class sendinfo: Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_sendinfo ,container, false)
        var dateedit = view.findViewById<EditText>(R.id.editTextDate)
        var timeedit = view.findViewById<EditText>(R.id.editTextTime)
        var mail = arguments?.getSerializable("mail") as String
        var iddoc = arguments?.getSerializable("iddoc") as String
        var cal = view.findViewById<ImageButton>(R.id.cal)
        cal.setOnClickListener{
            val currentDateTime = Calendar.getInstance()
            val startYear = currentDateTime.get(Calendar.YEAR)
            val startMonth = currentDateTime.get(Calendar.MONTH)
            val startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)
            val startHour = currentDateTime.get(Calendar.HOUR_OF_DAY)
            val startMinute = currentDateTime.get(Calendar.MINUTE)

            DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { _, year, month, day ->
                TimePickerDialog(requireContext(), TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                    val pickedDateTime = Calendar.getInstance()
                    pickedDateTime.set(year, month, day, hour, minute)
                }, startHour, startMinute, false).show()
            }, startYear, startMonth, startDay).show()
            dateedit.setText(startYear.toString()+"/"+startMonth.toString()+"/"+startDay)
            timeedit.setText(startHour.toString()+":"+startMinute.toString())
        }


        var link = view.findViewById<EditText>(R.id.editlink)
        var text = view.findViewById<TextView>(R.id.linkfor)
        var cp = view.findViewById<ImageButton>(R.id.cp3)

        var button = view.findViewById<Button>(R.id.button3)

        button.setOnClickListener {
            link.setText("http://bit.ly/"+iddoc)
            var message = "Your consultation requrest has been accepted by the doctor." + "Please make your self available at " +dateedit.text.toString()+" Time :"+timeedit.text.toString()+ " .Use the link " +link.text.toString()+ " to contact the doctor"

            Thread(Runnable {
                var sendmsg = notifypatient(message,mail.toLowerCase())
                sendmsg.send()
            }).start()







            link.visibility = View.VISIBLE
            text.visibility = View.VISIBLE
            cp.visibility = View.VISIBLE

        }
        val myClipboard: ClipboardManager = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        cp.setOnClickListener{
            val clipData = ClipData.newPlainText("link",link.text.toString() )
            myClipboard.setPrimaryClip(clipData)
        }






        return view
    }


}