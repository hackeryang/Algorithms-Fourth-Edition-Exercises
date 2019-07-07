package Chapter1_4High;

import java.util.Arrays;

//Exercise 1.4.19
public class MatrixLocalMinimum {
    private static class IndexPath {
        int row;
        int column;
    }

    private IndexPath minimumIndexPathOfItem(int[][] matrix, IndexPath indexPath) {
        IndexPath resultItem = new IndexPath();
        resultItem.column = indexPath.column;
        resultItem.row = indexPath.row;

        int currentItem = matrix[indexPath.row][indexPath.column];
        int left = matrix[indexPath.row][(indexPath.column - 1) >= 0 ? (indexPath.column - 1) : indexPath.column];
        int right = matrix[indexPath.row][(indexPath.column + 1) <= matrix.length ? (indexPath.column + 1) : indexPath.column];
        int top = matrix[(indexPath.row - 1) >= 0 ? (indexPath.row - 1) : indexPath.row][indexPath.column];
        int bottom = matrix[(indexPath.row + 1) <= matrix.length ? (indexPath.row + 1) : indexPath.row][indexPath.column];

        int[] rounder = {left, right, top, bottom, currentItem};  //将大矩阵的其中的一个小九宫格内的部分拿出来作为一个子集进行极小值的查找，上下左右中
        Arrays.sort(rounder);
        if (rounder[0] == currentItem) {
            System.out.print("row: " + resultItem.row + "column: " + resultItem.column);
            return resultItem;
        } else if (rounder[0] == left) {
            resultItem.column = (indexPath.column - 1);
            minimumIndexPathOfItem(matrix, resultItem);
        } else if (rounder[0] == right) {
            resultItem.column = (indexPath.column + 1);
            minimumIndexPathOfItem(matrix, resultItem);
        } else if (rounder[0] == top) {
            resultItem.column = (indexPath.row - 1);
            minimumIndexPathOfItem(matrix, resultItem);
        } else if (rounder[0] == bottom) {
            resultItem.row = (indexPath.row + 1);
            minimumIndexPathOfItem(matrix, resultItem);
        } else {

        }
        return resultItem;
    }

    /*
     * 找出数组中的最小值的index
     * */
    private static int minimunOfArray(int[] x) {
        int indexOfMinimum = 0;
        int itemOfMinimum = Integer.MAX_VALUE;
        for (int i = 0; i < x.length; i++) {
            if (x[i] < itemOfMinimum) {
                itemOfMinimum = x[i];
                indexOfMinimum = i;
            }
        }
        return indexOfMinimum;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {11, 2, 3, 4, 102},
                {53, 6, 7, 18, 101},
                {12, 11, 10, 89, 100},
                {14, 1, 8, 5, 0},
                {114, 51, 58, 55, 99}
        };
        int middleRow = matrix.length / 2;
        int[] row = matrix[middleRow];
        int index = minimunOfArray(row);
        MatrixLocalMinimum localMinimum = new MatrixLocalMinimum();
        IndexPath indexPath = new IndexPath();
        indexPath.row = middleRow;
        indexPath.column = index;
        IndexPath resultIndexPath = localMinimum.minimumIndexPathOfItem(matrix, indexPath);
    }
}
