package root.binance;

public class CurrenciesRate {

    private String fromCurrency;
    private String toCurrency;
    private double conversionRate;

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public double getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(double conversionRate) {
        this.conversionRate = conversionRate;
    }

    @Override
    public String toString() {
        if (conversionRate == 0.0) {
            return fromCurrency + " to " + toCurrency;
        } else {
            return fromCurrency + " to " + toCurrency + "\n" + "Conversion rate: " + conversionRate + "\n";
        }
    }
}
