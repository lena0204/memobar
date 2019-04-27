package com.lk.memobar2.adapters

import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.lk.memobar2.R
import com.lk.memobar2.database.MemoEntity

/**
 * Erstellt von Lena am 26/04/2019.
 */
class MemoListAdapter(private val memoList: List<MemoEntity>, private val listener: AdapterActionListener) :
    RecyclerView.Adapter<AdapterViewHolder>() {

    override fun getItemCount(): Int = memoList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.view_recyclerlist,
            parent, false)
        return AdapterViewHolder(v, listener)
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        val currentMemo = memoList[position]
        holder.tvId.text = currentMemo.id.toString()
        holder.tvContent.text = currentMemo.content
        holder.toggleActive.isChecked = currentMemo.isActive
    }

}