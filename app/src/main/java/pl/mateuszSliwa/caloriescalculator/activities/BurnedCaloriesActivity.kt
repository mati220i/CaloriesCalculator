package pl.mateuszSliwa.caloriescalculator.activities

import android.content.res.Configuration
import android.graphics.Point
import android.os.Bundle
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.burned_calories_activity.*
import pl.mateuszSliwa.caloriescalculator.R
import pl.mateuszSliwa.caloriescalculator.calculators.BurnedCaloriesCalculator
import java.lang.NumberFormatException
import android.widget.SeekBar


class BurnedCaloriesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.burned_calories_activity)

        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)

        val orientation = this.resources.configuration.orientation

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            background.layoutParams.height = size.y
            background.layoutParams.width = size.x
        } else {
            background.layoutParams.height = size.x
            background.layoutParams.height = size.y
        }


        var intensityValue = 0

        backBtn.setOnClickListener {
            onBackPressed();
        }

        cardio.setOnClickListener {
            intensitySlider.max = 5
        }

        strength.setOnClickListener {
            intensitySlider.max = 2
        }

        intensitySlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, progress: Int,
                                           fromUser: Boolean) {
                intensityValue = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                //Toast.makeText(applicationContext, "seekbar touch started!", Toast.LENGTH_SHORT).show()
            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                //Toast.makeText(applicationContext, "seekbar touch stopped!", Toast.LENGTH_SHORT).show()
            }
        })

        calculateBurnedCaloriesBtn.setOnClickListener {
            try {

                val id = training.checkedRadioButtonId
                if (id != -1) {
                    val radio: RadioButton = findViewById(id)
                    val totalCalories = BurnedCaloriesCalculator.calculateBurnedCalories(
                        radio.text.toString(),
                        intensityValue,
                        minutes.text.toString().toInt()
                    )
                    burnedCalories.text = totalCalories.toString() + " kcal"
                } else {
                    burnedCalories.text = "Nie wybrałeś rodzaju treningu"
                }

            } catch (e: NumberFormatException) {
                burnedCalories.text = "Podałeś błędne dane"
            }
        }
    }

}