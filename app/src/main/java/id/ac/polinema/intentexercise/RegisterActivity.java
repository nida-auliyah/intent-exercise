package id.ac.polinema.intentexercise;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import java.io.IOException;
public class RegisterActivity extends AppCompatActivity {

    public static final String FULLNAME_KEY = "fullname";
    public static final String EMAIL_KEY = "email";
    public static final String PASSWORD_KEY = "password";
    public static final String CONFIRMPASSWORD_KEY = "confirmpassword";
    public static final String HOMEPAGE_KEY ="homepage";
    public static final String ABOUT_KEY ="about";
    public static final String IMAGE_KEY ="ProfileImage";

    private static final int GALLERY_REQUEST_CODE = 1;
    private static final String TAG = RegisterActivity.class.getCanonicalName();
    private EditText fullnameInput;
    private EditText emailInput;
    private EditText passwordInput;
    private EditText confirmpasswordInput;
    private EditText homepageInput;
    private EditText aboutInput;
    private ImageView imageprofile;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fullnameInput = findViewById(R.id.text_fullname);
        emailInput = findViewById(R.id.text_email);
        passwordInput = findViewById(R.id.text_password);
        confirmpasswordInput = findViewById(R.id.text_confirm_password);
        homepageInput = findViewById(R.id.text_homepage);
        aboutInput = findViewById(R.id.text_about);
        imageprofile = findViewById(R.id.image_profile);
    }

    public void handleSubmit(View view) {

        String fullname = fullnameInput.getText().toString();
        String email = emailInput.getText().toString();
        String pass = passwordInput.getText().toString();
        String conf = confirmpasswordInput.getText().toString();
        String homepage = homepageInput.getText().toString();
        String about = aboutInput.getText().toString();
        if(fullname.equals("")||email.equals("")||pass.equals("")||conf.equals("")||homepage.equals("")||about.equals("")||
                bitmap==null||imageprofile.equals("")){
            Toast.makeText(getApplicationContext(), "Data Harus diisi!",Toast.LENGTH_SHORT).show();

        }else {
            Intent intent = new Intent(this, ProfileActivity.class);
            imageprofile.buildDrawingCache();
            Bitmap bit = imageprofile.getDrawingCache();
            Bundle extra =new Bundle();
            extra.putParcelable(IMAGE_KEY,bit);
            intent.putExtra(FULLNAME_KEY, fullname);
            intent.putExtra(EMAIL_KEY, email);
            intent.putExtras(extra);
            intent.putExtra(PASSWORD_KEY, pass);
            intent.putExtra(CONFIRMPASSWORD_KEY, conf);
            intent.putExtra(HOMEPAGE_KEY, homepage);
            intent.putExtra(ABOUT_KEY, about);
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }

        if (requestCode==GALLERY_REQUEST_CODE) {
            if (data != null) {
                try {
                    Uri imageUri = data.getData();
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    imageprofile.setImageBitmap(bitmap);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }

    public void handleimage(View view) {
        Intent in = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(in, GALLERY_REQUEST_CODE);
    }
}
