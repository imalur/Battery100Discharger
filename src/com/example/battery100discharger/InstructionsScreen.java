package com.example.battery100discharger;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.TextView;

public class InstructionsScreen extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructions_screen);
        
        String message = getResources().getString(R.string.about_dialog_message);
    	Spanned htmlMessage = Html.fromHtml(message);
    	((TextView) findViewById(R.id.tvInstructions)).setText(htmlMessage);
    }

    public void onButtonClick(View v){
    	finish();
    }
}
