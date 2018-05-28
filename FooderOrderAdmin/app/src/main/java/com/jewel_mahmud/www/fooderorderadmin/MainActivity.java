package com.jewel_mahmud.www.fooderorderadmin;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jewel_mahmud.www.fooderorderadmin.UI.SignInActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private Button btnSignIn;
    private TextView textSlogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initilize
        btnSignIn   = (Button) findViewById(R.id.btnSignIn);
        textSlogan  = (TextView) findViewById(R.id.textSlogan);

        // Add style to Slogan
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/NABILA.TTF");
        textSlogan.setTypeface(typeface);


        btnSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.btnSignIn:
                Intent signinIntent = new Intent(this, SignInActivity.class);
                startActivity(signinIntent);
                break;
        }
    }
}
