/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI_Dictionary;

import java.awt.event.KeyEvent;

/**
 *
 * @author Quan
 */
public class MainFrame extends javax.swing.JFrame {

    private FindEnglishWord findEnglishWordFrame;
    private FindVietWord findVietWordFrame;
    private OnlineSearch onlineSearchFrame;

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        findEnglishWordFrame = new FindEnglishWord();
        findEnglishWordFrame.setMainFrame(this);
        findVietWordFrame = new FindVietWord();
        findVietWordFrame.setMainFrame(this);
        onlineSearchFrame = new OnlineSearch();
        onlineSearchFrame.setMainFrame(this);
        initComponents();
    }

    public void setFindEnglishWordFrame(FindEnglishWord findEnglishWordFrame) {
        this.findEnglishWordFrame = findEnglishWordFrame;
    }

    public FindEnglishWord getFindEnglishWordFrame() {
        return findEnglishWordFrame;
    }

    public void setFindVietWordFrame(FindVietWord findVietWordFrame) {
        this.findVietWordFrame = findVietWordFrame;
    }

    public FindVietWord getFindVietWordFrame() {
        return findVietWordFrame;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Name = new javax.swing.JLabel();
        openSearchEngWord = new javax.swing.JButton();
        openSearchVietWord = new javax.swing.JButton();
        openInfor = new javax.swing.JButton();
        openOnlineSearch = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));

        Name.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        Name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Name.setText("TỪ ĐIỂN TIẾNG ANH");

        openSearchEngWord.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        openSearchEngWord.setText("Tra tiếng Anh");
        openSearchEngWord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openSearchEngWordActionPerformed(evt);
            }
        });

        openSearchVietWord.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        openSearchVietWord.setText("Tra tiếng Việt");
        openSearchVietWord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openSearchVietWordActionPerformed(evt);
            }
        });

        openInfor.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        openInfor.setText("Thông tin");
        openInfor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openInforActionPerformed(evt);
            }
        });

        openOnlineSearch.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        openOnlineSearch.setText("Dịch Online");
        openOnlineSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openOnlineSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(30, Short.MAX_VALUE)
                        .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, 753, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(openSearchEngWord, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(openSearchVietWord, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(openInfor, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(openOnlineSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(openSearchEngWord, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(openSearchVietWord, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(openOnlineSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(openInfor, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void openSearchEngWordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openSearchEngWordActionPerformed
        findEnglishWordFrame.setVisible(true);
        findEnglishWordFrame.setLocation(this.getLocation());
        this.setVisible(false);
    }//GEN-LAST:event_openSearchEngWordActionPerformed

    private void openSearchVietWordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openSearchVietWordActionPerformed
        findVietWordFrame.setVisible(true);
        findVietWordFrame.setLocation(this.getLocation());
        this.setVisible(false);
    }//GEN-LAST:event_openSearchVietWordActionPerformed

    private void openInforActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openInforActionPerformed

    }//GEN-LAST:event_openInforActionPerformed

    private void openOnlineSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openOnlineSearchActionPerformed
        onlineSearchFrame.setVisible(true);
        onlineSearchFrame.setLocation(this.getLocation());
        this.setVisible(false);
    }//GEN-LAST:event_openOnlineSearchActionPerformed

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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    public void Init() {
        setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Name;
    private javax.swing.JButton openInfor;
    private javax.swing.JButton openOnlineSearch;
    private javax.swing.JButton openSearchEngWord;
    private javax.swing.JButton openSearchVietWord;
    // End of variables declaration//GEN-END:variables
}
