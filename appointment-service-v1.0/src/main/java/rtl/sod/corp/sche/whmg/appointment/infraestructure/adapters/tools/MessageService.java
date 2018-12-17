package rtl.sod.corp.sche.whmg.appointment.infraestructure.adapters.tools;


import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import lombok.extern.slf4j.Slf4j;
import rtl.sod.corp.sche.whmg.appointment.infraestructure.adapters.tools.exception.PropertiesException;
import rtl.sod.corp.sche.whmg.appointment.infraestructure.adapters.tools.xml.XmlTransformation;
import rtl.sod.corp.sche.whmg.appointment.infraestructure.adapters.tools.xml.XmlValidator;




/**
 * Clase que maneja la validacion y transformacion de los mensajes recibidos.
 */
@Slf4j
public class MessageService {

    private static MessageService instance = null;

    private MessageService() {
    }

    public static synchronized MessageService getInstance() throws PropertiesException {

        if (instance == null) {
            instance = new MessageService();
        }
        return instance;
    }

   

    /**
     * Metodo que valida si el mensaje xml contra la definicion en el xsd
     * @param message Texto del mesaje original
     * @return True en caso de ser valido el mesnaje, False en caso contrario
     * @throws Exception
     */
    public boolean validateMessage(String message) throws Exception, TransformerConfigurationException,
                                                                       TransformerException,
                                                                       PropertiesException {

        boolean isValid = false;

        try {

            //Se valida el mensaje xml
            XmlValidator.getInstance().validateXML(message);
            isValid = true;

        } catch (TransformerConfigurationException e) {
            log.error("[[ERROR]] --> " + e.getLocalizedMessage(), e);
            throw e;
        } catch (TransformerException e) {
            log.error("[[ERROR]] --> " + e.getLocalizedMessage(), e);
            throw e;
        } catch (PropertiesException e) {
            log.error("[[ERROR]] --> " + e.getLocalizedMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("[[ERROR]] --> " + e.getLocalizedMessage(), e);
            throw e;
        }
        return isValid;
    }

    /**
     * Metodo que transforma el mensaje original al requerido por el destino
     * @param message Texto del mesaje original
     * @return String con el mensaje transformado
     * @throws Exception
     */
    public String transformMessage(String message) throws Exception {

        String targetMessage = null;

        try {

            //Se hace la transformacion
            targetMessage =
                    XmlTransformation.getInstance().xmlTransformation(message);

        } catch (Exception e) {
            log.error("[[ERROR]] --> " + e.getLocalizedMessage(), e);
            throw e;
        }
        return targetMessage;
    }
    
    

    
}