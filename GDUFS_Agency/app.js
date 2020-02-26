//app.js

App({

  data: {
    sessionid: null,
    // apiUrl:'http://120.78.219.119:8080/',
    apiUrl:'http://120.78.219.119:8080/agency',
    //  apiUrl: 'http://192.168.164.214:8080/agency',
    // apiUrl: 'http://10.173.152.128:8080/agency',
    userInfo: null,
    currentUrl:''
  },

  onLaunch: function(e) {
    var that = this
    //"getOenid"
    console.log(that.getSessionid())
  },
  getSessionid: function() {
    var that = this
    wx.getStorageSync({
      key: 'sessionId',
      success: function(res) {
        that.data.sessionid = res.data.sessionid;
        console.log("sessionid= " + that.data.sessionid)
      },
    });
  },
})