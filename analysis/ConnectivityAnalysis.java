package analysis;

public class ConnectivityAnalysis {
    private int[][] binaryImage;
    private int[][] labeledImage;
    private int label;

    public ConnectivityAnalysis(int[][] binaryImage) {
        this.binaryImage = binaryImage;
        this.labeledImage = new int[binaryImage.length][binaryImage[0].length];
        this.label = 1; // Starting label for connected components
    }

    public int[][] labelConnectedComponents() {
        for (int i = 0; i < binaryImage.length; i++) {
            for (int j = 0; j < binaryImage[0].length; j++) {
                if (binaryImage[i][j] == 1 && labeledImage[i][j] == 0) {
                    // Found a new connected component, perform depth-first search to label it
                    labelConnectedComponent(i, j);
                    label++;
                }
            }
        }

        return labeledImage;
    }

    private void labelConnectedComponent(int row, int col) {
        if (row < 0 || row >= binaryImage.length || col < 0 || col >= binaryImage[0].length || binaryImage[row][col] == 0 || labeledImage[row][col] > 0) {
            return;
        }

        labeledImage[row][col] = label;

        // Explore neighbors
        labelConnectedComponent(row - 1, col);
        labelConnectedComponent(row + 1, col);
        labelConnectedComponent(row, col - 1);
        labelConnectedComponent(row, col + 1);
    }

    public void printLabeledImage() {
        for (int i = 0; i < labeledImage.length; i++) {
            for (int j = 0; j < labeledImage[0].length; j++) {
                System.out.print(labeledImage[i][j] + "   ");
            }
            System.out.println();
        }
    }
}
