package com.lk.memobar2.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.lk.memobar2.R
import com.lk.memobar2.database.MemoEntity
import com.lk.memobar2.main.*
import java.lang.Exception

/**
 * Erstellt von Lena am 26/04/2019.
 */
class EditDialog: DialogFragment() {

    private lateinit var viewModel: MemoViewModel
    private var memo: MemoEntity? = null

    private val TAG = "EditDialog"

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        viewModel = ViewModelProviders.of(requireActivity()).get(MemoViewModel::class.java)
        memo = arguments?.getSerializable(Utils.MEMO_KEY) as MemoEntity?
        val title = arguments?.getInt(Utils.DIALOG_TITLE_RESOURCE) ?: R.string.dialog_edit_title
        if(memo == null) {
            throw Exception("No Memo available for editing!!")
        }
        return buildDialog(title, memo!!.content)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // show input method
        val editText = view.findViewById<EditText>(R.id.et_dialog_edit)

        if(editText.requestFocus()) {
            Log.v(TAG, "focus successfully requested")
            val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    // TODO different design: bigger, fullscreen, sharper corner, bigger input??
    private fun buildDialog(titleResource: Int, content: String): Dialog {
        val dialogLayout = requireActivity().layoutInflater.inflate(R.layout.dialog_edit, null)
        val editText = dialogLayout.findViewById<EditText>(R.id.et_dialog_edit)
        editText.setText(content, TextView.BufferType.EDITABLE)

        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle(titleResource)
        builder.setView(dialogLayout)
        builder.setNegativeButton(R.string.dialog_cancel) { _, _ ->
            dialog?.cancel()
        }
        builder.setPositiveButton(R.string.dialog_edit_yes) { _ , _ ->
            val todo = editText.text.toString()
            viewModel.updateContentOfMemo(todo, memo!!)
        }
        val dialog =  builder.create()
        if(editText.requestFocus()) {
            Log.v(TAG, "build: focus successfully requested")
            val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
        }
        return dialog
    }

}