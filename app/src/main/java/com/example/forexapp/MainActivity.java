package com.example.forexapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
<<<<<<< Updated upstream
=======
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
>>>>>>> Stashed changes
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.*;

import java.sql.Time;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

   static String curr_str;
   static String[] str_arr = new String[10];
   public ArrayList<String> refreshList  = new ArrayList<>();

    // eXCHANGE RATES - begin
<<<<<<< Updated upstream
    private double eurDol = 1.17;
    private double eurYen = 123.07;
    private double eurPound = 0.91;
    private double dolYen = 105.38;
    private double dolPound = 0.78;
    private double poundYen = 134.73;
    private double poundDol = 1.28;
=======
    private double eurDol = 99;
    private double eurYen = 99;
    private double eurPound = 99;
    private double dolYen = 99;
    private double dolPound = 99;
    private double poundYen = 99;
    //  2020_10_02
>>>>>>> Stashed changes
    // eXCHANGE RATES - end

    private String choice_from;
    private String choice_to;
    private String exRate;
    private String result;
    private double inputToDouble;
    private EditText et_userInput;
    private TextView tv_exRate;
    private TextView tv_result;
    private ImageButton bt_reset_right, bt_reset_left;
<<<<<<< Updated upstream
=======
    private Button bt_refresh;
    private Spinner spinner_from;
    private Spinner spinner_to;
    private String[] currency;
>>>>>>> Stashed changes


    DecimalFormat f_exRate = new DecimalFormat("#0.0000");
    DecimalFormat f_result = new DecimalFormat("#0.00");
/*
    private static String TAG = "WRITE_EXT_PERM";
    private static final int WRITE_EXT_PERM_CODE = 88;

 */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Permissions to download JSON and to write in storage
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        tv_exRate = findViewById(R.id.ET_exchange_rate);
        et_userInput = findViewById(R.id.ET_userInput);
        tv_result = findViewById(R.id.TV_result);
        bt_reset_right = findViewById(R.id.BT_reset_value_right);
        bt_reset_right.setOnClickListener(this);
        bt_reset_left = findViewById(R.id.BT_reset_value_left);
        bt_reset_left.setOnClickListener(this);
<<<<<<< Updated upstream
        final String[] currency = getResources().getStringArray(R.array.currency_array);
=======
        bt_refresh = findViewById(R.id.BT_refresh);
        currency = getResources().getStringArray(R.array.currency_array);
>>>>>>> Stashed changes

        //Spinner erstellen
        final Spinner spinner_from = findViewById(R.id.spinner_from);
        final Spinner spinner_to = findViewById(R.id.spinner_to);

        //Adapter erstellen und currenncy_array aus der Strings.xml zuweisen
        final ArrayAdapter<CharSequence> charSequenceArrayAdapter = ArrayAdapter.createFromResource(this, R.array.currency_array, android.R.layout.simple_spinner_item);
        charSequenceArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        // Adapter dem Spinner hinzufügen
        spinner_from.setAdapter(charSequenceArrayAdapter);
        spinner_to.setAdapter(charSequenceArrayAdapter);

        /**
         * Listener erstellen und dem Spinner zuweisen
         * Im folgenden wird geprüft ob etwas in das EditText-Feld eingegeben wurde
         * ist dies der Fall wird die Berechnung gestartet, hat der User nichts eingetragen,
         * so wird er aufgefordert einen Betrag einzugeben.
         * Hat der User als Ausgangs- und Zielwährung die gleiche Auswahl getroffen, so wird nichts
         * berechnet. Der User wird aufgefordert die Währungsauswahl zu überprüfen.
         * **/

        spinner_from.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (!et_userInput.getText().toString().matches("")) {
                    if (spinner_from.getSelectedItem().toString().equals(currency[0]) && spinner_to.getSelectedItem().toString().equals(currency[0])) {
                        tv_exRate.setText("Auswahl überprüfen");
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
                        tv_exRate.setText("Auswahl überprüfen");
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
                        tv_exRate.setText("Auswahl überprüfen");
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
                        tv_exRate.setText("Auswahl überprüfen");
                        tv_result.setText("");
                    }
                } // ENDE NOT NULL
                else{
                    tv_result.setText("Bitte Betrag eingeben");
                }
            }

<<<<<<< Updated upstream
=======
        try {
            refreshAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        bt_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    refreshAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        et_userInput.addTextChangedListener(new TextWatcher() {
>>>>>>> Stashed changes
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinner_to.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!et_userInput.getText().toString().matches("")) {
                    if (spinner_from.getSelectedItem().toString().equals(currency[0]) && spinner_to.getSelectedItem().toString().equals(currency[0])) {
                        tv_exRate.setText("Auswahl überprüfen");
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
                        tv_exRate.setText("Auswahl überprüfen");
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
                        tv_exRate.setText("Auswahl überprüfen");
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
                        tv_exRate.setText("Auswahl überprüfen");
                        tv_result.setText("");
                    }
                } //ENDE NOT NULL

                else {
                    tv_result.setText("Bitte Betrag eingeben");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }  // ENDE OnCreate



    @Override
<<<<<<< Updated upstream
=======
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        calculate(spinner_from, spinner_to);

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
>>>>>>> Stashed changes
    public void onClick(View v) {
        // Hier werden die Felder geleert und der User kann einen neuen Betrag eingeben
        if (v.getId() == bt_reset_right.getId() || v.getId() == bt_reset_left.getId()) {
            String leet = et_userInput.getText().toString();
            tv_exRate.setText("");
            tv_result.setText("");
            et_userInput.setText("");
        }
    }
<<<<<<< Updated upstream
=======

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
                //downloadJSON();
                String inputToString = et_userInput.getText().toString();
                inputToDouble = Double.parseDouble(inputToString);
                exRate = f_exRate.format(Double.parseDouble(refreshList.get(0)));
                result = f_result.format(inputToDouble * Double.parseDouble(refreshList.get(0)));
                tv_exRate.setText(exRate);
                tv_result.setText(result + " €");
            } else if (spinner_from.getSelectedItem().toString().equals(currency[0]) && spinner_to.getSelectedItem().toString().equals(currency[2])) {
                //downloadJSON();
                String inputToString = et_userInput.getText().toString();
                inputToDouble = Double.parseDouble(inputToString);
                exRate = f_exRate.format(Double.parseDouble(refreshList.get(1)));
                result = f_result.format(inputToDouble * Double.parseDouble(refreshList.get(1)));
                tv_exRate.setText(exRate);
                tv_result.setText(result + " ¥");
/*
            } else if (spinner_from.getSelectedItem().toString().equals(currency[0]) && spinner_to.getSelectedItem().toString().equals(currency[3])) {
                //downloadJSON();
                String inputToString = et_userInput.getText().toString();
                inputToDouble = Double.parseDouble(inputToString);
                exRate = f_exRate.format(Double.parseDouble(refreshList.get(2)));
                result = f_result.format(inputToDouble * Double.parseDouble(refreshList.get(2)));
                tv_exRate.setText(exRate);
                tv_result.setText(result + " £");

 */
            } else if (spinner_from.getSelectedItem().toString().equals(currency[1]) && spinner_to.getSelectedItem().toString().equals(currency[0])) {
                //downloadJSON();
                String inputToString = et_userInput.getText().toString();
                inputToDouble = Double.parseDouble(inputToString);
                exRate = f_exRate.format(1 / Double.parseDouble(refreshList.get(0)));
                result = f_result.format(inputToDouble / Double.parseDouble(refreshList.get(0)));
                tv_exRate.setText(exRate);
                tv_result.setText(result + " $");
            } else if (spinner_from.getSelectedItem().toString().equals(currency[1]) && spinner_to.getSelectedItem().toString().equals(currency[1])) {
                tv_exRate.setText(R.string.illegal_choice);
                tv_result.setText("");
            } else if (spinner_from.getSelectedItem().toString().equals(currency[1]) && spinner_to.getSelectedItem().toString().equals(currency[2])) {
                //downloadJSON();
                String inputToString = et_userInput.getText().toString();
                inputToDouble = Double.parseDouble(inputToString);
                exRate = f_exRate.format(Double.parseDouble(refreshList.get(2)));
                result = f_result.format(inputToDouble * Double.parseDouble(refreshList.get(2)));
                tv_exRate.setText(exRate);
                tv_result.setText(result + " ¥");
/*
            } else if (spinner_from.getSelectedItem().toString().equals(currency[1]) && spinner_to.getSelectedItem().toString().equals(currency[3])) {
                //downloadJSON();
                String inputToString = et_userInput.getText().toString();
                inputToDouble = Double.parseDouble(inputToString);
                exRate = f_exRate.format(1 / Double.parseDouble(refreshList.get(2)));
                result = f_result.format(inputToDouble / Double.parseDouble(refreshList.get(2)));
                tv_exRate.setText(exRate);
                tv_result.setText(result + " £");
 */
            } else if (spinner_from.getSelectedItem().toString().equals(currency[2]) && spinner_to.getSelectedItem().toString().equals(currency[0])) {
                //downloadJSON();
                String inputToString = et_userInput.getText().toString();
                inputToDouble = Double.parseDouble(inputToString);
                exRate = f_exRate.format(1 / Double.parseDouble(refreshList.get(1)));
                result = f_result.format(inputToDouble / Double.parseDouble(refreshList.get(1)));
                tv_exRate.setText(exRate);
                tv_result.setText(result + " $");
            } else if (spinner_from.getSelectedItem().toString().equals(currency[2]) && spinner_to.getSelectedItem().toString().equals(currency[1])) {
                //downloadJSON();
                String inputToString = et_userInput.getText().toString();
                inputToDouble = Double.parseDouble(inputToString);
                exRate = f_exRate.format(1 / Double.parseDouble(refreshList.get(2)));
                result = f_result.format(inputToDouble / Double.parseDouble(refreshList.get(2)));
                tv_exRate.setText(exRate);
                tv_result.setText(result + " €");
            } else if (spinner_from.getSelectedItem().toString().equals(currency[2]) && spinner_to.getSelectedItem().toString().equals(currency[2])) {
                tv_exRate.setText(R.string.illegal_choice);
                tv_result.setText("");
/*
            } else if (spinner_from.getSelectedItem().toString().equals(currency[2]) && spinner_to.getSelectedItem().toString().equals(currency[3])) {
                //downloadJSON();
                String inputToString = et_userInput.getText().toString();
                inputToDouble = Double.parseDouble(inputToString);
                exRate = f_exRate.format(Double.parseDouble(refreshList.get(5)));
                result = f_result.format(inputToDouble * Double.parseDouble(refreshList.get(5)));
                tv_exRate.setText(exRate);
                tv_result.setText(result + " £");
            } else if (spinner_from.getSelectedItem().toString().equals(currency[3]) && spinner_to.getSelectedItem().toString().equals(currency[0])) {
                //downloadJSON();
                String inputToString = et_userInput.getText().toString();
                inputToDouble = Double.parseDouble(inputToString);
                exRate = f_exRate.format(1 / Double.parseDouble(refreshList.get(2)));
                result = f_result.format(inputToDouble / Double.parseDouble(refreshList.get(2)));
                tv_exRate.setText(exRate);
                tv_result.setText(result + " $");
            } else if (spinner_from.getSelectedItem().toString().equals(currency[3]) && spinner_to.getSelectedItem().toString().equals(currency[1])) {
                //downloadJSON();
                String inputToString = et_userInput.getText().toString();
                inputToDouble = Double.parseDouble(inputToString);
                exRate = f_exRate.format(1 / Double.parseDouble(refreshList.get(4)));
                result = f_result.format(inputToDouble / Double.parseDouble(refreshList.get(4)));
                tv_exRate.setText(exRate);
                tv_result.setText(result + " €");
            } else if (spinner_from.getSelectedItem().toString().equals(currency[3]) && spinner_to.getSelectedItem().toString().equals(currency[2])) {
                //downloadJSON();
                String inputToString = et_userInput.getText().toString();
                inputToDouble = Double.parseDouble(inputToString);
                exRate = f_exRate.format(1 / Double.parseDouble(refreshList.get(5)));
                result = f_result.format(inputToDouble / Double.parseDouble(refreshList.get(5)));
                tv_exRate.setText(exRate);
                tv_result.setText(result + " ¥");
*/
            }
     /*           else if (spinner_from.getSelectedItem().toString().equals(currency[3]) && spinner_to.getSelectedItem().toString().equals(currency[3])) {
                tv_exRate.setText(R.string.illegal_choice);
                tv_result.setText("");
            }

      */
        } // ENDE NOT NULL
        else{
            tv_result.setText(R.string.please_insert_value);
        }
    }

    public void downloadJSON(){
        new JsonTask().execute();
        JSONObject json = new JsonTask().doInBackground();
        JSONObject exRates = null;
        try {
            exRates = json.getJSONObject("Realtime Currency Exchange Rate");
            /*
            str_arr[0] = exRates.getString("1. From_Currency Code");
            str_arr[1]= exRates.getString("2. From_Currency Name");
            str_arr[2] = exRates.getString("3. To_Currency Code");
            str_arr[3]= exRates.getString("4. To_Currency Name");
             */
            str_arr[4] = null;
            while(str_arr[4] == null) {
                str_arr[4] = exRates.getString("5. Exchange Rate");
            }
            /*
            str_arr[5]= exRates.getString("6. Last Refreshed");
            str_arr[6] = exRates.getString("7. Time Zone");
            str_arr[7]= exRates.getString("8. Bid Price");
            str_arr[8]= exRates.getString("9. Ask Price");
             */
        } catch (
                JSONException e) {
            e.printStackTrace();
        }
    }

    public void refreshAll() throws InterruptedException {
        int waitime = 100;
        refreshSingleCurrency("https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency=USD&to_currency=EUR&datatype=csv&apikey=3BKFOYOE1YT6PGJF"); //USDEUR 0
        //Thread.sleep(waitime);
        refreshSingleCurrency("https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency=USD&to_currency=JPY&datatype=csv&apikey=3BKFOYOE1YT6PGJF"); //USDJPY 1
        //Thread.sleep(waitime);
/*        refreshSingleCurrency("https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency=USD&to_currency=GBP&datatype=csv&apikey=3BKFOYOE1YT6PGJF"); //USDGBP 2     */
        //Thread.sleep(waitime);
        //refreshSingleCurrency("https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency=EUR&to_currency=USD&datatype=csv&apikey=3BKFOYOE1YT6PGJF"); //EURUSD 3
        //Thread.sleep(waitime);
        refreshSingleCurrency("https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency=EUR&to_currency=JPY&datatype=csv&apikey=3BKFOYOE1YT6PGJF"); //EURJPY 4
        //Thread.sleep(waitime);
/*        refreshSingleCurrency("https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency=EUR&to_currency=GBP&datatype=csv&apikey=3BKFOYOE1YT6PGJF"); //EURGBP 5     */
        //Thread.sleep(waitime);
        //refreshSingleCurrency("https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency=JPY&to_currency=USD&datatype=csv&apikey=3BKFOYOE1YT6PGJF"); //JPYUSD 6
        //Thread.sleep(waitime);
        //refreshSingleCurrency("https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency=JPY&to_currency=EUR&datatype=csv&apikey=3BKFOYOE1YT6PGJF"); //JPYEUR 7
        //Thread.sleep(waitime);
/*        refreshSingleCurrency("https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency=JPY&to_currency=GBP&datatype=csv&apikey=3BKFOYOE1YT6PGJF"); //JPYGBP 8     */
        //Thread.sleep(waitime);
        //refreshSingleCurrency("https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency=GBP&to_currency=USD&datatype=csv&apikey=3BKFOYOE1YT6PGJF"); //GBPUSD 9
        //Thread.sleep(waitime);
        //refreshSingleCurrency("https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency=GBP&to_currency=EUR&datatype=csv&apikey=3BKFOYOE1YT6PGJF"); //GBPEUR 10
        //Thread.sleep(waitime);
        //refreshSingleCurrency("https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency=GBP&to_currency=JPY&datatype=csv&apikey=3BKFOYOE1YT6PGJF"); //GBPJPY 11
    }
    public void refreshSingleCurrency(String url){
        curr_str= url;
        downloadJSON();
        refreshList.add(str_arr[4]);
    }
>>>>>>> Stashed changes
}



