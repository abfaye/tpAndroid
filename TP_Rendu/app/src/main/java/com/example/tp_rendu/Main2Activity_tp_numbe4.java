package com.example.tp_rendu;

import androidx.appcompat.app.AppCompatActivity;

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

public class Main2Activity_tp_numbe4 extends AppCompatActivity {
    public TextView text;
    private Bitmap img_bp;
    private Bitmap img_bp2;
    private ImageView img;
    private Button button1;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_tp_numbe4);
        img = (ImageView) findViewById(R.id.img44);
        button1 = (Button) findViewById(R.id.id_histo1);
        button2 = (Button) findViewById(R.id.button2);

        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inMutable = true;
        // convertToMutable(img_bp);

        img_bp = BitmapFactory.decodeResource(getResources(), R.drawable.imjj, o);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                egalisationHistoGris(img_bp);
                img.setImageBitmap(img_bp);

            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                egalisationHistoCouleur(img_bp);
                img.setImageBitmap(img_bp);

            }
        });
    }
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

    //augmentation du contraste d'une image couleur par égalisation d'histogramme
        public void egalisationHistoCouleur(Bitmap im) {
            int[] histR = redScale(im);
            int[] histG = greenScale(im);
            int[] histB = blueScale(im);
            int width = im.getWidth();
            int height = im.getHeight();
            int i;
            int[] histCR = new int[256];
            int[] histCG = new int[256];
            int[] histCB = new int[256];
            int cmptR = 0;
            int cmptG = 0;
            int cmptB = 0;
            for (i = 0; i < 256; i++) {
                cmptR = cmptR + histR[i];
                histCR[i] = cmptR;
                cmptG = cmptG + histG[i];
                histCG[i] = cmptG;
                cmptB = cmptB + histB[i];
                histCB[i] = cmptB;
            }
            int j;
            for (j = 0; j <width; j++) {
                for(int x=0;x<height;x++) {
                    double newRed = ((histCR[red(im.getPixel(j,x))]));
                    double newGreen = ((histCG[green(im.getPixel(j,x))]));
                    double newBlue = ((histCB[blue(im.getPixel(j,x))])) ;
                    im.setPixel(j,x,rgb((int)((newRed*255) /cmptR),(int)((newGreen*255) / cmptG),(int)((newBlue*255))/ cmptB));
                }
            }
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

    //augmentation du contraste d'une image grise par égalisation d'histogramme
    public void egalisationHistoGris(Bitmap im) {
        int[] hist = greyScale(im);
        int width = im.getWidth();
        int height = im.getHeight();
        int i;
        int[] histC = new int[256];
        int cmpt=0;
        for (i = 1; i < 256; i++) {
            cmpt = cmpt + hist[i];
            histC[i] = cmpt;
        }
        int[] pixels = new int[width * height];
        im.getPixels(pixels, 0, width, 0, 0, width, height);
        int j;
        for (j = 0; j < pixels.length; j++) {
            int newGrey =(histC[red(pixels[j])]*255)/cmpt;
            pixels[j] = rgb(newGrey, newGrey, newGrey);
        }
        im.setPixels(pixels, 0, width, 0, 0, width, height);
    }
}
