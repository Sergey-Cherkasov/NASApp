package br.svcdev.nasapp.mvvm.view.adapter

import androidx.recyclerview.widget.RecyclerView

interface IOnStartDragListener {
    fun onStartDrag(viewHolder: RecyclerView.ViewHolder)
}