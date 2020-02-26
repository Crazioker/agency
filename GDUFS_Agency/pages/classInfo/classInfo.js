// pages/classInfo/classInfo.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    goods:null,
    typeArray: [
      { "id": 0, "type": "代购" },
      { "id": 1, "type": "快递代拿" },
      { "id": 2, "type": "其他代办" },
    ],
    agentOrders:null,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    console.log(options.type)
    var studentId = wx.getStorageSync('studentId');
    wx.request({
      url: app.data.apiUrl + '/indent/getIndentsByType?studentId=' + studentId + '&type=' +options.type,
      method: 'Get',
      header: {
        'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
      },
      success(res) {
        var msg = res.data;
        that.setData({
          agentOrders: msg
        })
        for (var i in msg) {
          // console.log(msg[i]['indentid'] + " " + msg[i]['address']);

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
    wx.setNavigationBarTitle({
      title:that.data.typeArray[options.type].type,
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  }
})