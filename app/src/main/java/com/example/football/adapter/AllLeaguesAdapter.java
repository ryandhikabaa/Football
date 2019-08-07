package com.example.football.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.football.R;
import com.example.football.model.AllLeagueModel;
import com.example.football.ui.AllLeaguesDetailActivity;

import java.util.List;

public class AllLeaguesAdapter extends  RecyclerView.Adapter<AllLeaguesAdapter.MyViewHolder> {

    private Context mContext;
    private List<AllLeagueModel> mData;

    public AllLeaguesAdapter(Context mContext, List<AllLeagueModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.allleague_list, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final AllLeagueModel surah = mData.get(position);

        holder.League.setText(surah.getStrLeague());//
        holder.Sport.setText(surah.getStrSport());//
        holder.LeagueAlternate.setText(surah.getStrLeagueAlternate());//
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView League, Sport, LeagueAlternate;

        MyViewHolder(View itemView) {
            super(itemView);
            League = itemView.findViewById(R.id.tv_League);
            Sport = itemView.findViewById(R.id.tv_Sport);
            LeagueAlternate = itemView.findViewById(R.id.tv_leaguealternate);
//            tv_asma = itemView.findViewById(R.id.tv_asma);
//            tv_ayat = itemView.findViewById(R.id.tv_jumlah_ayat);
//            tv_type = itemView.findViewById(R.id.tv_turun_surat);
//            tv_urut = itemView.findViewById(R.id.tv_urutanwahyu);
//            tv_keterangan = itemView.findViewById(R.id.tv_keterangan);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(mContext, AllLeaguesDetailActivity.class);
                    i.putExtra("leagues", mData.get(getAdapterPosition()));
                    mContext.startActivity(i);
                }
            });
        }
    }
}
