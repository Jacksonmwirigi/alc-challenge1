package com.techweezy.alc4app;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import java.net.URL;
import java.util.Objects;

public class AboutAlc extends AppCompatActivity {
    WebView myWebView;
    ProgressBar progressBar1;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.about_activity);
        setContentView(R.layout.activity_about_alc);

        progressBar1=(ProgressBar)findViewById(R.id.aboutprogressBar);

        /***  Setting the webView and url to be loaded.**/
        myWebView=(WebView)findViewById(R.id.aboutWebView) ;
        myWebView.setWebViewClient(new CustomWebClient());
        WebSettings settings= myWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setDomStorageEnabled(true);
        myWebView.loadUrl("https://andela.com/alc");

    }



    /***Implementing a custom webClient **/
    private class CustomWebClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl (url);
            return true;
        }
        @Override
        public void onPageStarted(WebView webview, String url, Bitmap favicon) {

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            progressBar1.setVisibility(View.GONE);

        }
        /** This method Resolves SSL errors **/
        @Override
        public void onReceivedSslError(WebView myWebView, final SslErrorHandler handler, SslError error) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(AboutAlc.this);
            builder.setMessage("SSL Certificate Error, Tap Continue to Proceed!");
            builder.setPositiveButton("continue", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    handler.proceed();
                }
            });
            builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    handler.cancel();
                }
            });
            final AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    @Override
    public void onBackPressed() {
        if (myWebView.canGoBack()) {
            myWebView.goBack();
            return;
        }
        super.onBackPressed();
    }
}
