package id.ac.polinema.intentexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    private TextView fullnameText;
    private TextView emailText;
    private TextView homepageText;
    private TextView aboutText;
    private ImageView profileImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileImage = findViewById(R.id.image_profile);
        fullnameText = findViewById(R.id.label_fullname);
        emailText = findViewById(R.id.label_email);
        homepageText = findViewById(R.id.label_homepage);
        aboutText = findViewById(R.id.label_about);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Bundle extra = getIntent().getExtras();
            Bitmap bmp = extra.getParcelable("ProfileImage");

            String a = extras.getString(RegisterActivity.ABOUT_KEY);
            String fullname = extras.getString(RegisterActivity.FULLNAME_KEY);
            String  email = extras.getString(RegisterActivity.EMAIL_KEY);
            String  homepage = extras.getString(RegisterActivity.HOMEPAGE_KEY);


            profileImage.setImageBitmap(bmp);
            aboutText.setText(a);
            fullnameText.setText(fullname);
            emailText.setText(email);
            homepageText.setText(homepage);
            // TODO: display value here
        }
    }
}
