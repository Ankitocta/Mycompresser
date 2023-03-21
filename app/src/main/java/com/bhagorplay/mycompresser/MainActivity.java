package com.bhagorplay.mycompresser;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;


import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.theartofdev.edmodo.cropper.CropImage;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

import me.shaohui.advancedluban.Luban;
import me.shaohui.advancedluban.OnCompressListener;


public class MainActivity extends AppCompatActivity {
Button Pick_image,comp,use;
private static String picturePath;
private static final int REQUEST_IMAGE_CAPTURE = 2;
ImageView setting,compressimage,cam;
 float globleurisize;
 ProgressDialog p;
 ProgressBar progressBar;

   int quality=90;

       int chen1=1;
    Uri imageUri = null;
 TextView  q12;
 ActivityResultLauncher camerayu;
Spinner spinner ;
EditText KB_VALUE;
ArrayAdapter<String>arrayAdapter;
String[]KBMB={"KB"};
ArrayAdapter<String>arrayAdapter1;
String s;
String[]Formatc={"jpg","png","jpeg","webp"};
EditText Width,hight,size1;
static  String filep;
ImageView imageView,drive,crop;
Button Image,Useto;
 public   static String  globleuri ;
    public   static String   compressuri;
 TextView sizereal,getSizeafter;
ActivityResultLauncher<Intent>activityResultLauncher;
Button share;
    androidx.activity.result.ActivityResultLauncher<Intent> firstgellry,r;

ActivityResultLauncher<Intent>getActivityResultLauncher,newimagecrop;
    SharedPreferences DERECTORY;
static  ArrayList<Uri>u=new ArrayList<> ();
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );

        setContentView ( R.layout.activity_main );  mAdView = findViewById(R.id.adView);
                MobileAds.initialize(this, new OnInitializationCompleteListener () {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        SharedPreferences h;
        h=getBaseContext ().getSharedPreferences ( "der101",MODE_PRIVATE );
        long g=h.getLong ( "String" , 1);

            new Handler ().postDelayed ( new Runnable () {
                @Override
                public void run() {

                    AdRequest adRequest = new AdRequest.Builder().build();
                    mAdView.loadAd(adRequest);
                    mAdView.setAdListener(new AdListener () {
                        @Override
                        public void onAdLoaded() {
                            super.onAdLoaded ();

                        }

                        @Override
                        public void onAdFailedToLoad(LoadAdError adError) {
                            super.onAdFailedToLoad ( adError );
                            mAdView.loadAd ( adRequest );

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
                            // Code to be executed when the user clicks on an ad.
                        }

                        @Override
                        public void onAdClosed() {
                     super.onAdClosed ();
                     onResume ();
                        }
                    });
                }
            } ,200);


    loadAds();



        setting=findViewById ( R.id.setting );
        sizereal=findViewById ( R.id.actualfilesize );
        getSizeafter=findViewById ( R.id.FileSizeafter );
        size1=findViewById ( R.id.editTextNumberDecimal );
        Useto=findViewById ( R.id.Pickimage12 );
        Image=findViewById ( R.id.sharetext12 );
         progressBar=findViewById ( R.id.progressBar2 );
        spinner=findViewById ( R.id.spinner212 )
        ;Pick_image=findViewById ( R.id.Pickimage );
        q12=findViewById ( R.id.textView13 );
         share=findViewById ( R.id.sharetext );
         crop=findViewById ( R.id.cropcompressimage );
        imageView=findViewById ( R.id.Actualimage );
        drive=findViewById ( R.id. camerato_image );
        KB_VALUE=findViewById ( R.id.editTextNumberDecimal);

        Pick_image. callOnClick ();
        DERECTORY=getSharedPreferences ( "der",MODE_PRIVATE );
use=findViewById ( R.id.buttonuse );
use.setOnClickListener ( new View.OnClickListener () {
    @Override
    public void onClick(View view) {
        try {
            String download_link="https://youtu.be/ZHTH25hevjc";
            Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(download_link));
            startActivity(myIntent);
        }
        catch (ActivityNotFoundException e) {
            Toast.makeText( MainActivity.this, "No application can handle this request."
                    + " Please install a webbrowser",  Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
} );
        SharedPreferences.Editor editoWBr= DERECTORY.edit ();
         s=DERECTORY.getString ( "WB",".textfolderclass" );

share.setEnabled ( false ); share.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent k = new Intent( Intent.ACTION_SEND );
                k.setType( "video/mp4" );

                k.putExtra( Intent.EXTRA_STREAM, Uri.parse ( compressuri )  );

                startActivity( k );

                Toast.makeText(   MainActivity.this, "file share starting", Toast.LENGTH_SHORT ).show();
            }
        } );
     compressimage=findViewById ( R.id.cropcompressimage );

     compressimage.setOnClickListener ( new View.OnClickListener () {
         @Override
         public void onClick(View view) {
if (compressuri!=null){
    updatedata1 ( Uri.parse ( compressuri ) );
}
else{

    Toast.makeText (MainActivity.this , "Please compress   a image", Toast.LENGTH_SHORT ).show ();

}
         }
     } );
     comp=findViewById ( R.id.compress );
     comp.setOnClickListener ( new View.OnClickListener () {
         @Override
         public void onClick(View view) {

             String hp=String.valueOf ( size1.getText () );

             if (hp.isEmpty () ) {
                 Toast.makeText ( MainActivity.this, "Please Enter Compress  size  in  kb(1mb=1024kb)", Toast.LENGTH_SHORT ).show ();
                 KB_VALUE.requestFocus ();
             }

             else if(globleuri!=null) {

                 progressBar.setVisibility ( View.VISIBLE );
                q12.setVisibility ( View.VISIBLE );
                 float h = Float.parseFloat ( String.valueOf ( size1.getText () ) );
                 float g = globleurisize / 100;
                 int hh = (int) g;
                 if (g < 1) {
                     hh = 1;
                 }
                 int y = (int) (h / hh);

                 quality = y;
                 if (quality >= 98) {
                     quality = 98;
                 }


                 String path= Path.getRealPath (MainActivity.this, Uri.parse ( globleuri ) );

                 try {


                     runlasttimepedal ( quality,globleuri, h, 1, 1, path  );
                 } catch (IOException e) {
                     e.printStackTrace ();
                 }
             }











             else{

             }
         }
     } );

       Width=findViewById ( R.id. setwidth);
       hight=findViewById ( R.id.setHight );
       r=registerForActivityResult ( new ActivityResultContracts.StartActivityForResult (), new ActivityResultCallback<ActivityResult> () {
           @Override
           public void onActivityResult(ActivityResult result) {
if (result.getResultCode ()==RESULT_OK){
               try {
                   File file_ = new File(picturePath);
                   Uri uri_ = FileProvider.getUriForFile( MainActivity.this,
                           "com.bhagorplay.mycompresser", file_);


                       newimagecrop.launch ( new Intent (MainActivity.this,CropperActivity.class).putExtra ( "cropimage", uri_.toString () ) );




               } catch (/*IO*/Exception e) {
                   e.printStackTrace();
               }

}

else {}

           }
       } );
firstgellry=     registerForActivityResult ( new ActivityResultContracts.StartActivityForResult (), new ActivityResultCallback<ActivityResult> () {
    @Override
    public void onActivityResult(ActivityResult result) {
        try {
            if (result. getData ().getClipData() != null) {

                //  int count = data.getClipData().getItemCount();
                for (int i = 0; i < result.getData ().getClipData ().getItemCount (); i++) {
                    Uri uri = result.getData ().getClipData ().getItemAt ( i ).getUri ();



                }
            }
            else if ( result.getData() != null) {
                Uri u=result.getData ().getData () ;
            updatedata ( u );
            }
            else if (result.getData ()==null)
            {
                Toast.makeText ( MainActivity.this, "oops select image", Toast.LENGTH_SHORT ).show ();
            }

        }
        catch (NullPointerException GV){
            Toast.makeText ( MainActivity.this, "BB", Toast.LENGTH_SHORT ).show ();
        }


    }
} );

        camerayu=registerForActivityResult ( new ActivityResultContracts.StartActivityForResult (), new ActivityResultCallback<ActivityResult> () {
            @Override
            public void onActivityResult(ActivityResult result) {
                if ( result.getResultCode ()==Activity.RESULT_CANCELED){
                    Toast.makeText ( MainActivity.this, "vfv", Toast.LENGTH_SHORT ).show ();
                }
                else if( result.getResultCode ()==Activity.RESULT_OK&&result.getData ()!=null)    {



                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                        Toast.makeText ( MainActivity.this, "BB"+imageUri, Toast.LENGTH_SHORT ).show ();


                       getContentResolver ().update ( imageUri, null,null,null );
                        // Toast.makeText(CropImageActivity.this, "Image Saved", Toast.LENGTH_SHORT).show();
                        // new SingleMediaScanner ( CropperActivity.this, new File (  Environment.DIRECTORY_PICTURES  ));
                     updatedata ( imageUri );
                     }
                    else{



                        getContentResolver ().update ( imageUri,null,null,null );
                        updatedata ( imageUri );
                        Toast.makeText( MainActivity.this, "Image saved to internal!!", Toast.LENGTH_SHORT).show();
                        new SingleMediaScanner (MainActivity. this, new File (  Environment.DIRECTORY_PICTURES + File.separator + "TestFolder" ));

                    }


                }
                }
        } );
       drive.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {


             firstgellry.launch ( Intent.createChooser (   new Intent ( Intent.ACTION_GET_CONTENT ).putExtra ( Intent.EXTRA_ALLOW_MULTIPLE, false ).setType ( "image/*" ),"select image") );


            }
        } );
        cam=findViewById ( R.id.imageView3 );
new Handler ().postDelayed ( new Runnable () {
    @Override
    public void run() {
        Glide.with ( MainActivity.this ).load ( compressuri).placeholder ( R.drawable.ic_baseline_image_24 ).into (  compressimage);
        Glide.with ( MainActivity.this ).load ( globleuri ).placeholder ( R.drawable.ic_baseline_image_24 ).into ( imageView );
    }
} ,1200);


        arrayAdapter1=new ArrayAdapter<String> (MainActivity.this, android.R.layout.simple_spinner_dropdown_item  ,Formatc);

        arrayAdapter=new ArrayAdapter<String> (MainActivity.this, android.R.layout.simple_spinner_dropdown_item  ,KBMB);
      spinner.setAdapter ( arrayAdapter );spinner.setOnItemSelectedListener ( new AdapterView.OnItemSelectedListener () {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               // Toast.makeText ( MainActivity.this, "" +adapterView.getItemAtPosition ( i ), Toast.LENGTH_SHORT ).show ();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        } );
        setting.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                startActivity ( new Intent (MainActivity.this, com.bhagorplay.mycompresser.setting.class ) );
            }
        } );
cam.setOnClickListener ( new View.OnClickListener () {
    @Override
    public void onClick(View view) {
        gh();
    }
} );
      ;





        Pick_image.setOnClickListener ( view -> {

            Intent pickIntent = new Intent(Intent.ACTION_PICK);
            pickIntent.setType("image/*");



      activityResultLauncher.launch ( pickIntent );

        } );
        //
        activityResultLauncher=registerForActivityResult ( new ActivityResultContracts.StartActivityForResult (), new ActivityResultCallback<ActivityResult> () {
            @Override
            public void onActivityResult(ActivityResult result) {
       if (result.getResultCode ()==RESULT_OK&&result.getData ().getData ()!=null) {

     updatedata ( Uri.parse ( result.getData ().getData ().toString () ) );

}
            else{

}
            }
        } );
        newimagecrop=registerForActivityResult ( new ActivityResultContracts.StartActivityForResult (), new ActivityResultCallback<ActivityResult> () {
            @Override
            public void onActivityResult(ActivityResult result) {
                try {
                    if (result.getResultCode ()== Activity.RESULT_OK){
                   String t=result.getData ().getStringExtra ( "yu" );
                   updatedata ( Uri.parse ( t ) );
                }
                    else{

                    }
                }catch (Exception f){}



            }
        } );
        getActivityResultLauncher=registerForActivityResult ( new ActivityResultContracts.StartActivityForResult (), new ActivityResultCallback<ActivityResult> () {
            @Override
            public void onActivityResult(ActivityResult result) {

            }
        } );


        imageView.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                if ( globleuri!=null){
                    newimagecrop.launch ( new Intent (MainActivity.this,CropperActivity.class).putExtra ( "cropimage",globleuri ) );

                }
                else {

                }

            }
        } );

    }

    private void gh() {
        Dexter.withContext ( MainActivity.this ).withPermission ( Manifest.permission.CAMERA ).withListener ( new PermissionListener () {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
//                CropImage.activity ().setGuidelines ( CropImageView.Guidelines.ON ).start (MainActivity. this );
                Intent takePictureIntent_ = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // Ensure that there's a camera activity to handle the intent
                if (takePictureIntent_.resolveActivity(getPackageManager()) != null) {
                    // Create the File where the photo should go
                    File photoFile_ = null;
                    try {
                        photoFile_ = createImageFile();
                    } catch (IOException ex) {
                    }
                    if(photoFile_!=null){
                        picturePath=photoFile_.getAbsolutePath();
                    }
                    // Continue only if the File was successfully created
                    if (photoFile_ != null) {
                        Uri photoURI_ = FileProvider.getUriForFile( MainActivity.this,
                                "com.bhagorplay.mycompresser", photoFile_);
                        takePictureIntent_.putExtra(MediaStore.EXTRA_OUTPUT, photoURI_);
                    r.launch ( takePictureIntent_ );
                    }
                }
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
//                CropImage.activity ().setGuidelines ( CropImageView.Guidelines.ON ).start (MainActivity. this );
                Toast.makeText ( MainActivity.this, "permission is Dany please go tu setting and allow  ", Toast.LENGTH_SHORT ).show ();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
permissionToken.continuePermissionRequest ();
            }
        } ).check ();

    }





    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp_ = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new
                Date());
        String imageFileName_ = "JPEG_" + timeStamp_ + "_";
        File storageDir_ = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image_ = File.createTempFile(
                imageFileName_,  /* prefix */
                ".jpg",         /* suffix */
                storageDir_      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        picturePath= image_.getAbsolutePath();
        return image_;
    }



    public void runlasttimepedal(int value,String A ,float requrevalue, int width, int hight1, String path) throws IOException {
try {
    Luban.compress ( MainActivity.this, new File ( path ) ).setMaxSize ( 50 ).setCompressFormat ( Bitmap.CompressFormat.JPEG ).launch ( new OnCompressListener () {
        @Override
        public void onStart() {

        }

        @Override
        public void onSuccess(File file) {
            try {
                Storefile ( value, file, A, requrevalue, width, hight1, path );
            } catch (IOException e) {
                e.printStackTrace ();
            }
        }

        @Override
        public void onError(Throwable e) {

        }
    } );

}catch (Exception G) {
    if (requrevalue>globleurisize) {
        Toast.makeText ( MainActivity.this, "Please Enter Smaller size ", Toast.LENGTH_LONG ).show ();
        progressBar.setVisibility ( View.GONE );
        q12.setVisibility ( View.GONE );
    }
    else {
        {
                    InputStream imageStream = null;
        try {
            imageStream =  getContentResolver().openInputStream( Uri.parse ( globleuri ) );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Bitmap bitmap= BitmapFactory.decodeStream(imageStream);
            if(globleurisize>2600){
                Toast.makeText (MainActivity.this , ""+quality, Toast.LENGTH_SHORT ).show ();
                Float w = Float.parseFloat ( String.valueOf ( Width.getText () ) );
                Float H = Float.parseFloat ( String.valueOf ( hight.getText () ) );
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap ( getContentResolver (), Uri.parse ( file ) );
//                Bitmap bitmap = BitmapFactory.decodeFile ( file1.getAbsolutePath () );
                if (requrevalue<120&&requrevalue>35){
                    w=w/2;
                    H=H/2;
                }
                if (requrevalue<=35){
                    w=w/4;
                    H=H/4;
                }

                bitmap = Bitmap.createScaledBitmap ( bitmap, w.intValue (), H.intValue (), false );
                OutputStream fos = null;


                Uri imageUri = null;
                try {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

                        ContentResolver resolver = getContentResolver ();
                        ContentValues contentValues = new ContentValues ();
                        int i = new Random ().nextInt ( 1234567890 );
                        contentValues.put ( MediaStore.MediaColumns.DISPLAY_NAME, "Image_" + i + ".jpg" );
                        contentValues.put ( MediaStore.MediaColumns.MIME_TYPE, "image/jpeg" );
                        contentValues.put ( MediaStore.Images.Media.TITLE, i );


                        contentValues.put ( MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis () / 1000 );
                        contentValues.put ( MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis () );
                        contentValues.put ( MediaStore.Images.Media.IS_PENDING, 0);
                        contentValues.put ( MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/" + s );
                        imageUri = resolver.insert ( MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues );

                        fos = resolver.openOutputStream ( Objects.requireNonNull ( imageUri ) );
                        if (quality > 75 && quality <= 98) {




                            bitmap.compress ( Bitmap.CompressFormat.JPEG, quality, fos );

                            Objects.requireNonNull ( fos );
                            resolver.update ( imageUri, contentValues, null, null );



                            updatedata1 ( imageUri );

//                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
                        }
                        else if (quality > 50 && quality <= 75) {



                            bitmap.compress ( Bitmap.CompressFormat.JPEG, quality, fos );

                            Objects.requireNonNull ( fos );
                            resolver.update ( imageUri, contentValues, null, null );


                            updatedata1 ( imageUri );


//                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
                        }



                        else if (quality > 20 && quality <= 50) {




                            bitmap.compress ( Bitmap.CompressFormat.JPEG, quality, fos );

                            Objects.requireNonNull ( fos );
                            resolver.update ( imageUri, contentValues, null, null );

                            updatedata1 ( imageUri );

//                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
                        }

                        // complete
                        else if (quality>10&&quality<=20) {

//135


                            //10






                            bitmap.compress ( Bitmap.CompressFormat.JPEG, quality, fos );

                            Objects.requireNonNull ( fos );
                            resolver.update ( imageUri, contentValues, null, null );



                            updatedata1 ( imageUri );
// ainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();

                        }
                        // new SingleMediaScanner ( CropperActivity.this, new File (  Environment.DIRECTORY_PICTURES  ));

                        else if (quality<=10) {


//      if (requrevalue>10&&requrevalue<14&&globleurisize<800){
//    quality+=12;
//}

                            if (quality==10)  {

                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );






                                updatedata1 ( imageUri );
                            }

                            else if (quality==9){

                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );






                                updatedata1 ( imageUri );
                            }
                            else if (quality==8){

                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );






                                updatedata1 ( imageUri );
                            }
                            else if (quality==7){

                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );






                                updatedata1 ( imageUri );
                            }
                            else if (quality==6){

                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );






                                updatedata1 ( imageUri );
                            }
                            else if (quality==5){
                                quality=4;
                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );






                                updatedata1 ( imageUri );
                            }
                            else if (quality==4){

                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );






                                updatedata1 ( imageUri );
                            }
                            else if (quality==3){
                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );






                                updatedata1 ( imageUri );

                            }
                            else{

                                if (requrevalue>21){
                                    quality=3;
                                }
                                else if(requrevalue>10&&requrevalue<=21){
                                    quality=1;
                                }
                                else{
                                    quality=0;
                                }
                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );


                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );






                                updatedata1 ( imageUri );
                            }

                            //134 -700

                            //120-600



                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace ();
                }
            }
            else   if(globleurisize>1350&&globleurisize<=2600)  {
                Toast.makeText (MainActivity.this , ""+quality, Toast.LENGTH_SHORT ).show ();
                Float w = Float.parseFloat ( String.valueOf ( Width.getText () ) );
                Float H = Float.parseFloat ( String.valueOf ( hight.getText () ) );
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap ( getContentResolver (), Uri.parse ( file ) );
//                Bitmap bitmap = BitmapFactory.decodeFile ( file1.getAbsolutePath () );
                if (requrevalue<80&&requrevalue>29){
                    w=w/2;
                    H=H/2;
                }
                if (requrevalue<=29){
                    w=w/4;
                    H=H/4;
                }
                bitmap = Bitmap.createScaledBitmap ( bitmap, w.intValue (), H.intValue (), false );
                OutputStream fos = null;


                Uri imageUri = null;
                try {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

                        ContentResolver resolver = getContentResolver ();
                        ContentValues contentValues = new ContentValues ();
                        int i = new Random ().nextInt ( 1234567890 );
                        contentValues.put ( MediaStore.MediaColumns.DISPLAY_NAME, "Image_" + i + ".jpg" );
                        contentValues.put ( MediaStore.MediaColumns.MIME_TYPE, "image/jpeg" );
                        contentValues.put ( MediaStore.Images.Media.TITLE, i );


                        contentValues.put ( MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis () / 1000 );
                        contentValues.put ( MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis () );
                        contentValues.put ( MediaStore.Images.Media.IS_PENDING, 0);
                        contentValues.put ( MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/" + s );
                        imageUri = resolver.insert ( MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues );

                        fos = resolver.openOutputStream ( Objects.requireNonNull ( imageUri ) );
                        if (quality > 75 && quality <= 98) {




                            bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                            Objects.requireNonNull ( fos );
                            resolver.update ( imageUri, contentValues, null, null );



                            updatedata1 ( imageUri );

//                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
                        }
                        else if (quality > 50 && quality <= 75) {



                            bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                            Objects.requireNonNull ( fos );
                            resolver.update ( imageUri, contentValues, null, null );


                            updatedata1 ( imageUri );


//                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
                        }



                        else if (quality > 20 && quality <= 50) {




                            bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                            Objects.requireNonNull ( fos );
                            resolver.update ( imageUri, contentValues, null, null );

                            updatedata1 ( imageUri );

//                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
                        }

                        // complete
                        else if (quality>10&&quality<=20) {

//135


                            //10



                            quality += 12;


                            bitmap.compress ( Bitmap.CompressFormat.JPEG, quality, fos );

                            Objects.requireNonNull ( fos );
                            resolver.update ( imageUri, contentValues, null, null );



                            updatedata1 ( imageUri );
// ainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();

                        }
                        // new SingleMediaScanner ( CropperActivity.this, new File (  Environment.DIRECTORY_PICTURES  ));

                        else if (quality<=10) {


//      if (requrevalue>10&&requrevalue<14&&globleurisize<800){
//    quality+=12;
//}

                            if (quality==10)  {

                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );






                                updatedata1 ( imageUri );
                            }

                            else if (quality==9){

                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );






                                updatedata1 ( imageUri );
                            }
                            else if (quality==8){

                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );






                                updatedata1 ( imageUri );
                            }
                            else if (quality==7){

                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );






                                updatedata1 ( imageUri );
                            }
                            else if (quality==6){

                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );






                                updatedata1 ( imageUri );
                            }
                            else if (quality==5){
                                quality=4;
                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );






                                updatedata1 ( imageUri );
                            }
                            else if (quality==4){

                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );






                                updatedata1 ( imageUri );
                            }
                            else if (quality==3){
                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );






                                updatedata1 ( imageUri );

                            }
                            else{

                                if (requrevalue>21){
                                    quality=3;
                                }
                                else if(requrevalue>10&&requrevalue<=21){
                                    quality=2;
                                }
                                else{
                                    quality=0;
                                }
                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );






                                updatedata1 ( imageUri );
                            }

                            //134 -700

                            //120-600



                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace ();
                }
            }
            else if (globleurisize>800&&globleurisize<=1350){
                Toast.makeText (MainActivity.this , ""+quality, Toast.LENGTH_SHORT ).show ();
                Float w = Float.parseFloat ( String.valueOf ( Width.getText () ) );
                Float H = Float.parseFloat ( String.valueOf ( hight.getText () ) );
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap ( getContentResolver (), Uri.parse ( file ) );
//                Bitmap bitmap = BitmapFactory.decodeFile ( file1.getAbsolutePath () );
                if (requrevalue<45&&requrevalue>25){
                    w=w/2;
                    H=H/2;
                }
                if (requrevalue<=25){
                    w=w/4;
                    H=H/4;
                }

                bitmap = Bitmap.createScaledBitmap ( bitmap, w.intValue (), H.intValue (), false );
                OutputStream fos = null;


                Uri imageUri = null;
                try {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

                        ContentResolver resolver = getContentResolver ();
                        ContentValues contentValues = new ContentValues ();
                        int i = new Random ().nextInt ( 1234567890 );
                        contentValues.put ( MediaStore.MediaColumns.DISPLAY_NAME, "Image_" + i + ".jpg" );
                        contentValues.put ( MediaStore.MediaColumns.MIME_TYPE, "image/jpeg" );
                        contentValues.put ( MediaStore.Images.Media.TITLE, i );


                        contentValues.put ( MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis () / 1000 );
                        contentValues.put ( MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis () );
                        contentValues.put ( MediaStore.Images.Media.IS_PENDING, 0);
                        contentValues.put ( MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/" + s );
                        imageUri = resolver.insert ( MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues );

                        fos = resolver.openOutputStream ( Objects.requireNonNull ( imageUri ) );
                        if (quality > 75 && quality <= 98) {




                            bitmap.compress ( Bitmap.CompressFormat.JPEG, quality, fos );

                            Objects.requireNonNull ( fos );
                            resolver.update ( imageUri, contentValues, null, null );



                            updatedata1 ( imageUri );

//                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
                        }
                        else if (quality > 50 && quality <= 75) {



                            bitmap.compress ( Bitmap.CompressFormat.JPEG, quality, fos );

                            Objects.requireNonNull ( fos );
                            resolver.update ( imageUri, contentValues, null, null );


                            updatedata1 ( imageUri );


//                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
                        }



                        else if (quality > 20 && quality <= 50) {




                            bitmap.compress ( Bitmap.CompressFormat.JPEG, quality, fos );

                            Objects.requireNonNull ( fos );
                            resolver.update ( imageUri, contentValues, null, null );

                            updatedata1 ( imageUri );

//                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
                        }

                        // complete
                        else if (quality>10&&quality<=20) {

//135


                            //10




                            bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                            Objects.requireNonNull ( fos );
                            resolver.update ( imageUri, contentValues, null, null );



                            updatedata1 ( imageUri );
// ainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();

                        }
                        // new SingleMediaScanner ( CropperActivity.this, new File (  Environment.DIRECTORY_PICTURES  ));

                        else if (quality<=10) {


//      if (requrevalue>10&&requrevalue<14&&globleurisize<800){
//    quality+=12;
//}

                            if (quality==10)  {

                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );






                                updatedata1 ( imageUri );
                            }

                            else if (quality==9){

                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );






                                updatedata1 ( imageUri );
                            }
                            else if (quality==8){

                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );






                                updatedata1 ( imageUri );
                            }
                            else if (quality==7){

                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );






                                updatedata1 ( imageUri );
                            }
                            else if (quality==6){

                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );






                                updatedata1 ( imageUri );
                            }
                            else if (quality==5){

                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );






                                updatedata1 ( imageUri );
                            }
                            else if (quality==4){

                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );






                                updatedata1 ( imageUri );
                            }
                            else if (quality==3){
                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );






                                updatedata1 ( imageUri );

                            }
                            else{


                                if (requrevalue>21){
                                    quality=3;
                                }
                                else if(requrevalue>10&&requrevalue<=21){
                                    quality=2;
                                }
                                else{
                                    quality=1;
                                }
                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );






                                updatedata1 ( imageUri );
                            }

                            //134 -700

                            //120-600



                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace ();
                }








            }

            else if(globleurisize>200&&globleurisize<=800) {
                Toast.makeText ( MainActivity.this, "" + quality, Toast.LENGTH_SHORT ).show ();
                Float w = Float.parseFloat ( String.valueOf ( Width.getText () ) );
                Float H = Float.parseFloat ( String.valueOf ( hight.getText () ) );
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap ( getContentResolver (), Uri.parse ( file ) );
//                Bitmap bitmap = BitmapFactory.decodeFile ( file1.getAbsolutePath () );
                if (requrevalue<35&&requrevalue>10){
                    w=w/2;
                    H=H/2;
                }
                if (requrevalue<=10){
                    w=w/4;
                    H=H/4;
                }


                bitmap = Bitmap.createScaledBitmap ( bitmap, w.intValue (), H.intValue (), false );
                OutputStream fos = null;


                Uri imageUri = null;
                try {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

                        ContentResolver resolver = getContentResolver ();
                        ContentValues contentValues = new ContentValues ();
                        int i = new Random ().nextInt ( 1234567890 );
                        contentValues.put ( MediaStore.MediaColumns.DISPLAY_NAME, "Image_" + i + ".jpg" );
                        contentValues.put ( MediaStore.MediaColumns.MIME_TYPE, "image/jpeg" );
                        contentValues.put ( MediaStore.Images.Media.TITLE, i );


                        contentValues.put ( MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis () / 1000 );
                        contentValues.put ( MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis () );
                        contentValues.put ( MediaStore.Images.Media.IS_PENDING, 0 );
                        contentValues.put ( MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/" + s );
                        imageUri = resolver.insert ( MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues );

                        fos = resolver.openOutputStream ( Objects.requireNonNull ( imageUri ) );
                        if (quality > 75 && quality <= 98) {


                            bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                            Objects.requireNonNull ( fos );
                            resolver.update ( imageUri, contentValues, null, null );


                            updatedata1 ( imageUri );

//                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
                        } else if (quality > 50 && quality <= 75) {


                            bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                            Objects.requireNonNull ( fos );
                            resolver.update ( imageUri, contentValues, null, null );


                            updatedata1 ( imageUri );


//                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
                        } else if (quality > 20 && quality <= 50) {


                            bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                            Objects.requireNonNull ( fos );
                            resolver.update ( imageUri, contentValues, null, null );

                            updatedata1 ( imageUri );

//                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
                        }

                        // complete
                        else if (quality > 10 && quality <= 20) {

//135


                            //10


                            quality += 12;


                            bitmap.compress ( Bitmap.CompressFormat.JPEG, quality, fos );

                            Objects.requireNonNull ( fos );
                            resolver.update ( imageUri, contentValues, null, null );


                            updatedata1 ( imageUri );
// ainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();

                        }
                        // new SingleMediaScanner ( CropperActivity.this, new File (  Environment.DIRECTORY_PICTURES  ));

                        else if (quality <= 10) {


//      if (requrevalue>10&&requrevalue<14&&globleurisize<800){
//    quality+=12;
//}

                            if (quality == 10) {
                                quality += 2;
                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );


                                updatedata1 ( imageUri );
                            } else if (quality == 9) {
                                quality += 2;
                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );


                                updatedata1 ( imageUri );
                            } else if (quality == 8) {
                                quality += 2;
                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );


                                updatedata1 ( imageUri );
                            } else if (quality == 7) {
                                quality += 2;
                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );


                                updatedata1 ( imageUri );
                            } else if (quality == 6) {
                                quality += 2;
                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );


                                updatedata1 ( imageUri );
                            } else if (quality == 5) {
                                quality += 1;
                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );


                                updatedata1 ( imageUri );
                            } else if (quality == 4) {
                                quality += 1;
                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );


                                updatedata1 ( imageUri );
                            } else if (quality == 3) {
                                quality += 1;
                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );


                                updatedata1 ( imageUri );

                            } else {
                                if (requrevalue>21){
                                    quality=3;
                                }
                                else if(requrevalue>10&&requrevalue<=21){
                                    quality=2;
                                }
                                else{
                                    quality=1;
                                }
                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );


                                updatedata1 ( imageUri );
                            }

                            //134 -700

                            //120-600


                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace ();
                }
            }
            else{    Toast.makeText (MainActivity.this , ""+quality, Toast.LENGTH_SHORT ).show ();
                Float w = Float.parseFloat ( String.valueOf ( Width.getText () ) );
                Float H = Float.parseFloat ( String.valueOf ( hight.getText () ) );
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap ( getContentResolver (), Uri.parse ( file ) );
//                Bitmap bitmap = BitmapFactory.decodeFile ( file1.getAbsolutePath () );
                if (requrevalue<20&&requrevalue>8){
                    w=w/2;
                    H=H/2;
                }
                if (requrevalue<=8){
                    w=w/4;
                    H=H/4;
                }

                bitmap = Bitmap.createScaledBitmap ( bitmap, w.intValue (), H.intValue (), false );
                OutputStream fos = null;


                Uri imageUri = null;
                try {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

                        ContentResolver resolver = getContentResolver ();
                        ContentValues contentValues = new ContentValues ();
                        int i = new Random ().nextInt ( 1234567890 );
                        contentValues.put ( MediaStore.MediaColumns.DISPLAY_NAME, "Image_" + i + ".jpg" );
                        contentValues.put ( MediaStore.MediaColumns.MIME_TYPE, "image/jpeg" );
                        contentValues.put ( MediaStore.Images.Media.TITLE, i );


                        contentValues.put ( MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis () / 1000 );
                        contentValues.put ( MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis () );
                        contentValues.put ( MediaStore.Images.Media.IS_PENDING, 0);
                        contentValues.put ( MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/" + s );
                        imageUri = resolver.insert ( MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues );

                        fos = resolver.openOutputStream ( Objects.requireNonNull ( imageUri ) );
                        if (quality > 75 && quality <= 98) {

                            quality=50;


                            bitmap.compress ( Bitmap.CompressFormat.JPEG, quality, fos );

                            Objects.requireNonNull ( fos );
                            resolver.update ( imageUri, contentValues, null, null );



                            updatedata1 ( imageUri );

//                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
                        }
                        else if (quality > 50 && quality <= 75) {


                            quality=25;

                            bitmap.compress ( Bitmap.CompressFormat.JPEG, quality, fos );

                            Objects.requireNonNull ( fos );
                            resolver.update ( imageUri, contentValues, null, null );


                            updatedata1 ( imageUri );


//                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
                        }



                        else if (quality > 20 && quality <= 50) {


                            quality -= 16;

                            bitmap.compress ( Bitmap.CompressFormat.JPEG, quality, fos );

                            Objects.requireNonNull ( fos );
                            resolver.update ( imageUri, contentValues, null, null );

                            updatedata1 ( imageUri );

//                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
                        }

                        // complete
                        else if ( quality<=20) {

//135


                            //10



                            quality = 2;


                            bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                            Objects.requireNonNull ( fos );
                            resolver.update ( imageUri, contentValues, null, null );



                            updatedata1 ( imageUri );
// ainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();

                        }
                        // new SingleMediaScanner ( CropperActivity.this, new File (  Environment.DIRECTORY_PICTURES  ));


                    }
                    else{

                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace ();
                }

            }


        }
    }
}






    }

    private void Storefile(int value, File file1, String file, float requrevalue, int width, int hight1, String path) throws IOException {
    if (requrevalue>globleurisize) {
        Toast.makeText ( MainActivity.this, "Please Enter Smaller size ", Toast.LENGTH_LONG ).show ();
        progressBar.setVisibility ( View.GONE );
        q12.setVisibility ( View.GONE );
    }
    else {
        {
if(globleurisize>2700){
    Toast.makeText (MainActivity.this , ""+quality, Toast.LENGTH_SHORT ).show ();
    Float w = Float.parseFloat ( String.valueOf ( Width.getText () ) );
    Float H = Float.parseFloat ( String.valueOf ( hight.getText () ) );
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap ( getContentResolver (), Uri.parse ( file ) );
    Bitmap bitmap = BitmapFactory.decodeFile ( file1.getAbsolutePath () );
    if (requrevalue<120&&requrevalue>41){
        w=w/2;
        H=H/2;
    }
 if (requrevalue<=41){
     w=w/4;
     H=H/4;
 }

    bitmap = Bitmap.createScaledBitmap ( bitmap, w.intValue (), H.intValue (), false );
    OutputStream fos = null;


    Uri imageUri = null;
    try {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

            ContentResolver resolver = getContentResolver ();
            ContentValues contentValues = new ContentValues ();
            int i = new Random ().nextInt ( 1234567890 );
            contentValues.put ( MediaStore.MediaColumns.DISPLAY_NAME, "Image_" + i + ".jpg" );
            contentValues.put ( MediaStore.MediaColumns.MIME_TYPE, "image/jpeg" );
            contentValues.put ( MediaStore.Images.Media.TITLE, i );


            contentValues.put ( MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis () / 1000 );
            contentValues.put ( MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis () );
            contentValues.put ( MediaStore.Images.Media.IS_PENDING, 0);
            contentValues.put ( MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/" + s );
            imageUri = resolver.insert ( MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues );

            fos = resolver.openOutputStream ( Objects.requireNonNull ( imageUri ) );
            if (quality > 75 && quality <= 98) {




                bitmap.compress ( Bitmap.CompressFormat.JPEG, quality, fos );

                Objects.requireNonNull ( fos );
                resolver.update ( imageUri, contentValues, null, null );



                updatedata1 ( imageUri );

//                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
            }
            else if (quality > 50 && quality <= 75) {



                bitmap.compress ( Bitmap.CompressFormat.JPEG, quality, fos );

                Objects.requireNonNull ( fos );
                resolver.update ( imageUri, contentValues, null, null );


                updatedata1 ( imageUri );


//                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
            }



            else if (quality > 20 && quality <= 50) {




                bitmap.compress ( Bitmap.CompressFormat.JPEG, quality, fos );

                Objects.requireNonNull ( fos );
                resolver.update ( imageUri, contentValues, null, null );

                updatedata1 ( imageUri );

//                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
            }

            // complete
            else if (quality>10&&quality<=20) {

//135


                //10






                bitmap.compress ( Bitmap.CompressFormat.JPEG, quality, fos );

                Objects.requireNonNull ( fos );
                resolver.update ( imageUri, contentValues, null, null );



                updatedata1 ( imageUri );
// ainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();

            }
            // new SingleMediaScanner ( CropperActivity.this, new File (  Environment.DIRECTORY_PICTURES  ));

            else if (quality<=10) {


//      if (requrevalue>10&&requrevalue<14&&globleurisize<800){
//    quality+=12;
//}

                if (quality==10)  {

                    bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                    Objects.requireNonNull ( fos );
                    resolver.update ( imageUri, contentValues, null, null );






                    updatedata1 ( imageUri );
                }

                else if (quality==9){

                    bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                    Objects.requireNonNull ( fos );
                    resolver.update ( imageUri, contentValues, null, null );






                    updatedata1 ( imageUri );
                }
                else if (quality==8){

                    bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                    Objects.requireNonNull ( fos );
                    resolver.update ( imageUri, contentValues, null, null );






                    updatedata1 ( imageUri );
                }
                else if (quality==7){

                    bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                    Objects.requireNonNull ( fos );
                    resolver.update ( imageUri, contentValues, null, null );






                    updatedata1 ( imageUri );
                }
                else if (quality==6){

                    bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                    Objects.requireNonNull ( fos );
                    resolver.update ( imageUri, contentValues, null, null );






                    updatedata1 ( imageUri );
                }
                else if (quality==5){
                    quality=4;
                    bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                    Objects.requireNonNull ( fos );
                    resolver.update ( imageUri, contentValues, null, null );






                    updatedata1 ( imageUri );
                }
                else if (quality==4){

                    bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                    Objects.requireNonNull ( fos );
                    resolver.update ( imageUri, contentValues, null, null );






                    updatedata1 ( imageUri );
                }
                else if (quality==3){
                    bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                    Objects.requireNonNull ( fos );
                    resolver.update ( imageUri, contentValues, null, null );






                    updatedata1 ( imageUri );

                }
                else{

                    if (requrevalue>21){
                        quality=2;
                    }
                    else if(requrevalue>10&&requrevalue<=21){
                        quality=1;
                    }
                    else{
                        quality=0;
                    }
                    bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );


                    Objects.requireNonNull ( fos );
                    resolver.update ( imageUri, contentValues, null, null );






                    updatedata1 ( imageUri );
                }

                //134 -700

                //120-600



            }
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace ();
    }
}
   else   if(globleurisize>1350&&globleurisize<=2700)  {
              Toast.makeText (MainActivity.this , ""+quality, Toast.LENGTH_SHORT ).show ();
              Float w = Float.parseFloat ( String.valueOf ( Width.getText () ) );
              Float H = Float.parseFloat ( String.valueOf ( hight.getText () ) );
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap ( getContentResolver (), Uri.parse ( file ) );
              Bitmap bitmap = BitmapFactory.decodeFile ( file1.getAbsolutePath () );
    if (requrevalue<80&&requrevalue>29){
        w=w/2;
        H=H/2;
    }
    if (requrevalue<=29){
        w=w/4;
        H=H/4;
    }
              bitmap = Bitmap.createScaledBitmap ( bitmap, w.intValue (), H.intValue (), false );
              OutputStream fos = null;


              Uri imageUri = null;
              try {

                  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

                      ContentResolver resolver = getContentResolver ();
                      ContentValues contentValues = new ContentValues ();
                      int i = new Random ().nextInt ( 1234567890 );
                      contentValues.put ( MediaStore.MediaColumns.DISPLAY_NAME, "Image_" + i + ".jpg" );
                      contentValues.put ( MediaStore.MediaColumns.MIME_TYPE, "image/jpeg" );
                      contentValues.put ( MediaStore.Images.Media.TITLE, i );


                      contentValues.put ( MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis () / 1000 );
                      contentValues.put ( MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis () );
                      contentValues.put ( MediaStore.Images.Media.IS_PENDING, 0);
                      contentValues.put ( MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/" + s );
                      imageUri = resolver.insert ( MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues );

                      fos = resolver.openOutputStream ( Objects.requireNonNull ( imageUri ) );
                      if (quality > 75 && quality <= 98) {




                          bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                          Objects.requireNonNull ( fos );
                          resolver.update ( imageUri, contentValues, null, null );



                          updatedata1 ( imageUri );

//                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
                      }
                      else if (quality > 50 && quality <= 75) {



                          bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                          Objects.requireNonNull ( fos );
                          resolver.update ( imageUri, contentValues, null, null );


                          updatedata1 ( imageUri );


//                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
                      }



                      else if (quality > 20 && quality <= 50) {




                          bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                          Objects.requireNonNull ( fos );
                          resolver.update ( imageUri, contentValues, null, null );

                          updatedata1 ( imageUri );

//                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
                      }

                      // complete
                      else if (quality>10&&quality<=20) {

//135


                          //10



                          quality += 12;


                          bitmap.compress ( Bitmap.CompressFormat.JPEG, quality, fos );

                          Objects.requireNonNull ( fos );
                          resolver.update ( imageUri, contentValues, null, null );



                          updatedata1 ( imageUri );
// ainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();

                      }
                      // new SingleMediaScanner ( CropperActivity.this, new File (  Environment.DIRECTORY_PICTURES  ));

                      else if (quality<=10) {


//      if (requrevalue>10&&requrevalue<14&&globleurisize<800){
//    quality+=12;
//}

                          if (quality==10)  {

                              bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                              Objects.requireNonNull ( fos );
                              resolver.update ( imageUri, contentValues, null, null );






                              updatedata1 ( imageUri );
                          }

                          else if (quality==9){

                              bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                              Objects.requireNonNull ( fos );
                              resolver.update ( imageUri, contentValues, null, null );






                              updatedata1 ( imageUri );
                          }
                          else if (quality==8){

                              bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                              Objects.requireNonNull ( fos );
                              resolver.update ( imageUri, contentValues, null, null );






                              updatedata1 ( imageUri );
                          }
                          else if (quality==7){

                              bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                              Objects.requireNonNull ( fos );
                              resolver.update ( imageUri, contentValues, null, null );






                              updatedata1 ( imageUri );
                          }
                          else if (quality==6){

                              bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                              Objects.requireNonNull ( fos );
                              resolver.update ( imageUri, contentValues, null, null );






                              updatedata1 ( imageUri );
                          }
                          else if (quality==5){
quality=4;
                              bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                              Objects.requireNonNull ( fos );
                              resolver.update ( imageUri, contentValues, null, null );






                              updatedata1 ( imageUri );
                          }
                          else if (quality==4){

                              bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                              Objects.requireNonNull ( fos );
                              resolver.update ( imageUri, contentValues, null, null );






                              updatedata1 ( imageUri );
                          }
                          else if (quality==3){
                              bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                              Objects.requireNonNull ( fos );
                              resolver.update ( imageUri, contentValues, null, null );






                              updatedata1 ( imageUri );

                          }
                          else{

                              if (requrevalue>21){
                                  quality=3;
                              }
                              else if(requrevalue>10&&requrevalue<=21){
                                  quality=1;
                              }
                              else{
                                  quality=0;
                              }
                              bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                              Objects.requireNonNull ( fos );
                              resolver.update ( imageUri, contentValues, null, null );






                              updatedata1 ( imageUri );
                          }

                          //134 -700

                          //120-600



                      }
                  }
              } catch (FileNotFoundException e) {
                  e.printStackTrace ();
              }
          }
                        else if (globleurisize>800&&globleurisize<=1350){
                Toast.makeText (MainActivity.this , ""+quality, Toast.LENGTH_SHORT ).show ();
                Float w = Float.parseFloat ( String.valueOf ( Width.getText () ) );
                Float H = Float.parseFloat ( String.valueOf ( hight.getText () ) );
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap ( getContentResolver (), Uri.parse ( file ) );
                Bitmap bitmap = BitmapFactory.decodeFile ( file1.getAbsolutePath () );
    if (requrevalue<55&&requrevalue>19){
        w=w/2;
        H=H/2;
    }
    if (requrevalue<=25){
        w=w/4;
        H=H/4;
    }

                bitmap = Bitmap.createScaledBitmap ( bitmap, w.intValue (), H.intValue (), false );
                OutputStream fos = null;


                Uri imageUri = null;
                try {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

                        ContentResolver resolver = getContentResolver ();
                        ContentValues contentValues = new ContentValues ();
                        int i = new Random ().nextInt ( 1234567890 );
                        contentValues.put ( MediaStore.MediaColumns.DISPLAY_NAME, "Image_" + i + ".jpg" );
                        contentValues.put ( MediaStore.MediaColumns.MIME_TYPE, "image/jpeg" );
                        contentValues.put ( MediaStore.Images.Media.TITLE, i );


                        contentValues.put ( MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis () / 1000 );
                        contentValues.put ( MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis () );
                        contentValues.put ( MediaStore.Images.Media.IS_PENDING, 0);
                        contentValues.put ( MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/" + s );
                        imageUri = resolver.insert ( MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues );

                        fos = resolver.openOutputStream ( Objects.requireNonNull ( imageUri ) );
                        if (quality > 75 && quality <= 98) {




                            bitmap.compress ( Bitmap.CompressFormat.JPEG, quality, fos );

                            Objects.requireNonNull ( fos );
                            resolver.update ( imageUri, contentValues, null, null );



                            updatedata1 ( imageUri );

//                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
                        }
                        else if (quality > 50 && quality <= 75) {



                            bitmap.compress ( Bitmap.CompressFormat.JPEG, quality, fos );

                            Objects.requireNonNull ( fos );
                            resolver.update ( imageUri, contentValues, null, null );


                            updatedata1 ( imageUri );


//                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
                        }



                        else if (quality > 20 && quality <= 50) {




                            bitmap.compress ( Bitmap.CompressFormat.JPEG, quality, fos );

                            Objects.requireNonNull ( fos );
                            resolver.update ( imageUri, contentValues, null, null );

                            updatedata1 ( imageUri );

//                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
                        }

                        // complete
                        else if (quality>10&&quality<=20) {

//135


                            //10




                            bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                            Objects.requireNonNull ( fos );
                            resolver.update ( imageUri, contentValues, null, null );



                            updatedata1 ( imageUri );
// ainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();

                        }
                        // new SingleMediaScanner ( CropperActivity.this, new File (  Environment.DIRECTORY_PICTURES  ));

                        else if (quality<=10) {


//      if (requrevalue>10&&requrevalue<14&&globleurisize<800){
//    quality+=12;
//}

                            if (quality==10)  {

                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );






                                updatedata1 ( imageUri );
                            }

                            else if (quality==9){

                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );






                                updatedata1 ( imageUri );
                            }
                            else if (quality==8){

                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );






                                updatedata1 ( imageUri );
                            }
                            else if (quality==7){

                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );






                                updatedata1 ( imageUri );
                            }
                            else if (quality==6){

                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );






                                updatedata1 ( imageUri );
                            }
                            else if (quality==5){

                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );






                                updatedata1 ( imageUri );
                            }
                            else if (quality==4){

                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );






                                updatedata1 ( imageUri );
                            }
                            else if (quality==3){
                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );






                                updatedata1 ( imageUri );

                            }
                            else{


                                if (requrevalue>21){
                                    quality=3;
                                }
                                else if(requrevalue>10&&requrevalue<=21){
                                    quality=2;
                                }
                                else{
                                    quality=1;
                                }
                                bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                                Objects.requireNonNull ( fos );
                                resolver.update ( imageUri, contentValues, null, null );






                                updatedata1 ( imageUri );
                            }

                            //134 -700

                            //120-600



                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace ();
                }








            }

    else if(globleurisize>200&&globleurisize<=800) {
              Toast.makeText ( MainActivity.this, "" + quality, Toast.LENGTH_SHORT ).show ();
              Float w = Float.parseFloat ( String.valueOf ( Width.getText () ) );
              Float H = Float.parseFloat ( String.valueOf ( hight.getText () ) );
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap ( getContentResolver (), Uri.parse ( file ) );
              Bitmap bitmap = BitmapFactory.decodeFile ( file1.getAbsolutePath () );
    if (requrevalue<35&&requrevalue>10){
        w=w/2;
        H=H/2;
    }
    if (requrevalue<=10){
        w=w/4;
        H=H/4;
    }


              bitmap = Bitmap.createScaledBitmap ( bitmap, w.intValue (), H.intValue (), false );
              OutputStream fos = null;


              Uri imageUri = null;
              try {

                  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

                      ContentResolver resolver = getContentResolver ();
                      ContentValues contentValues = new ContentValues ();
                      int i = new Random ().nextInt ( 1234567890 );
                      contentValues.put ( MediaStore.MediaColumns.DISPLAY_NAME, "Image_" + i + ".jpg" );
                      contentValues.put ( MediaStore.MediaColumns.MIME_TYPE, "image/jpeg" );
                      contentValues.put ( MediaStore.Images.Media.TITLE, i );


                      contentValues.put ( MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis () / 1000 );
                      contentValues.put ( MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis () );
                      contentValues.put ( MediaStore.Images.Media.IS_PENDING, 0 );
                      contentValues.put ( MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/" + s );
                      imageUri = resolver.insert ( MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues );

                      fos = resolver.openOutputStream ( Objects.requireNonNull ( imageUri ) );
                      if (quality > 75 && quality <= 98) {


                          bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                          Objects.requireNonNull ( fos );
                          resolver.update ( imageUri, contentValues, null, null );


                          updatedata1 ( imageUri );

//                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
                      } else if (quality > 50 && quality <= 75) {


                          bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                          Objects.requireNonNull ( fos );
                          resolver.update ( imageUri, contentValues, null, null );


                          updatedata1 ( imageUri );


//                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
                      } else if (quality > 20 && quality <= 50) {


                          bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                          Objects.requireNonNull ( fos );
                          resolver.update ( imageUri, contentValues, null, null );

                          updatedata1 ( imageUri );

//                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
                      }

                      // complete
                      else if (quality > 10 && quality <= 20) {

//135


                          //10


                          quality += 12;


                          bitmap.compress ( Bitmap.CompressFormat.JPEG, quality, fos );

                          Objects.requireNonNull ( fos );
                          resolver.update ( imageUri, contentValues, null, null );


                          updatedata1 ( imageUri );
// ainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();

                      }
                      // new SingleMediaScanner ( CropperActivity.this, new File (  Environment.DIRECTORY_PICTURES  ));

                      else if (quality <= 10) {


//      if (requrevalue>10&&requrevalue<14&&globleurisize<800){
//    quality+=12;
//}

                          if (quality == 10) {
                              quality += 2;
                              bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                              Objects.requireNonNull ( fos );
                              resolver.update ( imageUri, contentValues, null, null );


                              updatedata1 ( imageUri );
                          } else if (quality == 9) {
                              quality += 2;
                              bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                              Objects.requireNonNull ( fos );
                              resolver.update ( imageUri, contentValues, null, null );


                              updatedata1 ( imageUri );
                          } else if (quality == 8) {
                              quality += 2;
                              bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                              Objects.requireNonNull ( fos );
                              resolver.update ( imageUri, contentValues, null, null );


                              updatedata1 ( imageUri );
                          } else if (quality == 7) {
                              quality += 2;
                              bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                              Objects.requireNonNull ( fos );
                              resolver.update ( imageUri, contentValues, null, null );


                              updatedata1 ( imageUri );
                          } else if (quality == 6) {
                              quality += 2;
                              bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                              Objects.requireNonNull ( fos );
                              resolver.update ( imageUri, contentValues, null, null );


                              updatedata1 ( imageUri );
                          } else if (quality == 5) {
                              quality += 1;
                              bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                              Objects.requireNonNull ( fos );
                              resolver.update ( imageUri, contentValues, null, null );


                              updatedata1 ( imageUri );
                          } else if (quality == 4) {
                              quality += 1;
                              bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                              Objects.requireNonNull ( fos );
                              resolver.update ( imageUri, contentValues, null, null );


                              updatedata1 ( imageUri );
                          } else if (quality == 3) {
                              quality += 1;
                              bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                              Objects.requireNonNull ( fos );
                              resolver.update ( imageUri, contentValues, null, null );


                              updatedata1 ( imageUri );

                          } else {
                              if (requrevalue>21){
                                  quality=3;
                              }
                              else if(requrevalue>10&&requrevalue<=21){
                                  quality=2;
                              }
                              else{
                                  quality=1;
                              }
                              bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                              Objects.requireNonNull ( fos );
                              resolver.update ( imageUri, contentValues, null, null );


                              updatedata1 ( imageUri );
                          }

                          //134 -700

                          //120-600


                      }
                  }
              } catch (FileNotFoundException e) {
                  e.printStackTrace ();
              }
          }
else{    Toast.makeText (MainActivity.this , ""+quality, Toast.LENGTH_SHORT ).show ();
              Float w = Float.parseFloat ( String.valueOf ( Width.getText () ) );
              Float H = Float.parseFloat ( String.valueOf ( hight.getText () ) );
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap ( getContentResolver (), Uri.parse ( file ) );
              Bitmap bitmap = BitmapFactory.decodeFile ( file1.getAbsolutePath () );

    if (requrevalue<30 ){
        w=w/2;
        H=H/2;
    }

              bitmap = Bitmap.createScaledBitmap ( bitmap, w.intValue (), H.intValue (), false );
              OutputStream fos = null;


              Uri imageUri = null;
              try {

                  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

                      ContentResolver resolver = getContentResolver ();
                      ContentValues contentValues = new ContentValues ();
                      int i = new Random ().nextInt ( 1234567890 );
                      contentValues.put ( MediaStore.MediaColumns.DISPLAY_NAME, "Image_" + i + ".jpg" );
                      contentValues.put ( MediaStore.MediaColumns.MIME_TYPE, "image/jpeg" );
                      contentValues.put ( MediaStore.Images.Media.TITLE, i );


                      contentValues.put ( MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis () / 1000 );
                      contentValues.put ( MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis () );
                      contentValues.put ( MediaStore.Images.Media.IS_PENDING, 0);
                      contentValues.put ( MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/" + s );
                      imageUri = resolver.insert ( MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues );

                      fos = resolver.openOutputStream ( Objects.requireNonNull ( imageUri ) );
                      if (quality > 75 && quality <= 98) {

                          quality=50;


                          bitmap.compress ( Bitmap.CompressFormat.JPEG, quality, fos );

                          Objects.requireNonNull ( fos );
                          resolver.update ( imageUri, contentValues, null, null );



                          updatedata1 ( imageUri );

//                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
                      }
                      else if (quality > 50 && quality <= 75) {


                          quality=25;

                          bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                          Objects.requireNonNull ( fos );
                          resolver.update ( imageUri, contentValues, null, null );


                          updatedata1 ( imageUri );


//                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
                      }



                      else if (quality > 20 && quality <= 50) {


                          quality -= 16;

                          bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                          Objects.requireNonNull ( fos );
                          resolver.update ( imageUri, contentValues, null, null );

                          updatedata1 ( imageUri );

//                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
                      }

                      // complete
                      else if ( quality<=20) {

//135


                          //10



                          quality = 2;


                          bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );

                          Objects.requireNonNull ( fos );
                          resolver.update ( imageUri, contentValues, null, null );



                          updatedata1 ( imageUri );
// ainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();

                      }
                      // new SingleMediaScanner ( CropperActivity.this, new File (  Environment.DIRECTORY_PICTURES  ));


                  }
                  else{

                  }
              } catch (FileNotFoundException e) {
                  e.printStackTrace ();
              }

          }


        }
    }
        ;}


    public  void filesize(){
  {
    Cursor cursor= getContentResolver ().query ( Uri.parse ( globleuri ),null,null,null ,null);

    int size=cursor.getColumnIndex ( OpenableColumns.SIZE );

    cursor.moveToFirst ();
    float m = 0;
    String vv="kb";

    int v= Integer.parseInt ( Long.toString ( cursor.getLong ( size ) ) );
    if (v>=1024){
        m= Integer. valueOf (  v/1024);
        vv="kb";}
    globleurisize=m;
    sizereal.setText ( "File Size:"+m+""+vv );
    cursor.close ();
}




};

    public  void filesize1(){


           Cursor cursor= getContentResolver ().query ( Uri.parse ( compressuri ),null,null,null ,null);

           int size=cursor.getColumnIndex ( OpenableColumns.SIZE );

           cursor.moveToFirst ();
           float m = 0;
           String vv="kb";

           int v= Integer.parseInt ( Long.toString ( cursor.getLong ( size ) ) );
           if (v>=1024){
               m= Integer. valueOf (  v/1024);
               vv="kb";}

           getSizeafter.setText ( "File Size:"+m+""+vv );
cursor.close ();


    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult ( requestCode, resultCode, data );
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult ( data );
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri ();
                newimagecrop.launch ( new Intent (MainActivity.this,CropperActivity.class).putExtra ( "cropimage",  resultUri.toString ()) );

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError ();
            }
        }
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK
        ){

            try {
                File file_ = new File(picturePath);
                Uri uri_ = FileProvider.getUriForFile(this,
                        "com.bhagorplay.mycompresser", file_);
                                 newimagecrop.launch ( new Intent (MainActivity.this,CropperActivity.class).putExtra ( "cropimage", uri_.toString () ) );
            } catch (/*IO*/Exception e) {
                e.printStackTrace();
            }
        }
    }







    public void  updatedata1(Uri t){
try {
    progressBar.setVisibility ( View.GONE );
    q12.setVisibility ( View.GONE );
    chen1 = 1;
    share.setEnabled ( true );
    compressuri = String.valueOf ( t );
    BitmapFactory.Options options = new BitmapFactory.Options ();
    options.inJustDecodeBounds = true;
    try {
        BitmapFactory.decodeStream ( getContentResolver ().openInputStream ( Uri.parse ( compressuri ) ), null, options );
    } catch (FileNotFoundException e) {
        e.printStackTrace ();
    }
    Width.setText ( "" + options.outWidth );
    hight.setText ( "" + options.outHeight );
    Glide.with ( MainActivity.this ).load ( compressuri ).into ( compressimage );

    filesize1 ();
    updatedata ( Uri.parse ( globleuri ) );
    startActivity ( new Intent ( MainActivity.this, MainActivity2.class ).putExtra ( "uri", compressuri.toString () ).putExtra ( "uridata", u ) );
}catch(Exception H){
    progressBar.setVisibility ( View.GONE );
    q12.setVisibility ( View.GONE );
    chen1 = 1;
    share.setEnabled ( true );
    compressuri = String.valueOf ( t );
    BitmapFactory.Options options = new BitmapFactory.Options ();
    options.inJustDecodeBounds = true;
    try {
        BitmapFactory.decodeStream ( getContentResolver ().openInputStream ( Uri.parse ( compressuri ) ), null, options );
    } catch (FileNotFoundException e) {
        e.printStackTrace ();
    }
    Width.setText ( "" + options.outWidth );
    hight.setText ( "" + options.outHeight );
    Glide.with ( MainActivity.this ).load ( compressuri ).into ( compressimage );

    filesize1 ();
    updatedata ( Uri.parse ( globleuri ) );
    startActivity ( new Intent ( MainActivity.this, MainActivity2.class ).putExtra ( "uri", compressuri.toString () ).putExtra ( "uridata", u ) );
}
    }


    public void  updatedata(Uri t){

     globleuri= String.valueOf ( t );
     BitmapFactory.Options options=new BitmapFactory.Options ();
     options.inJustDecodeBounds=true;
     try {
         BitmapFactory.decodeStream ( getContentResolver ().openInputStream ( Uri.parse ( globleuri ) ),null,options );
     } catch (FileNotFoundException e) {
         e.printStackTrace ();
     }
     Width.setText ( ""+options.outWidth );
     hight.setText ( ""+options.outHeight );
     Glide.with ( MainActivity.this ).load ( globleuri ).into ( imageView );
  //   Toast.makeText ( MainActivity.this,"" +globleuri,Toast.LENGTH_LONG).show ();;
     filesize ();

 }


    @Override
    public void onBackPressed() {

            finish ();
        super.onBackPressed ();

    }

    public float filel(Uri Myuri){
        Cursor cursor= getContentResolver ().query ( Uri.parse ( String.valueOf ( Myuri ) ),null,null,null ,null);

        int size=cursor.getColumnIndex ( OpenableColumns.SIZE );

        cursor.moveToFirst ();
        float m = 0;
        String vv="kb";

        int v= Integer.parseInt ( Long.toString ( cursor.getLong ( size ) ) );
        if (v>=1024){
            m= Integer. valueOf (  v/1024);
            vv="kb";}
        return m;
    }

    @Override
    protected void onResume() {
        super.onResume ();
    }

    private void loadAds() {
    }
}