package com.zhacky.app.examreviewer.utils;


import android.app.Activity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.zhacky.app.examreviewer.R;

public class UIHelper {

	/**
	 * Displays a given text on a TextView
	 * @param activity - The parent Activity of the TextView
	 * @param id - The Resource Id of the TextView
	 * @param text - The text to display
	 */
	public static void displayText(Activity activity, int id, String text) {
		TextView tv = (TextView) activity.findViewById(id);
		
		// -- resize Text View
		
		if (text.length()> 150 && text.length() <= 300){
			tv.setTextSize(14);
		} 
		else if(text.length() > 300){
			tv.setTextSize(12);
		}
		else{
			tv.setTextSize(16);
		}
		
		tv.setText(text);
	}

	/**
	 * Gets the text from an EditText field
	 * @param activity - The parent Activity of the EditText field
	 * @param id - The Resource Id of the EditText field
	 * @returns String - The text from the EditText field in String format
	 */
	public static String getText(Activity activity, int id) {
		EditText et = (EditText) activity.findViewById(id);
		return et.getText().toString();
	}
	/**
	 * Gets the text from an Button
	 * @param activity - The parent Activity of the Button
	 * @param id - The Resource Id of the Button
	 * @returns String - The text from the Button in String format
	 */
	public static String getButtonText(Activity activity, int id) {
		Button btn = (Button) activity.findViewById(id);
		return btn.getText().toString();
	}

	/**
	 * Gets the current checked state of a CheckBox
	 * @param activity - The parent Activity of the CheckBox
	 * @param id - The Resource Id of the CheckBox
	 * @returns Boolean - The boolean value whether the CheckBox is checked or not
	 */
	public static boolean getCBChecked(Activity activity, int id) {
		CheckBox cb = (CheckBox) activity.findViewById(id);
		return cb.isChecked();
	}

	/**
	 * Sets a CheckBox's checked state
	 * @param activity - The parent Activity of the CheckBox
	 * @param id - The Resource Id of the CheckBox
	 * @param value - The Boolean value whether the CheckBox will be checked or not
	 */
	public static void setCBChecked(Activity activity, int id, boolean value) {
		CheckBox cb = (CheckBox) activity.findViewById(id);
		cb.setChecked(value);
	}

	/**
	 * Displays a given text on a RadioButton
	 * @param activity - The parent Activity of the RadioButton
	 * @param id - The Resource Id of the RadioButton
	 * @param text - The text to display on the RadioButton
	 */
	public static void setRadioText(Activity activity, int id, String text) {
		RadioButton rb = (RadioButton) activity.findViewById(id);
		
	// -- resize RadioButton Text
		
		if (text.length()> 150 && text.length() <= 300){
			rb.setTextSize(14);
		} 
		else if(text.length() > 300){
			rb.setTextSize(12);
		}
		else{
			rb.setTextSize(16);
		}
	
		
		rb.setText(text);
	}

	/**
	 * Clears the selection on a RadioGroup
	 * @param activity - The parent Activity of the RadioGroup
	 * @param id - The Resource Id of the RadioGroup
	 */
	public static void clearSelection(Activity activity, int id) {
		RadioGroup rg = (RadioGroup) activity.findViewById(id);
		rg.clearCheck();
	}

	/**
	 * Gets the selected RadioButton from a given RadioGroup and returns the
	 * sequential number of the RadioButton
	 * @param activity - The parent Activity of the RadioGroup
	 * @param id - The Resource Id of the RadioGroup
	 * @returns Integer - The sequential number of the RadioButton
	 */
	public static int getSelected(Activity activity, int id) {
		RadioGroup rg = (RadioGroup) activity.findViewById(id);
		int result = 0;
		switch (rg.getCheckedRadioButtonId()) {
		case R.id.radio1:
			result = 1;
			break;
		case R.id.radio2:
			result = 2;
			break;
		case R.id.radio3:
			result = 3;
			break;
		case R.id.radio4:
			result = 4;
			break;
		default:
			result = 0;
			break;
		}
		return result;
	}

	/**
	 * Displays a given text on a Button
	 * @param activity - The parent Activity of the Button
	 * @param id - The Resource Id of the Button
	 * @param text - The text to display
	 */
	public static void setButtonText(Activity activity, int id, String text){
		Button btn = (Button)activity.findViewById(id);
		btn.setText(text);
	}

}
