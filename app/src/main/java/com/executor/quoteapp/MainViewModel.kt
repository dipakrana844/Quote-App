package com.executor.quoteapp

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import java.util.*

class MainViewModel(@SuppressLint("StaticFieldLeak")  val context: Context) : ViewModel() {
    private var quoteList: Array<QuoteItem> = emptyArray()
    private var index = 0

    init {
        quoteList = loadQuoteFromAssets()
    }

    private fun loadQuoteFromAssets(): Array<QuoteItem> {
        val inputStream = context.assets.open("quotes.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json, Array<QuoteItem>::class.java)

    }

    fun getQuote() = quoteList[index]
    fun nextQuote() = quoteList[++index]
    fun prevQuote() = quoteList[--index]

}