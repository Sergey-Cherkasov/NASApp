package br.svcdev.nasapp.util.toast

import android.content.Context

interface IToast {
    fun show(context: Context?, s: String?)
}