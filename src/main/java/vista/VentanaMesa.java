
package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author pasot
 */

public class VentanaMesa extends JFrame{
    
    private final JPanel panelMesaIzq;
    private JPanel panelMesaCentro;
    private final JPanel panelMesaDer; 
    private VentanaAgregar ventanaAgregar;
    private VentanaPagar ventanaPagar;
    private final JButton btnAgregar;
    private final JButton btnCancelar;
    public JList mesasJList;
    public DefaultListModel modelo;
    public JMenuItem i1;
    private JTable tabla;
    private TitledBorder bordePanelCentro;
    private BufferedReader bufferedReader;
    int propinaSugerida = 0;
    int subTotal; 
    private JTableHeader header;
    private String[] nombreColumnas = new String[]{"CODIGO","NOMBRE","CANTIDAD","PRECIO"};
    int total = 0;
    private JScrollPane scroll;
    private final JLabel totaltxt;
    private final JLabel subtotaltxt;
    private JCheckBox incluirPropina;
    private final JLabel totalLabel;
    private final JLabel propinaLabel;
    private JLabel subtotalLabel;
    private BufferedWriter bw;
    
    public VentanaMesa(){
        try {
            setIconImage(ImageIO.read(new File("res/Pizza-icon.png")));
        } catch (Exception e) {
        }
        
        JMenu menu, submenu;
        JMenuItem itemInventario, itemSalir, i3, i4, i5;
        JMenuBar menuBar = new JMenuBar();
        menu = new JMenu("Archivo");
        submenu = new JMenu("Submenu");
        itemInventario = new JMenuItem("Inventario");  
        itemSalir = new JMenuItem("Salir"); 
        menu.add(itemInventario);
        menu.add(itemSalir); 
        menuBar.add(menu);  
        this.setJMenuBar(menuBar);
        
        itemInventario.addActionListener(
                (ActionEvent e) -> {
                    VentanaInventario ventanaInventario = new VentanaInventario();
                    ventanaInventario.setVisible(true);
                }
        );
        itemSalir.addActionListener(
                (ActionEvent e) -> {
                    System.exit(0);
                }
        );

        
 /*PANEL IZQUIERDO*/       
        panelMesaIzq = new JPanel();
        modelo = new DefaultListModel();
        modelo.ensureCapacity(100);
        for (int i = 1; i < 10; i++) {
          modelo.addElement("Mesa "+i);
        }
        mesasJList = new JList(modelo);
        mesasJList.setMinimumSize(new Dimension(110, 500));
        mesasJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(mesasJList);
        panelMesaIzq.add(scrollPane, BorderLayout.CENTER);
        bordePanelCentro = BorderFactory.createTitledBorder("Mesa 1");
        
        MouseListener mouseListener = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            incluirPropina.setSelected(false);
          JList theList = (JList) mouseEvent.getSource();
          if (mouseEvent.getClickCount() == 1) {
            int index = theList.locationToIndex(mouseEvent.getPoint())+1;
            if (index >= 0) {
                
                String RUTA_MESA = "data\\mesa"+index+".txt";
                try {
                    
                    File archivo = new File(RUTA_MESA);

                    if(!archivo.exists()) {
                        bw = new BufferedWriter(new FileWriter(archivo));
                        
                    } else {
                        
                        
                    }
                bw.close();
                } catch (Exception e) {
                }
                
                
                
                bordePanelCentro.setTitle("Mesa "+index);
                panelMesaCentro.setBorder(bordePanelCentro);
                

                try {

                tabla = new JTable();
                panelMesaCentro.removeAll();  
                bufferedReader = new BufferedReader(new FileReader(RUTA_MESA));


                List<String[]> elements = new ArrayList<>();
                String linea;
                while ((linea = bufferedReader.readLine()) != null) {
                    String [] quitarComa = linea.split(",");
                    elements.add(quitarComa);
                }
                bufferedReader.close();
                
                scroll = new JScrollPane(tabla);
                header = tabla.getTableHeader();
                
                panelMesaCentro.add(header,BorderLayout.PAGE_START);
                panelMesaCentro.add(tabla,BorderLayout.CENTER);
                
                Object[][] contenido = new Object[elements.size()][4];
                for (int i = 0; i < elements.size(); i++) {
                    contenido[i][0]= elements.get(i)[0];
                    contenido[i][1]= elements.get(i)[1];
                    contenido[i][2]= elements.get(i)[2];
                    contenido[i][3]= elements.get(i)[3];
                }
                
                tabla.setModel(new DefaultTableModel(contenido,nombreColumnas));

                calcularTotal(contenido);

                } catch (Exception e) {
                }

                panelMesaCentro.add(new JScrollPane(tabla));

                }
              }
            }
        };
    mesasJList.addMouseListener(mouseListener);
        
        
/*PANEL CENTRO*/        
        panelMesaCentro = new JPanel();
        panelMesaCentro.setPreferredSize(new Dimension(480, 400));
        bordePanelCentro.setTitleColor( Color.BLUE );
        panelMesaCentro.setBorder(bordePanelCentro);
        JLabel mensajeInicio = new JLabel("Bienvenido, selecciona una mesa");
        mensajeInicio.setFont( new Font( "Helvetica", Font.BOLD, 18 ) );
        panelMesaCentro.add(mensajeInicio);
       
/*PANEL DERECHO*/
        panelMesaDer = new JPanel();
        panelMesaDer.setLayout(new GridLayout(3, 1));
        
        setLayout(new GridLayout(3, 1));
        JPanel botones = new JPanel();
        botones.setLayout(new GridLayout(2, 1));
        btnAgregar = new JButton("Agregar");
        btnCancelar = new JButton("Cancelar");
        botones.add(btnAgregar);
        
        
        botones.add(btnCancelar);
        panelMesaDer.add(botones);
        TitledBorder borde = BorderFactory.createTitledBorder("Total a Pagar");
        borde.setTitleColor( Color.BLUE );
        
        JPanel totalPagarPanel = new JPanel();
        totalPagarPanel.setLayout(new GridLayout(3, 3,10,4));
        totalPagarPanel.setPreferredSize(new Dimension(180,10));
        panelMesaDer.add(totalPagarPanel);
        totalPagarPanel.setBorder(borde);
        
        btnAgregar.addActionListener(
                (ActionEvent e) -> {
                    ventanaAgregar = new VentanaAgregar();
                    ventanaAgregar.setVisible(true);
                    
                }
        );
        
        btnCancelar.addActionListener(
                (ActionEvent e) -> {
                    ventanaPagar = new VentanaPagar();
                    ventanaPagar.setVisible(true);
                    
                }
        );
        totaltxt = new JLabel("Total: ");
        subtotaltxt = new JLabel("Subtotal: ");
        incluirPropina = new JCheckBox("Propina?",false);
        
        incluirPropina.addActionListener(
                (ActionEvent e) -> {
                    if (!incluirPropina.isSelected()) {
                        subtotalLabel.setText("$ "+total);
                    }else{
                        subtotalLabel.setText("$ "+subTotal);
                    }
                }
        );
        totalLabel = new JLabel("$ "+total);
        propinaLabel = new JLabel("+ $"+propinaSugerida);
        subtotalLabel = new JLabel("$ "+total);
        
        totalPagarPanel.add(totaltxt);
        totalPagarPanel.add(totalLabel);
        totalPagarPanel.add(incluirPropina);
        totalPagarPanel.add(propinaLabel);
        totalPagarPanel.add(subtotaltxt);
        totalPagarPanel.add(subtotalLabel);

          
        this.getContentPane().setLayout(new BorderLayout());
        
        
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelMesaIzq, panelMesaCentro);  
        this.getContentPane().add(splitPane,BorderLayout.WEST);
        this.add(panelMesaDer, BorderLayout.EAST);
        this.setSize(760, 520);
        this.setLocationRelativeTo(null);
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    }
    
    public void calcularTotal(Object[][] contenido){
        total=0;
        for (int i = 0; i < contenido.length; i++) {
            int precio = Integer.parseInt(tabla.getValueAt(i, 3).toString());
            int cantidad = Integer.parseInt(tabla.getValueAt(i, 2).toString());
            
            total += precio*cantidad;
            
            }
        
        propinaSugerida = (int) (0.1*(double) total);
        subTotal = total + propinaSugerida;
        
        totalLabel.setText("$ "+total);
        propinaLabel.setText("+ $"+propinaSugerida);
        subtotalLabel.setText("$ "+total);
                
        
        
    }

}
    
