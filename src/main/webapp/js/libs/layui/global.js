layui.define(['code', 'element', 'table', 'util'], function(exports){
  var $ = layui.jquery
  ,element = layui.element
  ,layer = layui.layer
  ,form = layui.form
  ,util = layui.util
  ,device = layui.device();


  


  //手机设备的简单适配
  var treeMobile = $('.site-tree-mobile')
  ,shadeMobile = $('.site-mobile-shade')

  treeMobile.on('click', function(){
    $('body').addClass('site-mobile');
  });

  shadeMobile.on('click', function(){
    $('body').removeClass('site-mobile');
  });

  exports('global', {});
});