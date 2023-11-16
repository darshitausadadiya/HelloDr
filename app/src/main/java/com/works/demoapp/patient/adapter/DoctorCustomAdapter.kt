package com.works.demoapp.patient.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.works.demoapp.R
import com.works.demoapp.patient.models.PatientData


class DoctorCustomAdapter (private val context: Activity, private val list:List<PatientData>
) : ArrayAdapter<PatientData>(context,
    R.layout.custom_doctor_list, list)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rootView = context.layoutInflater.inflate(R.layout.custom_doctor_list,null,true)
        val r_name = rootView.findViewById<TextView>(R.id.r_name)
        val r_age = rootView.findViewById<TextView>(R.id.r_age)
        val r_field = rootView.findViewById<TextView>(R.id.r_field)


        val user = list.get(position)
        r_name.text = "${user.first} ${user.last}"
        r_age.text = "Age : " + user.age.toString()
        r_field.text = user.field



        return rootView
    }

}