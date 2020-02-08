package pl.mateuszSliwa.caloriescalculator.activities

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Point
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.about_activity.*
import kotlinx.android.synthetic.main.about_activity.backBtn
import kotlinx.android.synthetic.main.about_activity.background
import pl.mateuszSliwa.caloriescalculator.BuildConfig
import pl.mateuszSliwa.caloriescalculator.R
import java.io.BufferedReader
import java.io.InputStreamReader


class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.about_activity)

        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)

        val orientation = this.resources.configuration.orientation

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            background.layoutParams.height = size.y
            background.layoutParams.width = size.x
        } else {
            background.layoutParams.height = size.x
            background.layoutParams.height = size.y
        }

        backBtn.setOnClickListener {
            onBackPressed()
        }

        version.text = BuildConfig.VERSION_NAME

        val log = getChangelog()
        changelog.text = log

        sendPropose.setOnClickListener {
            sendMessage("Propozycja / Opinia", "Wpisz swoją propozycję lub opinię dotyczącą aplikacji")
        }

        reportBug.setOnClickListener {
            sendMessage("Bug", "Opisz swój problem dotyczący aplikacji")
        }

    }

    private fun getChangelog(): String {
        val reader = BufferedReader(InputStreamReader(getAssets().open("changelog.log")))

        return reader.use(BufferedReader::readText)
    }

    private fun sendMessage(subject: String, text: String) {
        val i = Intent(Intent.ACTION_SEND)
        i.type = "message/rfc822"
        i.putExtra(Intent.EXTRA_EMAIL, arrayOf("mateuszsliwa7@wp.pl"))
        i.putExtra(Intent.EXTRA_SUBJECT, subject)
        i.putExtra(Intent.EXTRA_TEXT, text)
        try {
            startActivity(Intent.createChooser(i, "Wyślij wiadomość..."))
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(
                this,
                "Nie masz zainstalowanych żadnych klientow pocztowych",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}