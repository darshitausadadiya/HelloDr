package com.works.demoapp.patient.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "patient_appointment_table")
data class PatientAppointmentData(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val patientEmail : String? = "",
    val doctorEmail : String? = "",
    val doctorName : String? = "",
    val doctorField : String? = "",
    val note : String? = "",
    val date : String? = "",
    val hour : String? = ""
)




