/*author: T. Urness
    this camera demo is based largely off of the following tutorial:
    http://www.androidinterview.com/android-camera-tutorial-example/
 */

package com.example.urness.cameraone;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    final String TAG = "APP";
    static int TAKE_PIC =1;
    Uri outPutfileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*called when camera button is clicked*/
    public void CameraClick(View v) {
        Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(Environment.getExternalStorageDirectory(),
                "MyPhoto.jpg");
        outPutfileUri = Uri.fromFile(file);
        //launch camera
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outPutfileUri);
        startActivityForResult(intent, TAKE_PIC);
    }

    /*called when view image button is clicked*/
    public void ViewImage(View v) {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

    public void EmailImage(View v){
        String addresses[] = {"timothy.urness@drake.edu"};
        composeEmail(addresses, "testing");
    }

    public void composeEmail(String[] addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        //intent.setType("application/image");
        File pic = new File(Environment.getExternalStorageDirectory(), "MyPhoto.jpg");
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(pic));
        startActivity(intent);
    }

    @Override
    /*callback when camera intent returns*/
    protected void onActivityResult(int requestCode, int resultCode,Intent data)
    {
        if (requestCode == TAKE_PIC && resultCode==RESULT_OK){
            Toast.makeText(this, outPutfileUri.toString(), Toast.LENGTH_LONG).show();
        }
    }

}
