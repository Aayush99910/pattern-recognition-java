package conversion;

public class Binary {
    // Data members
    private int threshold;

    // Constructor function
    public Binary(int threshold1) {
        threshold = threshold1;
    }

    // Access function
    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold1) {
        threshold = threshold1;
    }

    // implementor function 
    public int[][] convertToBinary(int[][] imageArray) {
        for(int i = 0; i < imageArray.length; i++) {
            for (int j = 0; j < imageArray[i].length; j++) {
                int input = imageArray[i][j];
                if (input < threshold) {
                    imageArray[i][j] = 0;
                } else {
                    imageArray[i][j] = 1;
                }
            }
        }

        return imageArray;
    }

    public void printFinalImage(int[][] cleanImageArray) {
        for (int i = 0; i < cleanImageArray.length; i++) {
            for (int j = 0; j < cleanImageArray[i].length; j++) {
                String formattedValue = String.format("%4d", cleanImageArray[i][j]);
                System.out.print(formattedValue + " ");
            }
            System.out.println(); // Move to the next line after each row
        }
    }
}
    