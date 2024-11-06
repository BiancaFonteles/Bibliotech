package com.example.bibliotech;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class BookListActivity extends AppCompatActivity {
    DatabaseHelper dbHelper;
    ListView bookListView;
    ArrayAdapter<String> adapter;
    ArrayList<String> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        dbHelper = new DatabaseHelper(this);
        bookListView = findViewById(R.id.book_list_view);
        loadBooks();

        findViewById(R.id.btn_add_book).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookListActivity.this, AddBookActivity.class);
                startActivity(intent);
            }
        });
    }

    @SuppressLint("Range")
    private void loadBooks() {
        bookList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_BOOKS, new String[]{DatabaseHelper.COLUMN_BOOK_NAME}, null, null, null, null, null);

        while (cursor.moveToNext()) {
            bookList.add(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_BOOK_NAME)));
        }
        cursor.close();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bookList);
        bookListView.setAdapter(adapter);
    }
}
