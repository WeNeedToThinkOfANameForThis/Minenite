package org.minenite.plugin.managers;

import com.google.inject.Singleton;
import org.bukkit.entity.Player;
import org.minenite.plugin.objects.MineNitePlayer;

import java.util.HashMap;
import java.util.UUID;

@Singleton
public final class PlayerManager {

    HashMap<UUID, MineNitePlayer> players = new HashMap<>();

    public MineNitePlayer getPlayer(Player p) {

        if(!players.containsKey(p.getUniqueId())){
            players.put(p.getUniqueId(),new MineNitePlayer(p));
        }
        return players.get(p.getUniqueId());

    }
    public void store(UUID uuid, MineNitePlayer MNPlayer){
        if(!players.containsKey(uuid)){
            players.put(uuid, MNPlayer);
        }
    }
    public void remove(UUID uuid){
        if(players.containsKey(uuid)){
            players.remove(uuid);
        }
    }

}
