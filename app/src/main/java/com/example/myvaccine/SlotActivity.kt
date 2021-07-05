package com.example.myvaccine

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.browser.customtabs.CustomTabsIntent
import kotlinx.android.synthetic.main.activity_slot.*

class SlotActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slot)

        val bundle: Bundle? = intent.extras

        val vaccine = bundle?.get("VaccineName")
        Log.d("Key", "onViewCreated: $bundle")
        val centerName = bundle?.get("CenterName")
        val centerAdress = bundle?.get("CenterAddress")
        val centerArea = bundle?.get("CenterArea")
        val timings = bundle?.get("Timings")?: "Default"
        val feeType = bundle?.get("FeeType")?: "Default"
        val ageLimit = bundle?.get("AgeLimit")?: "Default"
        val availability = bundle?.get("Availability")?: "Default"



        txtFragVaccineName.text = vaccine.toString()
        txtFragCenterName.text = centerName.toString()
        txtFragAvailability.text = "Availability : ${availability.toString()}"
        txtFragCenterAddress.text = centerAdress.toString()
        txtFragAreaName.text = centerArea.toString()
        txtFragAgeLimit.text ="Age Limit : ${ageLimit.toString()}+"
        txtFragFeeType.text = "Fee Type : ${feeType.toString()}"
        txtFragTiming.text = timings.toString()

        btnBookSlot.setOnClickListener {
            val builder = CustomTabsIntent.Builder();
            val customTabsIntent = builder.build();
            customTabsIntent.launchUrl(this, Uri.parse("https://www.cowin.gov.in/"));

        }

    }
}