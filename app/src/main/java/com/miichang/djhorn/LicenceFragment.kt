package com.miichang.djhorn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import com.miichang.djhorn.databinding.FragmentLicenceBinding
import com.miichang.djhorn.databinding.FragmentSettingsDialogBinding

class LicenceFragment : Fragment() {
    companion object {
        fun newInstance(): LicenceFragment = LicenceFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLicenceBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }
}