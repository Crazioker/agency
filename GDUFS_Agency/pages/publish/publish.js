// pages/publish/publish.js
var util = require('../../utils/util.js')
var app = getApp()
var date = new Date();
var currentHours = date.getHours();
var currentMinute = date.getMinutes();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    selectArray: [{
      "id": "0",
      "text": "发布代购"
    }, {
      "id": "1",
      "text": "发布快递代拿"
    }, {
      "id": "2",
      "text": "发布其他代办"
    }],
    balance: 0,
    text: "您的代购需求",
    type: 0,
    description: '',
    address: '',
    state: 0,
    price: 0,
    time: '尽快送达',
    planTime: '尽快送达',
    multiIndex: [0, 0],
    multiArray: [
      ['今天', '明天', '3-2', '3-3', '3-4', '3-5'],
      [0, 1, 2, 3, 4, 5, 6]
    ],
  },

  //选择发布类别
  getData: function(e) {
    this.setData({
      type: e.detail.id
    })
    if (this.data.type == 0) {
      this.setData({
        text: "您的代购需求"
      })
    } else if (this.data.type == 1) {
      this.setData({
        text: "快递地点和取件方法"
      })
    } else {
      this.setData({
        text: "您的需求"
      })
    }
    console.log(e.detail)
  },

  //选择器的点击事件
  pickerTap: function() {
    this.setData({
      multiIndex: [0, 0],
      multiArray: [
        ['今天', '明天', '3-2', '3-3', '3-4', '3-5'],
        [0, 1, 2, 3, 4, 5, 6]
      ],
    })

    date = new Date();

    var monthDay = ['今天', '明天'];
    var hoursAndMin = ['尽快送达'];

    //x月x日 eg:12月6日
    for (var i = 2; i <= 6; i++) {
      var date1 = new Date(date);
      date1.setDate(date.getDate() + i);
      var md = (date1.getMonth() + 1) + "月" + date1.getDate() + "日";
      monthDay.push(md);
    }

    currentHours = date.getHours();
    currentMinute = date.getMinutes();

    this.loadPart(hoursAndMin);

    var data = {
      multiArray: this.data.multiArray,
      multiIndex: this.data.multiIndex
    }
    data.multiArray[0] = monthDay;
    data.multiArray[1] = hoursAndMin;
    this.setData(data);
  },

  //选择器列的绑定事件
  bindTimeColumnChange: function(e) {
    var column = e.detail.column;
    var value = e.detail.value;
    console.log(e)
    console.log('bindTimeColumnChange，column：', column + " value：" + value)
    console.log(this.data.multiArray[column][value])

    date = new Date();

    var that = this;

    var monthDay = ['今天', '明天'];
    var hoursAndMin;

    currentHours = date.getHours();
    currentMinute = date.getMinutes();

    var data = {
      multiArray: this.data.multiArray,
      multiIndex: this.data.multiIndex
    };

    //把选择的对应值赋值给multiIndex
    data.multiIndex[e.detail.column] = e.detail.value;

    //然后再判断是哪一列
    //如果是第一列
    if (column === 0) {
      //如果是第一行，则只加载当前时间之后的选项
      if (value === 0) {
        hoursAndMin = ['尽快送达'];
        that.loadPart(hoursAndMin);
      }
      //如果是其他行，则加载全部时间段
      else {
        hoursAndMin = [];
        that.loadAll(hoursAndMin);
      }

      data.multiIndex[1] = 0;
    }
    //如果是第二列
    else {
      //如果是今天
      if (data.multiIndex[0] === 0) {
        hoursAndMin = ['尽快送达'];
        that.loadPart(hoursAndMin);
      } else {
        hoursAndMin = [];
        that.loadAll(hoursAndMin);
      }
    }

    data.multiArray[1] = hoursAndMin;
    this.setData(data);
  },

  loadPart: function(hoursAndMin) {
    var minuteIndex;

    if (currentMinute > 0 && currentMinute <= 20) {
      minuteIndex = 10;
    } else if (currentMinute > 10 && currentMinute <= 20) {
      minuteIndex = 20;
    } else if (currentMinute > 20 && currentMinute <= 30) {
      minuteIndex = 30;
    } else if (currentMinute > 30 && currentMinute <= 40) {
      minuteIndex = 40;
    } else if (currentMinute > 40 && currentMinute <= 50) {
      minuteIndex = 50;
    } else {
      minuteIndex = 60;
    }

    if (minuteIndex == 60) {
      // 时
      for (var i = currentHours + 1; i < 24; i++) {
        // 分
        for (var j = 0; j < 60; j += 20) {
          var ham;
          if (j == 0) ham = i + ":" + j + '0';
          else ham = i + ":" + j;
          hoursAndMin.push(ham);
        }
      }
    } else {

      for (var j = minuteIndex; j < 60; j += 20) {
        var ham = currentHours + ":" + j;
        hoursAndMin.push(ham);
      }

      // 时
      for (var i = currentHours + 1; i < 24; i++) {
        // 分
        for (var j = 0; j < 60; j += 20) {
          var ham;
          if (j == 0) ham = i + ":" + j + '0';
          else ham = i + ":" + j;
          hoursAndMin.push(ham);
        }
      }
    }
  },

  loadAll: function(hoursAndMin) {
    // 时
    for (var i = 0; i < 24; i++) {
      // 分
      for (var j = 0; j < 60; j += 20) {
        var ham;
        if (j == 0) ham = i + ":" + j + '0';
        else ham = i + ":" + j;
        hoursAndMin.push(ham);
      }
    }
  },

  //选择送达时间
  bindTimeChange: function(e) {
    console.log('bindTimeChange，携带值为', e)
    var curDate = this.data.multiArray[0][e.detail.value[0]];
    var curTime = this.data.multiArray[1][e.detail.value[1]];
    console.log('日期：' + curDate + " 时间：" + curTime)
    var result = this.handleTime(curDate, curTime);
    this.setData({
      planTime: result
    })
    console.log("result: " + result + " planTime: " + this.data.planTime)
  },

  handleTime: function(curDate, curTime) {
    if (curDate === '今天') {
      this.setData({
        time: curTime
      })
      if (curTime === '尽快送达') {
        return '尽快送达';
      } else {
        return util.getToday(new Date()) + " " + curTime;
      }
    } else if (curDate === '明天') {
      this.setData({
        time: "明天" + curTime
      })
      return util.getToday(new Date(+new Date() + 86400000)) + " " + curTime;
    } else {
      this.setData({
        time: curDate + " " + curTime
      })
      return util.formatTime(new Date()).substring(0, 5) + "-" + curDate.substring(0, curDate.indexOf('月')) +
        "-" + curDate.substring(curDate.indexOf('月') + 1, curDate.length - 1) + " " + curTime
    }
  },

  //获取需求输入框的内容
  getDescription: function(e) {
    this.setData({
      description: e.detail.value
    })
    console.log(this.data.description)
  },

  //获取收货地点输入框的内容
  getAddress: function(e) {
    this.setData({
      address: e.detail.value
    })
    console.log(this.data.address)
  },

  //获取支付费用输入框的内容
  getPrice: function(e) {
    var that = this;
    var mesValue
    //正则验证，充值金额仅支持小数点前8位小数点后2位
    if (/^\d{1,8}?$/.test(e.detail.value)) {
      //满足条件
      mesValue = e.detail.value;
    } else {
      //不满足条件
      mesValue = e.detail.value.length > 8 ?
        e.detail.value.substring(0, 8) :
        e.detail.value.substring(0, e.detail.value.length - 1);
      wx.showToast({
        title: '支付金额仅支持8位整数哦',
        icon: 'none',
        duration: 1500
      })
    }
    this.setData({
      price: mesValue
    })
    console.log(this.data.price)
  },

  //检查输入是否有效
  inputInvalid: function() {
    if (this.data.description == "" || this.data.address == "" ||
      this.data.price == 0 || this.data.planTime == "") {
      return false;
    } else {
      return true;
    }
  },

  //发布按钮的点击事件
  publish: function(e) {
    if (this.inputInvalid()) {
      var b = parseInt(this.data.balance);
      console.log(b);
      if (this.data.price > b) {
        console.log("余额不足");
        wx.showToast({
          title: '余额不足请先充值！',
          icon: 'none',
          duration: 2000
        })
        setTimeout(function() {
          wx.navigateTo({
            url: '../recharge/recharge',
          })
        }, 1000)

      } else {
        var studentId = wx.getStorageSync("studentId");
        console.log(studentId)
        wx.request({
          url: app.data.apiUrl + '/indent/addIndent',
          method: "POST",
          data: {
            type: this.data.type,
            price: this.data.price,
            description: this.data.description,
            address: this.data.address,
            state: this.data.state,
            publishId: studentId,
            publishTime: util.formatTime(new Date()),
            planTime: this.data.planTime
          },

          header: {
            'content-type': 'application/x-www-form-urlencoded', // 默认值
          },
          success(res) {
            if (res.data == 1) {
              console.log("请求成功：" + res.data)
              wx.showToast({
                title: '发布成功！',
                icon: 'success',
                duration: 1500
              })
              setTimeout(function() {
                wx.switchTab({
                  url: '../index/index',
                })
              }, 800)
            } else {
              wx.showToast({
                title: '发布失败，稍后再试',
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
    } else {
      wx.showToast({
        title: '请输入完整信息哦！',
        icon: 'none',
        duration: 2000
      });
    }

    console.log(
      "发布类型type是：" + this.data.type + "\n" +
      "费用price是：" + this.data.price + "\n" +
      "需求description是：" + this.data.description + "\n" +
      "收货地点address是：" + this.data.address + "\n" +
      "发布时间punlishTime是：" + util.formatTime(new Date()) + "\n" +
      "送达时间planTime是：" + this.data.planTime + "\n")
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {},

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {
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
        console.log(msg['balance']);
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

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {
    wx.navigateTo({
      url: '../add/add',
    })
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