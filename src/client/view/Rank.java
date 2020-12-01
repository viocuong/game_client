/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view;

import Models.com.Pair;
import Models.com.User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

/**
 *
 * @author cuongnv
 */
public class Rank extends javax.swing.JFrame implements MouseListener {

    /**
     * Creates new form Ranking
     */
    private int p = 0, n = 0, idx = 1;
    private JPanel[] panelr = new JPanel[5];
    private JLabel[] ranki = new JLabel[5];
    private JLabel[] th = new JLabel[5];
    private ArrayList<Pair<String, String>> datas;
    private boolean isNext = true, isPrivious = false;

    public Rank() {
        initComponents();
        customComponent();
        setLocationRelativeTo(null);
        btnNext.addMouseListener(this);
        btnPrevious.addMouseListener(this);
    }

    public void customComponent() {
        btnCompetitor.setName("competitor");
        btnScore.setName("score");
        btnTimeWin.setName("timewin");
        btnCompetitor.setBackground(new Color(0, 0, 0, 0));
        btnTimeWin.setBackground(new Color(0, 0, 0, 0));
        btnScore.setBackground(new Color(0, 0, 0, 0));
        btnNext.setName("next");
        btnPrevious.setName("previous");
        this.setBackground(new Color(0, 0, 0, 0));
        panelMain.add(new panelCustom("leaderboard.png"));
        panelMain.setBackground(new Color(0, 0, 0, 0));

        btnCompetitor.setBorder(null);
        btnCompetitor.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnCompetitor.setBackground(new Color(0, 0, 0, 0));
                btnCompetitor.setBorder(null);
            }

            public void mouseExited(MouseEvent e) {
                btnCompetitor.setBackground(new Color(0, 0, 0, 0));
                btnCompetitor.setBorder(null);
            }
        });
        btnScore.setBorder(null);
        btnScore.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnScore.setBackground(new Color(0, 0, 0, 0));
                btnScore.setBorder(null);
            }

            public void mouseExited(MouseEvent e) {
                btnScore.setBackground(new Color(0, 0, 0, 0));
                btnScore.setBorder(null);
            }
        });
        btnTimeWin.setBorder(null);
        btnTimeWin.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnTimeWin.setBackground(new Color(0, 0, 0, 0));
                btnTimeWin.setBorder(null);
            }

            public void mouseExited(MouseEvent e) {
                btnTimeWin.setBackground(new Color(0, 0, 0, 0));
                btnTimeWin.setBorder(null);
            }

        });
        initPanel();
        //add(panelScroll);

    }

    public void addListenBtn(ActionListener l) {
        btnCompetitor.addActionListener(l);
        btnTimeWin.addActionListener(l);
        btnScore.addActionListener(l);
    }

    public void initPanel() {
        Font font = new Font(Font.SANS_SERIF, Font.ITALIC, 20);
        Color fontColor = new Color(254, 254, 254);

        int y = 80;
        for (int i = 0; i < 5; i++) {
            panelr[i] = new JPanel();
            panelr[i].setSize(410, 55);
            panelr[i].setPreferredSize(new Dimension(410, 55));
            panelr[i].setLocation(102, y);
            panelr[i].add(new customPanelRank("rankth.png", "1", "", ""));
            panelr[i].setBackground(new Color(0, 0, 0, 0));

            panelMain.add(panelr[i], 3, 0);

            y += 55;
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        JLabel btn = (JLabel) me.getComponent();
        if (btn.getName().equals("next")) {
            if (isNext) {
                isPrivious = true;
                idx = n;
                p = n;
                int tmp = n;
                int kt = 0;
                if (n + 5 > datas.size()) {
                    n = datas.size();
                    kt = 1;
                    isNext = false;
                } else {
                    n += 5;
                }
                showDetailRank(p, n);
                if (kt == 1) {
                    p -= 5;
                    n = tmp;
                }
            }

        } else {
            if(isPrivious){
                
                isNext = true;
                if (p >= 5 && n >= 5) {
                    n = p;
                    p -= 5;
                    idx -= 4;
                    
                }
                if(p>=0 && n>=0){
                    if(p == 0) idx = 0;
                    showDetailRank(p, n);
                }
                
                
                    
                
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    class customPanelRank extends JComponent {

        private int w, h;
        private String s1, s2, s3;
        private Image image;

        public customPanelRank(String src, String s1, String s2, String s3) {
            image = new ImageIcon(getClass().getResource("/Res/" + src)).getImage();
            w = image.getWidth(this);
            h = image.getHeight(this);
            this.setSize(w, h);

            this.setPreferredSize(new Dimension(w, h));
            this.s1 = s1;
            this.s2 = s2;
            this.s3 = s3;
        }

        public void paint(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(this.image, 0, 0, null);
            Font font = new Font(Font.SANS_SERIF, Font.ITALIC, 20);
            Color fontColor = new Color(254, 254, 254);
            g2d.setColor(fontColor);
            g2d.setFont(font);
            g2d.drawString(s1, 25, 40);
            g2d.drawString(s2, 150, 40);
            g2d.drawString(s3, 300, 40);
        }
    }

    class panelCustom extends JComponent {

        private int w, h;
        private Image image;

        public panelCustom(String src) {
            image = new ImageIcon(getClass().getResource("/Res/" + src)).getImage();
            w = image.getWidth(this);
            h = image.getHeight(this);
            this.setSize(w, h);
            this.setPreferredSize(new Dimension(w, h));
        }

        public void paint(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(this.image, 0, 0, null);
        }
    }

    public void show(ArrayList<Pair<String, String>> datas) {
        this.datas = datas;
        isNext = true;
        isPrivious = false;
        idx = 1;
        p = 0;

        n = 5;

        int y = 80, init = 0;
        int page = datas.size() / 5;
        for (int i = 0; i < n; i++) {
            if (i >= datas.size()) {
                break;
            }
            //panelr[i] = new JPanel();
            //panelr[i].setSize(410,55);
            //panelr[i].setPreferredSize(new Dimension(410,55));
            //panelr[i].setLocation(102,y);
            panelr[i].removeAll();
            panelr[i].revalidate();
            panelr[i].repaint();
            panelr[i].add(new customPanelRank("rankth.png", String.valueOf(idx), datas.get(i).getKey(), datas.get(i).getValue()));
            panelr[i].revalidate();
            panelr[i].repaint();
            y += 55;

            idx++;
            if (i == 4) {
                y = 80;
            }
        }
        p = n;
    }

    public void reset() {
        for (int i = 0; i < 5; i++) {

            panelr[i].removeAll();
            panelr[i].revalidate();
            panelr[i].repaint();
        }
    }

    public void showDetailRank(int l, int r) {
        int y = 80;
        int h = 0;
        reset();
        for (int i = l; i < r; i++) {
            if (i >= datas.size() || i <0) {
                break;
            }
            System.out.println(datas.get(i).getKey());
            panelr[h].add(new customPanelRank("rankth.png", String.valueOf(idx + 1), datas.get(i).getKey(), datas.get(i).getValue()));
            panelr[h].revalidate();
            panelr[h].repaint();
            y += 55;
            h++;
            idx++;
        }
        
        if(r< l+5){
           
            for(int i = r; i<l+5;i++){
                panelr[h].add(new customPanelRank("rankth.png", "", "", ""));
                panelr[h].revalidate();
                panelr[h].repaint();
                h++;
                y+=55;
                
            }
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

        panelMain = new javax.swing.JPanel();
        labelTitle = new javax.swing.JLabel();
        btnPrevious = new javax.swing.JLabel();
        btnNext = new javax.swing.JLabel();
        btnScore = new javax.swing.JButton();
        btnTimeWin = new javax.swing.JButton();
        btnCompetitor = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        labelTitle.setFont(new java.awt.Font("Monospaced", 3, 36)); // NOI18N
        labelTitle.setForeground(new java.awt.Color(254, 254, 254));
        labelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitle.setText("Xếp Hạng");

        btnPrevious.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Res/previous.png"))); // NOI18N
        btnPrevious.setText("   ");

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Res/next.png"))); // NOI18N
        btnNext.setText("    ");

        btnScore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Res/rank_score.png"))); // NOI18N

        btnTimeWin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Res/timewin.png"))); // NOI18N

        btnCompetitor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Res/competitor.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("X");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
        panelMain.setLayout(panelMainLayout);
        panelMainLayout.setHorizontalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(btnScore, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addComponent(labelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(153, 153, 153))
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createSequentialGroup()
                                .addComponent(btnTimeWin)
                                .addGap(5, 5, 5))
                            .addGroup(panelMainLayout.createSequentialGroup()
                                .addComponent(btnPrevious)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 136, Short.MAX_VALUE)))
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCompetitor, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(1, 1, 1)
                .addComponent(labelTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 294, Short.MAX_VALUE)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createSequentialGroup()
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnScore, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnTimeWin, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnCompetitor, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createSequentialGroup()
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnPrevious)
                            .addComponent(btnNext))
                        .addGap(79, 79, 79))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

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
            java.util.logging.Logger.getLogger(Rank.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Rank.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Rank.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Rank.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Rank().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCompetitor;
    private javax.swing.JLabel btnNext;
    private javax.swing.JLabel btnPrevious;
    private javax.swing.JButton btnScore;
    private javax.swing.JButton btnTimeWin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JPanel panelMain;
    // End of variables declaration//GEN-END:variables
}
