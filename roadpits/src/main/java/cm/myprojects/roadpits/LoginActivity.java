package cm.myprojects.roadpits;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    enum State{
        SIGNUP, LOGIN;
    }

    private State mState;

    @BindView(R.id.tb_login) Toolbar mToolbar;
    @BindView(R.id.et_username) EditText editUsername;
    @BindView(R.id.et_password) EditText editPassword;
    @BindView(R.id.btn_sign_up_login) Button btnSignUpLogin;
    @BindView(R.id.btn_sign_without_reg) Button getBtnSignUpWithoutReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        ParseInstallation.getCurrentInstallation().saveInBackground();
        if(ParseUser.getCurrentUser() != null){
            enterToAccount();
        }

        ActionBar actionBar = getSupportActionBar();

        mState = State.SIGNUP;

        btnSignUpLogin.setOnClickListener(View -> signUpLogin());
        getBtnSignUpWithoutReg.setOnClickListener(View -> loginWithoutRegistration());
    }

    private void loginWithoutRegistration() {
        if(ParseUser.getCurrentUser() == null){
            ParseAnonymousUtils.logIn((user, e) -> {
                if(user != null && e == null){
                    Toast.makeText(LoginActivity.this, "Welcome!", Toast.LENGTH_SHORT).show();
                    user.saveInBackground(e1 -> {
                        enterToAccount();
                    });
                }
            });
        }
    }

    private void signUpLogin() {
        String username = editUsername.getText().toString();
        String password = editPassword.getText().toString();

        if(mState == State.SIGNUP ){
            ParseUser appUser = new ParseUser();
            appUser.setUsername(username);
            appUser.setPassword(password);
            appUser.signUpInBackground(e -> {
                if(e == null) {
                    Toast.makeText(LoginActivity.this, "Signed up!", Toast.LENGTH_LONG).show();
                    enterToAccount();
                }
            });


        }else if(mState == State.LOGIN){
            ParseUser.logInInBackground(username, password, (user, e) -> {
                if(user != null && e == null){
                    Toast.makeText(LoginActivity.this, "User Logged", Toast.LENGTH_SHORT).show();
                    enterToAccount();
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_login:
                if(mState == State.SIGNUP){
                    mState = State.LOGIN;
                    item.setTitle("Sign up");
                    btnSignUpLogin.setText("Log in");
                }else if(mState == State.LOGIN){
                    mState = State.SIGNUP;
                    btnSignUpLogin.setText("Sign up");
                }
        }
        return super.onOptionsItemSelected(item);
    }

    public void enterToAccount(){
        if(ParseUser.getCurrentUser() != null){
            Intent intent = new Intent(this, MapActivity.class);
            startActivity(intent);

        }
    }
}
