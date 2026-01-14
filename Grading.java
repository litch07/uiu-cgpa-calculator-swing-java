import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Grading extends JFrame {
    private static final String[] LETTERS = {
            "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "F"
    };
    private static final String[] POINTS = {
            "4.00", "3.67", "3.33", "3.00", "2.67", "2.33", "2.00", "1.67", "1.33", "1.00", "0.00"
    };
    private static final String[] MARKS = {
            "90-100", "86-89", "82-85", "78-81", "74-77", "70-73", "66-69", "62-65", "58-61", "55-57", "<55"
    };

    public Grading() {
        Font baseFont = new Font("Segoe UI", Font.PLAIN, 12);
        Font titleFont = baseFont.deriveFont(Font.BOLD, 16f);
        Font headerFont = baseFont.deriveFont(Font.BOLD, 12f);

        setTitle("UIU Grading Scale");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel content = new JPanel(new BorderLayout(0, 10));
        content.setBorder(new EmptyBorder(12, 12, 12, 12));
        setContentPane(content);

        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setAlignmentX(LEFT_ALIGNMENT);

        JLabel titleLabel = new JLabel("UIU Grading Scale");
        titleLabel.setFont(titleFont);

        JLabel subtitleLabel = new JLabel("Letter grade to grade point and marks (%)");
        subtitleLabel.setFont(baseFont);

        header.add(titleLabel);
        header.add(Box.createVerticalStrut(4));
        header.add(subtitleLabel);

        content.add(header, BorderLayout.NORTH);

        JPanel table = new JPanel(new GridLayout(LETTERS.length + 1, 3, 6, 6));
        table.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));

        table.add(createHeaderCell("Letter Grade", headerFont));
        table.add(createHeaderCell("Grade Point", headerFont));
        table.add(createHeaderCell("Marks (%)", headerFont));

        for (int i = 0; i < LETTERS.length; i++) {
            boolean alternate = i % 2 == 0;
            table.add(createCell(LETTERS[i], baseFont, alternate));
            table.add(createCell(POINTS[i], baseFont, alternate));
            table.add(createCell(MARKS[i], baseFont, alternate));
        }

        content.add(table, BorderLayout.CENTER);

        pack();
        setSize(420, getHeight());
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JLabel createHeaderCell(String text, Font font) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(font);
        label.setOpaque(true);
        label.setBackground(new Color(235, 235, 235));
        label.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        return label;
    }

    private JLabel createCell(String text, Font font, boolean alternate) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(font);
        label.setOpaque(true);
        label.setBackground(alternate ? new Color(248, 248, 248) : Color.WHITE);
        label.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        return label;
    }
}
