package com.basdxz.vshade.shader;


import com.basdxz.vbuffers.common.Disposable;
import com.basdxz.vshade.layout.IVariableLayout;
import com.basdxz.vshade.query.IShaderQuery;
import com.basdxz.vshade.query.ShaderQuery;
import com.basdxz.vshade.type.GLSLType;
import com.basdxz.vshade.variable.linked.ILinkedVariable;
import lombok.*;
import org.lwjgl.opengl.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ShaderPeer implements IShaderPeer {
    protected final Set<IVariableLayout> layouts = new HashSet<>();
    protected final Map<Integer, ILinkedVariable<?, ?, ?>> vertexAttributes = new TreeMap<>();

    @Getter
    protected IShaderQuery query;
    protected boolean linked;
    @Getter
    protected int vertexStride;

    public void init(int program) {
        query = new ShaderQuery(program);
    }

    @Override
    public void addLayout(@NonNull IVariableLayout layout) {
        layouts.add(layout);
    }

    @Override
    public void link() {
        if (!linked) {
            layouts.forEach(IVariableLayout::link);
            compactLayout();
            linked = true;
        }
    }

    @Override
    public void addVertexAttribute(@NonNull ILinkedVariable<?, ?, ?> attribute) {
        vertexAttributes.put(attribute.location(), attribute);
    }

    @Override
    public ShaderPeer vertices(int vertices) {
        return this;
    }

    protected void compactLayout() {
        vertexStride = vertexAttributes.values().stream().mapToInt(GLSLType::typeSize).sum();
        var byteOffset = 0;
        for (val attribute : vertexAttributes.values()) {
            attribute.blockStride(vertexStride).offset(byteOffset);
            byteOffset += attribute.typeSize();
        }
    }

    @Override
    public void formatVAO(int vao) {
        for (val attribute : vertexAttributes.values()) {
            GL45.glEnableVertexArrayAttrib(vao, attribute.location());
            GL45.glVertexArrayAttribFormat(vao, attribute.location(),
                    attribute.rows() * attribute.columns(), attribute.primitiveType(),
                    false, 0);
            GL45.glVertexArrayBindingDivisor(vao, attribute.location(), 0);
            GL45.glVertexArrayAttribBinding(vao, attribute.location(), attribute.location());
        }
    }

    @Override
    public void formatVBO(int vao, int vbo) {
        for (val attribute : vertexAttributes.values())
            GL45.glVertexArrayVertexBuffer(vao, attribute.location(), vbo, attribute.offset(), vertexStride);
    }

    @Override
    public void dispose() {
        if (linked) {
            query = null;
            vertexStride = 0;
            layouts.forEach(Disposable::dispose);
            layouts.clear();
            vertexAttributes.clear();
            linked = false;
        }
    }
}
