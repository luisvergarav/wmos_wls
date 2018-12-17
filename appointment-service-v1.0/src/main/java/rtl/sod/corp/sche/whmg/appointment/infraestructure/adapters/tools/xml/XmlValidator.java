package rtl.sod.corp.sche.whmg.appointment.infraestructure.adapters.tools.xml;



import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;



import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import rtl.sod.corp.sche.whmg.appointment.infraestructure.adapters.http.rest.RestAPPConfig;
import rtl.sod.corp.sche.whmg.appointment.infraestructure.adapters.http.rest.RestConstants;
import rtl.sod.corp.sche.whmg.appointment.infraestructure.adapters.tools.exception.PropertiesException;
import lombok.extern.slf4j.Slf4j;

/**
 * Clase para validar un xml
 *
 */
@Slf4j
public class XmlValidator {


	
	private static final RestAPPConfig	CONFIG				= new RestAPPConfig();

    private static XmlValidator instance = null;

    private static Validator validator = null;

    private XmlValidator() {

    }

    /**
     *Metodo para recuperar una instancia de XMLValidator
     *
     * @return
     * @throws PropertiesException
     * @throws SAXException
     */
    public static synchronized XmlValidator getInstance() throws PropertiesException,
                                                                 SAXException {
        if (instance == null) {
            instance = new XmlValidator();
            initSchemaValidator();
        }
        return instance;
    }

    /**
     *Metodo para validar el encoding del archivo XML
     *
     * @param xml
     * @return
     * @throws XMLStreamException
     * @throws PropertiesException
     */
    public InputSource validateEncoding(String xml) throws XMLStreamException,
                                                           PropertiesException {

        String encodingFromXMLDeclaration = null;
        String fileEncoding = null;
        String targetEncoding = null;
        ByteArrayInputStream stream = null;
        InputSource is = null;

        final XMLStreamReader xmlStreamReader =
            XMLInputFactory.newInstance().createXMLStreamReader(new StringReader(xml));


        targetEncoding =
                CONFIG.getProperty(RestConstants.ROOT +
                                                            RestConstants.ENTITY +
                                                            RestConstants.ENCODE);

        //running on MS Windows fileEncoding is "CP1251"
        fileEncoding = xmlStreamReader.getEncoding();

        //the XML declares UTF-8 so encodingFromXMLDeclaration is "UTF-8"

        encodingFromXMLDeclaration =
                xmlStreamReader.getCharacterEncodingScheme();

        if (!encodingFromXMLDeclaration.equals(targetEncoding)) {
            //Se coloca el encode
            byte[] bytes = xml.getBytes();
            stream = new ByteArrayInputStream(bytes);

            is = new InputSource(stream);
            is.setEncoding(targetEncoding);
            is.setCharacterStream(new StringReader(xml));
            return is;
        }
        return null;

    }


    /**
     * Metodo para cargar el schema con el cual se va a validar
     *
     * @throws PropertiesException
     * @throws SAXException
     */
    private static void initSchemaValidator() throws PropertiesException,
                                                     SAXException {

        //Se obtiene la ruta del archivo xsd
        String xsdPath =
            RestConstants.RESOURCES_PATH + CONFIG.getProperty(RestConstants.ROOT +
                                                                                   RestConstants.ENTITY +
                                                                                   RestConstants.XSD_DIR) +
            CONFIG.getProperty(RestConstants.ROOT +
                                                        RestConstants.ENTITY +
                                                        RestConstants.XSD_FILE);

        //Se crea un archivo con la ruta del xsd
        File file = new File(xsdPath);

        SchemaFactory schemaFactory =
            SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        //Se instancia un schema con el xsd
        Schema schema = schemaFactory.newSchema(file);

        validator = schema.newValidator();
    }

    /**
     *Metodo para validar un xml
     *
     * @param xml
     * @return
     * @throws IOException
     * @throws SAXException
     */
    public synchronized boolean validateXML(String xml) throws Exception {


        Source xmlFile = null;
        boolean result = false;

        try {
            if (validator == null)
                initSchemaValidator();

            InputSource is = this.validateEncoding(xml);

            if (is == null) {
                Source xmlSource = new StreamSource(new StringReader(xml));
                validator.validate(xmlSource);
            } else {
                xmlFile = new SAXSource(is);

                //Se valida el mensaje xml
                validator.validate(xmlFile);
            }
            result = true;
        } catch (Exception e) {
            log.error("[[ERROR]] --> " + e.getLocalizedMessage(), e);
            throw e;
        } finally {
            xmlFile = null;

        }
        return result;
    }
}