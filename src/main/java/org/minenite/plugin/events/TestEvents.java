package org.minenite.plugin.events;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.minenite.plugin.MineNite;
import org.minenite.plugin.core.managers.PlayerManager;
import org.minenite.plugin.core.objects.MineNitePlayer;
import org.minenite.plugin.core.objects.buildings.FlatBuilding;

@Singleton
public final class TestEvents implements Listener {
    @Inject private PlayerManager playerManager;

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
        Player p = (Player) e.getWhoClicked();
        MineNitePlayer mnPlayer = playerManager.getPlayer(p);
        e.setCancelled(!mnPlayer.isAllowedToCraft());
    }

    @EventHandler
    public void onCraft(PrepareItemCraftEvent e){
        Player p = (Player) e.getViewers().get(0);
        MineNitePlayer mnPlayer = playerManager.getPlayer(p);
        if(!mnPlayer.isAllowedToCraft()) {
            e.getInventory().setResult(new ItemStack(Material.AIR));
        }
    }

    @EventHandler
    public void buildingAttempt(PlayerInteractEvent e){
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(e.getPlayer().getItemInHand() != null && e.getPlayer().getItemInHand().getType() == Material.WOOD_STEP){
                Player p  = e.getPlayer();
                MineNitePlayer MNPlayer = playerManager.getPlayer(p);
                MNPlayer.sendMessage("&aBuilding Now");
                FlatBuilding build = new FlatBuilding();

                build.build(p.getLocation().subtract(build.getSizeX()/2,1,build.getSizeZ()/2));
            }
        }
    }

    @EventHandler
    public void onChestOpen(PlayerInteractEvent e){
        Block block = e.getClickedBlock();

        if(e.getAction() != Action.RIGHT_CLICK_BLOCK){
            return;
        }
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
            for(ItemStack is  : chest.getBlockInventory().getContents()){
                if(is != null && is.getType() != Material.AIR){
                    items++;
                }
            }
            e.setCancelled(true);

            //mnPlayer.sendMessage("&c"+);
            if(items == 0) {
               mnPlayer.sendMessage("&cThis chest is empty");
               Player player = e.getPlayer();
                //player.playSound(player.getLocation(), Sound.BLOCK_CHEST_LOCKED, 100f, 100f);
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

                    MineNite mineNite = MineNite.getPlugin(MineNite.class);
                    Location hearts = loc.add(0.5,0.5,0.5);
                    mineNite.getScheduler().scheduleSyncRepeatingTask(mineNite, new Runnable() {
                        @Override
                        public void run() {
                           // loc.getWorld().playEffect(hearts, Effect.HEART,20);

                        }
                    },10,10);
                    //loc.getBlock().setType(Material.AIR);

                }
            },5,3);
        }
    }
}
