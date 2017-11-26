
package vista;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import sistema.Restaurante;

/**
 *
 * @author pasot
 */
public class VentanaInicio extends JFrame{
    public VentanaInicio(){
        this.setLayout(new GridLayout(3, 2));
        JButton btnMesa1 = new JButton("Mesa 1");
        this.add(btnMesa1);
        for (int i = 2; i < 7; i++) {
            this.add(new JButton("Mesa"+i));
        }
        btnMesa1.addActionListener(
                (ActionEvent e) -> {
                    Restaurante rest = new Restaurante();
                    
                }
        );
                
        this.setVisible(true);
        this.setSize(500,400);
    }
}
