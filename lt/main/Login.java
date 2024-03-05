import javax.swing.*;
import javax.swing.text.*;
import org.jdesktop.swingx.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Random;

import org.kordamp.ikonli.fontawesome5.*;
import org.kordamp.ikonli.swing.FontIcon;

public class Login {

    // Diseño principal
    JLayeredPane mainLayered = new JLayeredPane();
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    conexionMSSQL conexion = new conexionMSSQL("sa", "miespositaT02", "CINECLUB");
    public static Connection con;

    // fuente
    Font font = null;

    JPanel mainPanel = new JPanel(new GridBagLayout());
    GridBagConstraints mainGbc = new GridBagConstraints();

    // Botones de la barra de menu cierra, minimiza y maximiza
    JPanel menuBar = new JPanel(new FlowLayout(FlowLayout.RIGHT));

    public static JLabel CustomLabel(FontIcon icon, Color iconColor, Color normalColor, Color hoverColor) {
        icon.setIconColor(iconColor);
        return new JLabel(icon) {
            {
                setBackground(normalColor);

                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        setBackground(hoverColor);
                        icon.setIconColor(normalColor);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        setBackground(normalColor);
                        icon.setIconColor(iconColor);
                    }
                });
            }

            @Override
            protected void paintComponent(Graphics g) {
                setOpaque(false);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(getBackground());
                g2d.fillOval(0, 0, getWidth() - 1, getHeight() - 1);
                super.paintComponent(g);
            }

            @Override
            public boolean contains(int x, int y) {
                Shape shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
                return shape.contains(x, y);
            }
        };
    }

    JLabel close = CustomLabel(FontIcon.of(FontAwesomeSolid.TIMES, 16), new Color(205, 205, 205), new Color(0, 0, 0),
            new Color(234, 51, 33));
    JLabel minimize = CustomLabel(FontIcon.of(FontAwesomeSolid.WINDOW_MINIMIZE, 12), Color.WHITE, new Color(0, 0, 0),
            new Color(255, 190, 46));
    JLabel maximize = CustomLabel(FontIcon.of(FontAwesomeSolid.WINDOW_MAXIMIZE, 12), Color.WHITE, new Color(0, 0, 0),
            new Color(42, 202, 68));

    // Boton de empezar
    RoundedButton empezar = new RoundedButton("Empezar", Color.decode("#732626"), Color.decode("#d99748"),
            Color.decode("#ffffff"), Color.decode("#732626"), 35);

    // Panel login
    public static JPanel CustomPanel(int arc) {
        return new JPanel() {
            Shape shape;

            @Override
            protected void paintComponent(Graphics g) {
                int width = getWidth();
                int height = getHeight();
                Graphics2D graphics = (Graphics2D) g.create();

                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                graphics.setColor(getBackground());
                shape = new RoundRectangle2D.Float(0, 0, width, height, arc, arc);
                graphics.fill(shape);

                graphics.dispose();
            }

            @Override
            public void paint(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setClip(shape);
                super.paint(g2d);
            }
        };
    }

    JPanel loginPanel = CustomPanel(20);
    GridBagConstraints loginPanelGbc = new GridBagConstraints();

    JPanel bienvenida = new JPanel(new GridBagLayout());
    GridBagConstraints bienvenidaGbc = new GridBagConstraints();

    // Bienvenida

    JTextPane bienLabel = new JTextPane();
    JTextPane nosotros = new JTextPane();
    SimpleAttributeSet center = new SimpleAttributeSet();
    StyledDocument doc;

    // Boton de registro
    RoundedButton registro = new RoundedButton("Registrarse", Color.decode("#732626"), Color.decode("#d99748"),
            Color.decode("#d99748"), Color.decode("#d99748"), 35);

    // Panel de login
    JPanel login = new JPanel(new GridBagLayout());
    GridBagConstraints loginGbc = new GridBagConstraints();

    // login

    JLabel face = CustomLabel(FontIcon.of(FontAwesomeBrands.FACEBOOK, 18), Color.decode("#732626"),
            Color.decode("#f7f3f4"), Color.decode("#d99748"));
    JLabel google = CustomLabel(FontIcon.of(FontAwesomeBrands.GOOGLE, 16), Color.decode("#732626"),
            Color.decode("#f7f3f4"), Color.decode("#d99748"));
    JLabel microsoft = CustomLabel(FontIcon.of(FontAwesomeBrands.MICROSOFT, 16), Color.decode("#732626"),
            Color.decode("#f7f3f4"), Color.decode("#d99748"));

    JTextPane inicio = new JTextPane();
    JTextPane cuenta = new JTextPane();

    // Paneles email y password
    JPanel emailPanel = CustomPanel(20);
    GridBagConstraints emailGbc = new GridBagConstraints();

    JLabel email = new JLabel(FontIcon.of(FontAwesomeRegular.ENVELOPE, 16, Color.decode("#c3c0c1")));

    JXTextField emailField = new JXTextField();

    JPanel passwordPanel = CustomPanel(20);
    GridBagConstraints passwordGbc = new GridBagConstraints();

    JLabel password = new JLabel(FontIcon.of(FontAwesomeSolid.LOCK, 16, Color.decode("#c3c0c1")));

    PlaceHolverPasword passwordField = new PlaceHolverPasword("Contraseña");

    // Boton de inicio de sesion
    RoundedButton iniciar = new RoundedButton("Iniciar Sesion", Color.decode("#732626"), Color.decode("#d99748"),
            Color.decode("#ffffff"), Color.decode("#732626"), 35);

    // Panel crear cuenta
    JPanel crearPanel = CustomPanel(20);
    GridBagConstraints crearGbc = new GridBagConstraints();

    JPanel bienvenidaCine = new JPanel(new GridBagLayout());
    GridBagConstraints bienvenidaCineGbc = new GridBagConstraints();

    JTextPane bienCine = new JTextPane();
    JTextPane datos = new JTextPane();

    // Boton de inicio de sesion
    RoundedButton inicioSecion = new RoundedButton("Iniciar Sesion", Color.decode("#732626"), Color.decode("#d99748"),
            Color.decode("#d99748"), Color.decode("#d99748"), 35);

    // Panel crear cuenta
    JPanel crearCuenta = new JPanel(new GridBagLayout());
    GridBagConstraints crearCuentaGbc = new GridBagConstraints();

    JLabel face2 = CustomLabel(FontIcon.of(FontAwesomeBrands.FACEBOOK, 18), Color.decode("#732626"),
            Color.decode("#f7f3f4"), Color.decode("#d99748"));
    JLabel google2 = CustomLabel(FontIcon.of(FontAwesomeBrands.GOOGLE, 16), Color.decode("#732626"),
            Color.decode("#f7f3f4"), Color.decode("#d99748"));
    JLabel microsoft2 = CustomLabel(FontIcon.of(FontAwesomeBrands.MICROSOFT, 16), Color.decode("#732626"),
            Color.decode("#f7f3f4"), Color.decode("#d99748"));

    JTextPane crear = new JTextPane();
    JTextPane usar = new JTextPane();

    // Paneles inputs
    JPanel emailPanelC = CustomPanel(20);
    GridBagConstraints emailCGbc = new GridBagConstraints();

    JLabel emailC = new JLabel(FontIcon.of(FontAwesomeRegular.ENVELOPE, 16, Color.decode("#c3c0c1")));

    JXTextField emailFieldC = new JXTextField();

    JPanel passwordPanelC = CustomPanel(20);
    GridBagConstraints passwordCGbc = new GridBagConstraints();

    JLabel passwordC = new JLabel(FontIcon.of(FontAwesomeSolid.LOCK, 16, Color.decode("#c3c0c1")));

    PlaceHolverPasword passwordFieldC = new PlaceHolverPasword("Contraseña");

    JPanel nombreC = CustomPanel(20);
    GridBagConstraints nombreCGbc = new GridBagConstraints();

    JLabel nombre = new JLabel(FontIcon.of(FontAwesomeRegular.USER, 16, Color.decode("#c3c0c1")));

    JXTextField nombreField = new JXTextField();

    JPanel apellidoC = CustomPanel(20);
    GridBagConstraints apellidoCGbc = new GridBagConstraints();

    JLabel apellido = new JLabel(FontIcon.of(FontAwesomeRegular.USER, 16, Color.decode("#c3c0c1")));

    JXTextField apellidoField = new JXTextField();

    // Boton de registro
    RoundedButton registrarse = new RoundedButton("Registrarse", Color.decode("#732626"), Color.decode("#d99748"),
            Color.decode("#ffffff"), Color.decode("#732626"), 35);

    Point p = new Point();

    Timer timer;

    Random random = new Random();

    static ResultSet rs;

    public Login() {

        // fuente
        try {
            InputStream is = Login.class.getResourceAsStream("Fonts/Montserrat-Regular.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        // Panel principal
        mainPanel.setBackground(new Color(0, 0, 0));

        // Panel de la barra de menu
        menuBar.setBackground(new Color(0, 0, 0));

        // Botones de la barra de menu
        minimize.setPreferredSize(new Dimension(30, 30));
        menuBar.add(minimize);
        maximize.setPreferredSize(new Dimension(30, 30));
        menuBar.add(maximize);
        close.setPreferredSize(new Dimension(30, 30));
        menuBar.add(close);

        mainGbc.gridx = 0;
        mainGbc.gridy = 0;
        mainGbc.weightx = 1;
        mainGbc.weighty = 0;
        mainGbc.gridwidth = GridBagConstraints.REMAINDER;
        mainGbc.fill = GridBagConstraints.HORIZONTAL;
        mainGbc.anchor = GridBagConstraints.NORTH;
        mainPanel.add(menuBar, mainGbc);

        // Panel de login
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setBackground(new Color(255, 255, 255));
        loginPanel.setPreferredSize(new Dimension(900, 600));

        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER); // Alineacion al centro

        // Panel de bienvenida

        bienvenida.setBackground(Color.decode("#732626"));
        bienvenida.setPreferredSize(new Dimension(360, 600));

        // Label de bienvenida
        doc = bienLabel.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        bienLabel.setFont(font.deriveFont(Font.BOLD, 40));
        bienLabel.setForeground(Color.decode("#ffffff"));
        bienLabel.setText("\"Bienvenido Nuevamente\"");
        bienLabel.setEditable(false);
        bienLabel.setBackground(null);
        bienLabel.setBorder(null);
        bienLabel.setCaret(null);
        bienvenidaGbc.gridx = 0;
        bienvenidaGbc.gridy = 0;
        bienvenida.add(bienLabel, bienvenidaGbc);

        doc = nosotros.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        nosotros.setFont(font.deriveFont(Font.PLAIN, 18));
        nosotros.setForeground(Color.decode("#a57337"));
        nosotros.setText("Para unirte a nosotros, registrate con tus datos");
        nosotros.setEditable(false);
        nosotros.setBackground(null);
        nosotros.setBorder(null);
        nosotros.setCaret(null);

        bienvenidaGbc.gridx = 0;
        bienvenidaGbc.gridy = 1;
        bienvenidaGbc.weightx = 1;
        bienvenidaGbc.insets = new Insets(20, 0, 0, 0);
        bienvenidaGbc.fill = GridBagConstraints.HORIZONTAL;
        bienvenida.add(nosotros, bienvenidaGbc);

        // Boton de registro
        registro.setPreferredSize(new Dimension(200, 8));
        registro.setMargin(new Insets(7, 15, 7, 15));
        registro.setFont(font.deriveFont(Font.PLAIN, 14));
        bienvenidaGbc.gridx = 0;
        bienvenidaGbc.gridy = 2;
        bienvenidaGbc.weighty = 0;
        bienvenidaGbc.fill = GridBagConstraints.NONE;

        bienvenidaGbc.insets = new Insets(20, 0, 0, 0);
        bienvenida.add(registro, bienvenidaGbc);

        loginPanelGbc.gridx = 0;
        loginPanelGbc.gridy = 0;
        loginPanel.add(bienvenida, loginPanelGbc);

        // Panel login
        login.setBackground(new Color(255, 255, 255));
        login.setPreferredSize(new Dimension(540, 600));
        loginPanelGbc.gridx = 1;
        loginPanelGbc.gridy = 0;

        // Labels de login
        doc = inicio.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        inicio.setFont(font.deriveFont(Font.BOLD, 40));
        inicio.setForeground(Color.decode("#000000"));
        inicio.setText("Iniciar Sesion");
        inicio.setEditable(false);
        inicio.setBackground(null);
        inicio.setBorder(null);
        inicio.setCaret(null);
        loginGbc.gridx = 0;
        loginGbc.gridy = 0;
        loginGbc.gridwidth = 1;
        login.add(inicio, loginGbc);

        face.setPreferredSize(new Dimension(46, 46));
        loginGbc.anchor = GridBagConstraints.EAST;
        loginGbc.gridy = 1;
        loginGbc.gridx = 0;
        loginGbc.insets = new Insets(20, 0, 0, 0);
        login.add(face, loginGbc);

        google.setPreferredSize(new Dimension(46, 46));
        loginGbc.anchor = GridBagConstraints.CENTER;
        login.add(google, loginGbc);

        microsoft.setPreferredSize(new Dimension(46, 46));
        loginGbc.anchor = GridBagConstraints.WEST;
        login.add(microsoft, loginGbc);

        doc = cuenta.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        cuenta.setFont(font.deriveFont(Font.PLAIN, 18));
        cuenta.setForeground(Color.decode("#000000"));
        cuenta.setText("o usa tu cuenta");
        cuenta.setEditable(false);
        cuenta.setBackground(null);
        cuenta.setBorder(null);
        cuenta.setCaret(null);
        loginGbc.anchor = GridBagConstraints.CENTER;
        loginGbc.gridx = 0;
        loginGbc.gridy = 2;
        loginGbc.gridwidth = 1;
        loginGbc.insets = new Insets(10, 0, 15, 0);
        login.add(cuenta, loginGbc);

        // Paneles email y password
        emailPanel.setBackground(Color.decode("#f7f3f4"));
        emailPanel.setPreferredSize(new Dimension(300, 36));
        emailPanel.setLayout(new GridBagLayout());
        emailGbc.gridx = 0;
        emailGbc.gridy = 0;
        emailGbc.anchor = GridBagConstraints.CENTER;
        emailPanel.add(email, emailGbc);

        emailField.setPrompt("Correo Electronico");
        emailField.setPreferredSize(new Dimension(250, 36));
        emailField.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        emailField.setBackground(Color.decode("#f7f3f4"));
        emailField.setFont(font.deriveFont(Font.PLAIN, 12));
        emailGbc.gridx = 1;
        emailPanel.add(emailField, emailGbc);
        loginGbc.gridx = 0;
        loginGbc.gridy = 3;
        login.add(emailPanel, loginGbc);

        passwordPanel.setBackground(Color.decode("#f7f3f4"));
        passwordPanel.setPreferredSize(new Dimension(300, 36));
        passwordPanel.setLayout(new GridBagLayout());
        passwordGbc.gridx = 0;
        passwordGbc.gridy = 0;
        passwordGbc.anchor = GridBagConstraints.CENTER;
        passwordPanel.add(password, passwordGbc);

        passwordField.setPreferredSize(new Dimension(250, 36));
        passwordField.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        passwordField.setBackground(Color.decode("#f7f3f4"));
        passwordGbc.gridx = 1;
        passwordPanel.add(passwordField, passwordGbc);
        loginGbc.gridx = 0;
        loginGbc.gridy = 4;
        login.add(passwordPanel, loginGbc);

        // Boton de inicio de sesion
        iniciar.setPreferredSize(new Dimension(130, 36));
        iniciar.setMargin(new Insets(7, 15, 7, 15));
        iniciar.setFont(font.deriveFont(Font.PLAIN, 14));
        loginGbc.gridx = 0;
        loginGbc.gridy = 5;
        login.add(iniciar, loginGbc);
        loginPanel.add(login, loginPanelGbc);

        // Panel de crear cuenta
        crearPanel.setLayout(new GridBagLayout());
        crearPanel.setBackground(new Color(255, 255, 255));
        crearPanel.setPreferredSize(new Dimension(900, 600));

        // Panel de bienvenida Cine
        bienvenidaCine.setBackground(Color.decode("#732626"));
        bienvenidaCine.setPreferredSize(new Dimension(360, 600));

        // Label de bienvenida Cine
        doc = bienCine.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        bienCine.setFont(font.deriveFont(Font.BOLD, 40));
        bienCine.setForeground(Color.decode("#ffffff"));
        bienCine.setText("\"Bienvenido a CineClub\"");
        bienCine.setEditable(false);
        bienCine.setBackground(null);
        bienCine.setBorder(null);
        bienCine.setCaret(null);
        bienvenidaCineGbc.gridx = 0;
        bienvenidaCineGbc.gridy = 0;
        bienvenidaCine.add(bienCine, bienvenidaCineGbc);

        doc = datos.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        datos.setFont(font.deriveFont(Font.PLAIN, 18));
        datos.setForeground(Color.decode("#a57337"));
        datos.setText("Para unirte a nosotros, registrate con tus datos");
        datos.setEditable(false);
        datos.setBackground(null);
        datos.setBorder(null);
        datos.setCaret(null);
        bienvenidaCineGbc.gridx = 0;
        bienvenidaCineGbc.gridy = 1;
        bienvenidaCineGbc.weightx = 1;
        bienvenidaCineGbc.insets = new Insets(20, 0, 0, 0);
        bienvenidaCineGbc.fill = GridBagConstraints.HORIZONTAL;
        bienvenidaCine.add(datos, bienvenidaCineGbc);

        // Boton de inicio de sesion
        inicioSecion.setPreferredSize(new Dimension(200, 8));
        inicioSecion.setMargin(new Insets(7, 15, 7, 15));
        inicioSecion.setFont(font.deriveFont(Font.PLAIN, 14));
        bienvenidaCineGbc.gridx = 0;
        bienvenidaCineGbc.gridy = 2;
        bienvenidaCineGbc.weighty = 0;
        bienvenidaCineGbc.fill = GridBagConstraints.NONE;
        bienvenidaCineGbc.insets = new Insets(20, 0, 0, 0);
        bienvenidaCine.add(inicioSecion, bienvenidaCineGbc);

        crearGbc.gridx = 0;
        crearGbc.gridy = 0;
        crearPanel.add(bienvenidaCine, crearGbc);

        // Panel crear cuenta
        crearCuenta.setBackground(new Color(255, 255, 255));
        crearCuenta.setPreferredSize(new Dimension(540, 600));
        bienvenidaCineGbc.gridx = 1;
        bienvenidaCineGbc.gridy = 0;

        // Labels de crear cuenta

        doc = crear.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        crear.setFont(font.deriveFont(Font.BOLD, 40));
        crear.setForeground(Color.decode("#000000"));
        crear.setText("Crear una Cuenta");
        crear.setEditable(false);
        crear.setBackground(null);
        crear.setBorder(null);
        crear.setCaret(null);
        crearCuentaGbc.gridx = 0;
        crearCuentaGbc.gridy = 0;
        crearCuentaGbc.gridwidth = 1;
        crearCuenta.add(crear, crearCuentaGbc);

        face2.setPreferredSize(new Dimension(46, 46));
        crearCuentaGbc.anchor = GridBagConstraints.EAST;
        crearCuentaGbc.gridy = 1;
        crearCuentaGbc.gridx = 0;
        crearCuentaGbc.insets = new Insets(20, 0, 0, 0);
        crearCuenta.add(face2, crearCuentaGbc);

        google2.setPreferredSize(new Dimension(46, 46));
        crearCuentaGbc.anchor = GridBagConstraints.CENTER;
        crearCuenta.add(google2, crearCuentaGbc);

        microsoft2.setPreferredSize(new Dimension(46, 46));
        crearCuentaGbc.anchor = GridBagConstraints.WEST;
        crearCuenta.add(microsoft2, crearCuentaGbc);

        doc = usar.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        usar.setFont(font.deriveFont(Font.PLAIN, 18));
        usar.setForeground(Color.decode("#000000"));
        usar.setText("o usa tu correo para registrarte");
        usar.setEditable(false);
        usar.setBackground(null);
        usar.setBorder(null);
        usar.setCaret(null);
        crearCuentaGbc.anchor = GridBagConstraints.CENTER;
        crearCuentaGbc.gridx = 0;
        crearCuentaGbc.gridy = 2;
        crearCuentaGbc.gridwidth = 1;
        crearCuentaGbc.insets = new Insets(10, 0, 15, 0);
        crearCuenta.add(usar, crearCuentaGbc);

        // Paneles inputs
        nombreC.setBackground(Color.decode("#f7f3f4"));
        nombreC.setPreferredSize(new Dimension(300, 36));
        nombreC.setLayout(new GridBagLayout());
        nombreCGbc.gridx = 0;
        nombreCGbc.gridy = 0;
        nombreCGbc.anchor = GridBagConstraints.CENTER;
        nombreC.add(nombre, nombreCGbc);

        nombreField.setPrompt("Nombre");
        nombreField.setPreferredSize(new Dimension(250, 36));
        nombreField.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        nombreField.setBackground(Color.decode("#f7f3f4"));
        nombreField.setFont(font.deriveFont(Font.PLAIN, 12));
        nombreCGbc.gridx = 1;
        nombreC.add(nombreField, nombreCGbc);
        crearCuentaGbc.gridx = 0;
        crearCuentaGbc.gridy = 3;
        crearCuenta.add(nombreC, crearCuentaGbc);

        apellidoC.setBackground(Color.decode("#f7f3f4"));
        apellidoC.setPreferredSize(new Dimension(300, 36));
        apellidoC.setLayout(new GridBagLayout());
        apellidoCGbc.gridx = 0;
        apellidoCGbc.gridy = 0;
        apellidoCGbc.anchor = GridBagConstraints.CENTER;
        apellidoC.add(apellido, apellidoCGbc);

        apellidoField.setPrompt("Apellido");
        apellidoField.setPreferredSize(new Dimension(250, 36));
        apellidoField.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        apellidoField.setBackground(Color.decode("#f7f3f4"));
        apellidoField.setFont(font.deriveFont(Font.PLAIN, 12));
        apellidoCGbc.gridx = 1;
        apellidoC.add(apellidoField, apellidoCGbc);
        crearCuentaGbc.gridx = 0;
        crearCuentaGbc.gridy = 4;
        crearCuenta.add(apellidoC, crearCuentaGbc);

        emailPanelC.setBackground(Color.decode("#f7f3f4"));
        emailPanelC.setPreferredSize(new Dimension(300, 36));
        emailPanelC.setLayout(new GridBagLayout());
        emailCGbc.gridx = 0;
        emailCGbc.gridy = 0;
        emailCGbc.anchor = GridBagConstraints.CENTER;
        emailPanelC.add(emailC, emailCGbc);

        emailFieldC.setPrompt("Correo Electronico");
        emailFieldC.setPreferredSize(new Dimension(250, 36));
        emailFieldC.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        emailFieldC.setBackground(Color.decode("#f7f3f4"));
        emailFieldC.setFont(font.deriveFont(Font.PLAIN, 12));
        emailCGbc.gridx = 1;
        emailPanelC.add(emailFieldC, emailCGbc);
        crearCuentaGbc.gridx = 0;
        crearCuentaGbc.gridy = 5;
        crearCuenta.add(emailPanelC, crearCuentaGbc);

        passwordPanelC.setBackground(Color.decode("#f7f3f4"));
        passwordPanelC.setPreferredSize(new Dimension(300, 36));
        passwordPanelC.setLayout(new GridBagLayout());
        passwordCGbc.gridx = 0;
        passwordCGbc.gridy = 0;
        passwordCGbc.anchor = GridBagConstraints.CENTER;
        passwordPanelC.add(passwordC, passwordCGbc);

        passwordFieldC.setPreferredSize(new Dimension(250, 36));
        passwordFieldC.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        passwordFieldC.setBackground(Color.decode("#f7f3f4"));
        passwordCGbc.gridx = 1;
        passwordPanelC.add(passwordFieldC, passwordCGbc);
        crearCuentaGbc.gridx = 0;
        crearCuentaGbc.gridy = 6;
        crearCuenta.add(passwordPanelC, crearCuentaGbc);

        // Boton de registro
        registrarse.setPreferredSize(new Dimension(130, 36));
        registrarse.setMargin(new Insets(7, 15, 7, 15));
        registrarse.setFont(font.deriveFont(Font.PLAIN, 14));
        crearCuentaGbc.gridx = 0;
        crearCuentaGbc.gridy = 7;
        crearCuenta.add(registrarse, crearCuentaGbc);

        crearGbc.gridx = 1;
        crearGbc.gridy = 0;
        crearPanel.add(crearCuenta, crearGbc);

        empezar.setPreferredSize(new Dimension(200, 50));
        empezar.setMargin(new Insets(7, 15, 7, 15));
        empezar.setFont(font.deriveFont(Font.PLAIN, 14));

        mainGbc.gridx = 1;
        mainGbc.gridy = 0;
        mainGbc.weightx = 1;
        mainGbc.weighty = 1;
        mainGbc.gridwidth = GridBagConstraints.REMAINDER;
        mainGbc.fill = GridBagConstraints.NONE;
        mainGbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(empezar, mainGbc);
        mainPanel.setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight());
        mainLayered.add(mainPanel, Integer.valueOf(0));

        // Conexion a la base de datos
        try {
            con = conexion.conectar();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM PELICULA";
            rs = stmt.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }

        close.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        minimize.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
                frame.setState(Frame.ICONIFIED);
            }
        });

        maximize.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
                if (frame.getExtendedState() == JFrame.MAXIMIZED_BOTH) {
                    frame.setExtendedState(JFrame.NORMAL);
                } else {
                    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                }
            }
        });

        menuBar.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
                if (frame.getExtendedState() == JFrame.MAXIMIZED_BOTH) {
                    frame.setExtendedState(JFrame.NORMAL);
                }
                frame.setLocation(e.getXOnScreen() - p.x, e.getYOnScreen() - p.y);
            }
        });

        mainPanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
                    if (rs.next()) {
                        String path = rs.getString("CARATULA");
                        imagen img = new imagen(path);
                        ImageIcon ico = new ImageIcon(img.recortePersonalizado());
                        JLabel label = new JLabel(ico);
                        int x = e.getX() - ico.getIconWidth() / 2;
                        int y = e.getY() - ico.getIconHeight() / 2;
                        label.setBounds(x, y, ico.getIconWidth(), ico.getIconHeight());
                        mainPanel.add(label);
                        mainPanel.repaint();
                        Random rand = new Random();
                        int dx = rand.nextInt(21) - 10;
                        int dy = rand.nextInt(21) - 10;
                        new Timer(1, new ActionListener() {
                            int i = 0;

                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                label.setLocation(label.getX() + dx, label.getY() + dy);
                                mainPanel.repaint();
                                i++;
                                if (i > 15) {
                                    ((Timer) evt.getSource()).stop();
                                }
                            }
                        }).start();
                    } else {
                        rs.beforeFirst();
                    }

                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });

        mainPanel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                Component[] components = mainPanel.getComponents();
                for (Component component : components) {
                    if (component instanceof JLabel) {
                        JLabel label = (JLabel) component;
                        if (label.getBounds().contains(e.getPoint())) {
                            int dirx = e.getX() - p.x;
                            int diry = e.getY() - p.y;
                            if (timer != null && timer.isRunning()) {
                                timer.stop();
                            }
                            timer = new Timer(1, new ActionListener() {
                                int i = 0;

                                @Override
                                public void actionPerformed(ActionEvent event) {
                                    label.setLocation(label.getX() + dirx, label.getY() + diry);
                                    i++;
                                    if (i > 10) {
                                        ((Timer) event.getSource()).stop();
                                    }
                                }
                            });
                            timer.start();
                        }
                    }
                }
                p = e.getPoint();
            }
        });

        empezar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel blackJPanel = new JPanel();
                blackJPanel.setBackground(new Color(0, 0, 0));
                int x = ((int) screenSize.getWidth() - 900) / 2;
                int y = ((int) screenSize.getHeight() - 600) / 2;
                // blackJPanel.setBounds(x, y, 900, 600);
                loginPanel.setBounds(x, y, 900, 600);
                crearPanel.setBounds(x, y - 900, 900, 600);

                // mainLayered.add(blackJPanel,Integer.valueOf(1));
                mainLayered.add(loginPanel, Integer.valueOf(2));
                mainLayered.add(crearPanel, Integer.valueOf(2));
                mainLayered.revalidate();
                mainLayered.repaint();
            }
        });

        registro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Timer timer = new Timer(0, null);
                timer.addActionListener(new ActionListener() {
                    int pixelsMoved = 0;

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Point p = loginPanel.getLocation();
                        Point p2 = crearPanel.getLocation();
                        if (pixelsMoved < ((int) screenSize.getHeight() / 50) + 8) {
                            loginPanel.setLocation(p.x, p.y - 30);
                            loginPanel.revalidate();
                            loginPanel.repaint();
                            crearPanel.setLocation(p2.x, p2.y + 30);
                            crearPanel.revalidate();
                            crearPanel.repaint();
                            pixelsMoved++;
                        } else {
                            ((Timer) e.getSource()).stop();
                        }
                    }
                });
                timer.start();
            }
        });

        inicioSecion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Timer timer = new Timer(0, null);
                timer.addActionListener(new ActionListener() {
                    int pixelsMoved = 0;

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Point p = loginPanel.getLocation();
                        Point p2 = crearPanel.getLocation();
                        if (pixelsMoved < ((int) screenSize.getHeight() / 50) + 8) {
                            loginPanel.setLocation(p.x, p.y + 30);
                            loginPanel.revalidate();
                            loginPanel.repaint();
                            crearPanel.setLocation(p2.x, p2.y - 30);
                            crearPanel.revalidate();
                            crearPanel.repaint();
                            pixelsMoved++;
                        } else {
                            ((Timer) e.getSource()).stop();
                        }
                    }
                });
                timer.start();
            }
        });

        registrarse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pasword = new String(passwordFieldC.getPassword());
                String email = emailFieldC.getText();
                String vereficar = "SELECT CORREO_CLI FROM CLIENTE WHERE CORREO_CLI = ?";
                try {
                    PreparedStatement statementV = con.prepareStatement(vereficar);
                    statementV.setString(1, email);
                    ResultSet rs = statementV.executeQuery();
                    if (emailFieldC.getText().equals("") || pasword.equals("") || nombreField.getText().equals("")
                            || apellidoField.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Por favor llene todos los campos");
                    } else if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "El correo ya esta registrado");
                    } else {
                        try {
                            String query = "INSERT INTO CLIENTE (NOM_CLI,APE_CLI,CORREO_CLI,CONTRA_CLI) VALUES (?,?,?,CONVERT(varbinary,?))";
                            PreparedStatement statement = con.prepareStatement(query);
                            statement.setString(1, nombreField.getText());
                            statement.setString(2, apellidoField.getText());
                            statement.setString(3, emailFieldC.getText());
                            statement.setString(4, pasword);
                            int rowsInserted = statement.executeUpdate();
                            if (rowsInserted > 0) {
                                JOptionPane.showMessageDialog(null, "Usuario registrado con exito");
                                Timer timer = new Timer(0, null);
                                timer.addActionListener(new ActionListener() {
                                    int pixelsMoved = 0;

                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        Point p = loginPanel.getLocation();
                                        Point p2 = crearPanel.getLocation();
                                        if (pixelsMoved < ((int) screenSize.getHeight() / 50) + 8) {
                                            loginPanel.setLocation(p.x, p.y + 30);
                                            loginPanel.revalidate();
                                            loginPanel.repaint();
                                            crearPanel.setLocation(p2.x, p2.y - 30);
                                            crearPanel.revalidate();
                                            crearPanel.repaint();
                                            pixelsMoved++;
                                        } else {
                                            ((Timer) e.getSource()).stop();
                                        }
                                    }
                                });
                                timer.start();

                            }
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
iniciar.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String pasword = new String(passwordField.getPassword());
        String email = emailField.getText();
        String vereficar = "SELECT CORREO_CLI FROM CLIENTE WHERE CORREO_CLI = ?";

        try {
            Boolean admi = false;
            // Verificar si el usuario es el administrador
            if (email.equals("a") && pasword.equals("a")) {
                
                admi = true;
            }

            PreparedStatement statementV = con.prepareStatement(vereficar);
            statementV.setString(1, email);
            ResultSet rs = statementV.executeQuery();
            if (emailField.getText().equals("") || pasword.equals("")) {
                JOptionPane.showMessageDialog(null, "Por favor llene todos los campos");
            } else if (rs.next()) {

                String query = "SELECT * FROM CLIENTE WHERE CORREO_CLI = ? AND CONTRA_CLI = CONVERT(varbinary,?)";
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1, emailField.getText());
                statement.setString(2, pasword);
                ResultSet rs2 = statement.executeQuery();
                if (rs2.next()) {
                    Cliente cliente = new Cliente(rs2.getString("NOM_CLI"), rs2.getString("APE_CLI"), email,admi);
                    Peliculas.usuario=rs2.getString("NOM_CLI") + " " + rs2.getString("APE_CLI");
                    JOptionPane.showMessageDialog(null, "Bienvenido " + rs2.getString("NOM_CLI") + " "
                            + rs2.getString("APE_CLI"));
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(mainLayered);
                    frame.dispose();
                    JFrame landingPage = new JFrame();
                    landingPage.setContentPane(new LandingPage(cliente).mainPanel);
                    landingPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    landingPage.pack();
                    landingPage.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    landingPage.setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(null, "Correo o contraseña incorrectos");
                }
            } else {
                JOptionPane.showMessageDialog(null, "El correo no esta registrado");
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

});
    
    
    
    }

}
