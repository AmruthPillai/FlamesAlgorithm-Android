package com.flames;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import org.apache.commons.lang3.ArrayUtils;

import java.text.MessageFormat;

public class FlamesActivity extends AppCompatActivity {

    ImageView img_flamesLogo;
    TextInputLayout layout_yourName, layout_partnerName;
    TextInputEditText edit_yourName, edit_partnerName;
    Button btn_calculate;
    TextView text_flamesResult;

    String FLAMES = "FLAMES";
    char[] arr_FLAMES = FLAMES.toCharArray();

    String yourName, yourNameDisplay, partnerName, partnerNameDisplay, completeName;
    String[] arr_yourName, arr_partnerName, arr_completeName;

    YoYo.YoYoString logoAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flames);

        img_flamesLogo = (ImageView) findViewById(R.id.flames_logo);

        layout_yourName = (TextInputLayout) findViewById(R.id.input_layout_your_name);
        layout_partnerName = (TextInputLayout) findViewById(R.id.input_layout_partner_name);

        edit_yourName = (TextInputEditText) findViewById(R.id.input_your_name);
        edit_partnerName = (TextInputEditText) findViewById(R.id.input_partner_name);

        btn_calculate = (Button) findViewById(R.id.button_calculate);
        text_flamesResult = (TextView) findViewById(R.id.text_flames_result);

        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hide Soft Keyboard onClick
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow((null == getCurrentFocus()) ? null : getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                // Bounce Animation on Flames Logo
                logoAnimator = YoYo.with(Techniques.Bounce)
                        .playOn(img_flamesLogo);

                resetValues();

                if (edit_yourName.getText().toString().trim().isEmpty()) return;
                if (edit_partnerName.getText().toString().trim().isEmpty()) return;

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
        text_flamesResult.setText("");
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
