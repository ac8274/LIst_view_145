package com.example.list_view_145;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Button button;
    EditText A1;
    EditText QB;
    RadioButton rb2;
    RadioButton rb1;
    RadioGroup rd;
    TextView texxt2;
    TextView texxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        texxt = findViewById(R.id.textView);
        texxt2 = findViewById(R.id.textView2);
        rd = findViewById(R.id.rd);
        rb1 = findViewById(R.id.radioButton);
        rb2 = findViewById(R.id.radioButton2);
        QB = findViewById(R.id.el);
        A1 = findViewById(R.id.el2);
        button = findViewById(R.id.button);
    }

    public void next_activity(View view) {
        double QD_num = 0.0;
        double A1_num = 0.0;
        String QD_str = String.valueOf(QB.getText());
        String A1_str = String.valueOf(A1.getText());
        if( check_valid_value(QD_str) && check_valid_value(A1_str)){
            QD_num = strToDouble(QD_str);
            A1_num = strToDouble(A1_str);
            Intent si = new Intent(this, MainActivity2.class);
            si.putExtra("QD",QD_num);
            si.putExtra("A1",A1_num);
            si.putExtra("Q or D",Engineering_or_Not(rb2));
            startActivity(si);
        }
        else {
            Toast.makeText(this,"Your 'q/d' or 'a1' is not a number",Toast.LENGTH_LONG).show();
        }
    }

    public static boolean Engineering_or_Not(RadioButton rb1)
    {
        return (rb1.isChecked());
    }

    public static boolean check_valid_value(String num){
        /* function gets a string and checks if it could be turned into a double.*/
        try{
            double f = strToDouble(num);
            return true;
        }
        catch (Exception e) // if exception rises then string can't be convereted.
        {
            return false;
        }
    }

    public static double strToDouble(String str) {
        return Double.parseDouble(str);
    }
}