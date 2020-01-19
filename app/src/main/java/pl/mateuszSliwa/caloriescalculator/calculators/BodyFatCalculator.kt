package pl.mateuszSliwa.caloriescalculator.calculators

import java.lang.Math.log10
import kotlin.math.log10

class BodyFatCalculator {

    companion object {
        fun calculateBodyFatPercentage(sex: String, waist: Float, neck: Float, hip: Float, height: Float): Float {
            if (sex == "Kobieta") {
                return ((495 / (1.29579 - 0.35004 * log10(waist + hip - neck) + 0.22100 * log10(height))) - 450).toFloat()
            } else if (sex == "Mężczyzna") {
                return ((495 / (1.0324  - 0.19077 * log10(waist - neck) + 0.15456 * log10(height))) - 450).toFloat()
            }
            return 0F
        }
    }

}