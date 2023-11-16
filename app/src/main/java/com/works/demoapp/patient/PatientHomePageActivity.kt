package com.works.demoapp.patient

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.works.demoapp.MainActivity
import com.works.demoapp.R
import com.works.demoapp.patient.adapter.DoctorCustomAdapter
import com.works.demoapp.patient.models.PatientData
import com.works.demoapp.viewModel.UserViewModel


class PatientHomePageActivity : AppCompatActivity() {
    lateinit var listView: ListView


    lateinit var userImage: String
    lateinit var userName : String


    private lateinit var mUserViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_home_page)

        listView = findViewById(R.id.listView)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        mUserViewModel.getDoctorList("Doctor","General").observe(
            this,
            Observer<List<PatientData>> { patientData ->
                if (patientData.isNotEmpty()) {

                    val adapter = DoctorCustomAdapter(this, patientData)

                    listView.adapter = adapter
                    Log.d("userviewmode", "userviev mode1 p"+patientData.size)


                } else {


                }
            })

        val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        var first = sharedPreference.getString("first","")
        var last = sharedPreference.getString("last","")
        var email = sharedPreference.getString("email","")
        var password = sharedPreference.getString("password","")
        var age = sharedPreference.getString("age","")
        var client = sharedPreference.getString("client","Patient")
        var uid = sharedPreference.getInt("UID",0)
        userName = first +" "+last



        listView.setOnItemClickListener() { adapterView, view, i, l ->
            val selectedItem = adapterView.getItemAtPosition(i) as PatientData
            Log.d("info", selectedItem.toString())

            val intent = Intent(this, AppointmentActivity::class.java)
            intent.putExtra("name", selectedItem.first)
            intent.putExtra("surname", selectedItem.last)
            intent.putExtra("age", selectedItem.age)
            intent.putExtra("field", selectedItem.field)

            intent.putExtra("email",selectedItem.email)

            intent.putExtra("patientName",userName)
            startActivity(intent)

            true
        }


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.patient_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {


            R.id.logout -> {

            }
            R.id.appointments -> {
                val intent = Intent(applicationContext,PatientMyAppointmentsActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        mUserViewModel.getDoctorList("Doctor","General").observe(
            this,
            Observer<List<PatientData>> { patientData ->
                if (patientData.isNotEmpty()) {
                    val adapter = DoctorCustomAdapter(this, patientData)
                    listView.adapter = adapter

                    Log.d("password", "data " + patientData.get(0).first)

                } else {


                }
            })


    }
}