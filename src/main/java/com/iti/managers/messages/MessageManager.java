/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iti.managers.messages;

import com.iti.database.ConditionBuilder;
import com.iti.database.DB_Handler;
import com.iti.database.SQL_Condition;
import com.iti.database.psql.PSQL_Handler;
import com.iti.models.messages.Message;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author theda
 */
public class MessageManager {

    private DB_Handler dbHandler;

    public void createMessage(String from, String to, String body, String status,int cid) {
        Message message = new Message(from, to, body, status,cid);
        DB_Handler dbHandler = new PSQL_Handler();
        dbHandler.connect();
        dbHandler.create(message);
        dbHandler.disconnect();
    }

    public void open() {
        this.dbHandler = new PSQL_Handler();
        this.dbHandler.connect();
    }

    public List<Message> getUserMessages(int cid) {
        open();
        ConditionBuilder conditionBuilder = new ConditionBuilder()
                .where("customer_id", SQL_Condition.EQUAL, cid);
        List<Message> usersMessage = dbHandler.readByValue(Message.class, conditionBuilder);
        close();
        return usersMessage;
    }

    public boolean deleteMessage(int messageId) {
        open();
        ConditionBuilder conditionBuilder = new ConditionBuilder()
                .where("msg_id", SQL_Condition.EQUAL, messageId);
        boolean deleted = dbHandler.deleteByValue(Message.class, conditionBuilder);
        close();
        return deleted;
    }

    public List<Message> searchMessages(int cid, String from, String to, Timestamp startDate, Timestamp endDate, String body) {
        open();
        ConditionBuilder conditionBuilder = new ConditionBuilder()
                .where("customer_id", SQL_Condition.EQUAL, cid);
        if (from != null && !from.isEmpty()) {
            conditionBuilder.and("msg_from", SQL_Condition.EQUAL, from);
        }
        if (to != null && !to.isEmpty()) {
            conditionBuilder.and("msg_to", SQL_Condition.EQUAL, to);
        }
        if (startDate != null && endDate != null) {
            conditionBuilder.and("msg_date", SQL_Condition.MORE_OR_EQUAL, startDate)
                    .and("msg_date", SQL_Condition.LESS_OR_EQUAL, endDate);
        }
        List<Message> Messages = dbHandler.readByValue(Message.class, conditionBuilder);
        close();
        return Messages;
    }

    public void close() {
        dbHandler.disconnect();
    }
}
