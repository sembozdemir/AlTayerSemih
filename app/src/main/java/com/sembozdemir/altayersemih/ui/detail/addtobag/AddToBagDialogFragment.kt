package com.sembozdemir.altayersemih.ui.detail.addtobag

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.sembozdemir.altayersemih.R
import com.sembozdemir.altayersemih.network.model.OptionsItem
import com.sembozdemir.altayersemih.network.model.Product
import kotlinx.android.synthetic.main.fragment_add_to_bag_dialog.*

class AddToBagDialogFragment : BottomSheetDialogFragment() {

    companion object {

        private const val KEY_PRODUCT = "product"
        private const val KEY_SELECTED_SIZE_LABEL = "selectedSizeLabel"

        fun newInstance(product: Product, selectedSizeLabel: String?): AddToBagDialogFragment {
            return AddToBagDialogFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_PRODUCT, product)
                    putString(KEY_SELECTED_SIZE_LABEL, selectedSizeLabel)
                }
            }
        }
    }

    interface OnOptionItemSelectedListener {
        fun onOptionItemSelected(productConfigItem: ProductConfigItem, optionsItem: OptionsItem)
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
        val selectedSizeLabel = arguments?.getString(KEY_SELECTED_SIZE_LABEL)

        addToBagButton.isEnabled = !selectedSizeLabel.isNullOrEmpty()
        setupRecyclerViewConfigs(product, selectedSizeLabel)

        addToBagButton.setOnClickListener {
            Toast.makeText(activity, R.string.product_added_to_bag, Toast.LENGTH_LONG).show()
            // TODO: implement a real 'add to bag' implementation
        }
    }

    private fun setupRecyclerViewConfigs(product: Product?, selectedSizeLabel: String?) {
        with(addToBagRecyclerViewConfigs) {
            layoutManager = LinearLayoutManager(activity)
            adapter = ProductConfigsRecyclerAdapter(
                    createProductConfigItems(product, selectedSizeLabel)
            ).apply {
                onOptionSelected { productConfigItem, optionsItem ->
                    onOptionItemSelectedListener?.onOptionItemSelected(productConfigItem,
                            optionsItem)
                }
            }
        }
    }

    private fun createProductConfigItems(product: Product?, selectedSizeLabel: String?): List<ProductConfigItem> {
        return product?.configurableAttributes?.mapNotNull { configurableAttribute ->
            configurableAttribute.code?.let { code ->
                ProductConfigItem(code,
                        product.color.orEmpty(),
                        selectedSizeLabel,
                        product.sameColorSiblings,
                        configurableAttribute.options)
            }
        }.orEmpty()
    }

    fun setProduct(product: Product, selectedSizeLabel: String?) {
        addToBagButton.isEnabled = !selectedSizeLabel.isNullOrEmpty()
        setupRecyclerViewConfigs(product, selectedSizeLabel)
    }
}