/**
 * -----------------------------------------------------------------------
 * Copyright 2015 - Alistair Rutherford - www.netthreads.co.uk
 * -----------------------------------------------------------------------
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.netthreads.rss.data.traffic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.netthreads.rss.PullParser;

/**
 * This is a _simple_ XML Pull parser for RSS xml feed.
 *
 * Type: RSS Data
 *
 * Sample URL:
 *
 */
@SuppressWarnings("serial")
public class TrafficPullParser implements PullParser<TrafficData>
{
	private static final String DEFAULT_SEVERITY = "minor";
	
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
	private String itemCategory;
	private String itemSeverity;
	private String itemRoad;
	private String itemRegion;
	private String itemCounty;
	private String itemLatitude;
	private String itemLongitude;
	private String itemOverallStart;
	private String itemOverallEnd;
	private String itemEventStart;
	private String itemEventEnd;
	
	private static final Map<String, String> severityMap = new HashMap<String, String>()
	{
		{
			put("minor", "minor");
			put("no delay", "minor");
			put("moderate", "moderate");
			put("severe", "severe");
		}
	};
	
    private static final Map<String, String> categoryClassMap = new HashMap<String, String>()
	{
		{
			put("roadworks", "roadwork");
			put("resurfacing", "roadwork");
			put("bridge or barrier repairs", "roadwork");
			put("sports event", "incident");
			put("major event", "incident");			
			put("accident","accident");
			put("aerious accident","accident");
			put("congestion","congestion");
			put("broken down vehicle","congestion");
			put("vehicle on fire","incident");
			put("other unplanned","incident");
		}
	};
	
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
			String severity = hasSeverity(text);
			
			if (severity != null & !severity.isEmpty())
			{
				itemSeverity = severity;
			}
			else
			{
				itemCategory = text;
			}
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
		record.setCategory(itemCategory);
		record.setSeverity(itemSeverity);
		record.setRoad(itemRoad);
		record.setRegion(itemRegion);
		record.setCounty(itemCounty);
		record.setLatitude(itemLatitude);
		record.setLongitude(itemLongitude);
		record.setOverAllStart(itemOverallStart);
		record.setOverAllEnd(itemOverallEnd);
		record.setEventStart(itemEventStart);
		record.setEventEnd(itemEventEnd);
		
		String categoryClass = categoryClassMap.get(itemCategory.toLowerCase().trim());
		if (categoryClass == null)
		{
			categoryClass = "unknown";
		}
		record.setCategoryClass(categoryClass);
		
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
		itemCategory = "";
		itemSeverity = DEFAULT_SEVERITY;
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
	 * Extract severity - if possible.
	 *
	 * @param text
	 *
	 * @return text which matches severity signature
	 */
	private String hasSeverity(String text)
	{
		String severity = "";
		boolean found = false;
		Iterator<String> iterator = severityMap.keySet().iterator();
		while (iterator.hasNext() && !found)
		{
			String key = iterator.next();
			
			int index = text.toLowerCase().indexOf(key);
			if (index >= 0)
			{
				String keyVal = text.substring(index, key.length());
				severity = severityMap.get(keyVal.toLowerCase());
				found = true;
			}
		}
		
		return severity;
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
