package com.lk.memobar2.adapters

import android.view.ContextMenu
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.lk.memobar2.R

/**
 * Erstellt von Lena am 26/04/2019.
 */
class AdapterViewHolder(v: View, listener: AdapterActionListener):
    RecyclerView.ViewHolder(v),
    View.OnCreateContextMenuListener {

    val tvId: TextView = v.findViewById(R.id.tv_view_id)
    val tvContent: TextView = v.findViewById(R.id.tv_view_content)
    val toggleActive: CompoundButton = v.findViewById(R.id.toggle_view_active)

    init {
        toggleActive.setOnClickListener {
            listener.changeActiveState(tvId.text.toString().toInt())
        }
        tvContent.setOnClickListener {
            listener.editMemo(tvId.text.toString().toInt())
        }
        tvContent.setOnLongClickListener {
            listener.storeLongClickId(tvId.text.toString().toInt())
            false
        }
        v.setOnCreateContextMenuListener(this)
    }

    override fun onCreateContextMenu(contextMenu: ContextMenu,
                                     view: View,
                                     contextMenuInfo: ContextMenu.ContextMenuInfo?) {
        contextMenu.add(0, R.id.menu_delete_item, 0, R.string.menu_delete)
    }


}