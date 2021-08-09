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
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import test.LoginSession;
import test.MySQLConnection;

/**
 *
 * @author ikuna
 */
public class Exam_Form extends javax.swing.JFrame {

    /**
     * Creates new form Exam_Form
     */
    public Exam_Form() {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        SubjectTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        SubjectTable.getTableHeader().setOpaque(false);
        SubjectTable.getTableHeader().setBackground(new Color(32, 136, 203));
        SubjectTable.getTableHeader().setForeground(new Color(255, 255, 255));
        SubjectTable.setRowHeight(25);

        String studname = "";
        String contact = "";
        String address = "";
        String dob = "";
        String dept = "";
        String mothername = "";
        String emailid = "";
        String state = "";
        String category = "";
        String seatno = "";
        String enrollmentid = "";
        Date d = new Date();
        DefaultTableModel tbmodel = (DefaultTableModel) SubjectTable.getModel();
        tbmodel.setRowCount(0);
        try {

            Connection myConn = MySQLConnection.getConnection();

            String mySqlQuery
                    = "SELECT EnrollmentId,StudName,MobileNo,email,dob,DeptId,MotherName,State,Address,ExamSeatNo,CasteCategory from student where EnrollmentId=" + LoginSession.UserId;

            PreparedStatement preparedStatement = myConn.prepareStatement(mySqlQuery);
            //System.out.println(mySqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                studname = resultSet.getString("StudName");
                enrollmentid = resultSet.getString("EnrollmentId");
                contact = resultSet.getString("MobileNo");
                emailid = resultSet.getString("email");
                dob = resultSet.getString("dob");
                dept = resultSet.getString("DeptId");
                mothername = resultSet.getString("MotherName");
                state = resultSet.getString("State");
                address = resultSet.getString("Address");
                seatno = resultSet.getString("ExamSeatNo");
                category = resultSet.getString("CasteCategory");
                d = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
            }

            StudName.setText(studname);
            EnrollmentId.setText(enrollmentid);
            ContactNo.setText(contact);
            EmailId.setText(emailid);
            DateOfBirth.setDate(d);
            MotherName.setText(mothername);
            State.setSelectedItem(state);
            Address.setText(address);
            ExamSeatNo.setText(seatno);
            Category.setSelectedItem(category);

            String query
                    = "SELECT Dept_code from department where Dept_Id=" + dept;

            PreparedStatement preparedStatement1 = myConn.prepareStatement(query);
            //System.out.println(query);
            ResultSet resultSet1 = preparedStatement1.executeQuery();

            String deptname = "";
            while (resultSet1.next()) {
                deptname = resultSet1.getString("Dept_code");
            }
            Department.setSelectedItem(deptname);

            
            String q1 = "SELECT Examinationid, SubCode,ExamDate,ExamType from exam inner join student on exam.DeptId = student.DeptId " +
                        "where student.Class=exam.Class and student.AcademId=exam.AcademicId and student.EnrollmentId=" +LoginSession.UserId+
                        " and SetVisible=1 order by exam.ExamDate";
            PreparedStatement ps1 = myConn.prepareStatement(q1);

            ResultSet rs1 = ps1.executeQuery();
            String subcode = "";
            String subtype = "";
            String examtype = "";
            String subname = "";
            
            int sr = 0;
            while (rs1.next()) {
                    subcode = rs1.getString("SubCode");                   
                    examtype = rs1.getString("ExamType");
                    sr=sr+1;                    
                    String q2
                        = "SELECT Sub_Name,Type from subject where Sub_Code='"+subcode+"'";          
                      
                    PreparedStatement ps2 = myConn.prepareStatement(q2);
                  
                    ResultSet rs2 = ps2.executeQuery();
                    
                    while(rs2.next()){
                        subname = rs2.getString("Sub_Name");
                        subtype=rs2.getString("Type");
                        
                    }
                    String tbdata[] = {String.valueOf(sr),subcode,subname,examtype,subtype};
                   
                    tbmodel.addRow(tbdata);
               

                    
                    
            }



        } catch (Exception exception) {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        Department = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        submitbutton = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        Category = new javax.swing.JComboBox<>();
        EmailId = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        SubjectTable = new javax.swing.JTable();
        StudName = new javax.swing.JTextField();
        DateOfBirth = new com.toedter.calendar.JDateChooser();
        MotherName = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        State = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        ContactNo = new javax.swing.JTextField();
        EnrollmentId = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        Address = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        ExamSeatNo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(772, 568));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(772, 568));

        jPanel2.setBackground(new java.awt.Color(19, 103, 149));
        jPanel2.setForeground(new java.awt.Color(250, 250, 250));

        jLabel15.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(250, 250, 250));
        jLabel15.setText("Date of Birth");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(250, 250, 250));
        jLabel7.setText("Mother's name:");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(250, 250, 250));
        jLabel8.setText("Department:");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(250, 250, 250));
        jLabel10.setText("Contact no:");

        jLabel12.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(250, 250, 250));
        jLabel12.setText("Email id:");

        Department.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Department.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CSE", "IT", "ENTC", "CIVIL", "ELN", "MECH", "MTECH" }));

        jLabel11.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(250, 250, 250));
        jLabel11.setText("Category:");

        submitbutton.setBackground(new java.awt.Color(204, 204, 204));
        submitbutton.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        submitbutton.setText("Submit");
        submitbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitbuttonActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(250, 250, 250));
        jLabel6.setText("Student Name:");

        Category.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Category.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Open", "SC", "ST", "OBC", "Others" }));

        EmailId.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        EmailId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmailIdActionPerformed(evt);
            }
        });

        SubjectTable.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        SubjectTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Sr. No.", "SUBJECT CODE", "SUBJECT NAME", "EXAM TYPE", "SUBJECT TYPE", "SELECTED"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        SubjectTable.setFocusable(false);
        SubjectTable.setIntercellSpacing(new java.awt.Dimension(0, 0));
        SubjectTable.setRowHeight(25);
        SubjectTable.setSelectionBackground(new java.awt.Color(232, 57, 95));
        SubjectTable.setShowVerticalLines(false);
        SubjectTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(SubjectTable);
        if (SubjectTable.getColumnModel().getColumnCount() > 0) {
            SubjectTable.getColumnModel().getColumn(0).setPreferredWidth(10);
            SubjectTable.getColumnModel().getColumn(1).setPreferredWidth(40);
            SubjectTable.getColumnModel().getColumn(2).setPreferredWidth(250);
            SubjectTable.getColumnModel().getColumn(3).setPreferredWidth(50);
            SubjectTable.getColumnModel().getColumn(4).setPreferredWidth(50);
            SubjectTable.getColumnModel().getColumn(5).setPreferredWidth(50);
        }

        StudName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        StudName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StudNameActionPerformed(evt);
            }
        });

        MotherName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MotherName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MotherNameActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(250, 250, 250));
        jLabel14.setText("State:");

        State.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        State.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Andhra Pradesh", "Assam", "Arunachal Pradesh", "Bihar", "Goa", "Gujarat", "Jammu and Kashmir", "Jharkhand", "West Bengal", "Karnataka", "Kerala", "Madhya Pradesh", "Maharashtra", "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Orissa", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", "Tripura", "Uttaranchal", "Uttar Pradesh", "Haryana", "Himachal Pradesh", "Chhattisgarh" }));
        State.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StateActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(250, 250, 250));
        jLabel13.setText("Address:");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Enrollment Id:");

        ContactNo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        EnrollmentId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnrollmentIdActionPerformed(evt);
            }
        });

        Address.setColumns(1);
        Address.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Address.setLineWrap(true);
        Address.setRows(2);
        Address.setWrapStyleWord(true);
        jScrollPane6.setViewportView(Address);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Exam Form");

        jLabel16.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(250, 250, 250));
        jLabel16.setText("Exam Seat No.: ");

        ExamSeatNo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ExamSeatNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExamSeatNoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(452, 452, 452)
                        .addComponent(submitbutton))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 970, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGap(33, 33, 33)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(DateOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(Department, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel10))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(StudName, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(ContactNo, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(279, 279, 279)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(24, 24, 24)
                                        .addComponent(MotherName, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel14)
                                            .addComponent(jLabel16)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel11))
                                        .addGap(23, 23, 23)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(Category, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(State, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(EmailId, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(ExamSeatNo, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(399, 399, 399)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EnrollmentId, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(74, 74, 74))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EnrollmentId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(StudName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(ExamSeatNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel16)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(jLabel10))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(EmailId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel12)))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(ContactNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(State, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(Category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(DateOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15))))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(Department, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(MotherName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(22, 22, 22)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(submitbutton)
                        .addGap(39, 39, 39))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(jLabel13)
                        .addGap(458, 458, 458))))
        );

        jScrollPane1.setViewportView(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1072, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void submitbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitbuttonActionPerformed
        // TODO add your handling code here:
        int select_row = 0;
        String selectedsubcodes = "";
        for (select_row = 0; select_row < (SubjectTable.getModel().getRowCount()); select_row++) {

            Boolean check = (Boolean) SubjectTable.getModel().getValueAt(select_row, 5);
            //System.out.println(check);

            if (Boolean.TRUE.equals(check)) {
                selectedsubcodes += (SubjectTable.getModel().getValueAt(select_row, 1).toString()) + ",";
            }
        }
        //System.out.println(selectedsubcodes);
           S_Exam p1=new S_Exam();
           
           try {

            Connection myConn = MySQLConnection.getConnection();
            
            String q1="insert into examform (Examinationid,StudExaminationSeatNo,EnrollmentId,Subjects) values ('"+p1.examid+"', '"+ExamSeatNo.getText()+"',"+LoginSession.UserId+",'"+selectedsubcodes+"' )";

            
            PreparedStatement preparedStatement2 = myConn.prepareStatement(q1);
            //System.out.println(q1);
             preparedStatement2.execute();
            myConn.close();
           }
           
           
           catch (Exception exception) {
            JOptionPane.showMessageDialog(this, "Database error: " + exception.getMessage());
        }
            int yes_no = JOptionPane.showConfirmDialog(this, "Do you want to Submit Exam Form?", "Confirm", JOptionPane.YES_NO_OPTION);

                if (yes_no == JOptionPane.YES_OPTION) {
                    S_PrintExamForm p2 = new S_PrintExamForm();
                    p2.setVisible(true);
                }

           
           
           
    }//GEN-LAST:event_submitbuttonActionPerformed

    private void EmailIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmailIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EmailIdActionPerformed

    private void StudNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StudNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StudNameActionPerformed

    private void MotherNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MotherNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MotherNameActionPerformed

    private void StateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StateActionPerformed

    private void EnrollmentIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnrollmentIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EnrollmentIdActionPerformed

    private void ExamSeatNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExamSeatNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ExamSeatNoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Exam_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Exam_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Exam_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Exam_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Exam_Form().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Address;
    private javax.swing.JComboBox<String> Category;
    private javax.swing.JTextField ContactNo;
    private com.toedter.calendar.JDateChooser DateOfBirth;
    private javax.swing.JComboBox<String> Department;
    private javax.swing.JTextField EmailId;
    private javax.swing.JTextField EnrollmentId;
    private javax.swing.JTextField ExamSeatNo;
    private javax.swing.JTextField MotherName;
    private javax.swing.JComboBox<String> State;
    private javax.swing.JTextField StudName;
    private javax.swing.JTable SubjectTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JButton submitbutton;
    // End of variables declaration//GEN-END:variables
}
