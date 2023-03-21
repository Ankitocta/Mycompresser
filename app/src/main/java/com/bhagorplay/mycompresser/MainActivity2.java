package com.bhagorplay.mycompresser;



import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class MainActivity2 extends AppCompatActivity {
String image;
    ImageView back,share;
 ImageView photoView;
    Uri url;
    TextView size11,name;
    String size1;
Button button2,r;
AdView  mAdView;
    ArrayList<Uri>new1=new ArrayList<> ();
    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main2 );

        back=findViewById ( R.id.backimage );
        photoView=findViewById ( R.id.photoview );
        button2=findViewById ( R.id.button3 );
r=findViewById ( R.id.button );
        r.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                try {
                    String download_link="https://play.google.com/store/apps/details?id=com.bhagorplay.mycompresser";
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(download_link));
                    startActivity(myIntent);
                }
                catch (ActivityNotFoundException e) {
                    Toast.makeText( MainActivity2.this, "No application can handle this request."
                            + " Please install a webbrowser",  Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        } );

        size11=findViewById ( R.id.gr );
        name=findViewById ( R.id.textView12);
        new1 = (ArrayList<Uri>) getIntent().getSerializableExtra( "uridata" );
        share=findViewById ( R.id.shareimage );
        String uro=getIntent ().getStringExtra ( "uri" );
        url= Uri.parse ( uro );
        name.setText ("File name " +uro );

        back.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                finish ();
            }
        } );
        photoView.setImageURI ( url );
        share.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k = new Intent( Intent.ACTION_SEND );
                k.setType( "video/mp4" );

                k.putExtra( Intent.EXTRA_STREAM,  url   );

                startActivity( k );

                Toast.makeText(   MainActivity2.this, "file share starting", Toast.LENGTH_SHORT ).show();
            }
        } );
        try {
            Cursor cursor= getContentResolver ().query ( Uri.parse (  uro ),null,null,null ,null);

            int size=cursor.getColumnIndex ( OpenableColumns.SIZE );

            cursor.moveToFirst ();

            float m = 0;
            String vv="kb";

            int v= Integer.parseInt ( Long.toString ( cursor.getLong ( size ) ) );
            if (v>=1024){
                m= Integer.valueOf (  v/1024);
                vv="kb";}
            size11.setText ( "output file SIZE  "+m+" Kb" );
        }
      catch (Exception E){}
        SharedPreferences h;
        h=getBaseContext ().getSharedPreferences ( "der101",MODE_PRIVATE );
        long g=h.getLong ( "String" , 1);
        if (System.currentTimeMillis ()>g+60000*30){
        new Handler ().postDelayed ( new Runnable () {
            @Override
            public void run() {
                mAdView = findViewById(R.id.adView21);
                AdRequest adRequest = new AdRequest.Builder().build();
                mAdView.loadAd(adRequest);
                mAdView.setAdListener(new AdListener () {
                    @Override
                    public void onAdLoaded() {
                        super.onAdLoaded ();
                        Toast.makeText ( MainActivity2.this, "adl", Toast.LENGTH_SHORT ).show ();
                    }

                    @Override
                    public void onAdFailedToLoad(LoadAdError adError) {
                        super.onAdFailedToLoad ( adError );mAdView.loadAd ( adRequest );
                    }

                    @Override
                    public void onAdOpened() {
                    super.onAdOpened ();
                    }

                    @Override
                    public void onAdClicked() {
                     super.onAdClicked ();
                     mAdView.setVisibility ( View.GONE );
                        SharedPreferences.Editor editor= h.edit ();
                        editor.putLong ( "String",System.currentTimeMillis ());
                        editor.commit ();
                        editor.apply ();
                    }

                    @Override
                    public void onAdClosed() {
                        // Code to be executed when the user is about to return
                        // to the app after tapping on an ad.
                    }
                });
            }
        } ,200);}

    }

    @Override
    public void onBackPressed() {

       super.onBackPressed ();}

}