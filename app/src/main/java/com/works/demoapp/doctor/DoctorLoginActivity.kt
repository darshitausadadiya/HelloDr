package com.works.demoapp.doctor

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


class DoctorLoginActivity : AppCompatActivity() {
    lateinit var btnDoctorLogin: Button
    lateinit var btnDoctorRegister: Button
    lateinit var editTxtDoctorLEmail: EditText
    lateinit var editTxtDoctorLPassword: EditText
    var client: String = ""
    private lateinit var mUserViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_login)

        btnDoctorLogin = findViewById(R.id.btnDoctorLogin)
        btnDoctorRegister = findViewById(R.id.btnDoctorRegister)
        editTxtDoctorLEmail = findViewById(R.id.editTxtDoctorLEmail)
        editTxtDoctorLPassword = findViewById(R.id.editTxtDoctorLPassword)




        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        btnDoctorLogin.setOnClickListener {
            if (editTxtDoctorLEmail.text.toString() == "" || editTxtDoctorLPassword.text.toString() == "") {
                Toast.makeText(
                    this,
                    "",
                    Toast.LENGTH_LONG
                ).show()
            } else if (!editTxtDoctorLEmail.text.toString().contains("@doctor")) {
                Toast.makeText(
                    this,
                    "Enter a valid doctor email address",
                    Toast.LENGTH_LONG
                ).show()
            }else {
                val LoginEmail = editTxtDoctorLEmail.text.toString()
                val LoginPassword = editTxtDoctorLPassword.text.toString()


                if(LoginEmail.contains("@doctor")) { client = "Doctor"}
                mUserViewModel.getUsername(LoginEmail,LoginPassword, client).observe(
                    this,
                    Observer<List<PatientData>> { patientData ->
                        if (patientData.isNotEmpty()) {
                            Toast.makeText(this, "User login Successful", Toast.LENGTH_LONG).show()
                            Log.d("password", "data " + patientData.get(0).first)
                            val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", MODE_PRIVATE)
                            var editor = sharedPreference.edit()
                            editor.putString("first",patientData.get(0).first)
                            editor.putString("last",patientData.get(0).last)
                            editor.putString("age",patientData.get(0).age)
                            editor.putString("email",patientData.get(0).email)
                            editor.putString("password",patientData.get(0).password)
                            editor.putString("client",patientData.get(0).client)
                            editor.putInt("uid", patientData.get(0).UID!!)

                            editor.commit()
                            val intent = Intent(this, DoctorHomepageActivity::class.java)
                            intent.putExtra("name", patientData.get(0).first)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "Incorrect Email or Password.Please try again", Toast.LENGTH_LONG).show()
                        }
                    })
            }
        }

        btnDoctorRegister.setOnClickListener {
            var intent = Intent(this, DoctorRegisterActivity::class.java)
            startActivity(intent)
        }
    }
}