package com.works.demoapp.patient.models


import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
data class PatientData(
    @PrimaryKey(autoGenerate = true)
    var UID: Int = 0,
    var first: String? = "",
    var last: String? = "",
    var age: String? = "",
    var email: String? = "" ,
    var password: String? = "",
    var client: String? = "",
    var field: String? = "",
)
