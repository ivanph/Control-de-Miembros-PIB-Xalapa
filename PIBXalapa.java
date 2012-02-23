package pibxalapa;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package pibxalapa;

/**
 *
 * @author ivan
 */
public class PIBXalapa {
     
    public static void main(String[] args) {
        try{
        Class.forName("org.sqlite.JDBC");
        
        }catch (Exception e){
            javax.swing.JOptionPane.showConfirmDialog(null, e.toString());
        }
        VentanaPrincipal ventana_principal = new VentanaPrincipal();
      
        
		ventana_principal.run();
    }
}
