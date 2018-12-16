package rtl.sod.corp.sche.whmg.appointment.domain.ports;

public interface CommandBus {

    public void execute(Command command) throws Exception;
}