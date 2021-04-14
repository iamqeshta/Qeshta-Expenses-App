package com.iamqeshta.qeshtaexpensesroomdb.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.iamqeshta.qeshtaexpensesroomdb.databinding.FragmentBottomSheetDialogBinding

class BottomSheetDialog : BottomSheetDialogFragment() {
    private lateinit var bottomSheetDialogBinding: FragmentBottomSheetDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = FragmentBottomSheetDialogBinding.inflate(inflater, container, false)
        bottomSheetDialogBinding = view

        bottomSheetDialogBinding.addBtn.setOnClickListener {
            // Add Data in db
            dismiss()
        }
        return bottomSheetDialogBinding.root
    }
}