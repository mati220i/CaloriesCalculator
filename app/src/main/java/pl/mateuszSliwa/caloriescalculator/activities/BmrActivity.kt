package pl.mateuszSliwa.caloriescalculator.activities

import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import androidx.core.view.get
import kotlinx.android.synthetic.main.bmr_activity.*
import kotlinx.android.synthetic.main.bmr_activity.height
import kotlinx.android.synthetic.main.bmr_activity.result
import kotlinx.android.synthetic.main.bmr_activity.weight
import pl.mateuszSliwa.caloriescalculator.R
import pl.mateuszSliwa.caloriescalculator.calculators.BmrCalculator
import pl.mateuszSliwa.caloriescalculator.utils.LayoutPreparer
import java.lang.NumberFormatException

class BmrActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bmr_activity)

        LayoutPreparer.setDataOnLayout(this, weight, height, age, sexRadioGroup)

        backBtn.setOnClickListener {
            onBackPressed();
        }

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