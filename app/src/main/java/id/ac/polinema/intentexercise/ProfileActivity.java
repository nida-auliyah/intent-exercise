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
        fullnameText = findViewById(R.id.text_fullname);
        emailText = findViewById(R.id.text_email);
        homepageText = findViewById(R.id.text_homepage);
        aboutText = findViewById(R.id.text_about);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Bundle extra = getIntent().getExtras();
            Bitmap bitmap = extra.getParcelable("ProfileImage");


            profileImage.setImageBitmap(bitmap);
            aboutText.setText(extras.getString("about"));
            fullnameText.setText(extras.getString("fullname"));
            emailText.setText(extras.getString("email"));
            homepageText.setText(extras.getString("homepage"));
            // TODO: display value here
        }
    }
}
