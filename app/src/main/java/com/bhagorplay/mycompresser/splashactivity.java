package com.bhagorplay.mycompresser;


import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;


public class splashactivity extends AppCompatActivity {

private  int code=1;
SharedPreferences h;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_splashactivity );
    h=getBaseContext ().getSharedPreferences ( "der101",MODE_PRIVATE );

        chek1 ();
    }

    private void chek1() {
                Dexter.withContext ( splashactivity.this ).withPermission ( Manifest.permission.WRITE_EXTERNAL_STORAGE ).withListener ( new PermissionListener () {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                new Handler ().postDelayed ( new Runnable () {
                    @Override
                    public void run() {
                        startActivity ( new Intent (splashactivity.this,MainActivity.class));
                        finish ();
                    }
                },300 );
              ;
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

chek1 ();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest ();
            }
        } ).check ();
    }


}