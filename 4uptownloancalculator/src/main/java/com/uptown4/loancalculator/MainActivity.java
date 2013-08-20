package com.uptown4.loancalculator;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    // added this
    public final static String passToIntent1 = "com.uptown4.loancalculator.extra.1";
    public final static String passToIntent2 = "com.uptown4.loancalculator.extra.2";
    private final String TAG = "MainActivity" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG,"onCreate(Bundle savedInstanceState)") ;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i(TAG, "onCreateOptionsMenu(Menu menu)") ;
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }







    // added this
    public void clickAmortize(View view) {
        Log.i(TAG, "clickAmortize(View view)") ;
        EditText peLoanAmount = (EditText) findViewById(R.id.txtLoanAmount);
        EditText peInterestRate = (EditText) findViewById(R.id.txtInterestRate);
        EditText peNumberOfPayments = (EditText) findViewById(R.id.txtNumberOfPayments);
        EditText peCustomPayment = (EditText) findViewById(R.id.txtCustomPayment);

        String strCalculateResult ;

        // create calculation class module
        AmortizationLC alc = new AmortizationLC();

        //validate entries - start
            if (InValidInputs ())
            {
                return;
            }
        //validate entries - end


        // Setup
        alc.setPrinciple( Double.parseDouble( peLoanAmount.getText().toString() ) ) ; // 1500.00
        alc.setInterestRate( Double.parseDouble( peInterestRate.getText().toString() ) ) ; // 9 - 9 %
        alc.setNumberOfPayments( Integer.parseInt( peNumberOfPayments.getText().toString() )  ) ; // now by months - 24 24 months - was this 2 - 2 years

        // Calculate
        strCalculateResult = alc.calAmortize( alc.getPrinciple() , alc.getInterestRate() , alc.getNumberOfPayments() , 0 ) ;

        /*
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
*/

        // show custom details if they exist
        if (Double.parseDouble( peCustomPayment.getText().toString() ) > 1 ){
            strCalculateResult = alc.calAmortize( alc.getPrinciple() , alc.getInterestRate() , alc.getNumberOfPayments() , Double.parseDouble( peCustomPayment.getText().toString() ) ) ;

            // set textview values - start
/*                txt = (TextView)findViewById(R.id.lblCustomMonthlyPayment);
                txt.setText( "Monthly Payment - $" + alc.getMonthlyPayment() );
                txt = (TextView)findViewById(R.id.lblCustomTotalPrinciple);
                txt.setText( "Total Principle Paid - $" + alc.getTotalPrinciple() );
                txt = (TextView)findViewById(R.id.lblCustomTotalInterest);
                txt.setText( "Total Interest Paid - " + alc.getTotalInterest() );
                txt = (TextView)findViewById(R.id.lblCustomTotalPaid);
                txt.setText( "Total Paid - $" + alc.getTotalPaid() );
                txt = (TextView)findViewById(R.id.lblCustomCompoundedInterest);
                txt.setText( "Compounded Interest- " + alc.getCompoundedInterest() );
*/
            // set textview values - start
        }






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
















    public void clickCalculate(View view) {
        Log.i(TAG, "clickCalculate(View view)") ;

        String strCalculateResult ;
        String sVal ;
        sVal = "<html><body>";
        TextView txt ;
        AmortizationLC alc = new AmortizationLC();


        EditText peLoanAmount = (EditText) findViewById(R.id.txtLoanAmount);
        EditText peInterestRate = (EditText) findViewById(R.id.txtInterestRate);
        EditText peNumberOfPayments = (EditText) findViewById(R.id.txtNumberOfPayments);
        EditText peCustomPayment = (EditText) findViewById(R.id.txtCustomPayment);


            //validate entries - start
                if (InValidInputs ())
                {
                    return;
                }
            //validate entries - end


                // Setup - class --- start
                alc.setPrinciple( Double.parseDouble( peLoanAmount.getText().toString() ) ) ; // 1500.00
                alc.setInterestRate( Double.parseDouble( peInterestRate.getText().toString() ) ) ; // 9 - 9 %
                alc.setNumberOfPayments( Integer.parseInt( peNumberOfPayments.getText().toString() )  ) ; // now by months - 24 24 months - was this 2 - 2 years
                    if (Double.parseDouble( peCustomPayment.getText().toString() ) >= 1 ){
                        alc.setMonthlyPayment( ( Double.parseDouble( peCustomPayment.getText().toString() )  ) );
                    }
                // Setup - class --- end

        // Calculate
        strCalculateResult = alc.calSimplyCalculate( alc.getPrinciple() , alc.getInterestRate() , alc.getNumberOfPayments() , 0 ) ;


        // set textview values - start
        sVal = sVal + "<table border ='1'>";

        sVal = sVal + "<tr>";
        sVal = sVal + "<td>Monthly Payment</td>";
        sVal = sVal + "<td>Total Principle Paid</td>";
        sVal = sVal + "<td>Total Interest Paid</td>";
        sVal = sVal + "<td>Total Paid</td>";
        sVal = sVal + "<td>Compounded Interest</td>";
        sVal = sVal + "</tr>";

        sVal = sVal + "<tr>";
        sVal = sVal + "<td>$" + alc.getMonthlyPayment() + "</td>";
        sVal = sVal + "<td>$" + alc.getTotalPrinciple() + "</td>";
        sVal = sVal + "<td>$" + alc.getTotalInterest() + "</td>";
        sVal = sVal + "<td>$" + alc.getTotalPaid() + "</td>";
        sVal = sVal + "<td>$" + alc.getCompoundedInterest() + "</td>";
        sVal = sVal + "</tr>";

        sVal = sVal + "</table>";
        // set textview values - start



        // show custom details if they exist
        if (Double.parseDouble( peCustomPayment.getText().toString() ) >= 1 )
        {
            strCalculateResult = alc.calSimplyCalculate( alc.getPrinciple() , alc.getInterestRate() , alc.getNumberOfPayments() , Double.parseDouble( peCustomPayment.getText().toString() ) ) ;

            // set textview values - start
            sVal = sVal + "</br>";
            sVal = sVal + "<table border ='1'>";

            sVal = sVal + "<tr>";
            sVal = sVal + "<td>Custom Monthly Payment</td>";
            sVal = sVal + "<td>Custom Total Principle Paid</td>";
            sVal = sVal + "<td>Custom Total Interest Paid</td>";
            sVal = sVal + "<td>Custom Total Paid</td>";
            sVal = sVal + "<td>Custom Compounded Interest</td>";
            sVal = sVal + "<tr>";

            sVal = sVal + "</tr>";
            sVal = sVal + "<td>$" + alc.getMonthlyPayment() + "</td>";
            sVal = sVal + "<td>$" + alc.getTotalPrinciple() + "</td>";
            sVal = sVal + "<td>$" + alc.getTotalInterest() + "</td>";
            sVal = sVal + "<td>$" + alc.getTotalPaid() + "</td>";
            sVal = sVal + "<td>$" + alc.getCompoundedInterest() + "</td>";
            sVal = sVal + "</tr>";
            sVal = sVal + "</table>";
            // set textview values - end
        }

        //WebView wb = new WebView(getApplicationContext());
        WebView wb = (WebView)findViewById(R.id.wvResult1);
        wb.getSettings().setJavaScriptEnabled(true);



        //sVal = sVal + "<script type=\"text/javascript\">document.write('";
        //sVal = sVal + mMathString.toString();
        ////builder.append(pVal);
        //sVal = sVal + "');";
        //sVal = sVal + "document.write('<br />=' + eval(\"";
        //sVal = sVal + mMathString.toString();
        ////builder.append(mMathString.toString());
        //sVal = sVal + "\"));</script>";
        sVal = sVal + "<hr><p>" + strCalculateResult + "</p><hr>" ;
        sVal = sVal + "</body></html>";
            wb.loadDataWithBaseURL(null, sVal , "text/html", "utf-8", null);
    }








    public boolean InValidInputs (){
        Log.i(TAG, "ValidateInputs (double vPrinciple,double vInterestRate, int vNumberOfMonths , double vCustomPayment )") ;

        EditText peLoanAmount = (EditText) findViewById(R.id.txtLoanAmount);
        EditText peInterestRate = (EditText) findViewById(R.id.txtInterestRate);
        EditText peNumberOfPayments = (EditText) findViewById(R.id.txtNumberOfPayments);
        EditText peCustomPayment = (EditText) findViewById(R.id.txtCustomPayment);


        //validate entries - start
        if ( peLoanAmount.getText().length() == 0 || peLoanAmount.getText().length() <= 0 )
        {
            Toast.makeText(this, "Please enter a valid Loan Amount",
                    Toast.LENGTH_LONG).show();
            return true;
        }

        if ( peInterestRate.getText().length() == 0 || peInterestRate.getText().length() <= 0 )
        {
            Toast.makeText(this, "Please enter a valid Interest Rate",
                    Toast.LENGTH_LONG).show();
            return true;
        }

        if ( peNumberOfPayments.getText().length() == 0 || peNumberOfPayments.getText().length() <= 0 )
        {
            Toast.makeText(this, "Please enter a valid Number Of Payments",
                    Toast.LENGTH_LONG).show();
            return true;
        }

        if ( peCustomPayment.getText().length() == 0 || peCustomPayment.getText().length() <= 0 )
        {
            Toast.makeText(this, "Please set the Custom Payment to 0 or your preferred payment amount",
                    Toast.LENGTH_LONG).show();
            return true;
        }

        if (Double.parseDouble( peLoanAmount.getText().toString() ) <=0){
            Toast.makeText(this, "Please enter a valid Loan Amount",
                    Toast.LENGTH_LONG).show();
            return true;
        }

        if (Double.parseDouble( peInterestRate.getText().toString() ) <=0){
            Toast.makeText(this, "Please enter a valid Interest Rate Ex: 7 = 7%",
                    Toast.LENGTH_LONG).show();
            return true;
        }

        if (Double.parseDouble( peNumberOfPayments.getText().toString() ) <=0){
            Toast.makeText(this, "Please enter a valid Number Of Payments",
                    Toast.LENGTH_LONG).show();
            return true;
        }

        if (Double.parseDouble( peNumberOfPayments.getText().toString() ) >500){
            Toast.makeText(this, "Please enter a Number Of Payments value between 1 & 500",
                    Toast.LENGTH_LONG).show();
            return true;
        }

        if (Double.parseDouble( peCustomPayment.getText().toString() ) < 0){
            Toast.makeText(this, "Please set the Custom Payment to 0 or your preferred payment amount",
                    Toast.LENGTH_LONG).show();
            return true;
        }

        return false;
    }







//// Dialog box - start
/*
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
*/
//// Dialog box - end

}



