package com.sembozdemir.altayersemih.ui.detail.addtobag

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sembozdemir.altayersemih.R
import com.sembozdemir.altayersemih.network.model.OptionsItem
import com.sembozdemir.altayersemih.network.model.Product
import kotlinx.android.synthetic.main.fragment_add_to_bag_dialog.*

class AddToBagDialogFragment : BottomSheetDialogFragment() {

    companion object {

        const val DIALOG_TAG = "addToBagDialogFragment"

        private const val KEY_PRODUCT = "product"

        fun newInstance(product: Product): AddToBagDialogFragment {
            return AddToBagDialogFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_PRODUCT, product)
                }
            }
        }
    }

    interface OnOptionItemSelectedListener {
        fun onOptionItemSelected(otherProductConfigItem: ProductConfigItem?, optionsItem: OptionsItem)
    }

    private var onOptionItemSelectedListener: OnOptionItemSelectedListener? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        onOptionItemSelectedListener = context as? OnOptionItemSelectedListener

        if (onOptionItemSelectedListener == null) {
            throw IllegalStateException(
                    "${context.toString()} must implement OnOptionItemSelectedListener"
            )
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_to_bag_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val product = arguments?.getParcelable<Product>(KEY_PRODUCT)
        setupRecyclerViewConfigs(product)
    }

    private fun setupRecyclerViewConfigs(product: Product?) {
        with(addToBagRecyclerViewConfigs) {
            layoutManager = LinearLayoutManager(activity)
            adapter = ProductConfigsRecyclerAdapter(createProductConfigItems(product)).apply {
                onOptionSelected { otherProductConfigItem, optionsItem ->
                    onOptionItemSelectedListener?.onOptionItemSelected(otherProductConfigItem,
                            optionsItem)
                }
            }
        }
    }

    private fun createProductConfigItems(product: Product?): List<ProductConfigItem> {
        return product?.configurableAttributes?.mapNotNull { configurableAttribute ->
            configurableAttribute.code?.let { code ->
                ProductConfigItem(code,
                        product.color.orEmpty(),
                        product.sizeCode.orEmpty(),
                        configurableAttribute.options)
            }
        }.orEmpty()
    }

    fun setProduct(product: Product) {
        setupRecyclerViewConfigs(product)
    }
}