(ns inspect.html-dump-style
  (:gen-class))

(def css "
<style>
html {
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
}
*, *:before, *:after {
  -webkit-box-sizing: inherit;
  -moz-box-sizing: inherit;
  box-sizing: inherit;
}  
*{
  font-family: Roboto, sans-serif;
  font-size: 14px;
}
body {
  box-sizing: border-box;
  margin: 0px;  
  box-sizing: border-box;
  padding: 20px;
}
.map-block{
  border-left: 1px solid #ff9393;
  margin-top: 6px;
  margin-bottom: 6px;
  margin-left: 4px;
}
.map-start, .map-end,
.list-start, .list-end,
.set-start, .set-end,
.vector-start, .vector-end{  
  color: #a09f9f;
}
.map-key{
  color:#ff1a00;
  margin-left: 12px;
  background-color: #fff8f8;
  padding: 2px 5px;
  /* font-style: italic; */
}
.map-value{
  margin-left: 6px;
  display: inline-table;
}
.vector-block{
  background-color: #f7f9c6;
  margin-left: 1px;
}
.map-value > .vector-block{
  margin-bottom: 0px;
  padding: 4px 6px;
}
.list-block{
  border-left: 1px solid #a5a5a5;
  padding-left: 12px;
}
</style>
")