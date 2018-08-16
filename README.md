# QRichText

## 效果图


<img src="https://ws1.sinaimg.cn/large/006zFkU4gy1ftl3j9k6zug30go0ljke2.gif" width="50%" alt="效果图1"/><img src="https://ws1.sinaimg.cn/large/006zFkU4ly1fubciepohfg30ak0irkjl.gif" width="50%" alt="效果图2"/>



## 添加依赖

1. 在项目根目录的build.gradle中添加如下语句
```glide
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
2. 在module下的build.gradle中添加如下语句
```glide
	dependencies {
	        implementation 'com.github.Gennki:QRichText:v1.0.3'
	}

```

## 使用方法
### 1. xml布局如下:

```xml
<cn.qzb.richeditor.RichEditor
        android:id="@+id/editor"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
```
### 2. 在Activity的onCreate中初始化RichEditor

```java
// init rich text editor
val re = RE.getInstance(editor)
re.setPlaceHolder("Input text here...")
re.setPadding(20, 20, 20, 20)
re.setTextBackgroundColor(Color.WHITE)
```
### 3. 常用api

| Api                                         | 说明                                                         |
| :------------------------------------------ | :----------------------------------------------------------- |
| re.getHtml()                                | 获取富文本html代码                                           |
| re.setBold()                                | 加粗的时候调用这个方法为取消加粗,没有加粗的时候调用这个方法为加粗 |
| re.setItalic()                              | 斜体,使用方法和加粗相同                                      |
| re.setUnderLine()                           | 下划线,使用方法和加粗相同                                    |
| re.setTextSize(fontSize)                    | 字号大小,fontSize范围为1~7的整数                             |
| re.insertImage(url, alt, imageWidthPercent) | url为图片路径,alt为图片下方显示的说明文字,暂时不支持,imageWidthPercent为图片宽度占一屏幕的百分比,默认为100 |
| re.setTextColor(color)                      | 设置字体颜色                                                 |
| re.setTextBackgroundColor(color)            | 设置字体背景颜色                                             |
| re.moveToEndEdit()                          | 使用场景一般为要编辑某段富文本的时候,刚进入页面的时候,光标要显示到最后,并且编辑框的内容也要滑动到底部, **需要注意的是,刚进入页面的时候马上调用此方法可能会无效,因为页面还没有渲染好 最好延时几百毫秒后调用** |

