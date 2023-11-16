package com.works.demoapp.patient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.works.demoapp.R
import com.works.demoapp.patient.models.PatientData
import com.works.demoapp.viewModel.UserViewModel


class PatientRegisterActivity : AppCompatActivity() {
    lateinit var txtClientName: EditText
    lateinit var txtClientSurname: EditText
    lateinit var txtClientAge: EditText
    lateinit var txtClientEmail: EditText
    lateinit var txtClientPassword: EditText
    lateinit var btnConfirm: ImageButton

    lateinit var ClientName: String
    lateinit var ClientSurname: String
    lateinit var ClientAge: String
    lateinit var ClientEmail: String
    lateinit var ClientPassword: String
    private lateinit var mUserViewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_register)

        txtClientName = findViewById(R.id.txtClientName)
        txtClientSurname = findViewById(R.id.txtClientSurname)
        txtClientAge = findViewById(R.id.txtClientAge)
        txtClientEmail = findViewById(R.id.txtClientEmail)
        txtClientPassword = findViewById(R.id.txtClientPassword)
        btnConfirm = findViewById(R.id.btnRDocConfirm)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)


        btnConfirm.setOnClickListener {
            ClientName = txtClientName.text.toString()
            ClientSurname = txtClientSurname.text.toString()
            ClientAge = txtClientAge.text.toString()
            ClientEmail = txtClientEmail.text.toString() // + "@client.com"
            ClientPassword = txtClientPassword.text.toString()
            Log.d("name", ClientName)
            Log.d("surname", ClientSurname)
            Log.d("age", ClientAge)
            Log.d("email", ClientEmail)
            Log.d("password", ClientPassword)


            if (!ClientEmail.contains("@patient")) {
                Toast.makeText(
                    this,
                    "Enter a valid patient email address",
                    Toast.LENGTH_LONG
                ).show()
            } else if (ClientName != "" && ClientSurname != "" && ClientAge.toString() != "" && ClientEmail.toString() != "" && ClientPassword.toString() != "") {
                val user = PatientData(
                    0,
                    ClientName,
                    ClientSurname,
                    ClientAge,
                    ClientEmail,
                    ClientPassword,
                    "Patient"

                ) // <- Pass id, firstName, lastName, and age. Although id will be auto-generated because it is a primary key, we need to pass a value or zero (Don't worry, the Room library knows it is the primary key and is auto-generated).

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
                                    this@PatientRegisterActivity,
                                    PatientLoginActivity::class.java
                                )
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "Please try again", Toast.LENGTH_LONG).show()
                        }
                    })



                        } else {
                            Toast.makeText(
                                this,
                                "Do not enter incomplete information",
                                Toast.LENGTH_LONG
                            ).show()
                        }

                    }


            }


        }
