/*
package com.fyp.hca.services;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.classification.LogisticRegressionModel;
import org.apache.spark.mllib.classification.LogisticRegressionWithLBFGS;
import org.apache.spark.mllib.evaluation.MulticlassMetrics;
import org.apache.spark.mllib.feature.StandardScaler;
import org.apache.spark.mllib.feature.StandardScalerModel;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.util.MLUtils;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;

public class SparkService {
    public static void main(String[] args) {
        // Create a Spark session
        SparkSession spark = SparkSession.builder()
                .appName("JavaLogisticRegressionExample")
                .config("spark.master", "local")
                .getOrCreate();

        // Create a Java Spark Context
        JavaSparkContext jsc = new JavaSparkContext(spark.sparkContext());

        // Load and parse the data file into a JavaRDD of LabeledPoint
        String path = "data/mllib/sample_libsvm_data.txt";
        JavaRDD<LabeledPoint> data = MLUtils.loadLibSVMFile(jsc.sc(), path).toJavaRDD();

        // Feature scaling
        StandardScaler scaler = new StandardScaler(true, true);
        StandardScalerModel scalerModel = scaler.fit(data.map(LabeledPoint::features).rdd());


        JavaRDD<Object> scaledData = data.map(lp ->
                new LabeledPoint(lp.label(), scalerModel.transform(lp.features()))
        );

        JavaRDD<Object>[] splits = scaledData.randomSplit(new double[]{0.6, 0.4}, 11L);
        JavaRDD<Object> training = splits[0];
        JavaRDD<Object> test = splits[1];

        // Train a logistic regression model
        LogisticRegressionModel model = new LogisticRegressionWithLBFGS()
                .setNumClasses(2)
                .run((RDD<LabeledPoint>) training.rdd());

        // Compute raw scores on the test set
        JavaRDD<Object> predictionAndLabels = test.map((Function<LabeledPoint, Object>) p -> {
            Double prediction = model.predict(p.features());
            return new Tuple2<>(prediction, p.label());
        });

        // Get evaluation metrics
        MulticlassMetrics metrics = new MulticlassMetrics(predictionAndLabels.rdd());

        // Confusion matrix
        System.out.println("Confusion matrix: \n" + metrics.confusionMatrix());

        // Overall statistics
        System.out.println("Accuracy = " + metrics.accuracy());

        // Statistics by class
        for (int i = 0; i < metrics.labels().length; i++) {
            System.out.format("Class %f precision = %f\n", metrics.labels()[i], metrics.precision(metrics.labels()[i]));
            System.out.format("Class %f recall = %f\n", metrics.labels()[i], metrics.recall(metrics.labels()[i]));
            System.out.format("Class %f F1 score = %f\n", metrics.labels()[i], metrics.fMeasure(metrics.labels()[i]));
        }

        // Weighted stats
        System.out.println("Predicted Count = " + metrics.weightedPrecision());
        System.out.println("Ventilators = " + metrics.weightedRecall());
        System.out.println("Oxygen Cylinders = " + metrics.weightedFMeasure());
        System.out.println("Weighted false positive rate = " + metrics.weightedFalsePositiveRate());

        // Save and load model
        model.save(jsc.sc(), "target/tmp/LogisticRegressionModel");
        LogisticRegressionModel sameModel = LogisticRegressionModel.load(jsc.sc(), "target/tmp/LogisticRegressionModel");

        // Stop Spark context
        jsc.stop();
        spark.stop();
    }
}
*/
