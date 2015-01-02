package c1.command;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import common.ICommand;
import common.log.ILog;
import common.log.ILogManager;
import common.Registry;

public abstract class LoggedCommand
    extends Command
    implements ICommand
{
    public LoggedCommand(String command) {
        super(command);
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        log("Executing " + getCommandName());
        go();
    }

    abstract protected void go();
    
    private void log(String message) {
        getLog().log(message);
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(LoggedCommand.class);
    }

}