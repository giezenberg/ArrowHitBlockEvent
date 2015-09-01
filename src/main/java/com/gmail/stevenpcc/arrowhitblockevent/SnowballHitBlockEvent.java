package com.gmail.stevenpcc.arrowhitblockevent;

import org.bukkit.block.Block;
import org.bukkit.entity.Snowball;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockEvent;

public class SnowballHitBlockEvent extends BlockEvent {

    private static final HandlerList handlers = new HandlerList();

    private Snowball snowball;

    public SnowballHitBlockEvent(Snowball snowball, Block block) {
        super(block);
        this.snowball = snowball;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Snowball getSnowball() {
        return snowball;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

}
