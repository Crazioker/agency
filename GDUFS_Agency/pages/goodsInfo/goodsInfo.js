// pages/goodsInfo/goodsInfo.js
var util = require('../../utils/util.js')
var app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    goodsInfo:null,
    typeArray: [
      { "id": 0, "type": "代购" },
      { "id": 1, "type": "快递代拿" },
      { "id": 2, "type": "其他代办" },
    ],
    commentsArray:[
      {}
    ],
    commentContent:"",
    indentId:0,
    commentsArray:[],
  },
  accept:function(){
    var that=this;
    var studentId = wx.getStorageSync('studentId');
    var acceptedTime = util.formatTime(new Date());
    wx.request({
      url: app.data.apiUrl + '/accept/addAcceptance',
      method: 'POST',
      data: {
        studentId: studentId,
        indentId:that.data.goodsInfo.indentid,
        acceptedTime: acceptedTime
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded' // 默认值
      },
      fail: function () {
        wx.showToast({
          title: '服务器错误，接单失败',
          icon: 'none',
          duration: 2000
        })
      },
      success: function (res) {
        if (res.data == 1) {
          wx.showToast({
            title: '接单成功',
            icon: 'success',
            duration: 2000
          });
          setTimeout(function () {
            wx.switchTab({
              url: '../index/index'
            })
          }, 800)
        } else {
          wx.showToast({
            title: '保存失败，稍后再试',
            icon: 'none',
            duration: 2000
          });
        }
      }
    })
  },

  getCommentContent:function(e){
    console.log("输入框的内容："+e.detail.value);
    this.setData({
      commentContent: e.detail.value
    })
  },

  commitComment:function(e){
    var that = this;
    var studentId = wx.getStorageSync('studentId');
    if(this.data.commentContent == ''){
      wx.showToast({
        title: '请输入评论内容！',
        icon:'none',
        duration:1500
      })
    }else{
      wx.request({
        url: app.data.apiUrl + '/comment/addComment',
        method: 'POST',
        data: {
          indentId: this.data.indentId,
          acceptId: studentId,
          content: this.data.commentContent
        },
        header: {
          'content-type': 'application/x-www-form-urlencoded' // 默认值
        },
        fail: function () {
          wx.hideLoading();
          wx.showToast({
            title: '服务器错误',
            icon: 'none',
          })
        },
        success: function (res) {
          var msg = res.data;
          console.log("请求成功："+res.data)
          if (msg == 1){
            wx.showToast({
              title: '评论成功！',
              icon: 'success',
            })
            that.setData({
              commentContent:""
            })
            that.onShow();
          }else{
            wx.showToast({
              title: '评论失败！',
              icon: 'none',
            })
          }
        }
      })
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    console.log("indentId:"+options.id);
    this.setData({
      indentId: options.id
    })
    wx.request({
      url: app.data.apiUrl + '/indent/getIndentById',
      method: 'POST',
      data: {
        indentId:options.id
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded' // 默认值
      },
      fail: function () {
        wx.hideLoading();
        wx.showToast({
          title: '服务器错误',
          icon: 'none',
        })
      },
      success: function(res){
        var msg=res.data;
        that.setData({
          goodsInfo:msg
        })
        console.log(res.data)
      }
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
    var that = this;
    wx.request({
      url: app.data.apiUrl + '/comment/getComments',
      method: 'POST',
      data: {
        indentId: that.data.indentId,
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded' // 默认值
      },
      fail: function () {
        wx.showToast({
          title: '服务器错误，获取评论失败',
          icon: 'none',
          duration: 2000
        })
      },
      success: function (res) {
        console.log(res.data);
        that.setData({
          commentsArray:res.data
        })
      }
    })
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