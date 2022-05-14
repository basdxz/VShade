package com.basdxz.vshade.query.newstuff;

import java.nio.IntBuffer;
import java.util.List;

public interface IQueryProgramInterface {
    int interfaceType();

    List<IQueryProgramResource> resources();

    IntBuffer properties();
}
