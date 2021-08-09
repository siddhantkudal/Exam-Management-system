/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Student;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import test.LoginSession;
import test.MySQLConnection;

/**
 *
 * @author ikuna
 */
public class S_Course extends javax.swing.JPanel {

    /**
     * Creates new form S_Course
     */
    public S_Course() {
        initComponents();
        StudCourseTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD,14));
        StudCourseTable.getTableHeader().setOpaque(false);
        StudCourseTable.getTableHeader().setBackground(new Color(32,136,203));
        StudCourseTable.getTableHeader().setForeground(new Color(255,255,255));
        StudCourseTable.setRowHeight(25);
        DefaultTableModel tbmodel = (DefaultTableModel)StudCourseTable.getModel();
        tbmodel.setRowCount(0);
        try {

            Connection myConn = MySQLConnection.getConnection();

            //if (LoginSession.Usertype.equals("ExamCell")) 
            if (true){
                
                String mySqlQuery
                        = "SELECT subject.Sub_Code,subject.Sub_Name,subject.Type from subject "+
                          "inner join student on student.DeptId=subject.Dept_Id where "+
                          "cast((substring(Student.class,5,5)) as char(1)) =CAST(Subject.Semester AS char(1)) and "+
                          "subject.class=cast((substring(Student.class,1,2)) as char(2)) and student.EnrollmentId="+LoginSession.UserId;
                      
                PreparedStatement preparedStatement = myConn.prepareStatement(mySqlQuery);
                //System.out.println(mySqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {

                    String SubjectCode = resultSet.getString("Sub_Code");
                    String SubjectName = resultSet.getString("Sub_Name");
                    String  SubjectType = resultSet.getString("Type");
         
                    
                    
                    
                    String tbdata[] = {SubjectCode,SubjectName,SubjectType};
                    /*DefaultTableModel tbmodel = (DefaultTableModel)course_table.getModel();*/
                    
                    tbmodel.addRow(tbdata);
                    
                }

            }

        } catch (Exception exception) {
            //System.out.print(exception);
            JOptionPane.showMessageDialog(this, "Database error: " + exception.getMessage());
        }
    }
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        StudCourseTable = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        StudCourseTable.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        StudCourseTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Subject Code", "Subject Name", "Subject Type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        StudCourseTable.setFocusable(false);
        StudCourseTable.setIntercellSpacing(new java.awt.Dimension(0, 0));
        StudCourseTable.setRowHeight(25);
        StudCourseTable.setSelectionBackground(new java.awt.Color(232, 57, 95));
        StudCourseTable.setShowGrid(false);
        StudCourseTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(StudCourseTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 874, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                .addGap(38, 38, 38))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable StudCourseTable;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
