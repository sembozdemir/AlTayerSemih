package com.sembozdemir.altayersemih.ui.detail.addtobag

import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sembozdemir.altayersemih.R
import com.sembozdemir.altayersemih.network.model.Product
import kotlinx.android.synthetic.main.fragment_add_to_bag_dialog.*

class AddToBagDialogFragment : BottomSheetDialogFragment() {

    companion object {

        private const val KEY_PRODUCT = "product"

        fun newInstance(product: Product): AddToBagDialogFragment {
            return AddToBagDialogFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_PRODUCT, product)
                }
            }
        }
    }

    private val product: Product? by lazy { arguments?.getParcelable<Product>(KEY_PRODUCT) }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_to_bag_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(addToBagRecyclerViewConfigs) {
            layoutManager = LinearLayoutManager(activity)
            adapter = ProductConfigsRecyclerAdapter(
                    product?.configurableAttributes?.mapNotNull { configurableAttribute ->
                        configurableAttribute.code?.let { code ->
                            ProductConfigItem(code, configurableAttribute.options)
                        }
                    }.orEmpty()
            )
        }
    }
}