package com.works.demoapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.works.demoapp.data.UserDatabase
import com.works.demoapp.doctor.models.AppointmentData

import com.works.demoapp.repository.AppointmentDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// UserViewModel provides users data to the UI and survive configuration changes.
// A ViewModel acts as a communication center between the Repository and the UI.

 class AppointmentDataViewModel(application: Application): AndroidViewModel(application) {
     val readAllData: LiveData<List<AppointmentData>>
     //  var getUsername: LiveData<List<PatientAppointmentData>>


    private val repository: AppointmentDataRepository

    init {
        val userDao = UserDatabase.getDatabase(application).appointmentDataDao()
        repository= AppointmentDataRepository(userDao)
        readAllData = repository.readAllData
        //getUsername = repository.getUsername("", "", "")

    }

    fun addUser(user: AppointmentData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun updateUser(user: AppointmentData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }
//    fun getUsername(userName: String,password:String,client: String) : LiveData<List<PatientAppointmentData>> {
//        getUsername = repository.getUsername(userName, password, client)
//        viewModelScope.launch(Dispatchers.IO) {
//           getUsername = repository.getUsername(userName, password, client)
//
//        }
//        return getUsername
//    }

    fun deleteUser(user: AppointmentData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }


}