package com.lk.memobar2.adapters

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lk.memobar2.R

/**
 * Erstellt von Lena am 26/04/2019.
 */
class ViewHolderMemo(v: View, listener: AdapterActionListener): RecyclerView.ViewHolder(v) {

    val tvId: TextView = v.findViewById(R.id.tv_view_id)
    val tvContent: TextView = v.findViewById(R.id.tv_view_content)
    val cbActive: CheckBox = v.findViewById(R.id.cb_view_active)

    init {
        cbActive.setOnClickListener {
            listener.changeActiveState(tvId.text.toString().toInt())
        }
        tvContent.setOnClickListener {
            listener.editMemo(tvId.text.toString().toInt())
        }
    }

}