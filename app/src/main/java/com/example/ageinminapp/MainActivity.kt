package com.example.ageinminapp

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnDatePicker.setOnClickListener { view ->
            clickDatePicker(view)
            //toast is used to display a date once a date picker button is clicked
            // Toast.makeText(this,"Button works",Toast.LENGTH_LONG).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun clickDatePicker(view: View) {

        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

        val dpd =
            DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(this, "Date Picker works $day $month $year", Toast.LENGTH_LONG)
                    .show()
                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                tvSelectedDate.setText(selectedDate)
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                //theDate class will return the date in time of milliseconds
                //to convert it into secs divide by 1000
                //to convert to minutes divide by 60,000
                //to converts to hrs divide by 3600,000
                val selectedDateInMinutes = theDate!!.time / 60000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInMinutes = currentDate!!.time / 60000
                val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                tvSelectedDateInMinutes.setText(differenceInMinutes.toString())

                val selectedDateInHours = theDate!!.time / 3600000
                //currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInHours = currentDate!!.time / 3600000
                val differenceInHours = currentDateInHours - selectedDateInHours
                tvSelectedDateInHours.setText(differenceInHours.toString())

                val selectedDateInDays = theDate!!.time / (3600000 * 24)
                //val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInDays = currentDate!!.time / (3600000 * 24)
                val differenceInDays = currentDateInDays - selectedDateInDays
                tvSelectedDateInDays.setText(differenceInDays.toString())

                val selectedDateInMonths = theDate!!.time / (3600000 * 24 * (365/12))
                //val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInMonths = currentDate!!.time / (3600000 * 24 * (365/12))
                val differenceInMonths = currentDateInMonths - selectedDateInMonths
                tvSelectedDateInMonths.setText(differenceInMonths.toString())

                val selectedDateInYears = theDate!!.time / (3600000 * 24 * (365/12)*12)
                //val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInYears = currentDate!!.time / (3600000 * 24 * (365/12)*12)
                val differenceInYears = currentDateInYears - selectedDateInYears
                tvSelectedDateInYears.setText(differenceInYears.toString())

            },


            year,
            month,
            day
        )

        dpd.datePicker.setMaxDate(Date().time - 86400000)//86400000 is millisecs in one day to prevent selecting the future dates
        dpd.show()


    }

}


