package com.havish.cityshaperscorer

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView

class Mission12 : Fragment() {
    companion object {
        var score1 = 0
        var score2 = 0
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var v = activity!!.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        val view: View = inflater.inflate(R.layout.fragment_mission12, container, false)
        //val view1: View = inflater.inflate(R.layout.scorer, container, false)

       // val finalScoreText: TextView = view1.findViewById(R.id.finalScoreText)

        val scoreText: TextView = view.findViewById(R.id.scoreText)
        val label: TextView = view.findViewById(R.id.label)
        val label2: TextView = view.findViewById(R.id.label2)
        val seekBar: SeekBar = view.findViewById(R.id.seekBar)
        val seekBar2: SeekBar = view.findViewById(R.id.seekBar2)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            var prog = 0
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                label.text = "$progress"
                prog = progress

                score1 = prog * 10
                scoreText.text = "M12 - Design & Build: ${score1 + score2}"
                buttonVibrate(v)
                //calculateScore(finalScoreText)

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        seekBar2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            var prog = 0
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                label2.text = "$progress"
                prog = progress

                score2 = prog * 5
                scoreText.text = "M12 - Design & Build: ${score1 + score2}"
                buttonVibrate(v)
                //calculateScore(finalScoreText)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        return view
    }

    fun buttonVibrate(v: Vibrator) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(
                VibrationEffect.createOneShot(
                    25, 50
                )
            )
        }
    }

    fun calculateScore(text: TextView) {
        text.text =
            "Score: ${MainActivity.advantage +
                    MainActivity.elevatedFlag +
                    MainActivity.elevatedSupport +
                    MainActivity.crane1 +
                    MainActivity.crane2 +
                    MainActivity.crane3 +
                    MainActivity.drone +
                    MainActivity.wildlife +
                    MainActivity.treehouse1 +
                    MainActivity.treehouse2 +
                    MainActivity.traffic +
                    MainActivity.swing +
                    MainActivity.elevator1 +
                    MainActivity.elevator2 +
                    Mission12.score1 +
                    Mission12.score2
            }"
    }

}