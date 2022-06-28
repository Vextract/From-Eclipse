package root.main;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CalculationPackage {

    private double a;
    private double b;
    private String sign;

    public CalculationPackage(@JsonProperty("a") double a,
                              @JsonProperty("b") double b,
                              @JsonProperty("sign") String sign) {
        this.a = a;
        this.b = b;
        this.sign = sign;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
