package com.example.houserentalmanagement.user;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.houserentalmanagement.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class LoginUser extends AppCompatActivity {

    EditText et_email, et_password;
    Button btn_login;
    TextView tv_registerBtn;

    TextView tv_forgotPassword;

    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$";

    Pattern pat = Pattern.compile(emailRegex);

    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        setTitle("Login User");

        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);
        tv_registerBtn = findViewById(R.id.tv_registerButton);
        tv_forgotPassword = findViewById(R.id.tv_forgotPassword);

        tv_forgotPassword.setOnClickListener(v -> startActivity(new Intent(LoginUser.this, ResetPasswordUser.class)));

        progressDialog = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        btn_login.setOnClickListener(v -> performLogin());

        tv_registerBtn.setOnClickListener(v -> startActivity(new Intent(LoginUser.this, RegisterUser.class)));

    }

    private void performLogin() {
        String email = et_email.getText().toString();
        String password = et_password.getText().toString().trim();

        if (email.isEmpty()) {
            et_email.setError("Please Enter Email");
        } else if (!pat.matcher(email).matches()) {
            et_email.setError("Please Enter a valid Email");
        } else if (password.isEmpty()) {
            et_password.setError("Please input Password");
        } else if (password.length() < 6) {
            et_password.setError("Password too short");
        } else {
            progressDialog.setMessage("Login in to your Account....");
            progressDialog.setTitle("Loading");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    sendUserToMainActivity();
                    Toast.makeText(LoginUser.this, "Login Successful", Toast.LENGTH_SHORT).show();
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(LoginUser.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void sendUserToMainActivity() {
        Intent intent = new Intent(LoginUser.this, DashboardUserMain.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.actionExit) {
            finish();
            return true;
        }




        return super.onOptionsItemSelected(item);

    }

}