package com.bhagorplay.mycompresser;

import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import androidx.core.content.FileProvider;
import androidx.loader.content.CursorLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
import java.util.Random;

public class EmptyCode {
//    private void galleryAddPic() {
//        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//        File f = new File(currentPhotoPath);
//        Uri contentUri = Uri.fromFile(f);
//        mediaScanIntent.setData(contentUri);
//        this.sendBroadcast(mediaScanIntent);
//    }
//    private void setPic() {
//        // Get the dimensions of the View
//        int targetW = imageView.getWidth();
//        int targetH = imageView.getHeight();
//
//        // Get the dimensions of the bitmap
//        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
//        bmOptions.inJustDecodeBounds = true;
//
//        BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
//
//        int photoW = bmOptions.outWidth;
//        int photoH = bmOptions.outHeight;
//
//        // Determine how much to scale down the image
//        int scaleFactor = Math.max(1, Math.min(photoW/targetW, photoH/targetH));
//
//        // Decode the image file into a Bitmap sized to fill the View
//        bmOptions.inJustDecodeBounds = false;
//        bmOptions.inSampleSize = scaleFactor;
//        bmOptions.inPurgeable = true;
//
//        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
//        imageView.setImageBitmap(bitmap);
//    }
//private void dispatchTakePictureIntent() {
//    Intent takePictureIntent = new Intent( MediaStore.ACTION_IMAGE_CAPTURE);
//    // Ensure that there's a camera activity to handle the intent
//    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//        // Create the File where the photo should go
//        File photoFile = null;
//        try {
//            photoFile = createImageFile();
//        } catch (IOException ex) {
//            // Error occurred while creating the File
//
//        }
//        // Continue only if the File was successfully created
//        if (photoFile != null) {
//            Uri photoURI = FileProvider.getUriForFile(this,
//                    "com.bhagorplay.mycompresser",
//                    photoFile);
//            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
//            r.launch (   takePictureIntent );
//        }
//    }
//













//bitmap Size

    public static int sizeOf(Bitmap data) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR1) {
            return data.getRowBytes() * data.getHeight();
        } else if (Build.VERSION.SDK_INT<Build.VERSION_CODES.KITKAT){
            return data.getByteCount();
        } else{
            return data.getAllocationByteCount();
        }
    }

//    private String getRealPathFromURI(Uri contentUri) {
//        String[] proj = { MediaStore.Images.Media.DATA };
//        CursorLoader loader = new CursorLoader (MainActivity.this, contentUri, proj, null, null, null);
//        Cursor cursor = loader.loadInBackground();
//        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//        cursor.moveToFirst();
//        String result = cursor.getString(column_index);
//        cursor.close();
//        return result;
//    }
//      if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.Q){
//        progressBar.setVisibility ( View.VISIBLE );
//        InputStream imageStream = null;
//        try {
//            imageStream =  getContentResolver().openInputStream( Uri.parse ( globleuri ) );
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        Bitmap bitmap= BitmapFactory.decodeStream(imageStream);
//
//
//
//        {
//            {
//
//
//
//                {
//                    if(globleurisize>2600){
//                        Toast.makeText (MainActivity.this , ""+quality, Toast.LENGTH_SHORT ).show ();
//                        Float w = Float.parseFloat ( String.valueOf ( Width.getText () ) );
//                        Float H = Float.parseFloat ( String.valueOf ( hight.getText () ) );
////                Bitmap bitmap = MediaStore.Images.Media.getBitmap ( getContentResolver (), Uri.parse ( file ) );
//
//                        if (requrevalue<120 ){
//                            w=w/2;
//                            H=H/2;
//                        }
//
//
//                        bitmap = Bitmap.createScaledBitmap ( bitmap, w.intValue (), H.intValue (), false );
//                        OutputStream fos = null;
//
//
//                        Uri imageUri = null;
//                        try {
//
//                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//
//                                ContentResolver resolver = getContentResolver ();
//                                ContentValues contentValues = new ContentValues ();
//                                int i = new Random ().nextInt ( 1234567890 );
//                                contentValues.put ( MediaStore.MediaColumns.DISPLAY_NAME, "Image_" + i + ".jpg" );
//                                contentValues.put ( MediaStore.MediaColumns.MIME_TYPE, "image/jpeg" );
//                                contentValues.put ( MediaStore.Images.Media.TITLE, i );
//
//
//                                contentValues.put ( MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis () / 1000 );
//                                contentValues.put ( MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis () );
//                                contentValues.put ( MediaStore.Images.Media.IS_PENDING, 0);
//                                contentValues.put ( MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/" + s );
//                                imageUri = resolver.insert ( MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues );
//
//                                fos = resolver.openOutputStream ( Objects.requireNonNull ( imageUri ) );
//                                if (quality > 75 && quality <= 98) {
//
//
//
//
//                                    bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                    Objects.requireNonNull ( fos );
//                                    resolver.update ( imageUri, contentValues, null, null );
//
//
//
//                                    updatedata1 ( imageUri );
//
////                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
//                                }
//                                else if (quality > 50 && quality <= 75) {
//
//
//
//                                    bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                    Objects.requireNonNull ( fos );
//                                    resolver.update ( imageUri, contentValues, null, null );
//
//
//                                    updatedata1 ( imageUri );
//
//
////                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
//                                }
//
//
//
//                                else if (quality > 20 && quality <= 50) {
//
//
//
//
//                                    bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                    Objects.requireNonNull ( fos );
//                                    resolver.update ( imageUri, contentValues, null, null );
//
//                                    updatedata1 ( imageUri );
//
////                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
//                                }
//
//                                // complete
//                                else if (quality>10&&quality<=20) {
//
////135
//
//
//                                    //10
//
//
//
//                                    quality += 12;
//
//
//                                    bitmap.compress ( Bitmap.CompressFormat.JPEG, quality, fos );
//
//                                    Objects.requireNonNull ( fos );
//                                    resolver.update ( imageUri, contentValues, null, null );
//
//
//
//                                    updatedata1 ( imageUri );
//// ainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
//
//                                }
//                                // new SingleMediaScanner ( CropperActivity.this, new File (  Environment.DIRECTORY_PICTURES  ));
//
//                                else if (quality<=10) {
//
//
////      if (requrevalue>10&&requrevalue<14&&globleurisize<800){
////    quality+=12;
////}
//
//                                    if (quality==10)  {
//
//                                        bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                        Objects.requireNonNull ( fos );
//                                        resolver.update ( imageUri, contentValues, null, null );
//
//
//
//
//
//
//                                        updatedata1 ( imageUri );
//                                    }
//
//                                    else if (quality==9){
//
//                                        bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                        Objects.requireNonNull ( fos );
//                                        resolver.update ( imageUri, contentValues, null, null );
//
//
//
//
//
//
//                                        updatedata1 ( imageUri );
//                                    }
//                                    else if (quality==8){
//
//                                        bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                        Objects.requireNonNull ( fos );
//                                        resolver.update ( imageUri, contentValues, null, null );
//
//
//
//
//
//
//                                        updatedata1 ( imageUri );
//                                    }
//                                    else if (quality==7){
//
//                                        bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                        Objects.requireNonNull ( fos );
//                                        resolver.update ( imageUri, contentValues, null, null );
//
//
//
//
//
//
//                                        updatedata1 ( imageUri );
//                                    }
//                                    else if (quality==6){
//
//                                        bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                        Objects.requireNonNull ( fos );
//                                        resolver.update ( imageUri, contentValues, null, null );
//
//
//
//
//
//
//                                        updatedata1 ( imageUri );
//                                    }
//                                    else if (quality==5){
//                                        quality=4;
//                                        bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                        Objects.requireNonNull ( fos );
//                                        resolver.update ( imageUri, contentValues, null, null );
//
//
//
//
//
//
//                                        updatedata1 ( imageUri );
//                                    }
//                                    else if (quality==4){
//
//                                        bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                        Objects.requireNonNull ( fos );
//                                        resolver.update ( imageUri, contentValues, null, null );
//
//
//
//
//
//
//                                        updatedata1 ( imageUri );
//                                    }
//                                    else if (quality==3){
//                                        bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                        Objects.requireNonNull ( fos );
//                                        resolver.update ( imageUri, contentValues, null, null );
//
//
//
//
//
//
//                                        updatedata1 ( imageUri );
//
//                                    }
//                                    else{
//
//                                        if (requrevalue>21){
//                                            quality=3;
//                                        }
//                                        else if(requrevalue>10&&requrevalue<=21){
//                                            quality=1;
//                                        }
//                                        else{
//                                            quality=0;
//                                        }
//                                        bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                        Objects.requireNonNull ( fos );
//                                        resolver.update ( imageUri, contentValues, null, null );
//
//
//
//
//
//
//                                        updatedata1 ( imageUri );
//                                    }
//
//                                    //134 -700
//
//                                    //120-600
//
//
//
//                                }
//                            }
//                        } catch (FileNotFoundException e) {
//                            e.printStackTrace ();
//                        }
//                    }
//                    else   if(globleurisize>1350&&globleurisize<=2600)  {
//                        Toast.makeText (MainActivity.this , ""+quality, Toast.LENGTH_SHORT ).show ();
//                        Float w = Float.parseFloat ( String.valueOf ( Width.getText () ) );
//                        Float H = Float.parseFloat ( String.valueOf ( hight.getText () ) );
////                Bitmap bitmap = MediaStore.Images.Media.getBitmap ( getContentResolver (), Uri.parse ( file ) );
//
//                        if (requrevalue<80 ){
//                            w=w/2;
//                            H=H/2;
//                        }
//
//
//                        bitmap = Bitmap.createScaledBitmap ( bitmap, w.intValue (), H.intValue (), false );
//                        OutputStream fos = null;
//
//
//                        Uri imageUri = null;
//                        try {
//
//                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//
//                                ContentResolver resolver = getContentResolver ();
//                                ContentValues contentValues = new ContentValues ();
//                                int i = new Random ().nextInt ( 1234567890 );
//                                contentValues.put ( MediaStore.MediaColumns.DISPLAY_NAME, "Image_" + i + ".jpg" );
//                                contentValues.put ( MediaStore.MediaColumns.MIME_TYPE, "image/jpeg" );
//                                contentValues.put ( MediaStore.Images.Media.TITLE, i );
//
//
//                                contentValues.put ( MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis () / 1000 );
//                                contentValues.put ( MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis () );
//                                contentValues.put ( MediaStore.Images.Media.IS_PENDING, 0);
//                                contentValues.put ( MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/" + s );
//                                imageUri = resolver.insert ( MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues );
//
//                                fos = resolver.openOutputStream ( Objects.requireNonNull ( imageUri ) );
//                                if (quality > 75 && quality <= 98) {
//
//
//
//
//                                    bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                    Objects.requireNonNull ( fos );
//                                    resolver.update ( imageUri, contentValues, null, null );
//
//
//
//                                    updatedata1 ( imageUri );
//
////                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
//                                }
//                                else if (quality > 50 && quality <= 75) {
//
//
//
//                                    bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                    Objects.requireNonNull ( fos );
//                                    resolver.update ( imageUri, contentValues, null, null );
//
//
//                                    updatedata1 ( imageUri );
//
//
////                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
//                                }
//
//
//
//                                else if (quality > 20 && quality <= 50) {
//
//
//
//
//                                    bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                    Objects.requireNonNull ( fos );
//                                    resolver.update ( imageUri, contentValues, null, null );
//
//                                    updatedata1 ( imageUri );
//
////                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
//                                }
//
//                                // complete
//                                else if (quality>10&&quality<=20) {
//
////135
//
//
//                                    //10
//
//
//
//                                    quality += 12;
//
//
//                                    bitmap.compress ( Bitmap.CompressFormat.JPEG, quality, fos );
//
//                                    Objects.requireNonNull ( fos );
//                                    resolver.update ( imageUri, contentValues, null, null );
//
//
//
//                                    updatedata1 ( imageUri );
//// ainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
//
//                                }
//                                // new SingleMediaScanner ( CropperActivity.this, new File (  Environment.DIRECTORY_PICTURES  ));
//
//                                else if (quality<=10) {
//
//
////      if (requrevalue>10&&requrevalue<14&&globleurisize<800){
////    quality+=12;
////}
//
//                                    if (quality==10)  {
//
//                                        bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                        Objects.requireNonNull ( fos );
//                                        resolver.update ( imageUri, contentValues, null, null );
//
//
//
//
//
//
//                                        updatedata1 ( imageUri );
//                                    }
//
//                                    else if (quality==9){
//
//                                        bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                        Objects.requireNonNull ( fos );
//                                        resolver.update ( imageUri, contentValues, null, null );
//
//
//
//
//
//
//                                        updatedata1 ( imageUri );
//                                    }
//                                    else if (quality==8){
//
//                                        bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                        Objects.requireNonNull ( fos );
//                                        resolver.update ( imageUri, contentValues, null, null );
//
//
//
//
//
//
//                                        updatedata1 ( imageUri );
//                                    }
//                                    else if (quality==7){
//
//                                        bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                        Objects.requireNonNull ( fos );
//                                        resolver.update ( imageUri, contentValues, null, null );
//
//
//
//
//
//
//                                        updatedata1 ( imageUri );
//                                    }
//                                    else if (quality==6){
//
//                                        bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                        Objects.requireNonNull ( fos );
//                                        resolver.update ( imageUri, contentValues, null, null );
//
//
//
//
//
//
//                                        updatedata1 ( imageUri );
//                                    }
//                                    else if (quality==5){
//                                        quality=4;
//                                        bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                        Objects.requireNonNull ( fos );
//                                        resolver.update ( imageUri, contentValues, null, null );
//
//
//
//
//
//
//                                        updatedata1 ( imageUri );
//                                    }
//                                    else if (quality==4){
//
//                                        bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                        Objects.requireNonNull ( fos );
//                                        resolver.update ( imageUri, contentValues, null, null );
//
//
//
//
//
//
//                                        updatedata1 ( imageUri );
//                                    }
//                                    else if (quality==3){
//                                        bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                        Objects.requireNonNull ( fos );
//                                        resolver.update ( imageUri, contentValues, null, null );
//
//
//
//
//
//
//                                        updatedata1 ( imageUri );
//
//                                    }
//                                    else{
//
//                                        if (requrevalue>21){
//                                            quality=4;
//                                        }
//                                        else if(requrevalue>10&&requrevalue<=21){
//                                            quality=2;
//                                        }
//                                        else{
//                                            quality=1;
//                                        }
//                                        bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                        Objects.requireNonNull ( fos );
//                                        resolver.update ( imageUri, contentValues, null, null );
//
//
//
//
//
//
//                                        updatedata1 ( imageUri );
//                                    }
//
//                                    //134 -700
//
//                                    //120-600
//
//
//
//                                }
//                            }
//                        } catch (FileNotFoundException e) {
//                            e.printStackTrace ();
//                        }
//                    }
//                    else if (globleurisize>800&&globleurisize<=1350){
//                        Toast.makeText (MainActivity.this , ""+quality, Toast.LENGTH_SHORT ).show ();
//                        Float w = Float.parseFloat ( String.valueOf ( Width.getText () ) );
//                        Float H = Float.parseFloat ( String.valueOf ( hight.getText () ) );
////                Bitmap bitmap = MediaStore.Images.Media.getBitmap ( getContentResolver (), Uri.parse ( file ) );
//
//                        if (requrevalue<60 ){
//                            w=w/2;
//                            H=H/2;
//                        }
//
//                        bitmap = Bitmap.createScaledBitmap ( bitmap, w.intValue (), H.intValue (), false );
//                        OutputStream fos = null;
//
//
//                        Uri imageUri = null;
//                        try {
//
//                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//
//                                ContentResolver resolver = getContentResolver ();
//                                ContentValues contentValues = new ContentValues ();
//                                int i = new Random ().nextInt ( 1234567890 );
//                                contentValues.put ( MediaStore.MediaColumns.DISPLAY_NAME, "Image_" + i + ".jpg" );
//                                contentValues.put ( MediaStore.MediaColumns.MIME_TYPE, "image/jpeg" );
//                                contentValues.put ( MediaStore.Images.Media.TITLE, i );
//
//
//                                contentValues.put ( MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis () / 1000 );
//                                contentValues.put ( MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis () );
//                                contentValues.put ( MediaStore.Images.Media.IS_PENDING, 0);
//                                contentValues.put ( MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/" + s );
//                                imageUri = resolver.insert ( MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues );
//
//                                fos = resolver.openOutputStream ( Objects.requireNonNull ( imageUri ) );
//                                if (quality > 75 && quality <= 98) {
//
//
//
//
//                                    bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                    Objects.requireNonNull ( fos );
//                                    resolver.update ( imageUri, contentValues, null, null );
//
//
//
//                                    updatedata1 ( imageUri );
//
////                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
//                                }
//                                else if (quality > 50 && quality <= 75) {
//
//
//
//                                    bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                    Objects.requireNonNull ( fos );
//                                    resolver.update ( imageUri, contentValues, null, null );
//
//
//                                    updatedata1 ( imageUri );
//
//
////                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
//                                }
//
//
//
//                                else if (quality > 20 && quality <= 50) {
//
//
//
//
//                                    bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                    Objects.requireNonNull ( fos );
//                                    resolver.update ( imageUri, contentValues, null, null );
//
//                                    updatedata1 ( imageUri );
//
////                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
//                                }
//
//                                // complete
//                                else if (quality>10&&quality<=20) {
//
////135
//
//
//                                    //10
//
//
//
//                                    quality += 12;
//
//
//                                    bitmap.compress ( Bitmap.CompressFormat.JPEG, quality, fos );
//
//                                    Objects.requireNonNull ( fos );
//                                    resolver.update ( imageUri, contentValues, null, null );
//
//
//
//                                    updatedata1 ( imageUri );
//// ainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
//
//                                }
//                                // new SingleMediaScanner ( CropperActivity.this, new File (  Environment.DIRECTORY_PICTURES  ));
//
//                                else if (quality<=10) {
//
//
////      if (requrevalue>10&&requrevalue<14&&globleurisize<800){
////    quality+=12;
////}
//
//                                    if (quality==10)  {
//                                        quality+=2;
//                                        bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                        Objects.requireNonNull ( fos );
//                                        resolver.update ( imageUri, contentValues, null, null );
//
//
//
//
//
//
//                                        updatedata1 ( imageUri );
//                                    }
//
//                                    else if (quality==9){
//                                        quality+=2;
//                                        bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                        Objects.requireNonNull ( fos );
//                                        resolver.update ( imageUri, contentValues, null, null );
//
//
//
//
//
//
//                                        updatedata1 ( imageUri );
//                                    }
//                                    else if (quality==8){
//                                        quality+=2;
//                                        bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                        Objects.requireNonNull ( fos );
//                                        resolver.update ( imageUri, contentValues, null, null );
//
//
//
//
//
//
//                                        updatedata1 ( imageUri );
//                                    }
//                                    else if (quality==7){
//                                        quality+=2;
//                                        bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                        Objects.requireNonNull ( fos );
//                                        resolver.update ( imageUri, contentValues, null, null );
//
//
//
//
//
//
//                                        updatedata1 ( imageUri );
//                                    }
//                                    else if (quality==6){
//                                        quality+=2;
//                                        bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                        Objects.requireNonNull ( fos );
//                                        resolver.update ( imageUri, contentValues, null, null );
//
//
//
//
//
//
//                                        updatedata1 ( imageUri );
//                                    }
//                                    else if (quality==5){
//                                        quality+=1;
//                                        bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                        Objects.requireNonNull ( fos );
//                                        resolver.update ( imageUri, contentValues, null, null );
//
//
//
//
//
//
//                                        updatedata1 ( imageUri );
//                                    }
//                                    else if (quality==4){
//                                        quality+=1;
//                                        bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                        Objects.requireNonNull ( fos );
//                                        resolver.update ( imageUri, contentValues, null, null );
//
//
//
//
//
//
//                                        updatedata1 ( imageUri );
//                                    }
//                                    else if (quality==3){   quality+=1;
//                                        bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                        Objects.requireNonNull ( fos );
//                                        resolver.update ( imageUri, contentValues, null, null );
//
//
//
//
//
//
//                                        updatedata1 ( imageUri );
//
//                                    }
//                                    else{
//
//                                        if (requrevalue>17){
//                                            quality+=15;
//                                        }
//                                        else if(requrevalue>12&&requrevalue<=17){
//                                            quality+=12;
//                                        }
//                                        else{
//                                            quality+=8;
//                                        }
//                                        bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                        Objects.requireNonNull ( fos );
//                                        resolver.update ( imageUri, contentValues, null, null );
//
//
//
//
//
//
//                                        updatedata1 ( imageUri );
//                                    }
//
//                                    //134 -700
//
//                                    //120-600
//
//
//
//                                }
//                            }
//                        } catch (FileNotFoundException e) {
//                            e.printStackTrace ();
//                        }
//
//
//
//
//
//
//
//
//                    }
//
//                    else if(globleurisize>200&&globleurisize<=800) {
//                        Toast.makeText ( MainActivity.this, "" + quality, Toast.LENGTH_SHORT ).show ();
//                        Float w = Float.parseFloat ( String.valueOf ( Width.getText () ) );
//                        Float H = Float.parseFloat ( String.valueOf ( hight.getText () ) );
////                Bitmap bitmap = MediaStore.Images.Media.getBitmap ( getContentResolver (), Uri.parse ( file ) );
//
//                        if (requrevalue < 40  ) {
//                            w = w / 2;
//                            H = H / 2;
//                        }
//
//                        bitmap = Bitmap.createScaledBitmap ( bitmap, w.intValue (), H.intValue (), false );
//                        OutputStream fos = null;
//
//
//                        Uri imageUri = null;
//                        try {
//
//                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//
//                                ContentResolver resolver = getContentResolver ();
//                                ContentValues contentValues = new ContentValues ();
//                                int i = new Random ().nextInt ( 1234567890 );
//                                contentValues.put ( MediaStore.MediaColumns.DISPLAY_NAME, "Image_" + i + ".jpg" );
//                                contentValues.put ( MediaStore.MediaColumns.MIME_TYPE, "image/jpeg" );
//                                contentValues.put ( MediaStore.Images.Media.TITLE, i );
//
//
//                                contentValues.put ( MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis () / 1000 );
//                                contentValues.put ( MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis () );
//                                contentValues.put ( MediaStore.Images.Media.IS_PENDING, 0 );
//                                contentValues.put ( MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/" + s );
//                                imageUri = resolver.insert ( MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues );
//
//                                fos = resolver.openOutputStream ( Objects.requireNonNull ( imageUri ) );
//                                if (quality > 75 && quality <= 98) {
//
//
//                                    bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                    Objects.requireNonNull ( fos );
//                                    resolver.update ( imageUri, contentValues, null, null );
//
//
//                                    updatedata1 ( imageUri );
//
////                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
//                                } else if (quality > 50 && quality <= 75) {
//
//
//                                    bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                    Objects.requireNonNull ( fos );
//                                    resolver.update ( imageUri, contentValues, null, null );
//
//
//                                    updatedata1 ( imageUri );
//
//
////                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
//                                } else if (quality > 20 && quality <= 50) {
//
//
//                                    bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                    Objects.requireNonNull ( fos );
//                                    resolver.update ( imageUri, contentValues, null, null );
//
//                                    updatedata1 ( imageUri );
//
////                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
//                                }
//
//                                // complete
//                                else if (quality > 10 && quality <= 20) {
//
////135
//
//
//                                    //10
//
//
//                                    quality += 12;
//
//
//                                    bitmap.compress ( Bitmap.CompressFormat.JPEG, quality, fos );
//
//                                    Objects.requireNonNull ( fos );
//                                    resolver.update ( imageUri, contentValues, null, null );
//
//
//                                    updatedata1 ( imageUri );
//// ainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
//
//                                }
//                                // new SingleMediaScanner ( CropperActivity.this, new File (  Environment.DIRECTORY_PICTURES  ));
//
//                                else if (quality <= 10) {
//
//
////      if (requrevalue>10&&requrevalue<14&&globleurisize<800){
////    quality+=12;
////}
//
//                                    if (quality == 10) {
//                                        quality += 2;
//                                        bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                        Objects.requireNonNull ( fos );
//                                        resolver.update ( imageUri, contentValues, null, null );
//
//
//                                        updatedata1 ( imageUri );
//                                    } else if (quality == 9) {
//                                        quality += 2;
//                                        bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                        Objects.requireNonNull ( fos );
//                                        resolver.update ( imageUri, contentValues, null, null );
//
//
//                                        updatedata1 ( imageUri );
//                                    } else if (quality == 8) {
//                                        quality += 2;
//                                        bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                        Objects.requireNonNull ( fos );
//                                        resolver.update ( imageUri, contentValues, null, null );
//
//
//                                        updatedata1 ( imageUri );
//                                    } else if (quality == 7) {
//                                        quality += 2;
//                                        bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                        Objects.requireNonNull ( fos );
//                                        resolver.update ( imageUri, contentValues, null, null );
//
//
//                                        updatedata1 ( imageUri );
//                                    } else if (quality == 6) {
//                                        quality += 2;
//                                        bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                        Objects.requireNonNull ( fos );
//                                        resolver.update ( imageUri, contentValues, null, null );
//
//
//                                        updatedata1 ( imageUri );
//                                    } else if (quality == 5) {
//                                        quality += 1;
//                                        bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                        Objects.requireNonNull ( fos );
//                                        resolver.update ( imageUri, contentValues, null, null );
//
//
//                                        updatedata1 ( imageUri );
//                                    } else if (quality == 4) {
//                                        quality += 1;
//                                        bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                        Objects.requireNonNull ( fos );
//                                        resolver.update ( imageUri, contentValues, null, null );
//
//
//                                        updatedata1 ( imageUri );
//                                    } else if (quality == 3) {
//                                        quality += 1;
//                                        bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                        Objects.requireNonNull ( fos );
//                                        resolver.update ( imageUri, contentValues, null, null );
//
//
//                                        updatedata1 ( imageUri );
//
//                                    } else {
//
//                                        if (requrevalue > 17) {
//                                            quality += 15;
//                                        } else if (requrevalue > 12 && requrevalue <= 17) {
//                                            quality += 12;
//                                        } else {
//                                            quality += 8;
//                                        }
//                                        bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                        Objects.requireNonNull ( fos );
//                                        resolver.update ( imageUri, contentValues, null, null );
//
//
//                                        updatedata1 ( imageUri );
//                                    }
//
//                                    //134 -700
//
//                                    //120-600
//
//
//                                }
//                            }
//                        } catch (FileNotFoundException e) {
//                            e.printStackTrace ();
//                        }
//                    }
//                    else{    Toast.makeText (MainActivity.this , ""+quality, Toast.LENGTH_SHORT ).show ();
//                        Float w = Float.parseFloat ( String.valueOf ( Width.getText () ) );
//                        Float H = Float.parseFloat ( String.valueOf ( hight.getText () ) );
////                Bitmap bitmap = MediaStore.Images.Media.getBitmap ( getContentResolver (), Uri.parse ( file ) );
//
//
//                        bitmap = Bitmap.createScaledBitmap ( bitmap, w.intValue (), H.intValue (), false );
//                        OutputStream fos = null;
//
//
//                        Uri imageUri = null;
//                        try {
//
//                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//
//                                ContentResolver resolver = getContentResolver ();
//                                ContentValues contentValues = new ContentValues ();
//                                int i = new Random ().nextInt ( 1234567890 );
//                                contentValues.put ( MediaStore.MediaColumns.DISPLAY_NAME, "Image_" + i + ".jpg" );
//                                contentValues.put ( MediaStore.MediaColumns.MIME_TYPE, "image/jpeg" );
//                                contentValues.put ( MediaStore.Images.Media.TITLE, i );
//
//
//                                contentValues.put ( MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis () / 1000 );
//                                contentValues.put ( MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis () );
//                                contentValues.put ( MediaStore.Images.Media.IS_PENDING, 0);
//                                contentValues.put ( MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/" + s );
//                                imageUri = resolver.insert ( MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues );
//
//                                fos = resolver.openOutputStream ( Objects.requireNonNull ( imageUri ) );
//                                if (quality > 75 && quality <= 98) {
//
//                                    quality=50;
//
//
//                                    bitmap.compress ( Bitmap.CompressFormat.JPEG, quality, fos );
//
//                                    Objects.requireNonNull ( fos );
//                                    resolver.update ( imageUri, contentValues, null, null );
//
//
//
//                                    updatedata1 ( imageUri );
//
////                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
//                                }
//                                else if (quality > 50 && quality <= 75) {
//
//
//                                    quality=25;
//
//                                    bitmap.compress ( Bitmap.CompressFormat.JPEG, quality, fos );
//
//                                    Objects.requireNonNull ( fos );
//                                    resolver.update ( imageUri, contentValues, null, null );
//
//
//                                    updatedata1 ( imageUri );
//
//
////                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
//                                }
//
//
//
//                                else if (quality > 20 && quality <= 50) {
//
//
//                                    quality -= 16;
//
//                                    bitmap.compress ( Bitmap.CompressFormat.JPEG, quality, fos );
//
//                                    Objects.requireNonNull ( fos );
//                                    resolver.update ( imageUri, contentValues, null, null );
//
//                                    updatedata1 ( imageUri );
//
////                            Toast.makeText ( MainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
//                                }
//
//                                // complete
//                                else if ( quality<=20) {
//
////135
//
//
//                                    //10
//
//
//
//                                    quality = 2;
//
//
//                                    bitmap.compress ( Bitmap.CompressFormat.WEBP, quality, fos );
//
//                                    Objects.requireNonNull ( fos );
//                                    resolver.update ( imageUri, contentValues, null, null );
//
//
//
//                                    updatedata1 ( imageUri );
//// ainActivity.this, "Image Saved", Toast.LENGTH_SHORT ).show ();
//
//                                }
//                                // new SingleMediaScanner ( CropperActivity.this, new File (  Environment.DIRECTORY_PICTURES  ));
//
//
//                            }
//                            else{
//
//                            }
//                        } catch (FileNotFoundException e) {
//                            e.printStackTrace ();
//                        }
//
//                    }
//
//
//                }
//            }
//        }
//    }}
//public void setWidth(String  width,String height) {
//    this.width = this.width;
//}
//    Useto.setOnClickListener ( new View.OnClickListener () {
//        @Override
//        public void onClick(View view) {
//            try {
//                String download_link="https://youtu.be/n3hJsxKfrls";
//                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(download_link));
//                startActivity(myIntent);
//            }
//            catch (ActivityNotFoundException e) {
//                Toast.makeText(MainActivity.this, "No application can handle this request."
//                        + " Please install a webbrowser",  Toast.LENGTH_LONG).show();
//                e.printStackTrace();
//            }
//        }
//    } );
//Image.setOnClickListener ( new View.OnClickListener () {
//        @Override
//        public void onClick(View view) {
//            try {
//                String download_link="https://play.google.com/store/apps/details?id=com.bhagorplay.i2p";
//                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(download_link));
//                startActivity(myIntent);
//            }
//            catch (ActivityNotFoundException e) {
//                Toast.makeText(MainActivity.this, "No application can handle this request."
//                        + " Please install a webbrowser",  Toast.LENGTH_LONG).show();
//                e.printStackTrace();
//            }
//        }
//    } );
}
