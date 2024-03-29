package tech.sealsoft.crypto.adapter

import android.content.ClipData
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tech.sealsoft.crypto.R
import tech.sealsoft.crypto.model.CoinEntity
import tech.sealsoft.crypto.model.MarketEntity

class CoinRecycleAdapter(val context: Context) : RecyclerView.Adapter<CoinRecycleAdapter.MyViewHolder>() {

    var coinList : List<CoinEntity> = listOf()
    var mClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(coin: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.coin_recycler_adapter,parent,false)
        return MyViewHolder(view)
    }

    fun setOnItemClickListener(aClickListener: OnItemClickListener) {
        mClickListener = aClickListener
    }

    override fun getItemCount(): Int {
        return coinList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.coinName.text = coinList[position].symbol
        holder.coinDescription.text = coinList[position].name
        holder.coinPriceUsd.text = coinList[position].priceUsd.toString()

        holder.layoutItem.setOnClickListener {
            mClickListener?.onItemClick(holder.coinDescription.text.toString())
        }
    }

    fun setCoinListItems(coinList: List<CoinEntity?>){
        this.coinList = coinList as List<CoinEntity>
        notifyDataSetChanged()
    }


    class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val coinName: TextView = itemView!!.findViewById(R.id.coinName)
        val coinDescription: TextView = itemView!!.findViewById(R.id.description)
        val coinPriceUsd: TextView = itemView!!.findViewById(R.id.priceUsd)
        val layoutItem: LinearLayout = itemView!!.findViewById(R.id.lyt_parent)


    }


}