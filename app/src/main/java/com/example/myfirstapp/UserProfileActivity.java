package com.example.myfirstapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstapp.userCollection.User;

import java.util.List;
import java.util.Locale;
import java.util.Random;
/**
 * @author Zihan Meng
 * @author Zhaoyu Cao
 * @feature
 * @param
 * @return
 */
public class UserProfileActivity extends AppCompatActivity {
    LocationManager locationManager;
    LocationListener locationListener;
    TextView locationText, username,email,phone;
    private String country="";
    private Button btn_logout;
    private Button btn_manageAccounts;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        //set title to "User Profile"
        getSupportActionBar().setTitle("User Profile");

        Random rand = new Random();
        int rndInt = rand.nextInt(5) + 1; // n = the number of images, that start at idx 1
        String imgName = "img" + rndInt;
        int id = getResources().getIdentifier(imgName, "drawable", getPackageName());

        //Use findViewById function to match component
        ImageView imageView = findViewById(R.id.iv_avatar);
        imageView.setImageResource(id);
        username=findViewById(R.id.tv_userName);
        email=findViewById(R.id.tv_email);
        phone=findViewById(R.id.tv_phone);

        User user=(User) getIntent().getExtras().getSerializable("USER");

        username.setText(user.getUsername());
        email.setText(user.getEmail());
        phone.setText(user.getPhone());
        btn_logout = findViewById(R.id.btn_logout);
        btn_manageAccounts = findViewById(R.id.btn_manageAccounts);


        /**
         * @author Zihan Meng
         * @feature Set setOnClickListener on button, When click btn_logout button, go to LoginActivity.
         */
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logout = new Intent(UserProfileActivity.this,LoginActivity.class);
                startActivity(logout);
                Toast.makeText(UserProfileActivity.this,"Logout success",Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * @author Zihan Meng
         * @feature Set setOnClickListener on button, When click btn_manageAccounts button, go to ManageAccountActivity.
         */
        btn_manageAccounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent manageAccountActivity = new Intent(UserProfileActivity.this,ManageAccountActivity.class);
                startActivity(manageAccountActivity);
            }
        });


        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                locationText = findViewById(R.id.tv_location);
                locationText.setText(getAddress(location));
            }

            @Override
            public void onProviderDisabled(String provider){
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    || checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    || checkSelfPermission(Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.INTERNET},10);
            }
        }

        ImageButton loc = findViewById(R.id.btn_location);
        loc.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {
                locationManager.requestLocationUpdates("gps",1000,0,locationListener);
            }
        });
    }

    private String getAddress(Location location) {
        List<Address> result = null;
        try {
            if (location != null) {
                Geocoder gc = new Geocoder(this, Locale.getDefault());
                result = gc.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                System.out.println(result);
                if(result!=null && result.size()>0){

                    for (int i=0;i<result.size();i++) {
                        if (result.get(i) != null) {
                            Address address = result.get(i);

                            country = address.getLocality() + "," + address.getCountryName();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return country;
    }


}