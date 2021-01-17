package br.svcdev.nasapp.mvvm.view.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.svcdev.nasapp.R
import br.svcdev.nasapp.mvvm.view.ui.fragment.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }
}