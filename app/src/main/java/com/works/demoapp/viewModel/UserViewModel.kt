package com.works.demoapp.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.works.demoapp.data.UserDatabase

import com.works.demoapp.patient.models.PatientData
import com.works.demoapp.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// UserViewModel provides users data to the UI and survive configuration changes.
// A ViewModel acts as a communication center between the Repository and the UI.

 class UserViewModel(application: Application): AndroidViewModel(application) {
     val readAllData: LiveData<List<PatientData>>
      // var getUsername: LiveData<List<PatientData>>
    // var getDoctorList: LiveData<List<PatientData>>


    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository= UserRepository(userDao)
        readAllData = repository.readAllData


    }

    fun addUser(user: PatientData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun updateUser(user: PatientData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }
    fun getUsername(userName: String,password:String,client: String) : LiveData<List<PatientData>> {
       var getUsername = repository.getUsername(userName, password, client)
        viewModelScope.launch(Dispatchers.IO) {
            getUsername = repository.getUsername(userName, password, client)

        }
        return getUsername
    }
     fun getDoctorList(client: String,field: String): LiveData<List<PatientData>> {
         Log.d("userviewmode", "userviev mode $client$field")
          var getDoctorList = repository.getDoctorList( client,field)
         viewModelScope.launch(Dispatchers.IO) {
             getDoctorList = repository.getDoctorList(client,field)


         }
         return getDoctorList
     }

    fun deleteUser(user: PatientData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }


}