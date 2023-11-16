package com.works.demoapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.works.demoapp.doctor.models.AppointmentData

// UserDao contains the methods used for accessing the database, including queries.
@Dao
interface AppointmentDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)// <- Annotate the 'addUser' function below. Set the onConflict strategy to IGNORE so if exactly the same user exists, it will just ignore it.
     fun addUser(user: AppointmentData)

    @Update
     fun updateUser(user: AppointmentData)

    @Delete
     fun deleteUser(user: AppointmentData)



    @Query("SELECT * from appointment_data_table ORDER BY id DESC") // <- Add a query to fetch all users (in user_table) in ascending order by their IDs.
    fun readAllData(): LiveData<List<AppointmentData>>


   // @Query("SELECT * FROM patient_appointment_table WHERE email LIKE :userName AND password LIKE :password AND client LIKE :client ")
    // fun getUsername(userName: String,password:String,client: String): LiveData<List<PatientAppointmentData>>// <- This means function return type is List. Specifically, a List of Users.
}