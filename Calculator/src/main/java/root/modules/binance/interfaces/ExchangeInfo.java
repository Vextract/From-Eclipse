package root.modules.binance.interfaces;

import java.util.List;

import root.modules.binance.entities.CurrenciesRate;

public interface ExchangeInfo {

    CurrenciesRate getRate(String filter);

    List<CurrenciesRate> getAllConversionPairs();
}
