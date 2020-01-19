package com.example.tp_rendu;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.audiofx.DynamicsProcessing;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TP_number1 extends AppCompatActivity {
    public TextView text;
    private Bitmap img_bp;
    private ImageView img;
    private Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tp_number1);
        img = (ImageView)findViewById(R.id.idImagetp1);
        button2=(Button)findViewById(R.id.id_button2tp2);
        text = (TextView)findViewById(R.id.id_txt_TP1);
        //Log.i("TAG","message informatif");
        /**
         * Utilisation de BitmapFactory.Option permet de creer une option
         *
         */

        BitmapFactory.Options o =new BitmapFactory.Options();
        o.inMutable=true;
        // convertToMutable(img_bp);

        img_bp= BitmapFactory.decodeResource(getResources(), R.drawable.leguimes,o);
        //img_bp= img_bp.copy(Bitmap.Config.ARGB_8888, true);

        text.setText("Height : "+img_bp.getHeight()+"\n Width : "+img_bp.getWidth());


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              GreyImg_Pixels(img_bp);
                img.setImageBitmap(img_bp);

            }
        });

        //GreyImg(img_bp);
        img.setImageBitmap(img_bp);
    }

    public Bitmap toGray(Bitmap bmp){
        int color;
        int R, G,B;
        for(int i=0;i<bmp.getHeight();i++){
            for(int j=0;j<bmp.getWidth();j++){
                color=bmp.getPixel(i,j);

                 R = Color.red(color);
                 G = Color.green(color);
                 B = Color.blue(color);
                // calculate averagB
                int avg = (R+G+B) /3;

                //replace RGB value with avg

                bmp.setPixel(i,j,Color.rgb(avg,avg,avg));
            }
        }
    return bmp;
    }

    public  void GreyImg_Pixels(Bitmap img) {
        int width = img.getWidth();
        int height = img.getHeight();

        int[] pixels = new int[width * height];

        img.getPixels(pixels, 0, width, 0, 0, width, height);

        for (int i = 0; i < height*width; i++) {
            int grey = pixels[i];

            int red =   Color.red(grey);
            int green = Color.green(grey);
            int blue =  Color.blue(grey);

            grey = (int) (red * 0.3 + green * 0.59 +  blue * 0.11);

            pixels[i] = Color.rgb(grey,grey,grey);


        }
        img.setPixels(pixels, 0, width, 0, 0, width, height);
    }
}
