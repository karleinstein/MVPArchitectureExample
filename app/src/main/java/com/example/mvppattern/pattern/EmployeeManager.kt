package com.example.mvppattern.pattern

import android.app.DownloadManager
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import kotlin.math.log

class EmployeeManager {
    private var jsonArray: JSONArray? = null
    private var mIUpdateToView: IUpdateToView? = null

    companion object {
        const val URL = "http://dummy.restapiexample.com/api/v1/employees"
        const val TAG = "EmployeeManager"
    }

    fun initData() {
        val task = TaskLoadData()
        task.execute(URL)
    }


    inner class TaskLoadData : AsyncTask<String, Void, JSONArray?>() {
        override fun doInBackground(vararg params: String?): JSONArray? {
            val url = java.net.URL(URL)
            val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
            try {
                val inputStream = BufferedInputStream(urlConnection.inputStream)
                val bufferReader = BufferedReader(InputStreamReader(inputStream))
                val line = bufferReader.readLine()
                jsonArray = JSONArray(line)
            } finally {
                urlConnection.disconnect()
            }


            return jsonArray

        }

        override fun onPostExecute(result: JSONArray?) {
            super.onPostExecute(result)
            mIUpdateToView?.sendDataToView(jsonArray)
            Log.d(TAG, jsonArray.toString())

        }

    }

    fun setOnUpdateToView(iUpdateToView: IUpdateToView) {
        mIUpdateToView = iUpdateToView
    }


    interface IUpdateToView {
        fun sendDataToView(jsonArray: JSONArray?)
    }
}