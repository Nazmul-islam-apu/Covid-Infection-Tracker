package com.example.CovidCaseTracker.models;

public class Locations {
    private String province;
    private String country;
    private int daily_case;
    private int total_cases;

    public int getTotal_cases() {
        return total_cases;
    }

    public void setTotal_cases(int total_cases) {
        this.total_cases = total_cases;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getDaily_case() {
        return daily_case;
    }

    public void setDaily_case(int daily_case) {
        this.daily_case = daily_case;
    }

    @Override
    public String toString() {
        return "Locations{" +
                "province='" + province + '\'' +
                ", country='" + country + '\'' +
                ", daily_case=" + daily_case +
                ", total_cases=" + total_cases +
                '}';
    }
}
