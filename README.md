> Android Studio 3.x时代，项目中如果需要使用java 8新特性，除lambda表达式外，如需使用stream 、time、函数接口、Optionals、ConcurrentHashMap等Api，需要调用前限制api>= 24，即仅支持Android 7.0以上，低版本还需单独适配或使用非官方解决方案retrolambda，很大程度上劝退了一批开发者；
> Android Studio 4.0（2020年5月）发布后,直接支持java 8 标准API,而无需给应用程序设置最低 API 级别；

非官方解决方案retrolambda
```groovy
implementation 'me.tatarka:gradle-retrolambda:3.4.0'
```

Android Studio 3.0 后 新增的DEX编译器D8通过desugaring的过程能够支持部分Java 8 API的语法糖（lambda表达式）。
在Android Studio 4.0中，已将 desugaring engine 扩展为能够对 Java 语言 API 的语法糖。这意味着你现在可以较旧版本的 Android 的应用程序中使用Java标准语言API 。
- Sequential streams (java.util.stream1)
- A subset of java.time
- java.util.function
- Recent additions to java.util.{Map,Collection,Comparator}
- Optionals（java.util.Optional，java.util.OptionalInt and some other new - classes useful with the above APIs
- Some additions to java.util.concurrent.atomic (new methods on AtomicInteger, AtomicLong and AtomicReference)
- ConcurrentHashMap (with bug fixes for Android 5.0)

- ps: D8 的功能是把java字节码转化成dex代码，D8作为DX的一个替换方案
[关于D8编译器更多细节参考这篇文章](https://www.jianshu.com/p/bb6fb79dab17)


步入正题，Anroid studio 支持java8 ,我们需要做 **3**步
#### 1. 在 app/build.gradle文件 android 节点下指定java版本为1.8
```groovy
compileOptions {
    //支持新版java Api
    coreLibraryDesugaringEnabled true

    //指定java sdk版本为 1.8
    sourceCompatibility JavaVersion.VERSION_1_8
    targetCompatibility JavaVersion.VERSION_1_8
}
```

#### 2. 在app/build.gradle文件的android/defaultConfig节点下开启multiDexEnabled

```
defaultConfig {
        //多dex文件开启
        multiDexEnabled true
    }
```


#### 3. 在 app/build.gradlee dependencies 节点下添加引用desugaring engine 扩展，从而实现java 8语法糖
```
dependencies {
    implementation fileTree(dir: "libs", include: ["*.jar"])
    //通过desugaring的过程能够支持部分Java 8 API的语法糖
    coreLibraryDesugaring 'com.android.tools:desugar_jdk_libs:1.0.10'
}
```


如下测试代码，可以开始自由的编写java 8代码了

```java

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("Runnable", "Runnable");
            }
        }).start();


        new Thread(() -> Log.i("Runnable", "Runnable")).start();


        List<String> list = Arrays.asList("A", "B", "C", "D", "NB", "CBA", "DNF");

        list.stream().forEach(s -> {
            Log.i("stream() forEach", s);
        });


        list.stream().map(new Function<String, String>() {
            @Override
            public String apply(String s) {
                return "String is " + s;
            }
        }).forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                Log.i("string", s);
            }
        });

        //lambda表达式实现
        list.stream().map(s -> "String is " + s)
                .forEach(s ->
                        Log.i("string", s));
    }
}
```



