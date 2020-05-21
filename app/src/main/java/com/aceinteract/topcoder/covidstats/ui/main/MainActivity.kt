package com.aceinteract.topcoder.covidstats.ui.main

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.FileProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.aceinteract.topcoder.covidstats.R
import com.aceinteract.topcoder.covidstats.ui.base.BaseActivity
import com.aceinteract.topcoder.covidstats.util.showSnackBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        val navController = findNavController(R.id.frame_container)

        setupActionBarWithNavController(
            navController,
            AppBarConfiguration.Builder(
                R.id.total_stats_dest,
                R.id.region_stats_dest,
                R.id.covid_map_dest
            ).build()
        )
        bottom_nav.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.frame_container).navigateUp()
                || super.onSupportNavigateUp()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_share -> {
            val view = window.decorView.rootView
            val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            view.draw(canvas)
            saveBitmap(bitmap)?.let { shareImage(it) }
            false
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun saveBitmap(bitmap: Bitmap): Uri? {
        val folder = File(cacheDir, "images")
        return try {
            folder.mkdirs()
            val file = File(folder, "shared_image.png")

            val stream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
            stream.flush()
            stream.close()
            FileProvider.getUriForFile(
                this,
                "com.aceinteract.topcoder.covidstats.fileprovider",
                file
            )
        } catch (e: IOException) {
            showSnackBar(
                window.decorView.rootView,
                e.message ?: "Error saving shot.",
                true,
                Snackbar.LENGTH_LONG
            )
            null
        }
    }

    private fun shareImage(uri: Uri) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_STREAM, uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            type = "image/png"
        }
        startActivity(intent)
    }
}
