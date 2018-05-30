package org.minenite.plugin.core.objects;

import com.google.inject.Inject;
import lombok.Data;
import lombok.Getter;

import lombok.Setter;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.minenite.plugin.MineNite;
import org.minenite.plugin.core.managers.PlayerManager;
import org.minenite.plugin.core.objects.events.MinenitePlayerBuildEvent;
import org.minenite.plugin.core.objects.interfaces.CountdownFunction;
import org.minenite.plugin.core.utils.time.TimeUtils;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

@Getter
public class MineNitePlayer {
    @Inject private MineNite mineNite;
    @Inject private PlayerManager playerManager;
    private TimeUtils tu;
    Player player;
    int number;

    @Setter boolean allowedToCraft;

    public MineNitePlayer(Player p){
        number = new Random().nextInt(100 + 1);
        player = p;
        allowedToCraft = true;
        tu = new TimeUtils();
        mineNite = MineNite.getPlugin(MineNite.class);
    }

    public void sendMessage(String str){
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',str));
    }

    public void makeWait(Runnable runnable, double maxDistance,int secs){
        //int taskID = 0;


        countdown(secs);
        Location startLocation = player.getLocation();

        int endTask = mineNite.getScheduler().runTaskLater(mineNite, new Runnable() {
            @Override
            public void run() {
                runnable.run();
            }
        }, secs * 20).getTaskId();

        //player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 100f, 100f);

        int taskID = mineNite.getScheduler().scheduleSyncRepeatingTask(mineNite, new BukkitRunnable() {
            @Override
            public void run() {
                if (player.getLocation().distance(startLocation) > maxDistance) {
                    sendMessage("&cYou walked to far the pending action has been canceled");
                    if (mineNite.getScheduler().isQueued(endTask)) {
                        mineNite.getScheduler().cancelTask(endTask);
                    }
                } else {
                 //   player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 100f, 100f);
                }
            }
        }, 20L, 20L);

        mineNite.getScheduler().runTaskLater(mineNite, new Runnable() {
            @Override
            public void run() {
                if(mineNite.getScheduler().isQueued(taskID) || mineNite.getScheduler().isCurrentlyRunning(taskID) ){
                    mineNite.getScheduler().cancelTask(taskID);
                }
            }
        }, secs * 20);


    }

    public void countdown(int secs){
        //sendMessage("&a"+ (secs+1));
        Float startExp = player.getExp();
        int startLevel = player.getLevel();

        for(int i = secs;i != 0;i--){
            int finalI = i;
            tu.countdown(secs - i, () -> {
                player.setLevel(finalI);
                float f = Float.parseFloat((((100/secs)*finalI)/100)+"");
                player.setExp(f);
               // player.sendTitle(ChatColor.translateAlternateColorCodes('&',"&a"+finalI),"",0,25,0);
                sendMessage("&a"+ finalI);
            });
        }

        tu.countdown(secs + 1, () -> {
            player.setLevel(startLevel);
            player.setExp(startExp);
        });
    }

    public void build(Building building,Location startLoc){
        MinenitePlayerBuildEvent event = new MinenitePlayerBuildEvent(this, building, startLoc);
        // Call the event
        mineNite.getServer().getPluginManager().callEvent(event);
        // Check if the event is not cancelled
        if (!event.isCancelled()) {
            // Now you do the event
            event.getBuilding().build(event.getLocation());
        }

    }
}
