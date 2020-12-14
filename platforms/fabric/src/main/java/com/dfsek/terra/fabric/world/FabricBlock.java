package com.dfsek.terra.fabric.world;

import com.dfsek.terra.api.generic.world.block.Block;
import com.dfsek.terra.api.generic.world.block.BlockData;
import com.dfsek.terra.api.generic.world.block.BlockFace;
import com.dfsek.terra.api.generic.world.block.MaterialData;
import com.dfsek.terra.api.generic.world.vector.Location;
import com.dfsek.terra.fabric.world.handles.world.FabricWorldAccess;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;

public class FabricBlock implements Block {
    private final Handle delegate;

    public FabricBlock(BlockState state, BlockPos position, WorldAccess worldAccess) {
        this.delegate = new Handle(state, position, worldAccess);
    }

    @Override
    public void setBlockData(BlockData data, boolean physics) {
        delegate.worldAccess.setBlockState(delegate.position, ((FabricBlockData) data).getHandle(), 0, 0);
    }

    @Override
    public BlockData getBlockData() {
        return new FabricBlockData(delegate.worldAccess.getBlockState(delegate.position));
    }

    @Override
    public Block getRelative(BlockFace face) {
        return getRelative(face, 1);
    }

    @Override
    public Block getRelative(BlockFace face, int len) {
        return new FabricBlock(delegate.state, delegate.position.add(face.getModX(), face.getModY(), face.getModZ()), delegate.worldAccess);
    }

    @Override
    public boolean isEmpty() {
        return delegate.state.isAir();
    }

    @Override
    public Location getLocation() {
        return FabricAdapters.toVector(delegate.position).toLocation(new FabricWorldAccess(delegate.worldAccess));
    }

    @Override
    public MaterialData getType() {
        return new FabricMaterialData(delegate.state.getMaterial());
    }

    @Override
    public int getX() {
        return delegate.position.getX();
    }

    @Override
    public int getZ() {
        return delegate.position.getZ();
    }

    @Override
    public int getY() {
        return delegate.position.getY();
    }

    @Override
    public boolean isPassable() {
        return delegate.state.isAir();
    }

    @Override
    public Handle getHandle() {
        return delegate;
    }

    public static final class Handle {
        private final BlockState state;
        private final BlockPos position;
        private final WorldAccess worldAccess;

        public Handle(BlockState state, BlockPos position, WorldAccess worldAccess) {
            this.state = state;
            this.position = position;
            this.worldAccess = worldAccess;
        }
    }
}
