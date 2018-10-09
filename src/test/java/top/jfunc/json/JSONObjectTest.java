package top.jfunc.json;

import top.jfunc.json.impl.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/**
 * 使用的时候不会出现跟具体JSON框架耦合的代码
 */
public class JSONObjectTest {

    JsonObject jsonObject = new JSONObject();

    @Before
    public void init(){
        jsonObject.put("k1","v1");
        jsonObject.put("k2","v2");
        jsonObject.put("boolean1",true);
        jsonObject.put("integer1",1);
        jsonObject.put("long1",1L);
        jsonObject.put("double1",1D);
        jsonObject.put("float1",1F);
        jsonObject.put("bigInteger1",new BigInteger("1"));
        jsonObject.put("bigDecimal1",new BigDecimal("1"));

        JSONObject object = new JSONObject();
        object.put("k1" , "v1");
        jsonObject.put("k3", new JSONObject(object.unwrap()));
    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals("v1" , jsonObject.get("k1"));
        jsonObject.setStrict(false);
        Assert.assertNull(jsonObject.get("k4"));
    }

    @Test
    public void getDefault() throws Exception {
        Assert.assertEquals("v2" , jsonObject.get("k2" , "kv2"));
        Assert.assertEquals("kv2" , jsonObject.get("k4" , "kv2"));
    }

    @Test
    public void getJsonObject() throws Exception {
        JsonObject k3 = jsonObject.getJsonObject("k3");
        Assert.assertEquals("v1" , k3.getString("k1"));
    }

    @Test
    public void getJsonArray() throws Exception {
    }

    @Test
    public void getString() throws Exception {
        String v1 = jsonObject.getString("k1");
        Assert.assertEquals("v1" , v1);
    }

    @Test
    public void getStringDefault() throws Exception {
        String v1 = jsonObject.getString("k1" , "vv");
        Assert.assertEquals("v1" , v1);

        String v4 = jsonObject.getString("k4" , "vv");
        Assert.assertEquals("vv" , v4);
    }

    @Test
    public void getBoolean() throws Exception {
        Boolean boolean1 = jsonObject.getBoolean("boolean1");
        Assert.assertEquals(true , boolean1);
    }

    @Test
    public void getBooleanDefault() throws Exception {
        Boolean boolean1 = jsonObject.getBoolean("boolean1" , false);
        Assert.assertEquals(true, boolean1);

        Boolean v4 = jsonObject.getBoolean("k4" , false);
        Assert.assertEquals(false , v4);
    }

    @Test
    public void getInteger() throws Exception {
        Integer integer1 = jsonObject.getInteger("integer1");
        Assert.assertEquals((Integer) 1 , integer1);
    }

    @Test
    public void getIntegerDefault() throws Exception {
        Integer integer1 = jsonObject.getInteger("integer1" , 1);
        Assert.assertEquals((Integer) 1, integer1);

        Integer v4 = jsonObject.getInteger("k4" , 2);
        Assert.assertEquals((Integer) 2 , v4);
    }

    @Test
    public void getLong() throws Exception {
        Long long1 = jsonObject.getLong("long1");
        Assert.assertEquals(Long.valueOf(1) , long1);
    }

    @Test
    public void getLongDefault() throws Exception {
        Long long1 = jsonObject.getLong("long1" , 1L);
        Assert.assertEquals((Long)1L, long1);

        Long v4 = jsonObject.getLong("k4" , 2L);
        Assert.assertEquals(Long.valueOf(2) , v4);
    }

    @Test
    public void getFloat() throws Exception {
        Float float1 = jsonObject.getFloat("float1");
        Assert.assertEquals(Float.valueOf(1) , float1);
    }

    @Test
    public void getFloatDefault() throws Exception {
        Float float1 = jsonObject.getFloat("float1" , 1F);
        Assert.assertEquals(Float.valueOf(1), float1);

        Float v4 = jsonObject.getFloat("k4" , 2F);
        Assert.assertEquals(Float.valueOf(2) , v4);
    }

    @Test
    public void getDouble() throws Exception {
        Double double1 = jsonObject.getDouble("double1");
        Assert.assertEquals(Double.valueOf(1) , double1);
    }

    @Test
    public void getDoubleDefault() throws Exception {
        Double double1 = jsonObject.getDouble("double1" , 1D);
        Assert.assertEquals((Double) 1D, double1);

        Double v4 = jsonObject.getDouble("k4" , 2D);
        Assert.assertEquals(Double.valueOf(2) , v4);
    }

    @Test
    public void getBigInteger() throws Exception {
        BigInteger bigInteger1 = jsonObject.getBigInteger("bigInteger1");
        Assert.assertEquals(new BigInteger("1") , bigInteger1);
    }

    @Test
    public void getBigIntegerDefault() throws Exception {
        BigInteger bigInteger1 = jsonObject.getBigInteger("bigInteger1" , new BigInteger("2"));
        Assert.assertEquals(new BigInteger("1") , bigInteger1);

        BigInteger v4 = jsonObject.getBigInteger("k4" , new BigInteger("2"));
        Assert.assertEquals(new BigInteger("2") , v4);
    }

    @Test
    public void getBigDecimal() throws Exception {
        BigDecimal bigDecimal1 = jsonObject.getBigDecimal("bigDecimal1");
        Assert.assertEquals(new BigDecimal("1") , bigDecimal1);
    }

    @Test
    public void getBigDecimalDefault() throws Exception {
        BigDecimal bigDecimal1 = jsonObject.getBigDecimal("bigDecimal1" , new BigDecimal("2"));
        Assert.assertEquals(new BigDecimal("1") , bigDecimal1);

        BigDecimal v4 = jsonObject.getBigDecimal("k4" , new BigDecimal("2"));
        Assert.assertEquals(new BigDecimal("2") , v4);
    }

    @Test
    public void getClassObj() throws Exception {
        String k1 = jsonObject.get("k1", String.class);
        Assert.assertEquals("v1" , k1);
    }

    @Test
    public void keySet() throws Exception {
        Set<String> keySet = jsonObject.keySet();
        System.out.println(keySet);
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(10 , jsonObject.size());
    }

    @Test
    public void isEmpty() throws Exception {
        Assert.assertFalse(jsonObject.isEmpty());
    }

    @Test
    public void containsKey() throws Exception {
        Assert.assertTrue(jsonObject.containsKey("k1"));
        Assert.assertFalse(jsonObject.containsKey("k5"));
    }

    @Test
    public void containsValue() throws Exception {
        Assert.assertTrue(jsonObject.containsValue("v1"));
        Assert.assertFalse(jsonObject.containsValue("vv"));
    }

    @Test
    public void put() throws Exception {
        JsonObject put = jsonObject.put("k4", "v4");
        Assert.assertEquals(11 , jsonObject.size());
        Assert.assertEquals(11 , put.size());
        Assert.assertEquals("v4" , jsonObject.getString("k4"));
        Assert.assertEquals("v4" , put.getString("k4"));
    }

    @Test
    public void putAll() throws Exception {
        Map<String , Object> map = new HashMap<>(2);
        map.put("k5" , "v5");
        map.put("k6" , "v6");
        JsonObject putAll = this.jsonObject.putAll(map);
        Assert.assertEquals(12 , jsonObject.size());
        Assert.assertEquals(12 , putAll.size());

        Assert.assertEquals("v5" , jsonObject.getString("k5"));
        Assert.assertEquals("v6" , jsonObject.getString("k6"));
        Assert.assertEquals("v5" , putAll.getString("k5"));
        Assert.assertEquals("v6" , putAll.getString("k6"));
    }

    @Test
    public void clear() throws Exception {
        jsonObject.clear();
        Assert.assertEquals(0 , jsonObject.size());
    }

    @Test
    public void remove() throws Exception {
        Object k1 = jsonObject.remove("k1");
        Assert.assertEquals(9 , jsonObject.size());
    }

    @Test
    public void parse() throws Exception {
        JsonObject parse = jsonObject.parse("{\"xx\":\"ss\" , \"ss\":{\"cc\":12}}");
        Assert.assertEquals("ss"  , parse.getString("xx"));
        Assert.assertEquals((Integer) 12 , parse.getJsonObject("ss").getInteger("cc"));
    }

    @Test
    public void serialize() throws Exception {
        JsonObjectBean javaBean = new JsonObjectBean();
        javaBean.setK1("11");
        String serialize = jsonObject.serialize(javaBean,"boolean1");
        System.out.println(serialize);
    }

    @Test
    public void deserialize() throws Exception {
        JsonObjectBean deserialize = jsonObject.deserialize("{\"k1\":\"11\"}", JsonObjectBean.class);
        System.out.println(deserialize);

        JsonObjectBean deserialize1 = jsonObject.deserialize("{\"bigDecimal1\":1,\"bigInteger1\":1,\"boolean1\":true,\"double1\":1,\"float1\":1,\"integer1\":1,\"k1\":\"v1\",\"k2\":\"v2\",\"k3\":{\"k1\":\"v1\"},\"long1\":1}", JsonObjectBean.class);
        System.out.println(deserialize1);
    }

    @Test
    public void testToString() throws Exception {
        System.out.println(jsonObject.toString());
    }

    @Test
    public void testConvert() throws Exception {
        JsonObject parse = jsonObject.parse("{\"bigDecimal1\":1,\"bigInteger1\":1,\"boolean1\":true,\"double1\":1,\"float1\":1,\"integer1\":1,\"k1\":\"v1\",\"k2\":\"v2\",\"k3\":{\"k1\":\"v1\"},\"long1\":1}");
        System.out.println(parse.toString());
    }
}