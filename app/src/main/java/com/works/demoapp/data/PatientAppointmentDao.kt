package com.works.demoapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.works.demoapp.patient.models.PatientAppointmentData

// UserDao contains the methods used for accessing the database, including queries.
@Dao
interface PatientAppointmentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)// <- Annotate the 'addUser' function below. Set the onConflict strategy to IGNORE so if exactly the same user exists, it will just ignore it.
     fun addUser(user: PatientAppointmentData)

    @Update
     fun updateUser(user: PatientAppointmentData)

    @Delete
     fun deleteUser(user: PatientAppointmentData)



    @Query("SELECT * from patient_appointment_table ORDER BY id DESC") // <- Add a query to fetch all users (in user_table) in ascending order by their IDs.
    fun readAllData(): LiveData<List<PatientAppointmentData>>



    @Query("SELECT * FROM patient_appointment_table WHERE patientEmail = :email")
     fun getAppointment(email: String?): LiveData<List<PatientAppointmentData>>// <- This means function return type is List. Specifically, a List of Users.
}