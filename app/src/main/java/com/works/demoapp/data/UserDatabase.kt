package com.works.demoapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.works.demoapp.doctor.models.AppointmentData
import com.works.demoapp.doctor.models.DoctorAppointmentData
import com.works.demoapp.patient.models.PatientAppointmentData
import com.works.demoapp.patient.models.PatientData

// UserDatabase represents database and contains the database holder and server the main access point for the underlying connection to your app's persisted, relational data.

@Database(
    entities = [PatientData::class, PatientAppointmentData::class,AppointmentData::class,DoctorAppointmentData::class],
    version = 1,                // <- Database version
    exportSchema = true
)
abstract class UserDatabase: RoomDatabase() { // <- Add 'abstract' keyword and extends RoomDatabase
    abstract fun userDao(): UserDao
    abstract fun patientAppointmentDao(): PatientAppointmentDao
    abstract fun appointmentDataDao(): AppointmentDataDao
    abstract fun doctorAppointmentDataDao(): DoctorAppointmentDataDao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase{
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}