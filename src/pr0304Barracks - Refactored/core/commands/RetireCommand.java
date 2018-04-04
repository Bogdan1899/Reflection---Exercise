package pr0304Barracks.core.commands;

import pr0304Barracks.annotations.Inject;
import pr0304Barracks.contracts.Repository;

public class RetireCommand extends Command{

    @Inject
    private Repository repository;

    public RetireCommand(String[] data) {
        super(data);
    }

    @Override
    public String execute() throws ReflectiveOperationException {
        String unitType = super.getData()[1];

        try {
            this.repository.removeUnit(unitType);
            return unitType + " retired!";
        } catch (IllegalArgumentException ex){
            return ex.getMessage();
        }
    }
}
