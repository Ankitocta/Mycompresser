package com.bhagorplay.mycompresser;

import static android.content.Context.MODE_PRIVATE;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;



public class setting extends AppCompatActivity {
ImageView IM;
LinearLayout linearLayout,email,rating,policy,shareSupport;
SharedPreferences DERECTORY;
    Button Image,Useto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_setting );
        DERECTORY=getSharedPreferences ( "der",MODE_PRIVATE );
        SharedPreferences.Editor editoWBr= DERECTORY.edit ();
        String s=DERECTORY.getString ( "WB","" );
        Useto=findViewById ( R.id.Pickimage121 );
        Image=findViewById ( R.id.sharetext121 );
        if (s.matches ( "" ))  {

            Toast.makeText ( setting.this, ""+s, Toast.LENGTH_SHORT ).show ();
        }
        else if (!s.isEmpty ()){

            Toast.makeText ( setting.this, ""+s, Toast.LENGTH_SHORT ).show ();
        }
        Useto.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                try {
                    String download_link="https://youtu.be/n3hJsxKfrls";
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(download_link));
                    startActivity(myIntent);
                }
                catch (ActivityNotFoundException e) {
                    Toast.makeText(setting.this, "No application can handle this request."
                            + " Please install a webbrowser",  Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        } );
        Image.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                try {
                    String download_link="https://play.google.com/store/apps/details?id=com.bhagorplay.i2p";
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(download_link));
                    startActivity(myIntent);
                }
                catch (ActivityNotFoundException e) {
                    Toast.makeText(setting.this, "No application can handle this request."
                            + " Please install a webbrowser",  Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        } );
        IM=findViewById ( R.id.finishsetting );
        rating=findViewById ( R.id.rating );
        shareSupport=findViewById ( R.id.sharesupport );
        shareSupport.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
//                Intent myIntent = new Intent(Intent.ACTION_SEND, Uri.parse(download_link));
//                startActivity(myIntent);
            }
        } );
        policy=findViewById ( R.id.privacypolicy );
        policy.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

            }
        } );
//        rating.setOnClickListener ( new View.OnClickListener () {
//            @Override
//            public void onClick(View view) {
//                AppRate.with( setting.this)
//                        .setInstallDays(0) // default 10, 0 means install day.
//                        .setLaunchTimes(3) // default 10
//                        .setRemindInterval(2) // default 1
//                        .setShowLaterButton(true) // default true
//                        .setDebug(false) // default false
//                        .setOnClickButtonListener(new OnClickButtonListener () { // callback listener.
//                            @Override
//                            public void onClickButton(int which) {
//
//                                if (which==-1){
//                                    Toast.makeText ( setting.this, "Thanks Please Give Best Star and Review" , Toast.LENGTH_SHORT ).show ();
//                                }else if (which==-2){
//                                    Toast.makeText (setting.this, "Ok Thanks" , Toast.LENGTH_SHORT ).show ();
//                                }
//
//
//                            }
//                        })
//                        .monitor();
//
//                // Show a dialog if meets conditions
//                AppRate.showRateDialogIfMeetsConditions(  setting.this);AppRate.with(  setting.this).showRateDialog( setting.this);
//            }
//        } );
        email=findViewById ( R.id.contactsupport );
        email.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent email = new Intent(Intent.ACTION_SENDTO);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"aankitfirebaseid@gmail.com" });
               email.setData ( Uri.parse ( "mailto:" ) );
                email.putExtra(Intent.EXTRA_SUBJECT,  "");

//need this to prompts email client only


                startActivity(Intent.createChooser(email, "Choose an Email client :"));
            }
        } );
        linearLayout=findViewById ( R.id.folderclick );
        linearLayout.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(  setting.this );
                View view1=getLayoutInflater().inflate( R.layout. setname ,null);
               EditText fF=view1.findViewById( R.id.LL);
                Button CAN=view1.findViewById( R.id.cancel);
                Button OK=view1.findViewById( R.id.ok);

                builder.setView( view1 );
                final AlertDialog  alertDialog=builder.create();

                alertDialog.show();

                CAN.setOnClickListener ( new View.OnClickListener () {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss ();
                    }
                } );
                OK.setOnClickListener ( new View.OnClickListener () {
                    @Override
                    public void onClick(View view) {
                        String   SS=fF.getText().toString();
                        if(SS.isEmpty ()){
                            Toast.makeText ( setting.this,"Enter The Folder name" ,Toast.LENGTH_LONG).show ();
                        }
                        else{
                            Toast.makeText ( setting.this,""+SS ,Toast.LENGTH_LONG).show ();
                            SharedPreferences DERECTORY=getSharedPreferences ( "der",MODE_PRIVATE );
                            SharedPreferences.Editor editoWBr= DERECTORY.edit ();
                            editoWBr.putString ( "WB", ""+SS );
                            editoWBr.commit ();
                            alertDialog.dismiss ();
                        }
                    }
                } );
            }
        } );
                IM.setOnClickListener ( new View.OnClickListener () {
                    @Override
                    public void onClick(View view) {
                       finish ();
                    }
                } );

    }
}