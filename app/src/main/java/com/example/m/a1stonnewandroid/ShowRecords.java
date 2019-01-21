package com.example.m.a1stonnewandroid;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ShowRecords extends AppCompatActivity {

    TextView mName;
    TextView mPhone_Number;
    TextView mLambai;
    TextView mBazu;
    TextView mTeera;
    TextView mChaati;
    TextView mGhaira;
    TextView mGala;
    TextView mShalwar;
    TextView mPancha;
    TextView mKaf;
    TextView mKandha;
    TextView mCustID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_records);

        String recordId = "";
        Cursor customerRecords = null;
        try {
            Intent fromMain = getIntent();
            recordId = fromMain.getStringExtra("ID");
            String[] id  = new String[1];
            id[0] = recordId;

            customerRecords = getCustomerDataById(id);
            Toast.makeText(this, String.valueOf(customerRecords.getCount()), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Intent Not Loaded " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        mCustID = findViewById(R.id.IDTvId);
        mName =findViewById(R.id.SNameTvId);
        mPhone_Number = findViewById(R.id.SPhoneTvId);
        mLambai = findViewById(R.id.SLambaiTvId);
        mBazu =findViewById(R.id.SBazuTvId);
        mTeera= findViewById(R.id.STeeraTvId);
        mChaati = findViewById(R.id.SChatiTvId);
        mGhaira = findViewById(R.id.SGhairaTvId);
        mGala = findViewById(R.id.SGalaTvId);
        mShalwar = findViewById(R.id.SShalwarTvId);
        mPancha = findViewById(R.id.SPanchaTvId);
        mKaf = findViewById(R.id.SKafTvId);
        mKandha = findViewById(R.id.SKandhaTvId);




        int cName = 0;
        int cPhone = 0;
        int cLambai = 0;
        int cBazu = 0;
        int cTeera = 0;
        int cChaati = 0;
        int cGhaira = 0;
        int cGala = 0;
        int cShalwar = 0;
        int cPancha = 0;
        int cKaf = 0;
        int cKandha = 0;
        try {
            cName = customerRecords.getColumnIndex("Name");
            cPhone = customerRecords.getColumnIndex("Phone_Number");
            cLambai = customerRecords.getColumnIndex("Lambai");
            cBazu = customerRecords.getColumnIndex("Bazu");
            cTeera = customerRecords.getColumnIndex("Teera");
            cChaati = customerRecords.getColumnIndex("Chaati");
            cGhaira = customerRecords.getColumnIndex("Ghaira");
            cGala = customerRecords.getColumnIndex("Gala");
            cShalwar = customerRecords.getColumnIndex("Shalwar");
            cPancha = customerRecords.getColumnIndex("Pancha");
            cKaf = customerRecords.getColumnIndex("Kaf");
            cKandha = customerRecords.getColumnIndex("Kandha");
        } catch (Exception e) {
            Toast.makeText(this, "Column Not Found " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        try {
            customerRecords.moveToFirst();
            mCustID.setText(recordId);

//            Toast.makeText(this,customerRecords.getString(cName) , Toast.LENGTH_SHORT).show();
            CharSequence nameCS = customerRecords.getString(cName);
            mName.setText(nameCS);
            mLambai.setText(customerRecords.getString(cLambai));
            mBazu.setText(customerRecords.getString(cBazu));
            mTeera.setText(customerRecords.getString(cTeera));
            mChaati.setText(customerRecords.getString(cChaati));
            mGhaira.setText(customerRecords.getString(cGhaira));
            mGala.setText(customerRecords.getString(cGala));
            mShalwar.setText(customerRecords.getString(cShalwar));
            mPancha.setText(customerRecords.getString(cPancha));
            mKaf.setText(customerRecords.getString(cKaf));
            mKandha.setText(customerRecords.getString(cKandha));
        } catch (Exception e) {
            Toast.makeText(this, "Failed to Display Data " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public Cursor getCustomerDataById(String[] customerId) {


        Cursor c = null;
        String idString = customerId[0];
        try {

            c = MainActivity.zainStitchDb.rawQuery("SELECT * FROM Customer WHERE CustID = " + idString, null);
        } catch (Exception e) {
            Toast.makeText(this, "Selection Failed :  " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        if(c != null){
            return  c;
        }
        else
        {
            Toast.makeText(this, "Null Cursor Returned", Toast.LENGTH_SHORT).show();
        }
        return c;
    }

}
