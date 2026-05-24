import { Routes } from '@angular/router';
import { Events } from './events/events';
import { Persons } from './persons/persons';
import { Locations } from './locations/locations';

export const routes: Routes = [
  { path: '', redirectTo: '/events', pathMatch: 'full' },
  { path: 'events', component: Events },
  { path: 'locations', component: Locations },
  { path: 'persons', component: Persons },
];
