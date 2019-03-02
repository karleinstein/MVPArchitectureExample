package com.example.mvppattern.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.mvppattern.R
import com.example.mvppattern.model.Employee
import kotlinx.android.synthetic.main.item_employee.view.*

class AdapterEmployee(private val listEmployees: ArrayList<Employee>, private val context: Context) :
    RecyclerView.Adapter<AdapterEmployee.ViewHolder>() {
    private val mInflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.item_employee, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listEmployees.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val employee = listEmployees[position]
        holder.txtEmployeeName.text = employee.employee_name
        holder.txtEmployeeSalary.text = employee.employee_salary
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtEmployeeName: TextView = itemView.txtEmployeeName
        val txtEmployeeSalary: TextView = itemView.txtEmployeeSalary
    }
}