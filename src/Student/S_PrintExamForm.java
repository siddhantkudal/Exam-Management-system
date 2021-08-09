/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Student;

import static Student.S_Home.EXAMSEATNO;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import test.LoginSession;
import test.MySQLConnection;
import test.Student_Dashboard;
import static test.Student_Dashboard.imgdb;
import static test.Student_Dashboard.newImage;

/**
 *
 * @author Asus
 */
public class S_PrintExamForm extends javax.swing.JFrame {
    
    /**
     * Creates new form PrintExamForm
     */
    public S_PrintExamForm() {
        initComponents();
        StudName.setText(LoginSession.UserName);
        EnrollmentId.setText(LoginSession.UserId);
        
        int deptid=0;
        
        try {
            Connection myConn = MySQLConnection.getConnection();

            //String sr = srin.getText();
            String q = "select * from student where EnrollmentId=" +LoginSession.UserId;
            PreparedStatement preparedStatement;

            preparedStatement = myConn.prepareStatement(q);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {//now on 1st row  

                imgdb = resultSet.getBytes("images");
                ImageIcon image = new ImageIcon(imgdb);
                Image im = image.getImage();
                Image myimg = im.getScaledInstance(StudImg.getWidth(), StudImg.getHeight(), Image.SCALE_SMOOTH);
                newImage = new ImageIcon(myimg);
                StudImg.setIcon(newImage);
                
                Studmothername.setText(resultSet.getString("MotherName"));
                String gender=resultSet.getString("Gender");
                if(gender.equals("M")){
                     Gender.setText("Male");
                }else{
                Gender.setText("Female");
                }
                       
                EnrollmentId.setText(resultSet.getString("EnrollmentId"));
                Examseatno.setText(resultSet.getString("ExamSeatNo"));
                Category.setText(resultSet.getString("CasteCategory"));
                String fulladdress=resultSet.getString("Address")+","+resultSet.getString("City")+","+resultSet.getString("State");
                Address.setText(fulladdress);                
                ContactNo.setText(resultSet.getString("MobileNo"));
                EmailId.setText(resultSet.getString("email"));
                DOB.setText(resultSet.getString("dob"));  
                deptid=resultSet.getInt("DeptId");

            }//end of if 
            String queary = "select Dept_name from department where Dept_Id =" + deptid;

            PreparedStatement preparedStatement1 = myConn.prepareStatement(queary);
         
            ResultSet resultSet1 = preparedStatement1.executeQuery();

            while (resultSet1.next()) {

                DeptName.setText(resultSet1.getString("Dept_name"));

            }
            
            
            
            myConn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (Exception ex) {
            Logger.getLogger(Student_Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //____________________________________________For Table Data from database_______________________________________
        
        DefaultTableModel tbmodel = (DefaultTableModel)subjectListTable.getModel();
        tbmodel.setRowCount(0);
        
        try {
            Connection myConn = MySQLConnection.getConnection();
            String q1 = "select * from examform where ExaminationId='"+S_Exam.examid+"' and StudExaminationSeatNo ='"+EXAMSEATNO+"' and EnrollmentId="+LoginSession.UserId;
            String subcodearray="";
            PreparedStatement ps1 = myConn.prepareStatement(q1);
         
            ResultSet rs1 = ps1.executeQuery();

            while (rs1.next()) {
                subcodearray=rs1.getString("Subjects");
               
            }
                                    
            String subarray []=subcodearray.split(",");
            int sr=0;
            
            for(String Subcodes:subarray ){
                sr=sr+1;
                String mySqlQuery
                        = "SELECT SubCode,ExamDate, ExamStartTime, ExamEndTime from exam inner join student on exam.DeptId = student.DeptId " +
                        "where student.Class=exam.Class and student.AcademId=exam.AcademicId and student.EnrollmentId=" +LoginSession.UserId+
                        " and SetVisible=1 and exam.SubCode='"+Subcodes +"' order by exam.ExamDate";
                      
                PreparedStatement preparedStatement = myConn.prepareStatement(mySqlQuery);
                
                ResultSet resultSet = preparedStatement.executeQuery();
                
                
                String examdate="";
                String examstarttime="";
                String examendtime="";
                
                String subname="";

                if (resultSet.next()) {

                    examdate = resultSet.getString("ExamDate");
                    
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MMM-yyyy");
        
                    examdate=sdf2.format(sdf.parse(examdate));
                    
                    
                    
                    examstarttime = resultSet.getString("ExamStartTime");
                    examendtime = resultSet.getString("ExamEndTime");
                    
                    //String SubjectName = resultSet.getString("Sub_Name");
                    String query
                        = "SELECT Sub_Name from subject where Sub_Code='"+Subcodes+"'";
           
                      
                    PreparedStatement preparedStatement1 = myConn.prepareStatement(query);
                   // System.out.println(query);
                    ResultSet resultSet1 = preparedStatement1.executeQuery();
                    
                    if(resultSet1.next()){
                        subname = resultSet1.getString("Sub_Name");
                    }
                
                    
                    String tbdata[] = {String.valueOf(sr), Subcodes, subname, examdate, examstarttime + "-" + examendtime};
                    /*DefaultTableModel tbmodel = (DefaultTableModel)course_table.getModel();*/
                    tbmodel.addRow(tbdata);
                

                }              
            }       
         myConn.close();
        }catch (Exception ex) {
            System.out.println(ex);
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

        jScrollPane6 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        toprint = new javax.swing.JPanel();
        DBIMGLABLE = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        DeptName = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        EnrollmentId = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        StudName = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        Studmothername = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        Gender = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        DOB = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        Category = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Address = new javax.swing.JTextArea();
        jLabel26 = new javax.swing.JLabel();
        ContactNo = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        EmailId = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        subjectListTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        witlogo = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        StudImg = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        Examseatno = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        printbtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        toprint.setBackground(new java.awt.Color(255, 255, 255));
        toprint.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        toprint.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        DBIMGLABLE.setText("PHOTO");
        DBIMGLABLE.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        toprint.add(DBIMGLABLE, new org.netbeans.lib.awtextra.AbsoluteConstraints(1609, 218, 115, 130));

        jLabel8.setText("Sign");
        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        toprint.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 300, 150, 50));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Application Form for");
        toprint.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 115, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        toprint.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 169, 20));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Examination,");
        toprint.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 260, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("ESE");
        jLabel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        toprint.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 260, 164, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Department Name :");
        toprint.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, -1));

        DeptName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        DeptName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DeptName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        toprint.add(DeptName, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, 270, 20));

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setText("  To,\n  The COE, Walchand Institute of Technology, Solapur.\n  Sir/Mam,\n  I request the permission to present myself at the exam for the \n  papers mentioned below.");
        jTextArea1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane2.setViewportView(jTextArea1);

        toprint.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 690, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Enrollment ID :");
        toprint.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 106, -1));

        EnrollmentId.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        EnrollmentId.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EnrollmentId.setText("enroll");
        EnrollmentId.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        toprint.add(EnrollmentId, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 390, 140, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Personal information");
        jLabel14.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        toprint.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 890, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Full Name :");
        toprint.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, 123, -1));

        StudName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        StudName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        StudName.setText("jLabel16");
        StudName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        toprint.add(StudName, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 460, 286, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Mother Name :");
        toprint.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 460, -1, -1));

        Studmothername.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Studmothername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Studmothername.setText("jLabel18");
        Studmothername.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        toprint.add(Studmothername, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 460, 366, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Gender :");
        toprint.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 123, -1));

        Gender.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Gender.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Gender.setText("jLabel20");
        Gender.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        toprint.add(Gender, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 490, 115, -1));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("DOB :");
        toprint.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 490, 40, -1));

        DOB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        DOB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DOB.setText("jLabel22");
        DOB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        toprint.add(DOB, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 490, 115, -1));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setText("Category :");
        toprint.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 490, 87, -1));

        Category.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Category.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Category.setText("jLabel24");
        Category.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        toprint.add(Category, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 490, 185, -1));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setText("Address :");
        jLabel25.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        toprint.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 510, 884, -1));

        Address.setEditable(false);
        Address.setColumns(20);
        Address.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Address.setRows(2);
        Address.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane3.setViewportView(Address);

        toprint.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, 884, 40));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setText("Contact Number :");
        toprint.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 580, 109, -1));

        ContactNo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ContactNo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ContactNo.setText("jLabel27");
        ContactNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        toprint.add(ContactNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 580, 273, -1));

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel28.setText("Email ID :");
        toprint.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 580, -1, -1));

        EmailId.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        EmailId.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EmailId.setText("jLabel29");
        EmailId.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        toprint.add(EmailId, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 580, 370, -1));

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setText("Subject Opted for the Examination ");
        jLabel30.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        toprint.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 620, 884, -1));

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel31.setText("Exam Fee :");
        toprint.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 930, 72, -1));

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("₹ 1950");
        jLabel32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        toprint.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 930, 84, -1));

        jTextArea3.setEditable(false);
        jTextArea3.setColumns(20);
        jTextArea3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextArea3.setLineWrap(true);
        jTextArea3.setRows(2);
        jTextArea3.setText("Declaration : I here by declare that all statements made in this application are true complete and corretct to the best of my knowledge and belief . I understand that\n in the event of any information being found false or incorrect, my candidature is liable to be cancelled or reject.");
        jTextArea3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane5.setViewportView(jTextArea3);

        toprint.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 970, 890, 40));

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel33.setText("Place :");
        jLabel33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        toprint.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 1030, 227, 41));

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel34.setText("Date :");
        jLabel34.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        toprint.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 1071, 227, 50));

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel35.setText("Principal's Signature & Seal");
        jLabel35.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel35.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        toprint.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 1030, 336, 90));

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel36.setText("Student's Signnature");
        jLabel36.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel36.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        toprint.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 1030, 305, 90));

        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));

        subjectListTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        subjectListTable.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        subjectListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Sr.No.", "Code", "Paper Name", "Date", "Time"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        subjectListTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        subjectListTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        subjectListTable.setEnabled(false);
        subjectListTable.setGridColor(new java.awt.Color(0, 0, 0));
        subjectListTable.setOpaque(false);
        subjectListTable.setRequestFocusEnabled(false);
        subjectListTable.setRowHeight(25);
        subjectListTable.setShowGrid(true);
        subjectListTable.setUpdateSelectionOnSort(false);
        jScrollPane4.setViewportView(subjectListTable);
        if (subjectListTable.getColumnModel().getColumnCount() > 0) {
            subjectListTable.getColumnModel().getColumn(0).setPreferredWidth(10);
            subjectListTable.getColumnModel().getColumn(1).setPreferredWidth(50);
            subjectListTable.getColumnModel().getColumn(2).setPreferredWidth(250);
            subjectListTable.getColumnModel().getColumn(3).setPreferredWidth(50);
            subjectListTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        }

        toprint.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 660, 890, 260));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 23)); // NOI18N
        jLabel2.setText("WALCHAND INSTITUTE OF TECHNOLOGY , SOLAPUR");
        toprint.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, -1, 26));

        witlogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rec/small wit logo.png"))); // NOI18N
        toprint.add(witlogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 70, 130));

        jLabel37.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel37.setText("P.B.No.634, Walchand Hirachand Marg, Ashok Chowk, Solapur - 413006 (Maharashtra)");
        toprint.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 528, 23));

        jLabel38.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel38.setText("Phone : (0217)2652700, 2653040, 2391700 Extn: 318, Fax:(0217)2651538");
        toprint.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, -1, -1));

        jLabel39.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel39.setText("Email :");
        toprint.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, 49, -1));

        jLabel40.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(0, 102, 204));
        jLabel40.setText("principal@witsolapur.org");
        toprint.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, 171, -1));

        jLabel41.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(0, 102, 204));
        jLabel41.setText("principal.witsolapur@gmail.com ");
        toprint.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Examination Form");
        toprint.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 140, 180, 30));

        StudImg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        toprint.add(StudImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 150, 150, 150));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Exam Seat No. :");
        toprint.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 390, 106, -1));

        Examseatno.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Examseatno.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Examseatno.setText("examsetno");
        Examseatno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        toprint.add(Examseatno, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 390, 140, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(toprint, javax.swing.GroupLayout.PREFERRED_SIZE, 917, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(112, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(toprint, javax.swing.GroupLayout.PREFERRED_SIZE, 1185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane6.setViewportView(jPanel3);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        printbtn.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        printbtn.setText("Print");
        printbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(458, 458, 458)
                .addComponent(printbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(553, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(printbtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void printbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printbtnActionPerformed
        // TODO add your handling code here:
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("HT");

        job.setPrintable(new Printable(){
            public int print(Graphics pg,PageFormat pf, int pageNum){
                pf.setOrientation(PageFormat.LANDSCAPE);

                Paper paper = new Paper();
                double margin = 15;
                paper.setImageableArea(margin, margin, paper.getWidth() - margin * 3, paper.getHeight()- margin * 3);
                pf.setPaper(paper);

                if(pageNum > 0){
                    return Printable.NO_SUCH_PAGE;
                }
                Graphics2D g2 = (Graphics2D)pg;
                g2.translate(pf.getImageableX(), pf.getImageableY());
                g2.scale(0.6,0.6);
                toprint.print(g2);
                return Printable.PAGE_EXISTS;
            }
        });
        boolean ok = job.printDialog();
        if(ok){
            try{

                job.print();
            }
            catch (PrinterException ex){
                ex.printStackTrace();
            }
        }

    }//GEN-LAST:event_printbtnActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(S_PrintExamForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(S_PrintExamForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(S_PrintExamForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(S_PrintExamForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new S_PrintExamForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Address;
    private javax.swing.JLabel Category;
    private javax.swing.JLabel ContactNo;
    private javax.swing.JLabel DBIMGLABLE;
    private javax.swing.JLabel DOB;
    private javax.swing.JLabel DeptName;
    private javax.swing.JLabel EmailId;
    private javax.swing.JLabel EnrollmentId;
    private javax.swing.JLabel Examseatno;
    private javax.swing.JLabel Gender;
    public static javax.swing.JLabel StudImg;
    private javax.swing.JLabel StudName;
    private javax.swing.JLabel Studmothername;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JButton printbtn;
    private javax.swing.JTable subjectListTable;
    private javax.swing.JPanel toprint;
    private javax.swing.JLabel witlogo;
    // End of variables declaration//GEN-END:variables
}
