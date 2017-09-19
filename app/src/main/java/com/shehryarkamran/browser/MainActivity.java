package com.shehryarkamran.browser;


import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText text;
    private WebView wv;
    private Button back,forward,go;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (EditText)findViewById(R.id.Text);
        wv = (WebView)findViewById(R.id.webview);
        back = (Button)findViewById(R.id.btn_back);
        forward = (Button)findViewById(R.id.btn_forward);
        go = (Button)findViewById(R.id.btn_go);
        wv.loadUrl("https://www.google.com");
        wv.getSettings().setJavaScriptEnabled(true);
        wv.setWebViewClient(new ourWebClient());
        text.setCursorVisible(false);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             if(wv.canGoBack()) {
                 wv.goBack();
             }
            }
        });
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(wv.canGoForward()) {
                wv.goForward();
            }
            else {
                forward.setEnabled(false);
            }
            }
        });
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = text.getText().toString();
                if(!url.equals("")) {
                    wv.loadUrl("http://"+url);
                }
            }
        });
    }
    class ourWebClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view,String url) {
            wv.loadUrl(url);
            return true;
           // return super.shouldOverrideUrlLoading(view,url);
        }
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();

            //moveTaskToBack(false);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void exitByBackKey() {

        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage("Do you want to exit application?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {

                        finish();
                        //close();


                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                })
                .show();

    }
}