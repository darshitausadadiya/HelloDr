package com.works.demoapp.doctor.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "appointment_data_table")
data class AppointmentData(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val doctorEmail : String? = "",
    val patientEmail : String? = "",
    val patientName : String? = "",
    val doctorName : String? = "",
    val doctorField : String? = "",
    val note : String? = "",
    val date : String? = "",
    val hour : String? = ""
)
