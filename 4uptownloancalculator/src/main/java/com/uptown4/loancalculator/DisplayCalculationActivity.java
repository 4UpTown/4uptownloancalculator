package com.uptown4.loancalculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.webkit.WebView;

public class DisplayCalculationActivity extends Activity {

    private WebView mWebView;
    public StringBuilder mMathString;
    //private ButtonClickListener mClickListener;
    private final String TAG = "DisplayCalculationActivity" ;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate(Bundle savedInstanceState)") ;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_calculation);

        // Make sure we're running on Honeycomb or higher to use ActionBar APIs
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Get the message from the intent
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.passToIntent1);
        //String message2 = intent.getStringExtra(MainActivity.passToIntent2) + " 00 " + message ;


        //WebView wb = new WebView(getApplicationContext());
        WebView wb = (WebView)findViewById(R.id.wvResult);



        //WebView wv = (WebView)this.findViewById(R.id.wvResult);
        wb.getSettings().setJavaScriptEnabled(true);


        String sVal ;

        sVal = "" ;

        sVal = "<html><body>";
        //sVal = sVal + "<script type=\"text/javascript\">document.write('";
        //sVal = sVal + mMathString.toString();
        ////builder.append(pVal);
        //sVal = sVal + "');";
        //sVal = sVal + "document.write('<br />=' + eval(\"";
        //sVal = sVal + mMathString.toString();
        ////builder.append(mMathString.toString());
        //sVal = sVal + "\"));</script>";

        sVal = sVal + "<hr><p>" + message + "</p><hr>" ;

        sVal = sVal + "</body></html>";

        wb.loadDataWithBaseURL(null, sVal , "text/html", "utf-8", null);
        //wb.loadUrl(message);

//        setContentView(wb);


/*2222
        String sVal ;

        sVal = "" ;

        sVal = "<html><body>";
        sVal = sVal + "<script type=\"text/javascript\">document.write('";
        sVal = sVal + mMathString.toString();
        //builder.append(pVal);
        sVal = sVal + "');";
        sVal = sVal + "document.write('<br />=' + eval(\"";
        sVal = sVal + mMathString.toString();
        //builder.append(mMathString.toString());
        sVal = sVal + "\"));</script>";
        sVal = sVal + "</body></html>";

        mWebView.loadData( sVal , "application/xhtml", "UTF-8");
2222*/

        //setContentView(R.layout.activity_display_calculation);



        // Enable javascript for the view
        //mWebView = (WebView)findViewById(R.id.wvResult);

        //WebView myWebView = (WebView) findViewById(R.id.wvResult);
        //WebSettings webSettings = mWebView.getSettings();
        //webSettings.setJavaScriptEnabled(true);

        //mWebView.getSettings().setJavaScriptEnabled(true);
        // Set the listener for all the buttons
        //mClickListener = new ButtonClickListener();

        //updateWebView() ;


/*
        // Create the text view
        TextView textView = (TextView)findViewById(R.id.lblResult);

        textView.setTextSize(14);
        textView.setText("message");
//        textView.setText(message + message2);
*/
        // Set the text view as the activity layout
        //setContentView(textView);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i(TAG, "onOptionsItemSelected(MenuItem item)") ;
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }







    public void updateWebView() {
        Log.i(TAG, "updateWebView()") ;

        StringBuilder builder = new StringBuilder();

        builder.append("<html><body>");
        builder.append("<script type=\"text/javascript\">document.write('");
        builder.append(mMathString.toString());
        //builder.append(pVal);
        builder.append("');");
        builder.append("document.write('<br />=' + eval(\"");
        builder.append(mMathString.toString());
        //builder.append(mMathString.toString());
        builder.append("\"));</script>");
        builder.append("</body></html>");

        mWebView.loadData(builder.toString(), "application/xhtml", "UTF-8");
    }



/*
    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case 98:
                    if(mMathString.length() > 0)
                        mMathString.deleteCharAt(mMathString.length()-1);
                    break;
                case 99:
                    if(mMathString.length() > 0)
                        mMathString.delete(0, mMathString.length());
                    break;
                default:
                    mMathString.append(((Button) v).getText());
            }

            updateWebView();
        }

    }
*/

}
