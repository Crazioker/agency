//index.js
//获取应用实例
var app = getApp();

Page({
  data: {
    imgUrls: [
      { img: '/img/lunbo3.jpg', url:'http://news.gdufs.edu.cn/'},
      {img: '/img/lunbo2.jpg',url:'https://www.gdufs.edu.cn/info/1106/52492.htm'},
      {img: '/img/lunbo1.jpg',url:'https://www.gdufs.edu.cn/info/1106/52503.htm'},
      { img: '/img/lunbo4.jpg', url:'https://www.gdufs.edu.cn/info/1106/52515.htm'}
    ],
    agentOrders: [
      {price: 30, date: '2019/09/20 12:00', type: '代拿快递', place: '师苑4栋'},
    ],
    typeArray:[
      {"id":0,"type":"代购"},
      { "id": 1, "type": "快递代拿" },
      { "id": 2, "type": "其他代办" },
    ],
    current:0, 
    goods:null,
    num:5, //默认首页初始化五条商品信息
    isList:"new",
    classId:{
        'purchase': 0,//代购
        'express':1, //代拿快递
        'other':2, //其他
      }
  },
  onslideChange:function(e){
      this.setData({
         current:e.detail.current
       })
  },
  swiperInavigateTo: function(){
    var current=this.data.current;
    console.log("current值："+current)
    app.data.currentUrl=this.data.imgUrls[current]['url'];
    console.log("app.data.currentUrl:" + app.data.currentUrl)
      wx.navigateTo({
       url: '../outpage/outpage',
     })
  },
  onShow:function(){
    var that=this;
    var studentId=wx.getStorageSync('studentId');
    wx.request({
      url: app.data.apiUrl + '/indent/getIndents?studentId=' + studentId,
      method: 'Get',
      header: {
        'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
      },
      success(res) {
        var msg = res.data;
        that.setData({
          agentOrders:msg
        })
        for(var i in msg){
          console.log(msg[i]['indentid'] + " " + msg[i]['address']);
          
        }
        
        // console.log(msg['username']);
        // console.log(msg['balance']);
        // console.log(msg['address']);

      },
      fail(res) {
        console.log(res.data)
        wx.showToast({
          title: '网络异常！',
          icon: 'none',
          duration: 2000
        });
      }
    })
  },
  onLoad:function(){
  },
})

