import { Component, OnInit } from '@angular/core';
import { PostService } from '../post.service';
import { Observable } from 'rxjs';
import { Post } from '../models';
import { subscribe } from 'diagnostics_channel';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrl: './list.component.css'
})
export class ListComponent implements OnInit{
  
  constructor(private svc: PostService){}

  p$ !: Promise<Post[]> ;

  ngOnInit(): void {
    this.p$ = this.svc.getAllPosts();
  }

}
