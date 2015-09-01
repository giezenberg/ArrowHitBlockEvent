package com.gmail.stevenpcc.arrowhitblockevent;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftArrow;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftSnowball;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;

public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Test test = new Test(this);
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
    }

    @EventHandler
    private void onProjectileHit(final ProjectileHitEvent e) {
        if (e.getEntityType() == EntityType.ARROW) {
            // Must be run in a delayed task otherwise it won't be able to find the block
            Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                @Override
                public void run() {
                    try {
                        net.minecraft.server.v1_8_R3.EntityArrow entityArrow = ((CraftArrow) e.getEntity()).getHandle();

                        Field fieldX = net.minecraft.server.v1_8_R3.EntityArrow.class.getDeclaredField("d");
                        Field fieldY = net.minecraft.server.v1_8_R3.EntityArrow.class.getDeclaredField("e");
                        Field fieldZ = net.minecraft.server.v1_8_R3.EntityArrow.class.getDeclaredField("f");

                        fieldX.setAccessible(true);
                        fieldY.setAccessible(true);
                        fieldZ.setAccessible(true);

                        int x = fieldX.getInt(entityArrow);
                        int y = fieldY.getInt(entityArrow);
                        int z = fieldZ.getInt(entityArrow);

                        if (isValidBlock(y)) {
                            Block block = e.getEntity().getWorld().getBlockAt(x, y, z);
                            Bukkit.getServer().getPluginManager().callEvent(new ArrowHitBlockEvent((Arrow) e.getEntity(), block));
                        }

                    } catch (NoSuchFieldException | SecurityException | IllegalAccessException | IllegalArgumentException e1) {
                        e1.printStackTrace();
                    }
                }
            });

        }

        if (e.getEntityType() == EntityType.SNOWBALL) {
            // Must be run in a delayed task otherwise it won't be able to find the block
            Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                @Override
                public void run() {
                    try {
                        net.minecraft.server.v1_8_R3.EntitySnowball entitySnowball = ((CraftSnowball) e.getEntity()).getHandle();

                        Field fieldX = net.minecraft.server.v1_8_R3.EntitySnowball.class.getDeclaredField("d");
                        Field fieldY = net.minecraft.server.v1_8_R3.EntitySnowball.class.getDeclaredField("e");
                        Field fieldZ = net.minecraft.server.v1_8_R3.EntitySnowball.class.getDeclaredField("f");

                        fieldX.setAccessible(true);
                        fieldY.setAccessible(true);
                        fieldZ.setAccessible(true);

                        int x = fieldX.getInt(entitySnowball);
                        int y = fieldY.getInt(entitySnowball);
                        int z = fieldZ.getInt(entitySnowball);

                        if (isValidBlock(y)) {
                            Block block = e.getEntity().getWorld().getBlockAt(x, y, z);
                            Bukkit.getServer().getPluginManager().callEvent(new SnowballHitBlockEvent((Snowball) e.getEntity(), block));
                        }

                    } catch (NoSuchFieldException | SecurityException | IllegalAccessException | IllegalArgumentException e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
    }

    // If the arrow hits a mob or player the y coord will be -1
    private boolean isValidBlock(int y) {
        return y != -1;
    }

}
