package datastructure.sparsearray;

import java.util.Arrays;

/**
 * @ClassName SparseArray
 * @Description 稀疏数组和二维数组的转换
 * @Author lxyqaq @Email A00279565@student.ait.ie
 * @Date 2021/2/8 17:19
 * @Version 1.0
 */
public class SparseArray {

    public static void main(String[] args) {

        // 创建一个原始的二维数组 11*11
        int chessArray[][] = new int[11][11];
        // 0:表示没有棋子 1:表示白子 2:表示黑子
        chessArray[1][2] = 1;
        chessArray[2][3] = 2;

        //输出原始的二维数组
        System.out.println("原始的二维数组:");
        for (int[] row : chessArray) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            //换行
            System.out.println();
        }

        System.out.println();

        //使用for循环遍历
        /*for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < chessArray[i].length; j++) {
                System.out.printf("%d\t", chessArray[i][j]);
            }
            System.out.println();
        }*/

        //再将二维数组转换成稀疏数组
        //1.先遍历二维数组得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < chessArray[i].length; j++) {
                if (chessArray[i][j] != 0) sum++;
            }
        }

        //2.创建稀疏数组
        int sparseArray[][] = new int[sum + 1][3];
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;

        //3.遍历二维数组，将非0的值放进稀疏数组中
        //count 用于记录是第几个非0数据
        int count = 0;
        for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < chessArray[i].length; j++) {
                if (chessArray[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArray[i][j];
                }
            }
        }

        System.out.println("输出稀疏数组:");
        for (int i = 0; i < sparseArray.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArray[i][0], sparseArray[i][1], sparseArray[i][2]);
        }

        //将稀疏数组 --> 恢复成 原始的二维数组
        /*
         *  1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组，比如上面的  chessArr2 = int [11][11]
         *	2. 在读取稀疏数组后几行的数据，并赋给 原始的二维数组 即可.
         */


    }

}
