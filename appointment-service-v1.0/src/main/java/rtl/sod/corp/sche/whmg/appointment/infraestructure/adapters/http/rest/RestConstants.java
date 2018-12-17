package rtl.sod.corp.sche.whmg.appointment.infraestructure.adapters.http.rest;

public final class RestConstants {

	//Recursos de la aplicacion
    public static final String RESOURCES_PATH = "Corp/";
    public static final String RESOURCE_PROPERTIES_DIR = "AppProperties/";  
    public static final String RESOURCE_PROPERTIES_FILE = "ApplicationConfiguration.properties";
    public static final String RESOURCE_LOG4J_PROPERTIES_FILE = "SOD_CORP_Uxpos_Cliente_DocumentosEmitir_v1_1_log4j.properties";
    
    //Constantes de la aplicacion
    //root value properties corp.sod.finsac.Cheque.trasferencia.informar.
    
    public static final String ROOT = "rtl.sod.corp.sche.whmg";
    public static final String ENTITY = ".appointment";
    public static final String COUNTRY = "Country";
    public static final String SOURCE = ".source.tag";

    //Files
    public static final String XSD_DIR = ".XSD_Dir";
    public static final String XSD_FILE = ".XSD_File";
    public static final String XSL_DIR = ".XSL_Dir";
    public static final String XSL_FILE = ".XSL_File";

    public static final String XSL_FILE_HEADER = ".XSL_File_HEADER";
    //Tipos de colas
    public static final String QUEUE_NOTIFY_TYPE = "NOTIFY";
    public static final String QUEUE_NOTIFY_DELAY_TYPE = "NOTIFY_DELAY";
    
    //Queue target
    public static final String QP_NOTIFY_PROVIDER_URL = ".jms.url.notify";
    public static final String QP_NOTIFY_INITIAL_CONTXT_FACTORY =  ".context.provider.initialcontextfactory.notify";
    public static final String QP_NOTIFY_JMS_FACTORY = ".connection.factory.notify";
    public static final String QP_NOTIFY_QUEUE = ".topic.jndi.notify";
    public static final String QP_NOTIFY_USER = ".topic.user.notify";
    public static final String QP_NOTIFY_PASSWORD = ".topic.pass.notify";
    
  
  //Message header
  public static final String RETRY_QUANTITY_LABEL = ".RetriesLabel";
  public static final String RETRY_QUANTITY_VALUE = ".RetriesValue";
  public static final String DETAIL_EXCEPTION_LABEL = ".DetailExceptionLabel";
  public static final String DETAIL_EXCEPTION_VALUE = ".DetailExceptionValue";
  public static final String TRANSACTION_TYPE_LABEL = ".TransactionTypeLabel";
  public static final String TRANSACTION_TYPE_VALUE = ".TransactionTypeValue";
  public static final String BUSINESS_KEY_LABEL = ".BusinessKeyLabel";
  public static final String DELAY = ".Delay";
  public static final String COUNTRY_LABEL = ".CountryLabel";
  

    //XSL properties
    public static final String ENCODE = ".encode";

    //SAX
    public static final String SAX_LIBRARY = "net.sf.saxon.TransformerFactoryImpl";
    public static final String SAX_INDENT = "yes";
    public static final String SAX_METHOD = "xml";
	public static final int SUCCESS_UPDATE_CODE       	  = 200;
	public static final int INVALID_CUSTOMER_SUPPLIED_CODE= 400;
	public static final int CUSTOMER_ORDER_NOT_FOUND_CODE = 404;
	public static final int VALIDATION_EXCEPTION_CODE     = 405;
	
	public static final int LINE_NUMBER_MAX_CODE     = 30;
	public static final int ITEM_ID_MAX_CODE     = 100;
	public static final int NINE_INTEGER_NUMBER_CODE     = 9;
	public static final int TEN_INTEGER_NUMBER_CODE     = 10;
	
    public static final String API_PATH                   = "/api";
    public static final String VALIDATE_ERROR             = "Validate_Error";
    public static final String SERVICE_BASE_PACKAGE       = "rtl.sod.corp.sche.whmg.appointment.infraestructure.adapters.http.rest";
    public static final String SYSTEM_ERROR               = "System_Error";
    public static final String SUCCESS_RESPONSE           = "Success_Response";
    public static final int    SUCCESS_CODE               = 0;
    public static final int    ERROR_CODE                 = 1;
    public static final String JMS_FACTORY                = "jms_factory";
    public static final String JMS_TOPIC                  = "jms_topic";
    public static final String JMS_PATH                   = "jms_path";
    public static final String JMS_USER_NAME              = "jms_user_name";
    public static final String JMS_PASSWORD               = "jms_password";
    public static final String JMS_SELECTOR_CODE          = "orderId";
    public static final String JMS_XTXREF                 = "XtxRef";
    public static final String JMS_XCMREF                 = "XcmRef";
    public static final String JMS_XRHSREF                = "XrhsRef";
    public static final String JMS_USRTX                  = "XusrTx";
    public static final String JMS_COUNTRY                = "Xcountry";
    public static final String JMS_COMMERCE               = "Xcommerce";
    public static final String UPDATE_ORDER_ACTION        = "updateOrder";
    public static final String CANCEL_ORDER_ACTION        = "cancelOrder";
    public static final String FREE_MARKER_TEMPLATE_PATH = "templatePath";
    public static final String CANCEL_ORDER_TEMPLATE_FILE = "cancelOrderTemplate";
    public static final String UPDATE_ORDER_TEMPLATE_FILE = "updateOrderTemplate";

    public static final String TRANSACTION_REF_HEADER     = "X-txRef";
    public static final String CONSUMER_REF_HEADER        = "X-cmRef";
    public static final String NODE_REF_HEADER            = "X-nodeRef";

    public static final String TRANSACTION_REF_MDC        = "txRef";
    public static final String CONSUMER_REF_MDC           = "cmRef";
    public static final String NODE_REF_MDC               = "nodeRef";
    public static final String SERVICE_REF_MDC            = "srvRef";


	private RestConstants() {

	}
}
