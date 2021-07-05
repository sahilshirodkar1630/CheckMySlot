package com.example.myvaccine

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSearch.setOnClickListener {

            val pinCode = edtPinCode.text.toString()
            if (pinCode.length != 6) {
                edtPinCode.error = "Please enter a valid pin-code"
            } else {

                val c = Calendar.getInstance()
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)
                val day = c.get(Calendar.DAY_OF_MONTH)

                val dpd = DatePickerDialog(
                    this, { _, year, monthOfYear, dayOfMonth ->
                        val dateStr = """$dayOfMonth-${monthOfYear + 1}-${year}"""

                        val intent = Intent(applicationContext, CenterActivity::class.java)
                        intent.putExtra("PinCode", pinCode)
                        intent.putExtra("Date", dateStr)
                        startActivity(intent)
//                        getAppointment(pinCode, dateStr)
                    },
                    year,
                    month,
                    day
                )
                dpd.show()
            }
        }

    }
}