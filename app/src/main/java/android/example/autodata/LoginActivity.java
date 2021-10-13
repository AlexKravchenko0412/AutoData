package android.example.autodata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextLogin;
    private EditText editTextPassword;
    private UserDataDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextLogin = findViewById(R.id.editTextLogin);
        editTextPassword = findViewById(R.id.editTextPassword);
        dbHelper = new UserDataDBHelper(this);



    }

    public void onClickEnter(View view) {
        String login = editTextLogin.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        if(!login.isEmpty() && !password.isEmpty()) {
            Intent intent = new Intent(this, StepOneActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this,
                    R.string.enter_all_fields,
                    Toast.LENGTH_SHORT).show();
        }

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserContract.UserData.COLUMN_LOGIN, login);
        contentValues.put(UserContract.UserData.COLUMN_PASSWORD, password);
        database.insert(UserContract.UserData.TABLE_NAME, null, contentValues);

    }
}