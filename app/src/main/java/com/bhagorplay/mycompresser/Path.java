package com.bhagorplay.mycompresser;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import androidx.loader.content.CursorLoader;

public class Path {

    public static String getRealPath(Context context, Uri fileUri) {
        String realPath;
        // SDK < API11
        if (Build.VERSION.SDK_INT < 11) {
            realPath =  getRealPathFromURI_BelowAPI11(context, fileUri);
        }
        // SDK >= 11 && SDK < 19
        else if (Build.VERSION.SDK_INT < 19) {
            realPath =  getRealPathFromURI_API11to18(context, fileUri);
        }
        // SDK > 19 (Android 4.4) and up
        else {
            realPath =  getRealPathFromURI_API19(context, fileUri);
        }
        return realPath;
    }


    @SuppressLint("NewApi")
    public static String getRealPathFromURI_API11to18(Context context, Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        String result = null;

        CursorLoader cursorLoader = new CursorLoader(context, contentUri, proj, null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();

        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            result = cursor.getString(column_index);
            cursor.close();
        }
        return result;
    }

    public static String getRealPathFromURI_BelowAPI11(Context context, Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
        int column_index = 0;
        String result = "";
        if (cursor != null) {
            column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            result = cursor.getString(column_index);
            cursor.close();
            return result;
        }
        return result;
    }

    /**
     * Get a file path from a Uri. This will get the the path for Storage Access
     * Framework Documents, as well as the _data field for the MediaStore and
     * other file-based ContentProviders.
     *
     * @param context The context.
     * @param uri     The Uri to query.
     * @author paulburke
     */
    @SuppressLint("NewApi")
    public static String getRealPathFromURI_API19(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                else {
                    contentUri = MediaStore.Files.getContentUri("external");
                }
                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }


//    private void stop(float h) {
//
//        float requrevalue=h;
//        if (requrevalue>globleurisize) {
//            Toast.makeText ( MainActivity.this, "Please Enter Smaller size ", Toast.LENGTH_LONG ).show ();
//            progressBar.setVisibility ( View.GONE );
//            q12.setVisibility ( View.GONE );
//            p.dismiss ();
//
//        }
//        else {
//            {
//                InputStream imageStream = null;
//                try {
//                    imageStream = getContentResolver ().openInputStream ( Uri.parse ( globleuri ) );
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace ();
//                }
//                Bitmap bitmap = BitmapFactory.decodeStream ( imageStream );
//
//                Float w = Float.parseFloat ( String.valueOf ( Width.getText () ) );
//                Float H = Float.parseFloat ( String.valueOf ( hight.getText () ) );
//
//
//                bitmap = Bitmap.createScaledBitmap ( bitmap, w.intValue (), H.intValue (), false );
//                File file=new File ( getApplicationContext ().getExternalCacheDir (),File.separator+ new File ( Environment.DIRECTORY_PICTURES )+"iimage"+new Random ().nextInt (211111)+ ".jpg" )
//                        ;
//                try {
//                    FileOutputStream fout=new FileOutputStream ( file );
//                    bitmap.compress ( Bitmap.CompressFormat.WEBP,quality,fout );
//                    fout.flush ();;
//                    fout.close ();
//                    Uri uri_ = FileProvider.getUriForFile( MainActivity.this,
//                            "com.bhagorplay.mycompresser", file);
//                    //   Glide.with ( MainActivity.this ).load (  uri_).into ( compressimage );
////       updatedata1 ( uri_ );
//
//                    if (requrevalue<filel ( uri_ )){
//                        if(quality>0){
//                            if (quality>70){
//                                quality-=9;
//                            }
//                            quality-=1;
//                            stop ( h );
//                        }
//                        else{
//                            quality=3;
//                            infolast(3,h);
//                        }
//                    }
//                    else{
//                        upd (  quality,w,H);
//                    }
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace ();
//                } catch (IOException e) {
//                    e.printStackTrace ();
//                }
//            }}
//    }
//
//    private void upd(int quality, Float w,  Float h) {
//        InputStream imageStream = null;
//        try {
//            imageStream = getContentResolver ().openInputStream ( Uri.parse ( globleuri ) );
//        } catch (FileNotFoundException e) {
//            e.printStackTrace ();
//        }
//
//        Bitmap bitmap = BitmapFactory.decodeStream ( imageStream );
//
//
//
//
//        bitmap = Bitmap.createScaledBitmap ( bitmap, w.intValue (), h.intValue (), false );
//        File file=new File ( Environment.getExternalStorageDirectory (),File.separator+ new File ( Environment.DIRECTORY_PICTURES )+"iimage"+new Random ().nextInt (211111)+ ".jpg" )
//                ;
//        try {
//            FileOutputStream fout=new FileOutputStream ( file );
//            bitmap.compress ( Bitmap.CompressFormat.WEBP,quality,fout );
//            fout.flush ();;
//            fout.close ();
//            Uri uri_ = FileProvider.getUriForFile( MainActivity.this,
//                    "com.bhagorplay.mycompresser", file);
//            new SingleMediaScanner ( MainActivity.this,file ); p.dismiss ();
//
//        updatedata1 ( uri_ );
//        }
//        catch (Exception F){}
//    }
//
//    private void infolast(int i, float h) {
//        float requrevalue= h;
//        if (requrevalue>globleurisize) {
//            Toast.makeText ( MainActivity.this, "Please Enter Smaller size ", Toast.LENGTH_LONG ).show ();
//            progressBar.setVisibility ( View.GONE );p.dismiss ();
//            q12.setVisibility ( View.GONE );
//
//        }
//        else {
//
//            InputStream imageStream = null;
//            try {
//                imageStream = getContentResolver ().openInputStream ( Uri.parse ( globleuri ) );
//            } catch (FileNotFoundException e) {
//                e.printStackTrace ();
//            }
//            Float w = Float.parseFloat ( String.valueOf ( Width.getText () ) );
//            Float H = Float.parseFloat ( String.valueOf ( hight.getText () ) );
//            Bitmap bitmap = BitmapFactory.decodeStream ( imageStream );
////
//            w=w/2;
//            H=H/2;
//
//            bitmap = Bitmap.createScaledBitmap ( bitmap, w.intValue (), H.intValue (), false );
//            File file=new File ( getApplicationContext ().getExternalCacheDir (),File.separator+ new File ( Environment.DIRECTORY_PICTURES )+"iimage"+new Random ().nextInt (211111)+  ".jpg" )
//                    ;
//            try {
//                FileOutputStream fout=new FileOutputStream ( file );
//                bitmap.compress ( Bitmap.CompressFormat.WEBP,quality,fout );
//                fout.flush ();;
//                fout.close ();
//                Uri uri_ = FileProvider.getUriForFile( MainActivity.this,
//                        "com.bhagorplay.mycompresser", file);
//                //   Glide.with ( MainActivity.this ).load (  uri_).into ( compressimage );
////       updatedata1 ( uri_ );
//
//                if (requrevalue<filel ( uri_ )){
//                    if(quality>0){
//                        quality-=1;
//                        infolast (3, h );
//                    }
//                    else{
//                        quality=4;
//                        infolast4(h);
//                    }
//                }
//                else{
//                    upd (  quality,w,H);
//                }
//            } catch (FileNotFoundException e) {
//                e.printStackTrace ();
//            } catch (IOException e) {
//                e.printStackTrace ();
//            }
//        }
//    }
//
//    private void infolast4(float h) {
//        float requrevalue= h;
//        if (requrevalue>globleurisize) {
//            Toast.makeText ( MainActivity.this, "Please Enter Smaller size ", Toast.LENGTH_LONG ).show ();
//
//            p.dismiss ();    progressBar.setVisibility ( View.GONE );
//            q12.setVisibility ( View.GONE );
//
//        }
//        else {
//
//            InputStream imageStream = null;
//            try {
//                imageStream = getContentResolver ().openInputStream ( Uri.parse ( globleuri ) );
//            } catch (FileNotFoundException e) {
//                e.printStackTrace ();
//            }
//            Float w = Float.parseFloat ( String.valueOf ( Width.getText () ) );
//            Float H = Float.parseFloat ( String.valueOf ( hight.getText () ) );
//            Bitmap bitmap = BitmapFactory.decodeStream ( imageStream );
////
//            w=w/4;
//            H=H/4;
//
//            bitmap = Bitmap.createScaledBitmap ( bitmap, w.intValue (), H.intValue (), false );
//            File file=new File ( getApplicationContext ().getExternalCacheDir (),File.separator+ new File ( Environment.DIRECTORY_PICTURES )+"iimage"+new Random ().nextInt (211111)+  ".jpg" )
//                    ;
//            try {
//                FileOutputStream fout=new FileOutputStream ( file );
//                bitmap.compress ( Bitmap.CompressFormat.WEBP,quality,fout );
//                fout.flush ();;
//                fout.close ();
//                Uri uri_ = FileProvider.getUriForFile( MainActivity.this,
//                        "com.bhagorplay.mycompresser", file);
//                //   Glide.with ( MainActivity.this ).load (  uri_).into ( compressimage );
////       updatedata1 ( uri_ );
//
//                if (requrevalue<filel ( uri_ )){
//                    if(quality>0){
//                        quality-=1;
//                        infolast4 ( h );
//                    }
//                    else{
//                        upd (  quality,w,H);
//                    }
//                }
//                else{
//                    upd (  quality,w,H);
//                }
//            } catch (FileNotFoundException e) {
//                e.printStackTrace ();
//            } catch (IOException e) {
//                e.printStackTrace ();
//            }
//        }
//    }

    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whethe updatedata ( Uri.parse ( globleuri ) );
     *         super.onResume ();r the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }
}
