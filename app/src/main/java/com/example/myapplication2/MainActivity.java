package com.example.myapplication2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    TextView nametv;
    TextView classtv;
    TextView emailtv;
    TextView dobtv;
    TextView gendertv;
    TextView passtv;
    TextView conptv;
    FloatingActionButton fabbutt;
    FloatingActionButton fabbutt2;

    SharedPreferences sharedPreferences;

   /* public void fetchdata() {
        sharedPreferences = getSharedPreferences("DATABASE", MODE_PRIVATE);
        String headstring = getSharedPreferences("DATABASE", MODE_PRIVATE).getString("Name", "");
        nametv.setText(headstring);
        classtv.setText(sharedPreferences.getString("class", ""));
        emailtv.setText(sharedPreferences);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nametv = findViewById(R.id.name);
        emailtv = findViewById(R.id.email);
        gendertv = findViewById(R.id.gender);
        passtv = findViewById(R.id.pass);
        dobtv = findViewById(R.id.dateOfBirth);
        conptv = findViewById((R.id.confpass));
        classtv = findViewById(R.id.cls);
        fabbutt = findViewById(R.id.fabActivity);
        fabbutt2 = findViewById(R.id.fabAct2);
       // fetchFirebasedata();
        fabbutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //this denotes that my button has been clicked
                Intent intent = new Intent(MainActivity.this, AddDataActivity.class);
                startActivity(intent);
            }

        });
        fabbutt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainAct2.class);
                startActivity(intent);
            }
        });
    }

    public void fetchFirebasedata() {
        String uni_id = android.provider.Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
//Find unique ID for my android device
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference(uni_id);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {//this avoids crash bec. they search for the word within the quotes in Firebase
                    // Toast.makeText(MainActivity.this, dataSnapshot.child("Desc").getValue().toString(), Toast.LENGTH_SHORT).show();
                    nametv.setText(dataSnapshot.child(DatabaseStructure.nameID).getValue().toString());//dataSnapshot is a function from Firebase
                    classtv.setText(dataSnapshot.child(DatabaseStructure.classID).getValue().toString());
                    dobtv.setText(dataSnapshot.child(DatabaseStructure.dobID).getValue().toString());
                    gendertv.setText(dataSnapshot.child(DatabaseStructure.genderID).getValue().toString());
                    emailtv.setText(dataSnapshot.child(DatabaseStructure.emailID).getValue().toString());
                    passtv.setText(dataSnapshot.child("Enter Password").getValue().toString());
                    conptv.setText(dataSnapshot.child("Confirm Password").getValue().toString());



                } else {
                  Toast.makeText(MainActivity.this, "No Data Found :(", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
      // fetchdata();
        fetchFirebasedata();//to come back to activity A where the head and desc gets updated to the home screen

    }
}
