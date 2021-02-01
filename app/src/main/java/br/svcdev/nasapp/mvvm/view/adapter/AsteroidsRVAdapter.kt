package br.svcdev.nasapp.mvvm.view.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MotionEventCompat
import androidx.recyclerview.widget.RecyclerView
import br.svcdev.nasapp.R
import br.svcdev.nasapp.mvvm.model.entity.NearEarthObject
import br.svcdev.nasapp.util.toast.Toast
import kotlinx.android.synthetic.main.asteroids_rv_item.view.*

class AsteroidsRVAdapter(
    private val data: MutableList<NearEarthObject>,
    private val dragListener: IOnStartDragListener
) : RecyclerView.Adapter<AsteroidsRVAdapter.ViewHolder>(), IItemTouchHelperAdapter {

    inner class ViewHolder(containerView: View) :
        RecyclerView.ViewHolder(containerView), IItemTouchHelperViewHolder {
        @SuppressLint("ClickableViewAccessibility")
        fun bind(asteroid: NearEarthObject) {
            with(itemView) {
                asteroid_name.text = asteroid.name
                asteroid_drag_hamburger.setOnTouchListener { _, event ->
                    if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                        dragListener.onStartDrag(this@ViewHolder)
                    }
                    false
                }
                setOnClickListener { Toast().show(context, position.toString()) }
            }
        }

        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY)
        }

        override fun onItemClear() {
            itemView.setBackgroundColor(0)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.asteroids_rv_item, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(data[position])

    override fun getItemCount(): Int = data.size

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        data.removeAt(fromPosition).apply {
            data.add(if (toPosition > fromPosition) toPosition - 1 else toPosition, this)
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }
}