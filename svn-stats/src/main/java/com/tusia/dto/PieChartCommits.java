package com.tusia.dto;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class PieChartCommits {
	protected String name;
	protected int y = 0;
	
	public JSONArray getJson()
	{
		JSONArray retVal = new JSONArray();
		retVal.add(name);
		retVal.add(y);
		
		return retVal;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
}
