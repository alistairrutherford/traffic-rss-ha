package com.netthreads.rss.data.traffic;

import com.netthreads.rss.DataFactory;

/**
 * Traffic data factory.
 *
 */
public class TrafficDataFactory implements DataFactory<TrafficData>
{
	@Override
	public TrafficData createRecord()
	{
		return new TrafficData();
	}
	
}
