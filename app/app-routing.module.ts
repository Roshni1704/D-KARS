import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CardDetailsComponent } from './card-details/card-details.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { WalletComponent } from './wallet/wallet.component';

const routes: Routes = [
  {path:'customer/add',component:RegisterComponent},
  {path:'customer/login',component:LoginComponent},
  {path:'customer/wallet/:id',component:WalletComponent},
  {path:'customer/card/:id',component:CardDetailsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

export const routingComponents = [RegisterComponent,LoginComponent,WalletComponent,CardDetailsComponent];
