import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PostService } from '../post.service';
import { Post } from '../models';
import { Router } from '@angular/router';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrl: './form.component.css'
})
export class FormComponent implements OnInit{

  constructor(private fb:FormBuilder,private svc: PostService,private r:Router){}
  
  postform !: FormGroup;

  //you onl need this because of the upload
  @ViewChild("file")
  imageFile !: ElementRef

  ngOnInit(): void {
    //this.formgroup = this.creationmethod()
    this.postform = this.createForm();
  }

  // THIS IS NEWWWWW IF YOU HAVE MULTIVALUE FORM EVERYTHIGN CHANGES.
  //
  private createForm(): FormGroup<any>{
    return this.fb.group({
      comments: this.fb.control("",[Validators.required])
    });
  }

  pressed(): void {
    // NOW IS DIFF -> WE NEED TO USE FormData and craft it here.
    const f: FormData = new FormData();
    f.set("comments",this.postform.value["comments"])
    f.set("postImg",this.imageFile.nativeElement.files[0])
    this.svc.PostPost(f);
    //this one must use this bc if u use the form one , when u click it instantly moves there.
    this.r.navigate(['/posts']);
  }
}
