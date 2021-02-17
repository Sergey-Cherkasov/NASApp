package br.svcdev.nasapp.mvvm.view.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MotionEventCompat
import androidx.recyclerview.widget.RecyclerView
import br.svcdev.nasapp.R
import br.svcdev.nasapp.mvvm.model.entity.NearEarthObject
import kotlinx.android.synthetic.main.asteroids_rv_item.view.*
import kotlinx.android.synthetic.main.main_fragment.*

class AsteroidsRVAdapter(
    private val data: MutableList<NearEarthObject>,
    private val dragListener: IOnStartDragListener,
    private val itemClickListener: IAsteroidsRVItemClickListener
) : RecyclerView.Adapter<AsteroidsRVAdapter.ViewHolder>(), IItemTouchHelperAdapter {

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

    inner class ViewHolder(containerView: View) :
        RecyclerView.ViewHolder(containerView), IItemTouchHelperViewHolder {
        @SuppressLint("ClickableViewAccessibility")
        fun bind(asteroid: NearEarthObject) {
            val regex = """\(""".toRegex()
            with(itemView) {
                val position = regex.find(asteroid.name)?.range?.first ?: 0

                val textSpannable = SpannableString(asteroid.name)
                textSpannable.setSpan(
                    ForegroundColorSpan(Color.BLUE),
                    0,
                    position - 1,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )

                asteroid_name.text = textSpannable
                asteroid_name.setOnClickListener {
                    itemClickListener.onClick(
                        asteroid_name.text.substring(0 until (position - 1))
                    )
                }

                asteroid_drag_hamburger.setOnTouchListener { _, event ->
                    if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                        dragListener.onStartDrag(this@ViewHolder)
                    }
                    false
                }
            }
        }

        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY)
        }

        override fun onItemClear() {
            itemView.setBackgroundColor(0)
        }
    }
}