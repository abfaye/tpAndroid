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
        //img_bp= img_bp.copy(Bitmap.Config.ARGB_8888, true);

        text.setText("Height : "+img_bp.getHeight()+"\n Width : "+img_bp.getWidth());


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GreyImg(img_bp);
                // colorize(img_bp);
                colorize_Without_Red(img_bp);
                img.setImageBitmap(img_bp);

            }
        });

        //GreyImg(img_bp);
        img.setImageBitmap(img_bp);
    }
    public void colorize_Without_Red(Bitmap bmp){
        int width = bmp.getWidth();
        int height=bmp.getHeight();
        int a,r,g,b;
        int color=0;
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                color=bmp.getPixel(i,j);
                a = Color.alpha(color);
                r = Color.red(color);
                g = Color.green(color);
                b = Color.blue(color);
                int grv=(r+g+b)/3;
                float[] hsv = new float[3];
                Color.RGBToHSV(r, g, b, hsv);
                int aleatoir=(int)Math.random()*(350 - 0);
                color = Color.HSVToColor(grv, hsv);
                if((hsv[0] > 20) && (hsv[0] < 340)){
                    bmp.setPixel(i , j , Color.rgb(grv,grv,grv));
                }
            }
        }
    }
}
