package com.example.tp_rendu;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TP_number2 extends AppCompatActivity {
    public TextView text;
    private Bitmap img_bp;
    private ImageView img;
    private Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tp_number2);
        img = (ImageView)findViewById(R.id.idImagetp2);
        button2=(Button)findViewById(R.id.id_button2tp2);
        text=(TextView)findViewById(R.id.idTaille_Tp1);
        /**
         * Utilisation de BitmapFactory.Option permet de creer une option
         *
         */

        BitmapFactory.Options o =new BitmapFactory.Options();
        o.inMutable=true;
        img_bp= BitmapFactory.decodeResource(getResources(), R.drawable.leguimes,o);

        text.setText("Height : "+img_bp.getHeight()+"\n Width : "+img_bp.getWidth());


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorize_Without_Red(img_bp);
                img.setImageBitmap(img_bp);

            }
        });

        img.setImageBitmap(img_bp);
    }
    public void colorize_Without_Red(Bitmap img){
        int width = img.getWidth();
        int height = img.getHeight();

        int[] pixels = new int[width * height];

        img.getPixels(pixels, 0, width, 0, 0, width, height);

        for(int i=0;i<width*height;i++){
            int color=pixels[i];
            int r = Color.red(color);
            int g = Color.green(color);
            int b = Color.blue(color);
            float[] hsv = new float[3];
            Color.RGBToHSV(r, g, b, hsv);
            if((hsv[0] > 20) && (hsv[0] < 340)){
                int grv=(int) (r * 0.3 + g * 0.59 +  b * 0.11);
                pixels[i] = Color.rgb(grv,grv,grv);
            }
        }
        img.setPixels(pixels, 0, width, 0, 0, width, height);

    }
}

