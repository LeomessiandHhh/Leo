<template>
<div>
  <div style="margin-left: 150px;margin-top: 50px">
<rol style="margin-left: 50px" v-for="(item,index) in noteList" :key="item">
  <div class="note-list">
  <div class="note">
    <router-link  :to="'/notedetail?id='+(item.id)" class="note-cover" style="display:inline-block;width:240px;height:319.875px">
      <img :src="item.picture1" alt="" height="319.875" >
    </router-link>
    <div class="note-info clearfix">
    <router-link  :to="'/notedetail?id='+(item.id)" class="note-name"  style="height: 40px">{{ item.noteTitle }}</router-link>
    <router-link  :to="'/user?id='+(item.userId)" class="user-head" >
      <img :src="usernameList[index].avatar" alt="" >
    </router-link>
    <router-link :to="'/user?id='+(item.userId)"  class="user-name">{{usernameList[index].username}}</router-link>
    <span class="not-like right" data-like="not-like" onclick="setLike(31407977,this,'K0snn029WIBSivK9XB1n84BiPYLwX9uVfDZhIb8s')">{{item.likes}}</span>
  </div>
  </div>
  </div>
  </rol>

  </div>
  <div align="center" >
    <el-pagination
      background
      layout="prev, pager, next"
      :page-sizes="[10,20,50]"
      :total="pageInfo.total"
      :current-page="pageInfo.pageNo"
      :page-size="pageInfo.pageSize"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange">
    </el-pagination>
  </div>
</div>
</template>

<script>
import noteApi from "../../api/note"
import userApi from "../../api/user"
export default {
  data () {
    return {
      count:0,
      noteList: [],
      usernameList:[],
      pageInfo: {
        pageNo: 1,
        pageSize: 8,
        total: 0,
      },
    }
  },
  created() {
    this.getNoteList();
  },
  methods: {
    async getNoteList() {
      let res = await noteApi.getNoteList({pageNo: this.pageInfo.pageNo, pageSize: this.pageInfo.pageSize});
      res = res.data;
      if (res.success) {
        this.noteList = res.data.records;
        this.pageInfo.total = res.data.total;
        this.usernameList=[];
        let uidList=[];
        for(let i=0;i<this.noteList.length;i++)
          uidList.push({id:this.noteList[i].userId});
        let res2=  await userApi.getUserList(uidList);
        res2=res2.data;
        if(res2.success){
          this.usernameList=res2.data;
        }
        }
      },
    // 页面数量改变后查询处理
    handleSizeChange(val) {
      console.log(val);
      this.pageinfo.pageSize = val;
       this.getNoteList();
    },
    // 页码改变后查询处理
    handleCurrentChange(val) {
      console.log(val);
      this.pageInfo.pageNo = val;
      this.getNoteList();
    }
  }
}
</script>

<style scoped>
.note-list {
  display: inline-block;
  width: 240px;
  vertical-align: top;
}
.note-list .note {
  margin-bottom: 20px;
  position: relative;
}
element.style {
  display: inline-block;
  width: 240px;
  height: 319.875px;
}
.note-list .note-cover {
  background: #eee;
  border-top-right-radius: 8px;
  border-top-left-radius: 8px;
}
.note-list a {
  display: inline-block;
  vertical-align: top;
}
a {
  color: #333;
  text-decoration: none;
}
.note-list img {
  width: 240px;
  vertical-align: top;
  border-top-right-radius: 8px;
  border-top-left-radius: 8px;
}
.note-list .note-info {
  padding: 10px 10px 13px;
  border-bottom-right-radius: 8px;
  border-bottom-left-radius: 8px;
  box-shadow: 1px 1px 4px rgb(124 131 140 / 4%), 1px 1px 4px rgb(124 131 140 / 4%), -1px 1px 4px rgb(124 131 140 / 4%), -1px 1px 4px rgb(124 131 140 / 4%);
}
.note-list .note-name {
  display: block;
  max-height: 40px;
  font-size: 15px;
  line-height: 20px;
  text-overflow: ellipsis;
  overflow: hidden;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  margin-bottom: 10px;
}
.note-list .user-head img {
  width: 22px;
  height: 22px;
  border-radius: 100%;
}
.note-list .user-name {
  max-width: 120px;
  margin-left: 4px;
  font-size: 12px;
  line-height: 20px;
  vertical-align: middle;
  text-overflow: ellipsis;
  white-space: nowrap;
  overflow: hidden;
}
.note-list .not-like {
  background:  url(../../assets/love.png) no-repeat left 1px;
  _background-image: none;
  background-size: 15px;

}

.note-list .like, .note-list .not-like {
  -webkit-touch-callout: none;
  -webkit-tap-highlight-color: transparent;
  -webkit-user-select: none;
  padding-left: 19px;
  font-size: 12px;
  cursor: pointer;
  background-size: 15px;
  display: inline-block;
  height: 15px;
  position: absolute;
  right: 10px;
  bottom: 16px;
}
.right {
  float: right;
}
.note-list.ml13 {
  margin-left: 13px;
}
.note-list.ml14 {
  margin-left: 14px;
}
</style>
