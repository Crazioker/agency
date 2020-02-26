// pages/info/info.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    username:"default",
    phoneNum:"default",
    academy:"default",
    address:"default"
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    var studentId = wx.getStorageSync("studentId");
    wx.request({
      url: app.data.apiUrl +'/user/getUser?studentId='+studentId,
      success(res){
        var result = res.data
        console.log("请求成功：" + res.statusCode)
        console.log("username：" + result["username"]+"\n"+
                    "phoneNum：" + result["phoneNum"] + "\n" +
                    "address：" + result["address"] + "\n" +
                    "academy：" + result["academy"])
        that.setData({
          username: result["username"],
          phoneNum: result["phoneNum"],
          address: result["address"],
          academy: result["academy"],
        })
      },
      fail(res){
        console.log("请求失败：" + res.data)
        wx.showToast({
          title: '网络异常！',
          icon: 'none',
          duration: 2000
        })
      }
    })
  },

  getAcademy:function(e){
    console.log(e.detail.value);
    this.setData({
      academy:e.detail.value
    })
  },

  getUsername: function (e) {
    console.log(e.detail.value);
    this.setData({
      username: e.detail.value
    })
  },

  getPhoneNum: function (e) {
    console.log(e.detail.value);
    this.setData({
      phoneNum: e.detail.value
    })
  },

  getAddress: function (e) {
    console.log(e.detail.value);
    this.setData({
      address: e.detail.value
    })
  },

  save:function(){
    var studentId = wx.getStorageSync("studentId");
    console.log(
      "academy：" + this.data.academy,
      "username：" + this.data.username,
      "phoneNum：" + this.data.phoneNum,
      "address：" + this.data.address,
    );
    if(this.data.phoneNum.length != 11){
      wx.showToast({
        title: '手机号码应为11位哦!',
        icon:"none",
      })
    }else{
      wx.request({
        url: app.data.apiUrl +'/user/updateUser',
        method: "POST",
        data: {
          studentId: studentId,
          academy: this.data.academy,
          username: this.data.username,
          phoneNum: this.data.phoneNum,
          address: this.data.address,
        },
        header: {
          'content-type': 'application/x-www-form-urlencoded' // 默认值
        },
        success(res) {
          console.log("请求成功：" + res.data)
          if (res.data == 1) {
            wx.showToast({
              title: '保存成功！',
              icon: 'success',
              duration: 1500
            })
            
            setTimeout(function () {
              wx.switchTab({
                url: '../my/my'
              })
            }, 800)
          } else {
            wx.showToast({
              title: '网络异常,保存失败！',
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
    }
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