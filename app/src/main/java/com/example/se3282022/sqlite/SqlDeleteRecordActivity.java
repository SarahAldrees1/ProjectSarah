package com.example.se3282022.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.se3282022.R;

import es.dmoral.toasty.Toasty;

public class SqlDeleteRecordActivity extends AppCompatActivity {
    String id;
    UserDatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_delete_record);

        db = new UserDatabaseHelper(this);

        EditText etId = findViewById(R.id.et_id);
        Button btnDelete = findViewById(R.id.btn_delete_record);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = etId.getText().toString();
                deleteRecordInSqlite();
            }
        });
    }

    private void deleteRecordInSqlite() {
        if (id != null || !id.isEmpty()){
            int deleted = db.deleteUser(id);
            if (deleted != 0){
                Toasty.success(this, "Record deleted successfully against ID: ."+id, Toast.LENGTH_SHORT, true).show();

            }
            else Toasty.error(this, "Error in deleting record.", Toast.LENGTH_SHORT, true).show();

        }
        else
            Toasty.error(this, "Please input user ID.", Toast.LENGTH_SHORT, true).show();

    }
}