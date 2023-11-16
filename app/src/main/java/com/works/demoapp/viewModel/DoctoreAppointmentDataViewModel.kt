package com.works.demoapp.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.works.demoapp.data.UserDatabase
import com.works.demoapp.doctor.models.DoctorAppointmentData

import com.works.demoapp.repository.DoctorAppointmentDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// UserViewModel provides users data to the UI and survive configuration changes.
// A ViewModel acts as a communication center between the Repository and the UI.

 class DoctoreAppointmentDataViewModel(application: Application): AndroidViewModel(application) {
     val readAllData: LiveData<List<DoctorAppointmentData>>
     //  var getUsername: LiveData<List<PatientAppointmentData>>


    private val repository: DoctorAppointmentDataRepository

    init {
        val userDao = UserDatabase.getDatabase(application).doctorAppointmentDataDao()
        repository= DoctorAppointmentDataRepository(userDao)
        readAllData = repository.readAllData
        //getUsername = repository.getUsername("", "", "")

    }

    fun addUser(user: DoctorAppointmentData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun updateUser(user: DoctorAppointmentData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }
    fun getAppointment(email: String?) : LiveData<List<DoctorAppointmentData>> {
        var getAppointment =   repository.getAppointment(email)
        Log.d("Appointment", "patient emai 123$email")
        viewModelScope.launch(Dispatchers.IO) {
           getAppointment = repository.getAppointment(email)

        }
        return getAppointment
    }

    fun deleteUser(user: DoctorAppointmentData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }


}