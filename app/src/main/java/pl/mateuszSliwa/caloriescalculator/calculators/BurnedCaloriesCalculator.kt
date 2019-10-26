package pl.mateuszSliwa.caloriescalculator.calculators

class BurnedCaloriesCalculator {
    companion object {
        fun calculateBurnedCalories(training: String, intensity: Int, minutes: Int): Float {
            if (training == "Kardio") {
                // minimum burned calories per minute in cardio is 5 kcal
                var MIN_BURNED_CALORIES_PER_MINUTE = 5
                val caloriesPerMinuteWithIntensity = intensity + MIN_BURNED_CALORIES_PER_MINUTE

                return (caloriesPerMinuteWithIntensity * minutes).toFloat()
            } else if (training == "Siłowy") {
                var MIN_BURNED_CALORIES_PER_MINUTE = 7
                val caloriesPerMinuteWithIntensity = intensity + MIN_BURNED_CALORIES_PER_MINUTE

                return (caloriesPerMinuteWithIntensity * minutes).toFloat()
            }
            return 0F
        }
    }
}