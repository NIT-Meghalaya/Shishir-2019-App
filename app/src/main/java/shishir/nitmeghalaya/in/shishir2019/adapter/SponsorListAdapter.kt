package shishir.nitmeghalaya.`in`.shishir2019.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import shishir.nitmeghalaya.`in`.shishir2019.R
import shishir.nitmeghalaya.`in`.shishir2019.models.SponsorItem
import shishir.nitmeghalaya.`in`.shishir2019.viewholder.SponsorListItemViewHolder

class SponsorListAdapter (private val sponsorList:ArrayList<SponsorItem>):RecyclerView.Adapter<SponsorListItemViewHolder>(){
    override fun getItemCount(): Int {
        return sponsorList.size
    }

    override fun onBindViewHolder(holder: SponsorListItemViewHolder, position: Int) {
        val sponsor = sponsorList[position]
        holder.bind(sponsor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SponsorListItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sponsor_item,parent,false)
        return SponsorListItemViewHolder(view)
    }

}
