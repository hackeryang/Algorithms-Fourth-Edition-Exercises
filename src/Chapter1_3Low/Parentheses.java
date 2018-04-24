package Chapter1_3Low;

public class Parentheses {
    public static void main(String[] args){
        String stream="[(])";
        boolean isPaired=true;
        Stack<String> ops=new Stack<String>();
        for(int i=0;i<stream.length();i++){
            char item=stream.charAt(i);
            String s=String.valueOf(item);
            if(s.equals("[")){
                ops.push(s);
            }else if(s.equals("(")){
                ops.push(s);
            }else if(s.equals("{")){
                ops.push(s);
            }else if(s.equals("]")){
                String poppedString=ops.pop();
                if(!poppedString.equals("[")){
                    isPaired=false;
                    break;
                }
            }else if(s.equals("}")){
                String poppedString=ops.pop();
                if(!poppedString.equals("{")){
                    isPaired=false;
                    break;
                }
            }
            else if(s.equals(")")){
                String poppedString=ops.pop();
                if(!poppedString.equals(")")){
                    isPaired=false;
                    break;
                }
            }
        }
        //最后加上该语句，确保栈为空
        if(!ops.isEmpty()){
            isPaired=false;
        }
        System.out.println(isPaired);
    }
}
