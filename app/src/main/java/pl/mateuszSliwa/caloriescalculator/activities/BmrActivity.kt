package pl.mateuszSliwa.caloriescalculator.activities

import android.os.Bundle
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.bmr_activity.*
import pl.mateuszSliwa.caloriescalculator.R
import pl.mateuszSliwa.caloriescalculator.calculators.BmrCalculator
import java.lang.NumberFormatException

class BmrActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bmr_activity)

        calculateBmr.setOnClickListener {
            try {
                val id = sexRadioGroup.checkedRadioButtonId
                if (id != -1) {
                    val radio: RadioButton = findViewById(id)
                    val totalCalories = BmrCalculator.calculateBmr(
                        radio.text.toString(),
                        weight.text.toString().toFloat(),
                        height.text.toString().toFloat(),
                        age.text.toString().toInt()
                    )
                    result.text = totalCalories.toString() + " kcal"
                } else {
                    result.text = "Nie wybrałeś płci"
                }
            } catch (e: NumberFormatException) {
                result.text = "Podałeś błędne dane"
            }

        }

    }

}