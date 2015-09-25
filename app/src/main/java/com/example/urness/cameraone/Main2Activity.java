/*author: T. Urness
    *this activity is responsible for viwing the image saved by the camera
 */

package com.example.urness.cameraone;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main2Activity extends AppCompatActivity {
    private final String TAG = "Main2Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //load the image from file
        File file = new File(Environment.getExternalStorageDirectory(),
                "MyPhoto.jpg");
        try {
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(file));
            //the image taken in portrat mode needs to be rotated 90 degrees
            Bitmap b2 = RotateBitmap(b,90);
            ImageView img=(ImageView)findViewById(R.id.imageView1);
            img.setImageBitmap(b2);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    /*rotate a bitmap */
    public static Bitmap RotateBitmap(Bitmap source, float angle)
    {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }
}
