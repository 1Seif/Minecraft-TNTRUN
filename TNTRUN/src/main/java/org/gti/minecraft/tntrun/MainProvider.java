package org.gti.minecraft.tntrun;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.gti.minecraft.tntrun.utils.data.MySQL;
import org.gti.minecraft.tntrun.utils.files.FileManager;
import org.gti.minecraft.tntrun.utils.files.FileType;
import org.gti.minecraft.tntrun.utils.plugin.Provider;

import java.sql.DriverManager;

public class MainProvider implements Provider {

    public void onEnable() {
        FileManager fileManager = new FileManager();
        FileConfiguration cfg = fileManager.getFile(FileType.SETTINGS);

        MySQL mySQL = new MySQL(
                cfg.getString("mysql.host"),
                cfg.getInt("mysql.port"),
                cfg.getString("mysql.database"),
                cfg.getString("mysql.username"),
                cfg.getString("mysql.password"),
        cfg.getString("mysql.table"));

        mySQL.initialize();

    }

    public void onDisable() {



    }

}
