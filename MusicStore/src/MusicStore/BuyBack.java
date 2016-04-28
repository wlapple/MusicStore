package MusicStore;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author wlzapple, cabatts, ashalbert
 */
public class BuyBack extends JFrame {

    private final static int WIDTH = 350, HEIGHT = 200;
    private JButton back, transact;
    private JLabel itemL, condL;
    private backButtonHandler backBH;
    private BoxValueChangeHandler select;
    private transactButtonHandler transactBH;
    private final String username, password;

    private final String[] instruments = {"Drum Set", "Alto Sax", "Tenor Sax", "Trumpet",
        "Electric Guitar", "Euphonium", "Flute", "Drum Sticks", "Music Books",
        "Stands", "Amplifiers", "Guitar Picks", "Baritone Sax", "Timpani",
        "Cymbals", "CDs", "Violin", "Piano", "Ocarina", "Acoustic Guitar",
        "Trombone", "Sousaphone", "Marimba", "Clarinet", "Triangle"
    };

    private final String[] conditions = {"Perfect", "Good", "Acceptable", "Broken"};
    private final double[] percent = {.90, .65, .40};

    private JComboBox<String> options;
    private JComboBox<String> conds;

    private String selectedInstrument;

    public BuyBack(String username, String password) {
        this.username = username;
        this.password = password;
        this.getContentPane().setBackground(new Color(0, 129, 172));
        int receiptYN = JOptionPane.showConfirmDialog(null, "Does the customer have a receipt from within the last 3 years?", "Receipt", JOptionPane.YES_NO_OPTION);
        if (receiptYN != JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Inform the customer that we cannot accept an item without a receipt.", "", JOptionPane.PLAIN_MESSAGE);
            MainMenu mainMenu = new MainMenu(this.username, this.password);
        } else {

            select = new BoxValueChangeHandler();
            options = new JComboBox<>(instruments);
            options.setSelectedIndex(-1);
            options.addActionListener(select);

            conds = new JComboBox<>(conditions);
            conds.setSelectedIndex(-1);
            conds.addActionListener(select);

            back = new JButton("Back");
            backBH = new backButtonHandler();
            back.addActionListener(backBH);

            transact = new JButton("Transact");
            transactBH = new transactButtonHandler();
            transact.addActionListener(transactBH);

            itemL = new JLabel("Item for Buy-Back", SwingConstants.LEFT);
            condL = new JLabel("Condition of Item", SwingConstants.LEFT);

            this.setTitle("Buy-Back");

            SpringLayout layout = new SpringLayout();
            Container pane = getContentPane();
            pane.setLayout(layout);

            pane.add(itemL);
            pane.add(options);
            pane.add(condL);
            pane.add(conds);
            pane.add(back);
            pane.add(transact);

            transact.setEnabled(false);

            layout.putConstraint(SpringLayout.WEST, itemL, 50, SpringLayout.WEST, pane);
            layout.putConstraint(SpringLayout.NORTH, itemL, 25, SpringLayout.NORTH, pane);
            layout.putConstraint(SpringLayout.WEST, options, 50, SpringLayout.WEST, pane);
            layout.putConstraint(SpringLayout.NORTH, options, 40, SpringLayout.NORTH, pane);
            layout.putConstraint(SpringLayout.EAST, condL, -50, SpringLayout.EAST, pane);
            layout.putConstraint(SpringLayout.NORTH, condL, 25, SpringLayout.NORTH, pane);
            layout.putConstraint(SpringLayout.EAST, conds, -50, SpringLayout.EAST, pane);
            layout.putConstraint(SpringLayout.NORTH, conds, 40, SpringLayout.NORTH, pane);
            layout.putConstraint(SpringLayout.WEST, back, 25, SpringLayout.WEST, pane);
            layout.putConstraint(SpringLayout.SOUTH, back, -25, SpringLayout.SOUTH, pane);
            layout.putConstraint(SpringLayout.EAST, transact, -25, SpringLayout.EAST, pane);
            layout.putConstraint(SpringLayout.SOUTH, transact, -25, SpringLayout.SOUTH, pane);

            this.setSize(WIDTH, HEIGHT);
            this.setLocationRelativeTo(null);
            this.setVisible(true);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
    }

    private class backButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            BuyBack.this.dispose();
            MainMenu mainMenu = new MainMenu(username, password);
        }

    }

    private class transactButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String item = options.getSelectedItem().toString();
            String cond = conds.getSelectedItem().toString();
            
            BuyBack.this.dispose();
            if (!LogScreen.stockPrep.checkOrder(options.getSelectedIndex() + 1, 1)) {
                JOptionPane.showMessageDialog(null, "Inform the customer that we cannot accept the instrument at this time.");
                MainMenu mainMenu = new MainMenu(username, password);
            }
            else if (conds.getSelectedIndex() == 3) {
                JOptionPane.showMessageDialog(null, "Inform the customer that we can not accept"
                        + " an instrument in broken condition.");
                MainMenu mainmenu = new MainMenu(username, password);
            } else {
                LogScreen.stockPrep.invAdd(options.getSelectedIndex() + 1, 1);
                double price = Integer.parseInt(Inventory.stock[options.getSelectedIndex() + 1][2]) * percent[conds.getSelectedIndex()];
                JOptionPane.showMessageDialog(null, "For a " + item + " in " + cond + " condition,"
                        + " we will give you $" + Double.toString(price) + "0", "", JOptionPane.PLAIN_MESSAGE);
                MainMenu mainMenu = new MainMenu(username, password);
            }
        }

    }

    private class BoxValueChangeHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (options.getSelectedIndex() != -1) {
                if (conds.getSelectedIndex() != -1) {
                    transact.setEnabled(true);
                }
            }
        }

    }

}
