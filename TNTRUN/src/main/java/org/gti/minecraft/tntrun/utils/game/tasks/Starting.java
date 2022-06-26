package org.gti.minecraft.tntrun.utils.game.tasks;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.gti.minecraft.tntrun.utils.game.GameManager;
import org.gti.minecraft.tntrun.utils.game.GameStates;
import org.gti.minecraft.tntrun.utils.title.Title;
import org.gti.minecraft.tntrun.utils.title.TitleType;

public class Starting extends BukkitRunnable {

    private int counter = 20;
    GameManager manager;


    public Starting(GameManager manager){this.manager = manager;}


    @Override
    public void run() {
        counter--;

        if(counter == 3){
            if(Bukkit.getOnlinePlayers().size() < 2){
                manager.setGameStates(GameStates.WAITING);
                Bukkit.getOnlinePlayers().forEach(o -> o.sendMessage("&e(!) &fThere are no enough players!"));
                cancel();
            }
        }

        if(counter <= 0){
            cancel();
            manager.setGameStates(GameStates.ACTIVE);
            return;
        }

        Title title = new Title(TitleType.SUBTITLE);
        title.setTitle(this.counter + "&bs until game starts..");
        Bukkit.getOnlinePlayers().stream().forEach(title::send);

    }

}
