package nutchat.view.swinggui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class InfoPanel extends JPanel
{
    private static final long serialVersionUID = 1L;

    /**
     * Create the panel.
     */
    public InfoPanel()
    {
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] { 122, 154, 46, 0 };
        gridBagLayout.rowHeights = new int[] { 14, 0, 0, 0, 0, 0, 0, 0 };
        gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
        gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                        Double.MIN_VALUE };
        setLayout(gridBagLayout);

        JLabel lblNewLabel = new JLabel("NutChat by Amadeusz Sadowski");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.anchor = GridBagConstraints.NORTH;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 1;
        gbc_lblNewLabel.gridy = 0;
        add(lblNewLabel, gbc_lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Projekt zaliczeniowy na Politechnice Warszawskiej.");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1.anchor = GridBagConstraints.NORTH;
        gbc_lblNewLabel_1.gridx = 1;
        gbc_lblNewLabel_1.gridy = 2;
        add(lblNewLabel_1, gbc_lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel(
                        "Nie ma \u017Cadnych gwarancji co do dzia\u0142ania programu.");
        GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_2.gridx = 1;
        gbc_lblNewLabel_2.gridy = 4;
        add(lblNewLabel_2, gbc_lblNewLabel_2);

        JLabel lblcMit = new JLabel("(c) 2014, MIT license");
        GridBagConstraints gbc_lblcMit = new GridBagConstraints();
        gbc_lblcMit.insets = new Insets(0, 0, 0, 5);
        gbc_lblcMit.gridx = 1;
        gbc_lblcMit.gridy = 6;
        add(lblcMit, gbc_lblcMit);

    }

}
