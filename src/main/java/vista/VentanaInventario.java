
package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Inventario;

/**
 *
 * @author pasot
 */
public class VentanaInventario extends JFrame{
    
    public String linea;
    private JTable tabla;
    private final String RUTA_INVENTARIO = "data\\inventario.txt";
    public BufferedWriter output;
    private final JPanel panelInventario;
    public JButton agregarbtn;
    public JButton quitarbtn;
    public JTextField codigoTextField;
    public JTextField cantidadTextField;
    public JTextField nombreTextField;
    public JTextField precioTextField;
    private final JLabel codigoLabel;
    private final JLabel cantidadLabel;
    private final JLabel descripcionLabel;
    private final JLabel precioLabel;
    private BufferedReader bufferedReader;
    private Inventario inventario;
    
    public VentanaInventario(){
        inventario = Inventario.getInstance();
        setTitle("INVENTARIO");
        setSize(400,400);
        panelInventario = new JPanel();
        add(panelInventario,BorderLayout.SOUTH);
        leerInventario();
        
        panelInventario.setLayout(new GridLayout(6, 2));
        panelInventario.setPreferredSize(new Dimension(0, 80));
        agregarbtn = new JButton("Agregar");
        quitarbtn = new JButton("Quitar");
        codigoLabel = new JLabel("Codigo: ");
        cantidadLabel = new JLabel("Cantidad: ");
        descripcionLabel = new JLabel("Descripcion:");
        precioLabel = new JLabel("Precio: ");
        codigoTextField = new JTextField("");
        cantidadTextField = new JTextField("");
        nombreTextField = new JTextField("");
        precioTextField = new JTextField("");
        
        panelInventario.add(codigoLabel);
        panelInventario.add(codigoTextField);
        panelInventario.add(cantidadLabel);
        panelInventario.add(cantidadTextField);
        panelInventario.add(descripcionLabel);
        panelInventario.add(nombreTextField);
        panelInventario.add(precioLabel);
        panelInventario.add(precioTextField);
        panelInventario.add(agregarbtn);
        panelInventario.add(quitarbtn);
        
        
        this.agregarbtn.addActionListener((ActionEvent e) -> {
                    String codigo = codigoTextField.getText();
                    String cantidad = cantidadTextField.getText();
                    String nombre = nombreTextField.getText();
                    String precio = precioTextField.getText();
                    
                    System.out.println(codigo);
                    System.out.println(cantidad);
                    System.out.println(nombre);
                    System.out.println(precio);
                    String[] nuevaFila = new String[]{
                        codigo,nombre,cantidad,precio
                    };
                    agregar(nuevaFila,tabla);
                }
        );
        
        this.quitarbtn.addActionListener(
                (ActionEvent e) -> {
                    String codigo = codigoTextField.getText();
                    
                }
        );
        
        
    }
    
    public void agregar(String []nuevaFila, JTable tabla){
        
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.addRow(nuevaFila);
        escribirInventario(nuevaFila);
        
        cantidadTextField.setText("");
        codigoTextField.setText("");
        nombreTextField.setText("");
        precioTextField.setText("");
        
    }
    
    public void quitar(String []nuevaFila, JTable tabla){
        
        
    }
    
    private void leerInventario(){
        String[] nombreColumnas = new String[]{
            "CODIGO","NOMBRE","CANTIDAD","PRECIO"
        };
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
            
            add(tabla.getTableHeader(),BorderLayout.PAGE_START);
            add(tabla,BorderLayout.CENTER);
            
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
    
    
    
    private void escribirInventario(String []nuevaFila){
        
        BufferedWriter output = null;
        try {
            output = new BufferedWriter(new FileWriter(RUTA_INVENTARIO,true));
            
            output.newLine();
            for (String nuevaFila1 : nuevaFila) {
                output.write(nuevaFila1+",");
            }
            
        } catch (IOException ex) {
        System.out.println(ex.getMessage());
        } finally {
            try {
            output.close();
            } catch (IOException ex) {
            System.out.println("Imposible abrir el archivo");
            }
        }
    }
        
        
        
        
    }
    

