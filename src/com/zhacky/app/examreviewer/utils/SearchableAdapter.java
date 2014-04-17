package com.zhacky.app.examreviewer.utils;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhacky.app.examreviewer.R;
import com.zhacky.app.examreviewer.model.Item;

public class SearchableAdapter extends ArrayAdapter<Item> {
	Context context;
	List<Item> items;
	
	public SearchableAdapter(Context context, List<Item> items) {
		super(context, android.R.id.content, items);
		this.context = context;
		this.items = items;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	View v = vi.inflate(R.layout.activity_study	, null);
	Item item = items.get(position);
	
	ImageView iv = (ImageView) v.findViewById(R.id.imgItem);
	int imgSource = context.getResources().getIdentifier(item.getImage(), "drawable", context.getPackageName());
	if (imgSource != 0){
		iv.setImageResource(imgSource); 	
	}
	TextView tv = (TextView) v.findViewById(R.id.tvItemDesc);
		tv.setText(item.getDescription());
			
	
	
		return v;
	}

	
}
