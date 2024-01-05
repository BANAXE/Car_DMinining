package com.example.car_dmining;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FourthActivity extends AppCompatActivity {
    private TextView textview5,textview6,textview7;
    private String mpg, horsepower, weight, acceleration, displacement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displayed_data);

        textview5 = findViewById(R.id.text_view5);
        textview6 = findViewById(R.id.text_view6);
        textview7 = findViewById(R.id.text_view7);


        // Get data passed from SecondActivity
        Bundle dataFromSecondActivity = getIntent().getExtras();

        if (dataFromSecondActivity != null) {
            //Error can be here !!!!
            mpg = dataFromSecondActivity.getString("MPG");
            displacement = dataFromSecondActivity.getString("Displacement");
            acceleration = dataFromSecondActivity.getString("Acceleration");
            weight = dataFromSecondActivity.getString("Weight");
            horsepower = dataFromSecondActivity.getString("Horsepower");
            /*textview1.setText("MPG : " + mpg);
            textview2.setText("DISPLACEMENT : "+ displacement);
            textview3.setText("ACCELERATION : "+ acceleration);
            textview4.setText("WEIGHT : "+weight);
            textview5.setText("HORSEPOWER : "+horsepower );*/
        }
        // Get data passed from ThirdActivity
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            String selectedAlgorithm = extras.getString("SelectedAlgorithm");
            String knnValue = extras.getString("KNNValue");

            String[] labels = {
                    "japanese", "japanese", "japanese", "japanese", "japanese",
                    "japanese", "japanese", "american", "japanese", "european",
                    "european", "european", "european", "japanese", "european",
            };
            if (selectedAlgorithm.equals("KNN")){
                double mpgValue = Double.parseDouble(mpg);
                double displacementValue = Double.parseDouble(displacement);
                double accelerationValue = Double.parseDouble(acceleration);
                double weightValue = Double.parseDouble(weight);
                double horsepowerValue = Double.parseDouble(horsepower);
                double[][] trainingData = {
                        {35, 72, 69, 1613, 18},
                        {31, 76, 52, 1649, 17},
                        {39, 79, 58, 1755, 17},
                        {35, 81, 60, 1760, 16},
                        {31, 71, 65, 1773, 19},
                        {33, 91, 53, 1795, 18},
                        {33, 91, 53, 1795, 17},
                        {36, 98, 66, 1800, 14},
                        {36, 91, 60, 1800, 16},
                        {30, 97, 71, 1825, 12},
                        {36, 79, 58, 1825, 19},
                        {27, 97, 60, 1834, 19},
                        {26, 97, 46, 1835, 21},
                        {32, 71, 65, 1836, 21},
                        {30, 80, 62, 1845, 15},
                };

                double[] testData = {mpgValue, displacementValue, accelerationValue, weightValue, horsepowerValue};
                int k = Integer.parseInt(knnValue);

                String predictedOrigin = com.example.car_dmining.KNN.classify(trainingData, labels, testData, k);
                textview7.setText("Predicted Origin: " + predictedOrigin);
            } else if (selectedAlgorithm.equals("Bayes Network")) {
                int mpgValue = Integer.parseInt(mpg);
                int displacementValue = Integer.parseInt(displacement);
                int accelerationValue = Integer.parseInt(acceleration);
                int weightValue = Integer.parseInt(weight);
                int horsepowerValue = Integer.parseInt(horsepower);

                int[][] trainingData = {
                        {35, 72, 69, 1613, 18, 1}, // japanese
                        {31, 76, 52, 1649, 17, 1}, // japanese
                        {39, 79, 58, 1755, 17, 1}, // japanese
                        {35, 81, 60, 1760, 16, 1}, // japanese
                        {31, 71, 65, 1773, 19, 1}, // japanese
                        {33, 91, 53, 1795, 18, 1}, // japanese
                        {33, 91, 53, 1795, 17, 1}, // japanese
                        {36, 98, 66, 1800, 14, 2}, // american
                        {36, 91, 60, 1800, 16, 1}, // japanese
                        {30, 97, 71, 1825, 12, 3}, // european
                        {36, 79, 58, 1825, 19, 3}, // european
                        {27, 97, 60, 1834, 19, 3}, // european
                        {26, 97, 46, 1835, 21, 3}, // european
                        {32, 71, 65, 1836, 21, 1}, // japanese
                        {30, 80, 62, 1845, 15, 3}  // european
                };

                int[] testInstance = {mpgValue, displacementValue, accelerationValue, weightValue, horsepowerValue};

                Bayes.NaiveBayesModel model = Bayes.train(trainingData);
                String predictedClass = Bayes.predict(model, testInstance);
                textview7.setText("Predicted Class: " + predictedClass);
            } else if (selectedAlgorithm.equals("Decision Tree")) {
                int weightValue = Integer.parseInt(weight);
                double horsePowerValue = Double.parseDouble(horsepower);
                double displacementValue = Double.parseDouble(displacement);
                String result = DecisionTree.classify(weightValue, horsePowerValue, displacementValue);
                textview7.setText("Predicted Class: " + result);
            } else {
                textview7.setText("Choose a ML Algorithm :D");
            }
            // Display received data in TextViews
            textview5.setText("Selected Algorithm: " + selectedAlgorithm);

            if (selectedAlgorithm.equals("KNN")) {
                textview6.setText("KNN Value: " + knnValue);
            } else {
                //textview6.setText("KNN Value: Not Applicable");
                textview6.setText("");
            }
        }
    }

}