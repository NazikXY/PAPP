package com.example.v2.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.v2.R
import com.example.v2.storage
import kotlinx.android.synthetic.main.fragment_fill_item.*
import kotlinx.android.synthetic.main.fragment_my_dialog.view.*

class MyDialog : DialogFragment() {
    companion object {

        const val TAG = "SimpleDialog"

        private const val KEY_TITLE = "KEY_TITLE"
        private const val KEY_SUBTITLE = "KEY_SUBTITLE"
        private const val KEY_POSITION = "KEY_POSITION"
        private var key_position:Int? = null

        fun newInstance(title: String, position: Int): MyDialog {
            val args = Bundle()
            args.putString(KEY_TITLE, title)
            args.putInt(KEY_POSITION, position)
            val fragment = MyDialog()
            fragment.arguments = args
            return fragment
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fill_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        setupClickListeners(view)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    private fun setupView(view: View) {
        view.tvTitle.text = arguments?.getString(KEY_TITLE)
        key_position = arguments?.getInt(KEY_POSITION)
        etPrice.hint = storage.orderQueue[storage.current_order].order[key_position!!].price.toString()
        etCount.hint = storage.orderQueue[storage.current_order].order[key_position!!].count.toString()

    }

    private fun setupClickListeners(view: View) {
        view.btnPositive.setOnClickListener {
            if(etPrice.text.isNotEmpty()){
                storage.orderQueue[storage.current_order].order[key_position!!].price =
                    etPrice.text.toString().toDouble()
            }
            if(etCount.text.isNotEmpty()){
                storage.orderQueue[storage.current_order].order[key_position!!].totalCount =
                    etCount.text.toString().toDouble()
            }

            dismiss()
        }
        view.btnNegative.setOnClickListener {
            // TODO: Do some task here
            dismiss()
        }
    }

}