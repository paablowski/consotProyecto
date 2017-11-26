
package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pasot
 */
public class Inventario {
    
    private static final Inventario instance;
    
    public List<Producto> inventario;
    
    private Inventario(){
        
        inventario = new ArrayList<>();
        
    }
    
    static {
        instance = new Inventario();
    }
    
    public static Inventario getInstance(){
        return instance;
    }
    
    
    public void mostrar(){
        if (!inventario.isEmpty()) {
            inventario.forEach(System.out::println);
        }else{
            System.out.println("Inventario vacio");
        }
    }
    
    public void agregarProducto(int codigo, String nombre, int cantidad, int precio){
        inventario.add(new Producto(codigo, nombre, cantidad, precio));
        System.out.println("Se agrego: "+nombre);
    }
    
    public void quitarProducto(int codigo){
        inventario.remove(codigo);
        System.out.println("Se elimino producto ID: "+codigo);
    }
    
    public void agregarStock(int codigo, int cantidad){
        try {
            if (inventario.get(codigo) != null) {
            inventario.get(codigo).setCantidad(inventario.get(codigo).getCantidad()+cantidad);
                System.out.println("Se agrego "+cantidad+" a "+inventario.get(codigo).getNombre());
        }
        } catch (Exception IndexOutOfBoundsException) {
            System.out.println("Producto: "+codigo+" no existe");
            
        }
    }
    
    public void quitarStock(int codigo, int cantidad){
        try {
            if (inventario.get(codigo) != null) {
                inventario.get(codigo).setCantidad(inventario.get(codigo).getCantidad()-cantidad);
                System.out.println("Se quitaron "+cantidad+" a "+inventario.get(codigo).getNombre());
        }
        } catch (Exception IndexOutOfBoundsException) {
            System.out.println("Producto: "+codigo+" no existe");
            
        }
        
        
    }
    
}
