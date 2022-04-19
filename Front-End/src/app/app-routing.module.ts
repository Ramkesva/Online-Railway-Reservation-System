import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddtrainsComponent } from './addtrains/addtrains.component';
import { AdminhomeComponent } from './adminhome/adminhome.component';
import { HomeComponent } from './home/home.component';
import { LoginadminComponent } from './loginadmin/loginadmin.component';
import { LoginuserComponent } from './loginuser/loginuser.component';
import { ManagetrainsComponent } from './managetrains/managetrains.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { RegisteruserComponent } from './registeruser/registeruser.component';
import { SearchtrainsComponent } from './searchtrains/searchtrains.component';
import { UpdatetrainsComponent } from './updatetrains/updatetrains.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { UserdashboardComponent } from './userdashboard/userdashboard.component';
import { UserhomeComponent } from './userhome/userhome.component';

const routes: Routes = [{ path: '', redirectTo:'home', pathMatch: 'full'},
                        { path:'home', component:HomeComponent},
                        { path:'adminhome', component:AdminhomeComponent},
                        { path:'userhome', component:UserhomeComponent},
                        { path:'userdashboard', component:UserdashboardComponent},
                        { path:'userreg', component:RegisteruserComponent},
                        { path:'userLogin', component:LoginuserComponent},
                        { path:'AdminLogin', component:LoginadminComponent},
                        { path:'addtrains', component:AddtrainsComponent},
                        { path:'managetrains', component:ManagetrainsComponent},
                        { path:'updatetrains/:trainNo', component:UpdatetrainsComponent},
                        { path:'searchtrains', component:SearchtrainsComponent},
                        { path:'trainsdetails', component:UserDetailsComponent},
                        { path:'**', component: PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

export const routingComponents = [UserDetailsComponent,PageNotFoundComponent];
