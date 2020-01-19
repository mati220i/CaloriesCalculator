package pl.mateuszSliwa.caloriescalculator.activities

import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import kotlinx.android.synthetic.main.profile_activity.*
import kotlinx.android.synthetic.main.profile_activity.bodyTypeGroup
import kotlinx.android.synthetic.main.profile_activity.sexRadioGroup
import pl.mateuszSliwa.caloriescalculator.R
import pl.mateuszSliwa.caloriescalculator.utils.LayoutPreparer

class ProfileActivity : AppCompatActivity() {

    val PREFS_FILENAME = "pl.mateuszSliwa.caloriescalculator.prefs"

    private lateinit var sex : RadioButton
    private lateinit var bodyType: RadioButton

    private lateinit var sexVal : String
    private var weight : Float = 0.0f
    private var height : Float = 0.0f
    private var age : Int = 0
    private var waistS : Float = 0.0f
    private var hipS : Float = 0.0f
    private var neckS : Float = 0.0f
    private lateinit var bd : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_activity)

        LayoutPreparer.setDataOnLayout(this, weightCN, heightCN, waist, ageCN, hip, neck, sexRadioGroup, bodyTypeGroup)

        backBtn.setOnClickListener {
            onBackPressed()
        }


        clear.setOnClickListener {
            getData()

            sexRadioGroup.clearCheck()
            bodyTypeGroup.clearCheck()
            weightCN.setText("")
            heightCN.setText("")
            ageCN.setText("")
            waist.setText("")
            hip.setText("")
            neck.setText("")

            saveData("", -1F, -1F, -1, "", -1F, -1F, -1F)

            Toast.makeText(this, "Wyczyszczono dane", Toast.LENGTH_SHORT).show()
        }

        update.setOnClickListener {
            getData()

            saveData(sexVal, weight, height, age, bd, waistS, hipS, neckS)
            Toast.makeText(this, "Zaktualizowano dane", Toast.LENGTH_SHORT).show()
        }

    }

    private fun getData() {
        sex = findViewById(sexRadioGroup.checkedRadioButtonId)
        bodyType = findViewById(bodyTypeGroup.checkedRadioButtonId)

        sexVal = sex?.text.toString()
        weight = weightCN.text.toString().toFloat()
        age = ageCN.text.toString().toInt()
        height = heightCN.text.toString().toFloat()
        bd = bodyType?.text.toString()
        waistS = waist.text.toString().toFloat()
        hipS = hip.text.toString().toFloat()
        neckS = neck.text.toString().toFloat()
    }

    private fun saveData(sex : String, weight : Float, height : Float, age : Int, bodyType : String,
                         waist: Float, hip: Float, neck: Float) {
        val sharedPref = this.getSharedPreferences(PREFS_FILENAME, 0)

        with (sharedPref.edit()) {
            putString("sex", sex)
            putFloat("weight", weight)
            putFloat("height", height)
            putInt("age", age)
            putString("bodyType", bodyType)
            putFloat("waist", waist)
            putFloat("hip", hip)
            putFloat("neck", neck)
            commit()
        }
    }

}