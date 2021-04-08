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


    public long getNumberUsersDB() {
        return numberUsersDB;
    }

    public void setNumberUsersDB(long numberUsersDB) {
        this.numberUsersDB = numberUsersDB;
    }

        public boolean compareHistoriesDocumentsArrays(JSONArray firstArray, JSONArray secondArray) {
        boolean bool = false;
        int arrayLength = secondArray.length();
        for (long i = 0; i < arrayLength; i++) {
            String id, user_id;
            String id1, user_id1;
            int j = (int) i;
            Double type, type1, totalHours, totalDays, totalHours1, totalDays1;
            boolean status = false, status1 = false;
            Double costDay, costHour, projectCost, taxIVA, taxISR_r, taxIVA_r, revenue, total;
            Double costDay1, costHour1, projectCost1, taxIVA1, taxISR_r1, taxIVA_r1, revenue1, total1;
            Double rent, transport, internet, feed, others, totalFixedExpenses;
            Double rent1, transport1, internet1, feed1, others1, totalFixedExpenses1;

            JSONObject firstObject;
            JSONObject secondObject;
            firstObject = firstArray.getJSONObject(j);
            secondObject = secondArray.getJSONObject(j);

            id = firstObject.getString("id");

            try {
                user_id = firstObject.getString("user_id");
            } catch (Exception JSONObject) {
                user_id = null;
            }
            try {
                type = firstObject.getDouble("type");
            } catch (Exception JSONObject) {
                type = null;
            }
            try {
                totalHours = firstObject.getDouble("totalHours");
            } catch (Exception JSONObject) {
                totalHours = null;
            }

            try {
                totalDays = firstObject.getDouble("totalDays");
            } catch (Exception JSONObject) {
                totalDays = null;
            }
            status = firstObject.getBoolean("status");
            try {
                costDay = firstObject.getDouble("costDay");
            } catch (Exception JSONObject) {
                costDay = null;
            }
            try {
                costHour = firstObject.getDouble("costHour");
            } catch (Exception JSONObject) {
                costHour = null;
            }
            try {
                projectCost = firstObject.getDouble("projectCost");
            } catch (Exception JSONObject) {
                projectCost = null;
            }
            try {
                taxIVA = firstObject.getDouble("taxIVA");
            } catch (Exception JSONObject) {
                taxIVA = null;
            }
            try {
                taxISR_r = firstObject.getDouble("taxISR_r");
            } catch (Exception JSONObject) {
                taxISR_r = null;
            }
            try {
                taxIVA_r = firstObject.getDouble("taxIVA_r");
            } catch (Exception JSONObject) {
                taxIVA_r = null;
            }
            try {
                revenue = firstObject.getDouble("revenue");
            } catch (Exception JSONObject) {
                revenue = null;
            }
            try {
                total = firstObject.getDouble("total");
            } catch (Exception JSONObject) {
                total = null;
            }
            try {
                rent = firstObject.getDouble("rent");
            } catch (Exception JSONObject) {
                rent = null;
            }
            try {
                transport = firstObject.getDouble("transport");
            } catch (Exception JSONObject) {
                transport = null;
            }
            try {
                internet = firstObject.getDouble("internet");
            } catch (Exception JSONObject) {
                internet = null;
            }
            try {
                feed = firstObject.getDouble("feed");
            } catch (Exception JSONObject) {
                feed = null;
            }
            try {
                others = firstObject.getDouble("others");
            } catch (Exception JSONObject) {
                others = null;
            }
            try {
                totalFixedExpenses = firstObject.getDouble("totalFixedExpenses");
            } catch (Exception JSONObject) {
                totalFixedExpenses = null;
            }


            id1 = secondObject.getString("id");

            try {
                user_id1 = secondObject.getString("user_id");
            } catch (Exception JSONObject) {
                user_id1 = null;
            }
            try {
                type1 = secondObject.getDouble("type");
            } catch (Exception JSONObject) {
                type1 = null;
            }
            try {
                totalHours1 = secondObject.getDouble("totalHours");
            } catch (Exception JSONObject) {
                totalHours1 = null;
            }
            try {
                totalDays1 = secondObject.getDouble("totalDays");
            } catch (Exception JSONObject) {
                totalDays1 = null;
            }
            status1 = secondObject.getBoolean("status");
            try {
                costDay1 = secondObject.getDouble("costDay");
            } catch (Exception JSONObject) {
                costDay1 = null;
            }
            try {
                costHour1 = secondObject.getDouble("costHour");
            } catch (Exception JSONObject) {
                costHour1 = null;
            }
            try {
                projectCost1 = secondObject.getDouble("projectCost");
            } catch (Exception JSONObject) {
                projectCost1 = null;
            }
            try {
                taxIVA1 = secondObject.getDouble("taxIVA");
            } catch (Exception JSONObject) {
                taxIVA1 = null;
            }
            try {
                taxISR_r1 = secondObject.getDouble("taxISR_r");
            } catch (Exception JSONObject) {
                taxISR_r1 = null;
            }
            try {
                taxIVA_r1 = secondObject.getDouble("taxIVA_r");
            } catch (Exception JSONObject) {
                taxIVA_r1 = null;
            }
            try {
                revenue1 = secondObject.getDouble("revenue");
            } catch (Exception JSONObject) {
                revenue1 = null;
            }
            try {
                total1 = secondObject.getDouble("total");
            } catch (Exception JSONObject) {
                total1 = null;
            }
            try {
                rent1 = secondObject.getJSONObject("fixedExpenses").getDouble("rent");
            } catch (Exception JSONObject) {
                rent1 = null;
            }
            try {
                transport1 = secondObject.getJSONObject("fixedExpenses").getDouble("transport");
            } catch (Exception JSONObject) {
                transport1 = null;
            }
            try {
                internet1 = secondObject.getJSONObject("fixedExpenses").getDouble("internet");
            } catch (Exception JSONObject) {
                internet1 = null;
            }
            try {
                feed1 = secondObject.getJSONObject("fixedExpenses").getDouble("feed");
            } catch (Exception JSONObject) {
                feed1 = null;
            }
            try {
                others1 = secondObject.getJSONObject("fixedExpenses").getDouble("others");
            } catch (Exception JSONObject) {
                others1 = null;
            }
            try {
                totalFixedExpenses1 = secondObject.getJSONObject("fixedExpenses").getDouble("total");
            } catch (Exception JSONObject) {
                totalFixedExpenses1 = null;
            }

            if ((user_id1 == null && user_id.equals("")) || (user_id == null && user_id1.equals("")) || (user_id == null && user_id1 == null)) {
                bool = true;
            } else {
                bool = user_id.equals(user_id1);
            }
            if (!bool) {
                break;
            }
            if (type == null && type1 == null) {
                bool = true;
            } else {
                bool = type.equals(type1);
            }
            if (!bool) {
                break;
            }
            if (totalHours == null && totalHours1 == null) {
                bool = true;
            } else {
                bool = totalHours.equals(totalHours1);
            }
            if (!bool) {
                break;
            }
            if (totalDays == null && totalDays1 == null) {
                bool = true;
            } else {
                bool = totalDays.equals(totalDays1);
            }
            if (!bool) {
                break;
            }
            if (costDay == null && costDay1 == null) {
                bool = true;
            } else {
                bool = costDay.equals(costDay1);
            }
            if (!bool) {
                break;
            }
            if (costHour == null && costHour1 == null) {
                bool = true;
            } else {
                bool = costHour.equals(costHour1);
            }
            if (!bool) {
                break;
            }
            if (projectCost == null && projectCost1 == null) {
                bool = true;
            } else {
                bool = projectCost.equals(projectCost1);
            }
            if (!bool) {
                break;
            }
            if (taxIVA == null && taxIVA1 == null) {
                bool = true;
            } else {
                bool = taxIVA.equals(taxIVA1);
            }
            if (!bool) {
                break;
            }
            if (taxISR_r == null && taxISR_r1 == null) {
                bool = true;
            } else {
                bool = taxISR_r.equals(taxISR_r1);
            }
            if (!bool) {
                break;
            }
            if (taxIVA_r == null && taxIVA_r1 == null) {
                bool = true;
            } else {
                bool = taxIVA_r.equals(taxIVA_r1);
            }
            if (!bool) {
                break;
            }
            if (revenue == null && revenue1 == null) {
                bool = true;
            } else {
                bool = revenue.equals(revenue1);
            }
            if (!bool) {
                break;
            }
            if (total == null && total1 == null) {
                bool = true;
            } else {
                bool = total.equals(total1);
            }
            if (!bool) {
                break;
            }
            if (rent == null && rent1 == null) {
                bool = true;
            } else {
                bool = rent.equals(rent1);
            }
            if (!bool) {
                break;
            }
            if (transport == null && transport1 == null) {
                bool = true;
            } else {
                bool = transport.equals(transport1);
            }
            if (!bool) {
                break;
            }
            if (internet == null && internet1 == null) {
                bool = true;
            } else {
                bool = internet.equals(internet1);
            }
            if (!bool) {
                break;
            }
            if (feed == null && feed1 == null) {
                bool = true;
            } else {
                bool = feed.equals(feed1);
            }
            if (!bool) {
                break;
            }
            if (others == null && others1 == null) {
                bool = true;
            } else {
                bool = others.equals(others1);
            }
            if (!bool) {
                break;
            }
            if (totalFixedExpenses == null && totalFixedExpenses1 == null) {
                bool = true;
            } else {
                bool = totalFixedExpenses.equals(totalFixedExpenses1);
            }
            if (!bool) {
                break;
            }
            if (status == status1) {
                bool = true;
            }
            if (!bool) {
                break;
            }
            bool = id.equals(id1);
            if (!bool) {
                break;
            }


        }
        return bool;
    }
}
