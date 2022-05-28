package com.example.se3282022.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.se3282022.R;
import com.example.se3282022.firebase.FirebaseDeleteRecordActivity;
import com.example.se3282022.firebase.FirebaseFetchRecordActivity;
import com.example.se3282022.firebase.FirebaseMainActivity;
import com.example.se3282022.firebase.FirebaseUpdateRecordActivity;

public class SqliteMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_main);

        Button btnUpdate = findViewById(R.id.btn_update_record);
        Button btnDelete = findViewById(R.id.btn_delete);
        Button btnFetch = findViewById(R.id.btn_fetch_record);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUpdateRecord();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDelete();
            }
        });

        btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SqliteMainActivity.this, SqlFetchRecordActivity.class));
            }
        });
    }
    private void openDelete() {
        startActivity(new Intent(SqliteMainActivity.this, SqlDeleteRecordActivity.class));
    }

    private void openUpdateRecord() {
        startActivity(new Intent(SqliteMainActivity.this, SqlUpdateRecordActivity.class));
    }
}