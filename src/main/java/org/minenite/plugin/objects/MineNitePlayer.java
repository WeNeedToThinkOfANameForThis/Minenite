package org.minenite.plugin.objects;

import com.google.inject.Inject;
import lombok.Getter;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.minenite.plugin.MineNite;
import org.minenite.plugin.managers.PlayerManager;

import java.util.HashMap;
import java.util.Random;

@Getter
public class MineNitePlayer{
    Player player;
    int number;
    MineNite minenite = MineNite.getMineNite();


    @Inject private PlayerManager playerManager;
    public MineNitePlayer(Player p){
        number = new Random().nextInt(100 + 1);
        player = p;
    }

    public void sendMessage(String str){
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',str));
    }

    public void makeWait(Runnable runable, double maxDistance,int secs){
        //int taskID = 0;
        countdown(secs);
        Location startLocation = player.getLocation();

        int endTask = minenite.getServer().getScheduler().runTaskLater(minenite, new Runnable() {
            @Override
            public void run() {
                runable.run();
            }
        }, secs * 20).getTaskId();

        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 100f, 100f);

        int taskID = minenite.getServer().getScheduler().scheduleSyncRepeatingTask(minenite, new BukkitRunnable() {
            @Override
            public void run() {
                if (player.getLocation().distance(startLocation) > maxDistance) {
                    sendMessage("&cYou walked to far the pending action has been canceled");
                    if (minenite.getServer().getScheduler().isQueued(endTask)) {
                        minenite.getServer().getScheduler().cancelTask(endTask);
                        //minenite.getServer().getScheduler().cancelTask(taskID);
                    }
                } else {
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 100f, 100f);
                }
            }
        }, 20l, 20l);

        minenite.getServer().getScheduler().runTaskLater(minenite, new Runnable() {
            @Override
            public void run() {
                if(minenite.getServer().getScheduler().isQueued(taskID) || minenite.getServer().getScheduler().isCurrentlyRunning(taskID) ){
                    minenite.getServer().getScheduler().cancelTask(taskID);
                }
            }
        }, secs * 20);


    }

    public void countdown(int secs){
        //sendMessage("&a"+ (secs+1));
        for(int i = secs;i != 0;i--){
            int finalI = i;
            minenite.getServer().getScheduler().runTaskLater(minenite, new Runnable() {
                @Override
                public void run() {
                    player.sendTitle(ChatColor.translateAlternateColorCodes('&',"&a"+finalI),"",0,20,0);
                    sendMessage("&a"+ finalI);
                }
            }, (secs - i) * 20);
        }
    }
}
