/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.sql.Connection;
import java.lang.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Majey
 */
public class Operations {

    public static boolean isLogin(String username, String password, JFrame frame) {

        try {

            Connection myConn = MySQLConnection.getConnection();
            if (LoginSession.Usertype.equals("Student")) {
                String mySqlQuery
                        = "SELECT EnrollmentId, StudName FROM student WHERE EnrollmentId= '"
                        + username
                        + "' AND Password = '"
                        + password + "'";
                PreparedStatement preparedStatement = myConn.prepareStatement(mySqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    LoginSession.UserId = resultSet.getString("EnrollmentId");
                    //LoginSession.Usertype = resultSet.getString("Usertype");
                    LoginSession.UserName = resultSet.getString("StudName");

                    return true;
                }

            }
            
            
                if (LoginSession.Usertype.equals("ExamCell")) {
                String mySqlQuery
                        = "SELECT ExamCellId, Name FROM examcell WHERE ExamCellId = '"
                        + username
                        + "' AND Password = '"
                        + password + "'";
                PreparedStatement preparedStatement = myConn.prepareStatement(mySqlQuery);
                //System.out.println(mySqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    LoginSession.UserId = resultSet.getString("ExamCellId");
                    //LoginSession.Usertype = resultSet.getString("Usertype");
                    LoginSession.UserName = resultSet.getString("Name");

                    return true;
                }

            }

        } catch (Exception exception) {
            JOptionPane.showMessageDialog(frame, "Database error: " + exception.getMessage());
        }

        return false;
    }
}
