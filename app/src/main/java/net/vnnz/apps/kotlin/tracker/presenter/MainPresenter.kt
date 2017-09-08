package net.vnnz.apps.kotlin.tracker.presenter

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.view.View
import net.vnnz.apps.kotlin.tracker.utils.ImageUtils
import net.vnnz.apps.kotlin.tracker.view.MainView
import java.io.File

class MainPresenter {

    lateinit var mainView: MainView;

    fun bindView(_view: MainView) {
        mainView = _view

        mainView.getFloatingButton().setOnClickListener(View.OnClickListener { view ->
            mainView.startListActivity();
        })
    }

    val root: String = Environment.getExternalStorageDirectory().absolutePath + "/maps/";

    fun shareImage() {
        var file: File = File (root + "/map_europe.png");

        val share = Intent(Intent.ACTION_SEND)
        share.type = "image/png"
        share.putExtra(Intent.EXTRA_STREAM, Uri.parse(file.absolutePath))
        val shareIntent = Intent.createChooser(share, "Share Image")
        mainView.startActivity(shareIntent)
    }

    fun updateMaps() {
        var file: File = File (root + "/map_europe.png");

        if (file.exists()) {
            val bit = BitmapFactory.decodeFile(file.absolutePath)
            mainView.updateMap(bit);
        }
    }

    fun setMap(context: Context) {
        var file: File = File (root + "/map_europe.png")
        val bit = if (file.exists())
                BitmapFactory.decodeFile(file.absolutePath)
                else  ImageUtils.getBitmapFromAsset(context, "test_map.png");

        mainView.updateMap(bit);
    }
}