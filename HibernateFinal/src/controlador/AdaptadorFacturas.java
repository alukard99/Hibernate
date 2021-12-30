/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import modelo.Articulos;
import modelo.FacturasCab;
import modelo.FacturasLin;
import modelo.FacturasLinId;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
public class AdaptadorFacturas{
    
    private ControlDatos control;
    
    
    //METODOS PARA EXPORTAR 
    
    
    public JSONObject crearJson(FacturasCab factura){   // Devuelve un objeto JSONObject con los datos de la factura.
        Set<FacturasLin> facturasLin = factura.getFacturasLins();
        JSONObject json = new JSONObject();
        JSONArray arrayLin = new JSONArray();
        JSONArray arrayTot = new JSONArray();

        double lineaFact = 1; // La linea de factura se asigna de forma manual
        json.put("numfactura", String.valueOf(factura.getNumfac()));
        json.put("fechafactura", factura.getFechafac().toString());
        json.put("dnifactura", factura.getClientes().getDnicif());
        json.put("nombrecliente", factura.getClientes().getNombrecli());
        
        facturasLin.forEach((lin) -> { //Por cada linea de FacturasLin se añade al array de lineas
            arrayLin.put("lineafactura:" + lineaFact);
            arrayLin.put("referencia:" + lin.getArticulos().getReferencia());
            arrayLin.put("precio:" + lin.getPrecio());
            arrayLin.put("descuentolinea:" + lin.getDtolinea());
            arrayLin.put("ivalinea:" + lin.getIvalinea());
        });
        
        arrayTot.put("baseimp:" + factura.getFacturasTot().getBaseimp());
        arrayTot.put("impdescuento:" + factura.getFacturasTot().getImpDto());
        arrayTot.put("impiva:" + factura.getFacturasTot().getImpIva());
        arrayTot.put("total:" + factura.getFacturasTot().getTotalfac());
        
        json.put("lineasfactura",arrayLin);
        json.put("totalfactura", arrayTot); //Colocamos el array de facturas y el de totales al objeto json
        
        return json; //Devolvemos el objeto listo para imprimir.
    }
    
    public void crearXml(FacturasCab factura, File ruta) throws TransformerException, ParserConfigurationException{
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();

        Element raiz = doc.createElement("factura"); 
        doc.appendChild(raiz); 
        raiz.setAttribute("numFactura", factura.toString());
        raiz.setAttribute("fecha", factura.getFechafac().toString());
        raiz.setAttribute("total", factura.getFacturasTot().getTotalfac().toString());
        Element lineas = doc.createElement("lineas"); 
        raiz.appendChild(lineas);

        Set<FacturasLin> listLineas = factura.getFacturasLins();

        listLineas.forEach((lin) -> { //Por cada linea de facturas se crea un elemento linea y se añaden sus atributos.
            Element linea = doc.createElement("linea");
            linea.setAttribute("numlinea", String.valueOf(lin.getLinea()));
            linea.setAttribute("referencia", lin.getArticulos().getReferencia());
            linea.setAttribute("descripcion", lin.getArticulos().getDescripcion());
            linea.setAttribute("precio", lin.getArticulos().getPrecio().toString());
            linea.setAttribute("cantidad", lin.getCantidad().toString());
            linea.setAttribute("descuento", lin.getDtolinea().toString());
            linea.setAttribute("iva", lin.getIvalinea().toString());
            linea.setAttribute("total", (lin.getPrecio().subtract(lin.getDtolinea()).add(lin.getIvalinea()).toString()));

            lineas.appendChild(linea); //Tras asignar atributos se añade al elemento lineas.
        });

        Element totales = doc.createElement("total");
        totales.setAttribute("baseimp", factura.getFacturasTot().getBaseimp().toString());
        totales.setAttribute("impdescuento", factura.getFacturasTot().getImpDto().toString());
        totales.setAttribute("impiva", factura.getFacturasTot().getImpIva().toString());
        totales.setAttribute("total", factura.getFacturasTot().getTotalfac().toString());

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer t = tf.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(ruta);

        t.transform(source, result); //Se encarga de escribir el documento en formato xml
            
    }
    
    
    public void crearOferta(FacturasCab factura, File ruta) throws TransformerException, ParserConfigurationException{
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();

        Element raiz = doc.createElement("oferta");
        doc.appendChild(raiz);
        
        Element lineas = doc.createElement("lineas");
        raiz.appendChild(lineas);
        
        Set<FacturasLin> listLineas = factura.getFacturasLins();

        listLineas.forEach((lin) -> {
            Element linea = doc.createElement("linea");
            linea.setAttribute("numlinea", String.valueOf(lin.getLinea()));
            linea.setAttribute("referencia", lin.getArticulos().getReferencia());
            linea.setAttribute("descripcion", lin.getArticulos().getDescripcion());
            linea.setAttribute("precio", lin.getArticulos().getPrecio().toString());
            linea.setAttribute("cantidad", lin.getCantidad().toString());
            linea.setAttribute("descuento", lin.getDtolinea().toString());
            linea.setAttribute("iva", lin.getIvalinea().toString());
            linea.setAttribute("total", lin.getPrecio().toString());

            lineas.appendChild(linea);
        });

        
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer t = tf.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(ruta);

        t.transform(source, result);
            
    }
    
    public int exportarJson(JSONObject json, File ruta){
        try{
            FileWriter fw = new FileWriter(ruta);
            fw.write(json.toString());
            fw.flush();
            fw.close();
            return 0;
            }catch(IOException ex){
                return 1;
            }
    }
    
    
    //METODO PARA IMPORTAR EN XML
    
    
    public FacturasCab cargarOferta(File ruta, FacturasCab factura) throws ParserConfigurationException, SAXException, IOException{
        control = new ControlDatos();
        Set<FacturasLin> setLineas = factura.getFacturasLins();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(ruta);
        NodeList nList = document.getElementsByTagName("linea");
        for(int i=0; i < nList.getLength(); i++ ){ 
            FacturasLin lineas = new FacturasLin();
            List<Articulos> listaArt = control.recibirArticulos();
            String referencia = nList.item(i).getAttributes().getNamedItem("referencia").getNodeValue();
            for(Articulos art : listaArt) { //Por cada linea 
                if (art.getReferencia().equals(referencia)){ //Si la referencia de la linea concuerda con la de algun articulo
                    lineas.setArticulos(art);
                }
            }
            lineas.setPrecio(BigDecimal.valueOf(Double.valueOf(nList.item(i).getAttributes().getNamedItem("precio").getNodeValue())));
            lineas.setCantidad(BigDecimal.valueOf(Double.valueOf(nList.item(i).getAttributes().getNamedItem("cantidad").getNodeValue())));
            lineas.setDtolinea(BigDecimal.valueOf(Double.valueOf(nList.item(i).getAttributes().getNamedItem("descuento").getNodeValue())));
            lineas.setId(new FacturasLinId(factura.getNumfac(), Long.valueOf(nList.item(i).getAttributes().getNamedItem("numlinea").getNodeValue())));
            lineas.setIvalinea(BigDecimal.valueOf(Double.valueOf(nList.item(i).getAttributes().getNamedItem("iva").getNodeValue())));
            setLineas.add(lineas); 
            lineas.setFacturasCab(factura); 
        }
        factura.setFacturasLins(setLineas); //Unicamente queremos añadir las lineas presentes en el xml a la factura
        return factura;
    }            
}
