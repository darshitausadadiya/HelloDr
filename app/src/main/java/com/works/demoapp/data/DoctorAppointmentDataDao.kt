package com.works.demoapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.works.demoapp.doctor.models.DoctorAppointmentData

// UserDao contains the methods used for accessing the database, including queries.
@Dao
interface DoctorAppointmentDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)// <- Annotate the 'addUser' function below. Set the onConflict strategy to IGNORE so if exactly the same user exists, it will just ignore it.
    fun addUser(user: DoctorAppointmentData)

    @Update
    fun updateUser(user: DoctorAppointmentData)

    @Delete
    fun deleteUser(user: DoctorAppointmentData)


    @Query("SELECT * from doctor_appointment_data_table ORDER BY id DESC") // <- Add a query to fetch all users (in user_table) in ascending order by their IDs.
    fun readAllData(): LiveData<List<DoctorAppointmentData>>


    @Query("SELECT * FROM doctor_appointment_data_table WHERE doctorEmail = :email")
    fun getAppointment(email: String?): LiveData<List<DoctorAppointmentData>>
}