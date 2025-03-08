package com.iti.managers.messages;

import com.iti.api.API_ENUM;
import com.iti.database.DB_Handler;
import com.iti.database.SQL_Condition;
import com.iti.database.psql.PSQL_Handler;
import com.iti.models.messages.Api_Info;
import com.iti.models.messages.Pub_API_INFO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author theda
 */
public class ApiInfoManager {

    public List<Map<String, String>> getAvailableApi() {
        List<Map<String, String>> result = new ArrayList<>();
        DB_Handler dbh = new PSQL_Handler();
        dbh.connect();
        String query = "SELECT api_name, api_code FROM api_codename";
        List<Map<String, Object>> queryResult = dbh.executeSelectQuery(query);
        dbh.disconnect();
        for (Map<String, Object> row : queryResult) {
            Map<String, String> apiInfo = new HashMap<>();
            apiInfo.put("api_name", (String) row.get("api_name"));
            apiInfo.put("api_code", (String) row.get("api_code"));
            result.add(apiInfo);
        }

        return result;
    }

    public Api_Info createApiInfo(String code, String key, String secret, String sid, int cid) {
        Api_Info info = new Api_Info(code, key, secret, sid, cid);
        DB_Handler dbh = new PSQL_Handler();
        dbh.connect();
        info = dbh.create(info);
        dbh.disconnect();
        return info;
    }

    public List<Pub_API_INFO> getUserApiInfo(int customerId) {
        List<Pub_API_INFO> apiPubList = new ArrayList<>();
        List<Api_Info> apiList = new ArrayList<>();
        DB_Handler dbh = new PSQL_Handler();
        dbh.connect();
        apiList = (List<Api_Info>) dbh.readByValue(Api_Info.class, "customer_id", SQL_Condition.EQUAL, customerId);
        dbh.disconnect();
        for (Api_Info info : apiList) {
            int id = info.getApi_id();
            String name = API_ENUM.valueOf(info.getApi_code()).getApiName();
            String senderId = info.getSender_id();
            apiPubList.add(new Pub_API_INFO(id, name, senderId));
        }

        return apiPubList;
    }

    public Api_Info getApiInfoById(int apiId) {
        Api_Info apiInfo = null;
        DB_Handler dbh = new PSQL_Handler();
        dbh.connect();
        List<Api_Info> apiList = (List<Api_Info>) dbh.readByValue(Api_Info.class, "api_id", SQL_Condition.EQUAL, apiId);
        dbh.disconnect();
        if (apiList != null && !apiList.isEmpty()) {
            apiInfo = apiList.get(0);
        }
        return apiInfo;
    }
    
        public String getApiSenderId(int apiId) {
        Api_Info apiInfo = null;
        String senderid=null;
        DB_Handler dbh = new PSQL_Handler();
        dbh.connect();
        List<Api_Info> apiList = (List<Api_Info>) dbh.readByValue(Api_Info.class, "api_id", SQL_Condition.EQUAL, apiId);
        dbh.disconnect();
        if (apiList != null && !apiList.isEmpty()) {
            apiInfo = apiList.get(0);
        }
        if(apiInfo!=null)  senderid=apiInfo.getSender_id();
        return senderid;
    }

}
