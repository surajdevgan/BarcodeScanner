package com.devgan.suraj.barcodescanner;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.vision.barcode.Barcode;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class GenerateActivity extends AppCompatActivity {
    EditText text;
    ImageView imgcode;
    String text2Qr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate);
        text = (findViewById(R.id.edt));
        imgcode = (findViewById(R.id.itscode));
    }

    public void btngenerate(View view) {
        text2Qr = text.getText().toString().trim();
        MultiFormatWriter formatWriter = new MultiFormatWriter();

        try {
            BitMatrix matrix = formatWriter.encode(text2Qr, BarcodeFormat.QR_CODE,200,200);
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(matrix);
            imgcode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }

    }
}
