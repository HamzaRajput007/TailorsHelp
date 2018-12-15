package com.example.m.a1stonnewandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCustomer extends AppCompatActivity {

    EditText CustID;
    EditText Name;
    EditText Phone_Number;
    EditText Lambai;
    EditText Bazu;
    EditText Teera;
    EditText Chaati;
    EditText Ghaira;
    EditText Gala;
    EditText Shalwar;
    EditText Pancha;
    EditText Kaf;
    EditText Kandha;

    Button AddBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        CustID = findViewById(R.id.IdTvId);
        Name = findViewById(R.id.NameTvId);
        Phone_Number = findViewById(R.id.PhoneTvId);
        Lambai = findViewById(R.id.LambaiTvId);
        Bazu = findViewById(R.id.BazuTvId);
        Teera = findViewById(R.id.TeeraTvId);
        Chaati = findViewById(R.id.ChatiTvId);
        Ghaira = findViewById(R.id.GhairaTvId);
        Gala = findViewById(R.id.GalaTvId);
        Shalwar = findViewById(R.id.ShalwarTvId);
        Pancha = findViewById(R.id.PanchaTvId);
        Kaf = findViewById(R.id.KafTvId);
        Kandha = findViewById(R.id.KandhaTvId);

        AddBtn = findViewById(R.id.AddBtnId);
        AddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try{
                    MainActivity.zainStitchDb.execSQL("INSERT INTO Customer (CustID ,Name ,Phone_Number,Lambai,Bazu," +
                                                                    "Teera,Chaati,Ghaira,Gala,Shalwar,Pancha ,Kaf ,Kandha ) " +
                                                        "VALUES ( "+
                                                            "String.valueOf/(CustID.getText/())," +
                                                            "String.valueOf/(Name.getText/())," +
                                                            "String.valueOf/(Phone_Number.getText/())," +
                                                            "String.valueOf/(Lambai.getText/())," +
                                                            "String.valueOf/(Bazu.getText/())," +
                                                            "String.valueOf/(Teera.getText/())," +
                                                            "String.valueOf/(Chaati.getText/())," +
                                                            "String.valueOf/(Ghaira.getText/()),"+
                                                            "String.valueOf/(Gala.getText/())," +
                                                            "String.valueOf/(Shalwar.getText/())," +
                                                            "String.valueOf/(Pancha.getText/())," +
                                                            "String.valueOf/(Kaf.getText/())," +
                                                            "String.valueOf/(Kandha.getText/()))");

                    Toast.makeText(AddCustomer.this, "Record Added Successfully!", Toast.LENGTH_SHORT).show();

                    MainActivity object = new MainActivity();
                    object.LoadListViewItems(object.customerList);

                    Intent AddCustomerToMainActivity = new Intent(AddCustomer.this,MainActivity.class);
                    startActivity(AddCustomerToMainActivity);
//                    Phone_Number.getText(),Lambai.getText(),Bazu.getText(),Teera.getText(),Chaati.getText(),Ghaira.getText(),Gala.getText(),Shalwar.getText(),Pancha.getText(),Kaf.getText()
                }catch (Exception e){
                    Toast.makeText(AddCustomer.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
