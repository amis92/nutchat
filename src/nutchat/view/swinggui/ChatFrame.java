package nutchat.view.swinggui;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;

public class ChatFrame extends JFrame
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
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
        gbl_leftPanel.rowHeights = new int[] {0, 0, 0};
        gbl_leftPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_leftPanel.rowWeights = new double[]{0.0, 1.0, 0.0};
        leftPanel.setLayout(gbl_leftPanel);
        
        JPanel listHeaderPanel = new JPanel();
        GridBagConstraints gbc_listHeaderPanel = new GridBagConstraints();
        gbc_listHeaderPanel.insets = new Insets(5, 5, 5, 0);
        gbc_listHeaderPanel.fill = GridBagConstraints.HORIZONTAL;
        gbc_listHeaderPanel.gridx = 0;
        gbc_listHeaderPanel.gridy = 0;
        leftPanel.add(listHeaderPanel, gbc_listHeaderPanel);
        GridBagLayout gbl_listHeaderPanel = new GridBagLayout();
        gbl_listHeaderPanel.columnWidths = new int[]{52, 52, 0};
        gbl_listHeaderPanel.rowHeights = new int[]{23, 0};
        gbl_listHeaderPanel.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
        gbl_listHeaderPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        listHeaderPanel.setLayout(gbl_listHeaderPanel);
        
        JLabel contactListLabel = new JLabel("Contacts");
        GridBagConstraints gbc_contactListLabel = new GridBagConstraints();
        gbc_contactListLabel.fill = GridBagConstraints.BOTH;
        gbc_contactListLabel.insets = new Insets(0, 0, 0, 5);
        gbc_contactListLabel.gridx = 0;
        gbc_contactListLabel.gridy = 0;
        listHeaderPanel.add(contactListLabel, gbc_contactListLabel);
        
        JButton btnNewButton = new JButton("+");
        btnNewButton.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.anchor = GridBagConstraints.EAST;
        gbc_btnNewButton.gridx = 1;
        gbc_btnNewButton.gridy = 0;
        listHeaderPanel.add(btnNewButton, gbc_btnNewButton);
        
        JScrollPane scrollPane = new JScrollPane();
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 1;
        leftPanel.add(scrollPane, gbc_scrollPane);
        
        JList contactList = new JList();
        scrollPane.setViewportView(contactList);
        contactList.setVisibleRowCount(-1);
        contactList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JPanel footerPanel = new JPanel();
        GridBagConstraints gbc_footerPanel = new GridBagConstraints();
        gbc_footerPanel.insets = new Insets(0, 0, 5, 0);
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
