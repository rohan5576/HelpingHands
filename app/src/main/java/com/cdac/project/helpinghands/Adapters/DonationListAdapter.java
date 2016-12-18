package com.cdac.project.helpinghands.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cdac.project.helpinghands.R;
import com.cdac.project.helpinghands.classes.Donations;

import java.util.ArrayList;

/**
 * Created by parag on 20-07-2016.
 */
public class DonationListAdapter extends BaseAdapter {
    Context context;
    //  int count;
    ArrayList<Donations> list = new ArrayList<>();

    public DonationListAdapter(Context context1, ArrayList<Donations> list) {
        context = context1;
        //  count=n;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View vi = convertView;
        DonationHolder holder;

        if (convertView == null) {

            //Inflate the layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi = inflater.inflate(R.layout.consumefrag_item_layout, null);

            holder = new DonationHolder();
            holder.textViewDonateId = (TextView) vi.findViewById(R.id.textViewDonateId);
            holder.textViewFoodDesc = (TextView) vi.findViewById(R.id.textViewFoodDesc);

            holder.textViewQuantity = (TextView) vi.findViewById(R.id.textViewQuantity);
            /************  Set holder with LayoutInflater ************/
            vi.setTag(holder);
        } else {
            holder = (DonationHolder) vi.getTag();
        }
        holder.textViewDonateId.setText(list.get(i).getDonateId()+"");
        //holder.textViewQtLabel.setText(list.get(i).get);
        holder.textViewFoodDesc.setText(list.get(i).getFoodDesc());
        holder.textViewQuantity.setText(list.get(i).getFoodValue());


        return vi;
    }

    public class DonationHolder {
        TextView textViewFoodDesc;
        TextView textViewDonateId;
        TextView textViewQuantity;

    }
}
