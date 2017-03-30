// TipCalculator.java
// Calculates bills using 15% and custom percentage tips
package thall59.assignment5;

import android.os.health.PackageHealthStats;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.text.NumberFormat; // for currency formatting

import android.app.Activity; // base class for activities
import android.os.Bundle; // for saving state information
import android.text.Editable; // for EditText event handling
import android.text.TextWatcher; // EditText listener
import android.widget.EditText; // for bill amount input
import android.widget.SeekBar; // for changing custom tip percentage
import android.widget.SeekBar.OnSeekBarChangeListener; // SeekBar listener
import android.widget.TextView; // for displaying text


// TipCalculator class for the Tip Calculator app
public class TipCalculator extends AppCompatActivity
{

    // currency and percent formaters
    private static final NumberFormat currencyFormat =
            NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat =
            NumberFormat.getPercentInstance();

    private double billAmount = 0.0; // bill amount entered by the user
    private double customPercent = 0.20; // initial custom tip percentage
    private TextView amount_display_textview; // shows formatted bill amount
    private TextView percent_custom_textview; // shows the custom tip percentage
    private TextView tip_fifteen_textview; // shows 15% tip
    private TextView total_fifteen_textview; //shows total with 15% tip
    private TextView tip_custom_textview; // shows custom tip amount
    private TextView total_custom_textview; // shows total with custom tip


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // call superclass's version
        setContentView(R.layout.activity_tip_calculator); // inflate the GUI
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher); // icon

        // get references to the TextViews
        // that TipCalculator interacts with programmatically
        amount_display_textview =
                (TextView) findViewById(R.id.amount_display_textview);
        percent_custom_textview =
                (TextView) findViewById(R.id.percent_custom_textview);
        tip_fifteen_textview =
                (TextView) findViewById(R.id.tip_fifteen_textview);
        total_fifteen_textview =
                (TextView) findViewById(R.id.total_fifteen_textview);
        tip_custom_textview =
                (TextView) findViewById(R.id.tip_custom_textview);
        total_custom_textview =
                (TextView) findViewById(R.id.total_custom_textview);

        // update GUI based on billAmount and customPercent
        amount_display_textview.setText(
                currencyFormat.format(billAmount));
        updateStandard(); // update the 15% tip TextViews
        updateCustom(); // update the custom tip TextViews

        // set amount_edittext's TextWatcher
        EditText amount_edittext =
                (EditText) findViewById(R.id.amount_edittext);
        amount_edittext.addTextChangedListener(amountEditTextWatcher);

        // set custom_tip_seekbar's OnSeekBarChangeListener
        SeekBar custom_tip_seekbar =
                (SeekBar) findViewById(R.id.custom_tip_seekbar);
        custom_tip_seekbar.setOnSeekBarChangeListener(customSeekBarListener);

    } // end method onCreate

    // update the tip textview
    private void updateStandard()
    {
        //calculate 15% tip and total
        double fifteenPercentTip = billAmount * 0.15;
        double fifteenPercentTotal = billAmount + fifteenPercentTip;

        // display 15% tip and total formatted as currency
        tip_fifteen_textview.setText(currencyFormat.format(fifteenPercentTip));
        total_fifteen_textview.setText(currencyFormat.format(fifteenPercentTotal));
    }// end method updateStandard

    // updates the custom tip and total textviews
    private void updateCustom()
    {
        // show custom percent in percent_custom_textview formatted as %
        percent_custom_textview.setText(percentFormat.format(customPercent));

        // calculate the custom tip and total
        double customTip = billAmount * customPercent;
        double customTotal = billAmount + customTip;

        // display custom tip and total formatted as currency
        tip_custom_textview.setText(currencyFormat.format(customTip));
        total_custom_textview.setText(currencyFormat.format(customTotal));
    }// end method updateCustom

    // call when the user changes the position of seekbar
    private OnSeekBarChangeListener customSeekBarListener =
            new OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    // sets customPercent to position of the seekbars thumb
                    customPercent = progress / 100.0;
                    updateCustom(); // update the custom tip textviews
                } // end method onProgressChanged

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                } // end method onStartTrackingTouch

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                } // end method onStopTrackingTouch
            }; // end method OnSeekBarChangeListener


    // event-handling object that responds to amountEditText's events
    private TextWatcher amountEditTextWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {
            // convert amountEditText's into a double
            try
            {
               billAmount = Double.parseDouble(s.toString()) / 100.0;

            } // end try

            catch (NumberFormatException e){

                billAmount = 0.0; // default if an exception occurs
            } // end catch

            // display currency formatted bill amount
            amount_display_textview.setText(currencyFormat.format(billAmount));
            updateStandard(); // update the 15% textview
            updateCustom(); // update the custom textview
        } // end method onTextChanged

        @Override
        public void afterTextChanged(Editable s)
        {
        } // end method afterTextChanged

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        } // end method beforeTextChanged
    }; // end amountEditTextWatcher

} // end class TipCalculator
