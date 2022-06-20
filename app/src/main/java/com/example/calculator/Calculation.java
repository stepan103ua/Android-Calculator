package com.example.calculator;

import java.util.*;
public class Calculation {

    static double mul(double a,double b)
    {
        return a*b;
    }
    static double div(double a,double b)
    {
        return a/b;
    }
    static double add(double a,double b)
    {
        return a+b;
    }
    static double min(double a,double b)
    {
        return a-b;
    }

    public String lastNumber(String line)
    {
        String [] numbers = line.split(" ");
        if(numbers.length > 0)return numbers[numbers.length - 1];
        else return "null";
    }

    public String calculate(String line)
    {
        String[] numbs = line.split(" ");
        Vector<String> numbers = new Vector<>();
        for(int i=0;i<numbs.length;i++)
        {
            if(numbs[i].charAt(0) == '.')numbs[i] = '0'+numbs[i];
            numbers.add(numbs[i]);
        }

        while(numbers.size()!=1)
        {
            boolean cont = false;

            for(int i=0;i<numbers.size();i++)
            {
                if(numbers.get(i).equals("√"))
                {
                    int j = i+2;
                    String sline="";
                    while(!numbers.get(j).equals(")"))
                    {
                        sline+=numbers.get(j) + " ";
                        j++;
                    }
                    if(sline.contains("√"))sline+=") ";
                    numbers.set(i,Double.toString(Math.sqrt( Double.parseDouble(calculate(sline)))));
                    for(int k = i;k<j;k++)
                    {
                        numbers.remove(i+1);
                    }
                    cont = true;
                    break;
                }
            }
            if(numbers.contains(")") && !numbers.contains("("))
            {
                for(int i=0;i<numbers.size();i++)
                {
                    if(numbers.get(i).equals(")"))
                    {
                        numbers.remove(i);
                        i--;
                    }
                }
            }
            if(cont)continue;
            for(int i=0;i<numbers.size();i++)
            {
                if(numbers.get(i).equals("⨯"))
                {
                    numbers.set(i-1,Double.toString(mul(Double.parseDouble(numbers.get(i-1)),(Double.parseDouble(numbers.get(i+1))))));
                    numbers.remove(i);
                    numbers.remove(i);
                    cont = true;
                    break;
                }
                else if(numbers.get(i).equals("÷"))
                {
                    numbers.set(i-1,Double.toString(div(Double.parseDouble(numbers.get(i-1)),(Double.parseDouble(numbers.get(i+1))))));
                    numbers.remove(i);
                    numbers.remove(i);
                    cont = true;
                    break;
                }
            }
            if(cont)continue;
            for(int i=0;i<numbers.size();i++)
            {
                if(numbers.get(i).equals("+"))
                {
                    numbers.set(i-1,Double.toString(add(Double.parseDouble(numbers.get(i-1)),(Double.parseDouble(numbers.get(i+1))))));
                    numbers.remove(i);
                    numbers.remove(i);
                    cont = true;
                    break;
                }
                else if(numbers.get(i).equals("-"))
                {
                    numbers.set(i-1,Double.toString(min(Double.parseDouble(numbers.get(i-1)),(Double.parseDouble(numbers.get(i+1))))));
                    numbers.remove(i);
                    numbers.remove(i);
                    cont = true;
                    break;
                }
            }
            if(cont)continue;
        }
        return String.format("%.2f", Double.parseDouble(numbers.firstElement())).replace(',', '.');
    }
}