package pl.mateuszSliwa.caloriescalculator.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import pl.mateuszSliwa.caloriescalculator.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        profileBtn.setOnClickListener {
            val profileIntent = Intent(this, ProfileActivity::class.java)
            startActivity(profileIntent)
        }

        bmiBtn.setOnClickListener {
            val bmiIntent = Intent(this, BmiActivity::class.java)
            startActivity(bmiIntent)
        }

        bmrBtn.setOnClickListener {
            val bmrIntent = Intent(this, BmrActivity::class.java)
            startActivity(bmrIntent)
        }

        burnedCaloriesBtn.setOnClickListener {
            val burnedCaloriesIntent = Intent(this, BurnedCaloriesActivity::class.java)
            startActivity(burnedCaloriesIntent)
        }

        caloricNeeds.setOnClickListener {
            val caloricNeedsIntent = Intent(this, CaloricNeedsActivity::class.java)
            startActivity(caloricNeedsIntent)
        }

    }
}
