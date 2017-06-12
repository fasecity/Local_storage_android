package mymapstut.com.example.admin.mylocal_storage_app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import mymapstut.com.example.admin.mylocal_storage_app.model.DataItem;
import mymapstut.com.example.admin.mylocal_storage_app.sample.SampleDataProvider;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //get intent & intent extras
        String itemId = getIntent().getExtras().getString(DataItemAdapter.ITEM_ID_KEY);
        //get info from static hashmap this gets dataItem Id and maps
        DataItem item = SampleDataProvider.dataItemMap.get(itemId);
        Toast.makeText(this, "you picked " + item.getItemName(), Toast.LENGTH_SHORT).show();


    }

}
