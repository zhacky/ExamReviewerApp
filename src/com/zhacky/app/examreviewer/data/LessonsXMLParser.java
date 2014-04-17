package com.zhacky.app.examreviewer.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.util.Log;

import com.zhacky.app.examreviewer.R;
import com.zhacky.app.examreviewer.model.Item;

public class LessonsXMLParser {
private static final String LOGTAG = "QUIZAPP";
	
	private static final String ITEM_ID = "itemId";
	private static final String ITEM_TITLE = "title";
	private static final String ITEM_DESC = "desc";
	private static final String ITEM_UNITS = "units";
	private static final String ITEM_IMAGE = "image";
	
	private Item currentItem  = null;
	private String currentTag = null;
	List<Item> items = new ArrayList<Item>();

	public List<Item> parseXML(Context context) {

		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser xpp = factory.newPullParser();
			
			InputStream stream = context.getResources().openRawResource(R.raw.review);
			xpp.setInput(stream, null);

			int eventType = xpp.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_TAG) {
					handleStartTag(xpp.getName());
				} else if (eventType == XmlPullParser.END_TAG) {
					currentTag = null;
				} else if (eventType == XmlPullParser.TEXT) {
					
						handleText(xpp.getText());
				
				}
				eventType = xpp.next();
			}

		} catch (NotFoundException e) {
			Log.d(LOGTAG, e.getMessage());
		} catch (XmlPullParserException e) {
			Log.d(LOGTAG, e.getMessage());
		} catch (IOException e) {
			Log.d(LOGTAG, e.getMessage());
		}

		return items;
	}

	private void handleText(String text) {
		String xmlText = text;
		if (currentItem != null && currentTag != null) {
			if (currentTag.equals(ITEM_ID)) {
				Long id = Long.parseLong(xmlText);
				currentItem.setId(id);
			} 
			else if (currentTag.equals(ITEM_TITLE)) {
				currentItem.setTitle(xmlText);
			}
			else if (currentTag.equals(ITEM_DESC)) {
				currentItem.setDescription(xmlText);
			}
			else if (currentTag.equals(ITEM_UNITS)) {
				int units = Integer.parseInt(xmlText);
				currentItem.setUnits(units);
			}
			else if (currentTag.equals(ITEM_IMAGE)) {
				currentItem.setImage(xmlText);
			}
		}
	}

	private void handleStartTag(String name) {
		if (name.equals("item")) {
			currentItem = new Item();
			items.add(currentItem);
		}
		else {
			currentTag = name;
		}
	}
}
