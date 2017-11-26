
package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pasot
 */
public class Mesa {
    
    public List<Producto> pedido;
    Inventario inventario;
    
    private int id;
    
    

    public Mesa(int id) {
        this.id = id;
        inventario = Inventario.getInstance();
        pedido = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void mostrarPedido(){
        System.out.println("PEDIDO MESA 1");
        if (!pedido.isEmpty()) {
            pedido.forEach(System.out::println);
        }else{
            System.out.println("El pedido para la mesa está vacío");
        }
    }
    
    public void agregarProducto(int codigo,int cantidad){
        inventario.quitarStock(codigo, cantidad);
        String nombre = inventario.inventario.get(codigo).getNombre();
        int precio = inventario.inventario.get(codigo).getPrecio();
        pedido.add(new Producto(codigo, nombre, cantidad, precio));
        System.out.println("Se han agregado "+cantidad+" de "+nombre);
    }
    
    public void quitarProducto(int codigo,int cantidad){
        inventario.agregarStock(codigo, cantidad);
        pedido.remove(codigo);
        System.out.println("Se han quitado "+cantidad+" de "+inventario.inventario.get(codigo).getNombre());
    }
    
    public void calcularTotal(){
        
        int total = 0;
        int propinaSugerida = 0;
        int subTotal = 0; 
       
        for (Producto p : pedido) {
            total += p.getPrecio()*p.getCantidad();
        }
        
        propinaSugerida = (int) (0.1*(double) total);
        subTotal = total + propinaSugerida;
        
        System.out.println("Total mesa: $"+total);
        System.out.println("Propina sugerida: $"+propinaSugerida);
        System.out.println("Subtotal: $"+subTotal);
        
    }
    
}
