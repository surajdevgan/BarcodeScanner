package com.devgan.suraj.barcodescanner;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.opengl.Visibility;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    ImageView img;
    TextView txtresult;
    Button scan;
    Uri uri, imageUri;
    Bitmap mbitmap;
    public static final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scan = (findViewById(R.id.scan));
        img = (findViewById(R.id.imgview));
        txtresult = (findViewById(R.id.txtResult));
        scan.setVisibility(View.GONE);

    }


    public void btnScan(View view) {
        BarcodeDetector detector = new BarcodeDetector.Builder(getApplicationContext())
                .setBarcodeFormats(Barcode.ALL_FORMATS)
                .build();
        Frame frame = new Frame.Builder()
                .setBitmap(mbitmap).build();
        SparseArray<Barcode> barcodeSparseArray = detector.detect(frame);
        Barcode result = barcodeSparseArray.valueAt(0);
        txtresult.setText(result.rawValue);


    }

    public void camera(View view) {
        startActivity(new Intent(this,CameraActivity.class));

    }

    public void genrate(View view) {
        startActivity(new Intent(this,GenerateActivity.class));

    }

    public void SelectImg(View view) {

        Intent i = new Intent();
        i.setType("image/jpeg");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i,"Select Qr Code"),PICK_IMAGE);
        txtresult.setText("");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE)
        {
            uri = data.getData();
            try {
                mbitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                img.setImageBitmap(mbitmap);
                scan.setVisibility(View.VISIBLE);
            }
            catch (IOException e)
            {

            }



        }

    }
}
