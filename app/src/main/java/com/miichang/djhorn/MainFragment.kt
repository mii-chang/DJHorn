package com.miichang.djhorn

import android.app.ActivityOptions
import android.graphics.Color
import android.media.SoundPool
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.miichang.djhorn.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.fragment_main.*
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.Size

class MainFragment : Fragment() {
    companion object {
        fun newInstance(): MainFragment = MainFragment()
    }

    private var soundId = 0
    private val soundResource = arrayOf(R.raw.horn, R.raw.sexy, R.raw.jap_come, R.raw.lets_party)
    private var sound: Int? = null
    private var soundPool: SoundPool? = null

    override fun onResume() {
        super.onResume()
        soundId = (activity as MainActivity).soundId
        soundPool?.let {
            sound =
                it.load(requireContext(), soundResource[soundId], 0)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainBinding.inflate(
            inflater,
            container,
            false
        )
        soundPool = SoundPool.Builder().build()
        var isLoaded = false
        soundPool?.let {
            sound =
                it.load(requireContext(), soundResource[soundId], 0)
            it.setOnLoadCompleteListener { soundPool, sampleId, status ->
                isLoaded = true
            }
        }
        binding.button.setOnClickListener {
            soundPool?.let {
                if (isLoaded) {
                    it.play(sound!!, 1.0F, 1.0F, 0, 0, 1F)
                    viewKonfetti.build()
                        .addColors(
                            Color.parseColor("#FF1178"),
                            Color.parseColor("#FFF205"),
                            Color.parseColor("#7cFF01")
                        )
                        .setDirection(0.0, 359.0)
                        .setSpeed(1f, 10f)
                        .setFadeOutEnabled(true)
                        .setTimeToLive(2000L)
                        .addShapes(Shape.Square, Shape.Circle)
                        .addSizes(Size(20))
                        .setPosition(
                            viewKonfetti.x + viewKonfetti.width / 2,
                            viewKonfetti.y + viewKonfetti.height / 2 - 200
                        )
                        .burst(100)
                }
            }

        }

        binding.settingButton.setOnClickListener {
            requireActivity().startActivityForResult(
                SettingsDialogActivity.createIntent(requireContext()),
                MainActivity.REQUEST,
                ActivityOptions.makeSceneTransitionAnimation(requireActivity()).toBundle()
            )
        }
        return binding.root
    }
}