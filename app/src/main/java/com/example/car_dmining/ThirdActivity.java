package com.example.car_dmining;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {
    private EditText knnInput;
    private RadioGroup radioGroupMLAlgorithms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ml_algorithms);

        knnInput = findViewById(R.id.knn_input);
        radioGroupMLAlgorithms = findViewById(R.id.radio_group_ml_algorithms);

        Button resultsButton = findViewById(R.id.results_button);
        resultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedAlgorithm = getSelectedAlgorithm();
                String knnValue = knnInput.getText().toString();

                // Get data passed from SecondActivity
                Bundle dataFromSecondActivity = getIntent().getExtras();

                // Create an intent to switch to FourthActivity
                Intent intent = new Intent(ThirdActivity.this, FourthActivity.class);

                // Pass data to FourthActivity using intent extras
                intent.putExtra("SelectedAlgorithm", selectedAlgorithm);
                intent.putExtra("KNNValue", knnValue);
                intent.putExtras(dataFromSecondActivity);

                // Start the FourthActivity
                startActivity(intent);
            }
        });
    }

    private String getSelectedAlgorithm() {
        int selectedRadioButtonId = radioGroupMLAlgorithms.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
        return selectedRadioButton.getText().toString();
    }
}