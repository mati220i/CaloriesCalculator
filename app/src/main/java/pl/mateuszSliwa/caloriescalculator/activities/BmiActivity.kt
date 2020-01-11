package pl.mateuszSliwa.caloriescalculator.activities

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.bmi_activity.*
import pl.mateuszSliwa.caloriescalculator.R
import pl.mateuszSliwa.caloriescalculator.calculators.BmiCalculator
import pl.mateuszSliwa.caloriescalculator.utils.LayoutPreparer
import java.lang.NumberFormatException

class BmiActivity : AppCompatActivity() {

    val PREFS_FILENAME = "pl.mateuszSliwa.caloriescalculator.prefs"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bmi_activity)

        LayoutPreparer.setDataOnLayout(this, weight, height)

        backBtn.setOnClickListener {
            onBackPressed();
        }

        calculateBtn.setOnClickListener {
            try {
                val number = BmiCalculator.calculateBMI(
                    height.text.toString().toFloat(),
                    weight.text.toString().toFloat()
                )
                bmiNumber.text = number.toString()
                result.text = BmiCalculator.getBmiResult(number)
            } catch (e: NumberFormatException) {
                result.text = "Podałeś błędne dane"
            }
        }
    }

}