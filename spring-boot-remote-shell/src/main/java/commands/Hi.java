package commands;

import org.crsh.cli.Command;
import org.crsh.cli.Option;
import org.crsh.command.BaseCommand;

/**
 * @author April Han
 */
public class Hi extends BaseCommand {

    @Command
    public String main(@Option(names = {"n", "name"}) String name) {
        return "Hi " + name;
    }
}
