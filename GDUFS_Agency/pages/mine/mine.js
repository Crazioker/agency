var app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    //判断sessionId是否过期
    // isExceed: 1,
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
    wx.navigateTo({
      url: '../publish/publish',
    })

    // var sessionid=wx.getStorageSync('sessionId');
    // // console.log(sessionid);

    // wx.request({
    //   url: app.data.apiUrl + '/user/checkSession',
    //   method: 'GET',
    //   header: {
    //     'content-type': 'application/x-www-form-urlencoded',// 默认值
    //     'Cookie': sessionid
    //   },
    //   success: function(res) {
    //     // console.log(res.data)
    //     if (res.data == 0) {
    //       wx.showModal({
    //         title: '未登录',
    //         content: '返回登录页面',
    //         confirmText: '确定',
    //         cancelText: '取消',
    //         success: function (res) {
    //           if (res.confirm) {
    //             // console.log('用户点击确定')
    //             wx.navigateTo({
    //               url: '../login/login',
    //             })
    //           } else if (res.cancel) {
    //             // console.log('用户点击取消')
    //             wx.switchTab({
    //               url: '../index/index',
    //             })
    //           }
    //         }
    //       })
    //     } else {
    //       wx.navigateTo({
    //         url: '../publish/publish',
    //       })
    //     }
    //   }
    // })

    
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