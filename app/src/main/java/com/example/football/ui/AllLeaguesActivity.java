package com.example.football.ui;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.football.Constants;
import com.example.football.R;
import com.example.football.adapter.AllLeaguesAdapter;
import com.example.football.model.AllLeagueModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AllLeaguesActivity extends AppCompatActivity {
    private static final String TAG = AllLeaguesActivity.class.getSimpleName();
    private RecyclerView rvSurah;
    private AllLeaguesAdapter allLeaguesAdapter;
    private List<AllLeagueModel> allLeagueList = new ArrayList<>();
    private ProgressDialog mProgress;
    SwipeRefreshLayout swipeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_leagues);

        rvSurah = findViewById(R.id.recyclerView );
        swipeLayout = findViewById(R.id.swipe_container);

        mProgress = new ProgressDialog(this);
        mProgress.setTitle("Processing...");
        mProgress.setMessage("Please wait...");
        mProgress.setCancelable(false);
        mProgress.setIndeterminate(true);

        mProgress.show();
        fetchSurahApi();



        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code here
                allLeagueList.clear();
                fetchSurahApi();
                // To keep animation for 4 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        // Stop animation (This will be after 3 seconds)
                        swipeLayout.setRefreshing(false);
                    }
                }, 1500);
                Toast.makeText(getApplicationContext(), "Surah is Up to date!", Toast.LENGTH_SHORT).show();// Delay in millis
            }
        });

        setupRecycler();
    }

    private void setupRecycler(){
        allLeaguesAdapter = new AllLeaguesAdapter(this, allLeagueList);
        rvSurah.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvSurah.setHasFixedSize(true);
        rvSurah.setAdapter(allLeaguesAdapter);
    }

    private void fetchSurahApi() {
        AndroidNetworking.get(Constants.BASE_URL)
                .setTag("leagues")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray hasilList = response.getJSONArray("leagues");
                            for (int i = 0; i < hasilList.length(); i++) {
                                JSONObject hasil = hasilList.getJSONObject(i);
                                AllLeagueModel item = new AllLeagueModel();
                                item.setStrLeague(hasil.getString("strLeague"));
                                item.setStrSport(hasil.getString("strSport"));
                                item.setStrLeagueAlternate(hasil.getString("strLeagueAlternate"));
                                allLeagueList.add(item);
                            }
                            mProgress.dismiss();
                            allLeaguesAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("", "onError: " + anError.getErrorBody());
                        Toast.makeText(AllLeaguesActivity.this, Constants.EROR, Toast.LENGTH_SHORT).show();
                    }
                });


    }
}
