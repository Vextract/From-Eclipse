package root.main;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CalculationPackage {

    private Double a;
    private Double b;
    private String sign;

    public CalculationPackage(@JsonProperty("a") Double a,
                              @JsonProperty("b") Double b,
                              @JsonProperty("sign") String sign) {
        this.a = a;
        this.b = b;
        this.sign = sign;
    }

    public Double getA() {
        return a;
    }

    public void setA(Double a) {
        this.a = a;
    }

    public Double getB() {
        return b;
    }

    public void setB(Double b) {
        this.b = b;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
