package com.dino.library.ui

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetDialogFragment<B : ViewDataBinding>(private val layoutId: Int) :
    BottomSheetDialogFragment() {

    lateinit var binding: B

    var onClickListener: ((position: Int, text: String) -> Unit)? = null

    var onDismissListener: (() -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onDismiss(dialog: DialogInterface) {
        onDismissListener?.invoke()
        super.onDismiss(dialog)
    }

    fun onCloseClick() {
        dismiss()
    }

}