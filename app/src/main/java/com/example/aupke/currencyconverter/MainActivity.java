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

        String spinnerString = getSpinner(R.id.toSpinner);
        String spinnerString2 = getSpinner(R.id.spinner);
        Double spinnerCurrencyFrom = getSpinnerPosition(R.id.spinner);
        Double spinnerCurrencyTo = getSpinnerPosition(R.id.toSpinner);
        //resources.getValue(resources.getIdentifier(spinnerString, "dimen", MainActivity.this.getPackageName()), typedValue, true);
        //resources.getValue(spin);


        Double euroConvert = readAmount*spinnerCurrencyTo/spinnerCurrencyFrom;
        TextView textView = (TextView) findViewById(R.id.editText);
        textView.setText(euroConvert.toString());
        Log.i("Spinner from ", spinnerString);
        Log.i("Spinner from ", spinnerString2);
        Log.i("Second spinner value: ", spinnerCurrencyFrom.toString());
    }

    public String getSpinner(int spinner){
        Spinner toSpinner = (Spinner) findViewById(spinner);
        String text = toSpinner.getSelectedItem().toString();
        return text;
    }

    public Double getSpinnerPosition(int spinner){
        Spinner spinner1 = (Spinner) findViewById(spinner);
        int spinnerPosition = spinner1.getSelectedItemPosition();
        String[] currency_values = getResources().getStringArray(R.array.currencyInteger);
        double sizeD = Double.valueOf(currency_values[spinnerPosition]);
        return sizeD;
    }

    public void swap(View view){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
