const db = wx.cloud.database();
let ajaxTimes = 0;

export function getCollection(params) {
  console.log("db connection");

  ajaxTimes++;
  // 显示加载中 效果
  wx.showLoading({
    title: "加载中",
  });
  return new Promise((resolve, reject) => {
    db.collection(params).get({
      success: (result) => {
        resolve(result);
      },
      fail: (err) => {
        reject(err);
      },
      complete: () => {
        ajaxTimes--;
        if (ajaxTimes === 0) {
          //  关闭正在等待的图标
          wx.hideLoading();
        }
      },
    });
  });
}
