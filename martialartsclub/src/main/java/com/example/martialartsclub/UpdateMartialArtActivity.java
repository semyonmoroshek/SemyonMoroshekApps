package com.example.martialartsclub;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.martialartsclub.model.DatabaseHandler;
import com.example.martialartsclub.model.MartialArt;

import java.util.ArrayList;

public class UpdateMartialArtActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseHandler mDatabaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_martial_art);

        mDatabaseHandler = new DatabaseHandler(this);

        modifyUserInterface();
    }

    private void modifyUserInterface() {
        ArrayList<MartialArt> martialArtsObject = mDatabaseHandler.returnAllMartialArtObjects();
        if(martialArtsObject.size() > 0){
            ScrollView scrollView = new ScrollView(this);
            GridLayout gridLayout = new GridLayout(this);
            gridLayout.setRowCount(martialArtsObject.size());
            gridLayout.setColumnCount(5);

            TextView[] idTextViews = new TextView[martialArtsObject.size()];
            EditText[][] edtNamesPricesAndColors = new EditText[martialArtsObject.size()][3];
            Button[] modifyButtons = new Button[martialArtsObject.size()];

            Point screenSize = new Point();
            getWindowManager().getDefaultDisplay().getSize(screenSize);

            int screenWith = screenSize.x;
            int index = 0;

            for(MartialArt martialArtObject: martialArtsObject){
                idTextViews[index] = new TextView(this);
                idTextViews[index].setGravity(Gravity.CENTER);
                idTextViews[index].setText(martialArtObject.getMartialArtID() + "");

                edtNamesPricesAndColors[index][0] = new EditText(this);
                edtNamesPricesAndColors[index][1] = new EditText(this);
                edtNamesPricesAndColors[index][2] = new EditText(this);

                edtNamesPricesAndColors[index][0].setText(martialArtObject.getMartialArtName());
                edtNamesPricesAndColors[index][1].setText(martialArtObject.getMartialArtPrice() + "");
                edtNamesPricesAndColors[index][1].setInputType(InputType.TYPE_CLASS_NUMBER);
                edtNamesPricesAndColors[index][2].setText(martialArtObject.getMartialArtColor());

                edtNamesPricesAndColors[index][0].setId(martialArtObject.getMartialArtID() + 10);
                edtNamesPricesAndColors[index][1].setId(martialArtObject.getMartialArtID() + 20);
                edtNamesPricesAndColors[index][2].setId(martialArtObject.getMartialArtID() + 30);

                modifyButtons[index] = new Button(this);
                modifyButtons[index].setText("modify");
                modifyButtons[index].setId(martialArtObject.getMartialArtID());
                modifyButtons[index].setOnClickListener(this);

                gridLayout.addView(idTextViews[index], (int) (screenWith * 0.05), ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(edtNamesPricesAndColors[index][0],
                        (int) (screenWith * 0.20), ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(edtNamesPricesAndColors[index][1],
                        (int) (screenWith * 0.20), ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(edtNamesPricesAndColors[index][2],
                        (int) (screenWith * 0.20), ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(modifyButtons[index],
                        (int) (screenWith * 0.35),ViewGroup.LayoutParams.WRAP_CONTENT);

                        index++;
            }
            scrollView.addView(gridLayout);
            setContentView(scrollView);
        }
    }

    @Override
    public void onClick(View view) {

        int martialArtObjectId = view.getId();

        EditText edtMartialArtName = findViewById(martialArtObjectId + 10);
        EditText edtMartialArtPrice = findViewById(martialArtObjectId + 20);
        EditText edtMartialArtColor = findViewById(martialArtObjectId + 30);

        String martialArtNameStringValue = edtMartialArtName.getText().toString();
        String martialArtPriceStringValue = edtMartialArtPrice.getText().toString();
        String martialArtColorStringValue = edtMartialArtColor.getText().toString();



        try{
            double martialArtPriceDoubleValue =
                    Double.parseDouble(martialArtPriceStringValue);

            mDatabaseHandler.modifyMartialArtObject(
                    martialArtObjectId,
                    martialArtNameStringValue,
                    martialArtPriceDoubleValue,
                    martialArtColorStringValue);

            Toast.makeText(this, "Is updated", Toast.LENGTH_SHORT).show();
        }catch (NumberFormatException e){


        }


    }
}
