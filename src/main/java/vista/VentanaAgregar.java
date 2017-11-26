
package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pasot
 */
public class VentanaAgregar extends JFrame{
    private final JPanel panelAgregarIzq;
    private final JPanel panelAgregarCentro;
    private final JPanel panelAgregarDer;
    
    private JButton btnAgregar;
    private JButton btnQuitar;
    private BufferedReader bufferedReader;
    private final String RUTA_INVENTARIO = "data\\inventario.txt";
    private JTable tabla;
    private String linea;
    private DefaultTableModel modelo;
    private String[] nombreColumnas = new String[]{"CODIGO","NOMBRE","CANTIDAD","PRECIO"};
    
    public VentanaAgregar(){
        
        panelAgregarIzq = new JPanel();
        panelAgregarCentro = new JPanel();
        panelAgregarDer = new JPanel();
        
        
        
        panelAgregarIzq.setPreferredSize(new Dimension(250, 200));
        panelAgregarIzq.setMinimumSize(new Dimension(300, 500));
        
        
        panelAgregarCentro.setMinimumSize(new Dimension(110, 500));
        
        panelAgregarDer.setPreferredSize(new Dimension(250, 200));
        panelAgregarDer.setMinimumSize(new Dimension(300, 500));
        
        
/*PANEL IZQUIERDO*/
        JLabel inventarioLabel = new JLabel("Inventario:");
        inventarioLabel.setForeground(Color.BLUE);
        panelAgregarIzq.add(inventarioLabel);
        try {
            bufferedReader = new BufferedReader(new FileReader(RUTA_INVENTARIO));
            List<String[]> elements = new ArrayList<>();
            
            while ((linea = bufferedReader.readLine()) != null) {
                String [] quitarComa = linea.split(",");
                elements.add(quitarComa);
            }
            bufferedReader.close();
            tabla = new JTable();
            JScrollPane scroll = new JScrollPane(tabla);
            
            panelAgregarIzq.add(tabla.getTableHeader(),BorderLayout.PAGE_START);
            panelAgregarIzq.add(tabla,BorderLayout.CENTER);
            
            Object[][] contenido = new Object[elements.size()][4];
            for (int i = 0; i < elements.size(); i++) {
                contenido[i][0]= elements.get(i)[0];
                contenido[i][1]= elements.get(i)[1];
                contenido[i][2]= elements.get(i)[2];
                contenido[i][3]= "$"+elements.get(i)[3];
            }
            modelo = new DefaultTableModel(contenido,nombreColumnas);
            tabla.setModel(modelo);
        } catch (Exception e) {
        }
        
        tabla.addMouseListener(new java.awt.event.MouseAdapter()
            {
public void mouseClicked(java.awt.event.MouseEvent e)
{
int row=tabla.rowAtPoint(e.getPoint());
int col= tabla.columnAtPoint(e.getPoint());
JOptionPane.showMessageDialog(null," Value in the cell clicked :"+ " " +tabla.getValueAt(row,col).toString());

System.out.println(" Value in the cell clicked :"+ " " +tabla.getValueAt(row,col).toString());
}
}
);
        
        
//
        
/*PANEL CENTRO*/

        this.setLayout(new GridLayout(2, 1));
        
        btnAgregar = new JButton("Agregar");
        btnQuitar = new JButton("Quitar");
        Integer[] cantidad = {0,1,2,3,4,5,6,7,8,9,10};
        JComboBox cantidadComboBox = new JComboBox(cantidad);
        
        panelAgregarCentro.add(btnAgregar);
        panelAgregarCentro.add(cantidadComboBox);
        panelAgregarCentro.add(btnQuitar);
        
        
//

/*PANEL DERECHO*/
        JLabel pedidoLabel = new JLabel("Pedido de la mesa:");
        pedidoLabel.setForeground(Color.BLUE);
        panelAgregarDer.add(pedidoLabel);
        try {
            bufferedReader = new BufferedReader(new FileReader("data\\mesa1.txt"));
            List<String[]> elements = new ArrayList<>();
            
            while ((linea = bufferedReader.readLine()) != null) {
                String [] quitarComa = linea.split(",");
                elements.add(quitarComa);
            }
            bufferedReader.close();
            tabla = new JTable();
            JScrollPane scroll = new JScrollPane(tabla);
            
            panelAgregarDer.add(tabla.getTableHeader(),BorderLayout.PAGE_START);
            panelAgregarDer.add(tabla,BorderLayout.CENTER);
            
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


//
        
        JSplitPane pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelAgregarIzq, panelAgregarCentro);
        JSplitPane pane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pane, panelAgregarDer);


        add(pane2);
        
        this.setSize(750, 400);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        
        
    }
    
}
