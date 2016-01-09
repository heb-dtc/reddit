package com.heb.reddit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.heb.reddit.network.AuthenticationServiceClient;
import com.heb.reddit.network.TokenSafe;

public class HomeActivity extends AppCompatActivity {

    private Button authenticateButton;
    private Button showTokenButton;
    private AuthenticationServiceClient serviceClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        authenticateButton = (Button) findViewById(R.id.authenticate_button);
        showTokenButton = (Button) findViewById(R.id.show_token_button);

        final TokenSafe tokenSafe = TokenSafe.newInstance(getApplicationContext());
        serviceClient = new AuthenticationServiceClient(tokenSafe);
        authenticateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serviceClient.authenticate();
            }
        });

        showTokenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String token = tokenSafe.retrieveToken();

                Toast.makeText(getApplicationContext(), "Current token is: " + token, Toast.LENGTH_LONG).show();
            }
        });
    }
}
