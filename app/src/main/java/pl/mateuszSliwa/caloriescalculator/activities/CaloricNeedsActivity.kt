package pl.mateuszSliwa.caloriescalculator.activities

import android.os.Bundle
import android.widget.RadioButton
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.caloric_needs_activity.*
import pl.mateuszSliwa.caloriescalculator.R
import pl.mateuszSliwa.caloriescalculator.calculators.BmrCalculator
import pl.mateuszSliwa.caloriescalculator.calculators.BurnedCaloriesCalculator
import pl.mateuszSliwa.caloriescalculator.calculators.EpocCalculator

class CaloricNeedsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.caloric_needs_activity)

        var cardioIntensityValue = 0
        var strengthIntensityValue = 0

        cardioIntensitySlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(
                seekBar: SeekBar, progress: Int,
                fromUser: Boolean
            ) {
                cardioIntensityValue = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                //Toast.makeText(applicationContext, "seekbar touch started!", Toast.LENGTH_SHORT).show()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                //Toast.makeText(applicationContext, "seekbar touch stopped!", Toast.LENGTH_SHORT).show()
            }
        })

        strengthIntensitySlider.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(
                seekBar: SeekBar, progress: Int,
                fromUser: Boolean
            ) {
                strengthIntensityValue = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                //Toast.makeText(applicationContext, "seekbar touch started!", Toast.LENGTH_SHORT).show()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                //Toast.makeText(applicationContext, "seekbar touch stopped!", Toast.LENGTH_SHORT).show()
            }
        })

        calculateCaloricNeeds.setOnClickListener {
            try {
                var sex: RadioButton? = null
                val id = sexRadioGroup.checkedRadioButtonId

                if (id != -1) {
                    sex = findViewById(id)
                } else {
                    caloricNeedsResultMin.text = "Nie wybrałeś płci"
                }

                var bodyType: RadioButton? = null
                val bodyTypeId = bodyTypeGroup.checkedRadioButtonId

                if (bodyTypeId != -1) {
                    bodyType = findViewById(bodyTypeId)
                } else {
                    caloricNeedsResultMin.text = "Nie wybrano typu budowy"
                }


                val bmr = BmrCalculator.calculateBmr(
                    sex?.text.toString(),
                    weightCN.text.toString().toFloat(),
                    heightCN.text.toString().toFloat(),
                    ageCN.text.toString().toInt()
                )

                val caloriesFromCardio = BurnedCaloriesCalculator.calculateBurnedCalories(
                    "Kardio",
                    cardioIntensityValue,
                    cardioMinutes.text.toString().toInt()
                )
                var epocFromCardio = 0

                if (cardioMinutes.text.toString().toInt() > 0) {
                    epocFromCardio =
                        EpocCalculator.calculateEpoc("Kardio", cardioIntensityValue, bmr).toInt()
                }

                val totalCaloriesFromCardio =
                    (caloriesFromCardio + epocFromCardio) * numberOfTrainingsCardio.text.toString().toFloat()


                val caloriesFromStrength = BurnedCaloriesCalculator.calculateBurnedCalories(
                    "Siłowy",
                    strengthIntensityValue,
                    strengthMinutes.text.toString().toInt()
                )
                var epocFromStrength = 0

                if (strengthMinutes.text.toString().toInt() > 0) {
                    epocFromStrength =
                        EpocCalculator.calculateEpoc("Siłowy", strengthIntensityValue, bmr).toInt()
                }

                val totalCaloriesFromStrength =
                    (caloriesFromStrength + epocFromStrength) * numberOfTrainingsStrength.text.toString().toFloat()

                val totalBurnedCaloriesFromTrainings =
                    totalCaloriesFromCardio + totalCaloriesFromStrength

                val additionalCaloriesInOneDayFromTrainings = totalBurnedCaloriesFromTrainings / 7

                var neatMin = 0
                var neatMax = 0

                val bd = bodyType?.text.toString()

                when (bd) {
                    "Endomorfik" -> {
                        neatMin = 200
                        neatMax = 400
                    }
                    "Ektomorfik" -> {
                        neatMin = 700
                        neatMax = 900
                    }
                    "Mezomorfik" -> {
                        neatMin = 400
                        neatMax = 500
                    }
                }

                val caloriesWithNeatMin = bmr + additionalCaloriesInOneDayFromTrainings + neatMin
                val caloriesWithNeatMax = bmr + additionalCaloriesInOneDayFromTrainings + neatMax

                val totalCaloriesMin = caloriesWithNeatMin + (0.1 * caloriesWithNeatMin)
                val totalCaloriesMax = caloriesWithNeatMax + (0.1 * caloriesWithNeatMax)

                this.caloricNeedsResultMin.text = "Min: " + totalCaloriesMin.toInt() + " kcal"
                this.caloricNeedsResultMax.text = "Max: " + totalCaloriesMax.toInt() + " kcal"
            } catch (e: NumberFormatException) {
                caloricNeedsResultMin.text = "Wprowadzono złe dane"
            }
        }

    }

}