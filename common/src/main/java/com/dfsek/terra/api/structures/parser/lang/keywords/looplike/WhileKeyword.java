package com.dfsek.terra.api.structures.parser.lang.keywords.looplike;

import com.dfsek.terra.api.structures.parser.lang.Block;
import com.dfsek.terra.api.structures.parser.lang.Keyword;
import com.dfsek.terra.api.structures.parser.lang.Returnable;
import com.dfsek.terra.api.structures.structure.Rotation;
import com.dfsek.terra.api.structures.structure.buffer.Buffer;
import com.dfsek.terra.api.structures.tokenizer.Position;

import java.util.Random;

public class WhileKeyword implements Keyword<Block.ReturnInfo<?>> {
    private final Block conditional;
    private final Returnable<Boolean> statement;
    private final Position position;

    public WhileKeyword(Block conditional, Returnable<Boolean> statement, Position position) {
        this.conditional = conditional;
        this.statement = statement;
        this.position = position;
    }

    @Override
    public Block.ReturnInfo<?> apply(Buffer buffer, Rotation rotation, Random random, int recursions) {
        while(statement.apply(buffer, rotation, random, recursions)) {
            Block.ReturnInfo<?> level = conditional.apply(buffer, rotation, random, recursions);
            if(level.getLevel().equals(Block.ReturnLevel.BREAK)) break;
            if(level.getLevel().isReturnFast()) return level;
        }
        return new Block.ReturnInfo<>(Block.ReturnLevel.NONE, null);
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public ReturnType returnType() {
        return ReturnType.VOID;
    }
}
