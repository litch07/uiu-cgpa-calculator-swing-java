import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class Calculator extends JFrame {
    private static final int MAX_COURSE_COUNT = 15;
    private static final int INDEX_WIDTH = 26;
    private static final int CREDIT_WIDTH = 80;
    private static final int GRADE_WIDTH = 100;
    private static final int MARKS_WIDTH = 100;
    private static final int PREV_GRADE_WIDTH = 110;
    private static final int RETAKE_WIDTH = 70;
    private static final int REMOVE_WIDTH = 52;
    private static final int EMBEDDED_GRADING_MIN_WIDTH = 880;
    private static final int MIN_WINDOW_WIDTH = 850;
    private static final int MIN_WINDOW_HEIGHT = 620;
    private static final String COMPLETED_CREDITS_PLACEHOLDER = "e.g., 90";
    private static final String CURRENT_CGPA_PLACEHOLDER = "e.g., 3.45";
    private static final String GITHUB_URL = "https://github.com/litch07";
    private static final String FOOTER_NAME = "SADID AHMED";
    private static final String[] GRADE_LETTERS = {
            "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "F"
    };
    private static final double[] GRADE_POINTS = {
            4.00, 3.67, 3.33, 3.00, 2.67, 2.33, 2.00, 1.67, 1.33, 1.00, 0.00
    };
    private static final String[] GRADE_MARKS = {
            "90-100", "86-89", "82-85", "78-81", "74-77", "70-73",
            "66-69", "62-65", "58-61", "55-57", "<55"
    };

    private final JTextField completedCreditsText;
    private final JTextField currentCgpaText;
    private final JButton addButton;
    private final JButton countButton;
    private final JButton gradingButton;
    private final JPanel centerPanel;
    private final JPanel gradingPanel;
    private final Color gradingButtonForeground;
    private final Color gradingButtonBackground;
    private final JPanel coursesListPanel;
    private final JPanel addRowPanel;
    private final List<CourseRow> courseRows;
    private final String[] creditOptions;
    private final String[] gradeOptions;
    private final Font baseFont;
    private final Font titleFont;
    private final Font sectionFont;
    private final GraphicsDevice graphicsDevice;

    private boolean isFullscreen;
    private Rectangle windowedBounds;
    private int windowedState = JFrame.NORMAL;
    private boolean windowedResizable = true;

    private double totalCgpaCount, gpaTotalCount;
    private int cgpaCreditCount, gpaCreditCount;

    public Calculator() {
        baseFont = new Font("Segoe UI", Font.PLAIN, 13);
        titleFont = baseFont.deriveFont(Font.BOLD, 18f);
        sectionFont = baseFont.deriveFont(Font.BOLD, 13f);

        creditOptions = new String[] { "Select", "1", "2", "3", "4" };
        gradeOptions = new String[GRADE_LETTERS.length + 1];
        gradeOptions[0] = "Select";
        for (int i = 0; i < GRADE_LETTERS.length; i++) {
            gradeOptions[i + 1] = GRADE_LETTERS[i];
        }

        setTitle("UIU CGPA Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        JPanel content = new JPanel(new BorderLayout(0, 12));
        content.setBorder(new EmptyBorder(16, 16, 16, 16));
        setContentPane(content);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setAlignmentX(LEFT_ALIGNMENT);

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setAlignmentX(LEFT_ALIGNMENT);

        JLabel titleLabel = new JLabel("UIU CGPA Calculator");
        titleLabel.setFont(titleFont);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Calculate term GPA and updated CGPA with retake support");
        subtitleLabel.setFont(baseFont);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerPanel.add(titleLabel);
        headerPanel.add(Box.createVerticalStrut(4));
        headerPanel.add(subtitleLabel);

        topPanel.add(headerPanel);
        topPanel.add(Box.createVerticalStrut(14));
        headerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, headerPanel.getPreferredSize().height));
        headerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel baselinePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 18, 6));
        baselinePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210)),
                new EmptyBorder(10, 10, 10, 10)));
        baselinePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel completedCreditsLabel = new JLabel("Completed Credits");
        completedCreditsLabel.setFont(sectionFont);
        completedCreditsLabel.setPreferredSize(new Dimension(120, 26));

        completedCreditsText = new JTextField();
        completedCreditsText.setColumns(8);
        completedCreditsText.setFont(baseFont);
        completedCreditsText.setToolTipText("Total credits completed so far");
        installPlaceholder(completedCreditsText, COMPLETED_CREDITS_PLACEHOLDER);
        completedCreditsText.setPreferredSize(new Dimension(110, 26));

        JLabel currentCgpaLabel = new JLabel("Current CGPA");
        currentCgpaLabel.setFont(sectionFont);
        currentCgpaLabel.setPreferredSize(new Dimension(100, 26));

        currentCgpaText = new JTextField();
        currentCgpaText.setColumns(6);
        currentCgpaText.setFont(baseFont);
        currentCgpaText.setToolTipText("Your current CGPA");
        installPlaceholder(currentCgpaText, CURRENT_CGPA_PLACEHOLDER);
        currentCgpaText.setPreferredSize(new Dimension(110, 26));

        JPanel completedGroup = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
        completedGroup.add(completedCreditsLabel);
        completedGroup.add(completedCreditsText);

        JPanel cgpaGroup = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
        cgpaGroup.add(currentCgpaLabel);
        cgpaGroup.add(currentCgpaText);

        baselinePanel.add(completedGroup);
        baselinePanel.add(cgpaGroup);

        baselinePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, baselinePanel.getPreferredSize().height));
        topPanel.add(baselinePanel);
        content.add(topPanel, BorderLayout.NORTH);

        JPanel coursesSection = new JPanel(new BorderLayout(0, 8));
        coursesSection.setAlignmentX(LEFT_ALIGNMENT);
        coursesSection.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210)),
                new EmptyBorder(10, 0, 10, 0)));

        JPanel coursesHeader = new JPanel(new BorderLayout());
        coursesHeader.setAlignmentX(LEFT_ALIGNMENT);

        JLabel coursesTitle = new JLabel("Current Term Courses");
        coursesTitle.setFont(sectionFont);
        coursesHeader.add(coursesTitle, BorderLayout.WEST);

        JLabel coursesLimit = new JLabel("Max 15 courses");
        coursesLimit.setFont(baseFont.deriveFont(Font.PLAIN, 11f));
        coursesLimit.setForeground(new Color(90, 90, 90));
        coursesHeader.add(coursesLimit, BorderLayout.EAST);

        coursesSection.add(coursesHeader, BorderLayout.NORTH);

        coursesListPanel = new CoursesListPanel();
        coursesListPanel.setLayout(new BoxLayout(coursesListPanel, BoxLayout.Y_AXIS));
        coursesListPanel.setAlignmentX(LEFT_ALIGNMENT);

        coursesListPanel.add(buildHeaderRow(sectionFont));
        coursesListPanel.add(Box.createVerticalStrut(6));

        addButton = new JButton("Add Course");
        addButton.setToolTipText("Add a new course row");
        addButton.setFont(sectionFont);
        addButton.setMargin(new Insets(8, 20, 8, 20));

        addRowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        addRowPanel.setAlignmentX(LEFT_ALIGNMENT);
        addRowPanel.setBorder(new EmptyBorder(4, 0, 4, 0));
        addRowPanel.add(addButton);
        addRowPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, addRowPanel.getPreferredSize().height));

        coursesListPanel.add(addRowPanel);

        JScrollPane coursesScrollPane = new JScrollPane(
                coursesListPanel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        coursesScrollPane.setBorder(BorderFactory.createEmptyBorder());
        coursesScrollPane.getVerticalScrollBar().setUnitIncrement(12);
        coursesScrollPane.setPreferredSize(new Dimension(700, 260));

        coursesSection.add(coursesScrollPane, BorderLayout.CENTER);
        coursesSection.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        gradingPanel = buildEmbeddedGradingPanel();
        gradingPanel.setVisible(false);

        centerPanel = new JPanel(new BorderLayout(6, 0));
        centerPanel.add(coursesSection, BorderLayout.CENTER);
        centerPanel.add(gradingPanel, BorderLayout.EAST);
        content.add(centerPanel, BorderLayout.CENTER);

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 4));
        actionPanel.setAlignmentX(LEFT_ALIGNMENT);

        countButton = new JButton("Calculate Results");
        countButton.setFont(sectionFont);
        countButton.setMargin(new Insets(8, 18, 8, 18));
        gradingButton = new JButton("View Grading");
        gradingButton.setFont(sectionFont);
        gradingButton.setMargin(new Insets(8, 18, 8, 18));
        gradingButtonForeground = gradingButton.getForeground();
        gradingButtonBackground = gradingButton.getBackground();

        actionPanel.add(countButton);
        actionPanel.add(gradingButton);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setAlignmentX(LEFT_ALIGNMENT);

        bottomPanel.add(actionPanel);
        bottomPanel.add(Box.createVerticalStrut(8));

        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        footerPanel.setAlignmentX(LEFT_ALIGNMENT);

        JLabel footerLabel = new JLabel("Created by " + FOOTER_NAME);
        footerLabel.setFont(baseFont.deriveFont(Font.PLAIN, 11f));
        footerLabel.setForeground(new Color(100, 100, 100));

        JButton followButton = createFollowButton();

        footerPanel.add(footerLabel);
        footerPanel.add(followButton);
        bottomPanel.add(footerPanel);
        content.add(bottomPanel, BorderLayout.SOUTH);

        courseRows = new ArrayList<>();
        addCourseRow();

        addButton.addActionListener(e -> addCourseRow());
        countButton.addActionListener(countButtonListener());
        gradingButton.addActionListener(e -> new Grading());

        installKeyboardShortcuts();
        updateGradingViewState();
        addWindowStateListener(e -> updateGradingViewState());
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateGradingViewState();
            }
        });

        pack();
        setMinimumSize(new Dimension(MIN_WINDOW_WIDTH, MIN_WINDOW_HEIGHT));
        setSize(new Dimension(670, 520));
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(countButton);
        setVisible(true);
    }

    private JPanel buildHeaderRow(Font sectionFont) {
        JPanel headerRow = new JPanel(new GridBagLayout());
        headerRow.setAlignmentX(LEFT_ALIGNMENT);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 8);
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        JLabel indexHeader = createHeaderLabel("#", sectionFont, INDEX_WIDTH);
        gbc.gridx = 0;
        headerRow.add(indexHeader, gbc);

        JLabel creditHeader = createHeaderLabel("Credit", sectionFont, CREDIT_WIDTH);
        gbc.gridx = 1;
        headerRow.add(creditHeader, gbc);

        JLabel gradeHeader = createHeaderLabel("Expected Grade", sectionFont, GRADE_WIDTH);
        gbc.gridx = 2;
        headerRow.add(gradeHeader, gbc);

        JLabel marksHeader = createHeaderLabel("Marks (%)", sectionFont, MARKS_WIDTH);
        gbc.gridx = 3;
        headerRow.add(marksHeader, gbc);

        JLabel retakeHeader = createHeaderLabel("Retake", sectionFont, RETAKE_WIDTH);
        gbc.gridx = 4;
        headerRow.add(retakeHeader, gbc);

        JLabel prevHeader = createHeaderLabel("Previous Grade", sectionFont, PREV_GRADE_WIDTH);
        gbc.gridx = 5;
        headerRow.add(prevHeader, gbc);

        JLabel removeHeader = createHeaderLabel("Remove", sectionFont, REMOVE_WIDTH);
        gbc.gridx = 6;
        headerRow.add(removeHeader, gbc);

        gbc.gridx = 7;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        headerRow.add(new JLabel(""), gbc);

        headerRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, headerRow.getPreferredSize().height));
        return headerRow;
    }

    private void addCourseRow() {
        if (courseRows.size() >= MAX_COURSE_COUNT) {
            updateAddButtonState();
            return;
        }

        CourseRow row = createCourseRow(courseRows.size() + 1);
        courseRows.add(row);

        int insertIndex = Math.max(0, coursesListPanel.getComponentCount() - 1);
        coursesListPanel.add(row.panel, insertIndex);
        updateAddButtonState();

        coursesListPanel.revalidate();
        coursesListPanel.repaint();

        SwingUtilities.invokeLater(() -> coursesListPanel.scrollRectToVisible(row.panel.getBounds()));
    }

    private CourseRow createCourseRow(int index) {
        CourseRow row = new CourseRow();
        row.panel = new FadePanel();
        row.panel.setLayout(new GridBagLayout());
        row.panel.setAlignmentX(LEFT_ALIGNMENT);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.insets = new Insets(4, 0, 4, 8);
        gbc.anchor = GridBagConstraints.WEST;

        row.indexLabel = new JLabel(String.valueOf(index));
        row.indexLabel.setFont(baseFont);
        row.indexLabel.setPreferredSize(new Dimension(INDEX_WIDTH, 26));
        gbc.gridx = 0;
        row.panel.add(row.indexLabel, gbc);

        row.creditBox = createComboBox(creditOptions, CREDIT_WIDTH);
        row.creditBox.setToolTipText("Select course credit");
        gbc.gridx = 1;
        row.panel.add(row.creditBox, gbc);

        row.gradeBox = createComboBox(gradeOptions, GRADE_WIDTH);
        row.gradeBox.setToolTipText("Select letter grade");
        gbc.gridx = 2;
        row.panel.add(row.gradeBox, gbc);

        row.marksLabel = new JLabel("--");
        row.marksLabel.setFont(baseFont);
        row.marksLabel.setPreferredSize(new Dimension(MARKS_WIDTH, 26));
        row.marksLabel.setForeground(new Color(90, 90, 90));
        gbc.gridx = 3;
        row.panel.add(row.marksLabel, gbc);

        row.retakeCheck = new JCheckBox("Retake");
        row.retakeCheck.setFont(baseFont);
        row.retakeCheck.setToolTipText("Mark if this is a retake");
        row.retakeCheck.setPreferredSize(new Dimension(RETAKE_WIDTH, 26));
        gbc.gridx = 4;
        row.panel.add(row.retakeCheck, gbc);

        row.prevGradeBox = createComboBox(gradeOptions, PREV_GRADE_WIDTH);
        row.prevGradeBox.setEnabled(false);
        row.prevGradeBox.setToolTipText("Select previous letter grade");

        row.prevGradeLayout = new CardLayout();
        row.prevGradeContainer = new JPanel(row.prevGradeLayout);
        row.prevGradeContainer.setOpaque(false);

        JLabel prevPlaceholder = new JLabel("N/A");
        prevPlaceholder.setFont(baseFont);
        prevPlaceholder.setForeground(new Color(140, 140, 140));
        prevPlaceholder.setPreferredSize(new Dimension(PREV_GRADE_WIDTH, 26));

        row.prevGradeContainer.add(prevPlaceholder, "placeholder");
        row.prevGradeContainer.add(row.prevGradeBox, "select");
        row.prevGradeLayout.show(row.prevGradeContainer, "placeholder");

        gbc.gridx = 5;
        row.panel.add(row.prevGradeContainer, gbc);

        row.removeButton = new JButton("X");
        row.removeButton.setToolTipText("Remove this course");
        row.removeButton.setMargin(new Insets(2, 8, 2, 8));
        row.removeButton.setPreferredSize(new Dimension(REMOVE_WIDTH, 26));
        row.removeButton.setFocusPainted(false);
        row.removeButton.setFont(baseFont.deriveFont(Font.BOLD));

        gbc.gridx = 6;
        row.panel.add(row.removeButton, gbc);

        gbc.gridx = 7;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        row.panel.add(new JLabel(""), gbc);

        row.gradeBox.addActionListener(e -> row.marksLabel.setText(getGradeMarks(row.gradeBox.getSelectedItem())));

        row.retakeCheck.addActionListener(e -> {
            boolean selected = row.retakeCheck.isSelected();
            row.prevGradeBox.setEnabled(selected);
            row.prevGradeLayout.show(row.prevGradeContainer, selected ? "select" : "placeholder");
            if (!selected) {
                row.prevGradeBox.setSelectedIndex(0);
            }
            row.panel.revalidate();
            row.panel.repaint();
        });

        row.removeButton.addActionListener(e -> removeCourseRow(row));

        row.panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, row.panel.getPreferredSize().height));
        return row;
    }

    private JComboBox<String> createComboBox(String[] options, int width) {
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setPreferredSize(new Dimension(width, 26));
        comboBox.setFont(baseFont);
        return comboBox;
    }

    private void removeCourseRow(CourseRow row) {
        if (!courseRows.contains(row) || row.removing) {
            return;
        }

        row.removing = true;
        setRowEnabled(row, false);

        FadePanel panel = row.panel;
        Timer timer = new Timer(20, null);
        timer.addActionListener(e -> {
            float nextAlpha = panel.getAlpha() - 0.15f;
            panel.setAlpha(nextAlpha);
            panel.repaint();
            if (nextAlpha <= 0f) {
                timer.stop();
                coursesListPanel.remove(panel);
                courseRows.remove(row);
                refreshCourseNumbers();
                updateAddButtonState();
                coursesListPanel.revalidate();
                coursesListPanel.repaint();
            }
        });
        timer.start();
    }

    private void setRowEnabled(CourseRow row, boolean enabled) {
        row.creditBox.setEnabled(enabled);
        row.gradeBox.setEnabled(enabled);
        row.retakeCheck.setEnabled(enabled);
        row.prevGradeBox.setEnabled(enabled && row.retakeCheck.isSelected());
        row.removeButton.setEnabled(enabled);
    }

    private void refreshCourseNumbers() {
        for (int i = 0; i < courseRows.size(); i++) {
            courseRows.get(i).indexLabel.setText(String.valueOf(i + 1));
        }
    }

    private void updateAddButtonState() {
        boolean canAdd = courseRows.size() < MAX_COURSE_COUNT;
        addButton.setEnabled(canAdd);
    }

    private void installKeyboardShortcuts() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(event -> {
            if (event.getID() != KeyEvent.KEY_PRESSED) {
                return false;
            }

            int keyCode = event.getKeyCode();
            if (keyCode == KeyEvent.VK_ESCAPE) {
                Window activeWindow = KeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow();
                if (activeWindow != null && activeWindow != this) {
                    activeWindow.dispose();
                    return true;
                }
                if (isFullscreen) {
                    exitFullscreen();
                    return true;
                }
                dispose();
                return true;
            }

            if (keyCode == KeyEvent.VK_F11 || (keyCode == KeyEvent.VK_F && event.getModifiersEx() == 0)) {
                toggleFullscreen();
                return true;
            }

            return false;
        });
    }

    private void toggleFullscreen() {
        if (isFullscreen) {
            exitFullscreen();
        } else {
            enterFullscreen();
        }
    }

    private void enterFullscreen() {
        if (isFullscreen) {
            return;
        }

        windowedBounds = getBounds();
        windowedState = getExtendedState();
        windowedResizable = isResizable();
        isFullscreen = true;

        setMinimumSize(null);

        if (graphicsDevice != null && graphicsDevice.isFullScreenSupported()) {
            dispose();
            setUndecorated(true);
            setResizable(false);
            setVisible(true);
            graphicsDevice.setFullScreenWindow(this);
        } else {
            setExtendedState(JFrame.MAXIMIZED_BOTH);
        }
        updateGradingViewState();
    }

    private void exitFullscreen() {
        if (!isFullscreen) {
            return;
        }

        isFullscreen = false;
        if (graphicsDevice != null && graphicsDevice.getFullScreenWindow() == this) {
            graphicsDevice.setFullScreenWindow(null);
        }

        dispose();
        setUndecorated(false);
        setResizable(windowedResizable);
        setVisible(true);

        if (windowedBounds != null) {
            setBounds(windowedBounds);
        }
        setExtendedState(windowedState);
        setMinimumSize(new Dimension(MIN_WINDOW_WIDTH, MIN_WINDOW_HEIGHT));
        updateGradingViewState();
    }

    private void updateGradingViewState() {
        boolean showEmbedded = shouldShowEmbeddedGrading();
        gradingPanel.setVisible(showEmbedded);
        gradingButton.setVisible(!showEmbedded);
        if (!showEmbedded) {
            gradingButton.setForeground(gradingButtonForeground);
            gradingButton.setBackground(gradingButtonBackground);
            gradingButton.setToolTipText("Open the grading scale");
        }
        centerPanel.revalidate();
        centerPanel.repaint();
    }

    private boolean shouldShowEmbeddedGrading() {
        if (isFullscreen) {
            return true;
        }
        int state = getExtendedState();
        if ((state & JFrame.ICONIFIED) == JFrame.ICONIFIED) {
            return false;
        }
        return getWidth() >= EMBEDDED_GRADING_MIN_WIDTH;
    }

    private boolean isSelectOption(Object selected) {
        return selected == null || String.valueOf(selected).startsWith("Select");
    }

    private int gradeIndex(Object selected) {
        if (selected == null) {
            return -1;
        }
        String text = String.valueOf(selected);
        for (int i = 0; i < GRADE_LETTERS.length; i++) {
            if (GRADE_LETTERS[i].equals(text)) {
                return i;
            }
        }
        return -1;
    }

    private double getGradePoint(Object selected) {
        int index = gradeIndex(selected);
        return index >= 0 ? GRADE_POINTS[index] : 0.0;
    }

    private String getGradeMarks(Object selected) {
        int index = gradeIndex(selected);
        return index >= 0 ? GRADE_MARKS[index] : "--";
    }

    private String getInputText(JTextField field, String placeholder) {
        String text = field.getText().trim();
        if (text.isEmpty() || text.equals(placeholder)) {
            return "";
        }
        return text;
    }

    private void installPlaceholder(JTextField field, String placeholder) {
        Color placeholderColor = new Color(140, 140, 140);
        Color resolvedInputColor = UIManager.getColor("TextField.foreground");
        final Color inputColor = resolvedInputColor != null ? resolvedInputColor : Color.BLACK;

        field.setForeground(placeholderColor);
        field.setText(placeholder);
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(inputColor);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().trim().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(placeholderColor);
                }
            }
        });
    }

    private JLabel createHeaderLabel(String text, Font font, int width) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setPreferredSize(new Dimension(width, 26));
        return label;
    }

    private JButton createFollowButton() {
        JButton button = new JButton("Follow on GitHub");
        button.setFont(baseFont.deriveFont(Font.PLAIN, 11f));
        button.setForeground(new Color(0, 102, 180));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setToolTipText(GITHUB_URL);
        button.addActionListener(e -> openLink(GITHUB_URL));
        return button;
    }

    private void openLink(String url) {
        if (!Desktop.isDesktopSupported()) {
            JOptionPane.showMessageDialog(this, "Opening links is not supported on this system.",
                    "Link Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Unable to open the link.",
                    "Link Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JPanel buildEmbeddedGradingPanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 8));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210)),
                new EmptyBorder(8, 6, 8, 6)));

        JLabel title = new JLabel("Grading Scale");
        title.setFont(sectionFont);
        panel.add(title, BorderLayout.NORTH);

        JPanel table = new JPanel(new GridLayout(GRADE_LETTERS.length + 1, 3, 6, 6));
        table.add(createTableHeaderCell("Letter"));
        table.add(createTableHeaderCell("Point"));
        table.add(createTableHeaderCell("Marks (%)"));

        for (int i = 0; i < GRADE_LETTERS.length; i++) {
            boolean alternate = i % 2 == 0;
            table.add(createTableCell(GRADE_LETTERS[i], alternate));
            table.add(createTableCell(String.format("%.2f", GRADE_POINTS[i]), alternate));
            table.add(createTableCell(GRADE_MARKS[i], alternate));
        }

        panel.add(table, BorderLayout.CENTER);
        panel.setPreferredSize(new Dimension(220, 0));
        return panel;
    }

    private JLabel createTableHeaderCell(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(sectionFont);
        label.setOpaque(true);
        label.setBackground(new Color(235, 235, 235));
        label.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        return label;
    }

    private JLabel createTableCell(String text, boolean alternate) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(baseFont);
        label.setOpaque(true);
        label.setBackground(alternate ? new Color(248, 248, 248) : Color.WHITE);
        label.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        return label;
    }

    private ActionListener countButtonListener() {
        return e -> {
            totalCgpaCount = 0;
            cgpaCreditCount = 0;
            gpaCreditCount = 0;
            gpaTotalCount = 0;

            String completedCreditsValue = getInputText(completedCreditsText, COMPLETED_CREDITS_PLACEHOLDER);
            String currentCgpaValue = getInputText(currentCgpaText, CURRENT_CGPA_PLACEHOLDER);

            if (!currentCgpaValue.isEmpty() && !completedCreditsValue.isEmpty()) {
                try {
                    int cred = Integer.parseInt(completedCreditsValue);
                    double cg = Double.parseDouble(currentCgpaValue);

                    if (cg < 0 || cg > 4) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid CGPA between 0 and 4.",
                                "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    totalCgpaCount += cred * cg;
                    cgpaCreditCount += cred;
                } catch (NumberFormatException ex) {
                    if (completedCreditsValue.contains(".")) {
                        JOptionPane.showMessageDialog(null, "Credits must be a whole number (no decimal points).",
                                "Input Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid input for CGPA or Completed Credits.",
                                "Input Error", JOptionPane.ERROR_MESSAGE);
                    }
                    return;
                }
            }

            boolean allSelected = true;
            boolean anyRetakeSelected = false;

            for (CourseRow row : courseRows) {
                if (row.removing) {
                    continue;
                }

                if (isSelectOption(row.creditBox.getSelectedItem())
                        || isSelectOption(row.gradeBox.getSelectedItem())) {
                    allSelected = false;
                    JOptionPane.showMessageDialog(null,
                            "Please select valid options for all course credits and grades.", "Selection Error",
                            JOptionPane.ERROR_MESSAGE);
                    break;
                }

                if (row.retakeCheck.isSelected()) {
                    anyRetakeSelected = true;
                }

                if (row.retakeCheck.isSelected() && isSelectOption(row.prevGradeBox.getSelectedItem())) {
                    allSelected = false;
                    JOptionPane.showMessageDialog(null,
                            "Please select previous grade for retake course " + row.indexLabel.getText() + ".",
                            "Selection Error", JOptionPane.ERROR_MESSAGE);
                    break;
                }

                int cred = Integer.parseInt((String) row.creditBox.getSelectedItem());
                double newGrade = getGradePoint(row.gradeBox.getSelectedItem());

                if (row.retakeCheck.isSelected()) {
                    double prevGrade = getGradePoint(row.prevGradeBox.getSelectedItem());
                    gpaTotalCount += cred * newGrade;
                    gpaCreditCount += cred;
                    totalCgpaCount += cred * (newGrade - prevGrade);
                } else {
                    gpaTotalCount += cred * newGrade;
                    gpaCreditCount += cred;

                    totalCgpaCount += cred * newGrade;
                    cgpaCreditCount += cred;
                }
            }

            if (allSelected && anyRetakeSelected
                    && (currentCgpaValue.isEmpty() || completedCreditsValue.isEmpty())) {
                JOptionPane.showMessageDialog(null,
                        "For retake calculations, please enter Completed Credits and current CGPA.",
                        "Input Required", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (allSelected) {
                boolean hasGpa = gpaCreditCount > 0;
                boolean hasCgpa = cgpaCreditCount > 0;

                if (!hasGpa && !hasCgpa) {
                    JOptionPane.showMessageDialog(null, "No valid credits entered to calculate GPA/CGPA.",
                            "Calculation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Double gpaResult = hasGpa ? gpaTotalCount / gpaCreditCount : null;
                Double cgpaResult = hasCgpa ? totalCgpaCount / cgpaCreditCount : null;
                showResultsDialog(gpaResult, cgpaResult);
            }
        };
    }

    private void showResultsDialog(Double gpa, Double cgpa) {
        JDialog dialog = new JDialog(this, "Calculation Results", true);
        dialog.setLayout(new BorderLayout(0, 12));

        Font titleFont = sectionFont.deriveFont(Font.BOLD, 16f);
        Font valueFont = baseFont.deriveFont(Font.BOLD, 22f);
        Font labelFont = baseFont.deriveFont(Font.BOLD, 12f);

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBorder(new EmptyBorder(12, 16, 0, 16));

        JLabel titleLabel = new JLabel("Calculation Results");
        titleLabel.setFont(titleFont);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Review your term GPA and updated CGPA");
        subtitleLabel.setFont(baseFont);
        subtitleLabel.setForeground(new Color(90, 90, 90));
        subtitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        headerPanel.add(titleLabel);
        headerPanel.add(Box.createVerticalStrut(4));
        headerPanel.add(subtitleLabel);

        JPanel cardsPanel = new JPanel(new GridLayout(2, 2, 12, 12));
        cardsPanel.setBorder(new EmptyBorder(12, 16, 8, 16));

        cardsPanel.add(createResultCard("Term Credits", (double) gpaCreditCount, labelFont, valueFont));
        cardsPanel.add(createResultCard("Total Credits", (double) cgpaCreditCount, labelFont, valueFont));
        cardsPanel.add(createResultCard("Term GPA", gpa, labelFont, valueFont));
        cardsPanel.add(createResultCard("Updated CGPA", cgpa, labelFont, valueFont));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dialog.dispose());
        buttonPanel.add(closeButton);

        dialog.add(headerPanel, BorderLayout.NORTH);
        dialog.add(cardsPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.pack();
        dialog.setSize(420, dialog.getHeight());
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private JPanel createResultCard(String title, Double value, Font labelFont, Font valueFont) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210)),
                new EmptyBorder(10, 12, 10, 12)));
        card.setBackground(new Color(248, 248, 248));
        card.setOpaque(true);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(labelFont);
        titleLabel.setForeground(new Color(80, 80, 80));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        String displayValue = value == null ? "--"
                : (title.contains("Credits") ? String.format("%d", value.intValue())
                        : String.format("%.2f", value));
        JLabel valueLabel = new JLabel(displayValue);
        valueLabel.setFont(valueFont);
        valueLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(titleLabel);
        card.add(Box.createVerticalStrut(6));
        card.add(valueLabel);
        return card;
    }

    private static class CourseRow {
        FadePanel panel;
        JLabel indexLabel;
        JComboBox<String> creditBox;
        JComboBox<String> gradeBox;
        JLabel marksLabel;
        JCheckBox retakeCheck;
        JComboBox<String> prevGradeBox;
        JPanel prevGradeContainer;
        CardLayout prevGradeLayout;
        JButton removeButton;
        boolean removing;
    }

    private static class FadePanel extends JPanel {
        private float alpha = 1f;

        float getAlpha() {
            return alpha;
        }

        void setAlpha(float alpha) {
            this.alpha = Math.max(0f, Math.min(1f, alpha));
        }

        @Override
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            super.paint(g2);
            g2.dispose();
        }
    }

    private static class CoursesListPanel extends JPanel implements Scrollable {
        @Override
        public Dimension getPreferredScrollableViewportSize() {
            return getPreferredSize();
        }

        @Override
        public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
            return 12;
        }

        @Override
        public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
            return 60;
        }

        @Override
        public boolean getScrollableTracksViewportWidth() {
            return true;
        }

        @Override
        public boolean getScrollableTracksViewportHeight() {
            return false;
        }
    }
}
