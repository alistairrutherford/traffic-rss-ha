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

import com.netthreads.rss.RssData;

/**
 * Traffic data data class which matched HA XML definition.
 */
public class TrafficData extends RssData
{
	// ------------------------------------------------------------------------
	// Constants
	// ------------------------------------------------------------------------
	public static final String TEXT_ROAD = "road";
	public static final String TEXT_SEVERITY = "severity";
	public static final String TEXT_REGION = "region";
	public static final String TEXT_COUNTY = "county";
	public static final String TEXT_LATITUDE = "latitude";
	public static final String TEXT_LONGITUDE = "longitude";
	public static final String TEXT_OVERALL_START = "overallStart";
	public static final String TEXT_OVERALL_END = "overallEnd";
	public static final String TEXT_EVENT_START = "eventStart";
	public static final String TEXT_EVENT_END = "eventEnd";
	public static final String TEXT_DIRECTION = "direction";
	
	private String category;
	private String categoryClass;
	private String severity;
	private String road;
	private String region;
	private String county;
	private String latitude;
	private String longitude;
	private String overAllStart;
	private String overAllEnd;
	private String eventStart;
	private String eventEnd;
	private String direction;
	
	public String getCategory()
	{
		return category;
	}
	
	public void setCategory(String category)
	{
		this.category = category;
	}
	
	public String getCategoryClass()
	{
		return categoryClass;
	}
	
	public void setCategoryClass(String categoryClass)
	{
		this.categoryClass = categoryClass;
	}
	
	public String getRoad()
	{
		return road;
	}
	
	public void setRoad(String road)
	{
		this.road = road;
	}
	
	public String getRegion()
	{
		return region;
	}
	
	public void setRegion(String region)
	{
		this.region = region;
	}
	
	public String getCounty()
	{
		return county;
	}
	
	public void setCounty(String county)
	{
		this.county = county;
	}
	
	public String getLatitude()
	{
		return latitude;
	}
	
	public void setLatitude(String latitude)
	{
		this.latitude = latitude;
	}
	
	public String getLongitude()
	{
		return longitude;
	}
	
	public void setLongitude(String longitude)
	{
		this.longitude = longitude;
	}
	
	public String getOverAllStart()
	{
		return overAllStart;
	}
	
	public void setOverAllStart(String overAllStart)
	{
		this.overAllStart = overAllStart;
	}
	
	public String getOverAllEnd()
	{
		return overAllEnd;
	}
	
	public void setOverAllEnd(String overAllEnd)
	{
		this.overAllEnd = overAllEnd;
	}
	
	public String getEventStart()
	{
		return eventStart;
	}
	
	public void setEventStart(String eventStart)
	{
		this.eventStart = eventStart;
	}
	
	public String getEventEnd()
	{
		return eventEnd;
	}
	
	public void setEventEnd(String eventEnd)
	{
		this.eventEnd = eventEnd;
	}
	
	public String getSeverity()
	{
		return severity;
	}
	
	public void setSeverity(String severity)
	{
		this.severity = severity;
	}
	
	@Override
	public void setTitle(String title)
	{
		super.setTitle(title);
		
		if (title.indexOf('|') > 0)
		{
			String[] parts = title.split("\\|");
			
			int length = parts.length;
			if (length > 0)
			{
				super.setTitle(parts[0].trim());
			}
			
			if (length > 1)
			{
				direction = parts[1].trim();
			}
			
			if (length > 2)
			{
				categoryClass = parts[2].trim();
			}
		}
	}
	
	public String getDirection()
	{
		return direction;
	}
	
	public void setDirection(String direction)
	{
		this.direction = direction;
	}
	
	@Override
	public String toString()
	{
		return super.toString() + ", " + category + ", " + categoryClass + ", " + road + ", " + region + ", " + county + ", " + latitude + ", " + longitude + ", " + overAllStart + ", " + overAllEnd + ", " + eventStart + ", " + eventEnd + ", " + severity;
	}
}
