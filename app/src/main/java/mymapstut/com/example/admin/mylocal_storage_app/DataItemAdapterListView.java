package mymapstut.com.example.admin.mylocal_storage_app;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.Inflater;

import mymapstut.com.example.admin.mylocal_storage_app.model.DataItem;


public class DataItemAdapterListView extends ArrayAdapter<DataItem> {
   //pass in layout
    List<DataItem> myDataItems;
    ///read into xml file
    LayoutInflater myInflater;

    public DataItemAdapterListView(@NonNull Context context, @NonNull List<DataItem> objects) {
        super(context,R.layout.list_item, objects);
        myDataItems = objects;
        myInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
          convertView =  myInflater.inflate(R.layout.list_item,parent,false);
        }
        TextView tvName = (TextView) convertView.findViewById(R.id.itemNametext);
        ImageView tvPic = (ImageView) convertView.findViewById(R.id.imageView);
        DataItem itemPosition = myDataItems.get(position);
        tvName.setText(itemPosition.getItemName());
      //  tvPic.setImageResource(R.drawable.apple_pie);
        InputStream inputStream = null;
        try {
            //img getter make sure names match assets file
            String imgFile = itemPosition.getImage();
            inputStream = getContext().getAssets().open(imgFile);//gets assets folder
            Drawable d = Drawable.createFromStream(inputStream,null);
            tvPic.setImageDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return convertView;
    }
}
