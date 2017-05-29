package net.vnnz.apps.kotlin.tracker.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Environment
import android.util.Log
import net.vnnz.apps.kotlin.tracker.R
import net.vnnz.apps.kotlin.tracker.pojo.ListItem
import java.io.File
import java.io.FileOutputStream
import android.content.res.AssetManager
import java.io.IOException
import java.io.InputStream


class ImageUtils {

    companion object {

        fun loadBitmapFromFile(path: String)  {

        }

        fun getVisitedColors(items: Array<ListItem>)  = items./*filter {it.isSelected}.*/map {Color.parseColor(it.color)}

        fun fillImageMap(context: Context, items: Array<ListItem>?) : Bitmap {

            val options = BitmapFactory.Options()
            options.inMutable = true

            val bitmap = getBitmapFromAsset(context, "test_map.png");
            var myBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);

            if (items == null) {
               return myBitmap;
            }

            val allpixels = IntArray(myBitmap.height * myBitmap.width)

            myBitmap.getPixels(allpixels, 0, myBitmap.width, 0, 0, myBitmap.width, myBitmap.height)

            val visitedColors : List<Int>  = getVisitedColors(items!!)

            for (i in 0..allpixels.size - 1) {

                if (allpixels[i] in visitedColors) {
                    //  Log.e("TAG", "changing")
                    allpixels[i] = Color.RED
                }
            }

            myBitmap.setPixels(allpixels, 0, myBitmap.getWidth(), 0, 0, myBitmap.getWidth(), myBitmap.getHeight())
            return myBitmap
        }


        private fun getBitmapFromAsset(context:Context, strName: String): Bitmap {

            val assetManager = context.assets
            var istr: InputStream? = null
            try {
                istr = assetManager.open(strName)
            } catch (e: IOException) {
                e.printStackTrace()
            }

            val bitmap = BitmapFactory.decodeStream(istr)
            return bitmap
        }


        fun saveImage(bitmap : Bitmap, mapName: String) {
            val root: String = Environment.getExternalStorageDirectory().absolutePath
            val myDir: File = File(root + "/maps")
            myDir.mkdirs()

            val filename:String = "map_$mapName.png"
            var file:File = File (myDir, filename)
            if (file.exists()) file.delete()

            try {
                var out : FileOutputStream = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, out)
                out.flush()
                out.close()
            } catch (e : Exception) {
                Log.e("TAG", "file saving exception")
                e.printStackTrace();
            }
        }
    }

}