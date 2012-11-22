package org.hsc.silk.hbc;

import org.hsc.silk.App;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

public class WebViewHalalinfo extends Activity {

	App app;
	String url;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.webview_halalinfo);
		String barcode = getIntent().getStringExtra("barcode");
		app = (App)getApplication();
		url = app.getServerUrl();
		url+="?barcode=";
		url+=barcode; //911371440555";

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
//		System.out.println("url : " + url);
		WebView webview = (WebView)findViewById(R.id.webView1);
		webview.getSettings().setJavaScriptEnabled(true);
		if(!isNetworkConnected()){
			Toast.makeText(this, R.string.internetError, Toast.LENGTH_LONG).show();
			finish();
			
		}else{
			
			webview.loadUrl(url);
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

	}
	private boolean isNetworkConnected() {
		  ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		  NetworkInfo ni = cm.getActiveNetworkInfo();
		  if (ni == null) {
		   // There are no active networks.
		   return false;
		  } else
		   return true;
		 }
}
