import { Injectable } from '@angular/core';
import { signal } from '@angular/core';

export interface Toast {
  id: string;
  message: string;
  type: 'success' | 'error';
}

@Injectable({
  providedIn: 'root'
})
export class ToastService {
  toasts = signal<Toast[]>([]);

  show(message: string, type: 'success' | 'error' = 'success', duration = 3000) {
    const id = Math.random().toString(36).substr(2, 9);
    const toast: Toast = { id, message, type };
    
    this.toasts.update(t => [...t, toast]);
    
    setTimeout(() => {
      this.toasts.update(t => t.filter(x => x.id !== id));
    }, duration);
  }

  remove(id: string) {
    this.toasts.update(t => t.filter(x => x.id !== id));
  }
}
