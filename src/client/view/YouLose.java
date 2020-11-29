/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author cuongnv
 */
public class YouLose extends javax.swing.JFrame {

    /**
     * Creates new form YouLose
     */
    private int width;
    private int height;
    private JPanel panelLose;
    public YouLose() {
        initComponents();
        initCustom();
        setLocationRelativeTo(null);
    }
    public void initCustom(){
        this.setBackground(new Color(111,111,111,0));
        width = getWidth();
        height = getHeight();
        panelLose = new JPanel();
        Font font  = new Font(Font.SANS_SERIF,Font.BOLD, 28);
        Color fontColor = new Color(254,254,254);
        panelLose.setSize(451 ,450);
        panelLose.setPreferredSize(new Dimension(451,450));
        panelLose.add(new customPanel("youlose.png"));
        panelLose.setBackground(new Color(20,111,112,0));
        panelLose.setLayout(null);
        panelMain.add(panelLose);
        panelMain.setBackground(new Color(0,0,0,0));
        panelBtnRematch.setBackground(new Color(0,0,0,0));
        panelBtnExit.setBackground(new Color(0,0,0,0));
        btnExit.setForeground(fontColor);
        btnReMatch.setForeground(fontColor);
        btnExit.setFont(font);
        btnReMatch.setFont(font);
        btnExit.setHorizontalAlignment(JLabel.CENTER);
        btnReMatch.setHorizontalAlignment(JLabel.CENTER);
        
        btnExit.setName("btnEitResult");
        btnReMatch.setName("btnReMatch");
        btnExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnReMatch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        btnExit.setText("Thoát");
        btnReMatch.setText("Đấu lại");
        panelBtnRematch.add(new customPanel("btn_rematch.png"));
        panelBtnExit.add(new customPanel("btn_rematch.png"));
        
    }
    class customPanel extends JComponent{
        private int w;
        private int h;
        private Image image;
        public customPanel(String img){
            image = new ImageIcon(getClass().getResource("/Res/"+img)).getImage();
            w = image.getWidth(this);
            h = image.getHeight(this);
            this.setSize(w,h);
            this.setPreferredSize(new Dimension(w,h));
        }
        public void paint(Graphics g){
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(this.image,0, 0, this);
        }
    }
    public void addListenBtnExitAndRematch(MouseListener m){
        btnExit.addMouseListener(m);
        btnReMatch.addMouseListener(m);
    }
    public void setDetail(String s){
        this.labelDetail.setText(s);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMain = new javax.swing.JPanel();
        panelBtnRematch = new javax.swing.JPanel();
        btnReMatch = new javax.swing.JLabel();
        panelBtnExit = new javax.swing.JPanel();
        btnExit = new javax.swing.JLabel();
        labelDetail = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(451, 500));
        setResizable(false);

        panelMain.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panelMainMouseDragged(evt);
            }
        });
        panelMain.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelMainMousePressed(evt);
            }
        });

        panelBtnRematch.setPreferredSize(new java.awt.Dimension(200, 77));

        javax.swing.GroupLayout panelBtnRematchLayout = new javax.swing.GroupLayout(panelBtnRematch);
        panelBtnRematch.setLayout(panelBtnRematchLayout);
        panelBtnRematchLayout.setHorizontalGroup(
            panelBtnRematchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnReMatch, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
        panelBtnRematchLayout.setVerticalGroup(
            panelBtnRematchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnReMatch, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
        );

        panelBtnExit.setPreferredSize(new java.awt.Dimension(200, 77));

        javax.swing.GroupLayout panelBtnExitLayout = new javax.swing.GroupLayout(panelBtnExit);
        panelBtnExit.setLayout(panelBtnExitLayout);
        panelBtnExitLayout.setHorizontalGroup(
            panelBtnExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnExit, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
        panelBtnExitLayout.setVerticalGroup(
            panelBtnExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnExit, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
        );

        labelDetail.setFont(new java.awt.Font("Sarai", 1, 36)); // NOI18N
        labelDetail.setForeground(new java.awt.Color(254, 0, 62));
        labelDetail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
        panelMain.setLayout(panelMainLayout);
        panelMainLayout.setHorizontalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBtnRematch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(panelBtnExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(130, 130, 130))
        );
        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createSequentialGroup()
                .addContainerGap(331, Short.MAX_VALUE)
                .addComponent(labelDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBtnRematch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelBtnExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMain, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panelMainMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMainMousePressed
        m=evt.getX();
        n=evt.getY();
    }//GEN-LAST:event_panelMainMousePressed

    private void panelMainMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMainMouseDragged
        int x=evt.getXOnScreen();
        int y=evt.getYOnScreen();
        this.setLocation(x-m, y-n);
    }//GEN-LAST:event_panelMainMouseDragged
   private int m,n;
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
            java.util.logging.Logger.getLogger(YouLose.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(YouLose.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(YouLose.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(YouLose.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new YouLose().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnExit;
    private javax.swing.JLabel btnReMatch;
    private javax.swing.JLabel labelDetail;
    private javax.swing.JPanel panelBtnExit;
    private javax.swing.JPanel panelBtnRematch;
    private javax.swing.JPanel panelMain;
    // End of variables declaration//GEN-END:variables
}
