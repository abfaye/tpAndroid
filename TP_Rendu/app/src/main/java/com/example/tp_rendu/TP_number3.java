package com.example.tp_rendu;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static android.graphics.Color.red;
import static android.graphics.Color.rgb;

public class TP_number3 extends AppCompatActivity {
    public TextView text;
    private Bitmap img_bp;
    private ImageView img;
    private Button contraste1;
    private Button diminueContraste;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tp_number3);
        text = (TextView)findViewById(R.id.idTaille_Tp3);
        img = (ImageView)findViewById(R.id.idIMG3);
        contraste1=(Button)findViewById(R.id.id_contrast1);
        diminueContraste =(Button)findViewById(R.id.id_diminuecotraste);

        BitmapFactory.Options o =new BitmapFactory.Options();
        o.inMutable=true;
        // convertToMutable(img_bp);

        img_bp= BitmapFactory.decodeResource(getResources(), R.drawable.leguimes,o);

        text.setText("Height : "+img_bp.getHeight()+"\n Width : "+img_bp.getWidth());

        diminueContraste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extensionDynamiqueGris(img_bp);
                img.setImageBitmap(img_bp);

            }
        });

        contraste1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diminutionContrasteGris(img_bp);
                img.setImageBitmap(img_bp);

            }
        });
        img.setImageBitmap(img_bp);
    }
    //calcule l'histogramme du niveau de gris d'une image
    public int[] greyScale(Bitmap im) {
        int[] hist = new int[256];
        for (int i = 0; i < im.getWidth() - 1; i++) {
            for (int j = 0; j < im.getHeight() - 1; j++) {
                int greyLevel = red(im.getPixel(i, j));
                hist[greyLevel]++;
            }
        }
        return hist;
    }
    public int minArray(int[] array) {
        int i = 0;
        while (array[i] == 0) {
            i++;
        }
        return i;
    }

    public int maxArray(int[] array) {
        int i = (array.length) - 1;
        while (array[i] == 0) {
            i--;
        }
        return i;
    }

    //l'augementation du contraste pas extention de dynamique
    public void extensionDynamiqueGris(Bitmap im) {
        int[] hist = greyScale(im);
        int width = im.getWidth();
        int height = im.getHeight();
        int max = maxArray(hist);
        int min = minArray(hist);
        int[] LUT = new int[256];
        for (int ng = 0; ng < 256; ng++) {
            LUT[ng] = (255 * (ng - min)) / (max - min);
        }
        int[] pixels = new int[width * height];
        im.getPixels(pixels, 0, width, 0, 0, width, height);
        int i;
        for (i = 0; i < pixels.length; i++) {
            int grey = red(pixels[i]);
            pixels[i]=rgb(LUT[grey],LUT[grey],LUT[grey]);

        }
        im.setPixels(pixels, 0, width, 0, 0, width, height);
    }

    //diminution du contraste d'une image grise
    public void diminutionContrasteGris(Bitmap im) {
        int[] hist = greyScale(im);
        int width = im.getWidth();
        int height = im.getHeight();
        int[] LUT = new int[256];
        int[] newLUT = new int[256];
        int size=0;
        for(int j=0;j<256;j++){
            if(hist[j]>50){
                LUT[size]=hist[j];
                size++;
            }
        }
        for(int z=0;z<size;z++){
            newLUT[z]=LUT[z];
        }
        int[] pixels = new int[width * height];
        im.getPixels(pixels, 0, width, 0, 0, width, height);
        int i;
        for (i = 0; i < pixels.length; i++) {
            int grey=red(pixels[i]);
            int newGrey=newLUT[grey];
            pixels[i]=rgb(newGrey,newGrey,newGrey);

        }
        im.setPixels(pixels, 0, width, 0, 0, width, height);
    }
}
