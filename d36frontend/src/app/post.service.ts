import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Post } from './models';
import { Observable, lastValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private httpclient: HttpClient) { }
  
  public PostPost(f : FormData){
    // NOW YOU have to return in formData cant just parse objects in
    return lastValueFrom(this.httpclient.post<Post>("/api/post",f));
  }

  public getAllPosts(): Promise<Post[]> {

    return lastValueFrom(this.httpclient.get("/api/posts"))
    .then((result)=>{
      console.log(JSON.parse(JSON.stringify(result)))
      return JSON.parse(JSON.stringify(result));
    })
  }

  public getPost(post_id: string){
    const params = new HttpParams()
    .set("post_id",post_id)
    //now you learn the difference between setting param and no setting pararm
    // if you set param will be ?param 
    // just set the value
    return lastValueFrom(this.httpclient.get('/api/posts/'+ {post_id}))
    .then((result)=>{
      console.log(JSON.parse(JSON.stringify(result)))
      return JSON.parse(JSON.stringify(result));
    })
  }
}
