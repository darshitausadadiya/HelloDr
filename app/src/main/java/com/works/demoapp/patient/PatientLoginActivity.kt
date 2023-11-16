package com.works.demoapp.patient

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider


import com.works.demoapp.R
import com.works.demoapp.patient.models.PatientData
import com.works.demoapp.viewModel.UserViewModel


class PatientLoginActivity : AppCompatActivity() {
    lateinit var btnRegister: Button
    lateinit var btnLogin: Button
    lateinit var editTxtLEmail: EditText
    lateinit var editTxtLPassword: EditText

     var client: String = ""
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_login)

        btnLogin = findViewById(R.id.btnDoctorLogin)
        btnRegister = findViewById(R.id.btnDoctorRegister)
        editTxtLEmail = findViewById(R.id.editTxtDoctorLEmail)
        editTxtLPassword = findViewById(R.id.editTxtDoctorLPassword)


        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        btnRegister.setOnClickListener {
            var intent = Intent(this, PatientRegisterActivity::class.java)
            startActivity(intent)
        }
        btnLogin.setOnClickListener {

            if (editTxtLEmail.text.toString() == "" || editTxtLPassword.text.toString() == "") {
                Toast.makeText(
                    this,
                    "Enter a valid patient email address",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val LoginEmail = editTxtLEmail.text.toString()
                val LoginPassword = editTxtLPassword.text.toString()
                if(LoginEmail.contains("@patient")) { client = "Patient"}
                mUserViewModel.getUsername(LoginEmail,LoginPassword, client).observe(
                    this,
                    Observer<List<PatientData>> { patientData ->
                        if (patientData.isNotEmpty()) {
                            Toast.makeText(this, "User login Successful", Toast.LENGTH_LONG).show()
                            Log.d("password", "data " + patientData.get(0).first)
                            val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
                            var editor = sharedPreference.edit()
                            editor.putString("first",patientData.get(0).first)
                            editor.putString("last",patientData.get(0).last)
                            editor.putString("age",patientData.get(0).age)
                            editor.putString("email",patientData.get(0).email)
                            editor.putString("password",patientData.get(0).password)
                            editor.putString("client",patientData.get(0).client)
                            editor.putInt("uid", patientData.get(0).UID!!)

                            editor.commit()
                            val intent = Intent(this, PatientHomePageActivity::class.java)
                            intent.putExtra("name", patientData.get(0).first)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "Incorrect Email or Password.Please try again", Toast.LENGTH_LONG).show()
                        }
                    })

            }
        }

    }
}