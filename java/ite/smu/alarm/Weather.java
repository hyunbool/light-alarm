package ite.smu.alarm;
import android.app.ListActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Vector;

/**
 * Created by sm-pc on 2017-12-04.
 */

public class Weather extends ListActivity {

    Vector<String> weatherVector;

    String uri = "http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=108";
    URL weaURL;

    String tagName = "", mainW = "\n";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListView lv = getListView();

        setListAdapter(new ArrayAdapter<String>(this, R.layout.weather_view, getData_main()));

    }

    public Vector getData_main() {
        weatherVector = new Vector<String>();

        try {
            weaURL = new URL(uri);
            InputStream in = weaURL.openStream();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();

            xpp.setInput(in, "utf-8");

            int eventType = xpp.getEventType();

            boolean isItemTag = false;

            while (eventType != XmlPullParser.END_DOCUMENT) {

                if(eventType == XmlPullParser.START_TAG){
                    tagName = xpp.getName();
                    if (tagName.equals("header")) isItemTag = true;

                } else if (eventType == XmlPullParser.TEXT) {
                    if (isItemTag && tagName.equals("wf")) {
                        mainW += xpp.getText();
                        Log.i("WeatherApp", mainW);
                    }

                } else if (eventType == XmlPullParser.END_TAG) {
                    tagName = xpp.getName();

                    if (tagName.equals("header")) {
                        mainW = mainW.replace("<br />", "");
                        weatherVector.add(mainW);

                        mainW = "";

                    }
                }

                eventType = xpp.next();
            }

        } catch (Exception e) {
        }

        return weatherVector;

    }

}
