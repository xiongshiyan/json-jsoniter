package top.jfunc.json;

import org.junit.Assert;
import org.junit.Test;
import top.jfunc.json.impl.JSONArray;
import top.jfunc.json.impl.JSONObject;

public class PathTest {
    @Test
    public void testPathResolve(){
        JsonObject review = new JSONObject();
        review.put("time", "2015-11-14 00:00:00");
        review.put("author", "Tom");
        review.put("content", "hello");

        JsonArray reviews = new JSONArray();
        reviews.put(review);

        JsonObject article1 = new JSONObject();
        article1.put("time", "2015-11-14 00:00:00");
        article1.put("author", "Tom");
        article1.put("title", "My family");
        article1.put("content", "not finished yet.");
        article1.put("reviews", reviews);

        JsonObject article2 = new JSONObject();
        article2.put("time", "2015-11-15 00:00:00");
        article2.put("author", "John");
        article2.put("title", "My college life");
        article2.put("content", "not finished yet.");
        article2.put("reviews", reviews);

        JsonArray articles = new JSONArray();
        articles.put(article1);
        articles.put(article2);

        String p = "0.time";
        Assert.assertEquals("2015-11-14 00:00:00" , articles.getByPath(p));

        JsonObject topic1 = new JSONObject();
        topic1.put("banner", "What's your opinion about gambling?");
        topic1.put("articles", articles);

        JsonObject topic2 = new JSONObject();
        topic2.put("banner", "What's your opinion about gambling?");
        topic2.put("articles", articles);

        JsonArray topics = new JSONArray();
        topics.put(topic1);
        topics.put(topic2);

        JsonObject jsonObject = new JSONObject();
        jsonObject.put("topics", topics);

        String path = "topics[0].articles[1].reviews[0]";
        Object byPath = jsonObject.getByPath(path);
        JsonObject json = (JsonObject) new JSONObject().toJson(byPath);
        Assert.assertEquals("Tom" , json.getString("author"));
        Assert.assertEquals("hello" , json.getString("content"));
        Assert.assertEquals("2015-11-14 00:00:00" , json.getString("time"));

        path = "topics[0].articles[1].title";
        Assert.assertEquals("My college life" , jsonObject.getByPath(path));

    }
}