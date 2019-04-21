package Chapter1_4High;

//Exercise 1.4.20
public class BitonicSearch {
    public static int localMaximum(int[] x){
        if(x==null || x.length==0){
            return -1;
        }
        if(x.length==1 || x[0]>x[1]){
            return 0;
        }
        if(x[x.length-1]>x[x.length-2]){
            return x.length-1;
        }
        int mid=0;
        int left=1;
        int right=x.length-2;
        while(left<=right){  //不是小于< ，否则在数组数量为奇数的时候，例如{1，2，6，5，4}时，返回的是right，也就是5的index: 3
            mid=(left+right)/2;
            if(x[mid-1]<x[mid]){
                left=mid+1;
            }else if(x[mid]>x[mid+1]){
                right=mid-1;
            }else{
                return mid;
            }
        }
        return right;
    }
    public static int bitonicSearch(int[] x,int t) throws Exception{
        if(x==null || x.length<1) return -1;
        if(x.length<4){
            throw new Exception("该数组不是双调数组");
        }
        //获取最大值
        int maximumIndex=localMaximum(x);

        if(x[maximumIndex]>t){
            //往左边查找
            int left=0;
            int right=maximumIndex;
            int mid=0;
            while(left<=right){
                mid=(left+right)/2;
                if(x[mid]<t)
                    left=mid+1;
                else if(x[mid]>t)
                    right=mid-1;
                else return mid;
            }
            //往右边查找
            int left2=maximumIndex;
            int right2=x.length-1;
            int mid2=0;
            while(left2<=right2){
                mid2=(left2+right2)/2;
                if(x[mid2]<t){
                    right2=mid2-1;
                }else if(x[mid2]>t){
                    left2=mid2+1;
                }else{
                    return mid2;
                }
            }
            return -1;
        }else if(x[maximumIndex]<t){
            return -1;
        }else{
            return maximumIndex;
        }
    }
    public static void main(String[] args){
        int[] bitonicArray={1,2,6,5,4};
        try{
            int resultIndex=bitonicSearch(bitonicArray,4);
            if(resultIndex==-1){
                System.out.print("不包含给定整数");
            }else{
                System.out.print("包含给定整数，index值为 "+resultIndex);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
