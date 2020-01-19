package pl.mateuszSliwa.caloriescalculator.activities

import android.os.Bundle
import android.widget.RadioButton
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.body_fat_activity.*
import kotlinx.android.synthetic.main.body_fat_activity.backBtn
import kotlinx.android.synthetic.main.body_fat_activity.sexRadioGroup
import pl.mateuszSliwa.caloriescalculator.R
import pl.mateuszSliwa.caloriescalculator.calculators.BodyFatCalculator
import pl.mateuszSliwa.caloriescalculator.utils.LayoutPreparer

class BodyFatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.body_fat_activity)

        LayoutPreparer.setDataOnLayout(this, weight, height, waist, hip, neck, sexRadioGroup)

        backBtn.setOnClickListener {
            onBackPressed()
        }

        calculate.setOnClickListener {
            try {
                val id = sexRadioGroup.checkedRadioButtonId
                if (id != -1) {
                    val radio: RadioButton = findViewById(id)

                    val bodyFat = BodyFatCalculator.calculateBodyFatPercentage(
                        radio.text.toString(),
                        waist.text.toString().toFloat(),
                        neck.text.toString().toFloat(),
                        hip.text.toString().toFloat(),
                        height.text.toString().toFloat()
                    )
                    bodyFatPercentage.text = bodyFat.toString() + " %"

                    val weightValue = weight.text.toString().toFloat()
                    val fatMassValue = (bodyFat / 100) * weightValue
                    fatMass.text = fatMassValue.toString() + " kg"
                    leanMass.text = (weightValue - fatMassValue).toString() + " kg"

                } else {
                    bodyFatPercentage.text = "Nie wybrałeś płci"
                }
            } catch (e: NumberFormatException) {
                bodyFatPercentage.text = "Podałeś błędne dane"
            }
            scroll.fullScroll(ScrollView.FOCUS_UP)
            scroll.fullScroll(ScrollView.FOCUS_UP)

        }


    }


}