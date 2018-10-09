package top.jfunc.json;

import top.jfunc.json.annotation.JsonField;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

/**
 * @author xiongshiyan at 2018/6/11
 */
public class JsonObjectBean {
    @JsonField("kk")
    private String k1;
    @JsonField(serialize = false)
    private String k2;
    private Boolean boolean1;
    private Integer integer1;
    private Long long1;
    private Double double1;
    private Float float1;
    private BigInteger bigInteger1;
    private BigDecimal bigDecimal1;
    private Map<String , String> k3;

    public String getK1() {
        return k1;
    }

    public void setK1(String k1) {
        this.k1 = k1;
    }

    public String getK2() {
        return k2;
    }

    public void setK2(String k2) {
        this.k2 = k2;
    }

    public Boolean getBoolean1() {
        return boolean1;
    }

    public void setBoolean1(Boolean boolean1) {
        this.boolean1 = boolean1;
    }

    public Integer getInteger1() {
        return integer1;
    }

    public void setInteger1(Integer integer1) {
        this.integer1 = integer1;
    }

    public Long getLong1() {
        return long1;
    }

    public void setLong1(Long long1) {
        this.long1 = long1;
    }

    public Double getDouble1() {
        return double1;
    }

    public void setDouble1(Double double1) {
        this.double1 = double1;
    }

    public Float getFloat1() {
        return float1;
    }

    public void setFloat1(Float float1) {
        this.float1 = float1;
    }

    public BigInteger getBigInteger1() {
        return bigInteger1;
    }

    public void setBigInteger1(BigInteger bigInteger1) {
        this.bigInteger1 = bigInteger1;
    }

    public BigDecimal getBigDecimal1() {
        return bigDecimal1;
    }

    public void setBigDecimal1(BigDecimal bigDecimal1) {
        this.bigDecimal1 = bigDecimal1;
    }

    public Map<String, String> getK3() {
        return k3;
    }

    public void setK3(Map<String, String> k3) {
        this.k3 = k3;
    }

    @Override
    public String toString() {
        return "JsonObjectBean{" +
                "k1='" + k1 + '\'' +
                ", k2='" + k2 + '\'' +
                ", boolean1=" + boolean1 +
                ", integer1=" + integer1 +
                ", long1=" + long1 +
                ", double1=" + double1 +
                ", float1=" + float1 +
                ", bigInteger1=" + bigInteger1 +
                ", bigDecimal1=" + bigDecimal1 +
                ", k3=" + k3 +
                '}';
    }
}
