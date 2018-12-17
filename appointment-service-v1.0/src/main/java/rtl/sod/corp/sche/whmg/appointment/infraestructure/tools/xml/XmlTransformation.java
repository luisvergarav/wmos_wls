package rtl.sod.corp.sche.whmg.appointment.infraestructure.tools.xml;



import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import rtl.sod.corp.sche.whmg.appointment.infraestructure.adapters.http.rest.RestAPPConfig;
import rtl.sod.corp.sche.whmg.appointment.infraestructure.adapters.http.rest.RestConstants;
import rtl.sod.corp.sche.whmg.appointment.infraestructure.exception.PropertiesException;

/**
 * Clase utilizada para realizar la transformacion
 */
public class XmlTransformation {
	

	private static final RestAPPConfig	CONFIG				= new RestAPPConfig();


    private static XmlTransformation instance = null;

    private static Transformer transformer = null;

    private static String targetEncoding = null;
    

    
    private XmlTransformation() {
    }


    /**
     *Metodo para recuperar una instancia del tipo XMLTransformation
     *
     * @return
     * @throws PropertiesException
     * @throws TransformerConfigurationException
     * @throws FileNotFoundException 
     */
    public static synchronized XmlTransformation getInstance() throws PropertiesException,
                                                                      TransformerConfigurationException, FileNotFoundException {
        if (instance == null) {
            instance = new XmlTransformation();
            instance.initTransformation();
        }
        return instance;
    }


    /**
     * Metodo para inicializar en los parametros utilizados para la transformaciones
     *
     * @throws PropertiesException
     * @throws TransformerConfigurationException
     * @throws FileNotFoundException 
     */
    private  void initTransformation() throws PropertiesException,
                                                    TransformerConfigurationException, FileNotFoundException {

        //Se obtiene la ruta del archivo xsl
        String xslPath =
        		RestConstants.RESOURCES_PATH + CONFIG.getProperty(RestConstants.ROOT +
            																		RestConstants.ENTITY +
            																		RestConstants.XSL_DIR) +
        		CONFIG.getProperty(RestConstants.ROOT +
            											RestConstants.ENTITY +
                                                        RestConstants.XSL_FILE );
        
        

        targetEncoding =
        		CONFIG.getProperty(RestConstants.ROOT +
                											RestConstants.ENTITY +
                                                            RestConstants.ENCODE);

        TransformerFactory factory =
            TransformerFactory.newInstance(RestConstants.SAX_LIBRARY, null);

      
        //InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(xslPath);
        
        
        		
        //transformer = factory.newTransformer(new StreamSource(xslPath));
        

        transformer = factory.newTransformer(new StreamSource(xslPath));
        transformer.setOutputProperty(OutputKeys.INDENT, RestConstants.SAX_INDENT);
        transformer.setOutputProperty(OutputKeys.ENCODING, targetEncoding);
        transformer.setOutputProperty(OutputKeys.METHOD, RestConstants.SAX_METHOD);
    }

    /**
     *
     * @param xmlStr Mensaje XML como tipo de dato String
     * @return resultado de tipo String de la transformacion del XML entrante a traves de xsl.
     * @throws TransformerConfigurationException
     * @throws TransformerException
     */
    public synchronized String xmlTransformation(String xmlStr) throws TransformerConfigurationException,
                                                                       TransformerException,
                                                                       PropertiesException,
                                                                              ParserConfigurationException,
                                                                              SAXException, IOException {

       
        
        Source source = null;
      
        InputSource inputSource = null;
        ByteArrayInputStream stream = null;
        ByteArrayOutputStream baos = null;
        String result = null;

        if (transformer == null)
            initTransformation();

       
        byte[] bytes = xmlStr.getBytes();
        stream = new ByteArrayInputStream(bytes);
        inputSource = new InputSource(stream);
        inputSource.setEncoding(targetEncoding);
        source = new SAXSource(inputSource);
        
        baos = new ByteArrayOutputStream();
        Result result_ = new StreamResult(baos);
               
        transformer.transform(source, result_);

        result = baos.toString();

        return result;
    }
    
    
  
}