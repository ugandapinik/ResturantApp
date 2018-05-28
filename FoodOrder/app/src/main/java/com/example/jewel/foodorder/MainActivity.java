package com.example.jewel.foodorder;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jewel.foodorder.UI.LoginActivity;
import com.example.jewel.foodorder.UI.SignupActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSignIn;
    private Button btnSignUp;
    private TextView textSlogan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initilize
        btnSignIn   = (Button) findViewById(R.id.btnSignIn);
        btnSignUp   = (Button) findViewById(R.id.btnSignUp);
        textSlogan  = (TextView) findViewById(R.id.textSlogan);

//        // Add style to Slogan
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/NABILA.TTF");
        textSlogan.setTypeface(typeface);


        btnSignUp.setOnClickListener(this);
        btnSignIn.setOnClickListener(this);


    }


    @Override
    protected void onStart() {
        super.onStart();

    }


    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSignUp:
                Intent signupIntent = new Intent(this, SignupActivity.class);
                startActivity(signupIntent);
                break;

            case R.id.btnSignIn:
                Intent signinIntent = new Intent(this, LoginActivity.class);
                startActivity(signinIntent);
                break;
        }
    }
}
