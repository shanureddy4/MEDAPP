package com.example.myapplication

import android.util.Log
import javax.activation.CommandMap
import javax.activation.MailcapCommandMap

class notifypatient(var body:String,var receipent:String)
{
    fun send()
    {
        val mc: MailcapCommandMap = CommandMap.getDefaultCommandMap() as MailcapCommandMap
        mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html")
        mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml")
        mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain")
        mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed")
        mc.addMailcap("message/rfc822;; x-java-content- handler=com.sun.mail.handlers.message_rfc822")
        try {
            var sender = GMailSender("shanureddy78@gmail.com", "password")
            sender.sendMail("Consultation Confirmation", body, "shanureddy78@gmail.com", "shanureddy007@gmail.com")
        }catch (e:Exception){
            Log.e("SendMail",e.message,e)}

    }

}
fun main()
{
    val mc: MailcapCommandMap = CommandMap.getDefaultCommandMap() as MailcapCommandMap
    mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html")
    mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml")
    mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain")
    mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed")
    mc.addMailcap("message/rfc822;; x-java-content- handler=com.sun.mail.handlers.message_rfc822")
    try {
        var sender = GMailSender("shanureddy78@gmail.com", "s81nur544y")
        sender.sendMail("Consultation Confirmation", "msg", "MEDAPP", "shanureddy78@gmail.com")
    }catch (e:Exception){
        Log.e("SendMail",e.message,e)}

}

