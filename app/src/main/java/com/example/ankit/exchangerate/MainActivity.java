package com.example.ankit.exchangerate;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    public static final String ACCESS_KEY = "63e94b6c7f1c181d141f6bab640a0295";
    public static final String BASE_URL = "http://apilayer.net/api/";
    public static final String ENDPOINT = "live";

    Intent intent = new Intent(Intent.ACTION_DIAL);

    private Button getJSON;
    private EditText etamount, etcurrency;
    private TextView etvalue, etINR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        etamount = (EditText) findViewById(R.id.amountEd);
        etcurrency = (EditText) findViewById(R.id.currencyEd);
        etvalue = (TextView) findViewById(R.id.valueEd);
        etINR = (TextView) findViewById(R.id.inrEd);

        getJSON = (Button) findViewById(R.id.submitBtn);



        getJSON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getJSONOnline();
            }
        });

    }


    private void getJSONOnline() {

        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, BASE_URL + ENDPOINT + "?access_key=" + ACCESS_KEY , null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // the response is already constructed as a JSONObject!
                        try {

                            Double d=response.getJSONObject("quotes").getDouble("USD" +etcurrency.getText().toString());
                            Double inr=response.getJSONObject("quotes").getDouble("USDINR");
                            etvalue.setText(String.valueOf(inr/d));

                            Double dd=Double.parseDouble(etamount.getText().toString());
                            etINR.setText(String.valueOf(dd*(inr/d)));


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        Volley.newRequestQueue(this).add(jsonRequest);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onClickBuyHistory(View view) {
        intent = new Intent(getApplicationContext(),BuyHistory.class);
        startActivity(intent);

    }

    public void onClickAdd(View view) {
        intent = new Intent(getApplicationContext(),AddNewRecord.class);
        startActivity(intent);
    }
}
