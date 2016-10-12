package com.example.celiachu.dummydb;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.support.annotation.NonNull;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class dummy_data extends Activity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    FirebaseDatabase database;
    DatabaseReference myRef;

    EditText nameField;

    EditText dataField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy_data);

        nameField = (EditText) findViewById(R.id.enterName);
        dataField = (EditText) findViewById(R.id.enterData);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("Signed in", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("Signed out", "onAuthStateChanged:signed_out");
                }
            }
        };

    }

    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);

        mAuth.signInAnonymously().addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d("Successful Login", "signInAnonymously:onComplete:" + task.isSuccessful());

                if (!task.isSuccessful()) {
                    Log.w("Not successful", "signInAnonymously", task.getException());
                }
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void postData(View v) {


        String name = nameField.getText().toString();
        String data = dataField.getText().toString();

        nameField.setText("");
        dataField.setText("");


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(name + "'s News Articles");

        myRef.child(name).setValue(data);

        Toast.makeText(this, "Button Clicked", Toast.LENGTH_LONG).show();


    }
}
