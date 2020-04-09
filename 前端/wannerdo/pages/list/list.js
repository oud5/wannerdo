// pages/list/list.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    list:undefined
  },

  /**
   * 得到代办事项信息 
   */
  load:function()
  {
    var that = this;
    wx.login({
      success: function(res){
        // success
        console.log(res);
        wx.request({
          url: getApp().globalData.url+'Login',
          data: {
            code:res.code
          },
          method: 'GET', // OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT
          // header: {}, // 设置请求的 header
          success: function(res){
            // success
            console.log(res);
            console.log(res.data);
            that.setData({
              list:res.data
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
   * 添加event
   */
  add:function(e)
  {
    wx.reLaunch({
      url: '../add/add'
    })
  },

  /**
   * 查看event的细节
   */

  goTODetail:function(e)
  {
    // console.log(e.currentTarget.dataset)
    var id = e.currentTarget.dataset.id
    var title = e.currentTarget.dataset.title
    var content = e.currentTarget.dataset.content
    wx.reLaunch({
      url: '../detail/detail?title='+title+'&content='+content+"&id="+id,
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.load();
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