var app = getApp();
var util = require('../../utils/util.js')
Page({
  data: {
    balance: 0,
    typeArray: [{
        "id": 0,
        "type": "代购"
      },
      {
        "id": 1,
        "type": "快递代拿"
      },
      {
        "id": 2,
        "type": "其他代办"
      },
    ],
    stateArray: [{
        "id": 0,
        "state": "未接单"
      },
      {
        "id": 1,
        "state": "进行中"
      },
      {
        "id": 2,
        "state": "已完成"
      },
    ],
    acceptStateArray: [

    {
      "id": 0,
      "state": "进行中"
    },
    {
      "id": 1,
      "state": "已完成"
    },
    ],
    agentOrders: [
      // {
      //   price: 30,
      //   date: '2019/12/09 12:00',
      //   type: '代拿快递',
      //   place: '师苑4栋',
      //   status: '进行中',
      //   description: "蜂巢3号柜",
      //   indentId: 7,
      //   acceptId: 20161003434
      // }
    ],
    accessOrders: [
      // {
      //   price: 30,
      //   date: '2019/12/09 12:00',
      //   type: '代拿快递',
      //   place: '师苑4栋',
      //   status: '进行中',
      //   description: "蜂巢3号柜",
      //   indentId: 14,
      //   acceptId: 20161003424
      // }
    ],
    navbar: ['已发布', '已接取'],
    currentTab: 0
  },
  navbarTap: function(e) {
    console.debug(e);
    this.setData({
      currentTab: e.currentTarget.dataset.idx
    })
  },

  cancelAgentOrder: function(e) {
    var studentId = wx.getStorageSync('studentId');
    console.log("indentId: " + e.currentTarget.dataset.indentid);
    console.log("acceptId: " + e.currentTarget.dataset.acceptid);
    console.log("price: " + e.currentTarget.dataset.price);
    var that = this;
    wx.showModal({
      title: '提示',
      content: '确认取消该订单吗？',
      success(res) {
        if (res.confirm) {
          console.log('用户点击确认')
          // that.data.agentOrders.splice(e.currentTarget.dataset.index, 1)
          // that.setData({
          //   agentOrders: that.data.agentOrders
          // })
          wx.request({
            url: app.data.apiUrl + '/indent/cancelIndentByPublishNotAccepted',
            method: "POST",
            data: {
              indentId: e.currentTarget.dataset.indentid,
              price: e.currentTarget.dataset.price,
              studentId: studentId
            },

            header: {
              'content-type': 'application/x-www-form-urlencoded', // 默认值
            },
            success(res) {
              if (res.data == 1) {
                console.log("请求成功：" + res.data)
                wx.showToast({
                  title: '取消成功！',
                  icon: 'success',
                  duration: 1500
                })
                that.onShow();
              } else {
                wx.showToast({
                  title: '取消失败，稍后再试',
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
        } else if (res.cancel) {
          console.log('用户点击取消')
        }
      }
    })
  },

  cancelAgentOrderHasAccepted: function(e) {
    console.log("indentId: " + e.currentTarget.dataset.indentid);
    console.log("acceptId: " + e.currentTarget.dataset.acceptid);
    var that = this;
    wx.showModal({
      title: '提示',
      content: '确认取消该订单吗？',
      success(res) {
        if (res.confirm) {
          console.log('用户点击确认')
          // that.data.agentOrders.splice(e.currentTarget.dataset.index, 1)
          // that.setData({
          //   agentOrders: that.data.agentOrders
          // })
          wx.request({
            url: app.data.apiUrl + '/indent/cancelIndentByPublishHasAccepted',
            method: "POST",
            data: {
              indentId: e.currentTarget.dataset.indentid,
              acceptId: e.currentTarget.dataset.acceptid,
            },

            header: {
              'content-type': 'application/x-www-form-urlencoded', // 默认值
            },
            success(res) {
              if (res.data == 1) {
                console.log("请求成功：" + res.data)
                wx.showToast({
                  title: '取消成功！',
                  icon: 'success',
                  duration: 1500
                })
                that.onShow();
              } else {
                wx.showToast({
                  title: '取消失败，稍后再试',
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
        } else if (res.cancel) {
          console.log('用户点击取消')
        }
      }
    })
  },

  //确认送达
  updateIntentAccepted: function(e) {

    console.log(e.currentTarget);
    console.log(e.currentTarget.dataset.indentid);
    // acceptId: e.currentTarget.dataset.acceptid,
    //   price: e.currentTarget.dataset.price,
    var that = this;
    wx.showModal({
      title: '提示',
      content: '确认已送达吗？',
      success(res) {
        if (res.confirm) {
          console.log('用户点击确认送达')
          console.log(e.currentTarget.dataset.price)
          wx.request({
            url: app.data.apiUrl + '/indent/updateIndentByPublishHasAccepted',
            method: "POST",
            data: {
              indentId: e.currentTarget.dataset.indentid,
              acceptId: e.currentTarget.dataset.acceptid,
              price: e.currentTarget.dataset.price,
              finishedTime: util.formatTime(new Date())
            },

            header: {
              'content-type': 'application/x-www-form-urlencoded', // 默认值
            },
            success(res) {
              if (res.data == 1) {
                console.log("请求成功：" + res.data)
                wx.showToast({
                  title: '确认成功！',
                  icon: 'success',
                  duration: 1500
                })
                that.onShow();
              } else {
                wx.showToast({
                  title: '确认失败失败，稍后再试',
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
          var agentOrders = that.data.agentOrders;
          agentOrders.status = "已完成";
          that.setData({
            accessOrders: agentOrders
          })
        } else if (res.cancel) {
          console.log('用户点击取消')
        }
      }
    })
  },

  cancelAcceptedOrder: function(e) {
    console.log("indentId: " + e.currentTarget.dataset.indentid);
    console.log("acceptId: " + e.currentTarget.dataset.acceptid);
    var that = this;
    wx.showModal({
      title: '提示',
      content: '确认取消该订单吗？',
      success(res) {
        if (res.confirm) {
          console.log('用户点击确认')
          that.data.accessOrders.splice(e.currentTarget.dataset.index, 1)
          that.setData({
            accessOrders: that.data.accessOrders
          })
          wx.request({
            url: app.data.apiUrl + 'cancelIndentByPublishHasAccepted',
            method: "POST",
            data: {
              indentId: e.currentTarget.dataset.indentid,
              acceptId: e.currentTarget.dataset.acceptid,
            },

            header: {
              'content-type': 'application/x-www-form-urlencoded', // 默认值
            },
            success(res) {
              if (res.data == 1) {
                console.log("请求成功：" + res.data)
                wx.showToast({
                  title: '取消成功！',
                  icon: 'success',
                  duration: 1500
                })
              } else {
                wx.showToast({
                  title: '取消失败，稍后再试',
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
        } else if (res.cancel) {
          console.log('用户点击取消')
        }
      }
    })
  },

  onShow: function() {
    var that = this;
    var studentId = wx.getStorageSync("studentId");
    //获取已发布的数据源
    wx.request({
      url: app.data.apiUrl + '/indent/getIndentsByPubish?studentId=' + studentId,
      method: 'Get',
      header: {
        'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
      },
      success(res) {
        var msg = res.data;
        console.log(msg);
        that.setData({
          agentOrders: msg
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
    //获取已接取的数据源
    wx.request({
      url: app.data.apiUrl + '/accept/getAcceptances?acceptId=' + studentId,
      method: 'Get',
      header: {
        'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
      },
      success(res) {
        var msg = res.data;
        console.log(msg);
        that.setData({
          accessOrders: msg
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
  }

});