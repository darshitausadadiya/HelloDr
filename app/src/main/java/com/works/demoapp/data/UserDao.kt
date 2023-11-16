package com.works.demoapp.data

import androidx.lifecycle.LiveData
import androidx.room.*

import com.works.demoapp.patient.models.PatientData

// UserDao contains the methods used for accessing the database, including queries.
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)// <- Annotate the 'addUser' function below. Set the onConflict strategy to IGNORE so if exactly the same user exists, it will just ignore it.
     fun addUser(user: PatientData)

    @Update
     fun updateUser(user: PatientData)

    @Delete
     fun deleteUser(user: PatientData)



    @Query("SELECT * from user_table ORDER BY UID DESC") // <- Add a query to fetch all users (in user_table) in ascending order by their IDs.
    fun readAllData(): LiveData<List<PatientData>>


    @Query("SELECT * FROM user_table WHERE email = :userName AND password = :password AND client = :client ")
     fun getUsername(userName: String,password:String,client: String): LiveData<List<PatientData>>// <- This means function return type is List. Specifically, a List of Users.

    @Query("SELECT * FROM user_table WHERE client = :client AND field = :field")
    fun getDoctorList(client: String,field: String): LiveData<List<PatientData>>
}