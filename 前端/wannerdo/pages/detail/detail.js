// pages/detail/detail.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    id:undefined,
    title:undefined,
    content:undefined
  },

  /**
   * 返回list界面 
   */
  back:function(e)
  {
    wx.navigateBack()
  },

  /**
   * 删除这条信息
   */
  delete:function(e)
  {
    var that = this
    // console.log(that)
    wx.request({
      url: getApp().globalData.url+'DeleteEvent',
      data: {
        id:that.options.id
      },
      method: 'GET', // OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT
      // header: {}, // 设置请求的 header
      success: function(res){
        // success
        console.log("删除成功")
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
   *更改title
   */
  changeTitle:function(e)
  {
    console.log(e)
    var that = this
    wx.request({
      url: getApp().globalData.url+'ChangeTitle',
      data: {
        id:that.options.id,
        title:e.detail.value
      },
      method: 'GET', // OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT
      // header: {}, // 设置请求的 header
      success: function(res){
        // success
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
   *更改content
   */
  changeContent:function(e)
  {
    console.log(e)
    var that = this
    wx.request({
      url: getApp().globalData.url+'ChangeContent',
      data: {
        id:that.options.id,
        content:e.detail.value
      },
      method: 'GET', // OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT
      // header: {}, // 设置请求的 header
      success: function(res){
        // success
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
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    // console.log(typeof(options.id))
    this.setData({
      id:options.id,
      title:options.title,
      content:options.content
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