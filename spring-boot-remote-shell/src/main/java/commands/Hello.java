package commands;

import org.crsh.cli.Command;
import org.crsh.command.BaseCommand;

/**
 * @author April Han
 */
public class Hello extends BaseCommand {

    @Command
    public String main() {
        return "Hello World";
    }
}
