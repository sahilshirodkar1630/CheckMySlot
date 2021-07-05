package com.example.myvaccine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.myvaccine.databinding.ActivityCenterBinding
import com.example.myvaccine.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_center.*




class CenterActivity : AppCompatActivity() , OnClickCenter {

//    private lateinit var binding: ActivityCenterBinding
    private lateinit var centerList: MutableList<CenterModel>
    private lateinit var centerAdapter: CenterAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityCenterBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_center)


        recyclerViewCenter.layoutManager = LinearLayoutManager(this)
//        mAdapter = NewsListAdapter(this)
//        recyclerViewCenter.adapter = mAdapter

        val bundle: Bundle? = intent.extras
        val pincode = bundle?.get("PinCode")
        val date = bundle?.get("Date")
//        Toast.makeText(this,pincode.toString()+" "+data.toString(),Toast.LENGTH_LONG).show();


        centerList = mutableListOf()

//        binding.recyclerViewCenter.layoutManager = LinearLayoutManager(this)

        checkSlot(pincode as String,date as String)
    }

    private fun checkSlot(pinCode:String,date:String){

        val url =     "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByPin?pincode=$pinCode&date=$date"

        val queue = Volley.newRequestQueue(this)

        val request = JsonObjectRequest(Request.Method.GET,url,null,
            { response ->
                Log.e("request", "Success response is $response")
                val centerArray = response.getJSONArray("centers")
                if(centerArray.length() == 0){
                    Toast.makeText(this, "No centers available", Toast.LENGTH_SHORT).show()
                }
                for (i in 0 until centerArray.length()) {
                    val centerObj = centerArray.getJSONObject(i)
                    val centerName = centerObj.getString("name")
                    val centerAddress = centerObj.getString("address")
                    val centerDistrict = centerObj.getString("district_name")
                    val centerArea = centerObj.getString("block_name")
                    val centerFromTime = centerObj.getString("from")
                    val centerToTime = centerObj.getString("to")
                    val feeType = centerObj.getString("fee_type")

                    val sessionObj = centerObj.getJSONArray("sessions").getJSONObject(0)
                    val ageLimit = sessionObj.getInt("min_age_limit")
                    val vaccineName = sessionObj.getString("vaccine")
                    val availableCapacity = sessionObj.getInt("available_capacity")

                    val center = CenterModel(
                        centerName,
                        centerAddress,
                        centerDistrict,
                        centerArea,
                        centerFromTime,
                        centerToTime,
                        feeType,
                        ageLimit,
                        vaccineName,
                        availableCapacity
                    )
                    centerList.add(center)
                }

                centerAdapter = CenterAdapter(this,centerList)

                recyclerViewCenter.adapter = centerAdapter
                recyclerViewCenter.setHasFixedSize(true)

                centerAdapter.notifyDataSetChanged()

        },{error ->
                Log.e("Error", "Response is $error")
                Toast.makeText(this@CenterActivity, "Failed to get response", Toast.LENGTH_SHORT).show()

            })
        queue.add(request)

    }

    override fun OnItemClicked(center: CenterModel) {

        val intent = Intent(this, SlotActivity::class.java)
        intent.putExtra("VaccineName",center.vaccineName)
        intent.putExtra("CenterName",center.centerName)
        intent.putExtra("CenterAddress",center.centerAddress)
        intent.putExtra("CenterArea",center.centerArea)
        intent.putExtra("FeeType",center.feeType)
        intent.putExtra("AgeLimit",center.ageLimit.toString())
        intent .putExtra("Availability",center.availableCapacity.toString())
        val timing =    "From : ${center.centerFromTiming} To : ${center.centerToTiming}"
        intent.putExtra("Timings", timing)
        startActivity(intent)

    }
};