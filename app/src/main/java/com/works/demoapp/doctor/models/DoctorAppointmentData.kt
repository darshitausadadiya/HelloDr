package com.works.demoapp.doctor.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "doctor_appointment_data_table")
data class DoctorAppointmentData(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val doctorEmail : String? = "",
    val patientEmail : String? = "",
    val patientName : String? = "",
    val note : String? = "",
    val date : String? = "",
    val hour : String? = ""
)
