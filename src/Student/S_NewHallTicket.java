package Student;

import ExamCell.*;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ikuna
 */
public class S_NewHallTicket extends javax.swing.JPanel {

    /**
     * Creates new form E_HallTicket
     */
    public static byte[] imgdb;
    public static ImageIcon newImage;

    public S_NewHallTicket() {
        initComponents();
        toprint.setVisible(false);
        
        jLabel7.setText(test.LoginSession.UserName);
        EnrollmentId.setText(test.LoginSession.UserId);

        try {
            Connection myConn = MySQLConnection.getConnection();
            int deptid = 0;
            int accid = 0;
            String q = "select ExamSeatNo,CasteCategory,AcademId,Class,Gender,DeptId from student where EnrollmentId=" + test.LoginSession.UserId;
            PreparedStatement preparedStatement;

            preparedStatement = myConn.prepareStatement(q);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                Examseatno.setText(resultSet.getString("ExamSeatNo"));
                Category.setText(resultSet.getString("CasteCategory"));
                StudYear.setText(resultSet.getString("Class"));
                String gender = resultSet.getString("Gender");
                if (gender.equals("M")) {
                    Gender.setText("Male");
                } else {
                    Gender.setText("Female");
                }
                deptid = resultSet.getInt("DeptId");
                accid = resultSet.getInt("AcademId");

            }
            String queary = "select Dept_name from department where Dept_Id =" + deptid;

            PreparedStatement preparedStatement1 = myConn.prepareStatement(queary);

            ResultSet resultSet1 = preparedStatement1.executeQuery();

            while (resultSet1.next()) {

                DeptName.setText(resultSet1.getString("Dept_name"));

            }
            String q1 = "select AcademicYear from academicyear where Acad_Id =" + accid;

            PreparedStatement ps1 = myConn.prepareStatement(q1);

            ResultSet rs1 = ps1.executeQuery();

            while (rs1.next()) {

                Academicyear.setText(rs1.getString("AcademicYear"));

            }

            myConn.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        DefaultTableModel tbmodel = (DefaultTableModel) subjectListTable.getModel();
        tbmodel.setRowCount(0);

        try {
            Connection myConn = MySQLConnection.getConnection();
            String q1 = "select * from examform where ExaminationId='" + S_Exam.examid + "' and StudExaminationSeatNo ='" + EXAMSEATNO + "' and EnrollmentId=" + LoginSession.UserId;
            String subcodearray = "";
            PreparedStatement ps1 = myConn.prepareStatement(q1);

            ResultSet rs1 = ps1.executeQuery();

            while (rs1.next()) {
                subcodearray = rs1.getString("Subjects");

            }
            String subarray[] = subcodearray.split(",");
            int sr = 0;

            for (String Subcodes : subarray) {
                sr = sr + 1;
                String mySqlQuery
                        = "SELECT SubCode,ExamDate, ExamStartTime, ExamEndTime from exam inner join student on exam.DeptId = student.DeptId "
                        + "where student.Class=exam.Class and student.AcademId=exam.AcademicId and student.EnrollmentId=" + LoginSession.UserId
                        + " and SetVisible=1 and exam.SubCode='" + Subcodes + "' order by exam.ExamDate";

                PreparedStatement preparedStatement = myConn.prepareStatement(mySqlQuery);

                ResultSet resultSet = preparedStatement.executeQuery();

                String examdate = "";
                String examstarttime = "";
                String examendtime = "";

                String subname = "";

                if (resultSet.next()) {

                    examdate = resultSet.getString("ExamDate");

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MMM-yyyy");

                    examdate = sdf2.format(sdf.parse(examdate));

                    examstarttime = resultSet.getString("ExamStartTime");
                    examendtime = resultSet.getString("ExamEndTime");

                    //String SubjectName = resultSet.getString("Sub_Name");
                    String query
                            = "SELECT Sub_Name from subject where Sub_Code='" + Subcodes + "'";

                    PreparedStatement preparedStatement1 = myConn.prepareStatement(query);
                    // System.out.println(query);
                    ResultSet resultSet1 = preparedStatement1.executeQuery();

                    if (resultSet1.next()) {
                        subname = resultSet1.getString("Sub_Name");
                    }

                    String tbdata[] = {String.valueOf(sr), Subcodes, subname, examdate, examstarttime + "-" + examendtime};
                    /*DefaultTableModel tbmodel = (DefaultTableModel)course_table.getModel();*/
                    tbmodel.addRow(tbdata);

                }
            }
            myConn.close();
        } catch (Exception ex) {
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

        jPanel3 = new javax.swing.JPanel();
        Downloadbtn = new javax.swing.JButton();
        showbtn = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        toprint = new javax.swing.JPanel();
        witlogo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Examseatno = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        EnrollmentId = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        StudYear = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        Academicyear = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        Gender = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        subjectListTable = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel27 = new javax.swing.JLabel();
        DeptName = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        Category = new javax.swing.JLabel();
        StudImg = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(886, 551));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Downloadbtn.setBackground(new java.awt.Color(19, 103, 149));
        Downloadbtn.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        Downloadbtn.setForeground(new java.awt.Color(250, 250, 250));
        Downloadbtn.setText("Print/Download");
        Downloadbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DownloadbtnActionPerformed(evt);
            }
        });

        showbtn.setBackground(new java.awt.Color(19, 103, 149));
        showbtn.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        showbtn.setForeground(new java.awt.Color(250, 250, 250));
        showbtn.setText("Show");
        showbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(showbtn)
                .addGap(266, 266, 266)
                .addComponent(Downloadbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Downloadbtn)
                    .addComponent(showbtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));

        toprint.setBackground(new java.awt.Color(255, 255, 255));
        toprint.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        toprint.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        witlogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rec/small wit logo.png"))); // NOI18N
        toprint.add(witlogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 70, 130));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 23)); // NOI18N
        jLabel2.setText("WALCHAND INSTITUTE OF TECHNOLOGY ,SOLAPUR");
        toprint.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, -1, 26));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setText("Examination Hall Ticket");
        toprint.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 140, 230, 30));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel5.setText("Exam Name:");
        toprint.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 390, 100, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel6.setText("Name of Student :");
        toprint.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, -1, -1));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        toprint.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 190, 560, 20));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel8.setText("Sign");
        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        toprint.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 300, 150, 40));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel9.setText("Seat No :");
        toprint.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 100, -1));

        Examseatno.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        Examseatno.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Examseatno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        toprint.add(Examseatno, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 230, 160, 20));

        jLabel11.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel11.setText("Gender :");
        toprint.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 390, -1, -1));

        EnrollmentId.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        EnrollmentId.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EnrollmentId.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        toprint.add(EnrollmentId, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 230, 150, 20));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel13.setText("Year :");
        toprint.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 270, -1, -1));

        StudYear.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        StudYear.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        StudYear.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        toprint.add(StudYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 270, 150, 20));

        jLabel15.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel15.setText("Academic Year :");
        toprint.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, -1, -1));

        Academicyear.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        Academicyear.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Academicyear.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        toprint.add(Academicyear, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 270, 161, 20));

        jLabel17.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel17.setText("Category :");
        toprint.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, 106, -1));

        Gender.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        Gender.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Gender.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        toprint.add(Gender, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 390, 90, 20));

        jLabel19.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel19.setText("Enrollment ID :");
        toprint.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 230, -1, -1));

        jLabel20.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("E.S.C");
        jLabel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        toprint.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 390, 150, -1));

        subjectListTable.setAutoCreateRowSorter(true);
        subjectListTable.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        subjectListTable.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
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
                "SN", "Code", "Paper Name", "Date", "Time"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        subjectListTable.setRowHeight(25);
        subjectListTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(subjectListTable);
        if (subjectListTable.getColumnModel().getColumnCount() > 0) {
            subjectListTable.getColumnModel().getColumn(0).setResizable(false);
            subjectListTable.getColumnModel().getColumn(0).setPreferredWidth(20);
            subjectListTable.getColumnModel().getColumn(1).setResizable(false);
            subjectListTable.getColumnModel().getColumn(1).setPreferredWidth(50);
            subjectListTable.getColumnModel().getColumn(2).setResizable(false);
            subjectListTable.getColumnModel().getColumn(2).setPreferredWidth(350);
            subjectListTable.getColumnModel().getColumn(3).setResizable(false);
            subjectListTable.getColumnModel().getColumn(3).setPreferredWidth(50);
            subjectListTable.getColumnModel().getColumn(4).setResizable(false);
            subjectListTable.getColumnModel().getColumn(4).setPreferredWidth(50);
        }

        toprint.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 470, 890, 280));

        jLabel21.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel21.setText("Center Name :");
        toprint.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, 106, -1));

        jLabel22.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("WIT Solapur 413006");
        jLabel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        toprint.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(171, 350, 340, -1));

        jLabel23.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel23.setText("Principal Sign");
        jLabel23.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        toprint.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 770, 250, 80));

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextArea1.setRows(4);
        jTextArea1.setText("Note: Admit Card will be considered valid only if signed by Principal/Coordinator.Student must preserve & \nproduce this card at each session of the examination without which admission for Examination may be \ndisallowed. Adoption of recent changes in examination time table is a responsibility of Student. Use of any \ncommunication devices is strictly prohibited.* marked data is amended one.");
        jScrollPane2.setViewportView(jTextArea1);

        toprint.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 770, 631, 80));

        jLabel27.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel27.setText("Department :");
        toprint.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, -1, -1));

        DeptName.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        DeptName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DeptName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        toprint.add(DeptName, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 310, 450, 20));

        jLabel31.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel31.setText("P.B.No.634, Walchand Hirachand Marg, Ashok Chowk, Solapur - 413006 (Maharashtra)");
        toprint.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 528, 23));

        jLabel32.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel32.setText("Phone : (0217)2652700, 2653040, 2391700 Extn: 318, Fax:(0217)2651538");
        toprint.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, -1, -1));

        jLabel33.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel33.setText("Email :");
        toprint.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, 49, -1));

        jLabel34.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 102, 204));
        jLabel34.setText("principal@witsolapur.org");
        toprint.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, 171, -1));

        jLabel35.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 102, 204));
        jLabel35.setText("principal.witsolapur@gmail.com ");
        toprint.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, -1, -1));

        Category.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        Category.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Category.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        toprint.add(Category, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 390, 161, 20));

        StudImg.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        StudImg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        toprint.add(StudImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 140, 150, 150));

        jScrollPane3.setViewportView(toprint);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 964, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 956, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void DownloadbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DownloadbtnActionPerformed
        // TODO add your handling code here:
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("HT");

        job.setPrintable(new Printable() {
            public int print(Graphics pg, PageFormat pf, int pageNum) {
                pf.setOrientation(PageFormat.LANDSCAPE);

                Paper paper = new Paper();
                double margin = 10;
                paper.setImageableArea(margin, margin, paper.getWidth() - margin * 3, paper.getHeight() - margin * 3);
                pf.setPaper(paper);

                if (pageNum > 0) {
                    return Printable.NO_SUCH_PAGE;
                }
                Graphics2D g2 = (Graphics2D) pg;
                g2.translate(pf.getImageableX(), pf.getImageableY());
                g2.scale(0.6, 0.6);
                toprint.print(g2);
                return Printable.PAGE_EXISTS;
            }
        });
        boolean ok = job.printDialog();
        if (ok) {
            try {

                job.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }


    }//GEN-LAST:event_DownloadbtnActionPerformed

    private void showbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showbtnActionPerformed
        // TODO add your handling code here:
        toprint.setVisible(true);


    }//GEN-LAST:event_showbtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel Academicyear;
    public static javax.swing.JLabel Category;
    public static javax.swing.JLabel DeptName;
    private javax.swing.JButton Downloadbtn;
    private javax.swing.JLabel EnrollmentId;
    public static javax.swing.JLabel Examseatno;
    public static javax.swing.JLabel Gender;
    public static javax.swing.JLabel StudImg;
    public static javax.swing.JLabel StudYear;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton showbtn;
    private javax.swing.JTable subjectListTable;
    private javax.swing.JPanel toprint;
    private javax.swing.JLabel witlogo;
    // End of variables declaration//GEN-END:variables
}
