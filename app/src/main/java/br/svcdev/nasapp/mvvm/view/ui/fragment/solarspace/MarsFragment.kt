package br.svcdev.nasapp.mvvm.view.ui.fragment.solarspace

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.svcdev.nasapp.R

class MarsFragment :Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.mars_fragment, container, false)
    }
}