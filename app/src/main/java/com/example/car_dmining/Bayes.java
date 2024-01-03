package com.example.car_dmining;

import java.util.HashMap;
import java.util.Map;
public class Bayes {
    public static class NaiveBayesModel {
        private Map<String, Double> classProbabilities;
        private Map<String, Map<Integer, Double>> featureProbabilities;

        public NaiveBayesModel(Map<String, Double> classProbabilities, Map<String, Map<Integer, Double>> featureProbabilities) {
            this.classProbabilities = classProbabilities;
            this.featureProbabilities = featureProbabilities;
        }

        public Map<String, Double> getClassProbabilities() {
            return classProbabilities;
        }

        public Map<String, Map<Integer, Double>> getFeatureProbabilities() {
            return featureProbabilities;
        }
    }

    public static NaiveBayesModel train(int[][] trainingData) {
        int numInstances = trainingData.length;
        int numFeatures = trainingData[0].length - 1; // Last column is the class label

        // Count occurrences of each class
        Map<String, Integer> classCounts = new HashMap<>();
        for (int[] instance : trainingData) {
            String label = getClassLabel(instance);
            classCounts.put(label, classCounts.getOrDefault(label, 0) + 1);
        }

        // Calculate class probabilities
        Map<String, Double> classProbabilities = new HashMap<>();
        for (Map.Entry<String, Integer> entry : classCounts.entrySet()) {
            classProbabilities.put(entry.getKey(), (double) entry.getValue() / numInstances);
        }

        // Calculate feature probabilities
        Map<String, Map<Integer, Double>> featureProbabilities = new HashMap<>();
        for (int[] instance : trainingData) {
            String label = getClassLabel(instance);
            featureProbabilities.putIfAbsent(label, new HashMap<>());

            for (int i = 0; i < numFeatures; i++) {
                int featureValue = instance[i];
                featureProbabilities.get(label).put(featureValue,
                        featureProbabilities.get(label).getOrDefault(featureValue, 0.0) + 1);
            }
        }

        // Normalize feature probabilities
        for (Map.Entry<String, Map<Integer, Double>> entry : featureProbabilities.entrySet()) {
            String label = entry.getKey();
            Map<Integer, Double> labelFeatureProbabilities = entry.getValue();
            double total = labelFeatureProbabilities.values().stream().mapToDouble(Double::doubleValue).sum();

            for (Map.Entry<Integer, Double> featureEntry : labelFeatureProbabilities.entrySet()) {
                featureEntry.setValue(featureEntry.getValue() / total);
            }
        }

        return new NaiveBayesModel(classProbabilities, featureProbabilities);
    }

    public static String predict(NaiveBayesModel model, int[] instance) {
        Map<String, Double> classProbabilities = model.getClassProbabilities();
        Map<String, Map<Integer, Double>> featureProbabilities = model.getFeatureProbabilities();

        double minLikelihood = Double.POSITIVE_INFINITY;
        String predictedClass = null;

        for (Map.Entry<String, Double> entry : classProbabilities.entrySet()) {
            String label = entry.getKey();
            double classProb = Math.log(entry.getValue());

            for (int i = 0; i < instance.length; i++) {
                int featureValue = instance[i];
                Map<Integer, Double> labelFeatureProbabilities = featureProbabilities.get(label);

                // Using Laplace smoothing to handle unseen feature values
                double prob = labelFeatureProbabilities.getOrDefault(featureValue, 1.0 / featureProbabilities.size());
                classProb += Math.log(prob);
            }

            if (classProb < minLikelihood) {
                minLikelihood = classProb;
                predictedClass = label;
            }
        }

        return predictedClass;
    }

    private static String getClassLabel(int[] instance) {
        int classIndex = instance.length - 1;
        int classLabel = instance[classIndex];

        switch (classLabel) {
            case 1:
                return "japanese";
            case 2:
                return "american";
            case 3:
                return "european";
            default:
                throw new IllegalArgumentException("Unknown class label");
        }
    }
}
