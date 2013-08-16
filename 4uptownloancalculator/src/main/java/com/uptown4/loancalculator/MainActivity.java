package com.uptown4.loancalculator;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    // added this
    public final static String passToIntent1 = "com.uptown4.loancalculator.extra.1";
    public final static String passToIntent2 = "com.uptown4.loancalculator.extra.2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // added this
    public void clickCalculate(View view) {
        EditText peLoanAmount = (EditText) findViewById(R.id.txtLoanAmount);
        EditText peInterestRate = (EditText) findViewById(R.id.txtInterestRate);
        EditText peNumberOfPayments = (EditText) findViewById(R.id.txtNumberOfPayments);

        String strCalculateResult ;

        // create calculation class module
        AmortizationLC alc = new AmortizationLC();
            //validate entries - start
                if ( peLoanAmount.getText().length() == 0 || peLoanAmount.getText().length() <= 0 )
                {
                    Toast.makeText(this, "Please enter a valid Loan Amount",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                if ( peInterestRate.getText().length() == 0 || peInterestRate.getText().length() <= 0 )
                {
                    Toast.makeText(this, "Please enter a valid Interest Rate",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                if ( peNumberOfPayments.getText().length() == 0 || peNumberOfPayments.getText().length() <= 0 )
                {
                    Toast.makeText(this, "Please enter a valid Number Of Payments",
                            Toast.LENGTH_LONG).show();
                    return;
                }
            //validate entries - end


                // Setup
                alc.setPrinciple( Double.parseDouble( peLoanAmount.getText().toString() ) ) ; // 1500.00
                alc.setInterestRate( Double.parseDouble( peInterestRate.getText().toString() ) ) ; // 9 - 9 %
                alc.setNumberOfPayments( Integer.parseInt( peNumberOfPayments.getText().toString() )  ) ; // now by months - 24 24 months - was this 2 - 2 years

                    // Calculate
                    strCalculateResult = alc.calAmortize( alc.getPrinciple() , alc.getInterestRate() , alc.getNumberOfPayments() ) ;

// set textview values - start
    TextView txt = (TextView)findViewById(R.id.lblMonthlyPayment);
    txt.setText( "Monthly Payment - $" + alc.getMonthlyPayment() );
        txt = (TextView)findViewById(R.id.lblTotalPrinciple);
        txt.setText( "Total Principle Paid - $" + alc.getTotalPrinciple() );
            txt = (TextView)findViewById(R.id.lblTotalInterest);
            txt.setText( "Total Interest Paid - " + alc.getTotalInterest() );
                txt = (TextView)findViewById(R.id.lblTotalPaid);
                txt.setText( "Total Paid - $" + alc.getTotalPaid() );
                    txt = (TextView)findViewById(R.id.lblCompoundedInterest);
                    txt.setText( "Compounded Interest- " + alc.getCompoundedInterest() );

// set textview values - start




//// Dialog box - start
        // create the new dialog
        Dialog dialog = new Dialog(MainActivity.this);
            //set the title
            dialog.setTitle("Dialog Title");
                //Inflate the layout
                dialog.setContentView(R.layout.activity_display_calculation);
                    //update the dialogs contents.
                    //TextView text = (TextView)dialog.findViewById(R.id.lblResult);
                    //WebView wv = (WebView)dialog.findViewById(R.id.wvResult);

                    //text.setText( "Return Test Value - " + dbTest );
                        //display the dialog
                        ////dialog.show();
//// Dialog box - end

    // works
    //text.setText( strCalculateResult );
    //text.setText( "works\nworks2\nworks3\nworks4\nxxxx" );
    //wv.loadData( strCalculateResult , "application/xhtml" , "UTF-8" );


    //if(mMathString.length() > 0) mMathString.delete(0, mMathString.length());
    //mMathString.append((  (  strCalculateResult   ) v).getText());
    //updateWebView();

// show the calculation - start
        Intent intent = new Intent(  this ,  DisplayCalculationActivity.class);
/*
        WebView wv = (WebView) findViewById(R.id.wvResult);
            WebSettings webSettings = wv.getSettings();
            webSettings.setJavaScriptEnabled(true);
*/
        //EditText editText = (EditText) findViewById(R.id.txtLoanAmount);
        //wv.loadData( "works" , "application/xhtml" , "UTF-8" );
        //wv.loadData( strCalculateResult , "application/xhtml" , "UTF-8" );

        //String message = editText.getText().toString();

            // LINKS TO THE PUBLIC class at top...
            intent.putExtra( passToIntent1 , strCalculateResult);
            //intent.putExtra( passToIntent2 , "works\nworks2\nworks3");

                // don't forget this part!!!  This makes the page appear!!!
                startActivity(intent);
// show the calculation - end



    }

}
