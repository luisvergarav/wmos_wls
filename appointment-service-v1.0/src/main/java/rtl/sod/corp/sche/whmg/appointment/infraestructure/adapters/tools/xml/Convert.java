package rtl.sod.corp.sche.whmg.appointment.infraestructure.adapters.tools.xml;

public interface Convert<T> {

	
	String convert(T clazz) throws Exception;
}