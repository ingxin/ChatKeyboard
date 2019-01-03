repositories {
    maven { url "https://dl.bintray.com/ingxin/maven" }
}

dependencies {
    //解析表情（必须）
    implementation ''com.xingxin.android:emoticons:1.1.3'
    //表情键盘UI(必须)
    implementation 'com.xingxin.android:keyboard:1.1.3'
    //小浪花表情库(非必须)
    implementation ''com.xingxin.android:emoticons_xlh:1.0.0'
    //默认表情库(非必须)
    implementation 'com.xingxin.android:emoticions_default:1.0.0'
}
