package com.uptown4.loancalculator;

import android.util.Log;

import java.text.DecimalFormat;

/**
 * Created by ~Q~ on 8/8/13.
 */
public class AmortizationLC {
    private final String TAG = "AmortizationLC" ;

// properties - start
    // Principle
    private double pPrinciple = 0.00 ;
    // Interest Rate
    private double pInterestRate = 0.00 ;
    // Number of Payments
    private int pNumberOfPayments = 0 ;
    // Total Principle Paid
    private double pTotalPrinciple = 0 ;
    // Total Interest Paid
    private double pTotalInterest = 0 ;
    // Total Paid
    private double pTotalPaid= 0 ;
    // Total Paid
    private double pMonthlyPayment= 0 ;
    // Compounded Interest
    private double pCompoundedInterest= 0 ;



        public void setPrinciple(double val) {
            pPrinciple = val ;
        }
            public double getPrinciple() {
                return pPrinciple ;
            }

        public void setInterestRate(double val) {
            pInterestRate = val ;
        }
            public double getInterestRate() {
                return pInterestRate ;
            }

        public void setNumberOfPayments(int val) {
            pNumberOfPayments = val ;
        }
            public int getNumberOfPayments() {
                return pNumberOfPayments ;
            }

        public void setMonthlyPayment(double val) {
            pMonthlyPayment = val ;
        }
            public double getMonthlyPayment() {
                return pMonthlyPayment;
            }

    public double getTotalPrinciple() {
        return pTotalPrinciple ;
    }
    public double getTotalInterest() {
        return pTotalInterest ;
    }
    public double getTotalPaid() {
        return pTotalPaid ;
    }
    public double getCompoundedInterest() {
        return pCompoundedInterest;
    }

    // properties - end




    public void main(String[] args){
        // added - start
            //String sOut ;
        // added - end
        //sOut = "";
            //sOut = calAmortize(p,iy,n);
    }






    public String calSimplyCalculate(double vPrinciple,double vInterestRate, int vNumberOfMonths , double vCustomPayment ){
        Log.i(TAG, "calSimplyCalculate(double vPrinciple,double vInterestRate, int vNumberOfMonths , double vCustomPayment )") ;

        // added - start
            String sOut ;
            DecimalFormat df = new DecimalFormat("#.##") ;
            String dv ;
        // added - end

        double vNewPrinciple;

/*
        r = (1 + i/n)^n - 1.

        In this formula,
        r represents the effective interest rate,
        i represents the stated interest rate,
        and n represents the number of compounding periods per year.
*/

        pCompoundedInterest = Math.pow(  (1 + (vInterestRate/100)/vNumberOfMonths) , vNumberOfMonths) - 1 ;
        pCompoundedInterest = pCompoundedInterest * 100 ;

        dv = df.format(pCompoundedInterest) ;
            pCompoundedInterest = Double.parseDouble(dv);

        //r = (1 + .05/12)^12 - 1, or r = 5.12 percent.
        //int vNumberOfMonths=vNumberOfMonths;

        //double vInterestRatePerMonth=(vInterestRate/12)/100;

        double vInterestRatePerMonth=(pCompoundedInterest/12)/100;
        double vMonthlyPayment,vInterestPaid,vPrinciplePaid;
        int i;

        sOut = "" ;
        vMonthlyPayment=0;
        vInterestPaid=0;
        vPrinciplePaid=0;

        if (vCustomPayment <= 0)
        {
            //vMonthlyPayment=vPrinciple*vInterestRatePerMonth*Math.pow(1+vInterestRatePerMonth,(double)vNumberOfMonths)/(Math.pow(1+vInterestRatePerMonth,(double)vNumberOfMonths)-1);
            pMonthlyPayment=vPrinciple*vInterestRatePerMonth*Math.pow(1+vInterestRatePerMonth,(double)vNumberOfMonths)/(Math.pow(1+vInterestRatePerMonth,(double)vNumberOfMonths)-1);
            //pMonthlyPayment=vMonthlyPayment;
        }else {
            pMonthlyPayment = vCustomPayment ;
        }


        //sOut = sOut + "<table>";

        vNewPrinciple = vPrinciple ;

        //print amortization schedule for all months except the last month
        for(i=1;i<vNumberOfMonths;i++){
            if (pMonthlyPayment < vNewPrinciple)
            {
            vInterestPaid=vPrinciple*vInterestRatePerMonth;//interest paid
            //vPrinciplePaid=vMonthlyPayment-vInterestPaid; //principal paid
            vPrinciplePaid=pMonthlyPayment-vInterestPaid; //principal paid
            vNewPrinciple=vPrinciple-vPrinciplePaid; //new balance


            pTotalPrinciple = pTotalPrinciple  + vPrinciplePaid ;
            pTotalInterest = pTotalInterest + vInterestPaid ;
            pTotalPaid = pTotalPaid + pMonthlyPayment ;


// convert the values to ##.00 format - start
            dv = df.format(vPrinciple) ;
                vPrinciple = Double.parseDouble(dv);
            dv = df.format(vMonthlyPayment) ;
                vMonthlyPayment = Double.parseDouble(dv);
            dv = df.format(vInterestPaid) ;
                vInterestPaid = Double.parseDouble(dv);
            dv = df.format(vPrinciplePaid) ;
                vPrinciplePaid = Double.parseDouble(dv);
            dv = df.format(vNewPrinciple) ;
                vNewPrinciple = Double.parseDouble(dv);
            dv = df.format(pMonthlyPayment) ;
                pMonthlyPayment = Double.parseDouble(dv);
            dv = df.format(pTotalPrinciple) ;
                pTotalPrinciple = Double.parseDouble(dv);
            dv = df.format(pTotalInterest) ;
                pTotalInterest = Double.parseDouble(dv);
            dv = df.format(pTotalPaid) ;
                pTotalPaid = Double.parseDouble(dv);
// convert the values to ##.00 format -end

            vPrinciple=vNewPrinciple;  //update old balance
                }else{
                    i=vNumberOfMonths;
                }
            }

        //last month
        vPrinciplePaid=vPrinciple;
        vInterestPaid=vPrinciple*vInterestRatePerMonth;
        vMonthlyPayment=vPrinciplePaid+vInterestPaid;
        vNewPrinciple=0.0;

        pTotalPrinciple = pTotalPrinciple  + vPrinciplePaid ;
        pTotalInterest = pTotalInterest + vInterestPaid ;
        pTotalPaid = pTotalPaid + vMonthlyPayment ;

// convert the values to ##.00 format - start
        dv = df.format(vPrinciple) ;
            vPrinciple = Double.parseDouble(dv);
        dv = df.format(vMonthlyPayment) ;
            vMonthlyPayment = Double.parseDouble(dv);
        dv = df.format(vInterestPaid) ;
            vInterestPaid = Double.parseDouble(dv);
        dv = df.format(vPrinciplePaid) ;
            vPrinciplePaid = Double.parseDouble(dv);
        dv = df.format(vNewPrinciple) ;
            vNewPrinciple = Double.parseDouble(dv);
        dv = df.format(pMonthlyPayment) ;
            pMonthlyPayment = Double.parseDouble(dv);
        dv = df.format(pTotalPrinciple) ;
            pTotalPrinciple = Double.parseDouble(dv);
        dv = df.format(pTotalInterest) ;
            pTotalInterest = Double.parseDouble(dv);
        dv = df.format(pTotalPaid) ;
            pTotalPaid = Double.parseDouble(dv);
// convert the values to ##.00 format -end


            //sOut = sOut + printTotals() ;
              //  sOut = sOut + "</table>";
        return sOut ;
        }












    public String calAmortize(double vPrinciple,double vInterestRate, int vNumberOfMonths , double vCustomPayment ){
        Log.i(TAG, "calAmortize") ;

        // added - start
        String sOut ;
            sOut = "" ;
        DecimalFormat df = new DecimalFormat("#.##") ;
        String dv ;
            dv = "";
        // added - end

        double vNewPrinciple = 0;
        double vInterestRatePerMonth=(pCompoundedInterest/12)/100;
        double vMonthlyPayment=0;
        double vInterestPaid=0;
        double vPrinciplePaid=0;
        int i=0;

        pTotalPrinciple = 0 ;
        pTotalInterest = 0 ;
        pTotalPaid = 0 ;
        pMonthlyPayment = 0;


/*
        r = (1 + i/n)^n - 1.

        In this formula,
        r represents the effective interest rate,
        i represents the stated interest rate,
        and n represents the number of compounding periods per year.
*/

        // calculate the interest & compound interest--- start
            pCompoundedInterest = Math.pow(  (1 + (vInterestRate/100)/vNumberOfMonths) , vNumberOfMonths) - 1 ;
            pCompoundedInterest = pCompoundedInterest * 100 ;

            dv = df.format(pCompoundedInterest) ;
            pCompoundedInterest = Double.parseDouble(dv);
        // calculate the interest & compound interest--- end



        //r = (1 + .05/12)^12 - 1, or r = 5.12 percent.
        //int vNumberOfMonths=vNumberOfMonths;

        //double vInterestRatePerMonth=(vInterestRate/12)/100;


        if (vCustomPayment > 0)
        {
            pMonthlyPayment = vCustomPayment ;
        }else {
            //vMonthlyPayment=vPrinciple*vInterestRatePerMonth*Math.pow(1+vInterestRatePerMonth,(double)vNumberOfMonths)/(Math.pow(1+vInterestRatePerMonth,(double)vNumberOfMonths)-1);
            pMonthlyPayment=vPrinciple*vInterestRatePerMonth*Math.pow(1+vInterestRatePerMonth,(double)vNumberOfMonths)/(Math.pow(1+vInterestRatePerMonth,(double)vNumberOfMonths)-1);
            //pMonthlyPayment=vMonthlyPayment;
        }


        sOut = sOut + "<table>";
        sOut = sOut + "<table border='1' > "+ printHeader();

        //print amortization schedule for all months except the last month
        for(i=1;i<vNumberOfMonths;i++)
        {
            if (pMonthlyPayment < vNewPrinciple)
            {
            vInterestPaid=vPrinciple*vInterestRatePerMonth;//interest paid
            //vPrinciplePaid=vMonthlyPayment-vInterestPaid; //principal paid
            vPrinciplePaid=pMonthlyPayment-vInterestPaid; //principal paid
            vNewPrinciple=vPrinciple-vPrinciplePaid; //new balance

            pTotalPrinciple = pTotalPrinciple  + vPrinciplePaid ;
            pTotalInterest = pTotalInterest + vInterestPaid ;
            pTotalPaid = pTotalPaid + pMonthlyPayment ;


// convert the values to ##.00 format - start
            dv = df.format(vPrinciple) ;
            vPrinciple = Double.parseDouble(dv);
            dv = df.format(vMonthlyPayment) ;
            vMonthlyPayment = Double.parseDouble(dv);
            dv = df.format(vInterestPaid) ;
            vInterestPaid = Double.parseDouble(dv);
            dv = df.format(vPrinciplePaid) ;
            vPrinciplePaid = Double.parseDouble(dv);
            dv = df.format(vNewPrinciple) ;
            vNewPrinciple = Double.parseDouble(dv);
            dv = df.format(pMonthlyPayment) ;
            pMonthlyPayment = Double.parseDouble(dv);
            dv = df.format(pTotalPrinciple) ;
            pTotalPrinciple = Double.parseDouble(dv);
            dv = df.format(pTotalInterest) ;
            pTotalInterest = Double.parseDouble(dv);
            dv = df.format(pTotalPaid) ;
            pTotalPaid = Double.parseDouble(dv);
// convert the values to ##.00 format -end

            sOut = sOut + printSch(i,vPrinciple,pMonthlyPayment,vInterestPaid,vPrinciplePaid,vNewPrinciple);
            vPrinciple=vNewPrinciple;  //update old balance
                }else{
                    i=vNumberOfMonths;
                }
        }


        //last month
        vPrinciplePaid=vPrinciple;
        vInterestPaid=vPrinciple*vInterestRatePerMonth;
        vMonthlyPayment=vPrinciplePaid+vInterestPaid;
        vNewPrinciple=0.0;

        pTotalPrinciple = pTotalPrinciple + vPrinciplePaid ;
        pTotalInterest = pTotalInterest + vInterestPaid ;
        pTotalPaid = pTotalPaid + vMonthlyPayment ;

// convert the values to ##.00 format - start
        dv = df.format(vPrinciple) ;
        vPrinciple = Double.parseDouble(dv);
        dv = df.format(vMonthlyPayment) ;
        vMonthlyPayment = Double.parseDouble(dv);
        dv = df.format(vInterestPaid) ;
        vInterestPaid = Double.parseDouble(dv);
        dv = df.format(vPrinciplePaid) ;
        vPrinciplePaid = Double.parseDouble(dv);
        dv = df.format(vNewPrinciple) ;
        vNewPrinciple = Double.parseDouble(dv);
        dv = df.format(pMonthlyPayment) ;
        pMonthlyPayment = Double.parseDouble(dv);
        dv = df.format(pTotalPrinciple) ;
        pTotalPrinciple = Double.parseDouble(dv);
        dv = df.format(pTotalInterest) ;
        pTotalInterest = Double.parseDouble(dv);
        dv = df.format(pTotalPaid) ;
        pTotalPaid = Double.parseDouble(dv);
// convert the values to ##.00 format -end


        sOut = sOut + printSch(i,vPrinciple,vMonthlyPayment,vInterestPaid,vPrinciplePaid,vNewPrinciple);
        sOut = sOut + printTotals() ;
        sOut = sOut + "</table>";
        return sOut ;
    }







    public String printSch(int i,double p,double vMonthlyPayment,double vInterestPaid,double vPrinciplePaid,double vNewPrinciple){
        Log.i(TAG, "printSch") ;
        // added - start
            String sOut ;
        // added - end

        //"%-8d%-12.3f%-10.3f%-10.3f%-10.3f%-12.3f\n"

        sOut = "" ;

/*
//works
        sOut = sOut + sOut.format(
                "\n%-6s%-18s%-19s%-18s%-18s%-18s"
                , i
                , vMonthlyPayment
                , vPrinciplePaid
                , vInterestPaid
                , vNewPrinciple
                , p
            );
*/

/*
//works
        sOut = "\n # - " + i
                + " + Payment - " + vMonthlyPayment
                + " + To Principle - " + vPrinciplePaid
                + " + To Interest - " +  vInterestPaid
                + " + Payoff - " +  vNewPrinciple
                + " + Loan Balance " + p ;
*/

//works
        sOut = sOut + "<tr>";
        sOut = sOut + "<td>" + i + "</td>";
        sOut = sOut + "<td>" + p + "</td>";
        sOut = sOut + "<td>" + vMonthlyPayment + "</td>";
        sOut = sOut + "<td>" + vPrinciplePaid + "</td>";
        sOut = sOut + "<td>" + vInterestPaid + "</td>";
        //sOut = sOut + "<td>" + vNewPrinciple + "</td>";
        sOut = sOut + "</tr>";

        return sOut;
    }






    public String printHeader(){
        Log.i(TAG, "printHeader") ;
        // added - start
            String sOut ;
        // added - end

        int i;
        sOut = "<tr>";
        sOut = sOut + "<td colspan='5'>Amortization Schedule for  Borrower</td>";
        sOut = sOut + "</tr>";

        //sOut = "\nAmortization Schedule for  Borrower" ;
        //sOut = "<br>Amortization Schedule for  Borrower" ;

        //for(i=0;i<62;i++)  sOut = sOut + "-" ;
        //sOut = sOut + sOut.format("\n%-8s%-12s%-10s%-10s%-10s%-12s"," ","Old","Monthly","Interest","Principle","New","Balance");
        //sOut = sOut + sOut.format("\n%-8s%-12s%-10s%-10s%-10s%-12s\n\n","Month","Balance","Payment","Paid","Paid","Balance");

        sOut = sOut + "<tr>";
        sOut = sOut + "<td>#</td>" ;
        sOut = sOut + "<td>Balance</td>" ;
        sOut = sOut + "<td>Payment</td>" ;
        sOut = sOut + "<td>To Principle</td>" ;
        sOut = sOut + "<td>To Interest</td>" ;
        sOut = sOut + "</tr>";

/*
//works
        sOut = sOut + " # - "
                + "Payment - "
                + "To Principle - "
                + "To Interest - "
                + "Payoff - "
                + "Balance <br> " ;
*/

/*
//works
        sOut = sOut + sOut.format(
                "\n%-6s%-12s%-12s%-12s%-12s%-12s\n\n"
                , " # - "
                , "Payment - "
                , "To Principle - "
                , "To Interest - "
                , "Payoff - "
                , "Balance "
            );
*/

/*
//works
        sOut = sOut + sOut = " # - "
                + "Payment - "
                + "To Principle - "
                + "To Interest - "
                + "Payoff - "
                + "Balance " ;
*/
        return sOut;
    }






    public String printTotals(){
        Log.i(TAG, "printTotals") ;
        // added - start
            String sOut ;
        // added - end

        int i;

        sOut = "<hr>" ;
        sOut = sOut + "<table border='1' > ";
        sOut = sOut + "<tr>";
        //sOut = "\nTotal costs of loan for  Borrower\n" ;
        //sOut = "<br>Total costs of loan for  Borrower<br>" ;
        sOut = sOut + "<td colspan='3' >Total costs of loan</td>" ;
        sOut = sOut + "</tr>";

        sOut = sOut + "<tr>";
        sOut = sOut + "<td>Monthly Payment</td>";
        sOut = sOut + "<td>Total Principle Paid</td>";
        sOut = sOut + "<td>Total Interest Paid</td>";
        sOut = sOut + "<td>Total Amount Paid</td>";
        sOut = sOut + "</tr>";

        sOut = sOut + "<tr>";
        sOut = sOut + "<td>" + pMonthlyPayment + "</td>";
        sOut = sOut + "<td>" + pTotalPrinciple + "</td>";
        sOut = sOut + "<td>" + pTotalInterest + "</td>";
        sOut = sOut + "<td>" + pTotalPaid + "</td>";
        sOut = sOut + "</tr>";

        sOut = sOut + "</table> ";
        sOut = sOut + "<hr>" ;


//        for(i=0;i<62;i++)  sOut = sOut + "-" ;
/*
// works
        sOut = sOut + sOut.format(
                "\n%-12s%-12s%-12s\n\n"
                , "Monthly Payment - "
                , "Total Amount Paid - "
                , "Total Interest Paid"
        );
*/

/*
//works
        sOut = " # - "
                + "Payment - "
                + "To Principle - "
                + "To Interest - "
                + "Payoff - "
                + "Balance " ;
*/
        return sOut;
    }









/*
// This method is called at button click because we assigned the name to the
// "OnClick property" of the button
    public void ocCalculatePayment(View view)
    {
        /// D --- validate values --- start
        if (peLoanAmount.getText().length() == 0)
        {
            Toast.makeText(this, "Please enter a valid Loan Amount",
                    Toast.LENGTH_LONG).show();
            return;
        }

        if (peInterestRate.getText().length() == 0)
        {
            Toast.makeText(this, "Please enter a valid Interest Rate",
                    Toast.LENGTH_LONG).show();
            return;
        }

        if (peNumberOfPayments.getText().length() == 0)
        {
            Toast.makeText(this, "Please enter a valid Number Of Payments",
                    Toast.LENGTH_LONG).show();
            return;
        }

/*
        if (peMonthlyPaymentIPlanToMake.getText().length() == 0)
        {
            Toast.makeText(this, "Please enter a valid Monthly Payment I Plan To Make",
                    Toast.LENGTH_LONG).show();
            return;
        }

        if ( Double.parseDouble(peLoanAmount.getText().toString()) <= 0 )
        {
            Toast.makeText(this, "Please enter a valid Loan Amount",
                    Toast.LENGTH_LONG).show();
            return;
        }

        if ( Double.parseDouble(peInterestRate.getText().toString()) <= 0 )
        {
            Toast.makeText(this, "Please enter a valid Interest Rate",
                    Toast.LENGTH_LONG).show();
            return;
        }

        if ( Double.parseDouble(peNumberOfPayments.getText().toString()) <= 0 )
        {
            Toast.makeText(this, "Please enter a valid Number Of Payments",
                    Toast.LENGTH_LONG).show();
            return;
        }

        if ( Double.parseDouble(peMonthlyPaymentIPlanToMake.getText().toString()) <= 0 )
        {
            Toast.makeText(this, "Please enter a valid Monthly Payment I Plan To Make",
                    Toast.LENGTH_LONG).show();
            return;
        }
* /
    /// D --- end




    /// A --- setup input values --- start
    double pLoanAmount = Double.parseDouble(peLoanAmount.getText().toString());
    double vInterestRate = (   Double.parseDouble(peInterestRate.getText().toString())    / 100   );
    double pNumberOfPayments = Double.parseDouble(peNumberOfPayments.getText().toString());

/*
        double pCalculatedPayment = Double.parseDouble(peCalculatedPayment.getText().toString());
        double pTotalInterestPaid = Double.parseDouble(peTotalInterestPaid.getText().toString());
        double pTotalAmountPaid = Double.parseDouble(peTotalAmountPaid.getText().toString());

        double vMonthlyPaymentIPlanToMake = Double.parseDouble(peMonthlyPaymentIPlanToMake.getText().toString());
* /
    /// A --- end


    // B --- calculate values --- start
    //peLoanAmount.setText(String.valueOf(calculateLoanAmount(pLoanAmount))) ;
    //peInterestRate.setText(String.valueOf(calculateInterestRate(vInterestRate))) ;
    //peNumberOfPayments.setText(String.valueOf(calculateNumberOfPayments(pNumberOfPayments))) ;

/*
        pCalculatedPayment = calculateCalculatedPayment( pLoanAmount , vInterestRate , pNumberOfPayments ) ;
        pTotalInterestPaid = calculateTotalInterestPaid( pLoanAmount , vInterestRate , pNumberOfPayments ) ;
        pTotalAmountPaid = calculateTotalAmountPaid( pLoanAmount , vInterestRate , pNumberOfPayments ) ;
* /


/*
        peCalculatedPayment.setText(String.valueOf( pCalculatedPayment )) ;
        peTotalInterestPaid.setText(String.valueOf( pTotalInterestPaid )) ;
        peTotalAmountPaid.setText(String.valueOf( pTotalAmountPaid )) ;
* /
    //peMonthlyPaymentIPlanToMake.setText(String.valueOf(calculateMonthlyPaymentIPlanToMake(vMonthlyPaymentIPlanToMake))) ;

    // B --- end




    // C --- return values to interface --- start
    peLoanAmount = (EditText) findViewById(R.id.txtLoanAmount);
    peInterestRate = (EditText) findViewById(R.id.txtInterestRate);
    peNumberOfPayments = (EditText) findViewById(R.id.txtNumberOfPayments);

/*
        peCalculatedPayment = (EditText) findViewById(R.id.txtCalculatedPayment);
        peTotalInterestPaid = (EditText) findViewById(R.id.txtTotalInterestPaid);
        peTotalAmountPaid = (EditText) findViewById(R.id.txtTotalAmountPaid);
        peMonthlyPaymentIPlanToMake = (EditText) findViewById(R.id.txtMonthlyPaymentIPlanToMake);
* /
    // C --- end

} // ocCalculatePayment




    private double calculateCalculatedPayment(
            double vLoanAmount ,
            double vInterestRate ,
            double vNumberOfPayments
    )
    {
        double pval ;

        pval =vLoanAmount * vInterestRate *   Math.pow(1+ vInterestRate ,vNumberOfPayments)/( Math.pow(1+ vInterestRate ,vNumberOfPayments)-1 )  ;

/*
        vLoanAmount * vInterestRate *
                (
                        Math.pow(  (1+vInterestRate) , vNumberOfPayments )
                        /
                        Math.pow(  (1+vInterestRate) , vNumberOfPayments ) - 1
                )
*/

/*
        pval = (  vInterestRate * (vLoanAmount)    )
                /
                ( 1 - Math.pow( ( 1 + vInterestRate )  , vNumberOfPayments) ) ;
* /

        return ( pval );
    }



    private double calculateTotalInterestPaid (
            double vLoanAmount ,
            double vInterestRate ,
            double vNumberOfPayments
    )
    {
        return (2);
    }

    private double calculateTotalAmountPaid (
            double vLoanAmount ,
            double vInterestRate ,
            double vNumberOfPayments
    )
    {
        return (3);
    }
*/




}
