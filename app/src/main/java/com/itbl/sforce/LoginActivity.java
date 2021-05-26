package com.itbl.sforce;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.res.ResourcesCompat;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends Activity {

    TextView txt_remember, LogUser,LogPassword, txt_login, itbl;
    Button btnLogin;
    CheckBox remember;
    Animation btnAnim;
    public static String newuser1, newpass1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        txt_login =  findViewById(R.id.txt_login);
        itbl =  findViewById(R.id.itbl);
        LogUser= findViewById(R.id.logId);
        LogPassword=  findViewById(R.id.logpass);
        remember=  findViewById(R.id.remember);
        txt_remember=  findViewById(R.id.txt_remember);
        btnLogin=  findViewById(R.id.btnlogin);

        txt_login.setTypeface(ResourcesCompat.getFont(this, R.font.autour_one));
        itbl.setTypeface(ResourcesCompat.getFont(this, R.font.kaushan_script));
        btnLogin.setTypeface(ResourcesCompat.getFont(this, R.font.autour_one));

        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.btn_bounce);

        SharedPreferences loginPreferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String checkbox = loginPreferences.getString("remember","");

        if (checkbox.equals("true")){
            Intent intent = new Intent(LoginActivity.this,Dashboard.class);
            startActivity(intent);

        }else if (checkbox.equals("false")){
            Toast.makeText(LoginActivity.this,"Please Login",Toast.LENGTH_LONG).show();

        }

        txt_remember.setTypeface(ResourcesCompat.getFont(this, R.font.aldrich));

        btnLogin.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (LogUser.getText().toString().isEmpty() || LogPassword.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Fill The Empty Field", Toast.LENGTH_LONG).show();

                } else {
                    Fetch task = new Fetch(LoginActivity.this);
                    task.execute();
                }

            }

        });


        //Remember me
        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(compoundButton.isChecked()){

                    SharedPreferences loginPreferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor preferencesEditor = loginPreferences.edit();
                    preferencesEditor.putString("remember", "true");
                    preferencesEditor.apply();
                    Toast.makeText(LoginActivity.this,"Checked",Toast.LENGTH_SHORT).show();

                }else if(!compoundButton.isChecked()){

                    SharedPreferences loginPreferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor preferencesEditor = loginPreferences.edit();
                    preferencesEditor.putString("remember", "false");
                    preferencesEditor.apply();
                    Toast.makeText(LoginActivity.this,"Unchecked",Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

    private class Fetch  extends AsyncTask<Void,Void,String> {


        private Activity context;

        @SuppressWarnings("unused")
        ProgressDialog pd=null;


        public Fetch(Activity context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            pd = ProgressDialog.show(LoginActivity.this, "Login Processing",
                    "Please wait...");

        }

        @Override
        protected String doInBackground(Void... params) {

            String result = "" ;
            BufferedReader reader = null;
            StringBuilder stringBuilder ;

            try {
                URL url = new URL("");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept","application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);

                JSONObject jsonParam = new JSONObject();
                jsonParam.put("id", LogUser.getText().toString().trim());
                jsonParam.put("pass", LogPassword.getText().toString().trim());

                Log.i("JSON", jsonParam.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(jsonParam.toString());

                os.flush();
                os.close();

                Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                Log.i("MSG" , conn.getResponseMessage());
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                stringBuilder = new StringBuilder();

                String line = null;
                while ((line = reader.readLine()) != null)
                {
                    stringBuilder.append(line + "\n");
                }
                result=stringBuilder.toString();
                conn.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }// catch (Exception e) {
            // Log.e("log_tag", "Error in http connection!!" + e.toString());

            // }

            try {
                JSONArray jArray = new JSONArray(result.toString());
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject json_data = jArray.getJSONObject(i);
                    newuser1= json_data.getString("USERNAME");
                    newpass1 = json_data.getString("PASSWORD");

                }

            }
            catch (Exception e) {
                Log.e("log_tag", "Error in http connection!!" + e.toString());

            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {

            pd.dismiss();
            startActivity(new Intent(LoginActivity.this,Dashboard.class));

        }
    }
}
