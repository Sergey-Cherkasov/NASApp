package br.svcdev.nasapp.mvvm.view.ui.fragment.main

import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.svcdev.nasapp.R
import br.svcdev.nasapp.mvvm.model.entity.NasaServerResponseData
import br.svcdev.nasapp.mvvm.model.entity.PictureOfTheDayData
import br.svcdev.nasapp.mvvm.view.ui.activity.MainActivity
import br.svcdev.nasapp.mvvm.view.ui.fragment.bottomnavigationdrawer.BottomSheepFragment
import br.svcdev.nasapp.mvvm.view.ui.fragment.settings.SettingsFragment
import br.svcdev.nasapp.mvvm.view.ui.fragment.solarspace.SolarSpaceFragment
import br.svcdev.nasapp.mvvm.view.ui.fragment.webview.WebViewFragment
import br.svcdev.nasapp.mvvm.viewmodel.MainFragmentViewModel
import br.svcdev.nasapp.util.toast.*
import coil.api.load
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.bottom_sheet_layout.*
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
        private var isMain = true
    }

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private val viewModel: MainFragmentViewModel by lazy {
        ViewModelProvider(this).get(MainFragmentViewModel::class.java)
    }
    private var serverResponseData: NasaServerResponseData? = null
    private val toast: IToast = Toast()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner, {
            renderData(it)
        })
        viewModel.getProgressState().observe(viewLifecycleOwner, { isLoading ->
            if (isLoading) {
                showProgress()
            } else {
                hideProgress()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        input_layout.setEndIconOnClickListener {
            openFragment(WebViewFragment
                .newInstance("https://en.wikipedia.org/wiki/${et_wikipedia.text.toString()}"))
        }
        image_view.setOnClickListener {
            activity?.let {
                BottomSheepFragment.newInstance(R.layout.bottom_sheet_layout, serverResponseData)
                    .show(it.supportFragmentManager, "IMG_DESCRIPTION")
            }
        }
        et_wikipedia.setOnEditorActionListener { _, _, _ -> openFragment(WebViewFragment
            .newInstance("https://en.wikipedia.org/wiki/${et_wikipedia.text.toString()}"))
            true
        }
        setBottomAppBar(view)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_app_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.app_bar_solar_system -> openFragment(SolarSpaceFragment())
            R.id.app_bar_favorites -> toast.show(context, "Favorite")
            R.id.app_bar_settings -> openFragment(SettingsFragment())
            android.R.id.home -> activity?.let {
                BottomSheepFragment.newInstance(R.layout.bottom_navigation_drawer_fragment).show(
                    it.supportFragmentManager, "tag"
                )
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun renderData(data: PictureOfTheDayData) {
        when (data) {
            is PictureOfTheDayData.Success -> {
                serverResponseData = data.serverResponseData
                val url = serverResponseData?.url
                if (url.isNullOrEmpty()) {
                    //showError("Сообщение, что ссылка пустая")
                    toast.show(context, "Link is empty")
                } else {
                    //showSuccess()
                    image_view.load(url) {
                        lifecycle(viewLifecycleOwner)
                        error(R.drawable.ic_load_error)
                        placeholder(R.drawable.ic_no_photo)
                    }
                }
            }
            is PictureOfTheDayData.Loading -> {
                //showLoading()
            }
            is PictureOfTheDayData.Error -> {
                //showError(data.error.message)
                toast.show(context, data.error.message)
            }
        }
    }

    private fun setBottomAppBar(v: View) {
        val context = activity as MainActivity
        context.setSupportActionBar(v.findViewById(R.id.bottom_app_bar))
        setHasOptionsMenu(true)
        fab.setOnClickListener {
            if (isMain) {
                isMain = false
                bottom_app_bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                bottom_app_bar.navigationIcon = null
                bottom_app_bar.replaceMenu(R.menu.menu_bottom_app_bar_other_screen)
                fab.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_back))
            } else {
                isMain = true
                bottom_app_bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                bottom_app_bar.navigationIcon =
                    ContextCompat.getDrawable(context, R.drawable.ic_menu)
                bottom_app_bar.replaceMenu(R.menu.menu_bottom_app_bar)
                fab.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_add))
            }
        }
    }

    private fun openFragment(fragment: Fragment) {
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.container, fragment)
            ?.addToBackStack(null)
            ?.commit()
    }

    private fun hideProgress() {
        progress_bar.visibility = ProgressBar.INVISIBLE
    }

    private fun showProgress() {
        progress_bar.visibility = ProgressBar.VISIBLE
    }

}