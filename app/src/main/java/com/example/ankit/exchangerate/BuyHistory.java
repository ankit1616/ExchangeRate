package com.example.ankit.exchangerate;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class BuyHistory extends AppCompatActivity {

    DBController controller = new DBController(this);
    ListView ls;
    TextView infotext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ls = (ListView) findViewById(R.id.placeslist);
        infotext = (TextView) findViewById(R.id.txtresulttext);
        try {
            List<HashMap<String, String>> data = controller.getAllPlace();
            if (data.size() != 0) {
                // Srno, RMCode, Fileno, Loc, FileDesc, TAGNos
                SimpleAdapter adapter = new SimpleAdapter(
                        BuyHistory.this, data, R.layout.rows,
                        new String[]{"id", "date", "currency", "quantity", "buyvalue"}, new int[]{
                        R.id.txtid, R.id.txtdate,
                        R.id.txtcurrency, R.id.txtqty, R.id.txtbuyvalue});
                ls.setAdapter(adapter);
                String length = String.valueOf(data.size());
                infotext.setText(length + " record(s)");
            } else {
                infotext.setText("No data in database");
            }
        } catch (Exception ex) {
            infotext.setText(ex.getMessage().toString());
        }



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
