package br.svcdev.nasapp.mvvm.view.ui.fragment.solarspace

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.svcdev.nasapp.R
import br.svcdev.nasapp.mvvm.model.entity.NearEarthObject
import br.svcdev.nasapp.mvvm.model.entity.NeoAsteroidData
import br.svcdev.nasapp.mvvm.viewmodel.AsteroidsFragmentViewModel
import br.svcdev.nasapp.util.toast.Toast
import kotlinx.android.synthetic.main.asteroids_fragment.*

class AsteroidsFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(this).get(AsteroidsFragmentViewModel::class.java)
    }
    private val toast = Toast()

    private var serverResponseData: MutableList<NearEarthObject> = mutableListOf()
    private lateinit var adapter: AsteroidsRVAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner, { renderData(it) })
    }

    private fun renderData(data: NeoAsteroidData) {
        when (data) {
            is NeoAsteroidData.Success -> {
                asteroid_progress_bar.visibility = ProgressBar.GONE
                serverResponseData.clear()
                serverResponseData.addAll(data.serverResponseData.objects)
                adapter = AsteroidsRVAdapter(serverResponseData)
                asteroid_recycler_view.adapter = adapter
            }
            is NeoAsteroidData.Loading -> {
                asteroid_progress_bar.visibility = ProgressBar.VISIBLE
            }
            is NeoAsteroidData.Error -> {
                toast.show(context, data.error.message)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.asteroids_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        asteroid_recycler_view.layoutManager = LinearLayoutManager(context)
        asteroid_recycler_view.setOnScrollChangeListener { _, _, dy, _, _ ->
            asteroid_header.isSelected = asteroid_recycler_view.canScrollVertically(-1)
        }
    }
}