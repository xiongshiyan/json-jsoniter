package top.jfunc.json.impl;

import com.jsoniter.JsonIterator;
import com.jsoniter.output.JsonStream;
import top.jfunc.json.Json;
import top.jfunc.json.JsonArray;
import top.jfunc.json.JsonException;
import top.jfunc.json.JsonObject;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author xiongshiyan at 2018/6/10
 */
public class JSONObject extends BaseMapJSONObject {

    public JSONObject(Map<String , Object> map){
        super(map);
    }
    public JSONObject(){
    }
    public JSONObject(String jsonString){
        super(jsonString);
    }

    @Override
    protected Map<String, Object> str2Map(String jsonString) {
        try {
            return JsonIterator.parse(jsonString).read(Map.class);
        } catch (IOException e) {
            throw new JsonException(e);
        }
//        return JsonIterator.deserialize(jsonString, Map.class);
    }

    @Override
    protected String map2Str(Map<String, Object> map) {
        return JsonStream.serialize(map);
    }

    @Override
    public JsonObject parse(String jsonString) {
        this.map = str2Map(jsonString);
        return this;
    }

    @Override
    public <T> String serialize(T javaBean, boolean nullHold, String... ignoreFields) {
        return JsonStream.serialize(javaBean);
    }

    @Override
    public <T> T deserialize(String jsonString, Class<T> clazz) {
        return JsonIterator.deserialize(jsonString, clazz);
    }

    @Override
    public JsonObject getJsonObject(String key) {
        assertKey(key);
        //这里不能使用getJSONObject，因为每一种Json实现不一样，给出的JsonObject类型是不一致的。
        //这里就是各种JsonObject不能混用的原因
        Object temp = this.map.get(key);
        Object t = checkNullValue(key, temp);

        if(t instanceof Map){
            return new JSONObject((Map<String, Object>) t);
        }

        return (JsonObject) t;
    }

    @Override
    public JsonArray getJsonArray(String key) {
        assertKey(key);
        //这里不能使用getJSONObject，因为每一种Json实现不一样，给出的JsonObject类型是不一致的。
        //这里就是各种JsonObject不能混用的原因
        Object temp = this.map.get(key);
        Object t = checkNullValue(key, temp);

        if(t instanceof List){
            return new JSONArray((List<Object>)t);
        }
        return (JsonArray) t;
    }
    @Override
    public JsonObject fromMap(Map<String, Object> map) {
        return new JSONObject(map);
    }

    @Override
    public Json toJson(Object o) {
        if(o instanceof List){
            return new JSONArray((List<Object>) o);
        }
        if(o instanceof Map){
            return new JSONObject((Map<String, Object>) o);
        }
        try {
            return (Json) o;
        } catch (Exception e) {
            throw new JsonException("不能将非Json的对象转换为Json");
        }
    }
}
