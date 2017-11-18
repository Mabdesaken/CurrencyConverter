package com.example.aupke.currencyconverter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> history;
    private String TAG = "MainActivity";
    private int createCounter;
    private int resumeCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.containsKey("savedHistory") && savedInstanceState.containsKey("savedCreateCounter")) {
            // Restore history
            createCounter = savedInstanceState.getInt("savedCreateCounter");
            history = savedInstanceState.getStringArrayList("savedHistory");
        } else {
            // Clean slate
            history = new ArrayList<String>();
        }
        setContentView(R.layout.activity_main);

        Button convertButton = (Button) findViewById(R.id.button);

        final TextView toQuery = (TextView)findViewById(R.id.toTextView);
        final EditText queryEditText = (EditText) findViewById(R.id.editText2);
        final ListView viewOfHistory = (ListView) findViewById(R.id.historyView);
        //Now we're going to make the adapter
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, history);
        viewOfHistory.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    convert(v);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                String query = queryEditText.getText().toString().trim() + " " + getSpinnerCurrencyText(R.id.spinner) + " = "
                        + toQuery.getText().toString().trim() + " " + getSpinnerCurrencyText(R.id.toSpinner);
                if(!query.isEmpty()) {
                    //we're adding our query to the history now
                    adapter.add(query);
                }
            }
        });
        createCounter++;
        updateCounters();
    }

    @Override
    protected void onResume() {
        resumeCounter++;
        super.onResume();
        updateCounters();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "The activity's state is about to be saved.");
        outState.putStringArrayList("savedHistory", history);
        outState.putInt("savedCreateCounter", createCounter);
        outState.putInt("savedResumeCounter", resumeCounter);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "The activity's state is about to be restored.");
        if (savedInstanceState != null && savedInstanceState.containsKey("savedHistory") && savedInstanceState.containsKey("savedResumeCounter")) {
            // Restore history
            resumeCounter = savedInstanceState.getInt("savedResumeCounter");
            history = savedInstanceState.getStringArrayList("savedHistory");
        }
    }

    public void convert(View view) throws NoSuchFieldException, IllegalAccessException {
        EditText editText = (EditText) findViewById(R.id.editText2);
        Double readAmount = Double.parseDouble(editText.getText().toString());
        Double spinnerCurrencyFrom = getSpinnerCurrency(R.id.spinner);
        Double spinnerCurrencyTo = getSpinnerCurrency(R.id.toSpinner);
        //resources.getValue(resources.getIdentifier(spinnerString, "dimen", MainActivity.this.getPackageName()), typedValue, true);
        //resources.getValue(spin);


        Double euroConvert = readAmount*spinnerCurrencyTo/spinnerCurrencyFrom;
        TextView textView = (TextView) findViewById(R.id.toTextView);
        String decimal = String.format("%.2f", euroConvert);
        Log.i("Deci: ", decimal);
        textView.setText(decimal);
        TextView textViewTo = (TextView) findViewById(R.id.toTextViewIcon);
        textViewTo.setText(getSpinnerCurrencyIcon(R.id.toSpinner));

    }

    public String getSpinnerCurrencyText(int spinner){
        Spinner toSpinner = (Spinner)findViewById(spinner);
        int spinnerPosition = toSpinner.getSelectedItemPosition();
        String[] currencyText = getResources().getStringArray(R.array.Currencies);
        String text = String.valueOf(currencyText[spinnerPosition]);
        return text;
    }

    public String getSpinnerCurrencyIcon(int spinner){
        Spinner toSpinner = (Spinner) findViewById(spinner);
        int spinnerPosition = toSpinner.getSelectedItemPosition();
        String[] currencyIcons = getResources().getStringArray(R.array.currencyIcon);
        String icon = String.valueOf(currencyIcons[spinnerPosition]);
        return icon;
    }

    public Double getSpinnerCurrency(int spinner){
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

    public void updateCounters(){
        TextView createAndResume = (TextView)findViewById(R.id.createAndResumeTextView);
        createAndResume.setText("onCreate: " + createCounter + ", onResume: " + resumeCounter);
    }

}
