package uy.vacunas.ui.login;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import uy.vacunas.MainActivity;
import uy.vacunas.R;

public class LoginActivity extends AppCompatActivity {

    private WebView w;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);


        final Button loginButton = findViewById(R.id.login);

        w = (WebView) findViewById(R.id.webview);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(LoginActivity.this, WebViewActivity.class);
                //Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);

                startActivity(myIntent);

            }
        });
    }






}