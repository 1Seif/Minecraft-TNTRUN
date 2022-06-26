package org.gti.minecraft.tntrun.utils.files;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class FileManager {
    final File file = new File("./plugins/TNT-RUN/plugin", "settings.yml;");
    final FileConfiguration cfg = (FileConfiguration)YamlConfiguration.loadConfiguration(file);

    public void initialize(){

        if(!file.exists()){
            System.out.println("[FileManager] directory isn't exist, generating new one..");
            try {

                file.createNewFile();

                cfg.set("mysql.host", "localhost");
                cfg.set("mysql.port", 3306);
                cfg.set("mysql.database", "servername");
                cfg.set("mysql.table", "playersdata");
                cfg.set("mysql.username", "root");
                cfg.set("mysql.password", "");

                cfg.save(file);
                cfg.load(file);

            } catch (Exception e){e.printStackTrace();}
        } else {
            System.out.println("[FileManager] directory found. ignoring it.");
        }

    }

    public FileConfiguration getFile(FileType type){

        if(type == FileType.SETTINGS)return cfg;

        return null;
    }

}
