package com.jewel_mahmud.www.fooderorderadmin.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jewel_mahmud.www.fooderorderadmin.Model.User;
import com.jewel_mahmud.www.fooderorderadmin.R;
import com.jewel_mahmud.www.fooderorderadmin.Utils.Utils;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editPhone;
    private EditText editPassword;
    private Button btnSignIn;

    FirebaseDatabase database;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        editPhone = (EditText) findViewById(R.id.editPhone);
        editPassword = (EditText) findViewById(R.id.editPassword);
        btnSignIn  = (Button) findViewById(R.id.signin);

        database = FirebaseDatabase.getInstance();
        users = database.getReference("User");

        btnSignIn.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.signin:
                signInUser(editPhone.getText().toString(), editPassword.getText().toString());
                break;

        }
    }

    private void signInUser(String phone, String password) {
        final ProgressDialog dialog = new ProgressDialog(SignInActivity.this);
        dialog.setMessage("Please Waiting...");
        dialog.show();

        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(editPhone.getText().toString()).exists()){

                    dialog.dismiss();
                    //Get User Information
                    User user = dataSnapshot.child(editPhone.getText().toString()).getValue(User.class);
                    user.setPhone(editPhone.getText().toString());

                    if (Boolean.parseBoolean(user.getIsStuff())){
                        if (user.getPassword().equals(editPassword.getText().toString())){
                            // Login with stuff account
                            showMessage("Done");
                            Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
                            Utils.currentUser = user;
                            startActivity(intent);
                            finish();


                        }else{
                            showMessage("Wrong Password!");
                        }
                    }else{
                        showMessage("Please Login with Staff account!");
                    }
                }else{
                    showMessage("User not exists is not Exists!");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void showMessage(String message) {
        Toast.makeText(SignInActivity.this, message, Toast.LENGTH_SHORT).show();
    }

}
