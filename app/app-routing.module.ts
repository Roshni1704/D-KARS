import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CardDetailsComponent } from './card-details/card-details.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { WalletComponent } from './wallet/wallet.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { BookingComponent } from './booking/booking.component';
import { HomeComponent } from './home/home.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { DisplayParkingLotsComponent } from './display-parking-lots/display-parking-lots.component';
import { ParkingComponent } from './parking/parking.component';
import { CheckinComponent } from './checkin/checkin.component';


const routes: Routes = [
  {path:'customer/add',component:RegisterComponent},
  {path:'customer/home',component:HomeComponent},
  {path:'customer/login',component:LoginComponent},
  {path:'customer/dashboard',component:DashboardComponent},
  {path:'customer/wallet/:id',component:WalletComponent},
  {path:'customer/card/:id',component:CardDetailsComponent},
  {path:'booking/:id',component:BookingComponent},
  {path:'parking',component:ParkingComponent},
  {path:'parking/details/:location',component:DisplayParkingLotsComponent},
  {path:'checkout/:id',component:CheckoutComponent},
  {path:'checkin/:id',component:CheckinComponent},
  {path:'home',component:HomeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
