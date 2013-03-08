package com.imalur.battery100discharger;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Toast;

public class MainScreen extends Activity {

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        
        if (!RootUtils.isDeviceRooted()){
        	findViewById(R.id.btnB8).setEnabled(false);
        	findViewById(R.id.cbShow08).setEnabled(false);
        	findViewById(R.id.btn08).setEnabled(false);
        	findViewById(R.id.btnReboot).setEnabled(false);
        	Toast.makeText(this, R.string.toast_not_rooted, Toast.LENGTH_LONG).show();
        }
    }
    
    public void onButtonClick(View v){
    	switch (v.getId()) {
    	case R.id.cbShow08:
    		v.setVisibility(View.GONE);
    		findViewById(R.id.btn08).setVisibility(View.VISIBLE);
    		break;
		case R.id.btnInstructions:
			showInstructionsDialog();
			break;
		case R.id.btnB8:
			showB8Dialog();
			break;
		case R.id.btn08:
			show08Dialog();
			break;
		case R.id.btnReboot:
			showRebootDialog();
			break;
		default:
			break;
		}
    }

    private void showInstructionsDialog(){
    	startActivity(new Intent(this,  InstructionsScreen.class));
//    	String message = getResources().getString(R.string.about_dialog_message);
//    	Spanned htmlMessage = Html.fromHtml(message);
//    	
//    	new AlertDialog.Builder(this)
//    	.setCancelable(true)
//    	.setTitle(getResources().getString(R.string.about_dialog_title))
//    	.setMessage(htmlMessage)
//    	.setPositiveButton(android.R.string.ok, null)
//    	.create()
//    	.show();
    }
    
    private void showB8Dialog(){
    	String message = getResources().getString(R.string.b8_dialog_message);
    	Spanned htmlMessage = Html.fromHtml(message);
    	
    	new AlertDialog.Builder(this)
    	.setCancelable(true)
    	.setTitle(getResources().getString(R.string.b8_dialog_title))
    	.setMessage(htmlMessage)
    	.setPositiveButton(android.R.string.ok, 
    			new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if (RootUtils.runB8cmd(MainScreen.this))
							findViewById(R.id.btnB8).setEnabled(false);
						dialog.dismiss();
					}
				})
		.setNegativeButton(android.R.string.cancel, null)
		.create()
		.show();
    }

    private void show08Dialog(){
    	String message = getResources().getString(R.string._08_dialog_message);
    	Spanned htmlMessage = Html.fromHtml(message);
    	
    	new AlertDialog.Builder(this)
    	.setCancelable(true)
    	.setTitle(getResources().getString(R.string._08_dialog_title))
    	.setMessage(htmlMessage)
    	.setPositiveButton(android.R.string.ok, 
    			new DialogInterface.OnClickListener() {
    		
    		@Override
    		public void onClick(DialogInterface dialog, int which) {
    			if (RootUtils.run08cmd(MainScreen.this))
    				findViewById(R.id.btn08).setEnabled(false);
    		}
    	})
    	.setNegativeButton(android.R.string.cancel, null)
    	.create()
    	.show();
    }

    private void showRebootDialog(){
    	String message = getResources().getString(R.string.reboot_dialog_message);
    	Spanned htmlMessage = Html.fromHtml(message);
    	
    	new AlertDialog.Builder(this)
    	.setCancelable(true)
    	.setTitle(getResources().getString(R.string.reboot_dialog_title))
    	.setMessage(htmlMessage)
    	.setPositiveButton(android.R.string.ok, 
    			new DialogInterface.OnClickListener() {
    		
    		@Override
    		public void onClick(DialogInterface dialog, int which) {
    			RootUtils.runRebootcmd(MainScreen.this);
    		}
    	})
    	.setNegativeButton(android.R.string.cancel, null)
    	.create()
    	.show();
    }



}
