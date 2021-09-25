package com.dfsek.terra.addons.terrascript.buffer.items;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dfsek.terra.api.TerraPlugin;
import com.dfsek.terra.api.block.entity.BlockEntity;
import com.dfsek.terra.api.structure.buffer.BufferedItem;
import com.dfsek.terra.api.util.vector.Vector3;
import com.dfsek.terra.api.world.World;


public class BufferedStateManipulator implements BufferedItem {
    private static final Logger logger = LoggerFactory.getLogger(BufferedStateManipulator.class);
    
    private final TerraPlugin main;
    private final String data;
    
    public BufferedStateManipulator(TerraPlugin main, String state) {
        this.main = main;
        this.data = state;
    }
    
    @Override
    public void paste(Vector3 origin, World world) {
        try {
            BlockEntity state = world.getBlockState(origin);
            state.applyState(data);
            state.update(false);
        } catch(Exception e) {
            logger.warn("Could not apply BlockState at {}", origin, e);
        }
    }
}
