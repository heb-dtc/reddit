package com.heb.reddit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.heb.reddit.network.authentication.AuthenticationServiceClient;
import com.heb.reddit.network.authentication.TokenSafe;
import com.heb.reddit.network.content.ContentServiceClient;

public class HomeActivity extends AppCompatActivity {
    private Button authenticateButton;
    private Button showTokenButton;
    private Button fetchContentButton;

    private AuthenticationServiceClient serviceClient;
    private ContentServiceClient contentServiceClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        authenticateButton = (Button) findViewById(R.id.authenticate_button);
        showTokenButton = (Button) findViewById(R.id.show_token_button);
        fetchContentButton = (Button) findViewById(R.id.fetch_content_button);

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

        contentServiceClient = new ContentServiceClient(tokenSafe);
        fetchContentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentServiceClient.fetchPageContent("new");
                contentServiceClient.fetchSubRedditContent("tifu");
            }
        });
    }
}
