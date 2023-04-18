package com.example.c1;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.sql.Array;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTv, solutionTv;

    String history[] = new String[10];

    MaterialButton btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9,btn_AC, btn_Percent,btn_Delete, btn_Multiply, btn_Devide, btn_Minus,btn_Equal,btn_Plus,btn_Dot,btn_000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);

        assignId(btn_0, R.id.btn0);
        assignId(btn_1, R.id.btn1);
        assignId(btn_2, R.id.btn2);
        assignId(btn_3, R.id.btn3);
        assignId(btn_4, R.id.btn4);
        assignId(btn_5, R.id.btn5);
        assignId(btn_6, R.id.btn6);
        assignId(btn_7, R.id.btn7);
        assignId(btn_8, R.id.btn8);
        assignId(btn_9, R.id.btn9);
        assignId(btn_AC, R.id.btnAC);
        assignId(btn_Percent, R.id.btnPercent);
        assignId(btn_Delete, R.id.btnDelete);
        assignId(btn_Multiply, R.id.btnMultiply);
        assignId(btn_Devide, R.id.btnDevide);
        assignId(btn_Minus, R.id.btnMinus);
        assignId(btn_Equal, R.id.btnEqual);
        assignId(btn_Dot, R.id.btnDot);
        assignId(btn_000, R.id.btn000);
        assignId(btn_Plus, R.id.btnPlus);

    }

    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();

        if(buttonText.equals("AC")) {
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }

        if (buttonText.equals("=")) {
            solutionTv.setText(resultTv.getText());
            for(int index = history.length - 1; index > 0 ; index--){
                if(index>0){
                    history[index] = history[index-1];
                }
            }
            history[0] = resultTv.getText().toString();
            for(int i = 0 ; i < history.length - 1 ; i++){
                System.out.println(history[i]);
            }
            resultTv.setText("");

            return;
        }
        if(buttonText.equals("Del")){
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length()-1);
        }else {
            dataToCalculate = dataToCalculate+buttonText;
        }
        solutionTv.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);
        if(!finalResult.equals("Err")){
            resultTv.setText(finalResult);
        }
    }

    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        }
        catch (Exception e){
            return "Err";
        }
    }
}