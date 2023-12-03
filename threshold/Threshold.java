package threshold;


public class Threshold {
    public int calculateOtsuThreshold(int[] histogram) {
        int totalPixels = 0;
        for (int i = 0; i < histogram.length; i++) {
            totalPixels = totalPixels + histogram[i];
        }

        // lowest class variance 
        double lowestClassVariance = Double.POSITIVE_INFINITY;
        int bestThresholdValue = 0;

        // variables for background
        int sumBackground, sumBackgroundForMean, totalPixelBackground;
        double weightBackground, meanBackground;
        double varianceBackground = 0;
        double sumBackgroundForVariance;

        // variables for foreground
        int sumForeground, sumForegroundForMean, totalPixelForeground;
        double weightForeground, meanForeground;
        double varianceForeground = 0;
        double sumForegroundForVariance;

        for (int t = 0; t < histogram.length; t++) {
            // initialising the values for background
            sumBackground = 0;
            sumBackgroundForMean = 0;
            weightBackground = 0;
            meanBackground = 0;
            totalPixelBackground = 0;
            sumBackgroundForVariance = 0;
            varianceBackground = 0;

            // initializing the values for foreground
            sumForeground = 0;
            sumForegroundForMean = 0;
            weightForeground = 0;
            meanForeground = 0;
            totalPixelForeground = 0;
            sumForegroundForVariance = 0;
            varianceForeground = 0;

            // BACKGROUND
            for (int i = 0; i < t; i++) {
                sumBackground += histogram[i];
                sumBackgroundForMean += i * histogram[i];  
            }

            // weightBackground 
            weightBackground = (sumBackground == 0) ? 0: (double) sumBackground / totalPixels;

            // meanBackground 
            totalPixelBackground = sumBackground;
            meanBackground = (totalPixelBackground == 0) ? 0 : (double)sumBackgroundForMean / totalPixelBackground;

            // calculating the sum of the background pixel for variance
            for (int i = 0; i < t; i++) {
                sumBackgroundForVariance += (Math.pow(i - meanBackground, 2)) * histogram[i];  
            }

            // varianceBackground
            varianceBackground = (sumBackgroundForVariance == 0) ? 0 : sumBackgroundForVariance / totalPixelBackground;

            // FOREGROUND
            for (int i = t; i < histogram.length; i++) {
                sumForeground += histogram[i];
                sumForegroundForMean += i * histogram[i];  
            }

            // weightForeground
            weightForeground = (double) sumForeground / totalPixels;

            // meanForeground 
            totalPixelForeground = sumForeground;
            meanForeground = (totalPixelForeground == 0) ? 0 : (double)sumForegroundForMean / totalPixelForeground;

            // calculating the sum of the sum of foreground pixel for variance
            for (int i = t; i < histogram.length; i++) {
                sumForegroundForVariance += (Math.pow(i - meanForeground, 2)) * histogram[i];  
            }

            // varianceForeground
            varianceForeground = (totalPixelForeground == 0) ? 0 : sumForegroundForVariance / totalPixelForeground;

            // Within class variance
            double withinClassVariance = weightBackground * varianceBackground + weightForeground * varianceForeground;
            withinClassVariance = Double.parseDouble(String.format("%.4f", withinClassVariance));
            
            if (withinClassVariance < lowestClassVariance) {
                lowestClassVariance = withinClassVariance;
                bestThresholdValue = t;
            }
        }

        return bestThresholdValue;
    }
} 

// // package threshold;

// // public class Threshold {
// //     public int calculateOtsuThreshold(int[] histogram) {
// //         int totalPixels = 0;
// //         for (int i = 0; i < histogram.length; i++) {
// //             totalPixels = totalPixels + histogram[i];
// //         }

// //         // lowest class variance 
// //         double lowestClassVariance = Double.POSITIVE_INFINITY;
// //         int bestThresholdValue = 0;

// //         // variables for background
// //         int sumBackground, sumBackgroundForMean, totalPixelBackground;
// //         double weightBackground, meanBackground;
// //         double varianceBackground = 0;
// //         double sumBackgroundForVariance;

// //         // variables for foreground
// //         int sumForeground, sumForegroundForMean, totalPixelForeground;
// //         double weightForeground, meanForeground;
// //         double varianceForeground = 0;
// //         double sumForegroundForVariance;

// //         for (int t = 0; t < histogram.length; t++) {
// //             // BACKGROUND
// //             sumBackground = 0;
// //             sumBackgroundForMean = 0;
// //             totalPixelBackground = 0;
// //             sumBackgroundForVariance = 0;
// //             varianceBackground = 0;

// //             for (int i = 0; i < t; i++) {
// //                 sumBackground += histogram[i];
// //                 sumBackgroundForMean += i * histogram[i];
// //             }

// //             // Avoid division by zero
// //             if (sumBackground != 0) {
// //                 weightBackground = (double) sumBackground / totalPixels;
// //                 totalPixelBackground = sumBackground;
// //                 meanBackground = (double) sumBackgroundForMean / totalPixelBackground;

// //                 // calculating the sum of the background pixel for variance
// //                 for (int i = 0; i < t; i++) {
// //                     sumBackgroundForVariance += (Math.pow(i - meanBackground, 2)) * histogram[i];
// //                 }

// //                 varianceBackground = sumBackgroundForVariance / totalPixelBackground;
// //             } else {
// //                 weightBackground = 0;
// //             }

// //             // FOREGROUND
// //             sumForeground = 0;
// //             sumForegroundForMean = 0;
// //             totalPixelForeground = 0;
// //             sumForegroundForVariance = 0;
// //             varianceForeground = 0;

// //             for (int i = t; i < histogram.length; i++) {
// //                 sumForeground += histogram[i];
// //                 sumForegroundForMean += i * histogram[i];
// //             }

// //             // Avoid division by zero
// //             if (sumForeground != 0) {
// //                 weightForeground = (double) sumForeground / totalPixels;
// //                 totalPixelForeground = sumForeground;
// //                 meanForeground = (double) sumForegroundForMean / totalPixelForeground;

// //                 // calculating the sum of the foreground pixel for variance
// //                 for (int i = t; i < histogram.length; i++) {
// //                     sumForegroundForVariance += (Math.pow(i - meanForeground, 2)) * histogram[i];
// //                 }

// //                 varianceForeground = sumForegroundForVariance / totalPixelForeground;
// //             } else {
// //                 weightForeground = 0;
// //             }

// //             // Within class variance
// //             double withinClassVariance = weightBackground * varianceBackground + weightForeground * varianceForeground;
// //             withinClassVariance = Double.parseDouble(String.format("%.4f", withinClassVariance));

// //             if (withinClassVariance < lowestClassVariance) {
// //                 lowestClassVariance = withinClassVariance;
// //                 bestThresholdValue = t;
// //             }
// //         }

// //         return bestThresholdValue;
// //     }
// // }

// package threshold;

// public class Threshold {
//     public int calculateOtsuThreshold(int[] histogram) {
//         int totalPixels = 0;
//         for (int i = 0; i < histogram.length; i++) {
//             totalPixels += histogram[i];
//         }

//         double sum = 0;
//         for (int i = 0; i < histogram.length; i++) {
//             sum += i * histogram[i];
//         }
//         double sumB = 0;
//         int wB = 0;
//         int wF = 0;
//         double maxVariance = 0;
//         int threshold = 0;

//         for (int i = 0; i < histogram.length; i++) {
//             wB += histogram[i];
//             if (wB == 0) {
//                 continue;
//             }
//             wF = totalPixels - wB;

//             if (wF == 0) {
//                 break;
//             }

//             sumB += i * histogram[i];
//             double meanB = sumB / wB;
//             double meanF = (sum - sumB) / wF;
//             double betweenClassVariance = wB * wF * Math.pow((meanB - meanF), 2);
//             if (betweenClassVariance > maxVariance) {
//                 maxVariance = betweenClassVariance;
//                 threshold = i;
//             }
//         }
//         return threshold;
//     }
// }
