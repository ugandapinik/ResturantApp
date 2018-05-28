package com.example.jewel.foodorder.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jewel.foodorder.MainActivity;
import com.example.jewel.foodorder.Model.User;
import com.example.jewel.foodorder.R;
import com.example.jewel.foodorder.Utils.Utils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editPhone;
    private EditText editPassword;
    private Button btnSignIn;


    FirebaseDatabase database;
    DatabaseReference table_user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        editPhone = (EditText) findViewById(R.id.editPhone);
        editPassword = (EditText) findViewById(R.id.editPassword);
        btnSignIn = (Button) findViewById(R.id.signin);

        database = FirebaseDatabase.getInstance();
        table_user = database.getReference("User");

        btnSignIn.setOnClickListener(this);

    }





    private void initializeWidgets() {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signin:

                // show the ProgressDialog
                final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setMessage("Please Wait...");
                progressDialog.show();


                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if (dataSnapshot.child(editPhone.getText().toString()).exists()){
                            progressDialog.dismiss();
                            //Get User Information
                            User user = dataSnapshot.child(editPhone.getText().toString()).getValue(User.class);
                            user.setPhone(editPhone.getText().toString());
                            if (user.getPassword().equals(editPassword.getText().toString())){
                                //showMessage("Sign in successfully!!!");
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                Utils.currentUser = user;
                                startActivity(intent);

                            }else{
                                showMessage("Try Again! Wrong password!");
                            }
                        }else{
                            progressDialog.dismiss();
                            showMessage("User not exists!!!");
                        }


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                break;
        }
    }


    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
