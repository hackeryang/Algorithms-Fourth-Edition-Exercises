package Chapter1_3Low;

//exercise 1.3.10
public class InfixToPostfix {
    public static void main(String[] args){
        String expression="(1+((2+3)*(4*5)))";
        Stack<String> ops=new Stack<String>();
        double result=0;
        for(int j=0;j<expression.length();j++){
            char charAtIndex=expression.charAt(j);
            String s=String.valueOf(charAtIndex);
            if(s.equals("(")){

            }else if(s.equals("+")){
                ops.push(s);
            }else if(s.equals("-")){
                ops.push(s);
            }else if(s.equals("*")){
                ops.push(s);
            }else if(s.equals("/")){
                ops.push(s);
            }else if(s.equals(")")){
                String op=ops.pop();
                System.out.print(op);
            }else{
                System.out.print(s);
            }
        }
    }
}
