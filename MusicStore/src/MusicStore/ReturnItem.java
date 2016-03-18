/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MusicStore;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author wlzapple, cabatts, ashalbert
 */
class ReturnItem {
    private final static int WIDTH = 350;
    private final static int HEIGHT = 200;
    
    private JLabel itemL, condL;
    
    private JButton finalize;
    
    private String[] instruments = {"Drum Set", "Alto Sax", "Tenor Sax", "Trumpet",
            "Electric Guitar", "Baritone", "Flute", "Drum Sticks", "Music Books",
            "Stands", "Amplifiers", "Guitar Picks", "Baritone Sax", "Timpani",
            "Cymbals", "CDs", "Violin", "Piano", "Ocarina", "Acoustic Guitar",
            "Trombone", "Sousephone", "Marimba", "Clarinet", "Triangle"
    };
    
    private JComboBox<String> instrumentBox;
    
    
    
    //private JButton yes, no;
    
//    private YesButtonHandler yesBH;
//    private NoButtonHandler noBH;
    private ComboBoxHandler instCB;
    
    public ReturnItem(){
        //ask if they have a receipt
        int rYN = JOptionPane.YES_NO_OPTION;
        int receiptYN = JOptionPane.showConfirmDialog(null, "Does the customer have a receipt?", "Receipt", rYN);
        if(receiptYN != JOptionPane.YES_OPTION){
            JOptionPane.showMessageDialog(null, "Inform the customer that we cannot accept an item without a receipt.", "", JOptionPane.PLAIN_MESSAGE);
        }
        else{
            
            instrumentBox = new JComboBox<>(instruments);
            instrumentBox.setSelectedIndex(-1);
            instrumentBox.addActionListener(instCB);
            
            
            
        }
    }

//    private class NoButtonHandler implements ActionListener{
//
//        public NoButtonHandler() {
//        }
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//    }
//
//    private class YesButtonHandler implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        
//    }

    private static class ComboBoxHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }
    
}
