package ke.co.milleradulu.milleradulu.fadhili;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {
  SessionManagement session;
  Button btnLogout;
  TextView donorName;

  HashMap<String, String> donor;

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);

    donorName = findViewById(R.id.donor_name);

    session = new SessionManagement(getApplicationContext());

    session.checkLogin();

    donor = session.getUserDetails();


    donorName.setText(
      donor.get(
        SessionManagement.KEY_NAME
      )
    );

  }
}
