package com.heb.reddit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.heb.reddit.network.AuthenticationService;
import com.heb.reddit.network.AuthenticationServiceClient;

public class HomeActivity extends AppCompatActivity {

    private Button authenticateButton;
    private AuthenticationServiceClient serviceClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        authenticateButton = (Button) findViewById(R.id.authenticate_button);

        serviceClient = new AuthenticationServiceClient();
        authenticateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serviceClient.authenticate();
            }
        });
    }
}
