package id.ac.polinema.intentexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Checked;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

public class RegisterActivity extends AppCompatActivity implements Validator.ValidationListener {

    public static final String FULLNAME_KEY = "fullname";
    public static final String EMAIL_KEY = "email";
    public static final String PASSWORD_KEY = "password";
    public static final String CONFIRMPASSWORD_KEY = "confirmpassword";
    public static final String HOMEPAGE_KEY ="homepage";
    public static final String ABOUT_KEY ="about";
    public static final String IMAGE_KEY ="ProfileImage";
    public static final String USER_KEY ="user";

    private static final int GALLERY_REQUEST_CODE = 1;
    private static final String TAG = RegisterActivity.class.getCanonicalName();
    private EditText fullnameInput;
    @NotEmpty
    @Email
    private EditText emailInput;
    @Password(min = 6, scheme = Password.Scheme.ALPHA_NUMERIC_MIXED_CASE_SYMBOLS)
    private EditText passwordInput;
    @ConfirmPassword
    private EditText confirmpasswordInput;
    @NotEmpty
    private EditText homepageInput;
    @NotEmpty
    private EditText aboutInput;
    @Checked(message = "You must agree to the terms.")
    private CheckBox iAgreeCheckBox;
    Validator validator;

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
        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    public void handleSubmit(View view) {
        validator.validate();

        String fullname = fullnameInput.getText().toString();
        String email = emailInput.getText().toString();
        String pass = passwordInput.getText().toString();
        String conf = confirmpasswordInput.getText().toString();
        String homepage = homepageInput.getText().toString();
        String about = aboutInput.getText().toString();

        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra(FULLNAME_KEY, fullname);
        intent.putExtra(EMAIL_KEY,email);
        intent.putExtra(PASSWORD_KEY,pass);
        intent.putExtra(CONFIRMPASSWORD_KEY,conf);
        intent.putExtra(HOMEPAGE_KEY,homepage);
        intent.putExtra(ABOUT_KEY,about);
        startActivity(intent);
    }

    @Override
    public void onValidationSucceeded() {
        Toast.makeText(this, "Yay! we got it right!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ProfileActivity.class );
        intent.putExtra(USER_KEY,new UserModel(fullnameInput.getText().toString(),
                emailInput.getText().toString(),
                passwordInput.getText().toString(),
                homepageInput.getText().toString(),
                aboutInput.getText().toString()));
        intent.putExtra("profileImage",baos.toByteArray());
        startActivity(intent);
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }



}
