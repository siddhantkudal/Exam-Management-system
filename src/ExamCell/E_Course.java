package ExamCell;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import test.MySQLConnection;

/**
 *
 * @author ikuna
 */
public class E_Course extends javax.swing.JPanel {

    /**
     * Creates new form E_Course
     */
    public E_Course() {
        initComponents();
        
        course_table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD,14));
        course_table.getTableHeader().setOpaque(false);
        course_table.getTableHeader().setBackground(new Color(32,136,203));
        course_table.getTableHeader().setForeground(new Color(255,255,255));
        course_table.setRowHeight(25);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Dept_select = new javax.swing.JComboBox<>();
        Semester_code = new javax.swing.JComboBox<>();
        Subject_code = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        course_table = new javax.swing.JTable();
        addcourse = new javax.swing.JButton();
        searchcourse = new javax.swing.JButton();
        deletecourse = new javax.swing.JButton();
        updatecourse = new javax.swing.JButton();

        setBackground(new java.awt.Color(250, 250, 250));
        setMinimumSize(new java.awt.Dimension(886, 551));
        setPreferredSize(new java.awt.Dimension(886, 551));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Department");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Semester");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("Subject Type");

        Dept_select.setBackground(new java.awt.Color(19, 103, 149));
        Dept_select.setForeground(new java.awt.Color(250, 250, 250));
        Dept_select.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "CSE", "IT", "ENTC", "ELN", "CIVIL", "MECH", "MTECH", " " }));
        Dept_select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Dept_selectActionPerformed(evt);
            }
        });

        Semester_code.setBackground(new java.awt.Color(19, 103, 149));
        Semester_code.setForeground(new java.awt.Color(250, 250, 250));
        Semester_code.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "1", "2", "3", "4", "5", "6", "7", "8" }));

        Subject_code.setBackground(new java.awt.Color(19, 103, 149));
        Subject_code.setForeground(new java.awt.Color(250, 250, 250));
        Subject_code.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Mandatory", "Elective" }));
        Subject_code.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Subject_codeActionPerformed(evt);
            }
        });

        course_table.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        course_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Subject Code", "Subject Name", "Dept Code", "Semester", "Class", "Subject Type", "Theory/Prcatical"
            }
        ));
        course_table.setFocusable(false);
        course_table.setIntercellSpacing(new java.awt.Dimension(0, 0));
        course_table.setRowHeight(25);
        course_table.setSelectionBackground(new java.awt.Color(0, 102, 255));
        course_table.setShowVerticalLines(false);
        course_table.getTableHeader().setReorderingAllowed(false);
        course_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                course_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(course_table);

        addcourse.setBackground(new java.awt.Color(19, 103, 149));
        addcourse.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        addcourse.setForeground(new java.awt.Color(250, 250, 250));
        addcourse.setText("Add");
        addcourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addcourseActionPerformed(evt);
            }
        });

        searchcourse.setBackground(new java.awt.Color(19, 103, 149));
        searchcourse.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        searchcourse.setForeground(new java.awt.Color(250, 250, 250));
        searchcourse.setText("Search");
        searchcourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchcourseActionPerformed(evt);
            }
        });

        deletecourse.setBackground(new java.awt.Color(19, 103, 149));
        deletecourse.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        deletecourse.setForeground(new java.awt.Color(250, 250, 250));
        deletecourse.setText("Delete");
        deletecourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletecourseActionPerformed(evt);
            }
        });

        updatecourse.setBackground(new java.awt.Color(19, 103, 149));
        updatecourse.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        updatecourse.setForeground(new java.awt.Color(250, 250, 250));
        updatecourse.setText("Update");
        updatecourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatecourseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(addcourse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(239, 239, 239)
                .addComponent(updatecourse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(263, 263, 263)
                .addComponent(deletecourse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(116, 116, 116))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 869, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Dept_select, 0, 0, Short.MAX_VALUE))
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Semester_code, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Subject_code, 0, 0, Short.MAX_VALUE))
                        .addGap(83, 83, 83)
                        .addComponent(searchcourse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(407, 407, 407))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Semester_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(searchcourse))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Dept_select, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Subject_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addcourse)
                    .addComponent(updatecourse)
                    .addComponent(deletecourse))
                .addGap(132, 132, 132))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Subject_codeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Subject_codeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Subject_codeActionPerformed

    private void addcourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addcourseActionPerformed
        //jTabbedPane1.setSelectedIndex(6); 
        Add_Subject i = new Add_Subject();
        i.setVisible(true);// TODO add your handling code here:

    }//GEN-LAST:event_addcourseActionPerformed

    private void searchcourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchcourseActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tbmodel = (DefaultTableModel)course_table.getModel();
        tbmodel.setRowCount(0);
        try {

            Connection myConn = MySQLConnection.getConnection();

            String dept_select = "="+"'"+Dept_select.getSelectedItem().toString()+"'";
            String semester_code = "="+"'"+Semester_code.getSelectedItem().toString()+"'";
            String subject_type = "="+"'"+Subject_code.getSelectedItem().toString()+"'";

            //            String s1="=";
            //            String s2="=";
            //            String s3="=";

            //if (LoginSession.Usertype.equals("ExamCell"))
            if (true){

                if(dept_select.equals("='All'"))
                {
                    dept_select=" in('IT','CSE','ELN','ENTC','MECH','CIVIL','MTECH')";

                }
                if(semester_code.equals("='All'"))
                {
                    semester_code="in(1,2,3,4,5,6,7,8)";

                }
                if(subject_type.equals("='All'"))
                {
                    subject_type=" in('Mandatory','Elective')";

                }
                String mySqlQuery
                = "SELECT Sub_Code,Sub_Name,subject.Type,Department.Dept_code,subject.Semester,subject.Class,subject.TheoryPractical from subject inner join Department on subject.Dept_Id = Department.Dept_Id where subject.Semester " + semester_code + " and subject.Type " + subject_type + " and subject.Dept_Id in (select Dept_Id from department where Dept_code " + dept_select + ")";

                PreparedStatement preparedStatement = myConn.prepareStatement(mySqlQuery);
                //System.out.println(mySqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {

                    String subject_code = resultSet.getString("Sub_Code");
                    String subject_name = resultSet.getString("Sub_Name");
                    String dept_code = resultSet.getString("Dept_Code");
                    String semester =String.valueOf(resultSet.getInt("Semester"));
                    String Class = resultSet.getString("Class");
                    String type = resultSet.getString("Type");
                    String Type2=resultSet.getString("TheoryPractical");
                   
                
                    
                    String tbdata[] = {subject_code,subject_name,dept_code,semester,Class,type,Type2};
                  

                    tbmodel.addRow(tbdata);
                     
                }
                myConn.close();

            }

        } catch (Exception exception) {
            //System.out.print(exception);
            JOptionPane.showMessageDialog(this, "Database error: " + exception.getMessage());
        }
    }//GEN-LAST:event_searchcourseActionPerformed

    private void deletecourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletecourseActionPerformed
        // TODO add your handling code here:
        int yes_no = JOptionPane.showConfirmDialog(this, "Do you want to Delete?","Confirm",JOptionPane.YES_NO_OPTION);
        if(yes_no==JOptionPane.YES_OPTION){
            try{
                Connection myConn = MySQLConnection.getConnection();
                // DefaultTableModel tbmodel = (DefaultTableModel)course_table.getModel();
                //tbmodel.setRowCount(0);
                int select_row=course_table.getSelectedRow();
                
                String subcode=(course_table.getModel().getValueAt(select_row,0).toString());
                String query="DELETE from subject where Sub_Code="+"'"+subcode+"'";
                PreparedStatement preparedStatement= myConn.prepareStatement(query);

                preparedStatement.executeUpdate();
                DefaultTableModel tbmodel1 = (DefaultTableModel)course_table.getModel();
                tbmodel1.setRowCount(0);
                //show_user();

                JOptionPane.showMessageDialog(null, "Deleted Subject Successfully!!");

            }
            catch(Exception ex)
            {
                // System.out.print(ex);
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }

    }//GEN-LAST:event_deletecourseActionPerformed

    private void updatecourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatecourseActionPerformed
       // jTabbedPane1.setSelectedIndex(5);
       //Update_Subject i = new Update_Subject();
       // i.setVisible(true);

         //TODO add your handling code here:
        Update_Subject RowData= new Update_Subject();
        int select_row=course_table.getSelectedRow();
        String subcode=(course_table.getModel().getValueAt(select_row,0).toString());
        String subname=(course_table.getModel().getValueAt(select_row,1).toString());
        String deptcode=(course_table.getModel().getValueAt(select_row,2).toString());
        Object uclass=(course_table.getModel().getValueAt(select_row,4).toString());
        Object usubtype=(course_table.getModel().getValueAt(select_row,5).toString());
        Object sem=(course_table.getModel().getValueAt(select_row,3));
        RowData.setVisible(true);
        RowData.pack();
        RowData.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
       RowData.Update_SubCode.setText(subcode);
        
        RowData.Update_SubName.setText(subname);
        
       RowData.Update_DeptCode.setText(deptcode);

        

       RowData.Update_Semester.setSelectedItem(sem);

        
       RowData.Update_Class.setSelectedItem(uclass);
        
        RowData.Update_Type.setSelectedItem(usubtype);

    }//GEN-LAST:event_updatecourseActionPerformed

    private void course_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_course_tableMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_course_tableMouseClicked

    private void Dept_selectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Dept_selectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Dept_selectActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Dept_select;
    private javax.swing.JComboBox<String> Semester_code;
    private javax.swing.JComboBox<String> Subject_code;
    private javax.swing.JButton addcourse;
    private javax.swing.JTable course_table;
    private javax.swing.JButton deletecourse;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton searchcourse;
    private javax.swing.JButton updatecourse;
    // End of variables declaration//GEN-END:variables
}
