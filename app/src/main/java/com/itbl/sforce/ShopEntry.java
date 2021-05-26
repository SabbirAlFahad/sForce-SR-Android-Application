package com.itbl.sforce;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShopEntry extends AppCompatActivity {
    Spinner spinnerdiv, spinnerdis, spinnerthana;
    Button btn_shopEntrySave;

    private static final String TAG = "ShopEntry";

    //calender
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    EditText cPerson1, cPerson2, sMobile1, sMobile2, sAddress1, sAddress2,
            sWebAddress, sEmail, sTradeLIC, sVatNo, sCreatedBy;
    TextView sCreateDate;
    String strcPerson1, strcPerson2, strsMobile1, strsMobile2, strsAddress1,
            strsAddress2, strsWebAddress, strsEmail, strsTradeLIC, strsVatNo, strsCreatedBy, strsCreateDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_entry);

        //EditText
        cPerson1 =  findViewById(R.id.cPerson1);
        cPerson2 =  findViewById(R.id.cPerson2);
        sMobile1 =  findViewById(R.id.sMobile1);
        sMobile2 =  findViewById(R.id.sMobile2);
        sAddress1 =  findViewById(R.id.sAddress1);
        sAddress2 =  findViewById(R.id.sAddress2);
        sWebAddress =  findViewById(R.id.sWebAddress);
        sEmail =  findViewById(R.id.sEmail);
        sTradeLIC =  findViewById(R.id.sTradeLIC);
        sVatNo =  findViewById(R.id.sVatNo);
        sCreatedBy =  findViewById(R.id.sCreatedBy);
        sCreateDate =  findViewById(R.id.sCreateDate);


        //Spinner
        spinnerdiv =  findViewById(R.id.spinner1);
        spinnerdis =  findViewById(R.id.spinner2);
        spinnerthana =  findViewById(R.id.spinner3);

        //Button
        btn_shopEntrySave =  findViewById(R.id.btn_shopEntrySave);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            NotificationChannel channel = new NotificationChannel("New Notification","Shop Entry Notification",
                    NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);

        }


        final String[] itemsDiv = new String[] { "Select District Code", "9845", "2584", "0154" };
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,itemsDiv);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerdiv.setAdapter(aa);
        spinnerdiv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String divCode=itemsDiv[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> staticAdapter2 = ArrayAdapter.createFromResource(this, R.array.demo2_array,
                android.R.layout.simple_spinner_item);

        staticAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerthana.setAdapter(staticAdapter2);

        String[] items = new String[] { "Select District Code", "9845", "2584", "0154" };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);

        spinnerdis.setAdapter(adapter);

        spinnerdis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        //calender
        sCreateDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        ShopEntry.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                sCreateDate.setText(date);
            }
        };

        btn_shopEntrySave.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkDataEntered();
                strcPerson1=cPerson1.getText().toString().trim();
                strcPerson2=cPerson2.getText().toString().trim();
                strsMobile1=sMobile1.getText().toString().trim();
                strsMobile2=sMobile2.getText().toString().trim();
                strsAddress1=sAddress1.getText().toString().trim();
                strsAddress2=sAddress2.getText().toString().trim();
                strsWebAddress=sWebAddress.getText().toString().trim();
                strsEmail=sEmail.getText().toString().trim();
                strsTradeLIC=sTradeLIC.getText().toString().trim();
                strsVatNo=sVatNo.getText().toString().trim();
                strsCreatedBy=sCreatedBy.getText().toString().trim();
                strsCreateDate=sCreateDate.getText().toString().trim();

                shopEntry task = new shopEntry(ShopEntry.this);
                task.execute();

                goToDashboard();

                Intent intent = new Intent(ShopEntry.this, Profile.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(ShopEntry.this, 0, intent, 0);


                NotificationCompat.Builder builder = new NotificationCompat.Builder(ShopEntry.this,"New Notification");

                builder.setContentTitle("new notification");
                builder.setContentText("Shop Entered Successfully");
                builder.setSmallIcon(R.drawable.notifications_24dp);
                builder.setContentIntent(pendingIntent);
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(ShopEntry.this);

                managerCompat.notify(1,builder.build());

            }

        });
    }

    private class shopEntry extends AsyncTask<Void,Void,String> {


        private Activity context;

        @SuppressWarnings("unused")
        ProgressDialog pd=null;

        public shopEntry(Activity context) {
            this.context = context;
        }
        @Override
        protected void onPreExecute() {
            pd = ProgressDialog.show(ShopEntry.this, "Processing",
                    "Please wait a little");
        }
        @Override
        protected String doInBackground(Void... params) {
                  /*final List<Pair<String, String >> postParameters = new ArrayList<>();
                  postParameters.add (new Pair("id", LogEmail.getText().toString().trim()));
                  postParameters.add (new Pair("pass", LogPassword.getText().toString().trim()));*/

            String result = "0" ;
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
                jsonParam.put("cPerson1",strcPerson1);
                jsonParam.put("cPerson2", strcPerson2);
                jsonParam.put("sMobile1", strsMobile1);
                jsonParam.put("sMobile2", strsMobile2);
                jsonParam.put("sAddress1", strsAddress1);
                jsonParam.put("sAddress2", strsAddress2);
                jsonParam.put("sWebAddress", strsWebAddress);
                jsonParam.put("sEmail", strsEmail);
                jsonParam.put("sTradeLIC", strsTradeLIC);
                jsonParam.put("sVatNo", strsVatNo);
                jsonParam.put("sCreatedBy", strsCreatedBy);
                jsonParam.put("sCreateDate", strsCreateDate);

                Log.i("JSON", jsonParam.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
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
                result=result.replaceAll("\n","");
                conn.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return result;

        }

        @Override
        protected void onPostExecute(String result) {

            pd.dismiss();
            int r= Integer.parseInt(result);
        }
    }

    //Data Validation
    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }


    private void checkDataEntered() {

        if (isEmail(sEmail) == false){
            sEmail.setError("Enter A Valid Email!");
        }

        if (isEmpty(sTradeLIC)) {
            Toast t = Toast.makeText(this, "Enter Create Date", Toast.LENGTH_SHORT);
            t.show();
        }

        if (isEmpty(sVatNo)) {
            Toast t = Toast.makeText(this, "Enter VAT Reg. No. First", Toast.LENGTH_SHORT);
            t.show();
        }

        if (isEmpty(sAddress1)) {
            Toast t = Toast.makeText(this, "Fill Shop Address", Toast.LENGTH_SHORT);
            t.show();
        }

        if (isEmpty(cPerson1)) {
            Toast t = Toast.makeText(this, "Fill Contract Person Data", Toast.LENGTH_SHORT);
            t.show();
        }
    }

    public void PostData(View view) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(" ")   //JSON LINK
                .addConverterFactory(GsonConverterFactory.create()).build();

        ShopEntryInt shopEntryInt = retrofit.create(ShopEntryInt.class);

        ShopEntryData shopEntryData = new ShopEntryData(cPerson1.getText().toString(), cPerson2.getText().toString(),
                sMobile1.getText().toString(), sMobile2.getText().toString(),
                sAddress1.getText().toString(), sAddress2.getText().toString(),
                sWebAddress.getText().toString(), sEmail.getText().toString(),
                sTradeLIC.getText().toString(), sVatNo.getText().toString(),
                sCreatedBy.getText().toString(), sCreateDate.getText().toString());

        shopEntryInt.PostData(shopEntryData);
        Call<ShopEntryData> call = shopEntryInt.PostData(shopEntryData);

        call.enqueue(new Callback<ShopEntryData>() {
            @Override
            public void onResponse(Call<ShopEntryData> call, Response<ShopEntryData> response) {
                Toast.makeText(ShopEntry.this,"Done!",Toast.LENGTH_SHORT);
            }

            @Override
            public void onFailure(Call<ShopEntryData> call, Throwable t) {
                Toast.makeText(ShopEntry.this,"Server Connection Failed",Toast.LENGTH_SHORT);
            }
        });

    }

    private void goToDashboard() {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
    }

}
