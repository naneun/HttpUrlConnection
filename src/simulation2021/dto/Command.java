package simulation2021.dto;

import java.util.ArrayList;
import java.util.List;

public class Command {

    private final Long truckId;
    private final List<Integer> commands = new ArrayList<>();

    private Command(Long truckId, List<Integer> commands) {
        this.truckId = truckId;
        this.commands.addAll(commands);
    }

    public static Command of(Long truckId, List<Integer> commands) {
        return new Command(truckId, commands);
    }
}
