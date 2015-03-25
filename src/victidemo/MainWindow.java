/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package victidemo;

import com.sun.javafx.tk.FileChooserType;
import java.io.FileFilter;
import javafx.stage.FileChooser;
import javax.swing.JFileChooser;
import TextminingPackage.TextMiningParser;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import GraphicsElements.WordCloudContainer;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.io.File;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author jpasqualini
 */
public class MainWindow extends javax.swing.JPanel {
    JFileChooser fc;
     
    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        fc = new JFileChooser();
        fc.setAcceptAllFileFilterUsed(false);
        TextFileFilter ff = new TextFileFilter();
        fc.setFileFilter(ff);
        fc.setCurrentDirectory(new File(System.getProperty("user.home")+"\\Documents\\NetBeansProjects\\VictiDemo\\docs"));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        filenameTextfield = new javax.swing.JTextField();
        openButton = new javax.swing.JButton();
        titleLabel = new javax.swing.JLabel();

        filenameTextfield.setText("file name");
        filenameTextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filenameTextfieldActionPerformed(evt);
            }
        });

        openButton.setText("importer");
        openButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openButtonActionPerformed(evt);
            }
        });

        titleLabel.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Demonstrateur VICTI");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(filenameTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(openButton)))
                .addContainerGap(182, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(111, 111, 111)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filenameTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(openButton))
                .addContainerGap(234, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void openButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openButtonActionPerformed
        // TODO add your handling code here:
        int returnVal = fc.showDialog(this, "choisir Fichier");
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            filenameTextfield.setText(fc.getSelectedFile().getName());
            TextMiningParser tmp = new TextMiningParser();
            tmp.startHashingTextFile(fc.getSelectedFile()); 
            WordCloud wc = new WordCloud();
            JFrame resultFrame = new JFrame("résultat");
            resultFrame.add(wc);
            resultFrame.pack();
            System.out.println("will display");
            resultFrame.setVisible(true);
            wc.getWordCloudTextField().setText(tmp.getContentOfOfile());
            wc.getWordsCloudButton().addActionListener(new ActionListener() { 
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame resultFrame = new JFrame();
                    resultFrame.setLayout(new GridLayout(0, 4));
                    tmp.GetWordCloud().stream().forEach((wcc) -> {
                        System.out.println("///////////////////////////");
                        System.out.println("x:"+wcc.getBounds().getX());
                        System.out.println("y:"+wcc.getBounds().getY());
                        System.out.println("width:"+wcc.getBounds().getWidth());
                        System.out.println("height:"+wcc.getBounds().getHeight());
                        resultFrame.add(wcc);
                        
                    });
                    resultFrame.pack();
                    resultFrame.setVisible(true);
                }
            } );
        }       
    }//GEN-LAST:event_openButtonActionPerformed

    private void filenameTextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filenameTextfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filenameTextfieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField filenameTextfield;
    private javax.swing.JButton openButton;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables

    private javax.swing.filechooser.FileFilter TextFileFilter() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
