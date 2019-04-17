package com.mani.signin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {
    FirebaseAuth.AuthStateListener authStateListener;
    FirebaseAuth mAuth;
    Button signout,profile;
    GoogleSignInClient mGoogleSignInClient;




    @Override
    protected void onStart() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        Log.e("ak47","on Start");
        super.onStart();
        Log.e("ak47","on Start after super");
        mAuth.addAuthStateListener(authStateListener);
        Log.e("ak47","on Start Ends");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("ak47","on Create");
        super.onCreate(savedInstanceState);
        mAuth=FirebaseAuth.getInstance();



        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()==null)
                {
                    Log.e("ak47","user null");
                    Intent intent = new Intent(MainActivity.this,SignIn.class);
                    startActivity(intent);
                }

            }
        };
        setContentView(R.layout.activity_main);
        signout=findViewById(R.id.button);


        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                mGoogleSignInClient.signOut();

            }
        });
    }
}
