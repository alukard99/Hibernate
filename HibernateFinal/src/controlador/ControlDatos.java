
package controlador;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import modelo.Articulos;
import modelo.Clientes;
import modelo.EstadisticasClientes;
import modelo.FacturasCab;
import modelo.FacturasLin;
import modelo.FacturasTot;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

public class ControlDatos {

    
    //METODO PARA CONECTARSE A LA BD
    
    private Session sesion;
    private void conectar(){
        try{
            sesion = NewHibernateUtil.getSessionFactory().openSession(); 
        }
        catch(HibernateException ex){
            System.out.println("Error al conectar"); 
        }
    }
    
    //METODOS PARA RELLENAR TABLAS:
    
    public List<Articulos> recibirArticulos(){
        try{
            conectar();
            Criteria p = sesion.createCriteria(Articulos.class);
            List<Articulos> art = p.list();
            return art;
        }
        finally{
            sesion.close();
        }
    }
    
    public List<Clientes> listarClientesComboBox(){
        try{
            String senteciaQuery = "FROM Clientes";
            conectar();
            Query query = sesion.createQuery(senteciaQuery);
            List<Clientes> client = query.list();
            return client;
        }
        finally{
            sesion.close();
        }
    }
    
    public List<Clientes> recibirClientes(){
        try{
            conectar();
            Criteria p = sesion.createCriteria(Clientes.class);
            List<Clientes> client = p.list();
            return client;
        }
        finally{
            sesion.close();
        }
    }
    
    public List<FacturasCab> recibirFacturas(){
        try{
            conectar();
            Criteria p = sesion.createCriteria(FacturasCab.class);
            List<FacturasCab> fac = p.list();
            return fac;
        }
        finally{
            sesion.close();
        }
    }
    
     public List<EstadisticasClientes> recibirEstad(){
        try{
            conectar();
            Criteria p = sesion.createCriteria(EstadisticasClientes.class);
            List<EstadisticasClientes> estad = p.list();
            return estad;
        }
        finally{
            sesion.close();
        }
    }
    
    
     //METODOS PARA AÑADIR A LA BD
     
     
    public int aniadirArticulo(Articulos art){ //Añade un articulo
        try{
            conectar();
            sesion.beginTransaction();
            sesion.save(art);
            sesion.getTransaction().commit();
        } catch(ConstraintViolationException ex){
            sesion.close();
            return 1;
        }
        finally{
            if(sesion.isOpen()){
            sesion.close();                
            }
        }
        return 0;
    }
    
     public int aniadirCliente(Clientes client){ //Añade un cliente
        try{
            conectar();
            sesion.beginTransaction();
            sesion.save(client);
            sesion.getTransaction().commit();
        } catch(ConstraintViolationException ex){
            sesion.close();
            return 1;
        }
        finally{
            if(sesion.isOpen()){
            sesion.close();                
            }
        }
        return 0;
    }
    
    public int aniadirFactura(FacturasCab fact){ //Añade una factura
        try{
            conectar();
            sesion.beginTransaction();
            sesion.save(fact);
            sesion.getTransaction().commit();
        } catch(ConstraintViolationException ex){
            sesion.close();
            return 1;
        }
        finally{
            if(sesion.isOpen()){
            sesion.close();                
            }
        }
        return 0;
    }
    
    public void aniadirLinea(FacturasCab fac){ //Añade o borra una linea a la factura
        try{
            conectar();
            sesion.beginTransaction();
            sesion.update(fac);
            sesion.getTransaction().commit();
        }
        finally{
            sesion.close();
        }
    }
    
    public void aniadirStock(Articulos art, BigDecimal cant){ //Es llamado al borrar una linea de factura o una factura
        try{
            conectar();
            sesion.beginTransaction();
            art.setStock(art.getStock().add(cant));
            sesion.update(art);
            sesion.getTransaction().commit();
        }
        finally{
            sesion.close();
        }
    }
    
    public int crearTot(FacturasCab factura){ //Crea un objeto de tipo FacturasTot con los totales a 0
        FacturasTot total = new FacturasTot();
        total.setFacturasCab(factura); //Se relaciona con la cabecera.
        factura.setFacturasTot(total);
        total.setNumfac(factura.getNumfac());
        total.setBaseimp(BigDecimal.ZERO);
        total.setImpDto(BigDecimal.ZERO);
        total.setImpIva(BigDecimal.ZERO);
        total.setTotalfac(BigDecimal.ZERO);
        if(aniadirFacturaTot(total) ==0){
            return 0;
        }
        else{
            return 1;
        }
    }
    
    public int aniadirFacturaTot(FacturasTot total){ //Guarda el resultado del metodo anterior en la BD
        try{
            conectar();
            sesion.beginTransaction();
            sesion.save(total);
            sesion.getTransaction().commit();
            return 0;
        }
        catch(ConstraintViolationException ex){
            return 1;
        }
        finally{
            sesion.close();
        }
    }
    
    public void crearEstad(Date fechaIni, Date fechaFin, Clientes clienteIni, Clientes clienteFin){ //Crea una estadistica con los datos proporcionados y llama al procedure de la BD
        try {
            String servidor="localhost";
            String puerto = "1521";
            String id = "xe";
            String usuario = "HIBERNATE_FINAL";
            String passw = "HIBERNATE_FINAL";
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@"+servidor+":"+puerto+":"+id,usuario,passw);
            CallableStatement cs = con.prepareCall("{call sp_crearEstadistica (?,?,?,?)}");
            cs.setString(1, clienteIni.getDnicif());
            cs.setString(2, clienteFin.getDnicif());
            java.sql.Date sqlDate = new java.sql.Date(fechaIni.getTime());
            cs.setDate(3, sqlDate);
            java.sql.Date sqlDate2 = new java.sql.Date(fechaFin.getTime());
            cs.setDate(4, sqlDate2);
            cs.execute();
            cs.close();
            con.commit();
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al conectar con la BD" + ex);
        }
    }
    
    
    
    //METODOS PARA EDITAR REGISTROS DE LA BD
    
    
    public void editarArticulo(Articulos art){ //Edita un articulo
        try{
            if(!sesion.isOpen()){
                conectar();
                sesion.beginTransaction();   
            }
                sesion.update(art);
                sesion.getTransaction().commit();
        }
        finally{
            if(sesion.isOpen()){
                sesion.close();
            }
        }
    }
    
    public int editarCliente(Clientes client){ //Edita un cliente
        try{
            conectar();
            sesion.beginTransaction();
            sesion.update(client);
            sesion.getTransaction().commit();
        }
        finally{
            sesion.close();
        }
        return 0;
    }
    
     public void editarFactura(FacturasCab fact){ //Edita una factura
        try{
            if(!sesion.isOpen()){
                conectar();
                sesion.beginTransaction();   
            }
                sesion.update(fact);
                sesion.getTransaction().commit();
        }
        finally{
            if(sesion.isOpen()){
                sesion.close();
            }
        }
    }
    
    public void modLinea(FacturasLin linea){ //Edita una linea de la factura
        try{
            conectar();
            sesion.beginTransaction();
            sesion.update(linea);
            sesion.getTransaction().commit();
        }
        finally{
            sesion.close();
        }
    }
     
    public int restarStock(Articulos art, double cant){ //Quita stock tras añadir un articulo a FacturasLin
        try{
            conectar();
            sesion.beginTransaction();
            Double stock = Double.valueOf(art.getStock().toString());
            if(stock >= cant){ //Si la cantidad que se quiere retirar es menor a la cantidad existente 
                art.setStock(art.getStock().subtract(BigDecimal.valueOf(cant)));
                sesion.update(art);
                sesion.getTransaction().commit();
                return 0;
            }
            else{
                return 1;
            }
        }
        finally{
            sesion.close();
        }
    }
    
    public void editarTotal(FacturasTot total){ //Edita FacturasTot de la factura
        try{
            conectar();
            sesion.beginTransaction();
            sesion.update(total);
            sesion.getTransaction().commit();
        }
        finally{
            sesion.close();
        }
    }
    
    public void calcularTot(FacturasLin linea){ //Calcula el total de la factura cada vez que se añade un registro
        FacturasCab factura = linea.getFacturasCab();
        FacturasTot total = factura.getFacturasTot();
        
        BigDecimal precio = linea.getPrecio();
        BigDecimal cantidad = linea.getCantidad();
        
        BigDecimal iva = linea.getIvalinea();
        BigDecimal desc = linea.getDtolinea();
        
        precio = precio.multiply(cantidad);
        iva = precio.multiply(iva).divide(BigDecimal.valueOf(100));
        desc = precio.add(iva).multiply(desc).divide(BigDecimal.valueOf(100));

        total.setBaseimp(total.getBaseimp().add(precio));
        total.setImpDto(total.getImpDto().add(desc));
        total.setImpIva(total.getImpIva().add(iva));
        
        total.setTotalfac(total.getTotalfac().add(precio).add(iva).subtract(desc));
        
        factura.setFacturasTot(total);
        editarTotal(total);
        editarFactura(factura);
        
        
    }
    
    
    
    
     
    //METODOS PARA BORRAR REGISTROS DE LA BD
    
    
    public int borrarCliente(Clientes client){
        try{
            conectar();
            sesion.beginTransaction();
            sesion.delete(client);
            sesion.getTransaction().commit();
        }
        catch(ConstraintViolationException ex){
            return 1;
        }
        finally{
            if(sesion.isOpen()){
                sesion.close();
            }
        }
        return 0;
    }
    
    public void borrarArticulo(Articulos art){
        try{
            conectar();
            sesion.beginTransaction();
            sesion.delete(art);
            sesion.getTransaction().commit();
        }
        finally{
            sesion.close();
        }
    }
    
    public void borrarLinea(FacturasLin linea){
        try{
            conectar();
            sesion.beginTransaction();
            Articulos art = linea.getArticulos();
            BigDecimal cantidad = linea.getCantidad();
            art.setStock(art.getStock().add(cantidad));
            sesion.update(art);
            sesion.delete(linea);
            sesion.getTransaction().commit();
        }
        finally{
            sesion.close();
        }
    }
    
    public int borrarFactura(FacturasCab fact){
        try{
            Set<FacturasLin> lineas = fact.getFacturasLins();
            lineas.forEach((lin) -> {
                borrarLinea(lin);
            });
            conectar();
            sesion.beginTransaction();
            sesion.delete(fact);
            sesion.getTransaction().commit();
        }
        finally{
            if(sesion.isOpen()){
                sesion.close();
            }
        }
        return 0;
    }
}