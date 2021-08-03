package com.dfsek.terra.addons.terrascript.script.functions;

import com.dfsek.terra.addons.terrascript.api.ImplementationArguments;
import com.dfsek.terra.addons.terrascript.api.Returnable;
import com.dfsek.terra.addons.terrascript.api.Function;
import com.dfsek.terra.addons.terrascript.api.Variable;
import com.dfsek.terra.addons.terrascript.script.TerraImplementationArguments;
import com.dfsek.terra.addons.terrascript.api.Position;

import java.util.Map;

public class RandomFunction implements Function<Integer> {
    private final Returnable<Number> numberReturnable;
    private final Position position;

    public RandomFunction(Returnable<Number> numberReturnable, Position position) {
        this.numberReturnable = numberReturnable;
        this.position = position;
    }


    @Override
    public ReturnType returnType() {
        return ReturnType.NUMBER;
    }

    @Override
    public Integer apply(ImplementationArguments implementationArguments, Map<String, Variable<?>> variableMap) {
        return ((TerraImplementationArguments) implementationArguments).getRandom().nextInt(numberReturnable.apply(implementationArguments, variableMap).intValue());
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
