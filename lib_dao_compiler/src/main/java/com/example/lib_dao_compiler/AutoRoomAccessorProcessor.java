package com.example.lib_dao_compiler;

import androidx.room.Dao;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

/**
 * @Author winiymissl
 * @Date 2024-04-09 21:04
 * @Version 1.0
 */

@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_17)
@SupportedAnnotationTypes("androidx.room.Dao")
public class AutoRoomAccessorProcessor extends AbstractProcessor {
    private static final String PACKAGE_NAME = "com.example.lib_dao_compiler";

    private Messager messager;
    private Elements elementUtil;
    private Filer filer;
    private boolean generated = false;

    private String className = "";

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer = processingEnv.getFiler();
        elementUtil = processingEnv.getElementUtils();
        messager = processingEnv.getMessager();

        if (processingEnv.getOptions().containsKey("ROOM_ACCESSOR_NAME_PREFIX")) {
            className = processingEnv.getOptions().get("ROOM_ACCESSOR_NAME_PREFIX") + "RoomAccessor";
        } else {
            messager.printMessage(Diagnostic.Kind.ERROR, "ROOM_ACCESSOR_NAME_PREFIX must be defined.");
            throw new IllegalArgumentException();
        }
        if (className == null || className.isEmpty()) {
            messager.printMessage(Diagnostic.Kind.ERROR, "ROOM_ACCESSOR_NAME_PREFIX must be defined.");
            throw new IllegalArgumentException();
        }

        System.out.println(" ----------- AutoRoomAccessorProcessor ----------- ");
        System.out.println(" ----------- className = " + className + " ----------- ");
    }

    /**
     * 处理注解元素，生成DAO相关代码。
     *
     * @param set              包含被注解元素的集合。
     * @param roundEnvironment 当前编译回合的环境信息。
     * @return 总是返回true，表示处理完成。
     */
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        // 如果已经生成过或filer为空，则直接返回
        if (generated || filer == null) {
            return true;
        }
        List<MethodSpec> daoFunList = new ArrayList<>(); // 存储DAO方法信息
        List<MethodSpec> interfaceFuns = new ArrayList<>(); // 存储回调接口方法信息

        // 遍历被@Dao注解标记的元素
        for (Element element : roundEnvironment.getElementsAnnotatedWith(Dao.class)) {
            String daoClassName = element.getSimpleName().toString(); // 获取DAO类名
            System.out.println("find DAO = " + daoClassName);

            // 构建DAO类型的名称
            TypeName daoTypeName = ClassName.get(elementUtil.getPackageOf(element).toString(), daoClassName);

            // 为每个DAO生成获取方法，并加入到daoFunList中
            daoFunList.add(MethodSpec.methodBuilder("get" + daoClassName)
                    .addModifiers(Modifier.PUBLIC) // 公共访问
                    .returns(daoTypeName) // 返回类型为DAO类型
                    .addCode("if (onGetDaoCallback == null) {\n" + "throw new IllegalArgumentException(\"onGetDaoCallback must not be null!!\");\n" + "}\n" + "return onGetDaoCallback.onGet" + daoClassName + "();\n").build());

            // 为回调接口生成相应的方法定义，并加入到interfaceFuns中
            interfaceFuns.add(MethodSpec.methodBuilder("onGet" + daoClassName)
                    .addModifiers(Modifier.ABSTRACT) // 抽象方法
                    .returns(daoTypeName) // 返回类型为DAO类型
                    .build());
        }

        // 构建最终生成的类信息
        TypeSpec.Builder infoClazzBuilder = TypeSpec.classBuilder(className).addMethods(daoFunList);

        // 构建内部回调接口类
        TypeSpec interfaceInnerClass = buildCallback(className, interfaceFuns, infoClazzBuilder);

        // 构建Java文件并写入文件系统
        JavaFile javaFile = JavaFile.builder(PACKAGE_NAME, interfaceInnerClass).build();

        try {
            javaFile.writeTo(filer); // 写入文件
            generated = true; // 标记为已生成
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true; // 表示处理完成
    }


    /**
     * 构建回调接口类。
     *
     * @param className 回调接口的类名。
     * @param interfaceFuns 回调接口中方法的列表。
     * @param builder 类的构造器。
     * @return 构建好的内部接口类的类型规格。
     */
    private TypeSpec buildCallback(String className, List<MethodSpec> interfaceFuns, TypeSpec.Builder builder) {
        // 创建一个内部接口类，并添加所有指定的方法
        TypeSpec interfaceInnerClass = TypeSpec.interfaceBuilder("OnGetDaoCallback").addMethods(interfaceFuns).build();

        // 生成回调接口的全限定名
        TypeName onGetDaoCallbackType = ClassName.get(PACKAGE_NAME + "." + className, interfaceInnerClass.name);
        // 创建一个私有字段，用于存储回调接口的实例，初始值为null
        FieldSpec onGetDaoCallbackProperty = FieldSpec.builder(onGetDaoCallbackType, "onGetDaoCallback", Modifier.PRIVATE).initializer("null").build();

        // 向类构造器中添加内部接口类和回调接口的字段定义
        builder.addType(interfaceInnerClass).addField(onGetDaoCallbackProperty);
        // 返回内部接口类的类型规格
        return interfaceInnerClass;
    }
}