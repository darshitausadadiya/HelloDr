package com.works.demoapp.doctor

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.works.demoapp.MainActivity
import com.works.demoapp.R

import com.works.demoapp.doctor.adapter.DoctorAppointmentAdapter
import com.works.demoapp.doctor.models.DoctorAppointmentData

import com.works.demoapp.viewModel.DoctoreAppointmentDataViewModel



class DoctorHomepageActivity : AppCompatActivity() {
    lateinit var appointmentsList: ListView
    private lateinit var doctoreAppointmentDataViewModel: DoctoreAppointmentDataViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_homepage)

        appointmentsList = findViewById(R.id.appointmentsList)


        val sharedPreference = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        var doctorEmail = sharedPreference.getString("email", "")


        doctoreAppointmentDataViewModel =
            ViewModelProvider(this).get(DoctoreAppointmentDataViewModel::class.java)
        doctoreAppointmentDataViewModel.getAppointment(doctorEmail).observe(
            this,
            Observer<List<DoctorAppointmentData>> { patientAppointmentData ->
                if (patientAppointmentData.isNotEmpty()) {
                    Log.d("Appointment", "patient emai 123" + doctorEmail)
                    val adapter = DoctorAppointmentAdapter(this, patientAppointmentData)
                    appointmentsList.adapter = adapter


                } else {


                }
            })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.doctor_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.doctor_profile -> {

            }


            R.id.doctor_logout -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }
}

