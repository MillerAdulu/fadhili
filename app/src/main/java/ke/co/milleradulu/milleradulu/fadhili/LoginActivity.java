package ke.co.milleradulu.milleradulu.fadhili;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import ke.co.milleradulu.milleradulu.fadhili.apihandler.APIHelper;
import ke.co.milleradulu.milleradulu.fadhili.apihandler.APIServiceProvider;
import ke.co.milleradulu.milleradulu.fadhili.apihandler.clients.DonorClient;
import ke.co.milleradulu.milleradulu.fadhili.apihandler.models.Donor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

  private Button btnLogin, btnSignUp;
  private EditText emailLogin,passLogin;
  private TextView googleSignup;
  SessionManagement session;
  Donor donor;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    session = new SessionManagement(this);

    btnLogin = findViewById(R.id.btn_loginLogin);
    btnSignUp = findViewById(R.id.btn_signupLogin);
    emailLogin = findViewById(R.id.text_emailLogin);
    passLogin = findViewById(R.id.text_passLogin);

    btnLogin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View arg) {
        logIn();
      }
    });

    btnSignUp.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        startActivity(
          new Intent(
            LoginActivity.this,
            SignupActivity.class
            )
        );
      }

    });

  }

  void logIn() {
    String email = emailLogin.getText().toString();
    String password = passLogin.getText().toString();

    if(email.trim().length() > 0 && password.trim().length() > 0){
      donor = new Donor();
      donor.setDonorEmail(email);
      donor.setDonorPassword(password);

      DonorClient donorClient = APIServiceProvider.createService(DonorClient.class);
      Call<Donor> donorCall = donorClient.login(donor);

      APIHelper.enqueWithRetry(donorCall, new Callback<Donor>() {
        @Override
        public void onResponse(@NonNull Call<Donor> call, @NonNull Response<Donor> response) {
          donor = response.body();

          Toast.makeText(
            LoginActivity.this,
            "Logged in successfully!",
            Toast.LENGTH_SHORT
          ).show();

          session.createLoginSession(
            String.format(
              Locale.ENGLISH,
              "%s",
              donor.getDonorId()
            ),
            donor.getDonorUserName()
          );

          startActivity(
            new Intent(
              LoginActivity.this,
              HomeActivity.class
            )
          );
        }

        @Override
        public void onFailure(@NonNull Call<Donor> call, @NonNull Throwable t) {
          Toast.makeText(
            LoginActivity.this,
            "Unable to log you in, please confirm your credentials!",
            Toast.LENGTH_SHORT
          ).show();
        }
      });

    }
  }
}
