package pr0304Barracks.core.commands;

public class FightCommand extends Command{

    public FightCommand(String[] data) {
        super(data);
    }

    @Override
    public String execute() throws ReflectiveOperationException {
        return "fight";
    }
}
