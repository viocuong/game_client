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
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
/**
 *
 * @author cuongnv
 */
public class QuestionsForm extends javax.swing.JFrame{
    /**
     * Creates new form QuestionsForm
     */
    private int m,n;
    private int width;
    private JLabel btnNext;
    private int height;
    private int nextX = 304, nextY =662;
    private int[] myAns = new int[10];
    private ArrayList<Question> questions;
    private Tick tick;
    private JLabel[] btnAns = new JLabel[4];
    private JPanel panelNext = new JPanel();
    private int cur;
    public QuestionsForm() {
        initComponents();
        initCustom();
        setLocationRelativeTo(null);
    }
    public void initCustom() {
        
        btnNext = new JLabel();
        btnNext.setFont(new java.awt.Font("Monospaced", 3, 26)); // NOI18N
        btnNext.setForeground(new java.awt.Color(254, 254, 254));
        //btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Res/icons8-next.png"))); // NOI18N
        btnNext.setText("Câu tiếp");
        btnNext.setLocation(nextX-35, nextY-10);
        panelNext.setLocation(nextX-35, nextY-10);
        panelNext.setSize(230,103);
        panelNext.setBackground(new Color(0,0,0,0));
        panelNext.setPreferredSize(new Dimension(230,103));
        panelNext.add(new customPanelNext(230,103));
        btnNext.setSize(230,103 );
        btnNext.setPreferredSize(new Dimension(230,103));
        btnNext.setHorizontalAlignment(JLabel.CENTER);
        btnNext.setVerticalAlignment(JLabel.CENTER);
        btnNext.setHorizontalTextPosition(JLabel.CENTER);
        
        
        
        panelMain.add(panelNext,0,0);
        panelMain.add(btnNext,1,0);
        btnNext.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnNext.setLocation(nextX-35, nextY);
        tick = new Tick("tick.png",panelListAns.getWidth(),panelListAns.getHeight()-20);
        tick.setPreferredSize(new Dimension(panelListAns.getWidth(),panelListAns.getHeight()-20));
        tick.setSize(panelListAns.getWidth(),panelListAns.getHeight()-20);
        this.width = getWidth();
        this.height = getHeight();
        //btnNext.setBackground(new Color(255, 178, 0));
        //btnNext.setBorder(null);
        //btnNext.addMouseListener(new ListentBtnNext());
        panelListAns.setBackground(new Color(0, 0, 0, 0));
        int w = panelListAns.getWidth(), h=panelListAns.getHeight(); 
        this.tick = new Tick("tick.png",w,h/4 -20);
        JPanel[] backgroundP = new JPanel[4];
        for(int idx = 0; idx <4; idx++){
            backgroundP[idx] = new JPanel();
            btnAns[idx] = new JLabel();
            //int w = panelListAns.getWidth(), h=panelListAns.getHeight();
            //int num = this.cur;
            int locationH = 0;
            int vertical =h/4;
            Font font = new Font(Font.MONOSPACED, Font.BOLD,30);
            
            btnAns[idx].setForeground(new Color(254,254,254));
            btnAns[idx].setName(String.valueOf(idx));
            btnAns[idx].setBackground(new Color(45, 64, 89));
            btnAns[idx].setSize(w,vertical-20);
            backgroundP[idx].setSize(w,vertical-20);
            backgroundP[idx].add(new componentLabel(w,h));
            //btnAns[idx].add(new componentLabel(w,h));
            
            btnAns[idx].setFont(font);
            //ans.setText(s);
            //ans.setPreferredSize(new Dimension(w-1,vertical-20));
            btnAns[idx].addMouseListener(new ListentClickAns(idx));
            //btnAns[idx].setBackground(Color.red);
        }
        int locationH = 0;
        int vertical =h/4;
        for(int idx =0 ;idx < 4; idx++){
            backgroundP[idx].setLocation(0,locationH);
            btnAns[idx].setLocation(0, locationH);
            locationH+=(vertical);
            backgroundP[idx].setBackground(new Color(0,0,0,0));
            panelListAns.add(backgroundP[idx],1,0);
            panelListAns.add(btnAns[idx],2,0);
        }
        System.out.println(panelListAns.getWidth()+" "+panelListAns.getHeight());
        //panelListAns.add(new PanelAnsCustom(width,height));
    }
    
//    @Override
//    public void actionPerformed(ActionEvent ae) {
//        cur++;
//        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        if(cur<10) showQuestion(this.questions);
//        else submitAns();
//    }
    public void submitAns(){
        
    }
    public void addActionListentBtnNext(MouseListener listener){
        this.btnNext.addMouseListener(listener);
    }
    class customPanelNext extends JComponent{
        private int w,h;
    
        public customPanelNext(int w, int h){
            this.w = w;
            this.h = h;
            this.setSize(w,h);
            this.setPreferredSize(new Dimension(w,h));
        }
        public void paint(Graphics g){
            Graphics2D g2d= (Graphics2D) g;
            Image img = new ImageIcon(getClass().getResource("/Res/icon_next_blue.png")).getImage();
            g2d.drawImage(img,0,0, null);
        }
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

    class componentLabel extends JComponent{
        private int w;
        private int h;
        
        public componentLabel(int w, int h){
            this.w= w;
            this.h = h;
            
            this.setPreferredSize(new Dimension(w,h));
            this.setSize(w,h);
        }
        public void paint(Graphics g){
            Graphics2D g2d= (Graphics2D) g;
            g2d.setColor(new Color(89, 91, 131,150));
//            g2d.fillRoundRect(0, 0, w, h, 30, 30);
            Image img= new ImageIcon(getClass().getResource("/Res/iconAns.png")).getImage();
            g2d.drawImage(img, 0,0, this);
            Font font = new Font(Font.SANS_SERIF, Font.BOLD,30);
        }
    }
    class Tick extends JComponent{
        private Image image;
        private int w,h;
        public Tick(String img, int w, int h){
            image = new ImageIcon(getClass().getResource("/Res/"+img)).getImage();
            this.w = w;
            this.h = h;
            this.setSize(w,h);
            this.setPreferredSize(new Dimension(w,h));
            
        }
        public void paint(Graphics g){
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(image,20,20,this);
        }
    }
    
    
    public void resetTick(int i){
        for(int idx = 0; idx <4; idx++){
            
            if(idx != i){
                this.btnAns[idx].remove(this.tick);
                this.btnAns[idx].revalidate();
                this.btnAns[idx].repaint();
            }
            
        }
    }
    public void showQuestionI(Question q, int cur){
        //resetJpanel();
        //resetJpanel();
        this.cur = cur;
        question.setText(q.getQuestion());
        ArrayList<String> listAns = q.getListAns();
       
        for(int idx = 0; idx <4; idx++){
            btnAns[idx].setHorizontalAlignment(JLabel.CENTER);
            btnAns[idx].setVerticalAlignment(JLabel.CENTER);
            btnAns[idx].setHorizontalTextPosition(JLabel.CENTER);
            btnAns[idx].setText(listAns.get(idx));
            btnAns[idx].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btnAns[idx].revalidate();
            btnAns[idx].repaint();
            //btnAns[idx].setBackground(Color.red);
        }
        
//        panelListAns.revalidate();
//        panelListAns.repaint();
        
        
    }
    public void resetAllTick(){
        for(int i =0; i<4;i++){
            btnAns[i].remove(this.tick);
            btnAns[i].revalidate();
            btnAns[i].repaint();
        }
    }
    
    class ListentClickAns implements MouseListener{
        private int i;
                
        public ListentClickAns(int i){
            this.i = i;
        }
        @Override
        public void mouseClicked(MouseEvent me) {
           myAns[cur] = i+1;
           btnAns[i].add(tick,2,0);
           btnAns[i].revalidate();
           btnAns[i].repaint();
           
            resetTick(i);
            JOptionPane.showMessageDialog(panelMain,myAns[cur]);
        }
        @Override
        public void mousePressed(MouseEvent me) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseReleased(MouseEvent me) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent me) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent me) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        
        
    }
//    public void showQuestion(ArrayList<Question> questions) {
//        
//        this.questions = questions;
//        if(cur <10){
//            showQuestionI(questions.get(this.cur));
//        }
//    }
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
        question = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

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

        question.setFont(new java.awt.Font("TlwgMono", 3, 26)); // NOI18N
        question.setForeground(new java.awt.Color(254, 242, 242));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Res/question.png"))); // NOI18N

        javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
        panelMain.setLayout(panelMainLayout);
        panelMainLayout.setHorizontalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(panelListAns, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(35, 35, 35))
            .addGroup(panelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(question, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addComponent(panelTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(question, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33)
                .addComponent(panelListAns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(93, Short.MAX_VALUE))
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
    private javax.swing.JLabel iconAlarm;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel panelListAns;
    private javax.swing.JPanel panelMain;
    private javax.swing.JPanel panelTop;
    private javax.swing.JLabel question;
    // End of variables declaration//GEN-END:variables
}
