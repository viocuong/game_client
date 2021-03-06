/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view;

import Models.com.Pair;
import Models.com.User;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author cuongnv
 */
public class Game extends javax.swing.JFrame {
    /**
     * Creates new form Game
     */
    private ActionListener al;
    private ArrayList<JButton> listBtnPlayer;
    public Game() {
        initComponents();
        this.setVisible(true);
        initComponentCustom();
        setLocationRelativeTo(null);
    }
    public void initComponentCustom(){
        btnClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnMinimize.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jPanelListPlayer.setBackground(new Color(52, 30, 69,90));
        GridLayout gl = new GridLayout(8,1);
        gl.setHgap(20);
        gl.setVgap(20);
        jPanelListPlayer.setLayout(gl);
        jPanelListPlayer.setSize(430,500);
        jPanelListPlayer.setPreferredSize(new Dimension(430,500));
        jPanelGame.setSize(new Dimension(1209, 697));
        //jPanelInfoAccount.setBackground(new Color(52, 30, 69,50));
    }
    public void initJpanelPlayers(){
        int w = jPanelGame.getWidth(), h =jPanelGame.getHeight();
        //jPanelListPlayer = new JPanel();
        jPanelListPlayer.setBounds(w/3, 50, w/3,600 );
        jPanelListPlayer.setBackground(new Color(52, 30, 69,90));
        GridLayout gl = new GridLayout(8,1);
        gl.setHgap(20);
        gl.setVgap(20);
        jPanelListPlayer.setLayout(gl);
        jPanelListPlayer.setSize(430,500);
        jPanelListPlayer.setPreferredSize(new Dimension(430,500));
    }
    public void addListenBtnRank(MouseListener m){
        btnRank.addMouseListener(m);
    }
    class componentBtnPlayer extends JComponent{
        private int status;
        private int width;
        private int height;
        private float score;
        public componentBtnPlayer(int status, int width, int height, float score){
            this.status = status;
            this.width = width;
            this.height = height;
            this.score = score;
        }
        public void paint(Graphics g){
            //super.paint(g);
            Color fontColor = new Color(242, 242, 242);
            Font font  = new Font(Font.SANS_SERIF,Font.LAYOUT_LEFT_TO_RIGHT,20);
            Graphics2D g2d = (Graphics2D) g;
//            g2d.setColor(new Color(52, 30, 69));
//            g2d.fillRoundRect(0, 0, width, height, 20, 20);
            
            Color colorStatus = null;
            if(status == 1) colorStatus = Color.GREEN;
            else if(status ==2) colorStatus = Color.orange;
            else colorStatus = Color.red;
            g2d.setColor(colorStatus);
            g2d.fillOval(width-60, 10, 20, 20);
            Image img = new ImageIcon(getClass().getResource("/Res/icons8-change-user-30.png")).getImage();
            g2d.drawImage(img,20 ,8 , null);
//            g2d.setFont(font);
//            g2d.drawString("hello", 30, 20);
            g2d.setColor(new Color(255, 255, 51));
            Font f = new Font(Font.SANS_SERIF,Font.CENTER_BASELINE,14);
            g2d.drawString(String.valueOf(this.score),20, 55);
        }
    }
    public void showMyAccount(User user){
        this.labelAccount.setText(user.getUserName());
        //JOptionPane.showMessageDialog(this, user.getScore());
        this.labelScore.setText(String.valueOf(user.getScore()));
    }
    public void setActionListener(ActionListener al){
        this.al = al;
    }
    class PanelImage extends JPanel{
        private Image img;
        public PanelImage(String image){
            img = new ImageIcon(getClass().getResource("/Res/"+image)).getImage();
            Dimension size = new Dimension(img.getWidth(this),img.getHeight(this));
            setPreferredSize(size);
            setMinimumSize(size);
            setMaximumSize(size);
            setLayout(null);
        }
        @Override
        protected void paintComponent(Graphics g){
//            Graphics2D g2d = (Graphics2D) g;
//            int width = this.getWidth();
//            int height = this.getHeight();
//            Color cl1 = new Color(0, 143, 233);
//            Color cl2 = new Color(245, 183, 177);
//            GradientPaint gp = new GradientPaint(0, 0, cl1, 100, height, cl2);
//            g2d.setPaint(gp);
//            g2d.fillRect(0, 0, width, height);
            g.drawImage(img,0, 0, null);
        }
    }
    public void deleteBtnPlayer(){
        for(JButton b:listBtnPlayer ){
            jPanelListPlayer.remove(b);
        }
        listBtnPlayer.clear();
    }
    public void showListPlayer(Map<String, Pair<User, Integer>> listPlayer){
        int w = jPanelListPlayer.getWidth(), h = jPanelListPlayer.getHeight();
        jPanelListPlayer.removeAll();
        jPanelListPlayer.revalidate();
        jPanelListPlayer.repaint();
        initJpanelPlayers();
        Font font  = new Font(Font.SANS_SERIF,Font.LAYOUT_LEFT_TO_RIGHT,20);
        Color btnColor = new Color(52, 30, 69);
        Color fontColor = new Color(242, 242, 242);
        listBtnPlayer = new ArrayList<>();
        int y = 0;
        for(Map.Entry<String ,Pair<User,Integer>>  player: listPlayer.entrySet()){
            JButton btnPlayer = new JButton();
            btnPlayer.setForeground(fontColor);
            btnPlayer.setSize(new Dimension(w,60));
            btnPlayer.setLocation(0, y);
            btnPlayer.setBounds(0, y, w, 60);
            componentBtnPlayer comp = new componentBtnPlayer(player.getValue().getValue(), w, h, player.getValue().getKey().getScore());
            comp.setSize(new Dimension(w, 60));
            btnPlayer.setPreferredSize(new Dimension(w, 60));
            comp.setPreferredSize(new Dimension(w, 60));
            btnPlayer.add(comp);
            btnPlayer.setFont(font);
            btnPlayer.setBorder(null);
            btnPlayer.setBackground(btnColor);
            btnPlayer.addMouseListener(new MouseAdapter(){
                public void mouseEntered(MouseEvent e){
                    btnPlayer.setBackground(new Color(80, 47, 106));
                }
                public void mouseExited(MouseEvent e){
                    btnPlayer.setBackground(new Color(52, 30, 69));
                }
            });
            btnPlayer.setName((String.valueOf(player.getValue().getValue())));
            btnPlayer.setText(player.getValue().getKey().getUserName()+" ip:"+player.getKey());
            btnPlayer.addActionListener(al);
            
            listBtnPlayer.add(btnPlayer);
            
            jPanelListPlayer.add(btnPlayer);
            y+=65;
        }
        //JOptionPane.showMessageDialog(this,listBtnPlayer.size());
    }
    public void addListentBtnPlayer(ActionListener l){
        for(JButton btn : listBtnPlayer){
            //JOptionPane.showMessageDialog(this, btn.getText());
            btn.addActionListener(l);
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

        jPanelGame = new PanelImage("background_game_small.png");
        btnClose = new javax.swing.JLabel();
        btnMinimize = new javax.swing.JLabel();
        jPanelListPlayer = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        labelAccount = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        labelScore = new javax.swing.JLabel();
        labelRanking = new javax.swing.JLabel();
        labelRank = new javax.swing.JLabel();
        btnRank = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanelGame.setPreferredSize(new java.awt.Dimension(1209, 697));

        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Res/icons8-close.png"))); // NOI18N
        btnClose.setText("    ");
        btnClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCloseMouseClicked(evt);
            }
        });

        btnMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Res/icons8-minimize.png"))); // NOI18N
        btnMinimize.setText("    ");
        btnMinimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMinimizeMouseClicked(evt);
            }
        });
        btnMinimize.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnMinimizeKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanelListPlayerLayout = new javax.swing.GroupLayout(jPanelListPlayer);
        jPanelListPlayer.setLayout(jPanelListPlayerLayout);
        jPanelListPlayerLayout.setHorizontalGroup(
            jPanelListPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 430, Short.MAX_VALUE)
        );
        jPanelListPlayerLayout.setVerticalGroup(
            jPanelListPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(254, 254, 254));
        jLabel1.setText("Những người chơi khác");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Res/icons8-persion.png"))); // NOI18N
        jLabel2.setText("   ");

        labelAccount.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        labelAccount.setForeground(new java.awt.Color(254, 254, 254));
        labelAccount.setText("Account");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Res/icons8-scoreboard-48.png"))); // NOI18N
        jLabel4.setText("    ");

        labelScore.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        labelScore.setForeground(new java.awt.Color(254, 254, 254));

        labelRanking.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Res/icons8-rank.png"))); // NOI18N

        labelRank.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        labelRank.setForeground(new java.awt.Color(254, 254, 254));

        btnRank.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnRank.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Res/cup.png"))); // NOI18N
        btnRank.setText("    ");

        javax.swing.GroupLayout jPanelGameLayout = new javax.swing.GroupLayout(jPanelGame);
        jPanelGame.setLayout(jPanelGameLayout);
        jPanelGameLayout.setHorizontalGroup(
            jPanelGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGameLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelGameLayout.createSequentialGroup()
                        .addGroup(jPanelGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelAccount, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                            .addComponent(labelScore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanelGameLayout.createSequentialGroup()
                        .addComponent(labelRanking)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelRank, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnRank, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanelGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelGameLayout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addComponent(jPanelListPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 305, Short.MAX_VALUE))
                    .addGroup(jPanelGameLayout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(jLabel1)
                        .addGap(47, 289, Short.MAX_VALUE)))
                .addComponent(btnMinimize, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelGameLayout.setVerticalGroup(
            jPanelGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGameLayout.createSequentialGroup()
                .addGroup(jPanelGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClose)
                    .addComponent(btnMinimize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(646, 646, 646))
            .addGroup(jPanelGameLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanelGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelGameLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanelGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelGameLayout.createSequentialGroup()
                        .addGroup(jPanelGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(labelScore, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanelGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelRanking, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelRank, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(38, 38, 38)
                        .addComponent(btnRank, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanelListPlayer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelGame, javax.swing.GroupLayout.DEFAULT_SIZE, 1206, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelGame, javax.swing.GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMinimizeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnMinimizeKeyPressed
    
    }//GEN-LAST:event_btnMinimizeKeyPressed

    private void btnCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseClicked
        if(JOptionPane.showConfirmDialog(null,"Bạn chắc chăn muốn thoát game hay này?","Yes",JOptionPane.YES_NO_OPTION)==0){
            this.dispose();
        }
    }//GEN-LAST:event_btnCloseMouseClicked

    private void btnMinimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeMouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_btnMinimizeMouseClicked

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
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Game().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnClose;
    private javax.swing.JLabel btnMinimize;
    private javax.swing.JLabel btnRank;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanelGame;
    private javax.swing.JPanel jPanelListPlayer;
    private javax.swing.JLabel labelAccount;
    private javax.swing.JLabel labelRank;
    private javax.swing.JLabel labelRanking;
    private javax.swing.JLabel labelScore;
    // End of variables declaration//GEN-END:variables
}
