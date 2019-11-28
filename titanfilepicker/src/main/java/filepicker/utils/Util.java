package filepicker.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by VINOD KUMAR on 3/28/2019.
 */
@SuppressWarnings("unused")
public final class Util {

    private Util() {

    }

    public static int scaleUpByte(int byteValue) {
        return Double.valueOf(byteValue / 1024.0).intValue();
    }

    public static int calculateNoOfColumns(Context context, float columnWidthDp) { // For example columnWidthDp=180
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float screenWidthDp = displayMetrics.widthPixels / displayMetrics.density;
        return (int) (screenWidthDp / columnWidthDp + 0.5); // +0.5 for correct rounding to int.
    }
}
