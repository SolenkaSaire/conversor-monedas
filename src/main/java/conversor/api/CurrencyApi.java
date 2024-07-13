package main.java.conversor.api;

import main.java.conversor.model.Conversor;
import main.java.conversor.model.Country;
import main.java.conversor.util.HttpClientClass;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CurrencyApi extends HttpClientClass {
    private String apiUrl = "https://v6.exchangerate-api.com/v6/" + System.getenv("EXCHANGE-API-KEY");

    public void convertCurrency(Conversor currencyConversion) {
        String baseCurrencyCode = currencyConversion.getBaseCode();
        String targetCurrencyCode = currencyConversion.getTargetCode();
        Double conversionAmount = currencyConversion.getAmount();

        URI apiEndpoint = URI.create(apiUrl + "/pair/" + baseCurrencyCode + "/" + targetCurrencyCode + "/" + conversionAmount);

        System.out.println(apiEndpoint);
        try {
            JsonObject jsonResponse = getJsonResponseFromApi(apiEndpoint);

            double convertedAmount = jsonResponse.get("conversion_result").getAsDouble();

            currencyConversion.setAmountConverted(convertedAmount);
            currencyConversion.setCreatedAt(LocalDateTime.now().toString());
        } catch (Exception e) {
            throw new RuntimeException("Currency conversion failed.");
        }
    }

    public List<Country> getCountries() {
        URI apiEndpoint = URI.create(apiUrl + "/codes");
        List<Country> countries = new ArrayList<>();
        try {
            JsonObject jsonResponse = getJsonResponseFromApi(apiEndpoint);

            JsonArray countryArray = jsonResponse.getAsJsonArray("supported_codes");
            for (int i = 0; i < countryArray.size(); i++) {
                JsonArray countryData = countryArray.get(i).getAsJsonArray();
                String countryCode = countryData.get(0).getAsString();
                String countryName = countryData.get(1).getAsString();
                countries.add(new Country(countryCode, countryName));
            }
            return countries;
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch countries from API.");
        }
    }
}