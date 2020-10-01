package com.example.forexapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    // eXCHANGE RATES - begin
    private double eurDol = 1.17;
    private double eurYen = 123.07;
    private double eurPound = 0.91;
    private double dolYen = 105.38;
    private double dolPound = 0.78;
    private double poundYen = 134.73;
    private double poundDol = 1.28;
    // eXCHANGE RATES - end

    private String exRate;
    private String result;
    private double inputToDouble;
    private EditText et_userInput;
    private TextView tv_exRate;
    private TextView tv_result;
    private ImageButton bt_reset_right, bt_reset_left, bt_recalc_right, bt_recalc_left;
    private Spinner spinner_from;
    private Spinner spinner_to;
    private String[] currency;

    DecimalFormat f_exRate = new DecimalFormat("#0.0000");
    DecimalFormat f_result = new DecimalFormat("#0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_exRate = findViewById(R.id.ET_exchange_rate);
        et_userInput = findViewById(R.id.ET_userInput);
        tv_result = findViewById(R.id.TV_result);
        bt_reset_right = findViewById(R.id.BT_reset_value_right);
        bt_reset_right.setOnClickListener(this);
        bt_reset_left = findViewById(R.id.BT_reset_value_left);
        bt_reset_left.setOnClickListener(this);
        bt_recalc_right = findViewById(R.id.BT_recalc_right);
        bt_recalc_right.setOnClickListener(this);
        bt_recalc_left = findViewById(R.id.BT_recalc_left);
        bt_recalc_left.setOnClickListener(this);
        currency = getResources().getStringArray(R.array.currency_array);

        //Spinner mit .xml Verknüpfen
        spinner_from = findViewById(R.id.spinner_from);
        spinner_to = findViewById(R.id.spinner_to);

        //Adapter erstellen und currency_array aus der Strings.xml zuweisen
        ArrayAdapter<CharSequence> charSequenceArrayAdapter = ArrayAdapter.createFromResource(this, R.array.currency_array, android.R.layout.simple_spinner_item);
        charSequenceArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        // Adapter dem Spinner hinzufügen
        spinner_from.setAdapter(charSequenceArrayAdapter);
        spinner_to.setAdapter(charSequenceArrayAdapter);

       //Listener den Spinnern zuweisen
        spinner_from.setOnItemSelectedListener(this);
        spinner_to.setOnItemSelectedListener(this);

    }  // ENDE OnCreate

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        calculate(spinner_from, spinner_to);
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
    public void onClick(View v) {
        // Hier werden die Felder geleert und der User kann einen neuen Betrag eingeben
        if (v.getId() == bt_reset_right.getId() || v.getId() == bt_reset_left.getId()) {
            String leet = et_userInput.getText().toString();
            tv_exRate.setText("");
            tv_result.setText("");
            et_userInput.setText("");
        }
        // Betrag neu berechnen
        else if(v.getId() == bt_recalc_right.getId() || v.getId() == bt_recalc_left.getId()){
            calculate(spinner_from,spinner_to);
        }
    }

    public void calculate(Spinner spinner_from, Spinner spinner_to){
        /**
         * Im folgenden wird geprüft ob etwas in das EditText-Feld eingegeben wurde
         * ist dies der Fall wird die Berechnung gestartet, hat der User nichts eingetragen,
         * so wird er aufgefordert einen Betrag einzugeben.
         * Hat der User als Ausgangs- und Zielwährung die gleiche Auswahl getroffen, so wird nichts
         * berechnet. Der User wird aufgefordert die Währungsauswahl zu überprüfen.
         **/
        if (!et_userInput.getText().toString().matches("")) {
            if (spinner_from.getSelectedItem().toString().equals(currency[0]) && spinner_to.getSelectedItem().toString().equals(currency[0])) {
                tv_exRate.setText(R.string.illegal_choice);
                tv_result.setText("");
            } else if (spinner_from.getSelectedItem().toString().equals(currency[0]) && spinner_to.getSelectedItem().toString().equals(currency[1])) {
                String inputToString = et_userInput.getText().toString();
                inputToDouble = Double.parseDouble(inputToString);
                exRate = f_exRate.format(1 / eurDol);
                result = f_result.format(inputToDouble / eurDol);
                tv_exRate.setText(exRate);
                tv_result.setText(result + " €");
            } else if (spinner_from.getSelectedItem().toString().equals(currency[0]) && spinner_to.getSelectedItem().toString().equals(currency[2])) {
                String inputToString = et_userInput.getText().toString();
                inputToDouble = Double.parseDouble(inputToString);
                exRate = f_exRate.format(dolYen);
                result = f_result.format(inputToDouble * dolYen);
                tv_exRate.setText(exRate);
                tv_result.setText(result + " ¥");
            } else if (spinner_from.getSelectedItem().toString().equals(currency[0]) && spinner_to.getSelectedItem().toString().equals(currency[3])) {
                String inputToString = et_userInput.getText().toString();
                inputToDouble = Double.parseDouble(inputToString);
                exRate = f_exRate.format(dolPound);
                result = f_result.format(inputToDouble * dolPound);
                tv_exRate.setText(exRate);
                tv_result.setText(result + " £");
            } else if (spinner_from.getSelectedItem().toString().equals(currency[1]) && spinner_to.getSelectedItem().toString().equals(currency[0])) {
                String inputToString = et_userInput.getText().toString();
                inputToDouble = Double.parseDouble(inputToString);
                exRate = f_exRate.format(eurDol);
                result = f_result.format(inputToDouble * eurDol);
                tv_exRate.setText(exRate);
                tv_result.setText(result + " $");
            } else if (spinner_from.getSelectedItem().toString().equals(currency[1]) && spinner_to.getSelectedItem().toString().equals(currency[1])) {
                tv_exRate.setText(R.string.illegal_choice);
                tv_result.setText("");
            } else if (spinner_from.getSelectedItem().toString().equals(currency[1]) && spinner_to.getSelectedItem().toString().equals(currency[2])) {
                String inputToString = et_userInput.getText().toString();
                inputToDouble = Double.parseDouble(inputToString);
                exRate = f_exRate.format(eurYen);
                result = f_result.format(inputToDouble * eurYen);
                tv_exRate.setText(exRate);
                tv_result.setText(result + " ¥");
            } else if (spinner_from.getSelectedItem().toString().equals(currency[1]) && spinner_to.getSelectedItem().toString().equals(currency[3])) {
                String inputToString = et_userInput.getText().toString();
                inputToDouble = Double.parseDouble(inputToString);
                exRate = f_exRate.format(eurPound);
                result = f_result.format(inputToDouble * eurPound);
                tv_exRate.setText(exRate);
                tv_result.setText(result + " £");
            } else if (spinner_from.getSelectedItem().toString().equals(currency[2]) && spinner_to.getSelectedItem().toString().equals(currency[0])) {
                String inputToString = et_userInput.getText().toString();
                inputToDouble = Double.parseDouble(inputToString);
                exRate = f_exRate.format(1 / dolYen);
                result = f_result.format(inputToDouble / dolYen);
                tv_exRate.setText(exRate);
                tv_result.setText(result + " $");
            } else if (spinner_from.getSelectedItem().toString().equals(currency[2]) && spinner_to.getSelectedItem().toString().equals(currency[1])) {
                String inputToString = et_userInput.getText().toString();
                inputToDouble = Double.parseDouble(inputToString);
                exRate = f_exRate.format(1 / eurYen);
                result = f_result.format(inputToDouble / eurYen);
                tv_exRate.setText(exRate);
                tv_result.setText(result + " €");
            } else if (spinner_from.getSelectedItem().toString().equals(currency[2]) && spinner_to.getSelectedItem().toString().equals(currency[2])) {
                tv_exRate.setText(R.string.illegal_choice);
                tv_result.setText("");
            } else if (spinner_from.getSelectedItem().toString().equals(currency[2]) && spinner_to.getSelectedItem().toString().equals(currency[3])) {
                String inputToString = et_userInput.getText().toString();
                inputToDouble = Double.parseDouble(inputToString);
                exRate = f_exRate.format(1 / poundYen);
                result = f_result.format(inputToDouble / poundYen);
                tv_exRate.setText(exRate);
                tv_result.setText(result + " £");
            } else if (spinner_from.getSelectedItem().toString().equals(currency[3]) && spinner_to.getSelectedItem().toString().equals(currency[0])) {
                String inputToString = et_userInput.getText().toString();
                inputToDouble = Double.parseDouble(inputToString);
                exRate = f_exRate.format(1 / dolPound);
                result = f_result.format(inputToDouble / dolPound);
                tv_exRate.setText(exRate);
                tv_result.setText(result + " $");
            } else if (spinner_from.getSelectedItem().toString().equals(currency[3]) && spinner_to.getSelectedItem().toString().equals(currency[1])) {
                String inputToString = et_userInput.getText().toString();
                inputToDouble = Double.parseDouble(inputToString);
                exRate = f_exRate.format(1 / eurPound);
                result = f_result.format(inputToDouble / eurPound);
                tv_exRate.setText(exRate);
                tv_result.setText(result + " €");
            } else if (spinner_from.getSelectedItem().toString().equals(currency[3]) && spinner_to.getSelectedItem().toString().equals(currency[2])) {
                String inputToString = et_userInput.getText().toString();
                inputToDouble = Double.parseDouble(inputToString);
                exRate = f_exRate.format(poundYen);
                result = f_result.format(inputToDouble * poundYen);
                tv_exRate.setText(exRate);
                tv_result.setText(result + " ¥");
            } else if (spinner_from.getSelectedItem().toString().equals(currency[3]) && spinner_to.getSelectedItem().toString().equals(currency[3])) {
                tv_exRate.setText(R.string.illegal_choice);
                tv_result.setText("");
            }
        } // ENDE NOT NULL
        else{
            tv_result.setText(R.string.please_insert_value);
        }
    }
}



