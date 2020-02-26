// pages/changePwd/changePwd.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    pwd:'',
    repwd:''

  },

  pwdinput: function (e) {
    this.setData({
      pwd: e.detail.value
    });
    console.log(this.data.pwd);
  },

  repwdinput: function (e) {
    this.setData({
      repwd: e.detail.value
    });
    console.log(this.data.repwd);
  },

  save:function(){
    if(this.data.pwd!=this.data.repwd){
      wx.showToast({
        title: '两次输入不同，请重新输入！',
        icon: 'none',
        duration: 2000
      });
    }else{
      var studentId=wx.getStorageSync('studentId');
      wx.request({
        url: app.data.apiUrl + '/user/updatePwd',
        method: 'POST',
        data: {
          studentId: studentId,
          password: this.data.pwd
        },
        header: {
          'content-type': 'application/x-www-form-urlencoded' // 默认值
        },
        fail: function () {
          wx.showToast({
            title: '服务器错误，保存失败',
            icon: 'none',
            duration: 2000
          })
        },
        success: function (res) { 
          if(res.data==1){
            wx.showToast({
              title: '保存成功，重新登录',
              icon: 'success',
              duration: 2000
            });
            wx.setStorageSync('sessionId', '');
            wx.setStorageSync('studentId', '');
            wx.navigateTo({
              url: '../login/login',
            })
          }else{
            wx.showToast({
              title: '保存失败，稍后再试',
              icon: 'none',
              duration: 2000
            });
          }
        }
      })
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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