package com.example.se3282022.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.se3282022.R;
import com.example.se3282022.model.User;

import es.dmoral.toasty.Toasty;

public class SqlUpdateRecordActivity extends AppCompatActivity {
    String id, name, sureName, fatherName, nationalId, dob, gender;
    UserDatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_update_record);
        db = new UserDatabaseHelper(this);

        EditText etID = findViewById(R.id.et_id);
        EditText etName = findViewById(R.id.et_name);
        EditText etSureName = findViewById(R.id.et_sure_name);
        EditText etFatherName = findViewById(R.id.et_father_name);
        EditText etNationalId = findViewById(R.id.et_naional_id);
        EditText etDob = findViewById(R.id.et_date_of_birth);
        EditText etGender = findViewById(R.id.et_gender);
        Button btnUpdateRecord = findViewById(R.id.btn_update_record);

        btnUpdateRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = etID.getText().toString();
                name = etName.getText().toString();
                sureName = etSureName.getText().toString();
                fatherName = etFatherName.getText().toString();
                nationalId = etNationalId.getText().toString();
                dob = etDob.getText().toString();
                gender = etGender.getText().toString();
                updateRecordInSqlite();
            }
        });
    }

    private void updateRecordInSqlite() {
        if (id != null || !id.isEmpty() ||name != null || !name.isEmpty() ||
                sureName != null || !sureName.isEmpty() ||
                fatherName != null || !fatherName.isEmpty() ||
                nationalId != null || !nationalId.isEmpty() ||
                dob != null || !dob.isEmpty() ||
                gender != null || !gender.isEmpty()){
            User user = new User(id, name, sureName, fatherName, nationalId, dob, gender);
            int updated = db.updateUser(user);
            if (updated != 0)
                Toasty.success(this, "Record updated successfully against user: \n."+user.toString(), Toast.LENGTH_SHORT, true).show();
            else
                Toasty.error(this, "Error in updating record.", Toast.LENGTH_SHORT, true).show();

        }
        else Toasty.error(this, "Please input all values.", Toast.LENGTH_SHORT, true).show();
    }

}