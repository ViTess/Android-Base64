#Android Base64

###NDK实现Base64解/编码

>注：所有解码后的数据最后都带'\n'

####Encode

flag| mean 
---|---
DEFAULT    | 默认标准编码(每76字符换行/结尾添加=号)
NO_PADDING | 结尾字符不够时也不添加=号
NO_WRAP    | 除结尾外去掉所有换行符
CRLF       | 设置换行符风格为CRLF(\r\n)
URL_SAFE   | 设置url可使用的格式，即'\_'换成'-'，'+'和'/'换成'\_'

####Decode

flag| mean 
---|---
URL_SAFE   | 设置url可使用的格式，即'\_'换成'-'，'+'和'/'换成'\_'

####Update

16.11.12

* 补完decode方法

16.11.04

* 增加url safe编码

16.10.31

* 增加偏移量和读取长度的参数

* 增加效仿android.util中base64的标志

16.10.28

* 默认标准编码(每76字符换行/结尾添加=号/结尾换行)