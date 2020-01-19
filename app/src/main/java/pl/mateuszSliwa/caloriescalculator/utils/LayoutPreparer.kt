package pl.mateuszSliwa.caloriescalculator.utils


import android.content.Context
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.view.forEach

class LayoutPreparer {

    companion object {

        fun setDataOnLayout(clazz: Context, weight: EditText, height: EditText) {
            val sharedPref = clazz.getSharedPreferences("pl.mateuszSliwa.caloriescalculator.prefs", 0)

            weight.setText(sharedPref.getFloat("weight", -1F).toString())
            if (weight.text.toString() == "-1.0") {
                weight.text.clear()
            }

            height.setText(sharedPref.getFloat("height", -1F).toString())
            if (height.text.toString() == "-1.0") {
                height.text.clear()
            }
        }

        fun setDataOnLayout(clazz: Context, weight: EditText, height: EditText, sexGroup: RadioGroup) {
            this.setDataOnLayout(clazz, weight, height)

            val sharedPref = clazz.getSharedPreferences("pl.mateuszSliwa.caloriescalculator.prefs", 0)

            val sex = sharedPref.getString("sex", "")
            sexGroup.forEach {
                val btn = it.findViewById<RadioButton>(it.id)
                if (btn.text.toString() == sex) {
                    sexGroup.check(it.id)
                }
            }
        }

        fun setDataOnLayout(clazz: Context, weight: EditText, height: EditText, age: EditText,
                            sexGroup: RadioGroup) {
            this.setDataOnLayout(clazz, weight, height, sexGroup)

            val sharedPref = clazz.getSharedPreferences("pl.mateuszSliwa.caloriescalculator.prefs", 0)

            age.setText(sharedPref.getInt("age", -1).toString())
            if (age.text.toString() == "-1") {
                age.text.clear()
            }
        }

        fun setDataOnLayout(clazz: Context, weight: EditText, height: EditText, age: EditText,
                            sexGroup: RadioGroup, bodyTypeGroup: RadioGroup) {
            this.setDataOnLayout(clazz, weight, height, age, sexGroup)

            val sharedPref = clazz.getSharedPreferences("pl.mateuszSliwa.caloriescalculator.prefs", 0)

            val body = sharedPref.getString("bodyType", "")
            bodyTypeGroup.forEach {
                val btn = it.findViewById<RadioButton>(it.id)
                if (btn.text.toString() == body) {
                    bodyTypeGroup.check(it.id)
                }
            }
        }

        fun setDataOnLayout(clazz: Context, weight: EditText, height: EditText, waist: EditText,
                            hip: EditText, neck: EditText, sexGroup: RadioGroup) {
            this.setDataOnLayout(clazz, weight, height, sexGroup)

            val sharedPref = clazz.getSharedPreferences("pl.mateuszSliwa.caloriescalculator.prefs", 0)

            waist.setText(sharedPref.getFloat("waist", -1f).toString())
            if (waist.text.toString() == "-1.0") {
                waist.text.clear()
            }

            hip.setText(sharedPref.getFloat("hip", -1f).toString())
            if (hip.text.toString() == "-1.0") {
                hip.text.clear()
            }

            neck.setText(sharedPref.getFloat("neck", -1f).toString())
            if (neck.text.toString() == "-1.0") {
                neck.text.clear()
            }
        }

        fun setDataOnLayout(clazz: Context, weight: EditText, height: EditText, waist: EditText,
                            age: EditText, hip: EditText, neck: EditText, sexGroup: RadioGroup,
                            bodyTypeGroup: RadioGroup) {
            this.setDataOnLayout(clazz, weight, height, waist, hip, neck, sexGroup)

            val sharedPref = clazz.getSharedPreferences("pl.mateuszSliwa.caloriescalculator.prefs", 0)

            age.setText(sharedPref.getInt("age", -1).toString())
            if (age.text.toString() == "-1") {
                age.text.clear()
            }

            val body = sharedPref.getString("bodyType", "")
            bodyTypeGroup.forEach {
                val btn = it.findViewById<RadioButton>(it.id)
                if (btn.text.toString() == body) {
                    bodyTypeGroup.check(it.id)
                }
            }
        }
    }

}

