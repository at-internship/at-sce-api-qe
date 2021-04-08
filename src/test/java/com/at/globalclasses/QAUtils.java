package com.at.globalclasses;
import com.at.globalclasses.domain.QARandomData;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class QAUtils {
    private long numberUsersDB;
    private String timestamp = "\\d{4}-\\d{2}-\\d{2}\\w\\d{2}:\\d{2}:\\d{2}\\.\\d{3}\\+\\d{2}:\\d{2}";
    QARandomData randomCategory = new QARandomData();

public boolean compareDocumentsArrays(JSONArray firstArray, JSONArray secondArray){
   boolean bool=false;
   int arrayLength = secondArray.length();
   for(long i=0;i<arrayLength;i++){
     String id,firstName,lastName,email,password, status , type, type1;
     String id1,firstName1,lastName1,email1,password1 ,status1;
     int j= (int) i;

       JSONObject firstObject;
       JSONObject secondObject;

     firstObject=firstArray.getJSONObject(j);
     secondObject=secondArray.getJSONObject(j);

     try{firstName = firstObject.getString("firstName");}
     catch (Exception JSONObject){firstName=null;}
     try{lastName = firstObject.getString("lastName");}
     catch (Exception JSONObject){lastName=null;}
     try{email = firstObject.getString("email");}
     catch (Exception JSONObject){email=null;}
     try{password = firstObject.getString("password"); }
     catch (Exception JSONObject){password=null;}
     id = firstObject.getString("id");
     status = firstObject.get("status").toString();
     type = firstObject.get("type").toString();

     try{firstName1 = secondObject.getString("firstName");}
     catch (Exception JSONObject){firstName1=null;}
     try{lastName1 = secondObject.getString("lastName");}
     catch (Exception JSONObject){lastName1=null;}
     try{email1 = secondObject.getString("email");}
     catch (Exception JSONObject){email1=null;}
     try{password1 = secondObject.getString("password"); }
     catch (Exception JSONObject){password1=null;}
     id1 = secondObject.getString("id");
     status1 = secondObject.get("status").toString();
     type1 = secondObject.get("type").toString();


       if((firstName1==null && firstName=="") || (firstName==null && firstName1=="") || (firstName==null && firstName1==null)){
           bool=true; }
       else{
           bool = firstName.equals(firstName1);}
       if(bool == false){
           break;
       }
       if((lastName1==null && lastName=="") || (lastName==null && lastName1=="") || (lastName==null && lastName1==null)){
           bool=true; }
       else{
           bool = lastName.equals(lastName1); }
       if(bool == false){
           break;
       }
       if((email1==null && email=="")||(email==null && email1=="")||(email==null && email1==null)){
           bool=true; }
       else{
           bool = email.equals(email1); }
       if(bool == false){
           break;
       }
     /*  if((password1==null && password=="")||(password==null && password1=="")||(password==null && password1==null)){
           bool=true; }
       else{
           bool = password.equals(password1); }
       if(bool == false){
           break;
       }*/
       if(status==status1){
       bool = true;}
       if(bool == false){
           break;
       }
       if(type1==type){
           bool = true;}
       if(bool == false){
           break;
       }
       bool = id.equals(id1);
       if(bool == false){
           break;
       }





   }

  return bool;

}

public JSONArray arrayUsersInfoDB(String env, String mDataBase, String collection) {
        MongoDBConnection db = new MongoDBConnection(env, mDataBase);
        JSONArray bool = new JSONArray();
        try {
            bool = db.allUsersInfo(collection);
        } catch(Exception var9) {
            var9.printStackTrace();

        } finally {
            db.close();
        }
        return bool;
    }

public long numberOfUsersDB(String env, String mDataBase, String collection) {
        MongoDBConnection db = new MongoDBConnection(env, mDataBase);
        long number=0;
        try {
            number = db.collectionSize(collection);
        } catch(Exception var9) {
            var9.printStackTrace();
        } finally {
            db.close();
        }
        return number;
    }

public boolean validateRegex(String regex, String stringToValidate){
    Pattern pattern = Pattern.compile(regex);
    Matcher match = pattern.matcher(stringToValidate);

        return match.find();
}

public JSONObject fillHistoryBody(String field, String data, JSONObject historyJson){

        if (field.equals("user") && data.equals("null")) {
            historyJson.put("user_id",(Object) null);
        }

        if (data.equals("null") && (field != "user")) {
            historyJson.put(field, (Object) null); }

        if (data.equals("invalid") && (field != "user")) {
            switch ((randomCategory.correctRangeInt(1, 3))) {
                case 1:
                    historyJson.put(field, randomCategory.negativeInt());
                    break;
                case 2:
                    historyJson.put(field, randomCategory.negativeDouble());
                    break;
                case 3:
                    historyJson.put(field, randomCategory.randomString());
                    break;

            }
        }
        return historyJson;
    }

public JSONObject fillFixExpBody(String field, String data, JSONObject fixedExpensesJson){

        if (data.equals("null")) {
            fixedExpensesJson.put(field, (Object) null);
        }

        if (data.equals("invalid")) {
            switch ((randomCategory.correctRangeInt(1, 3))) {
                case 1:
                    fixedExpensesJson.put(field, randomCategory.negativeInt());
                    break;
                case 2:
                    fixedExpensesJson.put(field, randomCategory.negativeDouble());
                    break;
                case 3:
                    fixedExpensesJson.put(field, randomCategory.randomString());
                    break;
            }
        }
        return fixedExpensesJson;
    }

public JSONObject modifiedJsonHistory(JSONObject historyJson){
    Boolean statusValue = false;
        historyJson.remove("_id");
        historyJson.remove("_class");
        try{
        statusValue = historyJson.getBoolean("status");}
        catch(JSONException E){
            historyJson.put("status",0);
        }
        if (statusValue){
            historyJson.put("status",1);
        }

        if (!statusValue){
            historyJson.put("status",0);
        }

        return historyJson;
    }


    public long getNumberUsersDB() {
        return numberUsersDB;
    }

    public void setNumberUsersDB(long numberUsersDB) {
        this.numberUsersDB = numberUsersDB;
    }
}
