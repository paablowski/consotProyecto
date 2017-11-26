
package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pasot
 */
public class VentanaMesa extends JFrame{
    
    private JPanel panelMesaIzq;
    private JPanel panelMesaCentro;
    private JPanel panelMesaDer; 
    private VentanaAgregar ventanaAgregar;
    private JButton btnAgregar;
    private JButton btnCancelar;
    public JList mesasJList;
    public DefaultListModel modelo;
    public JMenuItem i1;
    private JTable tabla;
    private BufferedReader bufferedReader;
    private String RUTA_MESA;
    private String[] nombreColumnas = new String[]{"CODIGO","NOMBRE","CANTIDAD","PRECIO"};
    
    public VentanaMesa(){
        
        JMenu menu, submenu;
        JMenuItem i1, i2, i3, i4, i5;
        JMenuBar menuBar = new JMenuBar();
        menu = new JMenu("Archivo");
        submenu = new JMenu("Submenu");
        i1=new JMenuItem("Inventario");  
        i2=new JMenuItem("Salir"); 
        menu.add(i1);
        menu.add(i2); 
        menuBar.add(menu);  
        this.setJMenuBar(menuBar);
        
        i1.addActionListener(
                (ActionEvent e) -> {
                    VentanaInventario ventanaInventario = new VentanaInventario();
                    ventanaInventario.setVisible(true);
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
        
        
        MouseListener mouseListener = new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent mouseEvent) {
        JList theList = (JList) mouseEvent.getSource();
        if (mouseEvent.getClickCount() == 1) {
          int index = theList.locationToIndex(mouseEvent.getPoint())+1;
          if (index >= 0) {
            Object o = theList.getModel().getElementAt(index);
            System.out.println("Double-clicked on: " + o.toString());
              System.out.println("mesa"+index);
              String RUTA_MESA = "data\\mesa"+index+".txt";
              
              String[] nombreColumnas = new String[]{
            "CODIGO","CANTIDAD","DESCRIPCION","PRECIO"
              };
            try {
                bufferedReader = new BufferedReader(new FileReader(RUTA_MESA));
                List<String[]> elements = new ArrayList<>();
                String linea;
                while ((linea = bufferedReader.readLine()) != null) {
                    String [] quitarComa = linea.split(",");
                    elements.add(quitarComa);
                }
                bufferedReader.close();
                JTable tabla = new JTable();
                JScrollPane scroll = new JScrollPane(tabla);

                panelMesaCentro.add(tabla.getTableHeader(),BorderLayout.PAGE_START);
                panelMesaCentro.add(tabla,BorderLayout.CENTER);

                Object[][] contenido = new Object[elements.size()][4];
                for (int i = 0; i < elements.size(); i++) {
                    contenido[i][0]= elements.get(i)[0];
                    contenido[i][1]= elements.get(i)[1];
                    contenido[i][2]= elements.get(i)[2];
                    contenido[i][3]= elements.get(i)[3];
                }
                tabla.setModel(new DefaultTableModel(contenido,nombreColumnas));

            } catch (Exception e) {
            }
          }
        }
      }
    };
    mesasJList.addMouseListener(mouseListener);
        
        
/*PANEL CENTRO*/        
        panelMesaCentro = new JPanel();
        try {
            bufferedReader = new BufferedReader(new FileReader("data\\mesa1.txt"));
            List<String[]> elements = new ArrayList<>();
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                String [] quitarComa = linea.split(",");
                elements.add(quitarComa);
            }
            bufferedReader.close();
            tabla = new JTable();
            JScrollPane scroll = new JScrollPane(tabla);
            
            panelMesaCentro.add(tabla.getTableHeader(),BorderLayout.PAGE_START);
            panelMesaCentro.add(tabla,BorderLayout.CENTER);
            
            Object[][] contenido = new Object[elements.size()][4];
            for (int i = 0; i < elements.size(); i++) {
                contenido[i][0]= elements.get(i)[0];
                contenido[i][1]= elements.get(i)[1];
                contenido[i][2]= elements.get(i)[2];
                contenido[i][3]= "$"+elements.get(i)[3];
            }
            tabla.setModel(new DefaultTableModel(contenido,nombreColumnas));
            
        } catch (Exception e) {
        }
       
        panelMesaCentro.add(new JScrollPane(tabla));
        
/*PANEL DERECHO*/
        panelMesaDer = new JPanel();
        panelMesaDer.setLayout(new GridLayout(3, 1));
        
        

        setLayout(new GridLayout(3, 1));
        JPanel botones = new JPanel();
        botones.setLayout(new GridLayout(2, 1));
        btnAgregar = new JButton("Agregar");
        botones.add(btnAgregar);
        btnAgregar.addActionListener(
                (ActionEvent e) -> {
                    ventanaAgregar = new VentanaAgregar();
                    ventanaAgregar.setVisible(true);
                    
                }
        );
        
        btnCancelar = new JButton("Cancelar");
        botones.add(btnCancelar);
        panelMesaDer.add(botones);
        TitledBorder borde = BorderFactory.createTitledBorder("Total a Pagar");
        borde.setTitleColor( Color.BLUE );
        
        JPanel totalPagarPanel = new JPanel();
        totalPagarPanel.setLayout(new GridLayout(3, 3,10,4));
        totalPagarPanel.setPreferredSize(new Dimension(180,10));
        panelMesaDer.add(totalPagarPanel);
        totalPagarPanel.setBorder(borde);
        
        
        int total = 0;
        int propinaSugerida = 0;
        int Total = 0;
        JLabel totaltxt = new JLabel("Total: ");
        JLabel subtotaltxt = new JLabel("Subtotal: ");
        JCheckBox incluirPropina = new JCheckBox("Incluir propina: ",false);
        JLabel totalLabel = new JLabel("$ "+total);
        JLabel propinaLabel = new JLabel("+ $"+propinaSugerida);
        JLabel subtotalLabel = new JLabel("$ "+Total);
        
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
        this.setSize(745, 500);
        this.setLocationRelativeTo(null);
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    }
    
    
}
    
