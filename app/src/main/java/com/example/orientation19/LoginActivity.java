package com.example.orientation19;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.orientation19.Database.MyDatabase;
import com.example.orientation19.MyPojos.LoginData;
import com.example.orientation19.MyPojos.LoginResponse;
import com.example.orientation19.MyPojos.UserDetails;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText rollNumber, password;
    private Button signInButton;
    private ApiInterface apiInterface;
    private LoginResponse loginResponse;
    private MyDatabase myDatabase = new MyDatabase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        rollNumber = findViewById(R.id.et_roll_number);
        password = findViewById(R.id.et_password);
        signInButton = findViewById(R.id.sign_in_button);

        rollNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (rollNumber.getText().toString().length() <= 0) {
                    rollNumber.setError("Roll Number is Required");
                } else {
                    rollNumber.setError(null);
                }
            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (password.getText().toString().length() <= 0) {
                    password.setError("Password is Required");
                } else {
                    password.setError(null);
                }
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkRequiredFieldsNotEmpty()) {
                    apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
                    Call<LoginResponse> mCall = apiInterface.requestLogin(new LoginData(rollNumber.getText().toString(), password.getText().toString()));
                    mCall.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            if (response.body() != null) {
                                loginResponse=response.body();
                                if (loginResponse.getJWT() != null) {
                                    UserDetails userDetails = new UserDetails();
                                    userDetails.setLoginStatus("TRUE");
                                    userDetails.setJWTToken(loginResponse.getJWT());
                                    Log.d("jwt",loginResponse.getJWT());
                                    myDatabase.insert(userDetails);
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Error logging in, Please enter correct Roll number and Password", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Login response null", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Error logging in, Please check your Internet Connection", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private boolean checkRequiredFieldsNotEmpty() {
        boolean result = true;
        if (rollNumber.getText().toString().length() <= 0) {
            Toast.makeText(getApplicationContext(), "Please enter your Roll number", Toast.LENGTH_SHORT).show();
            result = false;
        }
        if (password.getText().toString().length() <= 0) {
            Toast.makeText(getApplicationContext(), "Please enter your password", Toast.LENGTH_SHORT).show();
            result = false;
        }
        return result;
    }
}
