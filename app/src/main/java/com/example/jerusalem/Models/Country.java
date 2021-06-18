package com.example.cov19.Models;

public class Country {

    String countryName ;
    String totalCases ;
    String image;
    String latCountry ;
    String longCountry;
    String recovered;
    String deaths;

    public Country(String latCountry, String longCountry , String countryName , String totalCases , String image , String recovered ,String deaths) {
        this.latCountry = latCountry;
        this.longCountry = longCountry;
        this.countryName = countryName;
        this.totalCases = totalCases;
        this.image = image;
        this.recovered = recovered;
        this.deaths = deaths;

    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public Country() {
    }

    public Country(String latCountry, String longCountry , String countryName , String totalCases , String image) {
        this.latCountry = latCountry;
        this.longCountry = longCountry;
        this.countryName = countryName;
        this.totalCases = totalCases;
        this.image = image;
    }

    public Country(String countryName, String totalCases, String image) {
        this.countryName = countryName;
        this.totalCases = totalCases;
        this.image = image;
    }

    public String getLatCountry() {
        return latCountry;
    }

    public void setLatCountry(String latCountry) {
        this.latCountry = latCountry;
    }

    public String getLongCountry() {
        return longCountry;
    }

    public void setLongCountry(String longCountry) {
        this.longCountry = longCountry;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(String totalCases) {
        this.totalCases = totalCases;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
