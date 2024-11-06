package com.example.bibliotech;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddBookActivity extends AppCompatActivity {
    EditText bookNameInput;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        dbHelper = new DatabaseHelper(this);
        bookNameInput = findViewById(R.id.book_name_input);

        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookName = bookNameInput.getText().toString();
                if (!bookName.isEmpty()) {
                    addBook(bookName);
                    finish();
                } else {
                    bookNameInput.setError(getString(R.string.hint_book_name));
                }
            }
        });
    }

    private void addBook(String bookName) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_BOOK_NAME, bookName);
        db.insert(DatabaseHelper.TABLE_BOOKS, null, values);
    }
}

