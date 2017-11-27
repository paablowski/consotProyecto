
package vista;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 *
 * @author consot
 */
public class PanelPagar extends JPanel{
    
    public PanelPagar(){
        inicializarComponentes();
        
    }
    
    public void inicializarComponentes(){
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        JPanel panelGarzon = new JPanel();
        JPanel paneltotalPagar = new JPanel();
        JPanel panelMedioPago = new JPanel();
        add(panelGarzon);
        add(paneltotalPagar);
        add(panelMedioPago);
        
        JLabel nombreGarzones = new JLabel("Garzon: ");
        JComboBox garzon = new JComboBox();
        garzon.addItem("");
        garzon.addItem("Juanito Perez");
        garzon.addItem("Pablo Picasso");
        garzon.addItem("Pepe Garrido");
        garzon.setSize(50, 10);
        
        JLabel totalPagar = new JLabel("Total a pagar: ");
        JLabel totalAPagar = new JLabel("$ 7.260");
        
        TitledBorder border = BorderFactory.createTitledBorder( "Metodo de Pago" );
        border.setTitleColor( Color.BLUE );
        setBorder( border );
        
        JButton efectivo = new JButton("Efectivo");
        JButton tarjetaCredito = new JButton("Tarjeta de credito");
        JButton tarjetaDebito = new JButton("Tarjeta debito");
        
        
        panelGarzon.add(nombreGarzones);
        panelGarzon.add(garzon);
        
        paneltotalPagar.add(totalPagar);
        paneltotalPagar.add(totalAPagar);
        
        panelMedioPago.add(efectivo);
        panelMedioPago.add(tarjetaCredito);
        panelMedioPago.add(tarjetaDebito);
        
    }
    
}
