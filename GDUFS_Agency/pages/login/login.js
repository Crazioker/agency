// pages/login/login.js
const app = getApp()
Page({
  /**
   * 页面的初始数据
   */
  data: {
    disabled: false,
    no: '',
    pwd: '',
    noinput: false,
    pwdinput: false,
  },
  noinput: function(e) {
    this.setData({
      no: e.detail.value
    });
    this.setData({
      noinput: true
    });
    if (this.data.noinput == true && this.data.pwdinput == true) {
      this.setData({
        disabled: false
      });
    }

  },
  pwdinput: function(e) {
    this.setData({
      pwd: e.detail.value
    });
    this.setData({
      pwdinput: true
    });
    if (this.data.noinput == true && this.data.pwdinput == true) {
      this.setData({
        disabled: false
      });
    }
  },
  formSubmit: function(e) {
    wx.showLoading({
      title: '登录中...',
    })
    console.log(e);
    this.setData({
      disabled: true
    });
    wx.request({
      url: app.data.apiUrl + '/user/login', 
      method: 'POST',
      data: {
        no: e.detail.value.no,
        pwd: e.detail.value.pwd
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded' // 默认值
      },
      fail: function() {
        wx.hideLoading();
        wx.showToast({
          title: '服务器错误，登录失败',
          icon: 'none',
        })
        var page = getCurrentPages().pop();
        page.onShow();
        // wx.redirectTo({
        //   url: '../login/login',
        // })
        // wx.switchTab({
        //   url: '../login/login',
        // })
      },
      success: function(res) {
        console.log(res);
        if (res.statusCode == 200) {
          if (res.data == 0) {
            wx.showToast({
              title: '用户名或密码错误',
              icon: 'none',
              duration: 2000
            })
            var page = getCurrentPages().pop();
            page.onShow();
          } else {
            wx.setStorageSync('sessionId', res.header["Cookie"]);
            wx.setStorageSync('studentId', e.detail.value.no);
            wx.showToast({
              title: res.data.msg,
              icon: 'success',
              duration: 2000
            })
            setTimeout(function() {
              wx.switchTab({
                url: '../index/index',
              })
            }, 2000)
          }
        } else {
          wx.showToast({
            title: '服务器出现错误',
            icon: 'none',
            duration: 2000
          })
        }
      }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.setData({
      disabled: false
    });
    var sessionid = wx.getStorageSync('sessionId');
    // console.log(sessionid);

    wx.request({
      url: app.data.apiUrl + '/user/checkSession',
      method: 'GET',
      header: {
        'content-type': 'application/x-www-form-urlencoded', // 默认值
        'Cookie': sessionid
      },
      success: function(res) {
        if (res.data == 1) {
          console.log('request success:' + res.data)
          wx.switchTab({
            url: '../index/index',
          })
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
    if (this.data.no == '' || this.data.pwd == '') {
      this.setData({
        disabled: true
      });
    } else {
      this.setData({
        disabled: false
      });
    }
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