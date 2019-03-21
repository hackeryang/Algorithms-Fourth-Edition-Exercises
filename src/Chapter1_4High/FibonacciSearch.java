package Chapter1_4High;

import java.util.Arrays;

//exercise 1.4.22
public class FibonacciSearch {  //仅用加减实现的二分查找，只能使用加法和减法以及常数的额外内存空间
    private final int FI_SIZE=20;
    public int fbSearch(int[] array,int target) {
        if (array == null || array.length == 0) {
            return -1;
        } else {
            int length = array.length;
            //制造一个长度为20的斐波那契数列
            int[] fb = makeFbArray(FI_SIZE);
            int k = 0;
            //找出数组的长度在斐波数列（减1）中的位置，将决定如何拆分，即F(n-2)+F(n-1)=F(n)中，F(n-1)<length<F(n)，
            //这样就可以把数组array根据length拆分成前F(n-2)个元素与后F(n-1)个元素两部分，看目标数在前后哪一个部分里面，
            //然后递归拆分，模拟二分查找
            while (length > fb[k] - 1) {
                k++;
            }
            //构造一个长度为fb[k]-1的新数列
            int[] temp = Arrays.copyOf(array, fb[k] - 1);
            for (int i = length; i < temp.length; i++) {
                if (i >= length) {
                    temp[i] = array[length - 1];  //用array数组的最后一个元素将temp数组大于length部分的位置填充满，length大小之前的元素都来自array
                }
            }
            int low = 0;
            int high = array.length - 1;
            while (low <= high) {
                int middle = low + fb[k - 1] - 1;
                if (temp[middle] > target) {
                    high = middle - 1;
                    k = k - 1;  //如果目标元素在后面那一段，也就是F(n-1)那一段，原本长度是F(n)，k减1就变成 F(n-1)那一段，在此基础上再进行斐波那契数列拆分，变成F(n-2)和F(n-3)两部分
                } else if (temp[middle] < target) {
                    low = middle + 1;
                    k = k - 2; //如果目标元素在前面那一段，也就是F(n-2)那一段，原本长度是F(n)，k减2变成F(n-2)那一段，在此基础上在进行斐波那契数列拆分，变成F(n-3)和F(n-4)两部分
                } else {
                    if (middle <= high) {
                        //若相等则说明mid即为查找到的位置
                        return middle;
                    } else {
                            //middle的值已经大于high，进入扩展数组的填充部分，即最后一个数就是要找的数
                            return high;
                    }
                }

            }
            return -1;
        }
    }
        public static int[] makeFbArray(int length){
            int[] array=null;
            if(length>2){
                array=new int[length];
                array[0]=1;
                array[1]=1;
                for(int i=2;i<length;i++){
                    array[i]=array[i-1]+array[i-2];
                }
            }
            return array;
        }

    public static void main(String[] args){
        int[] array={1,5,15,22,25,31,39,42,47,49,59,68,88,88,88,88,88};
        FibonacciSearch search=new FibonacciSearch();
        System.out.println("result: "+search.fbSearch(array,31));
    }
}
