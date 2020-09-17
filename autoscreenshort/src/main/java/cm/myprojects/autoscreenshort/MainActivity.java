package cm.myprojects.autoscreenshort;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import cm.myprojects.autoscreenshort.helper.ScreenshotUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatActivity activity = MainActivity.this;

    private LinearLayout parentView;
    private Button buttonScreenshotActivity;
    private Button buttonScreenshotView;
    private Button buttonSaveScreenshot;
    private Button buttonReset;

    private ImageView imageViewShowScreenshot;

    private Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing the views
        initViews();

        // initializing the listeners
        initListeners();
    }

    private void initViews() {

        parentView = findViewById(R.id.parentView);

        buttonScreenshotActivity = findViewById(R.id.buttonScreenshotActivity);
        buttonScreenshotView = findViewById(R.id.buttonScreenshotView);
        buttonSaveScreenshot = findViewById(R.id.buttonSaveScreenshot);
        buttonReset = findViewById(R.id.buttonReset);

        imageViewShowScreenshot = findViewById(R.id.imageViewShowScreenshot);

    }

    private void initListeners() {

        buttonScreenshotActivity.setOnClickListener(this);
        buttonScreenshotView.setOnClickListener(this);
        buttonSaveScreenshot.setOnClickListener(this);
        buttonReset.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.buttonScreenshotActivity:
                bitmap = ScreenshotUtil.getInstance().takeScreenshotForScreen(activity); // Take ScreenshotUtil for activity
                imageViewShowScreenshot.setImageBitmap(bitmap);
                break;

            case R.id.buttonScreenshotView:
                bitmap = ScreenshotUtil.getInstance().takeScreenshotForView(parentView); // Take ScreenshotUtil for any view
                imageViewShowScreenshot.setImageBitmap(bitmap);
                break;

            case R.id.buttonSaveScreenshot:
                requestPermissionAndSave();
                break;

            case R.id.buttonReset:
                bitmap = null;
                imageViewShowScreenshot.setImageBitmap(bitmap);
                break;
        }
    }

    private void requestPermissionAndSave(){

    }


}
