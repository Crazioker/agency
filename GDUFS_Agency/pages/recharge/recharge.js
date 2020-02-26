// pages/recharge/recharge.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    money: 0,
    disable: true
  },

  getMoney: function(e) {
    var value;
    //正则验证，充值金额仅支持4位整数
    if (/^\d{1,4}?$/.test(e.detail.value)) {
      //满足条件
      value = e.detail.value;
    } else {
      //不满足条件
      value = e.detail.value.length > 4 ?
        e.detail.value.substring(0, 4) :
        e.detail.value.substring(0, e.detail.value.length - 1);
      wx.showToast({
        title: '充值金额仅支持4位整数哦',
        icon: 'none',
        duration: 1500
      })
    }
    if (value.length < 1) {
      this.setData({
        disable: true
      })
    }else{
      this.setData({
        disable: false
      })
    }
    this.setData({
      money: value,
    })
    console.log("money："+this.data.money)
  },

  click: function() {
    var studentId = wx.getStorageSync('studentId');
    this.setData({
      disable: true
    })
    wx.request({
      url: app.data.apiUrl + '/user/updateBalance',
      method: "POST",
      data: {
        studentId: studentId,
        balance: this.data.money,
      },

      header: {
        'content-type': 'application/x-www-form-urlencoded', // 默认值
      },
      success(res) {
        if (res.data == 1) {
          console.log("请求成功：" + res.data)
          wx.showToast({
            title: '充值成功！',
            icon: 'success',
            duration: 1500
          })
          setTimeout(function () {
            wx.navigateTo({
              url: '../publish/publish',
            })
          }, 800)
        } else {
          wx.showToast({
            title: '充值失败，稍后再试',
            icon: 'none',
            duration: 2000
          });
        }
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
    this.setData({
      disable:false
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})