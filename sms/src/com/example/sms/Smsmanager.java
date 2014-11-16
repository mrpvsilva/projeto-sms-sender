package com.example.sms;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Base64;
import android.widget.Toast;

public class Smsmanager extends AsyncTask<String, String, String> {

	private Activity actvity;

	public Smsmanager(Activity actvity) {
		this.actvity = actvity;
	}

	@Override
	protected String doInBackground(String... params) {

		String msg = "";
		try {

			String url = "http://192.168.100.4/empresasms/enviarsms";
			String user = "userA";
			String pass = "123456";
			String userpass = user + ":" + pass;
			String encoding = Base64.encodeToString(userpass.getBytes(),
					userpass.length());
			String ddd = params[0]; // et_ddd.getText().toString();
			String numero = params[1]; // et_numero.getText().toString();
			String txt = params[2].replace(' ', '+'); // et_txtsms.getText().toString().replace(' ',
														// '+');

			String dados = "ddd=" + ddd + "&numero=" + numero + "&txtsms="
					+ txt;

			URL uri = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) uri
					.openConnection();

			connection.setRequestMethod("POST");
			connection.setRequestProperty("Authorization", "Basic " + encoding);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			DataOutputStream out = new DataOutputStream(
					connection.getOutputStream());
			out.writeBytes(dados);
			out.flush();
			out.close();

			connection.connect();

			int responsecode = connection.getResponseCode();
			if (responsecode == 200) {

				msg = "sms enviado com sucesso";

			} else {
				msg = connection.getResponseMessage();
			}
		} catch (IOException ex) {
			msg = ex.getMessage();
		}

		return msg;
	}

	protected void onPostExecute(String result) {

		Toast.makeText(actvity, result, Toast.LENGTH_LONG).show();

	}
}
