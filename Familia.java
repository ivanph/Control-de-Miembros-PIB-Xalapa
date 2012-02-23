/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pibxalapa;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;
/**
 *
 * @author ivan
 */
public class Familia {
    public int FamiliaID;
    public String Familia;
    public int No_Integrantes;
    public String Calle;
    public String Colonia;
    public String Municipio;
    public String Telefono;
    
   
    public boolean Registrar(){
       	
        try{
        Connection conexion = DriverManager.getConnection("jdbc:sqlite:"+System.getProperty("user.dir")+"/pibxalapa.db");
        Statement instruccion_insert = conexion.createStatement();
        String cad_sql = "insert into Familia (No_integrantes,Calle,Familia,Telefono,ColoniaID,MunicipioID) values ('"+No_Integrantes+"','"+Calle+"','"+Familia+"','"+Telefono+"','"+ColoniatoInt(this.Colonia)+"','"+MunicipiotoInt(this.Municipio)+"')";
        instruccion_insert.executeUpdate(cad_sql);
        conexion.close();
        return true;
        }catch (Exception e){
            javax.swing.JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
      
        }
    
    @Override
     public String toString (){
       return this.Familia;           
}
    
    public static ArrayList<Familia> ListaFamilias(){
        ArrayList<Familia> lista = new ArrayList<Familia>();
        try{
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:"+System.getProperty("user.dir")+"/pibxalapa.db");
            Statement instruccion_select = conexion.createStatement();
            String cad_sql = "select * from Familia";  
            ResultSet resultados = instruccion_select.executeQuery(cad_sql);
            while(resultados.next()) {
    			Familia familia = new Familia();
                        familia.FamiliaID = resultados.getInt("FamiliaID");
                        familia.Familia = resultados.getString("Familia");
                        familia.Calle = resultados.getString("Calle");
                        familia.Colonia = convertirColonia(resultados.getInt("ColoniaID"));
                        familia.Municipio = convertirMunicipio(resultados.getInt("MunicipioID"));
                        familia.No_Integrantes = resultados.getInt("No_integrantes");
                        familia.Telefono = resultados.getString("Telefono");
                        lista.add(familia);
            }
            conexion.close();
        }catch (Exception e){
          javax.swing.JOptionPane.showMessageDialog(null, e.toString());              
        }
        return lista;
    }
    

     public static String convertirColonia(int Colonia){
         String colonia = "";
         try{
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:"+System.getProperty("user.dir")+"/pibxalapa.db");
            Statement instruccion_select = conexion.createStatement();
            String cad_sql = "select Colonia from Colonia where ColoniaID = "+Colonia;  
            ResultSet resultados = instruccion_select.executeQuery(cad_sql);
            colonia = resultados.getString("Colonia");
            conexion.close();
         }catch (Exception e){
             javax.swing.JOptionPane.showMessageDialog(null, e.toString());
         }
           return colonia;
      }
     
     public static String convertirMunicipio(int Municipio){
         String municipio = "";
         try{
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:"+System.getProperty("user.dir")+"/pibxalapa.db");
            Statement instruccion_select = conexion.createStatement();
            String cad_sql = "select Municipio from Municipio where MunicipioID = "+Municipio;  
            ResultSet resultados = instruccion_select.executeQuery(cad_sql);
            municipio = resultados.getString("Municipio");
            conexion.close();
         }catch (Exception e){
             javax.swing.JOptionPane.showMessageDialog(null, e.toString());
         }
           return municipio;
      }
     
     public  int MunicipiotoInt(String Municipio){
         int ValorMunicipio = 0;
        try{
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:"+System.getProperty("user.dir")+"/pibxalapa.db");
            Statement instrucion_select = conexion.createStatement();
            String cad_municipio = "select MunicipioID from Municipio where Municipio = '"+Municipio+"'";
            ResultSet resultados = instrucion_select.executeQuery(cad_municipio);
            ValorMunicipio = resultados.getInt("MunicipioID"); 
            resultados.close();
         }catch (Exception e){
        javax.swing.JOptionPane.showMessageDialog(null, e.toString());
        }
        return ValorMunicipio;
     }
     
     public int ColoniatoInt(String Colonia){
         int ValorColonia = 0;
         
         try{
             Connection conexion = DriverManager.getConnection("jdbc:sqlite:"+System.getProperty("user.dir")+"/pibxalapa.db");
             Statement instrucion_select = conexion.createStatement();
             String cad_colonia = "select ColoniaID from Colonia where Colonia = '"+Colonia+"'";
             ResultSet resultados = instrucion_select.executeQuery(cad_colonia);
             ValorColonia = resultados.getInt("ColoniaID");
             resultados.close();
             conexion.close();
         }catch(Exception e){
             javax.swing.JOptionPane.showMessageDialog(null, e.toString());
         }
         
         return ValorColonia;
     }
}

