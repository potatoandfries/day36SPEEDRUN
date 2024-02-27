import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormComponent } from './components/form.component';
import { ListComponent } from './components/list.component';

//super important this one no need to put slashes
const routes: Routes = [
  {path:"",component: FormComponent},
  {path:"posts",component: ListComponent},
  {path:"**",redirectTo:".",pathMatch:"full"}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
