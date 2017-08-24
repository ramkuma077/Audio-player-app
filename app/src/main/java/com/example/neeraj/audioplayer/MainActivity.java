package com.example.neeraj.audioplayer;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    Cursor cursor;
    String []arr;
    String[] intentsong;
    String artist,album,title,composer,data,size,yearofrelease;
    ContentResolver cv;
    String arr2[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.listview);
        Intent intent=getIntent();

        cv=getContentResolver();
        artist= MediaStore.Audio.Media.ARTIST;
        title=MediaStore.Audio.Media.TITLE;
        composer=MediaStore.Audio.Media.COMPOSER;
        album=MediaStore.Audio.Media.ALBUM;
        data=MediaStore.Audio.Media.DATA;
        size=MediaStore.Audio.Media.SIZE;
        yearofrelease=MediaStore.Audio.Media.YEAR;

        cursor =cv.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,new String[]{artist,album,title,composer,data,size,yearofrelease},null,null,null);

        intentsong =new String[cursor.getCount()];
        arr2=new String[cursor.getCount()];
        arr=new String[cursor.getCount()];
        if (cursor.getCount()>0)
        {
            cursor.moveToFirst();
            for (int i=0;i<cursor.getCount();i++)
            {
                String artistname=cursor.getString(cursor.getColumnIndexOrThrow(artist));
                String albumname=cursor.getString(cursor.getColumnIndexOrThrow(album));
                String titles=cursor.getString(cursor.getColumnIndexOrThrow(title));
                String song=cursor.getString(cursor.getColumnIndexOrThrow(data));
                String siz=cursor.getString(cursor.getColumnIndexOrThrow(size));
                arr2[i]=song;
                String s1=artistname.concat("" +albumname);
                String s2=song.concat("" +title);
                String s3=s1.concat("" +s2);

                arr[i]=s3;
                cursor.moveToNext();
            }
        }


        ArrayAdapter<String>ad=new ArrayAdapter<String>(MainActivity.this,R.layout.support_simple_spinner_dropdown_item,arr);
        listView.setAdapter(ad);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MainActivity.this,AudioDemo.class);
                intent.putExtra("key1",arr2[position]);
                startActivity(intent);
            }
        });


    }
}
