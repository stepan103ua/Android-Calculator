package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.util.AndroidException;
import android.view.View;
import android.view.WindowInsetsAnimation;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Calculation calc = new Calculation();
    private Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,del,c,root;
    private TextView text,memory;
    private String line;
    private boolean rootOn = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initButtons();
    }

    private void initButtons()
    {
        text = findViewById(R.id.input);
        b0 = findViewById(R.id.but0);
        b1 = findViewById(R.id.but1);
        b2 = findViewById(R.id.but2);
        b4 = findViewById(R.id.but4);
        b5 = findViewById(R.id.but5);
        b6 = findViewById(R.id.but6);
        b7 = findViewById(R.id.but7);
        b8 = findViewById(R.id.but8);
        b9 = findViewById(R.id.but9);
        del = findViewById(R.id.butDel);
        c = findViewById(R.id.butC);
        root = findViewById(R.id.butSqrt);
        memory = findViewById(R.id.memory);
    }

    public void onButton7Click(View view) {
        text.setText(text.getText() + "7");
    }

    public void onButton8Click(View view) {
        text.setText(text.getText() + "8");
    }

    public void onButton9Click(View view) {
        text.setText(text.getText() + "9");
    }

    public void onButton4Click(View view) {
        text.setText(text.getText() + "4");
    }

    public void onButton5Click(View view) {
        text.setText(text.getText() + "5");
    }

    public void onButton6Click(View view) {
        text.setText(text.getText() + "6");
    }

    public void onButton1Click(View view) {
        text.setText(text.getText() + "1");
    }

    public void onButton2Click(View view) {
        text.setText(text.getText() + "2");
    }

    public void onButton3Click(View view) {
        text.setText(text.getText() + "3");
    }

    public void onButton0Click(View view) {
        text.setText(text.getText() + "0");
    }

    public void OnDelClick(View view) {
        if(text.getText().length() > 0)
            if(text.getText().charAt(text.getText().length()-1) != ' ')text.setText(text.getText().toString().substring(0,text.getText().length()-1));
            else text.setText(text.getText().toString().substring(0,text.getText().length()-3));
    }

    public void onCClick(View view) {
        if(text.getText().toString().equals(""))memory.setText("");
        else text.setText("");
        this.rootOn = false;
    }
    private boolean check()
    {
        if(text.getText().length()>0)
            if(calc.lastNumber(text.getText().toString()).charAt(0) == '-'
                    || calc.lastNumber(text.getText().toString()).charAt(0) == '.'
                    || ( calc.lastNumber(text.getText().toString()).charAt(0) >= '0'
                    && calc.lastNumber(text.getText().toString()).charAt(0) <= '9'))return true;
        return false;
    }
    private boolean checkContain(String ch)
    {
        if(!calc.lastNumber(text.getText().toString()).contains(ch)
                || calc.lastNumber(text.getText().toString()).charAt(0) == ch.charAt(0)
                && (calc.lastNumber(text.getText().toString()).charAt(0) >='0'
                &&calc.lastNumber(text.getText().toString()).charAt(0) <='9')
        ) return true;
        return false;
    }


    public void onComaClick(View view) {
        if(this.checkContain("."))
            text.setText(text.getText() + ".");
    }

    public void onDivClick(View view) {
        if(check())text.setText(text.getText() + " ÷ ");
    }

    public void onMulClick(View view) {
        if(check())text.setText(text.getText() + " ⨯ ");
    }

    public void onMinClick(View view) {
        if(check())text.setText(text.getText() + " - ");
    }

    public void onPlusClick(View view) {
        if(check())text.setText(text.getText() + " + ");
    }

    public void onEqClick(View view) {
        try {
            String mem = text.getText().toString();
            text.setText(calc.calculate(text.getText().toString()));
            memory.setText(mem + " = " + text.getText().toString());
            text.setText("");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void onRootClick(View view) {
        if(!this.rootOn)
        {
            this.rootOn = true;
            text.setText(text.getText() + "√ ( ");
        }
        else
        {
            this.rootOn = false;
            text.setText(text.getText() + " ) ");
        }
    }

    public void onMemClick(View view) {
        text.setText(text.getText() + calc.lastNumber(memory.getText().toString()));
    }

    public void onMinNumberClick(View view) {
        if(this.checkContain("-"))text.setText(text.getText() + "-");
    }
}