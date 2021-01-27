
<img src="https://avatars.githubusercontent.com/u/14914307?s=460&u=449bf10288f142cc2dfaa6a2fe8ea1901e3b9d01&v=4" height="100">

# ColoredTextView
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/fa95d66fb34c40959532cd991aefe47c)](https://www.codacy.com/gh/LinerSRT/ColoredTextView/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=LinerSRT/ColoredTextView&amp;utm_campaign=Badge_Grade)
[![API](https://img.shields.io/badge/API-14%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=14)

This project can help you if you want display text in android with different colors in single view
- **Minimum API is 14**
- **This project uses AndroidX (You can easy migrate to AppCompat)**

Usage
-----
In XML: 
Like a normal TextView, but you can also specify color of each word or even symbol. Using special encoding. 
**{c=your_color_in_hex, t=your_text}** - Sample encoded text
```xml
<com.liner.coloredtextview.ColoredTextView
        android:id="@+id/coloredText"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Im bold {c=#FF0000, t=red} text!" <!--Your text here (may be special formatted)--> 
/>
        
```
In Java: 
```java
ColoredTextView coloredTextView = findViewById(R.id.coloredText);
//Manual
coloredTextView.setText("Im bold {c=#FF0000, t=red} text!");
//With builder
coloredTextView.setText(new ColoredTextView.Builder()
    .add("Im normal color\n") // Simple text without any color
    .add("Im red color\n", Color.RED) // Colored text with specified color int
    .add("Im magenta color\n", Color.MAGENTA) // Colored text with specified color int
    .add("Im green color", "#00ff00") // Colored text with specified color string in hex
    .build());
```


### Result

<img src="https://github.com/LinerSRT/ColoredTextView/blob/master/media/screenshot.jpg?raw=true" height="500">

#### For more help see demo project
## License
Licensed under the Apache License, Version 2.0
