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
        Double dollarAmount = Double.parseDouble(editText.getText().toString());
        TypedValue typedValue = new TypedValue();

        String spinnerString = getSpinner(R.id.toSpinner);
        Resources resources = getResources();
        float value = typedValue.getFloat();
        resources.getValue(resources.getIdentifier(spinnerString, "dimen", MainActivity.this.getPackageName()), typedValue, true);



        Double euroConvert = dollarAmount*value;
        TextView textView = (TextView) findViewById(R.id.editText);
        textView.setText(euroConvert.toString() + " Euro");
        Log.i("Spinner from ", getSpinner(R.id.toSpinner));
    }

    public String getSpinner(int spinner){
        Spinner toSpinner = (Spinner) findViewById(spinner);
        String text = toSpinner.getSelectedItem().toString();
        return text;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
