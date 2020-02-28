package id.ac.polinema.intentexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    private TextView fullnameText;
    private TextView emailText;
    private TextView homepageText;
    private TextView aboutText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fullnameText = findViewById(R.id.text_fullname);
        emailText = findViewById(R.id.text_email);
        homepageText = findViewById(R.id.text_homepage);
        aboutText = findViewById(R.id.text_about);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String a = extras.getString(RegisterActivity.ABOUT_KEY);
            String fullname = extras.getString(RegisterActivity.FULLNAME_KEY);
            String  email = extras.getString(RegisterActivity.EMAIL_KEY);
            String  homepage = extras.getString(RegisterActivity.HOMEPAGE_KEY);

            aboutText.setText(a);
            fullnameText.setText(fullname);
            emailText.setText(email);
            homepageText.setText(homepage);
            // TODO: display value here
        }
    }
}
