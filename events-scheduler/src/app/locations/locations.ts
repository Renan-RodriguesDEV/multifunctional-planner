import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { API_BASE_URL } from '../config';
import { ToastService } from '../toast.service';

@Component({
  selector: 'app-locations',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './locations.html'
})
export class Locations {
  items: any[] = [];
  form: any = {};
  base = `${API_BASE_URL}/locations`;

  constructor(private http: HttpClient, private toast: ToastService) { this.load(); }

  load() { this.http.get<any[]>(this.base).subscribe(r => this.items = r || []); }

  save() {
    if (!this.form.address) {
      this.toast.show('Preencha o endereço', 'error');
      return;
    }
    if (this.form.id) this.http.put(`${this.base}/${this.form.id}`, this.form).subscribe(
      () => { this.toast.show('Localização atualizada!', 'success'); this.form = {}; this.load(); },
      () => this.toast.show('Erro ao atualizar', 'error')
    );
    else this.http.post(this.base, this.form).subscribe(
      () => { this.toast.show('Localização criada!', 'success'); this.form = {}; this.load(); },
      () => this.toast.show('Erro ao criar', 'error')
    );
  }

  edit(i: any){ this.form = { ...i }; }
  remove(id: any){ this.http.delete(`${this.base}/${id}`).subscribe(
    () => { this.toast.show('Localização deletada!', 'success'); this.load(); },
    () => this.toast.show('Erro ao deletar', 'error')
  ); }
}
