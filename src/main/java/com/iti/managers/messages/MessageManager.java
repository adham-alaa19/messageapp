/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iti.managers.messages;

import com.iti.database.ConditionBuilder;
import com.iti.database.DB_Handler;
import com.iti.database.SQL_Condition;
import com.iti.database.psql.PSQL_Handler;
import com.iti.models.Message;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author theda
 */
public class MessageManager {

    private DB_Handler dbHandler;

    public void createMessage(String from, String to, String body, String status) {
        Message message = new Message(from, to, body, status);
        DB_Handler dbHandler = new PSQL_Handler();
        dbHandler.connect();
        dbHandler.create(message);
        dbHandler.disconnect();
    }

    public void open() {
        this.dbHandler = new PSQL_Handler();
        this.dbHandler.connect();
    }

    // 1. Get user messages
    public List<Message> getUserMessages(String username) {
        open();
        // Build the query to fetch messages where the user is either the sender or receiver
//        String query = "SELECT * FROM message WHERE msg_from = ? OR msg_to = ?";

        ConditionBuilder conditionBuilder = new ConditionBuilder()
                .where("msg_from", SQL_Condition.EQUAL, username);

        List<Message> usersMessage = dbHandler.readByValue(Message.class, conditionBuilder);
        close();
        return usersMessage;
    }

    // 2. Delete a specific message by ID
    public boolean deleteMessage(int messageId) {
        open();
        // Ensure the message belongs to the user before deleting
        ConditionBuilder conditionBuilder = new ConditionBuilder()
                .where("msg_id", SQL_Condition.EQUAL, messageId);

        boolean deleted = dbHandler.deleteByValue(Message.class, conditionBuilder);
        close();
        return deleted;
    }

    // 3. Search in messages by a list of criteria (From, To, Date Range, Body)
    public List<Message> searchMessages(String username, String from, String to, Date startDate, Date endDate, String body) {
        open();
        // Build the condition dynamically based on the provided criteria
        ConditionBuilder conditionBuilder = new ConditionBuilder()
                .openGroup()
                .where("msg_from", SQL_Condition.EQUAL, username)
                .or("msg_to", SQL_Condition.EQUAL, username)
                .closeGroup();

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
        if (body != null && !body.isEmpty()) {
            conditionBuilder.and("body", SQL_Condition.LIKE, "%" + body + "%");
        }

        // Execute the query with the constructed condition
        List<Message> Messages = dbHandler.readByValue(Message.class, conditionBuilder);
        close();
        return Messages;
    }

    // 4. User can delete from his own history
    public boolean deleteUserHistory(String username) {
        open();
        // Delete all messages where the user is either the sender or receiver
        ConditionBuilder conditionBuilder = new ConditionBuilder()
                .where("msg_from", SQL_Condition.EQUAL, username)
                .or("msg_to", SQL_Condition.EQUAL, username);

        boolean isdeleted = dbHandler.deleteByValue(Message.class, conditionBuilder);
        close();
        return isdeleted;
    }

    // 5. User can search by his username or other criteria
    public List<Message> searchByUsernameOrCriteria(String username, String searchTerm) {
        // Build the condition to search for messages involving the user and matching the search term
        ConditionBuilder conditionBuilder = new ConditionBuilder()
                .openGroup()
                .where("msg_from", SQL_Condition.EQUAL, username)
                .or("msg_to", SQL_Condition.EQUAL, username)
                .closeGroup()
                .and("msg_from", SQL_Condition.LIKE, "%" + searchTerm + "%")
                .or("msg_to", SQL_Condition.LIKE, "%" + searchTerm + "%")
                .or("body", SQL_Condition.LIKE, "%" + searchTerm + "%");

        // Execute the query with the constructed condition
        List<Message> MessageList = dbHandler.readByValue(Message.class, conditionBuilder);
        close();
        return MessageList;
    }

    // Close the database connection when done
    public void close() {
        dbHandler.disconnect();
    }
}
