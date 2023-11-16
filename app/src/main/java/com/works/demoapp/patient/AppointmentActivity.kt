package com.works.demoapp.patient

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.works.demoapp.R
import com.works.demoapp.doctor.models.AppointmentData
import com.works.demoapp.doctor.models.DoctorAppointmentData
import com.works.demoapp.patient.models.PatientAppointmentData
import com.works.demoapp.viewModel.AppointmentDataViewModel
import com.works.demoapp.viewModel.DoctoreAppointmentDataViewModel
import com.works.demoapp.viewModel.PatientAppointmentViewModel
import java.util.Calendar

class AppointmentActivity : AppCompatActivity() {
    lateinit var txtAppName: TextView
    lateinit var txtAppSurname: TextView
    lateinit var txtAppAge: TextView
    lateinit var txtAppField: TextView
    lateinit var txtAppHour: TextView
    lateinit var btnSelectHour: ImageButton
    lateinit var btnSelectDate: ImageButton
    lateinit var btnMakeApp: Button
    lateinit var editTxtAppNote: EditText
    var Date = ""
    var selectedHour = ""
    lateinit var ImgApp: ImageView
    private lateinit var appointmentDataViewModel: AppointmentDataViewModel
    private lateinit var patientAppointmentViewModel: PatientAppointmentViewModel
    private lateinit var doctoreAppointmentDataViewModel: DoctoreAppointmentDataViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointment)

        txtAppName = findViewById(R.id.txtAppName)
        txtAppSurname = findViewById(R.id.txtAppSurname)
        txtAppAge = findViewById(R.id.txtAppAge)
        txtAppField = findViewById(R.id.txtAppField)
        txtAppHour = findViewById(R.id.txtAppHour)
        btnSelectHour = findViewById(R.id.btnSelectHour)
        btnSelectDate = findViewById(R.id.btnSelectDate)

        btnMakeApp = findViewById(R.id.btnMakeApp)
        editTxtAppNote = findViewById(R.id.editTxtAppNote)

        val doctorName = intent.getStringExtra("name")
        val doctorSurname = intent.getStringExtra("surname")
        val doctorAge = intent.getStringExtra("age")
        val doctorField = intent.getStringExtra("field")
        val doctorImage = intent.getStringExtra("image")
        val patientImage = intent.getStringExtra("patientImage")
        val patientFullName = intent.getStringExtra("patientName")
        val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)

        val patientEmail = sharedPreference.getString("email","")
        appointmentDataViewModel = ViewModelProvider(this).get(AppointmentDataViewModel::class.java)
        patientAppointmentViewModel = ViewModelProvider(this).get(PatientAppointmentViewModel::class.java)
        doctoreAppointmentDataViewModel = ViewModelProvider(this).get(DoctoreAppointmentDataViewModel::class.java)

        txtAppName.text = "Doctor Name : " + doctorName
        txtAppSurname.text = "Doctor Surname : " + doctorSurname
        txtAppAge.text = "Age : " + doctorAge
        txtAppField.text = "Field : " + doctorField


        val currentDate = Calendar.getInstance()
        val year = currentDate.get(Calendar.YEAR)
        val month = currentDate.get(Calendar.MONTH)
        val dayOfMonth = currentDate.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                // Seçilen tarihi kullan

                val selectedDate = Calendar.getInstance()
                selectedDate.set(selectedYear, selectedMonth, selectedDayOfMonth)
                val dayOfWeek = selectedDate.get(Calendar.DAY_OF_WEEK)


                if (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.TUESDAY || dayOfWeek == Calendar.WEDNESDAY
                    || dayOfWeek == Calendar.THURSDAY
                    || dayOfWeek == Calendar.SATURDAY) {
                    Toast.makeText(this, "Doctor is available only monday and friday between 10 AM to 5 PM ", Toast.LENGTH_LONG)
                        .show()
                } else {
                    // İşlemleriniz
                    var ay = "${selectedMonth + 1}"
                    if (selectedMonth + 1 < 10) {
                        ay = "0${selectedMonth + 1}"
                    }

                    Date = "$selectedDayOfMonth.$ay.$selectedYear"
                }

            },
            year,
            month,
            dayOfMonth
        )

        // Minimum tarih olarak bugünden önceki günleri belirle
        val minDate = Calendar.getInstance()
        minDate.add(Calendar.DAY_OF_MONTH, 0)
        datePickerDialog.datePicker.minDate = minDate.timeInMillis

        // Maksimum tarih olarak bugünden 20 gün sonrasını belirle
        val maxDate = Calendar.getInstance()
        maxDate.add(Calendar.DAY_OF_MONTH, 20)
        datePickerDialog.datePicker.maxDate = maxDate.timeInMillis

        btnSelectDate.setOnClickListener {
            datePickerDialog.show()
        }


        val mTimePicker: TimePickerDialog
        val mCurrentTime = Calendar.getInstance()
        val hour = mCurrentTime.get(Calendar.HOUR_OF_DAY)
        val minute = 0


        mTimePicker = TimePickerDialog(this, object : TimePickerDialog.OnTimeSetListener {

            override fun onTimeSet(p0: TimePicker?, hour: Int, minute: Int) {
               // val roundedMinute = (Math.round(minute.toFloat() / 30) * 30) % 60

                // This rounds the entered value to the nearest multiple of 15.
                // IMPORTANT: For example, between 5.45 and 6.00, be careful that the time closest to 6.00 is rounded to 5.00.
                if (hour < 10 || hour >= 17) {
                    Toast.makeText(
                        this@AppointmentActivity,
                        "Doctor is available only on Monday and Friday between 10 AM to 5 PM ",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    selectedHour = String.format("%d:%d", hour, 0)
                    txtAppHour.setText(
                        "Date: " + Date + "\nTime: " + String.format(
                            "%d:%d",
                            hour,
                            0
                        )
                    )
                }

            }

        }, hour, minute, true)


        btnSelectHour.setOnClickListener {
            if (Date.isEmpty()) {
                Toast.makeText(this, "Please select date first", Toast.LENGTH_LONG).show()
            } else {
                mTimePicker.show()
            }


        }
        btnMakeApp.setOnClickListener {

            val doctorEmail = intent.getStringExtra("email")
            val patientImage = patientImage
            val doctorImage = doctorImage
            val appointmentNote = editTxtAppNote.text.toString()
            val appointmentDate = Date
            val appointmentHour = selectedHour
            Log.d("Appointment", "patient emai " +patientEmail+patientFullName)
            Log.d("Appointment", "doctor emai " +doctorEmail+doctorField)
            Log.d("Appointment", "date emai " +appointmentDate+appointmentNote)
            Log.d("Appointment", "hour emai " +appointmentHour)
            if (patientEmail != null && appointmentDate.isNotEmpty() && appointmentHour.isNotEmpty()) {
                val doctorFullname = doctorName + " " + doctorSurname
                val appointmentInfo = AppointmentData(
                    0,
                    doctorEmail,
                    patientEmail,
                    patientFullName,
                    doctorFullname,
                    doctorField,
                    appointmentNote,
                    appointmentDate,
                    appointmentHour
                )
                val patientAppointmentinfo= PatientAppointmentData(
                    0,
                    patientEmail,
                    doctorEmail,
                    doctorFullname,
                    doctorField,
                    appointmentNote,
                    appointmentDate,
                    appointmentHour
                )
                val doctorAppointmentinfo= DoctorAppointmentData(
                    0,
                    doctorEmail,
                    patientEmail,
                    patientFullName,
                    appointmentNote,
                    appointmentDate,
                    appointmentHour
                )
                Log.d("Appointment", "patient emai " +patientEmail+patientFullName)
                Log.d("Appointment", "doctor emai " +doctorEmail+doctorFullname+doctorField)
                Log.d("Appointment", "date emai " +appointmentDate+appointmentNote)
                Log.d("Appointment", "hour emai " +appointmentHour)
                patientAppointmentViewModel.addUser(patientAppointmentinfo)
                doctoreAppointmentDataViewModel.addUser(doctorAppointmentinfo)
            //    addAppointmentToFirestore(patientEmail,doctorEmail!!,appointmentInfo)

                var success = appointmentDataViewModel.addUser(appointmentInfo)
                Log.d("Appointment", "success " +success)
                appointmentDataViewModel.readAllData.observe(
                    this,
                    Observer<List<AppointmentData>> { patientAppointmentData ->
                        if (patientAppointmentData.isNotEmpty()) {
                            Toast.makeText(this, "Your appointment has been created successfully", Toast.LENGTH_LONG).show()
                            val intent = Intent(this, PatientHomePageActivity::class.java)
                            startActivity(intent)
                            finish()

                        } else {


                        }
                    })

            } else {
                Toast.makeText(
                    this,
                    "Please fill in the required information completely",
                    Toast.LENGTH_LONG
                ).show()
            }


        }
    }


}