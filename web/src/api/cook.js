import http from "../utils/request";
export default {
  async getDailyHotCook() {
    return await http.getRest("cook/dailyhotcook");
  },
  async getCookDetail(params) {
    return await http.post("cook/cookdetail", params);
  },
  async getAuthorInfo(params) {
    return await http.post("cook/authorinfo", params);
  },
  async getMaterialInfo(params) {
    return await http.post("cook/material", params);
  },
};