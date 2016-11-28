package com.example.celiachu.informe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;

public class LoginScreen extends Activity {

    public static int APP_REQUEST_CODE = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AccountKit.initialize(getApplicationContext());
        setContentView(R.layout.login_screen);

        AccessToken accessToken = AccountKit.getCurrentAccessToken();

        if (accessToken == null) {

            Log.v("Test", "Test12");
            logIn();
        } else {
            Log.v("Test", "Test123");
            goToMyLoggedInActivity();
        }
    }

    @Override
    protected void onActivityResult(
            final int requestCode,
            final int resultCode,
            final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == APP_REQUEST_CODE) {
            AccountKitLoginResult loginResult = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            String toastMessage;
            if (loginResult.getError() != null) {
                toastMessage = loginResult.getError().getErrorType().getMessage();
                showErrorActivity(loginResult.getError());
            } else if (loginResult.wasCancelled()) {
                toastMessage = "Login Cancelled";
            } else {
                if (loginResult.getAccessToken() != null) {
                    toastMessage = "Success:" + loginResult.getAccessToken().getAccountId();
                } else {
                    toastMessage = String.format(
                            "Success:%s...",
                            loginResult.getAuthorizationCode().substring(0,10));
                }

                goToMyLoggedInActivity();
            }
        }
    }

    private void showErrorActivity(final AccountKitError error) {
        Log.println(Log.ASSERT, "AccountKit", error.toString());
    }

    private void goToMyLoggedInActivity(){
        final Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }

    public void logIn() {
        final Intent intent = new Intent(this, AccountKitActivity.class);

        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(
                        LoginType.EMAIL,
                        AccountKitActivity.ResponseType.TOKEN);

        intent.putExtra(
                AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                configurationBuilder.build());

        this.startActivityForResult(intent, APP_REQUEST_CODE);
    }
}
