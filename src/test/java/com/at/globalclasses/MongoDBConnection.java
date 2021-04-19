package com.at.globalclasses;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import static com.mongodb.client.model.Projections.*;
import cucumber.deps.com.thoughtworks.xstream.converters.reflection.FieldKeySorter;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mongodb.client.model.Projections.*;
import static java.lang.Integer.getInteger;
import static java.lang.Integer.parseInt;

public class MongoDBConnection {

    private static MongoClient mClient;
    private static MongoDatabase mDataBase;

    public MongoDBConnection(String env, String db) {
        Properties prop = new Properties();
        String propFileName = "config.properties";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.WARNING);
        try {
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            String uriString = prop.getProperty(env + "." + db);
            getMongoClient(uriString);
            mDataBase = getDB(db);
            System.out.println("Connection successful");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to make connection!");
        }
    }

    private MongoClient getMongoClient(String uriString) {
        if (mClient == null) {
            mClient = new MongoClient(new MongoClientURI(uriString));
        }
        return mClient;
    }

    private MongoDatabase getDB(String db) {
        return mClient.getDatabase(db);
    }

    public void close() {
        try {
            if (mClient != null) {
                mClient.close();
                mClient = null;
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public boolean compareID(String collection, String id) {
        boolean exist = false;
        MongoCollection<Document> coll = mDataBase.getCollection(collection);
        FindIterable<Document> findIterable = coll.find(Filters.eq("_id", new ObjectId(id)));
        try {
            for (Document doc : findIterable) {
                JSONObject mObject = new JSONObject(doc.toJson());
                String mongoID = mObject.getJSONObject("_id").get("$oid").toString();
                exist = mongoID.equals(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            exist = false;
        }
        return exist;
    }

    public String getObjectByID(String collection, String id) {
        String mObject = "";
        MongoCollection<Document> coll = mDataBase.getCollection(collection);
        FindIterable<Document> findIterable = coll.find(Filters.eq("_id", new ObjectId(id))).projection(fields(exclude("_class"), excludeId()));
        try {
            for (Document doc : findIterable) {
                JSONObject monObject = new JSONObject(doc.toJson());
                mObject = monObject.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mObject;
    }

    public String foundRandomID(String collection) {
        String id = "";
        MongoCollection<Document> coll = mDataBase.getCollection(collection);
        FindIterable<Document> findIterable = coll.find();
        try {
            JSONArray jResult = new JSONArray();
            JSONObject mObject = new JSONObject();
            for (Document doc : findIterable) {
                jResult.put(mObject = new JSONObject(doc.toJson()));
            }
            int jArrayLength = jResult.length() - 1;
            mObject = jResult.getJSONObject((int) (Math.random() * (jArrayLength - 0 + 1) + 0));
            id = mObject.getJSONObject("_id").get("$oid").toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public boolean compareJsonString( String collection, String object) {
        boolean bool = false;
        String data = "", category = "", title = "", description = "", img="";
        String status = "";
        MongoCollection<Document> coll = mDataBase.getCollection(collection);
        FindIterable<Document> findIterable = coll.find();

        for (Document document : findIterable) {
            JSONObject mongo = new JSONObject(document.toJson());
            String id = mongo.getJSONObject("_id").get("$oid").toString();
            String[] categoryList = { "JAVA", "PEGA", "JS" };
            if (mongo.has("category")) {
                category = categoryList[parseInt(mongo.get("category").toString()) - 1];
            }
            if (mongo.has("title")) {
                title = mongo.getString("title");
            }
            if (mongo.has("description")) {
                description = mongo.getString("description");
            }
            if (mongo.has("img")) {
                description = mongo.getString("img");
            }
            if (mongo.has("status")) {
                status = mongo.get("status").toString();
            } else {
                status = "";
            }

            data = data + id + category + title + description + img + status;
        }
        bool = compareAll(data, data);

        return bool;

    }

    public boolean compareAll(String mongoJson, String object) {
        boolean bool = false;
        if (mongoJson.equals(object)) {
            bool = true;
        }
        return bool;
    }

    public JSONArray allUsersInfo(String collection) {

        String firstName="",lastName="",email="",password="";
        int type=0,status=0;

        MongoCollection<Document> coll = mDataBase.getCollection(collection);

        FindIterable<Document> findIterable = coll.find();

        JSONArray usersMongoArray = new JSONArray();

        for (Document document : findIterable) {

            firstName=null;
            lastName=null;
            email=null;
            password=null;
            JSONObject mongo = new JSONObject(document.toJson());
            JSONObject jsonMongo = new JSONObject();

            String id = mongo.getJSONObject("_id").get("$oid").toString();
            jsonMongo.put("id",id);
            if (mongo.has("type")) {
                type = mongo.getInt("type");
            }
            jsonMongo.put("type",type);
            if (mongo.has("firstName")) {
                firstName = mongo.getString("firstName");
            }
            jsonMongo.put("firstName",firstName);
            if (mongo.has("lastName")) {
                lastName = mongo.getString("lastName");
            }
            jsonMongo.put("lastName",lastName);
            if (mongo.has("email")) {
                email = mongo.getString("email");
            }
            jsonMongo.put("email",email);
            if (mongo.has("password")) {
                password = mongo.getString("password");
            }
            jsonMongo.put("password",password);
            if (mongo.has("status")) {
                status = mongo.getInt("status");
            }
            jsonMongo.put("status",status);

            usersMongoArray.put(jsonMongo);

        }

        return usersMongoArray;

    }

    public long collectionSize(String collection) {
        long number = 0;

        MongoCollection<Document> coll = mDataBase.getCollection(collection);
        number = coll.countDocuments();

        return number;

    }

    public String getJsonFromDatabase(String collection,String email) {

        String jsonString = "";

        MongoCollection<Document> coll = mDataBase.getCollection(collection);
        FindIterable<Document> findIterable = coll.find(Filters.eq("email", email)).projection(fields(include("email","password"), excludeId()));

        try {
            for (Document doc : findIterable) {
                JSONObject monObject = new JSONObject(doc.toJson());
                jsonString = monObject.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonString;

    }

    public long collectionSizeWithUserId(String collection, String id) {
        long number = 0;

        MongoCollection<Document> coll = mDataBase.getCollection(collection);
        FindIterable<Document> findIterable = coll.find(Filters.eq("user_id", id)).projection(fields(include("user_id")));

        for (Document doc : findIterable) {
            number = number + 1;
        }
        return number;
    }

    public JSONArray allHistoryInfo(String collection, String idUser) {

        String user_id = "";
        String type = "", totalHours = "", totalDays = "";
        boolean status = false;
        String costDay = "", costHour = "", projectCost = "", taxIVA = "", taxISR_r = "", taxIVA_r = "", revenue = "", total = "";
        String rent, transport, internet, feed, others, totalFixedExpenses;

        MongoCollection<Document> coll = mDataBase.getCollection(collection);
        FindIterable<Document> findIterable = coll.find(Filters.eq("user_id", idUser));

        JSONArray usersMongoArray = new JSONArray();

        for (Document document : findIterable) {

            user_id = null;
            JSONObject mongo = new JSONObject(document.toJson());
            JSONObject jsonMongo = new JSONObject();

            String id = mongo.getJSONObject("_id").get("$oid").toString();
            jsonMongo.put("id", id);
            if (mongo.has("type")) {
                type = mongo.get("type").toString();
            }
            jsonMongo.put("type", type);

            if (mongo.has("totalHours")) {
                totalHours = mongo.get("totalHours").toString();
            }
            jsonMongo.put("totalHours", totalHours);

            if (mongo.has("totalDays")) {
                totalDays = mongo.get("totalDays").toString();
            }
            jsonMongo.put("totalDays", totalDays);

            if (mongo.has("user_id")) {
                user_id = mongo.get("user_id").toString();
            }
            jsonMongo.put("user_id", user_id);

            if (mongo.has("costDay")) {
                costDay = mongo.get("costDay").toString();
            }
            jsonMongo.put("costDay", costDay);

            if (mongo.has("costHour")) {
                costHour = mongo.get("costHour").toString();
            }
            jsonMongo.put("costHour", costHour);

            if (mongo.has("projectCost")) {
                projectCost = mongo.get("projectCost").toString();
            }
            jsonMongo.put("projectCost", projectCost);

            if (mongo.has("taxIVA")) {
                taxIVA = mongo.get("taxIVA").toString();
            }
            jsonMongo.put("taxIVA", taxIVA);

            if (mongo.has("taxISR_r")) {
                taxISR_r = mongo.get("taxISR_r").toString();
            }
            jsonMongo.put("taxISR_r", taxISR_r);

            if (mongo.has("taxIVA_r")) {
                taxIVA_r = mongo.get("taxIVA_r").toString();
            }
            jsonMongo.put("taxIVA_r", taxIVA_r);

            if (mongo.has("revenue")) {
                revenue = mongo.get("revenue").toString();
            }
            jsonMongo.put("revenue", revenue);

            if (mongo.has("total")) {
                total = mongo.get("total").toString();
            }
            jsonMongo.put("total", total);

            if (mongo.has("status")) {
                status = mongo.getBoolean("status");
            }
            jsonMongo.put("status", status);
            try {
                rent = mongo.getJSONObject("fixedExpenses").get("rent").toString();
            } catch (Exception e) {
                rent = "";
            }
            jsonMongo.put("rent", rent);

            try {
                transport = mongo.getJSONObject("fixedExpenses").get("transport").toString();
            } catch (Exception e) {
                transport = "";
            }
            jsonMongo.put("transport", transport);

            try {
                internet = mongo.getJSONObject("fixedExpenses").get("internet").toString();
            } catch (Exception e) {
                internet = "";
            }
            jsonMongo.put("internet", internet);

            try {
                feed = mongo.getJSONObject("fixedExpenses").get("feed").toString();
            } catch (Exception e) {
                feed = "";
            }
            jsonMongo.put("feed", feed);

            try {
                others = mongo.getJSONObject("fixedExpenses").get("others").toString();
            } catch (Exception e) {
                others = "";
            }
            jsonMongo.put("others", others);

            try {
                totalFixedExpenses = mongo.getJSONObject("fixedExpenses").get("total").toString();
            } catch (Exception e) {
                totalFixedExpenses = "";
            }
            jsonMongo.put("totalFixedExpenses", totalFixedExpenses);


            usersMongoArray.put(jsonMongo);
        }
        return usersMongoArray;
    }
}




