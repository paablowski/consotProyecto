
package util;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.swing.JScrollPane;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Inventario;
import modelo.Mesa;
import modelo.Producto;
import vista.VentanaInventario;

/**
 *
 * @author pasot
 */
public class ManejoArchivo {
    
    BufferedReader bufferedReader = null;
    String csvFile = "data\\data.csv";
    String RUTA_INVENTARIO = "data\\inventario.txt";
    String linea = "";
    String comas = ",";
    
    
    public ManejoArchivo(){
        
    }
    
    public void cargarInventario(){
        try {
            Inventario inventario = Inventario.getInstance();
            bufferedReader = new BufferedReader(new FileReader(RUTA_INVENTARIO));
            
            while ((linea = bufferedReader.readLine()) != null) {
                
                String[] productos = linea.split(comas);
                inventario.agregarProducto(Integer.parseInt(productos[0]), productos[1], Integer.parseInt(productos[2]), Integer.parseInt(productos[3]));
                
            }

        } catch (FileNotFoundException e) {
        } catch (IOException e){
        } finally{
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                }
            }
        }
    }
    
    public void guardarPedido(Mesa mesa){
        
        String ruta = "data\\mesa"+mesa.getId()+".txt";
        File archivo = new File(ruta);
        BufferedWriter bufferedWriter;
        
        
        try {
            if (!archivo.exists()) {
                
            bufferedWriter = new BufferedWriter(new FileWriter(archivo,true));
            bufferedWriter.write("Aqui se guardan los pedidos para mesa"+mesa.getId());
                for (Producto pedido : mesa.pedido) {
                    int codigo = pedido.getCodigo();
                    String nombre = pedido.getNombre();
                    int cantidad = pedido.getCantidad();
                    int precio = pedido.getPrecio();
                    bufferedWriter.write(codigo+","+nombre+","+cantidad+","+precio);
                    bufferedWriter.newLine();
                }
            
            
            bufferedWriter.close();
            }else{
                bufferedWriter = new BufferedWriter(new FileWriter(archivo));
                for (Producto pedido : mesa.pedido) {
                    int codigo = pedido.getCodigo();
                    String nombre = pedido.getNombre();
                    int cantidad = pedido.getCantidad();
                    int precio = pedido.getPrecio();
                    bufferedWriter.write(codigo+","+nombre+","+cantidad+","+precio);
                    bufferedWriter.newLine();
                }
                bufferedWriter.close();
            }
        } catch (IOException e) {
            e.getMessage();
        }
        
        
    }
    
    public void cargarPedido(Mesa mesa){
        String ruta = "data\\mesa"+mesa.getId()+".txt";
        File archivo = new File(ruta);
        BufferedReader bufferedReader;
        
        try {
            if (!archivo.exists()) {
                System.out.println("El pedido de la mesa no existe");
            }else{
                
                bufferedReader = new BufferedReader(new FileReader(archivo));
                String linea;
                List<String[]> pedido = new ArrayList<>();
                
                while ((linea = bufferedReader.readLine()) != null) {
                    String [] quitarComa = linea.split(",");
                    pedido.add(quitarComa);
                    
                }
                
                for (int i = 0; i < pedido.size(); i++) {
                int codigo = Integer.parseInt(pedido.get(i)[0]);
                String nombre = pedido.get(i)[1];
                int cantidad = Integer.parseInt(pedido.get(i)[2]);
                int precio = Integer.parseInt(pedido.get(i)[3]);
                mesa.pedido.add(new Producto(codigo, nombre, cantidad, precio));
                }
            }
        } catch (Exception e) {
        }
        
    }
    
    public void cargarVentanaInventario(){
        
        VentanaInventario ventanaInventario = new VentanaInventario();
        String[] nombreColumnas = new String[]{
            "CODIGO","NOMBRE","CANTIDAD","PRECIO"
        };
        try {
            bufferedReader = new BufferedReader(new FileReader(csvFile));
            List<String[]> elements = new ArrayList<>();
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                String [] quitarComa = linea.split(",");
                elements.add(quitarComa);
            }
            bufferedReader.close();
            JTable tabla = new JTable();
            JScrollPane scroll = new JScrollPane(tabla);
            
            ventanaInventario.add(tabla.getTableHeader(),BorderLayout.PAGE_START);
            ventanaInventario.add(tabla,BorderLayout.CENTER);
            
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
