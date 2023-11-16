package com.works.demoapp.doctor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.works.demoapp.R
import com.works.demoapp.patient.models.PatientData
import com.works.demoapp.viewModel.UserViewModel

class DoctorRegisterActivity : AppCompatActivity() {
    lateinit var spinnerSpecialties: Spinner
    lateinit var txtRDoctorName: EditText
    lateinit var txtRDoctorSurname: EditText
    lateinit var txtRDoctorAge: EditText
    lateinit var txtRDoctorEmail: EditText
    lateinit var txtRDoctorPassword: EditText
    lateinit var btnRDocConfirm: ImageButton



    lateinit var DoctorName: String
    lateinit var DoctorSurname: String
    lateinit var DoctorAge: String
    lateinit var DoctorField: String
    lateinit var DoctorEmail: String
    lateinit var DoctorPassword: String
    var client: String = ""
    private lateinit var mUserViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_register)


        txtRDoctorName = findViewById(R.id.txtRDoctorName)
        txtRDoctorSurname = findViewById(R.id.txtRDoctorSurname)
        txtRDoctorAge = findViewById(R.id.txtRDoctorAge)
        txtRDoctorEmail = findViewById(R.id.txtRDoctorEmail)
        txtRDoctorPassword = findViewById(R.id.txtRDoctorPassword)
        btnRDocConfirm = findViewById(R.id.btnRDocConfirm)
        spinnerSpecialties = findViewById(R.id.spinnerField)

        val specialties = resources.getStringArray(R.array.doctor_specialties)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, specialties)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSpecialties.adapter = adapter
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        btnRDocConfirm.setOnClickListener {
            DoctorName = txtRDoctorName.text.toString()
            DoctorSurname = txtRDoctorSurname.text.toString()
            DoctorAge = txtRDoctorAge.text.toString()
            DoctorField = spinnerSpecialties.selectedItem.toString()
            DoctorEmail = txtRDoctorEmail.text.toString()
            DoctorPassword = txtRDoctorPassword.text.toString()
            if (!DoctorEmail.contains("@doctor")) {
                Toast.makeText(
                    this,
                    "Enter a valid doctor email address",
                    Toast.LENGTH_LONG
                ).show()
            } else if (DoctorName != "" && DoctorSurname != "" && DoctorAge.toString() != "" && DoctorField != "" && DoctorEmail.toString() != "" && DoctorPassword.toString() != "") {
                val user = PatientData(
                    0,
                    DoctorName,
                    DoctorSurname,
                    DoctorAge,
                    DoctorEmail,
                    DoctorPassword,
                    "Doctor",
                    DoctorField

                )
                // Add Data to database
                            mUserViewModel.addUser(user)
                            mUserViewModel.readAllData.observe(
                                this,
                                Observer<List<PatientData>> { patientData ->
                                    if (patientData.isNotEmpty()) {
                                        Toast.makeText(this, "User added succesfully", Toast.LENGTH_LONG).show()
                                        Log.d("password", "data " + patientData.get(0).first)
                                        val intent =
                                            Intent(
                                                this@DoctorRegisterActivity,
                                                DoctorLoginActivity::class.java
                                            )
                                        startActivity(intent)
                                        finish()
                                    } else {
                                        Toast.makeText(this, "Please try again", Toast.LENGTH_LONG).show()
                                    }
                                })





            } else {
                Toast.makeText(this, "All fields are mandatory", Toast.LENGTH_LONG).show()
            }
        }
    }
}
