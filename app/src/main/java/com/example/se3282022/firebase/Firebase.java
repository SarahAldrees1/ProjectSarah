package com.example.se3282022.firebase;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.se3282022.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

import es.dmoral.toasty.Toasty;

public class Firebase extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase ;
    DatabaseReference databaseReference ;
    Context context;
    Firebase(Context context){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("user");
        this.context = context;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void addRecord(String iD, String name, String sureName, String fatherName,
                          String nationalID, String dob, String gender) {
        User user = new User(iD, name, sureName, fatherName, nationalID, dob, gender);
        databaseReference.child(iD).setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toasty.success(context, "Record added successfully.", Toast.LENGTH_SHORT, true).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toasty.error(context, "Error in adding record.", Toast.LENGTH_SHORT, true).show();
                    }
                });
    }

    public void updateRecord(String iD, String name, String sureName, String fatherName, String nationalID, String dob, String gender) {
        User user = new User(iD, name, sureName, fatherName, nationalID, dob, gender);
        Map<String, Object> userValues = user.toMap();
        databaseReference.child(iD).updateChildren(userValues)
               .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toasty.success(context, "Record updated successfully.", Toast.LENGTH_SHORT, true).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toasty.error(context, "Error in updating record.\n"+e.getLocalizedMessage() , Toast.LENGTH_SHORT, true).show();

            }
        });
    }

    public void deleteRecord(String iD) {
        databaseReference.child(iD).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toasty.success(context, "Record deleted successfully.", Toast.LENGTH_SHORT, true).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toasty.error(context, "Error in deleting.", Toast.LENGTH_SHORT, true).show();
            }
        });
    }
}
