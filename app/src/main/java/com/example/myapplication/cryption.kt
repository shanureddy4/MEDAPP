package com.example.myapplication

import java.text.Normalizer
fun getRandomString(length: Int) : String {
    val allowedChars = ('A'..'Z') + ('a'..'z')
    return (1..length)
        .map { allowedChars.random() }
        .joinToString("")
}

class Vigenere(var txt: String, var key: String) {

    val VIGENERE_ARRAY = arrayOf('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.', ',', '?', '!', '\'', '_', '-', '&', '@', '#', '$', '%', '*', '(', ')', '{', '}', '[', ']', '|', '~', '\\', ';', ':', '^', '+', '=', ' ', '`')


    fun encodeMessage(): String {
        var txtEncoded = ""
        val keyLength: Int = key.length
        val txtFlattened = flattenToAscii(txt)
        for (i in txtFlattened.indices) {
            val x =
                (VIGENERE_ARRAY.indexOf(txtFlattened[i].toUpperCase()) + VIGENERE_ARRAY.indexOf(key[i.rem(keyLength)].toUpperCase())).rem(
                    VIGENERE_ARRAY.size)
            txtEncoded += VIGENERE_ARRAY[x]
        }
        return txtEncoded
    }

    fun decodeMessage(): String {
        var txtDecoded = ""
        val keyLength: Int = key.length
        for (i in txt.indices) {
            val x =
                (VIGENERE_ARRAY.indexOf(txt[i].toUpperCase()) - VIGENERE_ARRAY.indexOf(key[i.rem(keyLength)].toUpperCase()) + VIGENERE_ARRAY.size).rem(
                    VIGENERE_ARRAY.size
                )
            txtDecoded += VIGENERE_ARRAY[x]
        }
        return txtDecoded
    }

    internal fun flattenToAscii(txt: String): String {
        var string = txt
        val out = CharArray(string.length)
        string = Normalizer.normalize(string, Normalizer.Form.NFD)
        var j = 0
        var i = 0
        val n = string.length
        while (i < n) {
            val c = string[i]
            if (c <= '\u007F') out[j++] = c
            ++i
        }
        return String(out)
    }
}
