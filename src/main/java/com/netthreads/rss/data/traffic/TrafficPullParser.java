/**
 * Copyright (C) 2008 Alistair Rutherford, Glasgow, Scotland, UK, www.netthreads.co.uk
 *
 */
package com.netthreads.rss.data.traffic;

import com.netthreads.rss.PullParser;

/**
 * This is a _simple_ XML Pull parser for RSS xml feed.
 * 
 * Type: RSS Data
 * 
 * Sample URL:
 * 
 */
public class TrafficPullParser implements PullParser<TrafficData>
{
	// In tag
	private boolean inCategoryClass = false;
	private boolean inRoad = false;
	private boolean inRegion = false;
	private boolean inCounty = false;
	private boolean inLatitude = false;
	private boolean inLongitude = false;
	private boolean inOverAllStart = false;
	private boolean inOverAllEnd = false;
	private boolean inEventStart = false;
	private boolean inEventEnd = false;

	// Record values.
	private String itemCategoryClass;
	private String itemRoad;
	private String itemRegion;
	private String itemCounty;
	private String itemLatitude;
	private String itemLongitude;
	private String itemOverallStart;
	private String itemOverallEnd;
	private String itemEventStart;
	private String itemEventEnd;

	/**
	 * Process start tag
	 * 
	 * @param tag
	 */
	@Override
	public boolean processStartTag(String tag)
	{
		if (tag.equals(TrafficData.TEXT_ROAD))
		{
			inRoad = true;
		}
		else if (tag.equals(TrafficData.TEXT_REGION))
		{
			inRegion = true;
		}
		else if (tag.equals(TrafficData.TEXT_CATEGORY))
		{
			inCategoryClass = true;
		}
		else if (tag.equals(TrafficData.TEXT_COUNTY))
		{
			inCounty = true;
		}
		else if (tag.equals(TrafficData.TEXT_LATITUDE))
		{
			inLatitude = true;
		}
		else if (tag.equals(TrafficData.TEXT_LONGITUDE))
		{
			inLongitude = true;
		}
		else if (tag.equals(TrafficData.TEXT_EVENT_START))
		{
			inEventStart = true;
		}
		else if (tag.equals(TrafficData.TEXT_EVENT_END))
		{
			inEventEnd = true;
		}
		else if (tag.equals(TrafficData.TEXT_OVERALL_START))
		{
			inOverAllStart = true;
		}
		else if (tag.equals(TrafficData.TEXT_OVERALL_END))
		{
			inOverAllEnd = true;
		}

		return false;
	}

	/**
	 * Process start tag
	 * 
	 * @param tag
	 */
	@Override
	public boolean processEndTag(String tag)
	{

		if (tag.equals(TrafficData.TEXT_ROAD))
		{
			inRoad = false;
		}
		else if (tag.equals(TrafficData.TEXT_REGION))
		{
			inRegion = false;
		}
		else if (tag.equals(TrafficData.TEXT_CATEGORY))
		{
			inCategoryClass = false;
		}
		else if (tag.equals(TrafficData.TEXT_COUNTY))
		{
			inCounty = false;
		}
		else if (tag.equals(TrafficData.TEXT_LATITUDE))
		{
			inLatitude = false;
		}
		else if (tag.equals(TrafficData.TEXT_LONGITUDE))
		{
			inLongitude = false;
		}
		else if (tag.equals(TrafficData.TEXT_EVENT_START))
		{
			inEventStart = false;
		}
		else if (tag.equals(TrafficData.TEXT_EVENT_END))
		{
			inEventEnd = false;
		}
		else if (tag.equals(TrafficData.TEXT_OVERALL_START))
		{
			inOverAllStart = false;
		}
		else if (tag.equals(TrafficData.TEXT_OVERALL_END))
		{
			inOverAllEnd = false;
		}

		return false;
	}

	/**
	 * Collect text values depending on conditions.
	 * 
	 * @param text
	 */
	@Override
	public void processText(String text)
	{
		if (inRoad)
		{
			itemRoad = text;
		}
		else if (inRegion)
		{
			itemRegion = text;
		}
		else if (inCategoryClass)
		{
			itemCategoryClass = text;
		}
		else if (inCounty)
		{
			itemCounty = text;
		}
		else if (inLatitude)
		{
			itemLatitude = text;
		}
		else if (inLongitude)
		{
			itemLongitude = text;
		}
		else if (inEventStart)
		{
			itemEventStart = text;
		}
		else if (inEventEnd)
		{
			itemEventEnd = text;
		}
		else if (inOverAllStart)
		{
			itemOverallStart = text;
		}
		else if (inOverAllEnd)
		{
			itemOverallEnd = text;
		}

	}

	/**
	 * Build record from parsed data.
	 * 
	 */
	@Override
	public void populateRecord(TrafficData record)
	{
		// Populate record.
		record.setCategoryClass(itemCategoryClass);
		record.setRoad(itemRoad);
		record.setRegion(itemRegion);
		record.setCounty(itemCounty);
		record.setLatitude(itemLatitude);
		record.setLongitude(itemLongitude);
		record.setOverAllStart(itemOverallStart);
		record.setOverAllEnd(itemOverallEnd);
		record.setEventStart(itemEventStart);
		record.setEventEnd(itemEventEnd);

		// Reset parser fields.
		reset();
	}

	/**
	 * Reset parsed strings.
	 * 
	 */
	@Override
	public void reset()
	{
		itemCategoryClass = "";
		itemRoad = "";
		itemRegion = "";
		itemCounty = "";
		itemLatitude = "";
		itemLongitude = "";
		itemOverallStart = "";
		itemOverallEnd = "";
		itemEventStart = "";
		itemEventEnd = "";
	}

	/**
	 * Inside text
	 * 
	 * @return
	 */
	@Override
	public boolean inTarget()
	{
		return inCategoryClass || inRoad || inRegion || inCounty || inLatitude || inLongitude || inOverAllStart || inOverAllEnd || inEventStart || inEventEnd;
	}

}
