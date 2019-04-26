package com.lk.memobar2.dialogs

import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.lk.memobar2.R
import com.lk.memobar2.database.MemoEntity
import com.lk.memobar2.main.MainActivity
import com.lk.memobar2.main.MemoViewModel
import java.lang.Exception

/**
 * Erstellt von Lena am 26/04/2019.
 */
class EditDialog: DialogFragment() {

    private lateinit var viewModel: MemoViewModel
    private var memo: MemoEntity? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        viewModel = ViewModelProviders.of(requireActivity()).get(MemoViewModel::class.java)
        memo = arguments?.getSerializable(MemoEntity.MEMO_KEY) as MemoEntity?
        val title = arguments?.getInt(MainActivity.DIALOG_TITLE_RESOURCE) ?: R.string.dialog_edit_title
        if(memo == null) {
            throw Exception("No Memo available for editing!!")
        }
        return buildDialog(title, memo!!.content)
    }

    private fun buildDialog(titleResource: Int, content: String): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val dialogLayout = requireActivity().layoutInflater.inflate(R.layout.dialog_edit, null)
        val editText = dialogLayout.findViewById<EditText>(R.id.et_dialog_edit)
        editText.setText(content, TextView.BufferType.EDITABLE)
        builder.setTitle(titleResource)
        builder.setView(dialogLayout)
        builder.setNegativeButton(R.string.dialog_cancel) { _, _ ->
            dialog.cancel()
        }
        builder.setPositiveButton(R.string.dialog_edit_yes) { _ , _ ->
            val todo = editText.text.toString()
            viewModel.updateContentOfMemo(todo, memo!!)
        }
        return builder.create()
    }

}