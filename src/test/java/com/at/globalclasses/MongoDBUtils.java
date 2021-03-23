package com.at.globalclasses;
import org.json.JSONObject;

public class MongoDBUtils {
    public static boolean existID(String env, String mDataBase, String collection, String id) {
        MongoDBConnection db = new MongoDBConnection(env, mDataBase);
        boolean exist = db.compareID(collection, id);
        db.close();
        return exist;
    }

    public static String getJObjectByID(String env, String mDataBase, String collection, String id) {
        MongoDBConnection db = new MongoDBConnection(env, mDataBase);
        String monObj = db.getObjectByID(collection, id);
        db.close();
        return monObj;
    }

    public static String getRandomID(String env, String mDataBase, String collection) {
        MongoDBConnection db = new MongoDBConnection(env, mDataBase);
        String id = db.foundRandomID(collection);
        db.close();
        return id;
    }

    public static boolean compareJsonString(String env, String mDataBase, String collection, String json) {
        MongoDBConnection db = new MongoDBConnection(env, mDataBase);
        boolean bool;
        try {
            bool = db.compareJsonString(collection, json);
        } catch(Exception var9) {
            var9.printStackTrace();
            bool = false;
        } finally {
            db.close();
        }
        return bool;
    }

    
    public static String compareJsonFromDatabase(String env, String mDataBase, String collection,String email) {
        MongoDBConnection db = new MongoDBConnection(env, mDataBase);
        String jsonFronDatabase;
        jsonFronDatabase = db.getJsonFromDatabase(collection,email);
        db.close();
        return jsonFronDatabase;
    }

     public static String obtainObject(String env, String mDataBase, String collection, String responseId) {
        MongoDBConnection db = new MongoDBConnection(env, mDataBase);
        JSONObject json = new JSONObject(responseId);
        String result = db.getObjectByID(collection, json.getString("id"));

        db.close();
        return result;
    }

     public boolean compareUsersPasswords(JSONObject firstObject, JSONObject secondObject) {
    	    boolean bool = false;
    	    String password, password1;
    	    try {
    	        password = firstObject.getString("password");
    	    } catch (Exception JSONObject) {
    	        password = null;
    	    }
    	    try {
    	        password1 = secondObject.getString("password");
    	    } catch (Exception JSONObject) {
    	        password1 = null;
    	    }
    	    if ((password1 == null && password == "") || (password == null && password1 == "") || (password == null && password1 == null)) {
    	        bool = true;
    	    } else {
    	        bool = password.equals(password1);
    	    }
    	    if (bool == false) {
    	        return bool;
    	    }
    	    return bool;
    	}
    	public boolean compareUsersDocuments(JSONObject firstObject, JSONObject secondObject) {
    	    boolean bool = false;
    	    String firstName, lastName, email, password;
    	    String firstName1, lastName1, email1, password1;
    	    int type, status, type1, status1;
    	    try {
    	        firstName = firstObject.getString("firstName");
    	    } catch (Exception JSONObject) {
    	        firstName = null;
    	    }
    	    try {
    	        lastName = firstObject.getString("lastName");
    	    } catch (Exception JSONObject) {
    	        lastName = null;
    	    }
    	    try {
    	        email = firstObject.getString("email");
    	    } catch (Exception JSONObject) {
    	        email = null;
    	    }
    	    try {
    	        status = firstObject.getInt("status");
    	    } catch (Exception JSONObject) {
    	        status = 0;
    	    }
    	    try {
    	        type = firstObject.getInt("type");
    	    } catch (Exception JSONObject) {
    	        type = 0;
    	    }
    	    try {
    	        firstName1 = secondObject.getString("firstName");
    	    } catch (Exception JSONObject) {
    	        firstName1 = null;
    	    }
    	    try {
    	        lastName1 = secondObject.getString("lastName");
    	    } catch (Exception JSONObject) {
    	        lastName1 = null;
    	    }
    	    try {
    	        email1 = secondObject.getString("email");
    	    } catch (Exception JSONObject) {
    	        email1 = null;
    	    }
    	    try {
    	        status1 = firstObject.getInt("status");
    	    } catch (Exception JSONObject) {
    	        status1 = 0;
    	    }
    	    try {
    	        type1 = firstObject.getInt("type");
    	    } catch (Exception JSONObject) {
    	        type1 = 0;
    	    }
    	    if ((firstName1 == null && firstName == "") || (firstName == null && firstName1 == "") || (firstName == null && firstName1 == null)) {
    	        bool = true;
    	    } else {
    	        bool = firstName.equals(firstName1);
    	    }
    	    if (bool == false) {
    	        return bool;
    	    }
    	    if ((lastName1 == null && lastName == "") || (lastName == null && lastName1 == "") || (lastName == null && lastName1 == null)) {
    	        bool = true;
    	    } else {
    	        bool = lastName.equals(lastName1);
    	    }
    	    if (bool == false) {
    	        return bool;
    	    }
    	    if ((email1 == null && email == "") || (email == null && email1 == "") || (email == null && email1 == null)) {
    	        bool = true;
    	    } else {
    	        bool = email.equals(email1);
    	    }
    	    if (bool == false) {
    	        return bool;
    	    }
    	    if (status == status1) {
    	        bool = true;
    	    }
    	    if (bool == false) {
    	        return bool;
    	    }
    	    if (type1 == type) {
    	        bool = true;
    	    }
    	    if (bool == false) {
    	        return bool;
    	    }
    	    return bool;
    	}
    
    
    
}
