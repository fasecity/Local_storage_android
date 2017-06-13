package mymapstut.com.example.admin.mylocal_storage_app;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.Locale;

import mymapstut.com.example.admin.mylocal_storage_app.model.DataItem;
import mymapstut.com.example.admin.mylocal_storage_app.sample.SampleDataProvider;

public class DetailActivity extends AppCompatActivity {

    private TextView nameText,decscriptionText,priceText;
    private ImageView imageView;

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
        //String itemId = getIntent().getExtras().getString(DataItemAdapter.ITEM_ID_KEY);
        //get info from static hashmap this gets dataItem Id and maps
       // DataItem item = SampleDataProvider.dataItemMap.get(itemId);
        //getting data from parcleble /2/use ifnn statment null saftey
        DataItem item = getIntent().getExtras().getParcelable(DataItemAdapter.ITEM_KEY);
//        if (item != null) {
//            Toast.makeText(this, "you picked " + item.getItemName(), Toast.LENGTH_SHORT).show();
//        }
//        else {
//            Toast.makeText(this, "no data revived", Toast.LENGTH_SHORT).show();
//        }
        if (item == null) {
            throw new AssertionError("null data recived");
        }
        priceText = (TextView) findViewById(R.id.priceText);
        nameText = (TextView) findViewById(R.id.nameText);
        decscriptionText = (TextView) findViewById(R.id.descriptionText);
        imageView = (ImageView) findViewById(R.id.imageViewFood);

        nameText.setText(item.getItemName());
        decscriptionText.setText(item.getDescription());
        //format double currency
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.getDefault());
        priceText.setText(nf.format(item.getPrice()));

        //input stream
        InputStream inputStream = null;
        try {
            String imageFile = item.getImage();
            inputStream = getAssets().open(imageFile);
            Drawable d = Drawable.createFromStream(inputStream, null);
            imageView.setImageDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }







    }

}
