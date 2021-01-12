package com.spring.demo.model;

public class LocationStats {
	
	private String state;
	private String country;
	private String latitude;
	private String longitude;
	private int latestCases;
	private int diffFromPrevDay;
	
	public int getDiffFromPrevDay() {
		return diffFromPrevDay;
	}
	public void setDiffFromPrevDay(int diffFromPrevDay) {
		this.diffFromPrevDay = diffFromPrevDay;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public int getLatestCases() {
		return latestCases;
	}
	public void setLatestCases(int latestCases2) {
		this.latestCases = latestCases2;
	}
	@Override
	public String toString() {
		return "LocationStats [country=" + country +", state=" + state + ", latitude=" + latitude + ", longitude="
				+ longitude + ", latestCases=" + latestCases + "]";
	}
	
	

}
