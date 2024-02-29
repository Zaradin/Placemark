package ie.setu.placemark.main

import android.app.Application
import ie.setu.placemark.models.PlacemarkMemStore
import ie.setu.placemark.models.PlacemarkModel
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    // array list of placemarks

    // old way
    // val placemarksArrayList = ArrayList<PlacemarkModel>()

    // with persistane interface
    val placemarks = PlacemarkMemStore()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("Placemark started")
    }
}