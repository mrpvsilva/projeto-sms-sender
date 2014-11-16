package com.example.sms;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	private Button enviar;
	private EditText et_ddd;
	private EditText et_numero;
	private EditText et_txtsms;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		enviar = (Button) findViewById(R.id.btn_enviar);
		enviar.setOnClickListener(EnviarSMS);

		et_ddd = (EditText) findViewById(R.id.et_ddd);
		et_numero = (EditText) findViewById(R.id.et_numero);
		et_txtsms = (EditText) findViewById(R.id.et_txtsms);

	}

	private OnClickListener EnviarSMS = new OnClickListener() {
		@Override
		public void onClick(View v) {
			 String ddd = et_ddd.getText().toString();
			String numero =  et_numero.getText().toString();
			String txt =  et_txtsms.getText().toString();

			new Smsmanager(MainActivity.this).execute(ddd,numero,txt);

		}
	};

}
