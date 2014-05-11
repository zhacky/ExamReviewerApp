package com.zhacky.app.examreviewer.model;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Item implements Parcelable {

	private final static String LOGTAG = "QUIZAPP";
	private long id;
	private String title;
	private String description;
	private int units;
	private String image;

	
	// set Constructor
	
	public Item(){
	image="none";
	
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Item(Parcel in) {
		Log.i(LOGTAG, "Parcel constructor");
		id = in.readLong();
		title = in.readString();
		description = in.readString();
		units = in.readInt();
		image = in.readString();

	}

	@Override
	public String toString() {

		return title;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		Log.i(LOGTAG, "writeToParcel");

		dest.writeLong(id);
		dest.writeString(title);
		dest.writeString(description);
		dest.writeInt(units);
		dest.writeString(image);

	}

	public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {

		@Override
		public Item createFromParcel(Parcel source) {
			Log.i(LOGTAG, "createFromParcel");
			return new Item(source);
		}

		@Override
		public Item[] newArray(int size) {
		
			Log.i(LOGTAG, "newArray");
			return new Item[size];
		}

	};
}
