package br.svcdev.nasapp.mvvm.view.adapter

interface IItemTouchHelperAdapter {
    fun onItemMove(fromPosition: Int, toPosition: Int)
    fun onItemDismiss(position: Int)
}