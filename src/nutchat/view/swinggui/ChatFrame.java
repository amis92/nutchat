package nutchat.view.swinggui;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import nutchat.controller.IChatController;
import nutchat.model.IMessage;
import nutchat.model.IUser;
import nutchat.model.MessageDispatcher;

public class ChatFrame extends JFrame
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final IChatController controller;
    private final ChatManager chatManager;
    private IUser currentUser;
    private JPanel contentPane;
    private DefaultListModel<IUser> contactListModel;
    private final Action addContactAction = new SwingAction();

    /**
     * Create the frame.
     */
    public ChatFrame(IChatController controller)
    {
        this.controller = controller;
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
        gbl_leftPanel.columnWidths = new int[] { 0, 0 };
        gbl_leftPanel.rowHeights = new int[] { 0, 0, 0 };
        gbl_leftPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
        gbl_leftPanel.rowWeights = new double[] { 0.0, 1.0, 0.0 };
        leftPanel.setLayout(gbl_leftPanel);

        JPanel listHeaderPanel = new JPanel();
        GridBagConstraints gbc_listHeaderPanel = new GridBagConstraints();
        gbc_listHeaderPanel.insets = new Insets(5, 5, 5, 0);
        gbc_listHeaderPanel.fill = GridBagConstraints.HORIZONTAL;
        gbc_listHeaderPanel.gridx = 0;
        gbc_listHeaderPanel.gridy = 0;
        leftPanel.add(listHeaderPanel, gbc_listHeaderPanel);
        GridBagLayout gbl_listHeaderPanel = new GridBagLayout();
        gbl_listHeaderPanel.columnWidths = new int[] { 52, 52, 0 };
        gbl_listHeaderPanel.rowHeights = new int[] { 23, 0 };
        gbl_listHeaderPanel.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
        gbl_listHeaderPanel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
        listHeaderPanel.setLayout(gbl_listHeaderPanel);

        JLabel contactListLabel = new JLabel("Contacts");
        GridBagConstraints gbc_contactListLabel = new GridBagConstraints();
        gbc_contactListLabel.fill = GridBagConstraints.BOTH;
        gbc_contactListLabel.insets = new Insets(0, 0, 0, 5);
        gbc_contactListLabel.gridx = 0;
        gbc_contactListLabel.gridy = 0;
        listHeaderPanel.add(contactListLabel, gbc_contactListLabel);

        JButton btnNewButton = new JButton("+");
        btnNewButton.setAction(addContactAction);
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

        contactListModel = new DefaultListModel<>();

        final JList<IUser> contactList = new JList<>(contactListModel);
        contactList.addListSelectionListener(new ListSelectionListener()
            {
                public void valueChanged(ListSelectionEvent arg0)
                {
                    if (contactList.getSelectedIndex() != -1)
                    {
                        ChatFrame.this.controller.openChatWith(contactList.getSelectedValue());
                    }
                }
            });
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

        JPanel chatPanel = new JPanel();
        splitPane.setRightComponent(chatPanel);
        chatPanel.setLayout(new CardLayout(2, 2));
        chatManager = new ChatManager(chatPanel);
    }

    /**
     * Reloads contacts from provided list.
     * 
     * @param users
     *            - the list to display contacts from.
     */
    public void refreshContactList(List<IUser> users)
    {
        contactListModel.clear();
        for (IUser user : users)
        {
            contactListModel.addElement(user);
        }
    }

    /**
     * Sets current user to the new provided one and changes frame title
     * appropriately.
     * 
     * @param self
     *            - the new current user.
     */
    public void refreshCurrentInfo(IUser self)
    {
        currentUser = self;
        setTitle(chatManager.formatTitle(false));
    }

    /**
     * Calls chat manager to show appropriate panel in chat panel's cardLayout.
     * 
     * @param user
     *            - user to open chat with.
     * @param chat
     *            - chat history to show.
     */
    public void openChatWith(IUser user, List<IMessage> chat)
    {
        chatManager.openChatWith(user, chat);
    }

    /**
     * Shows that there are new messages awaiting using chatManager.
     * 
     * @param message
     *            - the message to inform about.
     */
    public void showNewMessage(IMessage message)
    {
        chatManager.informOnNewMessage(message);
    }

    /**
     * Provides action for adding new user using {@link AddContactDialog}.
     * 
     * @author Amadeusz Sadowski
     * 
     */
    private class SwingAction extends AbstractAction
    {
        private static final long serialVersionUID = 1L;

        public SwingAction()
        {
            putValue(NAME, "+");
            putValue(SHORT_DESCRIPTION, "Creates new contact");
        }

        public void actionPerformed(ActionEvent e)
        {
            EventQueue.invokeLater(new Runnable()
                {

                    @Override
                    public void run()
                    {
                        AddContactDialog dialog = new AddContactDialog(ChatFrame.this, true);
                        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                        dialog.setVisible(true);
                        IUser newUser = dialog.getNewUser();
                        if (newUser != null)
                        {
                            controller.addUser(newUser);
                        }
                    }
                });
        }
    }

    /**
     * Manages CardLayout of chatPanel
     * 
     * @author Amadeusz
     * 
     */
    private class ChatManager
    {
        private final JPanel panel;
        private final CardLayout cardLayout;
        private final Map<IUser, ChatPanel> chatMap;
        private final MessageDispatcher dispatcher;
        private IUser currentChatUser = null;
        private IUser messageAwaitingUser = null;

        public ChatManager(JPanel cardPanel)
        {
            this.panel = cardPanel;
            this.cardLayout = (CardLayout) cardPanel.getLayout();
            this.chatMap = new HashMap<>();
            this.dispatcher = new MessageDispatcher(currentUser, controller);
        }

        /**
         * Puts {@link ChatPanel} on top of CardLayout. If there is no panel for
         * user, creates new one.
         * 
         * @param user
         *            - the user with which
         *            {@link ChatPanel#ChatPanel(IUser, List)} was created.
         * @param chat
         *            - chat history.
         */
        public void openChatWith(IUser user, List<IMessage> chat)
        {
            currentChatUser = user;
            if (!chatMap.containsKey(user))
            {
                chatMap.put(user, new ChatPanel(user, chat, dispatcher));
                panel.add(chatMap.get(user), user.getUserName());
            }
            // chatMap.get(user).;
            cardLayout.show(panel, user.getUserName());
            if (messageAwaitingUser == currentChatUser)
            {
                setTitle(formatTitle(false));
            }
        }

        public void informOnNewMessage(IMessage message)
        {
            if (message.getSender() != currentChatUser)
            {
                messageAwaitingUser = message.getSender();
                setTitle(formatTitle(true));
            }
        }

        private String formatTitle(boolean isAwaiting)
        {
            return String.format("NutChat - %s (%s)", currentUser.getUserName(), currentUser
                            .getAddress().getHostAddress());
        }
    }
}
