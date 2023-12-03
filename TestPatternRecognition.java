import image.*;
import threshold.*;
import analysis.*;
import conversion.*;
import recognition.*;

public class TestPatternRecognition {
    public static void main(String args[]) {
        int[][] pixelArray2 = {
            {1, 3, 5, 7, 9, 3, 4, 4, 5, 6},
            {1, 20, 25, 24, 3, 5, 6, 4, 2, 4},
            {1, 22, 35, 24, 3, 5, 6, 4, 5, 7},
            {1, 20, 28, 34, 2, 5, 6, 4, 8, 9},
            {1, 3, 5, 7, 9, 3, 4, 4, 5, 6},
            {1, 3, 5, 7, 9, 3, 30, 4, 5, 6},
            {1, 3, 5, 7, 9, 36, 34, 33, 5, 6},
            {1, 3, 5, 7, 9, 32, 39, 34, 5, 6},
            {1, 3, 5, 7, 9, 3, 38, 4, 5, 6},
            {1, 3, 5, 7, 9, 3, 4, 4, 5, 6}
        };

        // making an image class and passing the pixel array in it
        Image image = new Image(pixelArray2); 

        // creating a histogram
        int[] histogram = new int[256];

        // storing the histogram in variable histogram
        histogram = image.histogram();
        
        // making a thresholdClass
        Threshold thresholdClass = new Threshold();

        // getting the threshold value using otsu's method
        int threshold = thresholdClass.calculateOtsuThreshold(histogram);

        Binary binaryClass = new Binary(threshold);
        int[][] finalImageArray = binaryClass.convertToBinary(pixelArray2);
    
        ConnectivityAnalysis connectivity = new ConnectivityAnalysis(finalImageArray);
        int[][] labeledImage = connectivity.labelConnectedComponents();
        connectivity.printLabeledImage();

        ObjectRecognition objectRecognition = new ObjectRecognition(labeledImage);
        objectRecognition.recognizeObjects();
    }
}