import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { API_BASE_URL } from '../config';
import { ToastService } from '../toast.service';

@Component({
  selector: 'app-events',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './events.html'
})
export class Events {
  events: any[] = [];
  persons: any[] = [];
  locations: any[] = [];
  form: any = {};
  base = `${API_BASE_URL}/events`;

  constructor(private http: HttpClient, private toast: ToastService) {
    this.load();
    this.loadPersons();
    this.loadLocations();
  }

  load() {
    this.http.get<any[]>(this.base).subscribe(r => this.events = r || []);
  }

  loadPersons() {
    this.http.get<any[]>(`${API_BASE_URL}/persons`).subscribe(r => this.persons = r || []);
  }

  loadLocations() {
    this.http.get<any[]>(`${API_BASE_URL}/locations`).subscribe(r => this.locations = r || []);
  }

  save() {
    if (!this.form.name || !this.form.scheduledAt || !this.form.person_id || !this.form.location_id) {
      this.toast.show('Preencha todos os campos obrigatórios', 'error');
      return;
    }
    
    if (this.form.id) {
      this.http.put(`${this.base}/${this.form.id}`, this.form).subscribe(
        () => {
          this.toast.show('Evento atualizado com sucesso!', 'success');
          this.form = {};
          this.load();
        },
        () => this.toast.show('Erro ao atualizar evento', 'error')
      );
    } else {
      this.http.post(this.base, this.form).subscribe(
        () => {
          this.toast.show('Evento criado com sucesso!', 'success');
          this.form = {};
          this.load();
        },
        () => this.toast.show('Erro ao criar evento', 'error')
      );
    }
  }

  edit(item: any) { this.form = { ...item }; }

  remove(id: any) {
    this.http.delete(`${this.base}/${id}`).subscribe(
      () => {
        this.toast.show('Evento deletado com sucesso!', 'success');
        this.load();
      },
      () => this.toast.show('Erro ao deletar evento', 'error')
    );
  }
}
