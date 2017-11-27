
package vista;

import javax.swing.JFrame;

/**
 *
 * @author consot
 */
public class VentanaPagar extends JFrame{
    
    private PanelPagar panelPagar;
    
    public VentanaPagar(){
        inicializarComponentes();
    }
    
    public void inicializarComponentes(){
        setSize(400, 400);
        panelPagar = new PanelPagar();
        setResizable(false);
        add(panelPagar);
        this.setTitle("CANCELAR MESA 1");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
    }
    
    public static void getTotal(){
        
    }
    
}