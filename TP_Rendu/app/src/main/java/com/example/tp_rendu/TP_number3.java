package com.example.tp_rendu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static android.graphics.Color.blue;
import static android.graphics.Color.green;
import static android.graphics.Color.red;
import static android.graphics.Color.rgb;

public class TP_number3 extends AppCompatActivity {
    public TextView text;
    private Bitmap img_bp;
    private Bitmap img_bp2;
    private ImageView img;
    private Button contraste1;
    private Button diminueContraste;
    private  Button contrastRGB;
    private Button Histogram;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tp_number3);
        text = (TextView)findViewById(R.id.idTaille_Tp3);
        img = (ImageView)findViewById(R.id.idIMG3);
        contraste1=(Button)findViewById(R.id.id_contrast1);
        diminueContraste =(Button)findViewById(R.id.id_diminuecotraste);
        contrastRGB =(Button)findViewById(R.id.id_rgbcontrast);
        Histogram=(Button)findViewById(R.id.idHisto);
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
        Histogram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),"bouton a reagie",Toast.LENGTH_LONG)
                //.show();
                Intent myIntent = new Intent(TP_number3.this,Main2Activity_tp_numbe4.class);
                startActivity(myIntent);
            }
        });
        contrastRGB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extensionDynamiqueCouleur(img_bp);
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

    /**
     * QUESTION 3 TP 3
     * ’algorithme d’extension de dynamique aux trois canaux RGB s ́epar ́ement
     */
    //calcule l'histogramme du niveau de rouge d'une image
    public int[] redScale(Bitmap im) {
        int[] hist = new int[256];
        for (int i = 0; i < im.getWidth() ; i++) {
            for (int j = 0; j < im.getHeight(); j++) {
                int redLevel = red(im.getPixel(i, j));
                hist[redLevel]++;
            }
        }
        return hist;
    }

    //calcule l'histoggrame du niveau de bleu d'une image
    public int[] blueScale(Bitmap im) {
        int[] hist = new int[256];
        for (int i = 0; i < im.getWidth() ; i++) {
            for (int j = 0; j < im.getHeight() ; j++) {
                int blueLevel = blue(im.getPixel(i, j));
                hist[blueLevel]++;
            }
        }
        return hist;
    }

    //calcule l'histogramme du niveau de vert d'une image
    public int[] greenScale(Bitmap im) {
        int[] hist = new int[256];
        for (int i = 0; i < im.getWidth() ; i++) {
            for (int j = 0; j < im.getHeight(); j++) {
                int greenLevel = green(im.getPixel(i, j));
                hist[greenLevel]++;
            }
        }
        return hist;
    }

    //augmentation du contraste d'une image couleur par extension dynamique
    public void extensionDynamiqueCouleur(Bitmap im) {

        int[] hist1 = redScale(im);
        int[] hist2 = blueScale(im);
        int[] hist3 = greenScale(im);
        int width = im.getWidth();
        int height = im.getHeight();
        int maxr = maxArray(hist1);
        int minr = minArray(hist1);
        int maxg = maxArray(hist2);
        int ming = minArray(hist2);
        int maxb = maxArray(hist3);
        int minb = minArray(hist3);
        int[] LUTr = new int[256];
        int[] LUTg = new int[256];
        int[] LUTb = new int[256];
        for (int n = 0; n < 256; n++) {
            LUTr[n] = (255 * (n - minr)) / (maxr - minr);
            LUTg[n] = (255 * (n - ming)) / (maxg - ming);
            LUTb[n] = (255 * (n - minb)) / (maxb - minb);
        }
        int[] pixels = new int[width * height];
        im.getPixels(pixels, 0, width, 0, 0, width, height);
        int i;
        for (i = 0; i < pixels.length; i++) {
            pixels[i] = rgb(LUTr[red(pixels[i])], LUTg[green(pixels[i])], LUTb[blue(pixels[i])]);
        }
        im.setPixels(pixels, 0, width, 0, 0, width, height);
    }
}
