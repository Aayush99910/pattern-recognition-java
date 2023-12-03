package recognition;

public class ObjectRecognition {
    private int[][] labeledImage;

    public ObjectRecognition(int[][] labeledImage) {
        this.labeledImage = labeledImage;
    }

    public void recognizeObjects() {
        for (int label = 1; label <= getMaxLabel(); label++) {
            double circularity = calculateCircularity(label);
            System.out.println("Shape labeled " + label + ": " + classifyShape(circularity));
        }
    }

    private String classifyShape(double circularity) {
        // Check if the circularity is closer to a circle or square
        double circleCircularity = calculateCircularityForReference(Math.PI / 4);
        double squareCircularity = calculateCircularityForReference(1);

        double circleDifference = Math.abs(circularity - circleCircularity);
        double squareDifference = Math.abs(circularity - squareCircularity);

        if (circleDifference < squareDifference) {
            return "Circle";
        } else {
            return "Square";
        }
    }

    private double calculateCircularityForReference(double referenceCircularity) {
        // Use appropriate reference values for circularity calculation
        // For example, you may use properties of a perfect circle and square
        int referenceArea = 100; // Adjust as needed
        int referencePerimeter = 40; // Adjust as needed

        // R = (4 * PI * AREA) / (PERIMETER * PERIMETER)
        return (4 * Math.PI * referenceArea) / (referencePerimeter * referencePerimeter);
    }

    private double calculateCircularity(int label) {
        int area = calculateArea(label);
        int perimeter = calculatePerimeter(label);

        // R = (4 * PI * AREA) / (PERIMETER * PERIMETER)
        return (4 * Math.PI * area) / (perimeter * perimeter);
    }

    private int calculateArea(int label) {
        int count = 0;
        for (int i = 0; i < labeledImage.length; i++) {
            for (int j = 0; j < labeledImage[0].length; j++) {
                if (labeledImage[i][j] == label) {
                    count++;
                }
            }
        }
        return count;
    }

    private int calculatePerimeter(int label) {
        int perimeter = 0;
        for (int i = 0; i < labeledImage.length; i++) {
            for (int j = 0; j < labeledImage[0].length; j++) {
                if (labeledImage[i][j] == label) {
                    perimeter += countNeighbor(label, i, j);
                }
            }
        }
        return perimeter;
    }

    private int countNeighbor(int label, int row, int col) {
        int count = 0;
        int[][] neighbors = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int[] neighbor : neighbors) {
            int newRow = row + neighbor[0];
            int newCol = col + neighbor[1];

            if (newRow >= 0 && newRow < labeledImage.length && newCol >= 0 && newCol < labeledImage[0].length) {
                if (labeledImage[newRow][newCol] != label) {
                    count++;
                }
            } else {
                count++; // Consider pixels on the boundary as part of the perimeter
            }
        }

        return count;
    }

    private int getMaxLabel() {
        int maxLabel = 0;
        for (int i = 0; i < labeledImage.length; i++) {
            for (int j = 0; j < labeledImage[0].length; j++) {
                maxLabel = Math.max(maxLabel, labeledImage[i][j]);
            }
        }
        return maxLabel;
    }
}
