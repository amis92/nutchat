package nutchat.view.swinggui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class AddContactDialog extends JDialog
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField usernameField;
    private JTextField addressField;
    private JPanel panel;

    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {
        try
        {
            AddContactDialog dialog = new AddContactDialog();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public AddContactDialog()
    {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setTitle("Add new user");
        setBounds(100, 100, 260, 181);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new TitledBorder(null, "New user details", TitledBorder.LEADING,
                        TitledBorder.TOP, null, null));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        {
            panel = new JPanel();
            panel.setLayout(new FormLayout(new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC,
                            FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
                            FormFactory.DEFAULT_COLSPEC, }, new RowSpec[] {
                            FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                            FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, }));
            {
                JLabel lblUserName = new JLabel("User name:");
                panel.add(lblUserName, "2, 2");
            }
            {
                usernameField = new JTextField();
                panel.add(usernameField, "4, 2, fill, default");
                usernameField.setColumns(10);
            }
            {
                JLabel lblIpAddress = new JLabel("IP address:");
                panel.add(lblIpAddress, "2, 4");
            }
            {
                addressField = new JTextField();
                panel.add(addressField, "4, 4, fill, default");
                addressField.setColumns(10);
            }
        }
        GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
        gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING,
                                        gl_contentPanel.createSequentialGroup()
                                                        .addContainerGap()
                                                        .addComponent(panel,
                                                                        GroupLayout.DEFAULT_SIZE,
                                                                        636, Short.MAX_VALUE)
                                                        .addGap(0)));
        gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPanel
                                        .createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(panel, GroupLayout.DEFAULT_SIZE, 284,
                                                        Short.MAX_VALUE).addGap(0)));
        contentPanel.setLayout(gl_contentPanel);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton addButton = new JButton("Add");
                addButton.setActionCommand("OK");
                buttonPane.add(addButton);
                getRootPane().setDefaultButton(addButton);
            }
            {
                JButton cancelButton = new JButton("Cancel");
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
    }

}
