package org.hsc.silk.hbc;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.BarcodeTest.IntentIntegrator;
import com.example.BarcodeTest.IntentResult;


public class MainActivity extends Activity {

	AssetManager assetManager;
	
	String barcode = "";
	EditText input; 
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 


	}
	private static final int DIALOG_ALERT = 10;
	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		switch (id) {
	    case DIALOG_ALERT:
	    // Create out AlterDialog
	    Builder builder = new AlertDialog.Builder(this);
	    builder.setTitle(R.string.promptTitle);
	    builder.setMessage(R.string.promptsText);
	    builder.setCancelable(true);
	    // Set an EditText view to get user input
	    input= new EditText(this);
	 	input.setText(barcode);
	 	builder.setView(input);
	    builder.setPositiveButton(R.string.okBtn, new OkOnClickListener());
	    builder.setNegativeButton(R.string.cancelBtn, new CancelOnClickListener());
	    AlertDialog dialog = builder.create();
	    dialog.show();
	   }
	  return super.onCreateDialog(id);

	}

	public void startScan(View view){
//		showDialog(DIALOG_ALERT);
		barcode="";
		IntentIntegrator.initiateScan(this);
	}
	public void inputWithKeyboard(View view){
		barcode="";
		showDialog(DIALOG_ALERT);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case IntentIntegrator.REQUEST_CODE: {
			if (resultCode != RESULT_CANCELED) {
				IntentResult scanResult = IntentIntegrator.parseActivityResult(
						requestCode, resultCode, data);
				if (scanResult != null) {
					barcode = scanResult.getContents();
					showDialog(DIALOG_ALERT);
				}
			}
		}
		}
	}
	
	private final class CancelOnClickListener implements
	  DialogInterface.OnClickListener {
	  public void onClick(DialogInterface dialog, int which) {
		  barcode = "";
		  
	  }
	}

	private final class OkOnClickListener implements
	    DialogInterface.OnClickListener {
	  public void onClick(DialogInterface dialog, int which) {
		  barcode = input.getText().toString();
		  Intent intent = new Intent(MainActivity.this, WebViewHalalinfo.class);
		  intent.putExtra("barcode", barcode);
		  startActivity(intent);
	  }
	} 
}
