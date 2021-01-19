package br.svcdev.nasapp.mvvm.view.ui.fragment.settings

import android.os.Bundle
import android.view.View
import androidx.preference.CheckBoxPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import br.svcdev.nasapp.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        findPreference<SwitchPreferenceCompat>("dark_mode")?.setOnPreferenceChangeListener { _, _ ->
            activity?.recreate()
            true
        }

    }

}