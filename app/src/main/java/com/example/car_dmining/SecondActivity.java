package com.example.car_dmining;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private EditText mpgEditText, displacementEditText, accelerationEditText, weightEditText, horsepowerEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datainput_layout);

        mpgEditText = findViewById(R.id.mpg_edit_text);
        displacementEditText = findViewById(R.id.displacement_edit_text);
        accelerationEditText = findViewById(R.id.acceleration_edit_text);
        weightEditText = findViewById(R.id.weight_edit_text);
        horsepowerEditText = findViewById(R.id.horsepower_edit_text);

        Button chooseMLButton = findViewById(R.id.choose_ml_algorithms_button);
        chooseMLButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get data from EditText fields
                String mpg = mpgEditText.getText().toString();
                String displacement = displacementEditText.getText().toString();
                String acceleration = accelerationEditText.getText().toString();
                String weight = weightEditText.getText().toString();
                String horsepower = horsepowerEditText.getText().toString();

                // Create an intent to switch to the third activity
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);

                // Pass data to the third activity using intent extras
                intent.putExtra("MPG", mpg);
                intent.putExtra("Displacement", displacement);
                intent.putExtra("Acceleration", acceleration);
                intent.putExtra("Weight", weight);
                intent.putExtra("Horsepower", horsepower);

                // Start the third activity
                startActivity(intent);
            }
        });
    }
}