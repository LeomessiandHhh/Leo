<template>
  <div style="margin-top: 20px">
    <el-col :span="11" :offset="4">
  <div id="left" class="mt30">
    <el-carousel trigger="click" style="border-radius: 8px;overflow: hidden;"height=390px>
      <el-carousel-item  v-for="(item,index) in noteImg" :key="item">
        <el-image :src="item" style="width: 490px;height: 100%;margin-left:100px"></el-image>
      </el-carousel-item>
    </el-carousel>
    <!--作者-->
    <div class="menu-head">
      <h3 class="note-title relative" style="font-weight:normal">
        <p style="max-width:590px;font-weight:bold;">{{ note.noteTitle }}</p>
        <div class="editmod" style="bottom:4px;">
        </div>
      </h3>
      <div class="author clearfix">
        <div class="author-img">
          <router-link :to="'/user?id='+noteAuthor.id">
            <img :src="noteAuthor.avatar" alt="">
          </router-link>
        </div>
        <div class="author-info">
          <h3 style="width: auto;max-width: 372px;margin-right: 20px; margin-top: 0px">
            <router-link :to="'/user?id='+noteAuthor.id">{{ noteAuthor.username }}</router-link>
          </h3>
          <a class="gz" href="javascript:;" data-action="add" onclick=" guanzhu(this,'26590119','nBrgHF2v2CLo4RSDBGGnqqnnBOX5u6DZmUBSAzEQ','0') "><span class="addicon">＋</span> 关注</a>
        </div>
        <div class="fav-share clearfix" >
          <span class="not-like" data-like="like"  onclick="setLike(31468114,this,'nBrgHF2v2CLo4RSDBGGnqqnnBOX5u6DZmUBSAzEQ')" style="border:1px solid #FFB31A;color: #FFB31A; ">{{ note.likes }}</span>
          <a style="margin-left: 10px" href="javascript:;" onclick=" setFavorite('31468114',this) " data-favorite="unFavorite">收藏</a>
        </div>
      </div>
    </div>
    <!--笔记内容-->
    <div class="note-content">

      <div class="note-info">
       {{note.description}}</div>

      <div class="other-info">创建时间 :{{note.createTime}}</div>

    </div>
    <!--评论-->
    <div class="comment" style="margin-bottom: 80px;">
      <h3>评论</h3>
      <ul class="comment-list">


      </ul>
      <h3 class="mt30 not-comment">暂无评论</h3>
    </div>
  </div>
    </el-col>
    <el-col :span=4 :offset="1" style="margin-top: 10px"><h3 class="title">相关笔记</h3>
    <el-col v-for="(v,k,i) in noteList" style="margin-top: 20px;font-size: 20px">
      <el-col :span="2" v-for="(v2,k2,i2) in v">
   <router-link @click.native="this.getNoteDetail()":to="'/notedetail?id='+v2.id" class="related-title"  :style="'background: url('+ v2.picture2 +') no-repeat center center;background-size: cover'"></router-link>
      </el-col>
      <el-col :span="2" :offset="7">
        <div class="menu-information" v-for="(v2,k2,i2) in v">
          <router-link :to="'/notedetail?id='+v2.id" ><h3>{{v2.noteTitle}}</h3></router-link>
        <div class="source"><span class="not-like" data-like="not-like">
          {{v2.likes}}</span></div></div>
      </el-col>
    </el-col>
    </el-col>
  </div>
</template>

<script>
import NoteApi from "../../api/note"
export default {
  name: "NoteDetail",
  data(){
        return{
          id:'',
          note:'',
          noteImg:[],
          noteList:'',
          noteAuthor:'',
            };
          },
  created(){
    this.id=this.$route.query.id;
    this.getNoteDetail();
    this.getNoteAuthorInfo();
    this.getNoteList();
  },
  methods:{
    async getNoteDetail() {
      let res = await NoteApi.getNoteDetail({id:this.id});
      res = res.data;
      if (res.code == 200) {
        this.note = res.data;
        if(this.note.picture1!='')
          this.noteImg.push(this.note.picture1);
        if(this.note.picture2!='')
          this.noteImg.push(this.note.picture2);
        if(this.note.picture3!='')
          this.noteImg.push(this.note.picture3);
        if(this.note.picture4!='')
          this.noteImg.push(this.note.picture4);
      }
    },
    async getNoteAuthorInfo() {
      let res = await NoteApi.getNoteAuthorInfo({id:this.id});
      res = res.data;
      if (res.code == 200) {
        this.noteAuthor = res.data;
      }
    },
    async getNoteList(){
      let res=await NoteApi.getHotNote();
      res=res.data;
      if(res.success){
        this.noteList=res.data;
      }
    }
  }
}
</script>

<style scoped>
body{
  background: #fff;
  color: #333;
  font: 12px Noto Sans,"思源黑体";
  padding-top: 60px;
}
.menu-information {
  position: relative;
  display: inline-block;
  width: 160px;
  height: 88px;
  padding-top: 8px;
}
.menu-information h3 {
   font-size: 15px;
   margin-top: 2px;
   line-height: 20px;
   height: 40px;
   text-overflow: ellipsis;
   overflow: hidden;
   display: -webkit-box;
   -webkit-line-clamp: 2;
   -webkit-box-orient: vertical;
 }
.source {
  position: absolute;
  font-size: 13px;
  left: 0;
  bottom: 11px;
}
.source .like, .source .not-like {
  margin-right: 10px;
  padding-left: 19px;
  cursor: pointer;
  background: url(https://cp1.douguo.com/static/static/nweb/images/like2.png?v=98) no-repeat left 2px;
  background-size: 15px;
}
.related-title {
  display: inline-block;
  width: 88px;
  height: 88px;
  overflow: hidden;
  border-radius: 11px;
  margin-right: 8px;
  vertical-align: top;
}
.title {
  font-size: 16px;
  line-height: 16px;
  font-weight: bold;
}
.mt30 {
  margin-top: 30px;
}
.menu-head {
  position: relative;
  margin-top: 30px;
}
.menu-head .note-title {
  margin-top: 20px;
  font-size: 22px;
  line-height: 26px;
  font-weight: bold;
}
.menu-head .author {
  position: relative;
  margin: 25px 0;
}
.menu-head .author-img {
  width: 30px;
  height: 30px;
  margin-right: 6px;
}
.menu-head .author-info, .menu-head .author-img {
  float: left;
}
a {
  color: #333;
  text-decoration: none;
}
.menu-head .author-img img {
  width: 100%;
  height: 100%;
  border-radius: 100%;
}

element.style {
  width: auto;
  max-width: 372px;
  margin-right: 20px;
}
.menu-head .author-info h3 {
  display: inline-block;
  font-size: 13px;
  line-height: 30px;
  width: 372px;
}
.menu-head .gz {
  display: inline-block;
  width: 80px;
  text-align: center;
  font-size: 12px;
  color: #333;
  border: 1px solid #333;
  border-radius: 4px;
  left: 220px;
  height: 30px;
  line-height: 28px;
  top: -2px;
  vertical-align: top;
}
.fav-share {
  position: absolute;
  top: -2px;
  right: 0;
}
element.style {
  border: 1px solid #FFB31A;
  color: #FFB31A;
}
.fav-share .like, .fav-share .not-like {
  float: left;
  -webkit-touch-callout: none;
  -webkit-tap-highlight-color: transparent;
  -webkit-user-select: none;
  cursor: pointer;
  background: url(../../assets/love.png?v=7) no-repeat 20px 10px;
  background-size: 16px;
  height: 34px;
  line-height: 32px;
  display: inline-block;
  font-size: 15px;
  border: 1px solid #333;
  display: block;
  width: 80px;
  padding-left: 24px;
  text-align: center;
  border-radius: 4px;
}
.fav-share>a {
  display: inline-block;
  width: 80px;
  height: 34px;
  border-radius: 4px;
  color: #fff;
  border: 1px solid #FFB31A;
  font-size: 15px;
  text-align: center;
  text-decoration: none;
  line-height: 32px;
  background: #FFB31A url(../../assets/like.png) no-repeat 20px 6px;
  background-size: 20px 20px;
  padding-left: 20px;
}
.note-content .note-info {
  margin-top: 0;
  font-size: 15px;
  color: #333;
  line-height: 27px;
}
.note-content {
  padding-top:60px;
  padding-bottom: 40px;
}

.relative {
  position: relative;
}
.other-info {
  margin-top: 15px;
  font-size: 13px;
  color: #999;
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #eee;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #eee;
}
</style>
