package com.example.pressurepoints;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import Helpers.UsersControl;

public class MainActivity extends AppCompatActivity {

    private int PREMISSION_INTERNET = 1;
    private int PREMISSION_ACCESS_NETWORK_STATE = 1;
    private int PREMISSION_ACCESS_WIFI_STATE = 1;
    private int PREMISSION_RECORD_AUDIO = 1;
    private int PREMISSION_CAMERA = 1;
    private int PREMISSION_MODIFY_AUDIO_SETTINGS = 1;

    public static AnswersDataFlowControl answersDataFlowControl;
    public static UsersControl usersControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Define the dark mode disable for this application
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);

        // Define ActionBar object
        ActionBar actionBar;
        actionBar = getSupportActionBar();

        // Define ColorDrawable object and parse color
        // using parseColor method
        // with color hash code as its parameter
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#60609D"));

        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED)
            requestInternetPermission();
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED)
            requestAccessNetworkStatePermission();
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_WIFI_STATE) != PackageManager.PERMISSION_GRANTED)
            requestAccessWifiStatePermission();
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED)
            requestRecordAudioPermission();
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            requestCameraPermission();
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.MODIFY_AUDIO_SETTINGS) != PackageManager.PERMISSION_GRANTED)
            requestAudioSettingsPermission();

        answersDataFlowControl = new AnswersDataFlowControl();
        usersControl = new UsersControl();
    }

    private void requestInternetPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.INTERNET)){
            new AlertDialog.Builder(this)
                    .setTitle("Permissão necessária")
                    .setMessage("Esta permissão é necessária para o correto funcionamento do programa")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.INTERNET}, PREMISSION_INTERNET);
                        }
                    })
                    .setNegativeButton("voltar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }else{
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.INTERNET}, PREMISSION_INTERNET);
        }
    }

    private void requestAccessNetworkStatePermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_NETWORK_STATE)){
            new AlertDialog.Builder(this)
                    .setTitle("Permissão necessária")
                    .setMessage("Esta permissão é necessária para o correto funcionamento do programa")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.ACCESS_NETWORK_STATE}, PREMISSION_ACCESS_NETWORK_STATE);
                        }
                    })
                    .setNegativeButton("voltar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }else{
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_NETWORK_STATE}, PREMISSION_ACCESS_NETWORK_STATE);
        }
    }

    private void requestAccessWifiStatePermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_WIFI_STATE)){
            new AlertDialog.Builder(this)
                    .setTitle("Permissão necessária")
                    .setMessage("Esta permissão é necessária para o correto funcionamento do programa")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.ACCESS_WIFI_STATE}, PREMISSION_ACCESS_WIFI_STATE);
                        }
                    })
                    .setNegativeButton("voltar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }else{
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_WIFI_STATE}, PREMISSION_ACCESS_WIFI_STATE);
        }
    }

    private void requestRecordAudioPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO)){
            new AlertDialog.Builder(this)
                    .setTitle("Permissão necessária")
                    .setMessage("Esta permissão é necessária para o correto funcionamento do programa")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.RECORD_AUDIO}, PREMISSION_RECORD_AUDIO);
                        }
                    })
                    .setNegativeButton("voltar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }else{
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.RECORD_AUDIO}, PREMISSION_RECORD_AUDIO);
        }
    }

    private void requestCameraPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)){
            new AlertDialog.Builder(this)
                    .setTitle("Permissão necessária")
                    .setMessage("Esta permissão é necessária para o correto funcionamento do programa")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.CAMERA}, PREMISSION_CAMERA);
                        }
                    })
                    .setNegativeButton("voltar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }else{
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, PREMISSION_CAMERA);
        }
    }

    private void requestAudioSettingsPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.MODIFY_AUDIO_SETTINGS)){
            new AlertDialog.Builder(this)
                    .setTitle("Permissão necessária")
                    .setMessage("Esta permissão é necessária para o correto funcionamento do programa")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.MODIFY_AUDIO_SETTINGS}, PREMISSION_MODIFY_AUDIO_SETTINGS);
                        }
                    })
                    .setNegativeButton("voltar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }else{
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.MODIFY_AUDIO_SETTINGS}, PREMISSION_MODIFY_AUDIO_SETTINGS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PREMISSION_INTERNET){
            if(grantResults.length > 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Toast.makeText(MainActivity.this, "Você confirmou a permissão", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this, "Você negou a permissão", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == PREMISSION_ACCESS_NETWORK_STATE){
            if(grantResults.length > 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Toast.makeText(MainActivity.this, "Você confirmou a permissão", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this, "Você negou a permissão", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == PREMISSION_ACCESS_WIFI_STATE){
            if(grantResults.length > 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Toast.makeText(MainActivity.this, "Você confirmou a permissão", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this, "Você negou a permissão", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == PREMISSION_RECORD_AUDIO){
            if(grantResults.length > 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Toast.makeText(MainActivity.this, "Você confirmou a permissão", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this, "Você negou a permissão", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == PREMISSION_CAMERA){
            if(grantResults.length > 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Toast.makeText(MainActivity.this, "Você confirmou a permissão", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this, "Você negou a permissão", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == PREMISSION_MODIFY_AUDIO_SETTINGS){
            if(grantResults.length > 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Toast.makeText(MainActivity.this, "Você confirmou a permissão", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this, "Você negou a permissão", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static AnswersDataFlowControl getAnswersDataFlowControl() {
        return answersDataFlowControl;
    }

    public static UsersControl getUsersControl() {
        return usersControl;
    }
}