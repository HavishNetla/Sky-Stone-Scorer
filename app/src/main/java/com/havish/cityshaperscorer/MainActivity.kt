package com.havish.cityshaperscorer

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.support.v7.app.AppCompatActivity
import android.widget.SeekBar
import android.widget.TextView
import android.widget.ToggleButton


class MainActivity : AppCompatActivity() {
    companion object {
        var advantage = 0

        var elevatedSupport = 0
        var elevatedFlag = 0

        var crane1 = 0
        var crane2 = 0
        var crane3 = 0

        var drone = 0

        var wildlife = 0

        var treehouse1 = 0
        var treehouse2 = 0

        var traffic = 0

        var swing = 0

        var elevator1 = 0
        var elevator2 = 0

        var saftey = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scorer)
        var v = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        //==============================================================================================================
        val finalScore: TextView = findViewById(R.id.finalScoreText)
        val advantageScore: TextView = findViewById(R.id.advantageText)
        val toggle: ToggleButton = findViewById(R.id.advantageToggle)
        val elevatedSlider: SeekBar = findViewById(R.id.elevatedSlider)
        val elevatedSliderLabel: TextView = findViewById(R.id.elevatedSliderLabel)
        elevatedSlider.isEnabled = false

        toggle.setOnCheckedChangeListener { _, isChecked ->
            advantage = when (isChecked) {
                true -> 5
                false -> 0
            }

            advantageScore.text = "Advantage: $advantage"
            calculateScore(finalScore)
            buttonVibrate(v)
        }
        //==============================================================================================================
        val elevatedPlacesText: TextView = findViewById(R.id.elevatedPlacesText)
        val elevatedPlacesToggle: ToggleButton = findViewById(R.id.elevatedPlacesToggle)

        elevatedPlacesToggle.setOnCheckedChangeListener { _, isChecked ->
            elevatedSupport = when (isChecked) {
                true -> 20
                false -> 0
            }
            if (!isChecked) {
                elevatedSlider.isEnabled = false
                elevatedFlag = 0
                elevatedSliderLabel.text = "0"

                elevatedSlider.progress = 0
            } else {
                elevatedSlider.isEnabled = true
            }
            elevatedPlacesText.text = "M01 - Elevated Places: ${elevatedSupport + elevatedFlag}"
            calculateScore(finalScore)
            buttonVibrate(v)
        }

        elevatedSlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            var prog = 0
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                elevatedSliderLabel.text = "$progress"
                prog = progress

                elevatedFlag = prog * 15
                elevatedPlacesText.text = "M01 - Elevated Places: ${elevatedSupport + elevatedFlag}"
                calculateScore(finalScore)
                buttonVibrate(v)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })
        //==============================================================================================================
        val craneText: TextView = findViewById(R.id.craneText)
        val craneToggle1: ToggleButton = findViewById(R.id.craneToggle1)
        val craneToggle2: ToggleButton = findViewById(R.id.craneToggle2)
        val craneToggle3: ToggleButton = findViewById(R.id.craneToggle3)

        craneToggle2.isEnabled = false
        craneToggle3.isEnabled = false

        craneToggle1.setOnCheckedChangeListener { _, isChecked ->
            crane1 = when (isChecked) {
                true -> 20
                false -> 0
            }
            if (!isChecked) {
                craneToggle2.isChecked = false
                craneToggle3.isChecked = false

                craneToggle2.isEnabled = false
                craneToggle3.isEnabled = false
            } else {
                craneToggle2.isEnabled = true
            }
            craneText.text = "M02 - Crane: ${crane1 + crane2 + crane3}"
            calculateScore(finalScore)
            buttonVibrate(v)
        }

        craneToggle2.setOnCheckedChangeListener { _, isChecked ->
            crane2 = when (isChecked) {
                true -> 15
                false -> 0
            }

            if (!isChecked) {
                craneToggle3.isChecked = false
                craneToggle3.isEnabled = false
            } else {
                craneToggle3.isEnabled = true
            }
            craneText.text = "M02 - Crane: ${crane1 + crane2 + crane3}"
            calculateScore(finalScore)
            buttonVibrate(v)
        }

        craneToggle3.setOnCheckedChangeListener { _, isChecked ->
            crane3 = when (isChecked) {
                true -> 15
                false -> 0
            }

            craneText.text = "M02 - Crane: ${crane1 + crane2 + crane3}"
            calculateScore(finalScore)
            buttonVibrate(v)
        }
        //==============================================================================================================
        val droneText: TextView = findViewById(R.id.droneText)
        val droneToggle: ToggleButton = findViewById(R.id.droneToggle)

        droneToggle.setOnCheckedChangeListener { _, isChecked ->
            drone = when (isChecked) {
                true -> 10
                false -> 0
            }

            droneText.text = "M03 - Inspection Drone: ${drone}"
            calculateScore(finalScore)
            buttonVibrate(v)
        }
        //==============================================================================================================
        val wildlifeText: TextView = findViewById(R.id.wildlifeText)
        val wildlifeToggle: ToggleButton = findViewById(R.id.wildlifeToggle)

        wildlifeToggle.setOnCheckedChangeListener { _, isChecked ->
            wildlife = when (isChecked) {
                true -> 10
                false -> 0
            }

            wildlifeText.text = "M04 - Design For Wildlife: $wildlife"
            calculateScore(finalScore)
            buttonVibrate(v)
        }
        //==============================================================================================================
        val treehouseText: TextView = findViewById(R.id.treehouseText)
        val treehouseLabel1: TextView = findViewById(R.id.treehouseLabel1)
        val treehouseLabel2: TextView = findViewById(R.id.treehouseLabel2)
        val treehouseSlider1: SeekBar = findViewById(R.id.treehouseSlider)
        val treehouseSlider2: SeekBar = findViewById(R.id.treehouseSlider2)

        var count = 0
        var count1 = 0
        var count2 = 0
        treehouseSlider1.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            var prog = 0
            var hooman = false
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                count1 = progress
                count = count1 + count2

                if (count > 16) {
                    treehouseSlider2.progress -= 1
                }
                treehouseLabel1.text = "$progress"
                prog = progress
                count1 = prog

                treehouse1 = prog * 10
                treehouseText.text = "M05 - Treehouse: ${treehouse1 + treehouse2}"

                calculateScore(finalScore)
                if (hooman) {
                    buttonVibrate(v)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                hooman = true
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                hooman = false
            }
        })
        treehouseSlider2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            var prog = 0
            var hooman = false
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                count2 = progress
                count = count1 + count2

                if (count > 16) {
                    treehouseSlider1.progress -= 1
                }
                treehouseLabel2.text = "$progress"
                prog = progress

                treehouse2 = prog * 15
                treehouseText.text = "M05 - Treehouse: ${treehouse1 + treehouse2}"

                calculateScore(finalScore)
                if (hooman) {
                    buttonVibrate(v)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                hooman = true
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                hooman = false
                if (count > 16) {
                    treehouseSlider1.progress -= 1
                }
            }
        })
        //==============================================================================================================
        val trafficText: TextView = findViewById(R.id.trafficText)
        val trafficToggle: ToggleButton = findViewById(R.id.trafficToggle)

        trafficToggle.setOnCheckedChangeListener { _, isChecked ->
            traffic = when (isChecked) {
                true -> 10
                false -> 0
            }

            trafficText.text = "M06 - Traffic Jam: $traffic"
            calculateScore(finalScore)
            buttonVibrate(v)
        }
        //==============================================================================================================
        val swingText: TextView = findViewById(R.id.swingText)
        val swingToggle: ToggleButton = findViewById(R.id.swingToggle)

        swingToggle.setOnCheckedChangeListener { _, isChecked ->
            swing = when (isChecked) {
                true -> 20
                false -> 0
            }

            swingText.text = "M07 - Swing: $swing"
            calculateScore(finalScore)
            buttonVibrate(v)
        }
        //==============================================================================================================
        val elevatorText: TextView = findViewById(R.id.elevatorText)
        val elevatorToggle1: ToggleButton = findViewById(R.id.elevatorToggle)
        val elevatorToggle2: ToggleButton = findViewById(R.id.elevatorToggle2)

        elevatorToggle1.setOnCheckedChangeListener { _, isChecked ->
            elevator1 = when (isChecked) {
                true -> 15
                false -> 0
            }

            if (isChecked) {
                elevatorToggle2.isChecked = false
            }

            elevatorText.text = "M08 - Elevator: ${elevator1 + elevator2}"
            calculateScore(finalScore)
            buttonVibrate(v)
        }
        elevatorToggle2.setOnCheckedChangeListener { _, isChecked ->
            elevator2 = when (isChecked) {
                true -> 20
                false -> 0
            }

            if (isChecked) {
                elevatorToggle1.isChecked = false
            }

            elevatorText.text = "M08 - Elevator: ${elevator1 + elevator2}"
            calculateScore(finalScore)
            buttonVibrate(v)
        }
        //==============================================================================================================
        val safteyText: TextView = findViewById(R.id.safteyText)
        val safteySliderLabel: TextView = findViewById(R.id.safteyLabel)
        val safteySlider: SeekBar = findViewById(R.id.safteySlider)

        safteySlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            var hooman = false
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {

                saftey = 10 * progress
                safteySliderLabel.text = "$progress"
                safteyText.text = "M09 - Safety Factor: $saftey"

                calculateScore(finalScore)
                if (hooman) {
                    buttonVibrate(v)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                hooman = true
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                hooman = false
                if (count > 16) {
                    treehouseSlider1.progress -= 1
                }
            }
        })
    }

    fun calculateScore(text: TextView) {
        text.text =
            "Score: ${advantage + elevatedFlag + elevatedSupport + crane1 + crane2 + crane3 + drone + wildlife + treehouse1 + treehouse2 + traffic + swing + elevator1 + elevator2 + Mission12.score1 + Mission12.score2}"
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
}
