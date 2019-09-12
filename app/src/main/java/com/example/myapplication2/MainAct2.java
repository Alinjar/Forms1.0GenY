package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainAct2 extends AppCompatActivity {
    EditText oldet;
    EditText newet;
    EditText cfpet;
    Button savebt;
    FirebaseDatabase firebased;
    DatabaseReference ref;
AddDataActivity obj = new AddDataActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_act2);
        oldet = findViewById((R.id.oldpass));
        newet = findViewById((R.id.newpass));
        cfpet = findViewById((R.id.cfp));
        savebt = findViewById((R.id.buttonMainAct));
        String uni = android.provider.Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        firebased = FirebaseDatabase.getInstance();
        ref = firebased.getReference(uni);
        savebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String old = oldet.getText().toString();
                String neww = newet.getText().toString();
                String con = cfpet.getText().toString();

               // if (old.equals(obj.getPass())) {
                    if (neww.equals(con)) {
                        Toast.makeText(MainAct2.this, "Successful", Toast.LENGTH_SHORT).show();
                        ref.child(DatabaseStructure.passID).setValue(neww);
                        ref.child((DatabaseStructure.confPassID)).setValue(con);
                        finish();

                    }



            }
        });


    }
}
