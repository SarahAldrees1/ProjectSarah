package com.example.se3282022.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.se3282022.R;
import com.example.se3282022.firebase.FirebaseAllRecordActivity;
import com.example.se3282022.firebase.FirebaseFetchRecordActivity;
import com.example.se3282022.model.User;

import es.dmoral.toasty.Toasty;

public class SqlFetchRecordActivity extends AppCompatActivity {
    String id;
    UserDatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_fetch_record);
        db = new UserDatabaseHelper(this);

        EditText etId = findViewById(R.id.et_id);
        Button btnFetchSingle = findViewById(R.id.btn_single_record);
        Button btnFetchAll = findViewById(R.id.btn_all_record);

        btnFetchSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = etId.getText().toString();
                if (id != null && !id.isEmpty()){
                    fetchSingleRecord();
                }
                else Toasty.error(SqlFetchRecordActivity.this, "Please input all values.", Toast.LENGTH_SHORT, true).show();

            }
        });

        btnFetchAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SqlFetchRecordActivity.this, SqlAllRecordActivity.class));
            }
        });
    }

    public void fetchSingleRecord(){
        User user = db.getUser(id);
        if (user != null)
            Toasty.success(SqlFetchRecordActivity.this, user.toString(), Toast.LENGTH_SHORT).show();
        else
            Toasty.error(SqlFetchRecordActivity.this, "No record found", Toast.LENGTH_SHORT).show();

    }
}