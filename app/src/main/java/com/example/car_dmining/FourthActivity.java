package com.example.car_dmining;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FourthActivity extends AppCompatActivity {
    private TextView textview1,textview2,textview3,textview4,textview5,textview6,textview7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displayed_data);

        textview1 = findViewById(R.id.text_view1);
        textview2 = findViewById(R.id.text_view2);
        textview3 = findViewById(R.id.text_view3);
        textview4 = findViewById(R.id.text_view4);
        textview5 = findViewById(R.id.text_view5);
        textview6 = findViewById(R.id.text_view6);
        textview7 = findViewById(R.id.text_view7);


        // Get data passed from SecondActivity
        Bundle dataFromSecondActivity = getIntent().getExtras();

        if (dataFromSecondActivity != null) {
            //Error can be here !!!!
            String mpg = dataFromSecondActivity.getString("MPG");
            String displacement = dataFromSecondActivity.getString("Displacement");
            String acceleration = dataFromSecondActivity.getString("Acceleration");
            String weight = dataFromSecondActivity.getString("Weight");
            String horsepower = dataFromSecondActivity.getString("Horsepower");
            textview1.setText("MPG : " + mpg);
            textview2.setText("DISPLACEMENT : "+ displacement);
            textview3.setText("ACCELERATION : "+ acceleration);
            textview4.setText("WEIGHT : "+weight);
            textview5.setText("HORSEPOWER : "+horsepower );
        }
        // Get data passed from ThirdActivity
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            String selectedAlgorithm = extras.getString("SelectedAlgorithm");
            String knnValue = extras.getString("KNNValue");

            // Display received data in TextViews
            textview6.setText("Selected Algorithm: " + selectedAlgorithm);

            if (selectedAlgorithm.equals("KNN")) {
                textview7.setText("KNN Value: " + knnValue);
            } else {
                textview7.setText("KNN Value: Not Applicable");
            }
        }
    }

}