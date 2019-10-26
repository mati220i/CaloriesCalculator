package pl.mateuszSliwa.caloriescalculator.calculators

class EpocCalculator {
    companion object {
        fun calculateEpoc(training: String, intensity: Int, brm: Float) : Float {
            if (training == "Kardio") {
                when (intensity) {
                    0 -> return 5F
                    1 -> return 15F
                    2 -> return 35F
                    3 -> return 90F
                    4 -> return 130F
                    5 -> return 180F
                }
            } else if (training == "Siłowy") {
                when (intensity) {
                    0 -> return (0.04 * brm).toFloat()
                    1 -> return (0.055 * brm).toFloat()
                    2 -> return (0.07 * brm).toFloat()
                }
            }

            return 0F
        }
    }
}