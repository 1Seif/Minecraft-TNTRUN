package org.gti.minecraft.tntrun.utils.title;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.apache.commons.lang.NullArgumentException;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Title {

    TitleType titleType;
    String title;
    String subtitle;

    public Title(TitleType titleType){
        this.titleType = titleType;
    }

    public Title setTitle(String msg){this.title = msg;return this;}
    public Title setSubTitle(String msg){this.title = msg;return this;}

    public void send(Player p){
        if(title.isEmpty())
            throw new NullArgumentException("Title cannot be null");

        if(this.titleType == TitleType.TITLE)
            p.sendTitle(this.title, this.subtitle);

        if(this.titleType == TitleType.SUBTITLE){
            IChatBaseComponent component = IChatBaseComponent.ChatSerializer.a("{\"text\":\""+this.title+"\"}");
            PacketPlayOutChat packet = new PacketPlayOutChat(component, (byte)2);
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
        }

    }

}
