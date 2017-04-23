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

class ImageUtils {

    companion object {

        fun loadBitmapFromFile(path: String)  {

        }

        fun getVisitedColors(items: Array<ListItem>)  = items./*filter {it.isSelected}.*/map {Color.parseColor(it.color)}

        fun fillImageMap(context: Context, items: Array<ListItem>) : Bitmap {

            val options = BitmapFactory.Options()
            options.inMutable = true

            var myBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.test_map, options)

            val allpixels = IntArray(myBitmap.height * myBitmap.width)

            myBitmap.getPixels(allpixels, 0, myBitmap.width, 0, 0, myBitmap.width, myBitmap.height)

            val visitedColors : List<Int>  = getVisitedColors(items)
            var foo = 0;
            //Color.p
            Log.e("TAG", visitedColors.toString())
            Log.e("TAG", Color.parseColor("#23b04b").toString())
            Log.e("TAG", (Color.parseColor("#23b04b") in visitedColors).toString())
            for (i in 0..allpixels.size - 1) {
                if (allpixels[i] != foo) {
                    Log.e("TAG", allpixels[i].toString())
                }
                if (allpixels[i] in visitedColors) {
                    if (allpixels[i] != foo) {
                        Log.e("TAG", "changing")
                    }
                    allpixels[i] = Color.RED
                }
                foo = allpixels[i]
            }

            myBitmap.setPixels(allpixels, 0, myBitmap.getWidth(), 0, 0, myBitmap.getWidth(), myBitmap.getHeight())
            return myBitmap
        }


        fun saveImage(bitmap : Bitmap, mapName: String) {
            val root: String = Environment.getExternalStorageDirectory().getAbsolutePath();
            val myDir: File = File(root + "/maps");
            myDir.mkdirs();

            val filename:String = "map_$mapName.png";
            var file:File = File (myDir, filename)
            if (file.exists()) file.delete()

            try {
                var out : FileOutputStream = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
                out.flush();
                out.close();
            } catch (e : Exception) {
                Log.e("TAG", "file saving exception")
                e.printStackTrace();
            }
        }
    }

}