package org.minenite.plugin.events;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.minenite.plugin.MineNite;
import org.minenite.plugin.managers.PlayerManager;
import org.minenite.plugin.objects.MineNitePlayer;

@Singleton
public final class TestEvents implements Listener {
    @Inject private PlayerManager playerManager;
    @Inject private MineNite minenite;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        MineNitePlayer mnPlayer = playerManager.getPlayer(e.getPlayer());
        Player p = mnPlayer.getPlayer();
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e){
        playerManager.remove(e.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onCrafting(CraftItemEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onCraft(PrepareItemCraftEvent e){
        e.getInventory().setResult(new ItemStack(Material.AIR));
    }

    @EventHandler
    public void onChestOpen(PlayerInteractEvent e){

        Block block = e.getClickedBlock();

        if(e.getClickedBlock().getType() == Material.CHEST){
            if(e.getPlayer().isSneaking()){
                return;
            }
            if(e.getAction() != Action.RIGHT_CLICK_BLOCK){
                return;
            }
            MineNitePlayer mnPlayer = playerManager.getPlayer(e.getPlayer());
            Chest chest = (Chest) block.getState();

            int items = 0;
            for(ItemStack is  : chest.getBlockInventory().getStorageContents()){
                if(is != null && is.getType() != Material.AIR){
                    items++;
                }
            }
            e.setCancelled(true);

            //mnPlayer.sendMessage("&c"+);
            if(items == 0){
               mnPlayer.sendMessage("&cThis chest is empty");
               return;
            }
            mnPlayer.makeWait(new Runnable() {
                @Override
                public void run() {

                    //mnPlayer.getPlayer().openInventory(chest.getBlockInventory());
                    Location loc = block.getLocation();
                    for(ItemStack is : chest.getBlockInventory().getContents()){
                        chest.getBlockInventory().remove(is);
                        if(is != null && is.getType() != Material.AIR){
                            loc.getWorld().dropItem(loc, is);
                        }
                    }
                    //loc.getBlock().setType(Material.AIR);

                }
            },5,3);
        }
    }
}
