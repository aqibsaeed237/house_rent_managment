package com.example.houserentalmanagement.houseOwner;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.houserentalmanagement.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.regex.Pattern;

public class RegisterOwner extends AppCompatActivity {

    public static final String OWNER = "Owner";
    public static final String HOUSES = "houses";
    public static final String MEMBERS = "members";

    EditText et_email, et_password, et_confirmPassword, et_username, phone;
    Button btn_Register;
    TextView tv_loginBtn;

    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$";

    Pattern pat = Pattern.compile(emailRegex);
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_owner);
        setTitle("Register Owner");

        et_username = findViewById(R.id.et_username);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        phone = findViewById(R.id.phoneNo);
        et_confirmPassword = findViewById(R.id.et_confirmPassword);
        btn_Register = findViewById(R.id.btn_register);
        tv_loginBtn = findViewById(R.id.tv_loginButton);

        progressDialog = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        tv_loginBtn.setOnClickListener(v -> startActivity(new Intent(RegisterOwner.this, LoginOwner.class)));

        btn_Register.setOnClickListener(v -> PerformAuth());

    }

    private void PerformAuth() {
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();
        String confirmPassword = et_confirmPassword.getText().toString();
        String username = et_username.getText().toString();
        String phoneNo = phone.getText().toString();


        if (email.isEmpty()) {
            et_email.setError("Please Enter Email");
        } else if (!pat.matcher(email).matches()) {
            et_email.setError("Please Enter a valid Email");
        } else if (password.isEmpty()) {
            et_password.setError("Please input Password");
        } else if (password.length() < 6) {
            et_password.setError("Password too short");
        } else if (!confirmPassword.equals(password)) {
            et_confirmPassword.setError("Password doesn't matches");
        }
            else if(phoneNo.length()<10){
                phone.setError("PhoneNo length at least 10 characters");
        } else {
            progressDialog.setMessage("Creating your Account....");
            progressDialog.setTitle("Creating");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();

                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                    String userId = firebaseUser.getUid();
                    String email1 = firebaseUser.getEmail();

                    reference = FirebaseDatabase.getInstance().getReference().child(RegisterOwner.OWNER);
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("id", userId);
                    hashMap.put("username", username);
                    hashMap.put("email", email1);
                    hashMap.put("password",password);
                    hashMap.put("imageUrl", "default");
                    hashMap.put("phoneNo", phoneNo);
                    hashMap.put("search", username.toLowerCase());

                    reference.child(userId).setValue(hashMap).addOnCompleteListener(task1 -> {
                        if (task1.isSuccessful()) {
                            sendUserToMainActivity();
                        }
                    });


                    Toast.makeText(RegisterOwner.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(RegisterOwner.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void sendUserToMainActivity() {
        Intent intent = new Intent(RegisterOwner.this, DashboardOwner.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

}