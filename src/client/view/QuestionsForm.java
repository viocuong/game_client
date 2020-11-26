/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Color;
import java.util.ArrayList;
import Models.com.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;

/**
 *
 * @author cuongnv
 */
public class QuestionsForm extends javax.swing.JFrame implements ActionListener{

    /**
     * Creates new form QuestionsForm
     */
    private int m,n;
    private int cur =0;
    private int width;
    private int height;
    private int[] myAns = new int[10];
    private ArrayList<Question> questions;
    private Tick tick;

    public QuestionsForm() {
        initComponents();
        initCustom();
        setLocationRelativeTo(null);
    }

    public void initCustom() {
        tick = new Tick();
        this.width = getWidth();
        this.height = getHeight();
        btnNext.setBackground(new Color(255, 178, 0));
        btnNext.setBorder(null);
        btnNext.addActionListener(this);
        panelListAns.setBackground(new Color(0, 0, 0, 0));
        
        //panelListAns.add(new PanelAnsCustom(width,height));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        cur++;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(cur<10) showQuestion(this.questions);
        else submitAns();
    }
    public void submitAns(){
        
    }
    class PanelCustom extends JPanel {

        private String img;
        private Image image;
        private int width, height;

        public PanelCustom(String img) {
            this.img = img;
            image = new ImageIcon(getClass().getResource("/Res/" + img)).getImage();
            this.width = image.getWidth(this);
            this.height = image.getHeight(this);
            Dimension size = new Dimension(this.width, this.height);
            this.setPreferredSize(size);
            setMinimumSize(size);
            setMaximumSize(size);
            this.setLayout(null);
        }
        protected void paintComponent(Graphics g) {
            //Graphics2D g2d = (Graphics2D) g;
            g.drawImage(image, 0, 0, this);
        }
    }
//    class componentBtnAns extends JComponent{
//        private int width;
//        private int height;
//        public componentBtnAns(int width, int height){
//            this.width = width;
//            this.height = height;
//        }
//        public void paint(Graphics g){
//            //super.paint(g);
//            Color fontColor = new Color(242, 242, 242);
//            //Font font  = new Font(Font.SANS_SERIF,Font.LAYOUT_LEFT_TO_RIGHT,28);
//            Graphics2D g2d = (Graphics2D) g;
////            g2d.setColor(new Color(52, 30, 69));
////            g2d.fillRoundRect(0, 0, width, height, 20, 20);
//            
//            Color colorStatus = null;
//            if(status == 1) colorStatus = Color.GREEN;
//            else if(status ==2) colorStatus = Color.orange;
//            else colorStatus = Color.red;
//            g2d.setColor(colorStatus);
//            g2d.fillOval(width-60, 10, 20, 20);
//            Image img = new ImageIcon(getClass().getResource("/Res/icons8-change-user-30.png")).getImage();
//            g2d.drawImage(img,20 ,8 , null);
////            g2d.setFont(font);
////            g2d.drawString("hello", 30, 20);
//            g2d.setColor(new Color(255, 255, 51));
//            Font f = new Font(Font.SANS_SERIF,Font.CENTER_BASELINE,14);
//            g2d.drawString(String.valueOf(this.score),20, 55);
//        }
//    }
    class Tick extends JComponent{
        private Image image;
        private int w,h;
        public void Tick(String img, int w, int h){
            image = new ImageIcon(getClass().getResource("/Res/"+img)).getImage();
            this.w = w;
            this.h = h;
        }
        public void paint(Graphics g){
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(this.image,20,h/3,null);
            
        }
    }
    class BtnAnsCustom extends JComponent{
        public BtnAnsCustom(){
            
        }
    }
    public JButton createBtn(String s, int i, JButton[] btns){
        int num = this.cur;
        JButton res = new JButton();
        Font font = new Font(Font.SANS_SERIF, Font.BOLD,30);
        res.setForeground(new Color(254,254,254));
        res.setText(s);
        res.setName(String.valueOf(i));
        res.setBorder(null);
        res.setBackground(new Color(45, 64, 89,150));
        res.addActionListener((ae) -> {
            myAns[num]=i;
            resetTick(res, num, i,btns);
        });
        return res;
    }
    public void resetTick(JButton btn, int cur, int i,JButton[] btns){
        for(int idx = 0; idx <4; idx++){
            if(idx == i){
                btns[i].add(this.tick);
            }
            else btns[i].remove(tick);
        }
    }
    public void showQuestionI(Question q){
        int w = panelListAns.getWidth(), h=panelListAns.getHeight();
        panelListAns.removeAll();
        question.setText(q.getQuestion());
        ArrayList<String> listAns = q.getListAns();
        JButton[] btnAns = new JButton[4];
        for(int idx = 0; idx <4; idx++){
            btnAns[idx] = createBtn(listAns.get(idx),idx,btnAns);
        }
        int locationH = 0;
        int vertical =h/4;
        for(int idx =0 ;idx < 4; idx++){
            btnAns[idx].setSize(w,vertical-20);
            btnAns[idx].setPreferredSize(new Dimension(w,vertical-20));
            btnAns[idx].setLocation(0, locationH);
            locationH+=(vertical);
            panelListAns.add(btnAns[idx]);
        }
        
    }
    public void showQuestion(ArrayList<Question> questions) {
        this.questions = questions;
        
        if(cur <10){
            showQuestionI(questions.get(this.cur));
            
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

        panelMain = new PanelCustom("background_question.jpeg");
        panelTop = new javax.swing.JPanel();
        iconAlarm = new javax.swing.JLabel();
        panelListAns = new javax.swing.JPanel();
        btnNext = new javax.swing.JButton();
        question = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        panelTop.setBackground(new Color(245, 162, 93));
        panelTop.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panelTopMouseDragged(evt);
            }
        });
        panelTop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelTopMousePressed(evt);
            }
        });

        iconAlarm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Res/icon_alarm.png"))); // NOI18N

        javax.swing.GroupLayout panelTopLayout = new javax.swing.GroupLayout(panelTop);
        panelTop.setLayout(panelTopLayout);
        panelTopLayout.setHorizontalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTopLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconAlarm, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelTopLayout.setVerticalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTopLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(iconAlarm)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelListAnsLayout = new javax.swing.GroupLayout(panelListAns);
        panelListAns.setLayout(panelListAnsLayout);
        panelListAnsLayout.setHorizontalGroup(
            panelListAnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 504, Short.MAX_VALUE)
        );
        panelListAnsLayout.setVerticalGroup(
            panelListAnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );

        btnNext.setFont(new java.awt.Font("Monospaced", 3, 24)); // NOI18N
        btnNext.setForeground(new java.awt.Color(254, 254, 254));
        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Res/icons8-next.png"))); // NOI18N
        btnNext.setText("Câu tiếp");
        btnNext.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        question.setFont(new java.awt.Font("TlwgMono", 1, 18)); // NOI18N
        question.setForeground(new java.awt.Color(254, 242, 242));

        javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
        panelMain.setLayout(panelMainLayout);
        panelMainLayout.setHorizontalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createSequentialGroup()
                        .addComponent(panelListAns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createSequentialGroup()
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))))
            .addGroup(panelMainLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(question, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addComponent(panelTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(question, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelListAns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panelTopMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelTopMousePressed
        // TODO add your handling code here:
        m=evt.getX();
        n=evt.getY();
    }//GEN-LAST:event_panelTopMousePressed

    private void panelTopMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelTopMouseDragged
        int x=evt.getXOnScreen();
        int y=evt.getYOnScreen();
        this.setLocation(x-m, y-n);
    }//GEN-LAST:event_panelTopMouseDragged

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
            java.util.logging.Logger.getLogger(QuestionsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuestionsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuestionsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuestionsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuestionsForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNext;
    private javax.swing.JLabel iconAlarm;
    private javax.swing.JPanel panelListAns;
    private javax.swing.JPanel panelMain;
    private javax.swing.JPanel panelTop;
    private javax.swing.JLabel question;
    // End of variables declaration//GEN-END:variables
}
