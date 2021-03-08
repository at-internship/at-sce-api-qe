package com.at.globalclasses;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;


public class QAUtils {
    private long numberUsersDB;

    public QAUtils(){

    }

public boolean compareDocumentsArrays(JSONArray firstArray, JSONArray secondArray){
   boolean bool=false;
   int arrayLength = secondArray.length();
   for(long i=0;i<arrayLength;i++){
     String id,firstName,lastName,email,password;
     String id1,firstName1,lastName1,email1,password1;
     int j= (int) i, type, status, type1,status1;

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
     status = firstObject.getInt("status");
     type = firstObject.getInt("type");

     try{firstName1 = secondObject.getString("firstName");}
     catch (Exception JSONObject){firstName1=null;}
     try{lastName1 = secondObject.getString("lastName");}
     catch (Exception JSONObject){lastName1=null;}
     try{email1 = secondObject.getString("email");}
     catch (Exception JSONObject){email1=null;}
     try{password1 = secondObject.getString("password"); }
     catch (Exception JSONObject){password1=null;}
     id1 = secondObject.getString("id");
     status1 = secondObject.getInt("status");
     type1 = secondObject.getInt("type");


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
       if((password1==null && password=="")||(password==null && password1=="")||(password==null && password1==null)){
           bool=true; }
       else{
           bool = password.equals(password1); }
       if(bool == false){
           break;
       }
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


////// Encapsulation
    public long getNumberUsersDB() {
        return numberUsersDB;
    }

    public void setNumberUsersDB(long numberUsersDB) {
        this.numberUsersDB = numberUsersDB;
    }
}
