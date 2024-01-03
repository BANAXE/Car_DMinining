package com.example.car_dmining;

import java.util.*;

public class KNN {

    public static double euclideanDistance(double[] point1, double[] point2) {
        double sum = 0.0;
        for (int i = 0; i < point1.length; i++) {
            sum += Math.pow((point1[i] - point2[i]), 2);
        }
        return Math.sqrt(sum);
    }

    public static int[] kNearestNeighbors(double[][] trainingData, double[] testData, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingDouble(o -> -o[1]));

        for (int i = 0; i < trainingData.length; i++) {
            double dist = euclideanDistance(trainingData[i], testData);
            pq.offer(new int[]{i, (int) dist});
            if (pq.size() > k) {
                pq.poll();
            }
        }

        int[] neighborsIndices = new int[k];
        while (!pq.isEmpty()) {
            neighborsIndices[--k] = pq.poll()[0];
        }
        return neighborsIndices;
    }

    public static String classify(double[][] trainingData, String[] labels, double[] testData, int k) {
        int[] neighborsIndices = kNearestNeighbors(trainingData, testData, k);

        HashMap<String, Integer> votes = new HashMap<>();
        for (int index : neighborsIndices) {
            String label = labels[index];
            votes.put(label, votes.getOrDefault(label, 0) + 1);
        }

        int maxVotes = 0;
        String predictedClass = "";
        for (Map.Entry<String, Integer> entry : votes.entrySet()) {
            if (entry.getValue() > maxVotes) {
                maxVotes = entry.getValue();
                predictedClass = entry.getKey();
            }
        }
        return predictedClass;
    }
}