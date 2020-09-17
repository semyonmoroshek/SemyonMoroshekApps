package cm.myprojects.autoscreenshort;

import android.graphics.Bitmap;
import android.view.View;

public class ScreenShot {

    public static Bitmap takePrintScreen(View view){
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache(true);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return bitmap;
    }

    public static Bitmap takeScreenHotOfRootView(View v){
        return takeScreenHotOfRootView(v);
    }
}
