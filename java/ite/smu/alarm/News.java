package ite.smu.alarm;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.URL;
import java.util.Vector;

/**
 * Created by glori on 2017-12-06.
 */

public class News extends ListActivity {

    Vector<String> newsVector;

    String uri = "http://api.sbs.co.kr/xml/news/rss.jsp?pmDiv=ranking";
    URL newsURL;

    String tagName = "", title = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        super.onCreate(savedInstanceState);

        ListView lv = getListView();

        setListAdapter(new ArrayAdapter<String>(this, R.layout.news_view, getData()));
    }

    public Vector getData() {
        newsVector = new Vector<String>();

        try {
            newsURL = new URL(uri);
            InputStream in = newsURL.openStream();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();

            xpp.setInput(in, "utf-8");

            int eventType = xpp.getEventType();

            boolean isItemTag = false;

            while (eventType != XmlPullParser.END_DOCUMENT) {

                if (eventType == XmlPullParser.START_TAG) {
                    tagName = xpp.getName();
                    if (tagName.equals("item")) isItemTag = true;

                } else if (eventType == XmlPullParser.TEXT) {
                    if (isItemTag && tagName.equals("title")) {
                        title += xpp.getText();
                        Log.i("NewsApp", title);
                    }

                } else if (eventType == XmlPullParser.END_TAG) {
                    tagName = xpp.getName();

                    if (tagName.equals("item")) {
                        newsVector.add(title);

                        title = "";

                    }
                }

                eventType = xpp.next();
            }

        } catch (Exception e) {
            Log.e("NewsApp:", "예외발생 :" + e.getMessage());
        }

        return newsVector;

    }


}
