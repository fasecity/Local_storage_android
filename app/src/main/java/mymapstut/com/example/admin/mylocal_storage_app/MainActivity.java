package mymapstut.com.example.admin.mylocal_storage_app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import mymapstut.com.example.admin.mylocal_storage_app.model.DataItem;
import mymapstut.com.example.admin.mylocal_storage_app.sample.SampleDataProvider;

public class MainActivity extends AppCompatActivity {
    //TextView tv;
    List<DataItem> dataItemList = SampleDataProvider.dataItemList;
    List<String> itemNamesList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //tv = (TextView) findViewById(R.id.output);
       // tv.setText("");
        Collections.sort(dataItemList, new Comparator<DataItem>() {
            @Override
            public int compare(DataItem o1, DataItem o2) {
                //sorts aplhabetically
                return o1.getItemName().compareTo(o2.getItemName());
            }
        });
//        for (DataItem item:dataItemList) {
//           // tv.append(item.getItemName() + "\n");
//            itemNamesList.add(item.getItemName());
//
//        }
//      Collections.sort(itemNamesList);
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
//                (this,android.R.layout.simple_list_item_1,itemNamesList);
        DataItemAdapter dataItemAdapter = new DataItemAdapter(this,dataItemList);
        RecyclerView rc = (RecyclerView) findViewById(R.id.rcView);
        layoutManager = new LinearLayoutManager(this);
        rc.setHasFixedSize(false);//only use this for fixed ammount
        rc.setLayoutManager(layoutManager);
        rc.setAdapter(dataItemAdapter);
//        DataItemAdapterListView dataItemAdapter = new DataItemAdapterListView(this,dataItemList);
//        ListView rc = (ListView) findViewById(R.id.mylist);
//        rc.setAdapter(dataItemAdapter);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
