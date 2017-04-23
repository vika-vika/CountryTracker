package net.vnnz.apps.kotlin.tracker.utils

import android.content.Context
import android.graphics.Bitmap
import net.vnnz.apps.kotlin.tracker.pojo.ListItem
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import android.graphics.BitmapFactory
import android.graphics.Color
import net.vnnz.apps.kotlin.tracker.R


class ImageUtils {

    companion object {

        fun loadBitmapFromFile(path: String)  {

        }

        fun fillImageMap(context: Context, path: String) : Bitmap {

            val options = BitmapFactory.Options()
            options.inMutable = true

            var myBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.europe_map, options)

            val allpixels = IntArray(myBitmap.getHeight() * myBitmap.getWidth())

            myBitmap.getPixels(allpixels, 0, myBitmap.getWidth(), 0, 0, myBitmap.getWidth(), myBitmap.getHeight())

            for (i in 0..allpixels.size - 1) {
                if (allpixels[i] == Color.parseColor("#f1b9d6")) {
                    allpixels[i] = Color.RED
                }
            }

            myBitmap.setPixels(allpixels, 0, myBitmap.getWidth(), 0, 0, myBitmap.getWidth(), myBitmap.getHeight())
            return myBitmap
        }
    }

}