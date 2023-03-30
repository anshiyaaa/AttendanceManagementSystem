package org.exmaple;

import org.exmaple.models.ModelAttendance;
import org.exmaple.models.ModelClass;
import org.exmaple.models.ModelUsers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbHandler {

    public static final String TABLE_USER = "user";
    public static final String TABLE_CLASS = "class";

    public static final String TABLE_ATTENDANCE = "attendance";

    //Column Names
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_CLASS_ID = "class_id";
    public static final String COLUMN_CLASS_NAME = "class_name";
    private static final String COLUMN_ID = "id";
    public static Connection connect(){

        Connection connect = null;

        String url = "src/main/resources/Database/AMS.db";

        try {
            connect = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return connect;
    }


    public static int getUserIdFromUser(Connection connection, String nameuser) {
        String query = "SELECT * FROM "+ TABLE_USER + " WHERE " + COLUMN_USERNAME + " = " + nameuser;
        List<ModelUsers> modelUsers = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                int id = resultSet.getInt(COLUMN_USER_ID);

                ModelUsers modelUsers1 = new ModelUsers(id ,"","");
                modelUsers.add(modelUsers1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return modelUsers.get(0).getUser_id();
    }

    public List<ModelUsers> getAllBooks(Connection conn, String names){

        String query = "SELECT * FROM "+ TABLE_USER + " WHERE "+COLUMN_USERNAME + " like %" + names + "%";

        List<ModelUsers> modelUsers = new ArrayList<>();

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                int user_id = resultSet.getInt(COLUMN_USER_ID);
                String username = resultSet.getString(COLUMN_USERNAME);
                String password = resultSet.getString(COLUMN_PASSWORD);

                ModelUsers modelUsers1 = new ModelUsers(user_id,"","");
                ModelUsers.add(modelUsers1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



       return modelUsers;

    }


    public static List<ModelClass> getBooksFilter(Connection conn,int active){

        String query = "SELECT * FROM "+ TABLE_CLASS + " WHERE " + COLUMN_CLASS_NAME + " = " + active;
        List<ModelClass> modelClasses = new ArrayList<>();

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                int class_id = resultSet.getInt(COLUMN_USER_ID);
                String class_name = resultSet.getString(COLUMN_CLASS_NAME);

                ModelClass modelClass1 = new ModelClass(class_id,"");
                ModelClass.add(modelClass1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return modelClasses;
    }

    public static void addAttendance(ModelAttendance modelAttendance, Connection connection){

        String sql = "INSERT INTO " + TABLE_ATTENDANCE + "("+ COLUMN_ID + "," + COLUMN_USER_ID + "," + COLUMN_CLASS_ID +") " +
                "VALUES(?,?,?)";

        try{
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, modelAttendance.getId());
            pstmt.setString(2, modelAttendance.getUser_id());
            pstmt.setString(3, modelAttendance.getClass_id());
            pstmt.executeUpdate();

            pstmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}


