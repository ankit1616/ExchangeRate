package com.example.ankit.exchangerate;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNewRecord extends AppCompatActivity {

    EditText etdate,etcurrecy,etqty,etbuyvalue,etcurrvalue;
    Button btn;

    DBController controller = new DBController(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_record);

        etdate=(EditText) findViewById(R.id.dateEt);
        etcurrecy=(EditText) findViewById(R.id.currrencyEt);
        etqty=(EditText) findViewById(R.id.quantityEt);
        etbuyvalue=(EditText) findViewById(R.id.buyvalueEt);
        //etcurrvalue=(EditText) findViewById(R.id.currvalueEt);




        btn=(Button)findViewById(R.id.btnSubmit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                        controller = new DBController(getApplicationContext());
                        SQLiteDatabase db = controller.getWritableDatabase();
                        ContentValues cv = new ContentValues();
                        cv.put("date", etdate.getText().toString());
                        cv.put("currency", etcurrecy.getText().toString());
                        cv.put("quantity", etqty.getText().toString());
                        cv.put("buyvalue", etbuyvalue.getText().toString());
                        db.insert("buyrecord", null, cv);
                        db.close();
                        //infotext.setText("Place added Successfully");
                    }


        });
}}
