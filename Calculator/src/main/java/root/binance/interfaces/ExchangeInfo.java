package root.binance.interfaces;

import java.util.List;

import root.binance.valueObjects.CurrenciesRate;

public interface ExchangeInfo {

    CurrenciesRate getRate(String filter);

    List<CurrenciesRate> getAllConversionPairs();
}
