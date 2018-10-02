package com.sembozdemir.altayersemih.ui.detail.addtobag

import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sembozdemir.altayersemih.R

class AddToBagDialogFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_to_bag_dialog, container, false)
    }
}