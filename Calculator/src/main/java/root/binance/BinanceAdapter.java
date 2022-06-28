package root.binance;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class BinanceAdapter implements ExchangeInfo {

    private BinanceConnector connector;

    private JSONParser parser = new JSONParser();

    @Autowired
    public BinanceAdapter(BinanceConnector connector) {
        this.connector = connector;
    }

    @Override
    public CurrenciesRate getRate(String filter) {
        JSONObject jsonObject;
        try {
            jsonObject = (JSONObject) parser.parse(connector.getResponse(filter));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        CurrenciesRate javaObject = new CurrenciesRate();

        String bothCurrencies = (String) jsonObject.get("symbol");
        String fromCurrency = bothCurrencies.substring(0,3);
        String toCurrency = bothCurrencies.substring(3);
        double conversionRate = Double.parseDouble((String) jsonObject.get("price"));

        javaObject.setFromCurrency(fromCurrency);
        javaObject.setToCurrency(toCurrency);
        javaObject.setConversionRate(conversionRate);

        return javaObject;
    }


    @Override
    public List<CurrenciesRate> getAllConversionPairs() {
        List<CurrenciesRate> list = new ArrayList<>();

            JSONObject jsonObject;
            try {
                jsonObject = (JSONObject) parser.parse(connector.getResponse("all"));
            } catch (IOException | ParseException e) {
                throw new RuntimeException(e);
            }

            JSONArray symbols = (JSONArray) jsonObject.get("symbols");
            for (int i = 0; i < symbols.size(); i++) {
                CurrenciesRate javaObject = new CurrenciesRate();
                JSONObject bothCurrencies = (JSONObject) symbols.get(i);
                String bothCurrencies1 = (String) bothCurrencies.get("symbol");
                String fromCurrency = bothCurrencies1.substring(0,3);
                String toCurrency = bothCurrencies1.substring(3);

                javaObject.setFromCurrency(fromCurrency);
                javaObject.setToCurrency(toCurrency);

                list.add(javaObject);
            }

        return list;
    }
}
