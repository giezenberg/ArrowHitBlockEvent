package com.gmail.stevenpcc.arrowhitblockevent;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

/**
 * Created by Develop on 25-8-2015.
 */
public class Test implements Listener {

    public Test(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onArrow(ArrowHitBlockEvent e) {
        Block b = e.getBlock();
        Arrow a = e.getArrow();

        // Check if it is our arrow
        if (a.getCustomName().equalsIgnoreCase("SHOOT")) {
            if (a.getShooter() instanceof Player) {
                Player p = (Player) a.getShooter();

                // Do blocklogic
                switch (b.getType()) {
                    case LAPIS_BLOCK:
                        p.sendMessage(ChatColor.GREEN + "+10");
                        debug(e, p);
                        break;
                    case ANVIL:
                        p.sendMessage(ChatColor.GREEN + "+50");
                        debug(e, p);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    @EventHandler
    public void onSnowball(SnowballHitBlockEvent e) {
        Block b = e.getBlock();
        Snowball s = e.getSnowball();

        if (s.getCustomName().equalsIgnoreCase("SHOOT")) {
            if (s.getShooter() instanceof Player) {
                Player p = (Player) s.getShooter();

                // Do blocklogic
                switch (b.getType()) {
                    case LAPIS_BLOCK:
                        p.sendMessage(ChatColor.GREEN + "+10");
                        debug(e, p);
                        break;
                    case ANVIL:
                        p.sendMessage(ChatColor.GREEN + "+50");
                        debug(e, p);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    @EventHandler
    public void onShoot(ProjectileLaunchEvent e) {
        if (e.getEntity().getShooter() instanceof Player) {
            e.getEntity().setCustomName("SHOOT");
            e.getEntity().setCustomNameVisible(true);
        }
    }

    private void debug(ArrowHitBlockEvent e, Player p) {
        p.sendMessage(e.getBlock().getLocation().toString());
        p.sendMessage(e.getArrow().getCustomName());
    }

    private void debug(SnowballHitBlockEvent e, Player p) {
        p.sendMessage(e.getBlock().getLocation().toString());
        p.sendMessage(e.getSnowball().getCustomName());
    }


    private void addPoints(Player p, int points) {
        //do that fancy shit here.
    }
}
