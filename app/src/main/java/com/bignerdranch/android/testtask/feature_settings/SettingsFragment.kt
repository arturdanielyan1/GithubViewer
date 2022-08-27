package com.bignerdranch.android.testtask.feature_settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.bignerdranch.android.testtask.R
import com.bignerdranch.android.testtask.core.data.getAppMode
import com.bignerdranch.android.testtask.core.data.saveAppMode
import com.bignerdranch.android.testtask.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {
        super.onViewCreated(v, savedInstanceState)

        binding.themeRg.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId) {
                R.id.light_mode_radio -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    saveAppMode(requireActivity(), AppCompatDelegate.MODE_NIGHT_NO)
                }
                R.id.dark_mode_radio -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    saveAppMode(requireActivity(), AppCompatDelegate.MODE_NIGHT_YES)
                }
                R.id.system_mode_radio -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    saveAppMode(requireActivity(), AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                }
            }
        }

        binding.themeRg.check(
            when(getAppMode(requireActivity())) {
                AppCompatDelegate.MODE_NIGHT_NO -> R.id.light_mode_radio
                AppCompatDelegate.MODE_NIGHT_YES -> R.id.dark_mode_radio
                else -> R.id.system_mode_radio
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}