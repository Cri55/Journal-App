package com.example.cristian.journalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    private LinearLayout Profsection;
    private SignInButton SignIn;
    private Button Signout;
    private ImageView Prof_pie;
    private TextView Name, Email;
    private GoogleApiClient googleApiClient;
    private FirebaseAuth mAuth;
    private static final int REQ_CODE = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Profsection = (LinearLayout)findViewById(R.id.profsection);
        Prof_pie = (ImageView)findViewById(R.id.propic);
        Name =(TextView)findViewById(R.id.name);
        Email=(TextView)findViewById(R.id.email);
        //find sign out button and cast onclicklistener
        //find sign in button and cast onclicklistener
        SignIn = (SignInButton)findViewById( R.id.bn_login);
        SignIn.setOnClickListener(this);

        //set user details to invisible
        Profsection.setVisibility(View.GONE);

        // Configure Google Sign In
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this,this).addApi(Auth.GOOGLE_SIGN_IN_API,signInOptions).build();



    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check for existing Google Sign In account, if the user is already signed in
// the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        Intent skiplog  =new Intent(this, MainActivity.class);
        startActivity(skiplog);
}

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bn_login:
            signIn();
            break;
            case R.id.logout:
                signout();
                break;
        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) { }

    //sign in method
    public void signIn(){
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent,REQ_CODE);
    }

    //signout
    public void signout(){
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>(){
            @Override
            public void onResult(@NonNull Status status) {
            //TODO start login activity after user logout

            }
        });
    }

    public void handleResult(GoogleSignInResult result) {
        if(result.isSuccess()){
            GoogleSignInAccount account = result.getSignInAccount();
            Intent startact =new Intent(this, MainActivity.class);
            startActivity(startact);
            String name = account.getDisplayName();
            String email = account.getEmail();
            //String img_url = account.getPhotoUrl().toString();
            //set got info to the display
            //Glide.with(this).load(img_url).into(Prof_pie);
            // Sign in success, update UI with the signed-in user's information
        }
        else{

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQ_CODE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
        }

    }


}
