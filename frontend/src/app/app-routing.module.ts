import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    loadComponent: () => import("./landing/landing.component").then(mod => mod.LandingComponent)
  },
  {
    path: 'sign-in',
    loadComponent: () => import("./sign-in/sign-in.component").then(mod => mod.SignInComponent)
  },
  {
    path: 'sign-up',
    loadComponent: () => import("./sign-up/sign-up.component").then(mod => mod.SignUpComponent)
  },
  {
    path: '**',
    pathMatch: 'full',
    loadComponent: () => import("./not-found/not-found.component").then(mod => mod.NotFoundComponent)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
