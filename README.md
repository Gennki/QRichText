# QRichText

## 效果图
![效果图](https://ws1.sinaimg.cn/large/006zFkU4gy1ftl3j9k6zug30go0ljke2.gif)

## 添加依赖
1. 在项目根目录的build.gradle中添加如下语句
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
2. 在module下的build.gradle中添加如下语句
```
	dependencies {
	        implementation 'com.github.Gennki:QRichText:v1.0.2'
	}

```

## 使用方法
1. xml布局如下:
```
<cn.qzb.richeditor.RichEditor
        android:id="@+id/editor"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
```
2. 在Activity的onCreate中初始化RichEditor
```
// init rich text editor
RE.init(editor)
RE.setPlaceHolder("Input text here...")
RE.setPadding(20, 20, 20, 20)
```
3. 常用api
- RE.getHtml();// 获取富文本html代码
- RE.setBold();// 加粗的时候调用这个方法为取消加粗,没有加粗的时候调用这个方法为加粗
- RE.setItalic();// 斜体,使用方法和加粗相同
- RE.setUnderLine();// 下划线,使用方法和加粗相同
- RE.setTextSize(fontSize);// 字号大小,fontSize范围为1~7的整数
- RE.insertImage(url, alt, imageWidthPercent);// url为图片路径,alt暂时不支持,随便填就行,imageWidthPercent为图片宽度占一屏幕的百分比,默认为100
- RE.setTextColor(color);// 设置字体颜色
- RE.setTextBackgroundColor(color);// 设置字体背景颜色
