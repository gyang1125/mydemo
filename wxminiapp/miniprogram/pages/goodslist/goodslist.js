import * as wxdb from "../../request/wxdbservice.js";
Page({
  data: {
    tabs: [
      {
        id: 0,
        value: "综合",
        isActive: true,
      },
      {
        id: 1,
        value: "销量",
        isActive: false,
      },
      {
        id: 2,
        value: "价格",
        isActive: false,
      },
    ],

    products: [],
  },

  queryParams: {
    query: "",
    pid: "",
    pagenum: 1,
    pagesize: 10,
  },

  totalPages: 1,

  onLoad: function (options) {
    this.queryParams.pid = options.pid;
    this.getProductList();
  },

  getProductList() {
    wxdb.getCollection("products").then((res) => {
      console.log(res.data);
      const total = res.data.length;
      this.totalPages = Math.ceil(total / this.queryParams.pagesize);
      // console.log(this.totalPages);

      this.setData({
        products: [...this.data.products, ...res.data],
      });
      // 关闭下拉刷新的窗口 如果没有调用下拉刷新的窗口 直接关闭也不会报错
      wx.stopPullDownRefresh();
    });
  },

  // 标题点击事件 从子组件传递过来的
  handleTabsItemChange(e) {
    // 1 获取被点击的标题索引
    const { index } = e.detail;
    // 2 修改源数组
    let { tabs } = this.data;
    tabs.forEach((v, i) =>
      i === index ? (v.isActive = true) : (v.isActive = false)
    );
    // 3 赋值道data中
    this.setData({
      tabs,
    });
  },

  // 页面上滑 滚动条触底事件
  onReachBottom() {
    //  1 判断还有没有下一页数据
    if (this.queryParams.pagenum >= this.totalPages) {
      // 没有下一页数据
      // console.log(
      //   "%c" + "没有下一页数据",
      //   "color:red;font-size:100px;background-image:linear-gradient(to right,#0094ff,pink)"
      // );
      wx.showToast({ title: "没有下一页数据" });
    } else {
      // 还有下一页数据
      //  console.log('%c'+"有下一页数据","color:red;font-size:100px;background-image:linear-gradient(to right,#0094ff,pink)");
      this.queryParams.pagenum++;
      this.getProductList();
    }
  },
  // 下拉刷新事件
  onPullDownRefresh() {
    // 1 重置数组
    this.setData({
      products: [],
    });
    // 2 重置页码
    this.queryParams.pagenum = 1;
    // 3 发送请求
    this.getProductList();
  },
});
