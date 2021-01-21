package br.svcdev.nasapp.mvvm.view.ui.fragment.solarspace

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.svcdev.nasapp.R
import br.svcdev.nasapp.mvvm.model.entity.NearEarthObject
import br.svcdev.nasapp.mvvm.model.entity.NeoAsteroidServerResponseData
import kotlinx.android.synthetic.main.asteroids_rv_item.view.*

class AsteroidsRVAdapter(private val asteroids: List<NearEarthObject>) :
    RecyclerView.Adapter<AsteroidsRVAdapter.ViewHolder>() {

//    private var asteroids: List<NearEarthObject> = listOf()
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }

    inner class ViewHolder(containerView: View) :
        RecyclerView.ViewHolder(containerView) {
        fun bind(asteroid: NearEarthObject) {
            with(itemView) {
                name_asteroid.text = asteroid.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.asteroids_rv_item, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(asteroids[position])

    override fun getItemCount(): Int = asteroids.size
}