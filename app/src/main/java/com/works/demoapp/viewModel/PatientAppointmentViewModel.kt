package com.works.demoapp.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.works.demoapp.data.UserDatabase
import com.works.demoapp.patient.models.PatientAppointmentData

import com.works.demoapp.repository.PatientAppointmentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// UserViewModel provides users data to the UI and survive configuration changes.
// A ViewModel acts as a communication center between the Repository and the UI.

 class PatientAppointmentViewModel(application: Application): AndroidViewModel(application) {
     val readAllData: LiveData<List<PatientAppointmentData>>
     //  var getUsername: LiveData<List<PatientAppointmentData>>


    private val repository: PatientAppointmentRepository

    init {
        val userDao = UserDatabase.getDatabase(application).patientAppointmentDao()
        repository= PatientAppointmentRepository(userDao)
        readAllData = repository.readAllData
        //getUsername = repository.getUsername("", "", "")

    }

    fun addUser(user: PatientAppointmentData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun updateUser(user: PatientAppointmentData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }
    fun getAppointment(email: String?) : LiveData<List<PatientAppointmentData>> {
        var getAppointment =   repository.getAppointment(email)
        Log.d("Appointment", "patient emai 123$email")
        viewModelScope.launch(Dispatchers.IO) {
           getAppointment = repository.getAppointment(email)

        }
        return getAppointment
    }

    fun deleteUser(user: PatientAppointmentData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }


}