package com.example.myvaccine

data class CenterModel (
    val centerName: String,
    val centerAddress: String,
    val centerDistrict :  String,
    val centerArea : String,
    val centerFromTiming: String,
    var centerToTiming: String,
    var feeType: String,
    var ageLimit: Int,
    var vaccineName: String,
    var availableCapacity: Int
)