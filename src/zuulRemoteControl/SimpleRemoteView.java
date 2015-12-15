package zuulRemoteControl;

import java.net.URL;
import javax.swing.ImageIcon;
import model.Model;
import view.GameListener;

// This class is used to create a simple remote control view
public class SimpleRemoteView extends javax.swing.JFrame implements GameListener {

    private static final long serialVersionUID = 5L;
    private Model engine;
    private URL roomImage;

    /**
     * Creates new form SimpleRemoteView
     *
     * @param engine
     */
    public SimpleRemoteView(Model engine) {
        this.engine = engine;
        engine.addGameListener(this);
        initComponents();
        this.setVisible(true);
    }

    /**
     * This method is called from constructor to initialize the form. WARNING:
     * Do NOT modify this code. The content of this method is always regenerated
     * by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPain = new javax.swing.JPanel();
        roomLabel = new javax.swing.JLabel();
        northButton = new javax.swing.JButton();
        eastButton = new javax.swing.JButton();
        westButton = new javax.swing.JButton();
        southButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        northButton.setText("North");
        northButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                northButtonActionPerformed(evt);
            }
        });

        eastButton.setText("East");
        eastButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eastButtonActionPerformed(evt);
            }
        });

        westButton.setText("West");
        westButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                westButtonActionPerformed(evt);
            }
        });

        southButton.setText("South");
        southButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                southButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout mainPainLayout = new org.jdesktop.layout.GroupLayout(mainPain);
        mainPain.setLayout(mainPainLayout);
        mainPainLayout.setHorizontalGroup(
            mainPainLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(mainPainLayout.createSequentialGroup()
                .add(westButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(roomLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                .add(18, 18, 18)
                .add(eastButton)
                .add(37, 37, 37))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, mainPainLayout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(mainPainLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, mainPainLayout.createSequentialGroup()
                        .add(southButton)
                        .add(258, 258, 258))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, mainPainLayout.createSequentialGroup()
                        .add(northButton)
                        .add(263, 263, 263))))
        );
        mainPainLayout.setVerticalGroup(
            mainPainLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(mainPainLayout.createSequentialGroup()
                .add(northButton)
                .add(29, 29, 29)
                .add(mainPainLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(roomLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                    .add(westButton)
                    .add(eastButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(southButton))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(mainPain, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(mainPain, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void eastButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eastButtonActionPerformed
        go("east");
}//GEN-LAST:event_eastButtonActionPerformed

    private void southButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_southButtonActionPerformed
        go("south");
    }//GEN-LAST:event_southButtonActionPerformed

    private void northButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_northButtonActionPerformed
        go("north");
    }//GEN-LAST:event_northButtonActionPerformed

    private void westButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_westButtonActionPerformed
        go("west");
    }//GEN-LAST:event_westButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton eastButton;
    private javax.swing.JPanel mainPain;
    private javax.swing.JButton northButton;
    private javax.swing.JLabel roomLabel;
    private javax.swing.JButton southButton;
    private javax.swing.JButton westButton;
    // End of variables declaration//GEN-END:variables

    public void gameStateModified(String roomImageName) {
        roomImage = getClass().getResource(roomImageName);
        if (roomImage == null) {
            System.out.println("image not found");
        } else {
            ImageIcon icon = new ImageIcon(roomImage);
            roomLabel.setIcon(icon);
            this.pack();
        }
    }

    private void go(String direction) {
        engine.interpretCommand("go " + direction);
    }

    public void gameStateModified(String imageName, String mapName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
