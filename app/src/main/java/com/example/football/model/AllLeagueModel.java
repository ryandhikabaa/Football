package com.example.football.model;

import android.os.Parcel;
import android.os.Parcelable;

public class AllLeagueModel implements Parcelable{
    private String idLeague;
    private String strLeague;
    private String strSport;
    private String strLeagueAlternate;

    public AllLeagueModel() {
    }

    public AllLeagueModel(String idLeague, String strLeague, String strSport, String strLeagueAlternate) {
        this.idLeague = idLeague;
        this.strLeague = strLeague;
        this.strSport = strSport;
        this.strLeagueAlternate = strLeagueAlternate;
    }

    protected AllLeagueModel(Parcel in) {
        idLeague = in.readString();
        strLeague = in.readString();
        strSport = in.readString();
        strLeagueAlternate = in.readString();
    }

    public static final Creator<AllLeagueModel> CREATOR = new Creator<AllLeagueModel>() {
        @Override
        public AllLeagueModel createFromParcel(Parcel in) {
            return new AllLeagueModel(in);
        }

        @Override
        public AllLeagueModel[] newArray(int size) {
            return new AllLeagueModel[size];
        }
    };

    //Get

    public String getIdLeague() {
        return idLeague;
    }

    public String getStrLeague() {
        return strLeague;
    }

    public String getStrSport() {
        return strSport;
    }

    public String getStrLeagueAlternate() {
        return strLeagueAlternate;
    }


    //Setter

    public void setIdLeague(String idLeague) {
        this.idLeague = idLeague;
    }

    public void setStrLeague(String strLeague) {
        this.strLeague = strLeague;
    }

    public void setStrSport(String strSport) {
        this.strSport = strSport;
    }

    public void setStrLeagueAlternate(String strLeagueAlternate) {
        this.strLeagueAlternate = strLeagueAlternate;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idLeague);
        parcel.writeString(strLeague);
        parcel.writeString(strSport);
        parcel.writeString(strLeagueAlternate);
    }
}
