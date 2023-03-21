package com.bhagorplay.mycompresser;





import  androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.core.view.GestureDetectorCompat;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

public class CropperActivity extends AppCompatActivity {
    private int mCurrentImageIndex = 0;
    private ArrayList<String> mImages;
    private final HashMap<Integer, Uri> mCroppedImageUris = new HashMap<>();
    private boolean mCurrentImageEdited = false;
    private boolean mFinishedClicked = false;


    TextView mImageCount;
    ImageView  rotet,finish1;
 TextView CRO;
    CropImageView mCropImageView;
    CropImage cropImage;
   String S;
AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_cropper);
        SharedPreferences h;
        h=getBaseContext ().getSharedPreferences ( "der101",MODE_PRIVATE );
     long g=h.getLong ( "String" , 1);
if(System.currentTimeMillis ()>g+60000*30){
        new Handler ().postDelayed ( new Runnable () {
            @Override
            public void run() {
                mAdView = findViewById(R.id.adView22);
                AdRequest adRequest = new AdRequest.Builder().build();
                mAdView.loadAd(adRequest);
                mAdView.setAdListener(new AdListener () {
                    @Override
                    public void onAdLoaded() {
                        super.onAdLoaded ();
                        Toast.makeText (  CropperActivity.this, "adl", Toast.LENGTH_SHORT ).show ();
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
                        SharedPreferences.Editor editor= h.edit ();
                        editor.putLong ( "String",System.currentTimeMillis ());
                        editor.commit ();
                        editor.apply ();mAdView.setVisibility ( View.GONE );
                    }

                    @Override
                    public void onAdClosed() {
                        // Code to be executed when the user is about to return
                        // to the app after tapping on an ad.
                    }
                });
            }
        } ,200);}
CRO=findViewById ( R.id.CROP );
rotet=findViewById ( R.id.Rotat )
;
finish1=findViewById ( R.id.finish );
mCropImageView=findViewById ( R.id.cropImageView );
        setUpCropImageView();


        S = getIntent ().getStringExtra ("cropimage");







        setImage(S);

        finish1.setOnClickListener ( new View.OnClickListener () {
    @Override
    public void onClick(View view) {
        setResult( Activity.RESULT_CANCELED);
        finish();
    }
} );
;
rotet.setOnClickListener ( new View.OnClickListener () {
    @Override
    public void onClick(View view) {
        mCropImageView.rotateImage ( 90 );
    }
} );
CRO.setOnClickListener ( new View.OnClickListener () {
    @Override
    public void onClick(View view) {
save_the_image ();
    }
} );


    }




    private void setUpCropImageView() {
        mCropImageView.setOnCropImageCompleteListener((CropImageView view, CropImageView.CropResult result) -> {
            int o=mCurrentImageIndex;
         //   Toast.makeText (CropImageActivity.this , ""+mCurrentImageIndex, Toast.LENGTH_SHORT ).show ();
           S=result.toString ();

            mCropImageView.setImageUriAsync( Uri.parse ( S ) );

        });
        if (mFinishedClicked) {
            Intent intent = new Intent();
            intent.putExtra( "yu", S);
            setResult( Activity.RESULT_OK, intent);
            finish();
        }
    }




    private void save_the_image() {
        mCurrentImageEdited = false;
        String root = Environment.getExternalStorageDirectory().toString();
        File folder = new File(root +  "/PDFfiles/");
        Uri uri = mCropImageView.getImageUri();



        Bitmap imageBitmap = (Bitmap)  mCropImageView.getCroppedImage ();
        OutputStream fos;
        Uri imageUri = null;
        try{

            if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.Q){

                ContentResolver resolver = getContentResolver();
                ContentValues contentValues =  new ContentValues ();
                int i=new Random ().nextInt (1234567890);
                contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, "Image_" + i + ".jpg");
                contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");
                 contentValues.put(MediaStore. Images.Media.TITLE, i);


                 contentValues.put(MediaStore. Images.Media.DATE_ADDED, System.currentTimeMillis() / 1000);
                 contentValues.put(MediaStore. Images.Media.DATE_TAKEN, System.currentTimeMillis());
                contentValues.put(MediaStore. Images.Media.IS_PENDING,1);
                contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES+"/.testfolderimagecompressoer"  );
                imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

                 fos = resolver.openOutputStream( Objects.requireNonNull(imageUri));
                 imageBitmap.compress(Bitmap.CompressFormat.JPEG, 95, fos);
                 Objects.requireNonNull(fos);
                  resolver.update ( imageUri,contentValues,null,null );
               // Toast.makeText(CropImageActivity.this, "Image Saved", Toast.LENGTH_SHORT).show();
               // new SingleMediaScanner ( CropperActivity.this, new File (  Environment.DIRECTORY_PICTURES  ));

            }
            else{

                File file=new File ( getApplicationContext ().getExternalCacheDir (),File.separator+ new File ( Environment.DIRECTORY_PICTURES )+"iimage"+new Random ().nextInt (211111)+ ".jpg");

                    FileOutputStream fout=new FileOutputStream ( file );
                     imageBitmap.compress ( Bitmap.CompressFormat.JPEG, 99,fout );
                    fout.flush ();;
                    fout.close ();
                    Uri uri_ = FileProvider.getUriForFile( CropperActivity.this,
                            "com.bhagorplay.mycompresser", file);
                // Save image to gallery
               // String savedImageURL = MediaStore.Images.Media.insertImage(getContentResolver(), imageBitmap, UUID.randomUUID ().toString (),  UUID.randomUUID ().toString ());

                // Parse the gallery image url to uri
                imageUri = Uri.parse( String.valueOf ( uri_ ) );


            }

         }catch(Exception e){

            Toast.makeText(CropperActivity.this, "Image not saved \n" + e.toString(), Toast.LENGTH_SHORT).show();
        }

 S= String.valueOf ( imageUri );
        mFinishedClicked=true;
        setUpCropImageView ();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed ();
        setResult( Activity.RESULT_CANCELED);
        finish();
    }


    private void setImage(String uri ) {

        mCropImageView.setImageUriAsync( Uri.parse ( uri ) );
    }

 }