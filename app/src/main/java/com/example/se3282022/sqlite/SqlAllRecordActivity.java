package com.example.se3282022.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.se3282022.R;
import com.example.se3282022.firebase.FirebaseAdapter;
import com.example.se3282022.model.User;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class SqlAllRecordActivity extends AppCompatActivity implements OnListCLick {
    UserDatabaseHelper db;
    ArrayList userList = new ArrayList<User>();
    SqliteAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_all_record);

        db = new UserDatabaseHelper(this);
        initRecyclerview();
        getUserListFromDb();
    }

    private void initRecyclerview() {
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SqliteAdapter(userList, this);
        recyclerView.setAdapter(adapter);

    }

    private void getUserListFromDb() {
        userList.clear();
        userList = (ArrayList) db.getAllUsers();
        adapter.list = userList;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onUserClick(int position) {
        Toasty.normal(SqlAllRecordActivity.this, userList.get(position).toString(), Toasty.LENGTH_SHORT).show();
    }
}