package org.gti.minecraft.tntrun.utils.plugin;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.gti.minecraft.tntrun.Main;

import java.util.Arrays;

public class PluginManager {

    public void initExecutors(Main main, ICommand... iCommands){

        for(ICommand iCommand : iCommands){
            main.getCommand(iCommand.getaCommand().name()).setExecutor(iCommand);
            System.out.println("[PluginManager] loaded command /"+iCommand.getaCommand().name()+" from "+iCommand.getClass().getName());
        }

    }

    public void initListeners(Main main, Listener... listeners){

        for(Listener listener : listeners){
            Bukkit.getServer().getPluginManager().registerEvents(listener, Main.getPlugin(Main.class));
            System.out.println("[PluginManager] loaded listener "+listener.getClass().getSimpleName()+" from "+listener.getClass().getName());
        }

    }

}
