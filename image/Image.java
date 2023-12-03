package image;

public class Image {
    // variable 
    private int[][] pixels;

    // Manager function
    public Image(int[][] pixels1) {
        pixels = pixels1;
    }

    // Access function 
    // get and set for arrays
    public int[][] getPixels() {
        return pixels;
    }

    public void setPixels(int[][] pixels1) {
        pixels = pixels1;
    }

    // Implementor function
    public int[] histogram() {
        int[] histogram = new int[256];
        
        // looping in the 2d array and making a histogram out of it
        for (int i = 0; i < pixels.length; i++) {
            int[] array = pixels[i];
            for (int j = 0; j < array.length; j++) {
                int value = array[j];
                histogram[value]++;
            }
        }

        return histogram;
    }

    public int[] histogram1D() {
        int[] histogramValues = {8, 7, 2, 6, 9, 4};

        int[] histogram = new int[histogramValues.length];

        // Populate the histogram array
        for (int i = 0; i < histogramValues.length; i++) {
            histogram[i] = histogramValues[i];
        }

        return histogram;
    }
    

    // prints the histogram
    public void printHistogram() {
        int[] hist1 = this.histogram();

        for (int i = 0; i < hist1.length; i++) {
            System.out.println(i + ": " + hist1[i]);
        }
    }
}