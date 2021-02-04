import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CardDetailsComponent } from './card-details/card-details.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { WalletComponent } from './wallet/wallet.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { BookingComponent } from './booking/booking.component';
import { CheckinComponent } from './checkin/checkin.component';
import { CheckoutComponent } from "./checkout/checkout.component";

const routes: Routes = [
  {path:'customer/register',component:RegisterComponent},
  {path:'customer/login',component:LoginComponent},
  {path:'dashboard',component:DashboardComponent},
  {path:'customer/wallet/:id',component:WalletComponent},
  {path:'customer/card/:id',component:CardDetailsComponent},
  {path:'parking/booking/:id',component:BookingComponent},
  {path:'customer/checkin/:id', component:CheckinComponent},
  {path:'customer/checkout/:id', component:CheckoutComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

//export const routingComponents = [RegisterComponent,LoginComponent,WalletComponent,CardDetailsComponent,DashboardComponent];
