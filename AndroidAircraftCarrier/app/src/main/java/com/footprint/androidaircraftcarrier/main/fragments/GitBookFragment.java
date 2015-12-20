//package com.footprint.androidaircraftcarrier.main.fragments;
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.webkit.WebSettings;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//
//import com.footprint.androidaircraftcarrier.app.ShellFragment;
//
///**
// * Created by liquanmin on 15/12/18.
// */
//public class GitBookFragment extends ShellFragment {
//    private WebView webView;
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        webView = new WebView(getContext());
//        configWebView();
//        return webView;
//    }
//
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        WebSettings webSettings = webView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//        webSettings.setDomStorageEnabled(true);
//        webSettings.setUseWideViewPort(true);
//        webView.loadUrl("http://www.muzileecoding.com/");
//    }
//
//    private void configWebView() {
//        webView.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//        });
//    }
//}