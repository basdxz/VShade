package com.basdxz.vshade.type;

import com.basdxz.vshade.exception.ShaderException;
import com.google.common.collect.ImmutableMap;
import lombok.*;
import org.lwjgl.opengl.*;

import static com.basdxz.vbuffers.common.Constants.*;

/*
    Reference: https://www.khronos.org/registry/OpenGL-Refpages/gl4/html/glGetActiveUniform.xhtml
 */
@Getter
public enum GLSLTypes implements GLSLType {
    NULL(new Builder(NULL_STRING, NULL_INT, NULL_INT)),

    FLOAT(floatBuilder("float", GL11.GL_FLOAT)),
    FLOAT_VEC2(floatBuilder("vec2", GL20.GL_FLOAT_VEC2).rows(2)),
    FLOAT_VEC3(floatBuilder("vec3", GL20.GL_FLOAT_VEC3).rows(3)),
    FLOAT_VEC4(floatBuilder("vec4", GL20.GL_FLOAT_VEC4).rows(4)),

    DOUBLE(doubleBuilder("double", GL11.GL_DOUBLE)),
    DOUBLE_VEC2(doubleBuilder("dvec2", GL40.GL_DOUBLE_VEC2).rows(2)),
    DOUBLE_VEC3(doubleBuilder("dvec3", GL40.GL_DOUBLE_VEC3).rows(3)),
    DOUBLE_VEC4(doubleBuilder("dvec4", GL40.GL_DOUBLE_VEC4).rows(4)),

    INT(intBuilder("int", GL11.GL_INT)),
    INT_VEC2(intBuilder("ivec2", GL20.GL_INT_VEC2).rows(2)),
    INT_VEC3(intBuilder("ivec3", GL20.GL_INT_VEC3).rows(3)),
    INT_VEC4(intBuilder("ivec4", GL20.GL_INT_VEC4).rows(4)),

    UNSIGNED_INT(uintBuilder("unsigned int", GL11.GL_UNSIGNED_INT)),
    UNSIGNED_INT_VEC2(uintBuilder("uvec2", GL30.GL_UNSIGNED_INT_VEC2).rows(2)),
    UNSIGNED_INT_VEC3(uintBuilder("uvec3", GL30.GL_UNSIGNED_INT_VEC3).rows(3)),
    UNSIGNED_INT_VEC4(uintBuilder("uvec4", GL30.GL_UNSIGNED_INT_VEC4).rows(4)),

    BOOL(booleanBuilder("bool", GL20.GL_BOOL)),
    BOOL_VEC2(booleanBuilder("bvec2", GL20.GL_BOOL_VEC2).rows(2)),
    BOOL_VEC3(booleanBuilder("bvec3", GL20.GL_BOOL_VEC3).rows(3)),
    BOOL_VEC4(booleanBuilder("bvec4", GL20.GL_BOOL_VEC4).rows(4)),

    FLOAT_MAT2(floatBuilder("mat2", GL20.GL_FLOAT_MAT2).rows(2).columns(2)),
    FLOAT_MAT3(floatBuilder("mat3", GL20.GL_FLOAT_MAT3).rows(3).columns(3)),
    FLOAT_MAT4(floatBuilder("mat4", GL20.GL_FLOAT_MAT4).rows(4).columns(4)),

    FLOAT_MAT2X3(floatBuilder("mat2x3", GL21.GL_FLOAT_MAT2x3).rows(2).columns(3)),
    FLOAT_MAT2X4(floatBuilder("mat2x4", GL21.GL_FLOAT_MAT2x4).rows(2).columns(4)),
    FLOAT_MAT3X2(floatBuilder("mat3x2", GL21.GL_FLOAT_MAT3x2).rows(3).columns(2)),
    FLOAT_MAT3X4(floatBuilder("mat3x4", GL21.GL_FLOAT_MAT3x4).rows(3).columns(4)),
    FLOAT_MAT4X2(floatBuilder("mat4x2", GL21.GL_FLOAT_MAT4x2).rows(4).columns(2)),
    FLOAT_MAT4X3(floatBuilder("mat4x3", GL21.GL_FLOAT_MAT4x3).rows(4).columns(3)),

    DOUBLE_MAT2(doubleBuilder("dmat2", GL40.GL_DOUBLE_MAT2).rows(2).columns(2)),
    DOUBLE_MAT3(doubleBuilder("dmat3", GL40.GL_DOUBLE_MAT3).rows(3).columns(3)),
    DOUBLE_MAT4(doubleBuilder("dmat4", GL40.GL_DOUBLE_MAT4).rows(4).columns(4)),

    DOUBLE_MAT2X3(doubleBuilder("dmat2x3", GL40.GL_DOUBLE_MAT2x3).rows(2).columns(3)),
    DOUBLE_MAT2X4(doubleBuilder("dmat2x4", GL40.GL_DOUBLE_MAT2x4).rows(2).columns(4)),
    DOUBLE_MAT3X2(doubleBuilder("dmat3x2", GL40.GL_DOUBLE_MAT3x2).rows(3).columns(2)),
    DOUBLE_MAT3X4(doubleBuilder("dmat3x4", GL40.GL_DOUBLE_MAT3x4).rows(3).columns(4)),
    DOUBLE_MAT4X2(doubleBuilder("dmat4x2", GL40.GL_DOUBLE_MAT4x2).rows(4).columns(2)),
    DOUBLE_MAT4X3(doubleBuilder("dmat4x3", GL40.GL_DOUBLE_MAT4x3).rows(4).columns(3)),

    SAMPLER_1D(samplerBuilder("sampler1D", GL20.GL_SAMPLER_1D)),
    SAMPLER_2D(samplerBuilder("sampler2D", GL20.GL_SAMPLER_2D)),
    SAMPLER_3D(samplerBuilder("sampler3D", GL20.GL_SAMPLER_3D)),
    SAMPLER_CUBE(samplerBuilder("samplerCube", GL20.GL_SAMPLER_CUBE)),
    SAMPLER_1D_SHADOW(samplerBuilder("sampler1DShadow", GL20.GL_SAMPLER_1D_SHADOW)),
    SAMPLER_2D_SHADOW(samplerBuilder("sampler2DShadow", GL20.GL_SAMPLER_2D_SHADOW)),
    SAMPLER_1D_ARRAY(samplerBuilder("sampler1DArray", GL30.GL_SAMPLER_1D_ARRAY)),
    SAMPLER_2D_ARRAY(samplerBuilder("sampler2DArray", GL30.GL_SAMPLER_2D_ARRAY)),
    SAMPLER_1D_ARRAY_SHADOW(samplerBuilder("sampler1DArrayShadow", GL30.GL_SAMPLER_1D_ARRAY_SHADOW)),
    SAMPLER_2D_MULTISAMPLE(samplerBuilder("sampler2DMS", GL32.GL_SAMPLER_2D_MULTISAMPLE)),
    SAMPLER_2D_MULTISAMPLE_ARRAY(samplerBuilder("sampler2DMSArray", GL32.GL_SAMPLER_2D_MULTISAMPLE_ARRAY)),
    SAMPLER_CUBE_SHADOW(samplerBuilder("samplerCubeShadow", GL30.GL_SAMPLER_CUBE_SHADOW)),
    SAMPLER_BUFFER(samplerBuilder("samplerBuffer", GL31.GL_SAMPLER_BUFFER)),
    SAMPLER_2D_RECT(samplerBuilder("sampler2DRect", GL31.GL_SAMPLER_2D_RECT)),
    SAMPLER_2D_RECT_SHADOW(samplerBuilder("sampler2DRectShadow", GL31.GL_SAMPLER_2D_RECT_SHADOW)),

    INT_SAMPLER_1D(samplerBuilder("isampler1D", GL30.GL_INT_SAMPLER_1D)),
    INT_SAMPLER_2D(samplerBuilder("isampler2D", GL30.GL_INT_SAMPLER_2D)),
    INT_SAMPLER_3D(samplerBuilder("isampler3D", GL30.GL_INT_SAMPLER_3D)),
    INT_SAMPLER_CUBE(samplerBuilder("isamplerCube", GL30.GL_INT_SAMPLER_CUBE)),
    INT_SAMPLER_1D_ARRAY(samplerBuilder("isampler1DArray", GL30.GL_INT_SAMPLER_1D_ARRAY)),
    INT_SAMPLER_2D_ARRAY(samplerBuilder("isampler2DArray", GL30.GL_INT_SAMPLER_2D_ARRAY)),
    INT_SAMPLER_2D_MULTISAMPLE(samplerBuilder("isampler2DMS", GL32.GL_INT_SAMPLER_2D_MULTISAMPLE)),
    INT_SAMPLER_2D_MULTISAMPLE_ARRAY(samplerBuilder("isampler2DMSArray", GL32.GL_INT_SAMPLER_2D_MULTISAMPLE_ARRAY)),
    INT_SAMPLER_BUFFER(samplerBuilder("isamplerBuffer", GL31.GL_INT_SAMPLER_BUFFER)),
    INT_SAMPLER_2D_RECT(samplerBuilder("isampler2DRect", GL31.GL_INT_SAMPLER_2D_RECT)),

    UNSIGNED_INT_SAMPLER_1D(samplerBuilder("usampler1D", GL30.GL_UNSIGNED_INT_SAMPLER_1D)),
    UNSIGNED_INT_SAMPLER_2D(samplerBuilder("usampler2D", GL30.GL_UNSIGNED_INT_SAMPLER_2D)),
    UNSIGNED_INT_SAMPLER_3D(samplerBuilder("usampler3D", GL30.GL_UNSIGNED_INT_SAMPLER_3D)),
    UNSIGNED_INT_SAMPLER_CUBE(samplerBuilder("usamplerCube", GL30.GL_UNSIGNED_INT_SAMPLER_CUBE)),
    UNSIGNED_INT_SAMPLER_1D_ARRAY(samplerBuilder("usampler1DArray", GL30.GL_UNSIGNED_INT_SAMPLER_1D_ARRAY)),
    UNSIGNED_INT_SAMPLER_2D_ARRAY(samplerBuilder("usampler2DArray", GL30.GL_UNSIGNED_INT_SAMPLER_2D_ARRAY)),
    UNSIGNED_INT_SAMPLER_2D_MULTISAMPLE(samplerBuilder("usampler2DMS", GL32.GL_UNSIGNED_INT_SAMPLER_2D_MULTISAMPLE)),
    UNSIGNED_INT_SAMPLER_2D_MULTISAMPLE_ARRAY(samplerBuilder("usampler2DMSArray", GL32.GL_UNSIGNED_INT_SAMPLER_2D_MULTISAMPLE_ARRAY)),
    UNSIGNED_INT_SAMPLER_BUFFER(samplerBuilder("usamplerBuffer", GL31.GL_UNSIGNED_INT_SAMPLER_BUFFER)),
    UNSIGNED_INT_SAMPLER_2D_RECT(samplerBuilder("usampler2DRect", GL31.GL_UNSIGNED_INT_SAMPLER_2D_RECT)),

    IMAGE_1D(imageBuilder("image1D", GL42.GL_IMAGE_1D)),
    IMAGE_2D(imageBuilder("image2D", GL42.GL_IMAGE_2D)),
    IMAGE_3D(imageBuilder("image3D", GL42.GL_IMAGE_3D)),
    IMAGE_2D_RECT(imageBuilder("image2DRect", GL42.GL_IMAGE_2D_RECT)),
    IMAGE_2D_CUBE(imageBuilder("imageCube", GL42.GL_IMAGE_CUBE)),
    IMAGE_BUFFER(imageBuilder("imageBuffer", GL42.GL_IMAGE_BUFFER)),
    IMAGE_1D_ARRAY(imageBuilder("image1DArray", GL42.GL_IMAGE_1D_ARRAY)),
    IMAGE_2D_ARRAY(imageBuilder("image2DArray", GL42.GL_IMAGE_2D_ARRAY)),
    IMAGE_2D_MULTISAMPLE(imageBuilder("image2DMS", GL42.GL_IMAGE_2D_MULTISAMPLE)),
    IMAGE_2D_MULTISAMPLE_ARRAY(imageBuilder("image2DMSArray", GL42.GL_IMAGE_2D_MULTISAMPLE_ARRAY)),

    INT_IMAGE_1D(imageBuilder("iimage1D", GL42.GL_INT_IMAGE_1D)),
    INT_IMAGE_2D(imageBuilder("iimage2D", GL42.GL_INT_IMAGE_2D)),
    INT_IMAGE_3D(imageBuilder("iimage3D", GL42.GL_INT_IMAGE_3D)),
    INT_IMAGE_2D_RECT(imageBuilder("iimage2DRect", GL42.GL_INT_IMAGE_2D_RECT)),
    INT_IMAGE_2D_CUBE(imageBuilder("iimageCube", GL42.GL_INT_IMAGE_CUBE)),
    INT_IMAGE_BUFFER(imageBuilder("iimageBuffer", GL42.GL_INT_IMAGE_BUFFER)),
    INT_IMAGE_1D_ARRAY(imageBuilder("iimage1DArray", GL42.GL_INT_IMAGE_1D_ARRAY)),
    INT_IMAGE_2D_ARRAY(imageBuilder("iimage2DArray", GL42.GL_INT_IMAGE_2D_ARRAY)),
    INT_IMAGE_2D_MULTISAMPLE(imageBuilder("iimage2DMS", GL42.GL_INT_IMAGE_2D_MULTISAMPLE)),
    INT_IMAGE_2D_MULTISAMPLE_ARRAY(imageBuilder("iimage2DMSArray", GL42.GL_INT_IMAGE_2D_MULTISAMPLE_ARRAY)),

    UNSIGNED_INT_IMAGE_1D(imageBuilder("uimage1D", GL42.GL_UNSIGNED_INT_IMAGE_1D)),
    UNSIGNED_INT_IMAGE_2D(imageBuilder("uimage2D", GL42.GL_UNSIGNED_INT_IMAGE_2D)),
    UNSIGNED_INT_IMAGE_3D(imageBuilder("uimage3D", GL42.GL_UNSIGNED_INT_IMAGE_3D)),
    UNSIGNED_INT_IMAGE_2D_RECT(imageBuilder("uimage2DRect", GL42.GL_UNSIGNED_INT_IMAGE_2D_RECT)),
    UNSIGNED_INT_IMAGE_2D_CUBE(imageBuilder("uimageCube", GL42.GL_UNSIGNED_INT_IMAGE_CUBE)),
    UNSIGNED_INT_IMAGE_BUFFER(imageBuilder("uimageBuffer", GL42.GL_UNSIGNED_INT_IMAGE_BUFFER)),
    UNSIGNED_INT_IMAGE_1D_ARRAY(imageBuilder("uimage1DArray", GL42.GL_UNSIGNED_INT_IMAGE_1D_ARRAY)),
    UNSIGNED_INT_IMAGE_2D_ARRAY(imageBuilder("uimage2DArray", GL42.GL_UNSIGNED_INT_IMAGE_2D_ARRAY)),
    UNSIGNED_INT_IMAGE_2D_MULTISAMPLE(imageBuilder("uimage2DMS", GL42.GL_UNSIGNED_INT_IMAGE_2D_MULTISAMPLE)),
    UNSIGNED_INT_IMAGE_2D_MULTISAMPLE_ARRAY(imageBuilder("uimage2DMSArray", GL42.GL_UNSIGNED_INT_IMAGE_2D_MULTISAMPLE_ARRAY)),

    UNSIGNED_INT_ATOMIC_COUNTER(uintBuilder("atomic_uint", GL42.GL_UNSIGNED_INT_ATOMIC_COUNTER).isOpaque(true));

    private static final ImmutableMap<Integer, GLSLType> enumTypeMap = newEnumTypeMap();

    private final String typeName;
    private final int primitiveType;
    private final int boxedType;
    private final boolean isOpaque;
    private final int rows;
    private final int columns;
    private final int units;
    private final int unitSize;
    private final int rowSize;
    private final int columnSize;
    private final int typeSize;

    GLSLTypes(Builder builder) {
        builder.validate();
        typeName = builder.typeName();
        primitiveType = builder.primitiveType();
        boxedType = builder.boxedType();
        isOpaque = builder.isOpaque();
        rows = builder.rows();
        columns = builder.columns();
        units = initUnits();
        unitSize = builder.unitSize();
        rowSize = initRowSize();
        columnSize = initColumnSize();
        typeSize = initTypeSize();
    }

    private int initUnits() {
        return rows * columns;
    }

    private int initRowSize() {
        return columns * unitSize;
    }

    private int initColumnSize() {
        return rows * unitSize;
    }

    private int initTypeSize() {
        return rows * columns * unitSize;
    }

    private static Builder booleanBuilder(String typeName, int boxedType) {
        return new Builder(typeName, GL20.GL_BOOL, boxedType);
    }

    private static Builder uintBuilder(String typeName, int boxedType) {
        return new Builder(typeName, GL11.GL_UNSIGNED_INT, boxedType);
    }

    private static Builder intBuilder(String typeName, int boxedType) {
        return new Builder(typeName, GL11.GL_INT, boxedType);
    }

    private static Builder floatBuilder(String typeName, int boxedType) {
        return new Builder(typeName, GL11.GL_FLOAT, boxedType);
    }

    private static Builder doubleBuilder(String typeName, int boxedType) {
        return new Builder(typeName, GL11.GL_DOUBLE, boxedType).unitSize(DOUBLE_SIZE);
    }

    private static Builder samplerBuilder(String typeName, int boxedType) {
        return new Builder(typeName, GL11.GL_INT, boxedType).isOpaque(true);
    }

    private static Builder imageBuilder(String typeName, int boxedType) {
        return new Builder(typeName, GL11.GL_INT, boxedType).isOpaque(true);
    }

    @Getter
    @Setter
    @RequiredArgsConstructor
    private static class Builder {
        @NonNull
        private final String typeName;
        private final int primitiveType;
        private final int boxedType;
        private boolean isOpaque = false;
        private int rows = 1;
        private int columns = 1;
        private int unitSize = 4;

        public void validate() {
            if (typeName.equals(NULL_STRING))
                return;
            if (typeName.length() < 1)
                throw new ShaderException("Field typeName length can't be less than 1.");
            if (primitiveType < 1)
                throw new ShaderException("Field primitiveType can't be less than 1.");
            if (boxedType < 1)
                throw new ShaderException("Field boxedType can't be less than 1.");
            if (rows < 1)
                throw new ShaderException("Field attributeRows can't be less than 1.");
            if (columns < 1)
                throw new ShaderException("Field attributeColumns can't be less than 1.");
            if (unitSize < 1)
                throw new ShaderException("Field attributeSizeBytes can't be less than 1.");
        }
    }

    @Override
    public String toString() {
        return typeToString();
    }

    private static ImmutableMap<Integer, GLSLType> newEnumTypeMap() {
        val map = ImmutableMap.<Integer, GLSLType>builder();
        for (GLSLType type : GLSLTypes.values())
            map.put(type.boxedType(), type);
        return map.build();
    }

    public static GLSLType get(int glEnum) {
        return enumTypeMap.getOrDefault(glEnum, NULL);
    }
}
