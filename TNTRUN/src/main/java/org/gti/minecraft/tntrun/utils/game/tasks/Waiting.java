package org.gti.minecraft.tntrun.utils.game.tasks;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.gti.minecraft.tntrun.Main;
import org.gti.minecraft.tntrun.utils.game.GameManager;
import org.gti.minecraft.tntrun.utils.game.GameStates;
import org.gti.minecraft.tntrun.utils.title.Title;
import org.gti.minecraft.tntrun.utils.title.TitleType;

public class Waiting extends BukkitRunnable {

    GameManager manager;
    public Waiting(GameManager gameManager){this.manager = gameManager;}


    @Override
    public void run() {

        if(Bukkit.getServer().getOnlinePlayers().size() < 2){

            Title title = new Title(TitleType.SUBTITLE);
            title.setTitle(Main.getMain().colorWith("&fGame must contain at least &b2&f players"));
            Bukkit.getOnlinePlayers().forEach(title::send);

        } else {
            manager.setGameStates(GameStates.STARTING);
            cancel();
        }

    }

}
