package org.gti.minecraft.tntrun.utils.game;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.gti.minecraft.tntrun.Main;
import org.gti.minecraft.tntrun.utils.game.tasks.Active;
import org.gti.minecraft.tntrun.utils.game.tasks.Starting;
import org.gti.minecraft.tntrun.utils.game.tasks.Waiting;

public class GameManager {

    GameStates gameStates;

    public void setGameStates(GameStates gameStates) {
        if(this.gameStates == gameStates)return;
        this.gameStates = gameStates;
        Plugin plugin = Main.getPlugin(Main.class);

        switch (gameStates){

            case WAITING:
                Bukkit.getOnlinePlayers().stream().forEach(p -> p.kickPlayer("Game has ended!"));
                new Waiting(this).runTaskTimer(plugin, 0, 20);
                break;

            case STARTING:
                new Starting(this).runTaskTimer(plugin, 0, 20);
                break;
            case ACTIVE:
                new Active(this).runTaskTimer(plugin, 0, 20);
                break;

        }

    }

    public GameStates getGameStates(){return gameStates;}
}
