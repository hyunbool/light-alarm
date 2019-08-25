package ite.smu.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static android.content.Context.ALARM_SERVICE;


public class ListViewAdapter extends ArrayAdapter implements View.OnClickListener
 {

    public interface ListBtnClickListener{
        void onListBtnClick(View v);
    }
    int resourceId;
    private ListBtnClickListener listBtnClickListener;

    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>();

    public ListViewAdapter(Context context, int resource, ArrayList<ListViewItem> list, ListBtnClickListener clickListener) {
        super(context, resource, list);
        this.resourceId = resource;
        this.listBtnClickListener = clickListener;
    }

    @Override
    public int getCount(){
        return listViewItemList.size();
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView timeTextView = (TextView) convertView.findViewById(R.id.time) ;
        TextView ampmTextView = (TextView) convertView.findViewById(R.id.ampm) ;
        TextView monTextView = (TextView) convertView.findViewById(R.id.mondayt);
        TextView tueTextView = (TextView) convertView.findViewById(R.id.tuesdayt);
        TextView wedTextView = (TextView) convertView.findViewById(R.id.wednesdayt) ;
        TextView thuTextView = (TextView) convertView.findViewById(R.id.thursdayt);
        TextView friTextView = (TextView) convertView.findViewById(R.id.fridayt);
        TextView satTextView = (TextView) convertView.findViewById(R.id.saturdayt) ;
        TextView sunTextView = (TextView) convertView.findViewById(R.id.sundayt);
        ImageView lightImageView = (ImageView) convertView.findViewById(R.id.light_img);
        ImageView soundImageView = (ImageView) convertView.findViewById(R.id.sound_img);
        ImageView vibImageView = (ImageView) convertView.findViewById(R.id.vib_img);

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        ListViewItem listViewItem = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        timeTextView.setText(listViewItem.getTime());
        ampmTextView.setText(listViewItem.getAmPm());
        monTextView.setText(listViewItem.getMon());
        tueTextView.setText(listViewItem.getTue());
        wedTextView.setText(listViewItem.getWed());
        thuTextView.setText(listViewItem.getThu());
        friTextView.setText(listViewItem.getFri());
        satTextView.setText(listViewItem.getSat());
        sunTextView.setText(listViewItem.getSun());
        lightImageView.setImageDrawable(listViewItem.getLight());
        soundImageView.setImageDrawable(listViewItem.getSound());
        vibImageView.setImageDrawable(listViewItem.getVib());


        Button btn = (Button) convertView.findViewById(R.id.confirm);
        btn.setTag(position);
        btn.setOnClickListener(this);

        Button btn_d = (Button) convertView.findViewById(R.id.delete);
        btn_d.setTag(position);
        btn_d.setOnClickListener(this);

        return convertView;
    }

    public void onClick(View v) {
        //listBtnClickListener(MainActivity)의 onListBtnClick() 함수 호출
        if (this.listBtnClickListener != null) {
            this.listBtnClickListener.onListBtnClick(v);
        }
    }




    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(Drawable light, Drawable sound, Drawable vib, String ampm, String time, String mon, String tue, String wed, String thu, String fri, String sat, String sun) {
        ListViewItem item = new ListViewItem();

        item.setLight(light);
        item.setSound(sound);
        item.setVib(vib);
        item.setAmPm(ampm);
        item.setTime(time);
        item.setMon(mon);
        item.setTue(tue);
        item.setWed(wed);
        item.setThu(thu);
        item.setFri(fri);
        item.setSat(sat);
        item.setSun(sun);

        listViewItemList.add(item);
    }
}
