<html>
<#include "head.ftl">
<h1>Hello</h1>
<pre>${value}!
$!{money}</pre>
<body>heyheyhey
<br>User: ${User.name}</br>
<br>${User.discription}
</body>

<#-- 使用list -->
<#list colors as color>
    <br>${color_index},This is ${color}</br>
</#list>

<#-- 使用map -->
<select>
<#list map?keys as key>
    <option value="${key}">${map[key]}</option>
</#list>
</select>

<#-- 定义变量 -->
<#assign var = "Happy birthday, ${User.name}">
    ${var}

<#-- 定义宏 -->
<#macro animal Asian European American="rabbit">
    Text: ${Asian}, ${American}, ${European}
</#macro>
<#-- 使用宏 -->
<@animal Asian="tiger" European="dolphin"/>

<br><br>

<#-- 定义一个循环输出的宏 -->
<#macro list title items>
    　　${title}
    　　<#list items as x>
    　　　　*${x}
    　　</#list>
</#macro>

<#-- 使用宏 -->
<@list items=["mouse", "elephant", "python"] title="Animals"/>


</html>