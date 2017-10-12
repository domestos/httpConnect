package com.example.valerapelenskyi.httpconnect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url_select_php =  "http://lvilwks0004.lvi.gameloft.org/PHPScript/db_select.php?number=111116/1/1";
        new Connect(url_select_php, this).execute();



    }


}
