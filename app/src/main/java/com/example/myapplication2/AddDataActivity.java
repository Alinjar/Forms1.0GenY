package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

//import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddDataActivity extends AppCompatActivity {
    EditText nameet;
    EditText classet;
    EditText dobet;
    EditText emailet;
    EditText passet;
    EditText conpasset;
    EditText genet;
    String passstr;

    Button saveButton;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    private RadioGroup radig;
    private RadioButton radib;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        FirebaseApp.initializeApp(this);
        nameet = findViewById(R.id.nameEditText);
        classet = findViewById(R.id.classEdit);
        radig = findViewById(R.id.radioSex);
        dobet = findViewById(R.id.dateEdit);
        emailet = findViewById(R.id.emailEditText);
        passet = findViewById(R.id.passEditText);
        conpasset = findViewById(R.id.confirmEditText);
        saveButton = findViewById(R.id.buttonAddActivity);
        String uni_id = android.provider.Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
//Find unique ID for my android device

        firebaseDatabase = FirebaseDatabase.getInstance(); //Reference

        reference = firebaseDatabase.getReference(uni_id); //getReference

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namestr = nameet.getText().toString();
                String classtr = classet.getText().toString();
                String genderstr = ((RadioButton) findViewById(radig.getCheckedRadioButtonId())).getText().toString();
                String dobstr = dobet.getText().toString();
                String emailstr = emailet.getText().toString();
                passstr = passet.getText().toString();
                String conpstr = conpasset.getText().toString();


                if (conpstr.equals(passstr)) {
                    Toast.makeText(AddDataActivity.this, "Successful", Toast.LENGTH_SHORT).show();

                    reference.child(DatabaseStructure.nameID).setValue(namestr); //for the desc and heading to come as a subpart of the uni_id
                    reference.child("CLASS").setValue(classtr);
                    reference.child("DATEOfBIRTH").setValue(dobstr);
                    reference.child(DatabaseStructure.genderID).setValue(genderstr);
                    reference.child("EMAIL").setValue(emailstr);
                    reference.child("Enter Password").setValue(passstr);
                    reference.child("Confirm Password").setValue(conpstr);
                    finish();
                }

                //saving data code
                /*SharedPreferences sharedPreferences = getApplication().getSharedPreferences("DATABASE", MODE_PRIVATE); //stored in Cache Memory
                SharedPreferences.Editor editor = sharedPreferences.edit();
                //mapping key value
                if (passstr.compareTo(conpstr) == 0) {
                    editor.putString("NAME", namestr);
                    editor.putString("CLASS", classtr);
                    editor.putString("GENDER", genderstr);
                    editor.putString("DATEofBIRTH", dobstr);
                    editor.putString("EMAIL", emailstr);
                    editor.putString("Enter Password", passstr);
                    editor.putString("Confirm Password", conpstr);
                    editor.commit();
                    finish();
                    Toast.makeText(AddDataActivity.this, "description", Toast.LENGTH_SHORT).show();
               */
                else {
                    Toast.makeText(AddDataActivity.this, "Please Input Same Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
public String getPass()
{
    return passstr;
}

}
