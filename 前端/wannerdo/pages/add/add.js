// pages/add/add.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    title:undefined,
    content:undefined
  },

  send:function(e)
  {
    var that = this;
    // console.log(this.data.title)
    wx.login({
      success: function(res){
        // success
        console.log("send")
        console.log(res);
        // console.log("a");
        console.log(that.data.title)
        wx.request({
          url: getApp().globalData.url+'AddEvent',
          data: {
            title:that.data.title,
            content:that.data.content,
            code:res.code
          },
          method: 'GET', // OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT
          // header: {}, // 设置请求的 header
          success: function(res){
            // success
            //TODO
            console.log("成功");
            // console.log(that.title)
            wx.switchTab({
              url: '../list/list'
            })
            
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
  },

  /**
   * title 
   */
  setTitle:function(e)
  {
    // console.log(e)
    this.setData({
      title:e.detail.value
    })
  },

  /**
   * content 
   */
  setContent:function(e)
  {
    // console.log(e)
    this.setData({
      content:e.detail.value
    })
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