package ie.setu.placemark.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import ie.setu.placemark.R
import ie.setu.placemark.databinding.ActivityPlacemarkBinding
import ie.setu.placemark.main.MainApp
import ie.setu.placemark.models.PlacemarkModel
import timber.log.Timber
import timber.log.Timber.i

class PlacemarkActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlacemarkBinding
    var placemark = PlacemarkModel()
    var app: MainApp? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlacemarkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.topAppBar.title = title
        setSupportActionBar(binding.topAppBar)

        app = application as MainApp
        i("Placemark Activity started...")
        binding.btnAdd.setOnClickListener() {
            placemark.title = binding.placemarkTitle.text.toString()
            placemark.description = binding.placemarkDescription.text.toString()

            if (placemark.title.isNotEmpty()) {
                // Add the new Placemark to the array list
                app!!.placemarksArrayList.add(PlacemarkModel(placemark.title, placemark.description))

                for(i in app!!.placemarksArrayList.indices){
                    i("Placemark[$i]:${this.app!!.placemarksArrayList[i]}")
                }
            }
            else {
                Snackbar.make(it,"Please Enter a title", Snackbar.LENGTH_LONG).show()
            }
        }

        // Button for adding placemark
        binding.btnAdd.setOnClickListener() {
            placemark.title = binding.placemarkTitle.text.toString()
            placemark.description = binding.placemarkDescription.text.toString()
            if (placemark.title.isNotEmpty()) {
                app!!.placemarksArrayList.add(placemark.copy())
                i("add Button Pressed: ${placemark}")
                for (i in app!!.placemarksArrayList.indices) {
                    i("Placemark[$i]:${this.app!!.placemarksArrayList[i]}")
                }
                setResult(RESULT_OK)
                finish()
            }
            else {
                Snackbar.make(it,"Please Enter a title", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_placemark, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> {
                setResult(RESULT_CANCELED)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}