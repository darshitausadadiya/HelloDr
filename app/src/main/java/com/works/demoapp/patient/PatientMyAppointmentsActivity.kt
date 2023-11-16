package com.works.demoapp.patient

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.works.demoapp.R
import com.works.demoapp.patient.adapter.PatientAppointmentAdapter
import com.works.demoapp.patient.models.PatientAppointmentData
import com.works.demoapp.viewModel.PatientAppointmentViewModel

class PatientMyAppointmentsActivity : AppCompatActivity() {
    lateinit var patientAppointmentsList : ListView
    private lateinit var patientAppointmentViewModel: PatientAppointmentViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_my_appointments)

        patientAppointmentsList = findViewById(R.id.patientAppointmentsList)

       val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)

        var patientEmail :String? = sharedPreference.getString("email","")
        Log.d("Appointment", "patient emai " +patientEmail)
        patientAppointmentViewModel = ViewModelProvider(this).get(PatientAppointmentViewModel::class.java)
        patientAppointmentViewModel.getAppointment(patientEmail).observe(
            this,
            Observer<List<PatientAppointmentData>> { patientAppointmentData ->
                if (patientAppointmentData.isNotEmpty()) {
                    Log.d("Appointment", "patient emai 123" +patientEmail)
                    val adapter = PatientAppointmentAdapter(this,patientAppointmentData)
                    patientAppointmentsList.adapter = adapter


                } else {


                }
            })


        patientAppointmentsList.setOnItemLongClickListener { adapterView, _, i, _ ->
            val selectedAppointment = adapterView.getItemAtPosition(i) as PatientAppointmentData
            AlertDialog.Builder(this).apply {
                setTitle("Cancel Appointment")
                setMessage("Are you sure you want to cancel the appointment?")
                setPositiveButton("Yes") { _, _ ->


//                    patientAppointmentService.deleteAppointment(
//                        patientEmail,
//                        selectedAppointment.doctorEmail!!,
//                        selectedAppointment.id!!
//                    ) { success ->
//                        if (success) {
//                            Toast.makeText(this@PatientMyAppointmentsActivity, "Appointment canceled", Toast.LENGTH_SHORT).show()
//
//                            patientAppointmentService.getAppointmentsForPatient(patientEmail) { updatedAppointments ->
//                                val newAdapter = PatientAppointmentAdapter(this@PatientMyAppointmentsActivity, updatedAppointments)
//                                patientAppointmentsList.adapter = newAdapter
//                            }
//                        } else {
//                            Toast.makeText(this@PatientMyAppointmentsActivity, "\n" +
//                                    "Appointment could not be canceled", Toast.LENGTH_SHORT).show()
//                        }
//                    }
                }
                setNegativeButton("No", null)
            }.create().show()
            true
        }

    }
}

