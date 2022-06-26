package org.gti.minecraft.tntrun;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.gti.minecraft.tntrun.utils.files.FileManager;
import org.gti.minecraft.tntrun.utils.game.GameManager;
import org.gti.minecraft.tntrun.utils.game.GameStates;
import org.gti.minecraft.tntrun.utils.plugin.Provider;

public class Main extends JavaPlugin {

    public String colorWith(String g){return ChatColor.translateAlternateColorCodes('&', g);}
    private static Main main;
    private GameManager gameManager;


    @Override
    public void onEnable() {
        main = this;

        Provider provider = new MainProvider();
        provider.onEnable();

        FileManager fileManager = new FileManager();
        fileManager.initialize();

        gameManager = new GameManager();
        gameManager.setGameStates(GameStates.WAITING);

        this.intro();

    }

    @Override
    public void onDisable() {
        Provider provider = new MainProvider();
        provider.onDisable();
    }


    private void intro(){

        Bukkit.getServer().getConsoleSender().sendMessage(colorWith("&f----------&r[&a&lENABLED&f]&f----------"));
        Bukkit.getServer().getConsoleSender().sendMessage("");
        Bukkit.getServer().getConsoleSender().sendMessage(colorWith("&fPlugin: &b")+this.getDescription().getName());
        Bukkit.getServer().getConsoleSender().sendMessage(colorWith("&fCoder: &b")+this.getDescription().getAuthors().get(0));
        Bukkit.getServer().getConsoleSender().sendMessage(colorWith("&fVersion: &b")+this.getDescription().getVersion());
        Bukkit.getServer().getConsoleSender().sendMessage("");
        Bukkit.getServer().getConsoleSender().sendMessage(colorWith("&f----------[&a&lENABLED&f]&f----------"));


    }

    public static Main getMain(){return main;}
}
