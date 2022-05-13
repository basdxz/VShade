package com.basdxz.vshade.query.newstuff;

import com.basdxz.vbuffers.common.MemUtils;
import lombok.*;

import java.nio.IntBuffer;

import static com.basdxz.vshade.query.newstuff.QueryProgramResource.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL42.*;
import static org.lwjgl.opengl.GL43.*;

/*
    References: https://www.khronos.org/registry/OpenGL-Refpages/gl4/html/glGetProgramInterface.xhtml
 */
@Getter
public enum QueryProgramInterface implements IQueryProgramInterface {
    UNIFORM(GL_UNIFORM, NAME_LENGTH, TYPE, ARRAY_SIZE, OFFSET, BLOCK_INDEX, ARRAY_STRIDE, MATRIX_STRIDE, IS_ROW_MAJOR, ATOMIC_COUNTER_BUFFER_INDEX, REFERENCED_BY_VERTEX_SHADER, REFERENCED_BY_TESS_CONTROL_SHADER, REFERENCED_BY_TESS_EVALUATION_SHADER, REFERENCED_BY_GEOMETRY_SHADER, REFERENCED_BY_FRAGMENT_SHADER, REFERENCED_BY_COMPUTE_SHADER, LOCATION),
    UNIFORM_BLOCK(GL_UNIFORM_BLOCK),
    ATOMIC_COUNTER_BUFFER(GL_ATOMIC_COUNTER_BUFFER),
    PROGRAM_INPUT(GL_PROGRAM_INPUT),
    PROGRAM_OUTPUT(GL_PROGRAM_OUTPUT),
    VERTEX_SUBROUTINE(GL_VERTEX_SUBROUTINE),
    TESS_CONTROL_SUBROUTINE(GL_TESS_CONTROL_SUBROUTINE),
    TESS_EVALUATION_SUBROUTINE(GL_TESS_EVALUATION_SUBROUTINE),
    GEOMETRY_SUBROUTINE(GL_GEOMETRY_SUBROUTINE),
    FRAGMENT_SUBROUTINE(GL_FRAGMENT_SUBROUTINE),
    COMPUTE_SUBROUTINE(GL_COMPUTE_SUBROUTINE),
    VERTEX_SUBROUTINE_UNIFORM(GL_VERTEX_SUBROUTINE_UNIFORM),
    TESS_CONTROL_SUBROUTINE_UNIFORM(GL_TESS_CONTROL_SUBROUTINE_UNIFORM),
    TESS_EVALUATION_SUBROUTINE_UNIFORM(GL_TESS_EVALUATION_SUBROUTINE_UNIFORM),
    GEOMETRY_SUBROUTINE_UNIFORM(GL_GEOMETRY_SUBROUTINE_UNIFORM),
    FRAGMENT_SUBROUTINE_UNIFORM(GL_FRAGMENT_SUBROUTINE_UNIFORM),
    COMPUTE_SUBROUTINE_UNIFORM(GL_COMPUTE_SUBROUTINE_UNIFORM),
    TRANSFORM_FEEDBACK_VARYING(GL_TRANSFORM_FEEDBACK_VARYING),
    BUFFER_VARIABLE(GL_BUFFER_VARIABLE),
    SHADER_STORAGE_BLOCK(GL_SHADER_STORAGE_BLOCK),
    TRANSFORM_FEEDBACK_BUFFER(GL_TRANSFORM_FEEDBACK_BUFFER);

    private final int interfaceType;
    private final IntBuffer properties;

    QueryProgramInterface(int interfaceType, IQueryProgramResource... programResources) {
        this.interfaceType = interfaceType;
        if (programResources.length < 1)
            throw new IllegalArgumentException("Argument programResources can't be less than one.");

        val tempProperties = MemUtils.newIntBuffer(programResources.length);
        for (int i = 0; i < programResources.length; i++)
            tempProperties.put(i, programResources[i].programResourceType());
        properties = tempProperties.asReadOnlyBuffer();
    }
}
