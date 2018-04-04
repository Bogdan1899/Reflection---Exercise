package pr0304Barracks.contracts;

public interface CommandInterpreter {

	Executable interpretCommand(String[] data, String commandName) throws ClassNotFoundException, IllegalAccessException, InstantiationException, ReflectiveOperationException;
}
