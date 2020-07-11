package com.miichang.djhorn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import com.miichang.djhorn.databinding.FragmentSettingsDialogBinding

class SettingsDialogFragment : Fragment() {
    companion object {
        fun newInstance(): SettingsDialogFragment = SettingsDialogFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSettingsDialogBinding.inflate(
            inflater,
            container,
            false
        )
        var soundId = 0
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radioButton = group.findViewById<RadioButton>(checkedId)
            soundId = group.indexOfChild(radioButton)
        }

        binding.okButton.setOnClickListener {
            val activity = requireActivity()
            val intent = activity.intent
            intent.putExtra(MainActivity.SOUND_ID, soundId)
            activity.setResult(MainActivity.RESULT, intent)
            activity.finish()
            activity.overridePendingTransition(
                R.anim.abc_fade_in,
                R.anim.abc_fade_out
            )
        }

        binding.licenceButton.setOnClickListener {
            fragmentManager.apply {
                this?.let {
                    val fragmentTransaction = it.beginTransaction()
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.replace(R.id.container, LicenceFragment.newInstance())
                    fragmentTransaction.commit()
                }
            }
        }

        return binding.root
    }
}