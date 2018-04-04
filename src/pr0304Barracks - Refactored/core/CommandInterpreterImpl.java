package pr0304Barracks.core;

import pr0304Barracks.annotations.Inject;
import pr0304Barracks.contracts.CommandInterpreter;
import pr0304Barracks.contracts.Executable;
import pr0304Barracks.contracts.Repository;
import pr0304Barracks.contracts.UnitFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class CommandInterpreterImpl implements CommandInterpreter{

    private static final String PATH = "pr0304Barracks.core.commands.";
    private static final String SUFFIX = "Command";

    private UnitFactory unitFactory;
    private Repository unitRepository;

    public CommandInterpreterImpl(UnitFactory unitFactory, Repository unitRepository) {
        this.unitFactory = unitFactory;
        this.unitRepository = unitRepository;
    }

    @Override
    public Executable interpretCommand(String[] data, String commandName) throws ReflectiveOperationException {
        Class executableClass = Class.forName(
                PATH + commandName.toUpperCase().charAt(0) + commandName.substring(1, commandName.length()) + SUFFIX);
        Constructor constructor = executableClass.getConstructor(String[].class);
        Executable executable = (Executable) constructor.newInstance((Object) data);

        Field[] executableFields = executableClass.getDeclaredFields();
        Field[] allCommandFields = this.getClass().getDeclaredFields();

        for (Field executableField : executableFields) {
            executableField.setAccessible(true);

            if (executableField.isAnnotationPresent(Inject.class)){
                for (Field allCommandField : allCommandFields) {
                    allCommandField.setAccessible(true);

                    if (executableField.getType().equals(allCommandField.getType())){
                        executableField.set(executable, allCommandField.get(this));
                    }
                }
            }
        }

        return executable;
    }
}
