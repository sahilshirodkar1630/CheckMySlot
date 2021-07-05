package com.example.myvaccine

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.slot_item.view.*

class CenterAdapter(private val listener: OnClickCenter ,private val centerList : List<CenterModel>):
    RecyclerView.Adapter<CenterAdapter.CenterViewHolder>() {

    inner class CenterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val VaccineName = itemView.txtName
        var CenterNamee = itemView.txtCenterName
        val CenterAddress = itemView.txtCenterAddres
        val SlotTimings = itemView.txtTimings
        val AgeLimit = itemView.txtAge

    }

    override fun getItemCount(): Int {
        return centerList.size
    }

    override fun onBindViewHolder(holder: CenterViewHolder, position: Int) {
        val currentItem = centerList[position]

        holder.CenterNamee.text = currentItem.centerName

        holder.CenterAddress.text = currentItem.centerAddress
        holder.AgeLimit.text = "Age Limit : ${currentItem.ageLimit}+"
//        holder.availabilityTV.text = "Availability : ${currentItem.availableCapacity}"
        holder.SlotTimings.text =
            "From : ${currentItem.centerFromTiming} To : ${currentItem.centerToTiming}"
        holder.VaccineName.text = currentItem.vaccineName
//        holder.feeTypeTV.text = currentItem.feeType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CenterViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.slot_item, parent, false)
        val viewHolder = CenterViewHolder(itemView)
        itemView.setOnClickListener {
            listener.OnItemClicked(centerList[viewHolder.adapterPosition])
        }
        return viewHolder
    }


}
interface OnClickCenter{
    fun OnItemClicked(center:CenterModel)
}