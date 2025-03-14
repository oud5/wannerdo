//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    motto: '欢迎使用Wannerdo',
    userInfo: undefined,
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo')
  },
  //事件处理函数
  bindViewTap: function() {
    wx.switchTab({
      url: '../list/list'
    })
  },
  onLoad: function () {
    var that = this
    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true,
        motto:"请点击头像"
      })
    } else if (this.data.canIUse){
      // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
      // 所以此处加入 callback 以防止这种情况
      app.userInfoReadyCallback = res => {
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true,
          motto:"请点击头像"
        })
      }
    } else {
      // 在没有 open-type=getUserInfo 版本的兼容处理
      wx.getUserInfo({
        success: res => {
          app.globalData.userInfo = res.userInfo
          this.setData({
            userInfo: res.userInfo,
            hasUserInfo: true,
            motto:"请点击头像"
          })
        }
      })
    }



    wx.getSetting({
      success(res){
        console.log(res.authSetting)
        if(JSON.stringify(res.authSetting) != "{}")
        {
          wx.switchTab({
            url: '../list/list'
          })
        }
        // if(res.authSetting != {})
        // {
        //   wx.switchTab({
        //     url: '../list/list'
        //   })
        // }
      }
    })


  },
  getUserInfo: function(e) {
    console.log(e)
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true,
      motto: '请点击头像'
    })
    
    //授权的时候检查是否有这个用户的openid
    wx.login({
      success: function(res){
        // success
        console.log(res);
        wx.request({
          url: getApp().globalData.url+'CheckHasOpenid',
          data: {
            code:res.code
          },
          method: 'GET', // OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT
          // header: {}, // 设置请求的 header
          success: function(res){
            // success
            console.log(res);
            console.log(res.data);
          },
          fail: function() {
            // fail
          },
          complete: function() {
            // complete
          }
        })

      },
      fail: function() {
        // fail
      },
      complete: function() {
        // complete
      }
    })

    this.bindViewTap()
  }
})
