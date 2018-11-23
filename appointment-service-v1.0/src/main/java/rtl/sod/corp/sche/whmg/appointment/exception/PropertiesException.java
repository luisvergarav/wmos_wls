package rtl.sod.corp.sche.whmg.appointment.exception;

/**
 * Excepcion de propiedades.
 */
public class PropertiesException extends Exception {

    @SuppressWarnings("compatibility")
    private static final long serialVersionUID = -761319671120711046L;

    public PropertiesException(Throwable throwable) {
        super(throwable);
    }

    public PropertiesException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public PropertiesException(String string) {
        super(string);
    }

    public PropertiesException() {
        super();
    }
}