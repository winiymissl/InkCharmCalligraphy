package com.example.lib_router_core.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import com.example.lib_compiler.util.Constants;
import com.example.lib_router_core.template.Router;
import com.example.lib_router_core.thread.DefaultPoolExecutor;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import dalvik.system.DexFile;

/**
 * @Author winiymissl
 * @Date 2024-04-08 13:27
 * @Version 1.0
 */
public class ClassUtils {
    private static final String EXTRACTED_SUFFIX = ".zip";

    /**
     * 根据包名获取对应的所有类名。
     *
     * @param context     上下文环境，用于访问应用的包管理器等服务。
     * @param packageName 要查询的包名。
     * @return 返回一个包含所有匹配包名的类名的集合。
     * @throws PackageManager.NameNotFoundException 如果包名未找到，抛出此异常。
     * @throws IOException                          如果读取dex文件发生错误，抛出此异常。
     * @throws InterruptedException                 如果线程执行过程中被中断，抛出此异常。
     */
    public static Set<String> getFileNameByPackageName(Context context, final String packageName) throws PackageManager.NameNotFoundException, IOException, InterruptedException {
        final Set<String> classNames = new HashSet<>(); // 用于存储匹配到的类名

        List<String> paths = getSourcePaths(context); // 获取应用的所有dex文件路径
        final CountDownLatch parserCtl = new CountDownLatch(paths.size()); // 控制并发解析dex文件的线程数

        for (final String path : paths) {
            DefaultPoolExecutor.getInstance().execute(new Runnable() {
                @Override
                public void run() {
                    DexFile dexfile = null;

                    try {
                        if (path.endsWith(EXTRACTED_SUFFIX)) {
                            // 处理已提取的dex文件，避免权限错误
                            dexfile = DexFile.loadDex(path, path + ".tmp", 0);
                        } else {
                            dexfile = new DexFile(path);
                        }

                        Enumeration<String> dexEntries = dexfile.entries();
                        while (dexEntries.hasMoreElements()) {
                            String className = dexEntries.nextElement();
                            // 匹配包名，并添加到集合中
                            if (className.startsWith(packageName)) {
                                classNames.add(className);
                            }
                        }
                    } catch (Throwable ignore) {
                        // 日志记录dex文件解析过程中的错误
                        Log.e(Constants.PROJECT, "Scan map file in dex files made error.", ignore);
                    } finally {
                        if (null != dexfile) {
                            // 确保dex文件被正确关闭
                            try {
                                dexfile.close();
                            } catch (Throwable ignore) {
                            }
                        }

                        // 线程计数器减一，表示当前dex文件已处理完成
                        parserCtl.countDown();
                    }
                }
            });
        }

        // 等待所有线程完成dex文件的解析
        parserCtl.await();

        // 日志记录匹配到的类名数量
        Log.d(Constants.PROJECT, "Filter " + classNames.size() + " classes by packageName <" + packageName + ">");
        return classNames;
    }


    /**
     * 获取应用程序源代码路径列表。
     * <p>此方法会首先获取应用的基本APK路径，如果应用配置为调试模式，还会尝试添加即时运行的dex文件路径。</p>
     *
     * @param context 上下文对象，用于访问应用的包管理器等信息。
     * @return 包含应用源代码路径的列表。列表中的第一个元素是应用的基本APK路径，如果应用处于调试模式，后续元素是即时运行的dex文件路径。
     * @throws PackageManager.NameNotFoundException 如果无法找到应用的包信息。
     * @throws IOException                          如果在读取文件时发生错误。
     */
    public static List<String> getSourcePaths(Context context) throws PackageManager.NameNotFoundException, IOException {
        // 通过包管理器获取应用的ApplicationInfo
        ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);

        List<String> sourcePaths = new ArrayList<>();
        sourcePaths.add(applicationInfo.sourceDir); // 添加默认的APK路径到列表

        // 如果应用配置为调试模式，尝试加载即时运行的dex文件路径并添加到列表中
        if (Router.debuggable()) {
            sourcePaths.addAll(tryLoadInstantRunDexFile(applicationInfo));
        }
        return sourcePaths;
    }

    /**
     * 尝试加载Instant Run模式下的dex文件路径列表，用于捕获使用ApkSplits=false的分支。
     *
     * @param applicationInfo 应用的ApplicationInfo对象，用于获取应用的相关信息。
     * @return 返回一个包含Instant Run模式下dex文件路径的字符串列表。
     */
    private static List<String> tryLoadInstantRunDexFile(ApplicationInfo applicationInfo) {
        List<String> instantRunSourcePaths = new ArrayList<>();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && null != applicationInfo.splitSourceDirs) {
            // 添加分割的APK路径，通常用于Instant Run和最新版本。
            instantRunSourcePaths.addAll(Arrays.asList(applicationInfo.splitSourceDirs));
            Log.d(Constants.PROJECT, "Found InstantRun support");
        } else {
            try {
                // 通过反射Google Instant Run SDK的方法，获取dex文件的路径。
                Class pathsByInstantRun = Class.forName("com.android.tools.fd.runtime.Paths");
                Method getDexFileDirectory = pathsByInstantRun.getMethod("getDexFileDirectory", String.class);
                String instantRunDexPath = (String) getDexFileDirectory.invoke(null, applicationInfo.packageName);

                File instantRunFilePath = new File(instantRunDexPath);
                if (instantRunFilePath.exists() && instantRunFilePath.isDirectory()) {
                    // 遍历指定路径下的文件，将所有以".dex"结尾的文件路径添加到列表中。
                    File[] dexFile = instantRunFilePath.listFiles();
                    for (File file : dexFile) {
                        if (null != file && file.exists() && file.isFile() && file.getName().endsWith(".dex")) {
                            instantRunSourcePaths.add(file.getAbsolutePath());
                        }
                    }
                    Log.d(Constants.PROJECT, "Found InstantRun support");
                }

            } catch (Exception e) {
                // 捕获反射调用可能引发的异常，并记录错误日志。
                Log.e(Constants.PROJECT, "InstantRun support error, " + e.getMessage());
            }
        }

        return instantRunSourcePaths;
    }

}
