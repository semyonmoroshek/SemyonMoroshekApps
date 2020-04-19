package com.example.martialartsclub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.martialartsclub.model.DatabaseHandler;
import com.example.martialartsclub.model.MartialArt;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddMartialArtActivity extends AppCompatActivity {

    @BindView(R.id.et_name) EditText editName;
    @BindView(R.id.et_price) EditText editPrice;
    @BindView(R.id.et_color) EditText editColor;
    @BindView(R.id.btn_add_martial_art) Button btnAddMartialArt;
    @BindView(R.id.btn_go_back_from_add) Button btnGoBak;

    DatabaseHandler mDatabaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_martial_art);
        ButterKnife.bind(this);

        mDatabaseHandler = new DatabaseHandler(this);

        btnAddMartialArt.setOnClickListener(View -> addMartialArtObjectToDatabase());
        btnGoBak.setOnClickListener(View -> goBack());

    }

    private void goBack() {
        finish();
    }

    private void addMartialArtObjectToDatabase(){
        String name = editName.getText().toString();
        String price = editPrice.getText().toString();
        String color = editColor.getText().toString();

        double priceDouble = Double.parseDouble(price);

        MartialArt martialArtObject = new MartialArt(0, name, priceDouble, color);

        mDatabaseHandler.addMartialArt(martialArtObject);

        Toast.makeText(this, martialArtObject.toString() + " saved", Toast.LENGTH_LONG).show();

    }
}
