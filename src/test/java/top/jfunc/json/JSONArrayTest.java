package top.jfunc.json;

import top.jfunc.json.impl.JSONArray;
import top.jfunc.json.impl.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 使用的时候不会出现跟具体JSON框架耦合的代码
 */
public class JSONArrayTest {
    private JsonArray jsonArray = new JSONArray();
    @Test
    public void unwrap() throws Exception {
        Object unwrap = jsonArray.unwrap();
        Assert.assertTrue(List.class.isAssignableFrom(unwrap.getClass()));
    }

    @Test
    public void parse() throws Exception {
        String src = "[\"12\",\"13\"]";
        JsonArray array = jsonArray.parse(src);
        Assert.assertEquals(2 , jsonArray.size());
        Assert.assertEquals(2 , array.size());
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(0 , jsonArray.size());
        jsonArray.put("12");
        Assert.assertEquals(1 , jsonArray.size());
    }

    @Test(expected = JsonException.class)
    public void get() throws Exception {
        jsonArray.setStrict(true);
        jsonArray.put("12");
        Assert.assertEquals("12" , jsonArray.get(0));

        jsonArray.get(1);
    }

    @Test
    public void getString() throws Exception {
        jsonArray.put("12");
        Assert.assertEquals("12" , jsonArray.getString(0));
    }

    @Test
    public void getBoolean() throws Exception {
    }

    @Test
    public void getInteger() throws Exception {
    }

    @Test
    public void getLong() throws Exception {
    }

    @Test
    public void getDouble() throws Exception {
    }

    @Test
    public void getFloat() throws Exception {
    }

    @Test
    public void getBigInteger() throws Exception {
    }

    @Test
    public void getBigDecimal() throws Exception {
    }

    @Test
    public void getJsonObject() throws Exception {
        JsonObject jsonObject = new JSONObject();
        jsonObject.put("k1" , "v1");
        jsonObject.put("k2" , "v2");
        jsonArray.put(jsonObject);
        Assert.assertEquals("{\"k1\":\"v1\",\"k2\":\"v2\"}" , jsonArray.getJsonObject(0).toString());
    }

    @Test
    public void getJsonArray() throws Exception {
        JsonArray array = new JSONArray();
        array.put("12");
        array.put("13");
        JsonArray array2 = new JSONArray();
        array2.put("122");
        array2.put("132");

        jsonArray.put(array);
        jsonArray.put(array2);
        Assert.assertEquals("[\"12\",\"13\"]" , jsonArray.getJsonArray(0).toString());

    }

    @Test
    public void remove() throws Exception {
        JsonArray array = new JSONArray();
        array.put("12");
        array.put("13");
        JsonArray array2 = new JSONArray();
        array2.put("122");
        array2.put("132");

        jsonArray.put(array);
        jsonArray.put(array2);

        jsonArray.remove(0);
        Assert.assertEquals("[[\"122\",\"132\"]]" , jsonArray.toString());
    }

    @Test
    public void clear() throws Exception {
        JsonArray array = new JSONArray();
        array.put("12");
        array.put("13");
        JsonArray array2 = new JSONArray();
        array2.put("122");
        array2.put("132");

        jsonArray.clear();
        Assert.assertEquals("[]" , jsonArray.toString());
    }

    @Test
    public void put() throws Exception {
        jsonArray.put("12");
        jsonArray.put("13");
        Assert.assertEquals(2 , jsonArray.size());
    }

    @Test
    public void putWithIndex() throws Exception {
        jsonArray.put("12");
        jsonArray.put(0 ,"13");
        Assert.assertEquals(1 , jsonArray.size());
        Assert.assertEquals("13" , jsonArray.getString(0));
    }

    @Test
    public void putAll() throws Exception {
        jsonArray.putAll(Arrays.asList("12","12","13","13"));
        Assert.assertEquals(String.class , jsonArray.get(0).getClass());
        Assert.assertEquals(4 , jsonArray.size());
    }

    @Test
    public void testToString() throws Exception {
        JsonObject jsonObject = new JSONObject();
        jsonObject.put("k1" , "v1");
        jsonObject.put("k2" , "v2");
        jsonArray.put(jsonObject);
        Assert.assertEquals("[{\"k1\":\"v1\",\"k2\":\"v2\"}]" , jsonArray.toString());
    }
    @Test
    public void testFromList() throws Exception {
        List<Object> list = Arrays.asList("12","21",12);
        JsonArray array = jsonArray.fromList(list);
        Assert.assertEquals("[\"12\",\"21\",12]" , array.toString());
    }
}