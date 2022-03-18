package com.basdxz.vshade.shader;


import com.basdxz.vbuffers.common.Disposable;
import com.basdxz.vshade.layout.IVariableLayout;
import com.basdxz.vshade.query.IShaderQuery;
import com.basdxz.vshade.variable.ILinkedVariable;
import lombok.*;

public interface IShaderPeer extends Disposable {
    IShaderQuery query();

    void addLayout(@NonNull IVariableLayout layout);

    void link();

    void addVertexAttribute(@NonNull ILinkedVariable<?, ?, ?> attribute);

    int vertexStride();

    void formatVAO(int vbo);

    void formatVBO(int vbo, int vba);
}
