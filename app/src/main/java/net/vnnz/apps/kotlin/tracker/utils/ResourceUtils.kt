package net.vnnz.apps.kotlin.tracker.utils

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.util.TypedValue
import net.vnnz.apps.kotlin.tracker.R
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.StringWriter

class ResourceUtils {

    companion object {
        fun getColor(id: Int, context: Context): Int {
            val typedValue = TypedValue()
            val theme = context.getTheme()
            theme.resolveAttribute(id, typedValue, true)
            return typedValue.data
        }

        @Suppress("DEPRECATION")
        fun getColoredIcon(context: Context): Drawable {
            val mDrawable = context.getResources().getDrawable(R.drawable.ic_done)
            mDrawable.colorFilter = PorterDuffColorFilter(getColor(R.attr.colorAccent, context), PorterDuff.Mode.MULTIPLY)
            return mDrawable
        }

        fun readJSONfromRaw(res : Int, context: Context) : String{
            val inputStream = context.getResources().openRawResource(res)
            val writer = StringWriter()
            val buffer = CharArray(1024)
            try {
                val reader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
                var n: Int = reader.read(buffer)
                while (n != -1) {
                    writer.write(buffer, 0, n)
                    n = reader.read(buffer)
                }
            } finally {
                inputStream.close()
            }

            return writer.toString()
        }
    }
}