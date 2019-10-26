package pl.mateuszSliwa.caloriescalculator.calculators

import kotlin.math.pow

class BmiCalculator {

    companion object {
        fun calculateBMI(height: Float, weight: Float): Float {
            return weight / (height / 100).pow(2)
        }

        fun getBmiResult(bmiNumber: Float): String {
            if (bmiNumber < 16) {
                return "Wygłodzenie"
            } else if (bmiNumber >= 16 && bmiNumber < 17) {
                return "Wychudzenie"
            } else if (bmiNumber >= 17 && bmiNumber < 18.5) {
              return "Niedowaga"
            } else if (bmiNumber >= 18.5 && bmiNumber < 25) {
                return "Waga prawidłowa"
            } else if (bmiNumber >= 25 && bmiNumber < 30) {
                return "Nadwaga"
            } else if (bmiNumber >= 30 && bmiNumber < 35) {
                return "I Stopień Otyłości"
            } else if (bmiNumber >= 35 && bmiNumber < 40) {
                return "II Stopień otyłości"
            } else if (bmiNumber >= 40) {
                return "Otyłość skrajna"
            }

            return "Błąd"
        }
    }

}