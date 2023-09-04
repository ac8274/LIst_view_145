package com.example.list_view_145;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity2 extends AppCompatActivity implements AdapterView.OnItemClickListener {
    Intent gi;
    Button returner;
    TextView A1;
    TextView AN;
    TextView qd;
    TextView n;
    TextView Sn;
    ListView lv;
    Double[] B = new Double[20];
    double a1;
    double qd12;
    boolean ans;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        returner = findViewById(R.id.button2);
        A1 = findViewById(R.id.txt3);
        qd = findViewById(R.id.txt4);
        n = findViewById(R.id.txt5);
        Sn = findViewById(R.id.txt6);
        lv = findViewById(R.id.An);
        AN = findViewById(R.id.txt);
        gi = getIntent();
        B = list_veiw_filler();
        ArrayAdapter<Double> adp = new ArrayAdapter<Double>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,B);
        lv.setAdapter(adp);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lv.setOnItemClickListener(this);
        text_setter(qd12,a1);
    }


    public Double[] list_veiw_filler()
    {
        Double[] B = new Double[20];
        a1 = 0;
        qd12 = 0;
        ans = false;
        //int i = 2;
        ans = gi.getBooleanExtra("Q or D",ans);
        a1 = gi.getDoubleExtra("A1",a1);
        qd12 = gi.getDoubleExtra("QD",qd12);
        for(int i=2;i<22;i++)
        {
            B[i-2] = ANQN(i,a1,qd12,ans);
        }
        return B;
    }


    public void text_setter(Double q,Double a1)
    {
        String st = " a1 = "+Double.toString(a1);
        String st2 = " q/d = "+Double.toString(q);
        A1.setText(st);
        qd.setText(st2);
        if(ans && qd12 == 0)
        {
            String st3 = " S(n) = "+Double.toString(a1);
            Sn.setText(st3);
        }
    }
    public double ANQN(int position,double a1,double qd,boolean answear)
    {
        if(answear)
        {
            return Qn_calculator(position,a1,qd);
        }
        else
        {
            return An_calculator(position,a1,qd);
        }
    }


    public double Qn_calculator(int position,double a1,double q){
        return (a1*(Math.pow(q,((double)position)-1.0)));
    }

    public double An_calculator(int position,double a1,double d){
        return (a1+((((double) position) - 1.0)*d));
    }


    public void return_main(View view) {
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String st = " n = "+Integer.toString(position+2);
        String st2 = " A(n) = "+Double.toString(B[position]);
        n.setText(st);
        AN.setText(st2);
        Sn_writer(position+2);
    }

    public void Sn_writer(int position)
    {
        String st = " S(n) = "+Double.toString(Sn_calculator_NORMAL(position,B[position]));
        String st2 = " S(n) = "+Double.toString(Sn_calculator_Engineer(position));
        if(ans && qd12 == 1)
        {
            Sn.setText(st);
        }
        else if(ans){
            Sn.setText(st2);
        }
        else
        {
            Sn.setText(st);
        }
    }


    public Double Sn_calculator_Engineer(int position)
    {
        return((a1*(Math.pow(qd12,position) -1.0))/(qd12-1));
    }

    public Double Sn_calculator_NORMAL(int position, Double an)
    {
        return(position*(an+a1));
    }



}