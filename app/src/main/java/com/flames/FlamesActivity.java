package com.flames;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.commons.lang3.ArrayUtils;

import java.text.MessageFormat;

public class FlamesActivity extends AppCompatActivity {

    EditText edit_yourName, edit_partnerName;
    Button btn_calculate;
    TextView text_flamesResult;

    String FLAMES = "FLAMES";
    char[] arr_FLAMES = FLAMES.toCharArray();

    String yourName, yourNameDisplay, partnerName, partnerNameDisplay, completeName;
    String[] arr_yourName, arr_partnerName, arr_completeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flames);

        edit_yourName = (EditText) findViewById(R.id.input_your_name);
        edit_partnerName = (EditText) findViewById(R.id.input_partner_name);
        btn_calculate = (Button) findViewById(R.id.button_calculate);
        text_flamesResult = (TextView) findViewById(R.id.text_flames_result);

        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hide Soft Keyboard onClick
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow((null == getCurrentFocus()) ? null : getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                resetValues();
                processName();
                eliminateCommonLetters();
                flamesCalculator();
                displayFlames();
            }
        });
    }

    public void resetValues() {
        FLAMES = getString(R.string.FLAMES);
        arr_FLAMES = FLAMES.toCharArray();
    }

    public void processName() {
        yourNameDisplay = edit_yourName.getText().toString();
        yourName = yourNameDisplay.toLowerCase().replaceAll("\\s+", "");
        arr_yourName = yourName.split("");

        partnerNameDisplay = edit_partnerName.getText().toString();
        partnerName = partnerNameDisplay.toLowerCase().replaceAll("\\s+", "");
        arr_partnerName = partnerName.split("");
    }

    public void eliminateCommonLetters() {
        for (String i : arr_yourName) {
            for (String j : arr_partnerName) {
                if ( i.equals(j) ) {
                    arr_yourName = ArrayUtils.removeElement(arr_yourName, i);
                    arr_partnerName = ArrayUtils.removeElement(arr_partnerName, j);
                    break;
                }
            }
        }

        arr_completeName = ArrayUtils.addAll(arr_yourName, arr_partnerName);
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : arr_completeName) {
            stringBuilder.append(string);
        }

        completeName = stringBuilder.toString();
    }

    public void flamesCalculator() {
        int index, flamesLength = 6;

        while (FLAMES.length() != 1) {
            index = completeName.length() % flamesLength;

            if (index == 0) {
                FLAMES = FLAMES.replace(String.valueOf(arr_FLAMES[FLAMES.length() - 1]), "");
                arr_FLAMES = FLAMES.toCharArray();
            } else {
                FLAMES = FLAMES.replace(String.valueOf(arr_FLAMES[index - 1]), "");
                FLAMES = FLAMES.substring(index - 1) + FLAMES.substring(0, index - 1);
                arr_FLAMES = FLAMES.toCharArray();
            }

            flamesLength--;
        }

    }

    public void displayFlames() {

        switch (arr_FLAMES[0]) {
            case 'F':
                text_flamesResult.setText(MessageFormat.format("{0} and {1} are friends!", yourNameDisplay, partnerNameDisplay));
                break;
            case 'L':
                text_flamesResult.setText(MessageFormat.format("{0} and {1} are lovers!", yourNameDisplay, partnerNameDisplay));
                break;
            case 'A':
                text_flamesResult.setText(MessageFormat.format("{0} and {1} are affectionate towards each other!", yourNameDisplay, partnerNameDisplay));
                break;
            case 'M':
                text_flamesResult.setText(MessageFormat.format("{0} and {1} are or will be married!", yourNameDisplay, partnerNameDisplay));
                break;
            case 'E':
                text_flamesResult.setText(MessageFormat.format("{0} and {1} are enemies!", yourNameDisplay, partnerNameDisplay));
                break;
            case 'S':
                text_flamesResult.setText(MessageFormat.format("{0} and {1} are like siblings!", yourNameDisplay, partnerNameDisplay));
                break;
        }
    }
}
