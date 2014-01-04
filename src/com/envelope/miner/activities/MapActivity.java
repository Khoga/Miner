package com.envelope.miner.activities;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.envelope.miner.Miner;
import com.envelope.miner.R;
import com.google.gson.Gson;


public class MapActivity extends ParentActivity {

    private SharedPreferences prefs;
    private Editor editor;
    private Dialog dialog;
    private Context context;
	


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);

        context = getApplicationContext();
        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = prefs.edit();

        checker();
    }

    private void FirstLoginDialog()
    {
    	dialog = new Dialog(MapActivity.this);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.first_login_dialog);
        dialog.setCancelable(false);
        
        final EditText NickET = (EditText) dialog.findViewById(R.id.NickET);
        final EditText OsobaPolecajacaET = (EditText) dialog.findViewById(R.id.OsobapolecajacaET);
        Button Register = (Button) dialog.findViewById(R.id.FullRegBTN);
        Button Login = (Button) dialog.findViewById(R.id.LoginBTN);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            	if(NickET.getText().toString().matches("")) Toast.makeText(context, "Pole Nick jest wymagane", Toast.LENGTH_LONG).show();
            	else
            	{
            		String nick = NickET.getText().toString();
            		String osobaPolecajaca = OsobaPolecajacaET.getText().toString();
            		
            		miner = new Miner(nick);
            		Gson gson = new Gson();
            	    String json = gson.toJson(miner);
            	    editor.putString("Miner", json);
            	    editor.commit();
            	    
            	    FillRightLayout();
            	    dialog.cancel();
            	}
            }
        });
        
        dialog.show();
    }
    
    public void checker()
    {
    	if(miner == null) 
    		FirstLoginDialog();
    	else FillRightLayout();
    }
    
    public void FillRightLayout()
    {
    	TextView tv = (TextView) findViewById(R.id.NameTV);
        tv.setText(miner.Name);
    }
    
    

    public void RockClick(View v)
    {
        Intent intent = new Intent(MapActivity.this, RockActivity.class);
        startActivity(intent);


    }




}
