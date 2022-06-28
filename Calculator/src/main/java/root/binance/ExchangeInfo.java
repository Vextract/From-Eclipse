package root.binance;

import java.util.List;

public interface ExchangeInfo {

    CurrenciesRate getRate(String filter);

    List<CurrenciesRate> getAllConversionPairs();
}
