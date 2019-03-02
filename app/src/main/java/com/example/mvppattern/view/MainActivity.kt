package com.example.mvppattern.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.example.mvppattern.R
import com.example.mvppattern.model.Employee
import com.example.mvppattern.pattern.EmployeeManager
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity(), View.OnClickListener, EmployeeManager.IUpdateToView {
    private lateinit var adapter: AdapterEmployee
    private var listEmployees: ArrayList<Employee>? = null
    private var mId = ""
    private var mEmployeeName = ""
    private var mEmployeeSalary = ""
    private var mEmployeeAge = ""
    private var mEmployeeImage = ""

    companion object {
        const val TAG = "MainActivity"
    }

    override fun sendDataToView(jsonArray: JSONArray?) {
        listEmployees = ArrayList()
        for (i: Int in 0 until jsonArray!!.length()) {
            val employee = Employee(
                jsonArray.getJSONObject(i).optString("id"),
                jsonArray.getJSONObject(i).optString("employee_name"),
                jsonArray.getJSONObject(i).optString("employee_salary"),
                jsonArray.getJSONObject(i).optString("employee_age"),
                jsonArray.getJSONObject(i).optString("profile_age")
            )
            if (!listEmployees!!.contains(employee))
                listEmployees!!.add(employee)
        }
        adapter = AdapterEmployee(listEmployees!!, this)
        rcvEmployee.layoutManager = LinearLayoutManager(this)
        rcvEmployee.adapter = adapter
    }

    override fun onClick(v: View?) {
        when (v?.id) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()
    }

    private fun initComponents() {
        val manager = EmployeeManager()
        manager.initData()
        manager.setOnUpdateToView(this)

    }

}
