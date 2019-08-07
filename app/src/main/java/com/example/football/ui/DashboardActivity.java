package com.example.football.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.football.R;

public class DashboardActivity extends AppCompatActivity {
    private CardView c_a, c_b, c_all_league;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        c_all_league = findViewById(R.id.card_all_league);

        c_all_league.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, AllLeaguesActivity.class);
                startActivity(intent);

            }
        });
    }
}
