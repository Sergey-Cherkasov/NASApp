package br.svcdev.nasapp.mvvm.view.ui.fragment.bottomnavigationdrawer

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.svcdev.nasapp.R
import br.svcdev.nasapp.mvvm.model.entity.NasaServerResponseData
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_navigation_drawer_fragment.*
import kotlinx.android.synthetic.main.bottom_sheet_layout.*

class BottomSheepFragment : BottomSheetDialogFragment() {

    companion object {
        fun newInstance(layoutId: Int, serverResponse: NasaServerResponseData? = null) =
            BottomSheepFragment().apply {
                arguments = Bundle().apply {
                    putInt("layoutId", layoutId)
                    putParcelable("serverResponse", serverResponse)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(arguments?.getInt("layoutId")!!, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

// Вариант настройки шрифта для textview заголовка и описания полученного изображения
//        activity?.let {
//            bottom_sheet_description_header.typeface =
//                Typeface.createFromAsset(it.assets, "LinuxLibertineCapitalsItalic.ttf")
//            bottom_sheet_description_header.setTypeface(bottom_sheet_description_header.typeface,
//                Typeface.BOLD)
//            bottom_sheet_description.typeface =
//                Typeface.createFromAsset(it.assets, "LinuxLibertineCapitalsItalic.ttf")
//        }

        when (arguments?.getInt("layoutId")) {
            R.layout.bottom_navigation_drawer_fragment ->
                navigation_view.setNavigationItemSelectedListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.navigation_one -> Toast.makeText(context, "1", Toast.LENGTH_SHORT)
                            .show()
                        R.id.navigation_two -> Toast.makeText(context, "2", Toast.LENGTH_SHORT)
                            .show()
                    }
                    true
                }
            R.layout.bottom_sheet_layout -> {
                bottom_sheet_description_header.text =
                    arguments?.getParcelable<NasaServerResponseData>("serverResponse")?.title!!
                bottom_sheet_description.text =
                    arguments?.getParcelable<NasaServerResponseData>("serverResponse")?.explanation!!
            }
        }

    }
}