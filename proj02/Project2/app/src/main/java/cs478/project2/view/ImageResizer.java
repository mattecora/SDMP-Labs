package cs478.project2.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageResizer {

    public static Bitmap scaleImage(Context context, int resource, int newWidth, int newHeight) {
        // Open original bitmap without loading in memory
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap originalBitmap = BitmapFactory.decodeResource(context.getResources(), resource, options);

        // Get original bitmap dimensions
        int originalWidth = options.outWidth;
        int originalHeight = options.outHeight;
        int sampleSize = 1;

        // Compute optimal sample size
        while (originalWidth / (2*sampleSize) >= newWidth && originalHeight / (2*sampleSize) >= newHeight) {
            sampleSize *= 2;
        }

        // Load resized bitmap
        options = new BitmapFactory.Options();
        options.inSampleSize = sampleSize;
        return BitmapFactory.decodeResource(context.getResources(), resource, options);
    }

    private ImageResizer() {}

}
