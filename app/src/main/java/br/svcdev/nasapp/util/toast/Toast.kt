package br.svcdev.nasapp.util.toast

import android.content.Context
import android.view.Gravity
import android.widget.Toast

class Toast : IToast {
    override fun show(context: Context?, s: String?) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).apply {
            setGravity(Gravity.BOTTOM, 0, 250)
            show()
        }
    }
}