package com.demo.percentcalculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText etPercent, etNumber;
    String strPercent, strNumber, resultMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCompute = (Button) findViewById(R.id.btn_compute);

        btnCompute.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(this, "Computing....", Toast.LENGTH_SHORT).show();
        ComputeResult();
    }

    public void ComputeResult(){
        etPercent = (EditText) findViewById(R.id.et_percent);
        etNumber = (EditText) findViewById(R.id.et_number);
        if(etPercent.getText().toString().isEmpty() || etNumber.getText().toString().isEmpty()){
            strPercent = "0";
            strNumber = "0";
        }else{
            strPercent = etPercent.getText().toString();
            strNumber = etNumber.getText().toString();
        }

        double rawPercent = Double.parseDouble(strPercent);
        double rawNumber = Double.parseDouble(strNumber);

        rawPercent = ToDecimal(rawPercent);

        double result = rawPercent * rawNumber;
        // Casted result to int type, remove to show decimal value
        resultMessage = strPercent +"% of " + strNumber + " is " + (int) result;

        // Create Bundle instance, this will allow transfer of data from Activity to DialogFragment
        Bundle args = new Bundle();
        args.putString("result", resultMessage);

        // Display result in an Activity
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        intent.putExtras(args);
        startActivity(intent);

        clearEditText();
    }
    public void clearEditText(){
        etNumber.getText().clear();
        etPercent.getText().clear();
        etPercent.requestFocus();
    }
    public double ToDecimal(double nbr){
        nbr = nbr/100;
        return nbr;
    }
}