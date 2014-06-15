package nutchat.view.swinggui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JSplitPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JList;
import java.awt.Insets;
import javax.swing.ListSelectionModel;
import java.awt.CardLayout;

public class ChatFrame extends JFrame
{

    private JPanel contentPane;

    /**
     * Create the frame.
     */
    public ChatFrame()
    {
        setTitle("NutChat by Amadeusz Sadowski");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 560, 640);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(1, 0, 0, 0));
        
        JSplitPane splitPane = new JSplitPane();
        contentPane.add(splitPane);
        
        JPanel leftPanel = new JPanel();
        splitPane.setLeftComponent(leftPanel);
        GridBagLayout gbl_leftPanel = new GridBagLayout();
        gbl_leftPanel.columnWidths = new int[]{0, 0};
        gbl_leftPanel.rowHeights = new int[]{0, 0, 0, 0};
        gbl_leftPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_leftPanel.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
        leftPanel.setLayout(gbl_leftPanel);
        
        JPanel listHeaderPanel = new JPanel();
        GridBagConstraints gbc_listHeaderPanel = new GridBagConstraints();
        gbc_listHeaderPanel.insets = new Insets(5, 5, 5, 5);
        gbc_listHeaderPanel.fill = GridBagConstraints.HORIZONTAL;
        gbc_listHeaderPanel.gridx = 0;
        gbc_listHeaderPanel.gridy = 0;
        leftPanel.add(listHeaderPanel, gbc_listHeaderPanel);
        listHeaderPanel.setLayout(new GridLayout(1, 0, 0, 0));
        
        JLabel lblNewLabel = new JLabel("Contacts");
        listHeaderPanel.add(lblNewLabel);
        
        JButton btnNewButton = new JButton("+");
        btnNewButton.setHorizontalAlignment(SwingConstants.RIGHT);
        listHeaderPanel.add(btnNewButton);
        
        JList contactList = new JList();
        contactList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        GridBagConstraints gbc_contactList = new GridBagConstraints();
        gbc_contactList.insets = new Insets(0, 0, 5, 0);
        gbc_contactList.fill = GridBagConstraints.BOTH;
        gbc_contactList.gridx = 0;
        gbc_contactList.gridy = 1;
        leftPanel.add(contactList, gbc_contactList);
        
        JPanel footerPanel = new JPanel();
        GridBagConstraints gbc_footerPanel = new GridBagConstraints();
        gbc_footerPanel.fill = GridBagConstraints.HORIZONTAL;
        gbc_footerPanel.gridx = 0;
        gbc_footerPanel.gridy = 2;
        leftPanel.add(footerPanel, gbc_footerPanel);
        
        JButton aboutButton = new JButton("About NutChat");
        footerPanel.add(aboutButton);
        
        JPanel rightPanel = new JPanel();
        splitPane.setRightComponent(rightPanel);
        rightPanel.setLayout(new CardLayout(0, 0));
    }

}
