package com.spring.demo.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.spring.demo.model.LocationStats;

@Service
public class CoronaVirusDataService {
	
	private static String VIRUS_DATA_URL ="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/"
			+ "csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	
	private List<LocationStats> allStatistics = new ArrayList<>();
	
	public List<LocationStats> getAllStatistics() {
		return allStatistics;
	}

	@PostConstruct
	@Scheduled(cron = "* * 1 * * *") //runs every second and specify in main that this method has to run every second (first star is sec second star is min then hourv, day, month , year specifyng 1 means first hr of everyday) 
	public void fetchVirusData() throws IOException, InterruptedException
	{
		List<LocationStats> newStatistics = new ArrayList<>();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = (HttpRequest) HttpRequest.newBuilder().uri(URI.create(VIRUS_DATA_URL)).build();
		
		HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		StringReader readCSV = new StringReader(httpResponse.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(readCSV);
		for (CSVRecord record : records) {
			
		    LocationStats locationStats= new LocationStats();
		    
		    locationStats.setCountry(record.get("Country/Region"));
		    locationStats.setState(record.get("Province/State"));
		    locationStats.setLatitude(record.get("Lat"));
		    locationStats.setLongitude(record.get("Long"));
			/* locationStats.setLatestCases(record.get(record.size()-1)); */
		    int latestCases= Integer.parseInt(record.get(record.size()-1));
		    int prevDayCases=Integer.parseInt(record.get(record.size()-2));
		    
		    locationStats.setLatestCases(latestCases);
		    locationStats.setDiffFromPrevDay(latestCases-prevDayCases);
		    
		    System.out.println(locationStats);
		    newStatistics.add(locationStats);
		    		
			
		}
		
		this.allStatistics=newStatistics;
				
	}

}
