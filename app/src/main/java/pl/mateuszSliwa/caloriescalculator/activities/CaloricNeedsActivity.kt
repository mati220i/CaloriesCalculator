package pl.mateuszSliwa.caloriescalculator.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.caloric_needs_activity.*
import pl.mateuszSliwa.caloriescalculator.R
import pl.mateuszSliwa.caloriescalculator.calculators.BmrCalculator
import pl.mateuszSliwa.caloriescalculator.calculators.BurnedCaloriesCalculator
import pl.mateuszSliwa.caloriescalculator.calculators.EpocCalculator
import pl.mateuszSliwa.caloriescalculator.utils.LayoutPreparer

class CaloricNeedsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.caloric_needs_activity)

        LayoutPreparer.setDataOnLayout(this, weightCN, heightCN, ageCN, sexRadioGroup, bodyTypeGroup)

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

        cadrioNumberMinus.setOnClickListener {
            var value: Int

            try {
                value = Integer.valueOf(numberOfTrainingsCardio.text.toString())
            } catch (e : NumberFormatException) {
                value = 0
            }

            if (value <= 0) {
                value = 0
            } else {
                value--
            }

            numberOfTrainingsCardio.setText(value.toString())

        }

        cadrioNumberPlus.setOnClickListener {
            var value: Int

            try {
                value = Integer.valueOf(numberOfTrainingsCardio.text.toString())
            } catch (e : NumberFormatException) {
                value = 0
            }

            ++value
            numberOfTrainingsCardio.setText(value.toString())
        }

        strengthNumberMinus.setOnClickListener {
            var value: Int

            try {
                value = Integer.valueOf(numberOfTrainingsStrength.text.toString())
            } catch (e : NumberFormatException) {
                value = 0
            }

            if (value <= 0) {
                value = 0
            } else {
                value--
            }

            numberOfTrainingsStrength.setText(value.toString())

        }

        strengthNumberPlus.setOnClickListener {
            var value: Int

            try {
                value = Integer.valueOf(numberOfTrainingsStrength.text.toString())
            } catch (e : NumberFormatException) {
                value = 0
            }

            ++value
            numberOfTrainingsStrength.setText(value.toString())
        }

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


                val sexVal = sex?.text.toString()
                val weight = weightCN.text.toString().toFloat()
                val height = heightCN.text.toString().toFloat()
                val age = ageCN.text.toString().toInt()

                val bmr = BmrCalculator.calculateBmr(
                    sexVal,
                    weight,
                    height,
                    age
                )

                val cardioMinutesOfTraining = try { cardioMinutes.text.toString().toInt() } catch (e: NumberFormatException) { 0 }
                val numberOfTrainingsCardioVal = try { numberOfTrainingsCardio.text.toString().toFloat() } catch (e: NumberFormatException) { 0F }

                val strengthMinutesOfTraining = try { strengthMinutes.text.toString().toInt() } catch (e: NumberFormatException) { 0 }
                val numberOfTrainingsStrengthVal = try { numberOfTrainingsStrength.text.toString().toFloat() } catch (e: NumberFormatException) { 0F }

                val caloriesFromCardio = BurnedCaloriesCalculator.calculateBurnedCalories(
                    "Kardio",
                    cardioIntensityValue,
                    cardioMinutesOfTraining
                )
                var epocFromCardio = 0

                if (cardioMinutesOfTraining > 0) {
                    epocFromCardio =
                        EpocCalculator.calculateEpoc("Kardio", cardioIntensityValue, bmr).toInt()
                }

                val totalCaloriesFromCardio =
                    (caloriesFromCardio + epocFromCardio) * numberOfTrainingsCardioVal


                val caloriesFromStrength = BurnedCaloriesCalculator.calculateBurnedCalories(
                    "Siłowy",
                    strengthIntensityValue,
                    strengthMinutesOfTraining
                )
                var epocFromStrength = 0

                if (strengthMinutesOfTraining > 0) {
                    epocFromStrength =
                        EpocCalculator.calculateEpoc("Siłowy", strengthIntensityValue, bmr).toInt()
                }

                val totalCaloriesFromStrength =
                    (caloriesFromStrength + epocFromStrength) * numberOfTrainingsStrengthVal

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

                shareData.visibility = View.VISIBLE
            } catch (e: NumberFormatException) {
                caloricNeedsResultMin.text = "Wprowadzono złe dane"
                shareData.visibility = View.INVISIBLE
            }
        }

        shareData.setOnClickListener {

            val shareDataIntent = Intent()
            shareDataIntent.action = Intent.ACTION_SEND
            shareDataIntent.putExtra(Intent.EXTRA_TEXT, "Zapotrzebowanie kaloryczne: \n" + caloricNeedsResultMin.text + "\n" + caloricNeedsResultMax.text)
            shareDataIntent.type = "text/plain"

            startActivity(Intent.createChooser(shareDataIntent, "Wybierz aplikację"))
        }

    }

}