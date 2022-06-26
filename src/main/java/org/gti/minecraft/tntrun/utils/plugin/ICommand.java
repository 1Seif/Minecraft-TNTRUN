package org.gti.minecraft.tntrun.utils.plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.gti.minecraft.tntrun.Main;

public abstract class ICommand implements CommandExecutor {

    private final ACommand aCommand;


    public ICommand(){
        aCommand = getClass().getDeclaredAnnotation(ACommand.class);
    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String strings[]) {

        if(!getaCommand().permission().isEmpty()){
            if(!commandSender.hasPermission(getaCommand().permission())) {
                commandSender.sendMessage(Main.getMain().colorWith("&cOnly admins or higher can access this command."));
                return true;
            }
        }

        if(getaCommand().requiresPlayer()){
            if(!(commandSender instanceof Player)){
                commandSender.sendMessage("You must be a Player to execute.");
            }
            execute((Player) commandSender, strings);
            return true;
        }

        execute(commandSender, strings);
        return true;
    }

    public void execute(CommandSender sender, String args[]){}
    public void execute(Player p, String args[]){}

    public ACommand getaCommand(){return aCommand;}
}
