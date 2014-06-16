package nutchat.view.swinggui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

import nutchat.model.IMessage;
import nutchat.model.IUser;

public class ChatPanel extends JPanel
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final DefaultListModel<IMessage> listModel;

    /**
     * Create the panel.
     */
    public ChatPanel(IUser recipient, List<IMessage> chat)
    {
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] { 0 };
        gridBagLayout.rowHeights = new int[] { 0 };
        gridBagLayout.columnWeights = new double[] { 1.0 };
        gridBagLayout.rowWeights = new double[] { 1.0 };
        setLayout(gridBagLayout);

        JSplitPane splitPane = new JSplitPane();
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        GridBagConstraints gbc_splitPane = new GridBagConstraints();
        gbc_splitPane.insets = new Insets(0, 0, 5, 0);
        gbc_splitPane.fill = GridBagConstraints.BOTH;
        gbc_splitPane.gridx = 0;
        gbc_splitPane.gridy = 0;
        add(splitPane, gbc_splitPane);

        JPanel newMessagePanel = new JPanel();
        splitPane.setRightComponent(newMessagePanel);
        GridBagLayout gbl_newMessagePanel = new GridBagLayout();
        gbl_newMessagePanel.columnWidths = new int[] { 1, 0 };
        gbl_newMessagePanel.rowHeights = new int[] { 1, 0, 0 };
        gbl_newMessagePanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
        gbl_newMessagePanel.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
        newMessagePanel.setLayout(gbl_newMessagePanel);

        JTextArea newMessageTextArea = new JTextArea();
        newMessageTextArea.setTabSize(4);
        GridBagConstraints gbc_newMessageTextArea = new GridBagConstraints();
        gbc_newMessageTextArea.insets = new Insets(5, 5, 0, 5);
        gbc_newMessageTextArea.fill = GridBagConstraints.BOTH;
        gbc_newMessageTextArea.gridx = 0;
        gbc_newMessageTextArea.gridy = 0;
        newMessagePanel.add(newMessageTextArea, gbc_newMessageTextArea);

        JLabel sendHintLabel = new JLabel("Press Enter to send");
        GridBagConstraints gbc_sendHintLabel = new GridBagConstraints();
        gbc_sendHintLabel.insets = new Insets(5, 5, 5, 5);
        gbc_sendHintLabel.anchor = GridBagConstraints.EAST;
        gbc_sendHintLabel.gridx = 0;
        gbc_sendHintLabel.gridy = 1;
        newMessagePanel.add(sendHintLabel, gbc_sendHintLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportBorder(new TitledBorder(null, String.format("Chat with %s:",
                        recipient.getUserName()), TitledBorder.LEADING, TitledBorder.TOP, null,
                        null));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        splitPane.setLeftComponent(scrollPane);

        listModel = new DefaultListModel<>();
        for (IMessage message : chat)
        {
            listModel.addElement(message);
        }
        
        JList<IMessage> chatMessagesList = new JList<>(listModel);
        scrollPane.setViewportView(chatMessagesList);

    }

}
