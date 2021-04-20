package com.example.CovidCaseTracker.services;

import com.example.CovidCaseTracker.models.Locations;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CovidDataServices {

    private String covid_confirmed_url = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    private List<Locations> all_location = new ArrayList<>();

    public List<Locations> getAll_location() {
        return all_location;
    }

    @PostConstruct
    @Scheduled(cron = "* * * 1 * *")
    public void fetch_confirmed_cases_data() throws IOException, InterruptedException {
        List<Locations> new_location = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(covid_confirmed_url))
                .build();

        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

        StringReader data_reader = new StringReader(httpResponse.body());

        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(data_reader);

        for (CSVRecord record : records) {
            Locations locations = new Locations();
            locations.setProvince(record.get("Province/State"));
            locations.setCountry(record.get("Country/Region"));
            locations.setTotal_cases(Integer.parseInt(record.get(record.size()-1)));
            int recent_cases = Integer.parseInt(record.get(record.size()-1));
            int last_cases = Integer.parseInt(record.get(record.size()-2));
            locations.setDaily_case(recent_cases-last_cases);
            new_location.add(locations);

        }

        this.all_location = new_location;

    }
}
