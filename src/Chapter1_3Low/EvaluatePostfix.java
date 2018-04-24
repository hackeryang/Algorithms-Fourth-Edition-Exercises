package Chapter1_3Low;

public class EvaluatePostfix{
    private static String evaluatePostfix(String expression){
        Stack<Double> vals=new Stack<Double>();
        for(int j=0;j<expression.length();j++){
            char charAtIndex=expression.charAt(j);
            String s=String.valueOf(charAtIndex);
            if(s.equals("+")){
                Double valDouble1=vals.pop();
                Double valDouble2=vals.pop();
                s=valDouble1+valDouble2+"";
                vals.push(Double.parseDouble(s));
            }else if(s.equals("-")){
                Double valDouble1=vals.pop();
                Double valDouble2=vals.pop();
                s=valDouble1-valDouble2+"";
                vals.push(Double.parseDouble(s));
            }else if(s.equals("*")){
                Double valDouble1=vals.pop();
                Double valDouble2=vals.pop();
                s=valDouble1*valDouble2+"";
                vals.push(Double.parseDouble(s));
            }else if(s.equals("/")){
                Double valsDouble1=vals.pop();
                Double valsDouble2=vals.pop();
                s=valsDouble1/valsDouble2+"";
                vals.push(Double.parseDouble(s));
            }
        }
        return vals.pop().toString();
    }
}
