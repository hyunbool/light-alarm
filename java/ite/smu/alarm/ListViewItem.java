package ite.smu.alarm;

import android.graphics.drawable.Drawable;

/**
 * Created by glori on 2017-11-29.
 */

public class ListViewItem {
    private Drawable lightDrawable;
    private Drawable soundDrawable;
    private Drawable vibDrawable;
    private String time;
    private String ampm;
    private String mon;
    private String tue;
    private String wed;
    private String thu;
    private String fri;
    private String sat;
    private String sun;

    public void setTime(String time){
        this.time = time;
    }

    public void setAmPm(String ampm){
        this.ampm = ampm;
    }

    public void setMon(String mon){this.mon = mon;}

    public void setTue(String tue){this.tue = tue;}

    public void setWed(String wed){this.wed = wed;}

    public void setThu(String thu){this.thu = thu;}

    public void setFri(String fri){this.fri = fri;}

    public void setSat(String sat){this.sat = sat;}

    public void setSun(String sun){this.sun = sun;}

    public void setLight(Drawable light){lightDrawable = light;}

    public void setSound(Drawable sound){
        soundDrawable = sound;
    }

    public void setVib(Drawable vib){
        vibDrawable = vib;
    }



    public String getTime(){
        return this.time;
    }

    public String getAmPm(){
        return this.ampm;
    }

    public String getMon() { return this.mon;}

    public String getTue(){return this.tue;}

    public String getWed(){return this.wed;}

    public String getThu() { return this.thu;}

    public String getFri(){return this.fri;}

    public String getSat(){return this.sat;}

    public String getSun(){return this.sun;}

    public Drawable getLight(){return this.lightDrawable;}

    public Drawable getSound(){
        return this.soundDrawable;
    }

    public Drawable getVib(){
        return this.vibDrawable;
    }


}
