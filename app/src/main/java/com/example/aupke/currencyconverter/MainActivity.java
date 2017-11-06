package com.example.aupke.currencyconverter;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public void convert(View view) throws NoSuchFieldException, IllegalAccessException {
        EditText editText = (EditText) findViewById(R.id.editText2);
        Double readAmount = Double.parseDouble(editText.getText().toString());
        Double spinnerCurrencyFrom = getSpinnerPosition(R.id.spinner);
        Double spinnerCurrencyTo = getSpinnerPosition(R.id.toSpinner);
        //resources.getValue(resources.getIdentifier(spinnerString, "dimen", MainActivity.this.getPackageName()), typedValue, true);
        //resources.getValue(spin);


        Double euroConvert = readAmount*spinnerCurrencyTo/spinnerCurrencyFrom;
        TextView textView = (TextView) findViewById(R.id.toTextView);
        textView.setText(euroConvert.toString());
    }

    public Spinner getSpinner(int spinner){
        Spinner toSpinner = (Spinner) findViewById(spinner);
        return toSpinner;
    }

    public Double getSpinnerPosition(int spinner){
        Spinner spinner1 = (Spinner) findViewById(spinner);
        int spinnerPosition = spinner1.getSelectedItemPosition();
        String[] currency_values = getResources().getStringArray(R.array.currencyInteger);
        double currency = Double.valueOf(currency_values[spinnerPosition]);
        return currency;
    }

    public void swap(View view) throws NoSuchFieldException, IllegalAccessException {

        Spinner spinner2 = (Spinner) findViewById(R.id.toSpinner);
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner);
        int spinner1temporary = spinner1.getSelectedItemPosition();
        spinner1.setSelection(spinner2.getSelectedItemPosition());
        spinner2.setSelection(spinner1temporary);
        convert(view);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
