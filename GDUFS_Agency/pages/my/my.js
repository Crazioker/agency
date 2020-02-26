var app = getApp();
Page({
  data: {
    username: "用户名未设置",
    balance: '0.0'
  },

  goToRecharge:function(){
    wx.navigateTo({
      url: '../recharge/recharge',
    })
  },

  onShow: function() {
    // var sessionid = wx.getStorageSync('sessionId');
    // if (sessionid == "") {
    //   wx.showModal({
    //     title: '未登录',
    //     content: '返回登录页面',
    //     confirmText: '确定',
    //     cancelText: '取消',
    //     success: function (res) {
    //       if (res.confirm) {
    //         //  console.log('用户点击确定')
    //         wx.navigateTo({
    //           url: '../login/login',
    //         })
    //       } else if (res.cancel) {
    //         //  console.log('用户点击取消')
    //         wx.switchTab({
    //           url: '../index/index',
    //         })
    //       }
    //     }
    //   })
    // }else{
    //   wx.request({
    //     url: app.data.apiUrl+'/user/getuser',
    //   })
    // }
    var that = this;
    var studentId = wx.getStorageSync("studentId");
    wx.request({
      url: app.data.apiUrl + '/user/getUser?studentId=' + studentId,
      method: 'Get',
      header: {
        'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
      },
      success(res) {
        var msg = res.data;
        console.log("请求成功：" + res.data);
        console.log(msg['username']);
        console.log(msg['balance']);
        console.log(msg['address']);
        if (msg['username'] != null) {
          that.setData({
            username: msg['username']
          })
        }
        that.setData({
          balance: msg['balance']
        })

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

  onLoad: function() {
  },

  goToPreview:function(){
    wx.navigateTo({
      url: '../preview/preview',
    })
  },

  logout:function(){
    console.log('logout')
    wx.setStorageSync('sessionId', '');
    wx.setStorageSync('studentId', '');
    wx.navigateTo({
      url: '../login/login',
    })
  },
  userInformation:function() {
    console.log('userInformation')
    wx.navigateTo({
      url: '../info/info',
    })
  },

  changepassword: function () {
    console.log('changepassword')
    wx.navigateTo({
      url: '../changePwd/changePwd',
    })
  },

})