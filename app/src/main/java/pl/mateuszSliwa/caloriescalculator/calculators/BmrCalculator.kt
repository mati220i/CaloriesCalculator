package pl.mateuszSliwa.caloriescalculator.calculators

class BmrCalculator {

    companion object {
        fun calculateBmr(sex: String, weight: Float, height: Float, age: Int): Float {
            if (sex == "Mężczyzna") {
                return ((9.99 * weight) + (6.25 * height) - (4.92 * age) + 5).toFloat()
            } else if (sex == "Kobieta") {
                return ((9.99 * weight) + (6.25 * height) - (4.92 * age) - 161).toFloat()
            } else {
                return 0F
            }
        }
    }

}